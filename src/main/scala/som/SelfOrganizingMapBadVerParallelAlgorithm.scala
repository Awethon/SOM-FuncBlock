
package som

import org.eltech.ddm.clustering.ClusteringMiningModel.{INDEX_CLUSTERS => INDEX_NEURONS}
import org.eltech.ddm.inputdata.MiningInputStream
import org.eltech.ddm.miningcore.algorithms.{BlockExecuteTimingListner, MiningAlgorithm}
import org.eltech.ddm.miningcore.miningmodel.EMiningModel
import org.eltech.ddm.miningcore.miningmodel.EMiningModel.INDEX_ATTRIBUTE_SET
import som.steps._
import wrapper.{MiningLoopElement, MiningLoopVectors}

class SelfOrganizingMapBadVerParallelAlgorithm(implicit miningSettings: SOMFunctionSettings) extends MiningAlgorithm(miningSettings) {

  override def initBlocks(): Unit = {
    blocks =
      MiningSequence(
        MiningLoopElement(INDEX_NEURONS)(
          MiningLoopElement(INDEX_ATTRIBUTE_SET)(InitNeuronByRandom())
        ),
        WhileChangeNeuronsLoop(
            MiningLoopVectors(
              MiningLoopElement(INDEX_NEURONS)(
                MiningParallel(
                  MiningLoopElement(INDEX_ATTRIBUTE_SET)(AccumulateDistanceFromVectorToNeuron())
                ),
                CalculateDistanceFromVectorToNeuron(),
              ),
              MiningLoopElement(INDEX_NEURONS)(ChooseWinnerNeuron()),
              MiningParallel(
                MiningLoopElement(INDEX_ATTRIBUTE_SET)(AdjustNeuronWeights())
              )
            )
        )
      )

    blocks.addListenerExecute(new BlockExecuteTimingListner())
  }

  override def createModel(miningInputStream: MiningInputStream): EMiningModel = {
    new SOMMiningModel(miningSettings)
  }
}
