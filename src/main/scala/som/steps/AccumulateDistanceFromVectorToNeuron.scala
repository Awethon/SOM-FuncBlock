package som.steps

import org.eltech.ddm.inputdata.MiningInputStream
import org.eltech.ddm.miningcore.algorithms.MiningBlock
import org.eltech.ddm.miningcore.miningfunctionsettings.EMiningFunctionSettings
import org.eltech.ddm.miningcore.miningmodel.EMiningModel
import som.SOMMiningModel


class AccumulateDistanceFromVectorToNeuron(settings: EMiningFunctionSettings) extends MiningBlock(settings) {

  override protected def execute(inputData: MiningInputStream, model: EMiningModel): EMiningModel = {
    val somModel = model.asInstanceOf[SOMMiningModel]

    val iCurrentVector = somModel.getCurrentVectorIndex
    val iAttr = somModel.getCurrentAttributeIndex
    val iCurrentNeuron = somModel.getCurrentNeuronIndex
    val vec = inputData.getVector(iCurrentVector)
    val vecAttr = vec.getValue(iAttr)
    val neuronWeight = somModel.getNeuronWeight(iCurrentNeuron, iAttr).getValue
    val vecToNeuronDist = somModel.getVectorToNeuronDistance(iCurrentVector, iCurrentNeuron)
    if (vecToNeuronDist == null) println(s"NULL V$iCurrentVector to N$iCurrentNeuron dist")
    val v = Math.abs(vecAttr - neuronWeight)

    vecToNeuronDist.addDistance(v * v)
    //System.out.println("Thread-" + Thread.currentThread().getName() + " vc = " + vc);
    model
  }

}

object AccumulateDistanceFromVectorToNeuron {
  def apply()(implicit settings: EMiningFunctionSettings): AccumulateDistanceFromVectorToNeuron = new AccumulateDistanceFromVectorToNeuron(settings)
}
