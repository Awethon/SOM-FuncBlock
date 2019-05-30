package som.steps

import org.eltech.ddm.inputdata.MiningInputStream
import org.eltech.ddm.miningcore.algorithms.MiningBlock
import org.eltech.ddm.miningcore.miningmodel.EMiningModel
import som.{SOMFunctionSettings, SOMMiningModel}

class AdjustNeuronWeights(settings: SOMFunctionSettings) extends MiningBlock(settings) {
  private val selfOrganizingPhaseEpoches = 10000 //the iteration number in the phase 1: self-organizing phase
  private val eta0 = 0.1
  var sigma0 = 0.0
  var tau1 = 0.0

  private def eta(n: Int): Double = {
    var result = eta0 * Math.exp(-(n + 1) / (tau1 * 10))
    if (result < 0.01) result = 0.01
    result
  }

  private def h(distance: Double, n: Int): Double = {
    if (n < selfOrganizingPhaseEpoches) { //self-organizing phase
      val sigma = sigma0 * Math.exp(-n / tau1)
      Math.exp(-distance * distance * 50000 / (2 * sigma * sigma))
    } else { //convergence phase
      1
    }
  }

  override def execute(dataSet: MiningInputStream, model: EMiningModel): EMiningModel = {
    val somModel = model.asInstanceOf[SOMMiningModel]

    val rows: Int = dataSet.getVectorsNumber
    val cols: Int = dataSet.getLogicalData.getAttributesNumber
    sigma0 = 0.707 * Math.sqrt((rows - 1) * (rows - 1) + (cols - 1) * (cols - 1))
    tau1 = 1000 / Math.log(sigma0)




    val iVec = somModel.getCurrentVectorIndex
    val iAttr = somModel.getCurrentAttributeIndex

    val winnerNeuron = somModel.getWinnerNeuronForVector(iVec)

    val iWinnerNeuron = winnerNeuron.getIndexOfNeuron

    (iWinnerNeuron +: settings.getNeighbours(iWinnerNeuron)).foreach { iNeuron =>
      val weight = somModel.getNeuronWeight(iNeuron, iAttr)

      val currentIteration = somModel.getNeuronSet().getNumberOfIterations
      val distanceBetweenNeurons = Math.sqrt((0 until cols).foldLeft(0.0)((distsqr, iAttr) => {
        val d = somModel.getNeuronWeight(iWinnerNeuron, iAttr).getValue - somModel.getNeuronWeight(iNeuron, iAttr).getValue
        distsqr + (d * d)
      }))

      val attrValue = dataSet.getVector(iVec).getValue(iAttr)

      val neighbourhood = h(distanceBetweenNeurons, currentIteration)
      val learningRate = eta(currentIteration)
      val delta = learningRate * neighbourhood * (attrValue - weight.getValue)
      weight.setValue(weight.getValue + delta)
    }


    somModel.getNeuronSet().setChanged(true)

    somModel
  }
}

object AdjustNeuronWeights {
  def apply()(implicit settings: SOMFunctionSettings): AdjustNeuronWeights = new AdjustNeuronWeights(settings)
}
