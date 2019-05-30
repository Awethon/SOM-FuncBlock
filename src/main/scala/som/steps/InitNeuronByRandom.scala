package som.steps

import java.time.LocalTime
import java.util.Random

import org.eltech.ddm.inputdata.MiningInputStream
import org.eltech.ddm.miningcore.algorithms.MiningBlock
import org.eltech.ddm.miningcore.miningfunctionsettings.EMiningFunctionSettings
import org.eltech.ddm.miningcore.miningmodel.EMiningModel
import som.SOMMiningModel


class InitNeuronByRandom(settings: EMiningFunctionSettings) extends MiningBlock(settings) {
  final private val rand = new Random(3)

  override protected def execute(data: MiningInputStream, model: EMiningModel): EMiningModel = {
    val somModel = model.asInstanceOf[SOMMiningModel]

    val iAttr = somModel.getCurrentAttributeIndex
    val iNeuron = somModel.getCurrentNeuronIndex

    val weight = somModel.getNeuronWeight(iNeuron, iAttr)

    val countVectors = data.getVectorsNumber
    val indexVector = rand.nextInt(countVectors)
    val vector = data.getVector(indexVector)

    weight.setValue(vector.getValue(iAttr))

    model
  }
}

object InitNeuronByRandom {
  def apply()(implicit settings: EMiningFunctionSettings): InitNeuronByRandom = new InitNeuronByRandom(settings)
}
