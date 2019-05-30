package som.steps

import org.eltech.ddm.inputdata.MiningInputStream
import org.eltech.ddm.miningcore.algorithms.MiningBlock
import org.eltech.ddm.miningcore.miningfunctionsettings.EMiningFunctionSettings
import org.eltech.ddm.miningcore.miningmodel.EMiningModel
import som.{SOMMiningModel, VectorToNeuronDistance}


class CalculateDistanceFromVectorToNeuron(settings: EMiningFunctionSettings) extends MiningBlock(settings) {

  override protected def execute(inputData: MiningInputStream, model: EMiningModel): EMiningModel = {
    val somModel = model.asInstanceOf[SOMMiningModel]

    val iVec = somModel.getCurrentVectorIndex
    val iNeuron = somModel.getCurrentNeuronIndex
    val vc: VectorToNeuronDistance = somModel.getVectorToNeuronDistance(iVec, iNeuron)

    //System.out.println("Thread-" + Thread.currentThread().getName() + " vc  " + vc);
    vc.setDistance(Math.sqrt(vc.getDistance))

    model
  }

}

object CalculateDistanceFromVectorToNeuron {
  def apply()(implicit settings: EMiningFunctionSettings): CalculateDistanceFromVectorToNeuron = new CalculateDistanceFromVectorToNeuron(settings)
}
