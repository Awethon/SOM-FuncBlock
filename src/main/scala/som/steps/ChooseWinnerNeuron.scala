package som.steps

import org.eltech.ddm.miningcore.algorithms.MiningBlock
import org.eltech.ddm.miningcore.miningfunctionsettings.EMiningFunctionSettings
import org.eltech.ddm.miningcore.miningmodel.EMiningModel
import som.{SOMMiningModel, VectorToNeuronDistance}

class ChooseWinnerNeuron(settings: EMiningFunctionSettings) extends MiningBlock(settings) {
  override def execute(model: EMiningModel): EMiningModel = {
    val somModel = model.asInstanceOf[SOMMiningModel]

    val iVec = somModel.getCurrentVectorIndex
    val iNeuron = somModel.getCurrentNeuronIndex
    val vecToNeuronDist: VectorToNeuronDistance  = somModel.getVectorToNeuronDistance(iVec, iNeuron)

    val winner = somModel.getWinnerNeuronForVector(iVec)

    if (vecToNeuronDist.getDistance < winner.getDistanceToNeuron) {
      winner.setIndexOfNeuron(iNeuron)
      winner.setDistanceToNeuron(vecToNeuronDist.getDistance)
//      System.out.println("Thread-" + Thread.currentThread.getName + " vector  " + iVec + " in cluster " + iNeuron + " d=" + v.getDistanceToNeuron)
    }

    somModel
  }
}

object  ChooseWinnerNeuron {
  def apply()(implicit settings: EMiningFunctionSettings): ChooseWinnerNeuron = new ChooseWinnerNeuron(settings)
}
