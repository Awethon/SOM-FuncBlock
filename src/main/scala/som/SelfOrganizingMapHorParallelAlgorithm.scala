
package som

import org.eltech.ddm.clustering.ClusteringMiningModel.{INDEX_CLUSTERS => INDEX_NEURONS}
import org.eltech.ddm.inputdata.MiningInputStream
import org.eltech.ddm.miningcore.algorithms.{BlockExecuteTimingListner, MiningAlgorithm, MiningBlock}
import org.eltech.ddm.miningcore.miningmodel.EMiningModel
import org.eltech.ddm.miningcore.miningmodel.EMiningModel.INDEX_ATTRIBUTE_SET
import som.steps._
import wrapper.{MiningLoopElement, MiningLoopVectors, MiningParallel, MiningSequence}

class SelfOrganizingMapHorParallelAlgorithm(implicit miningSettings: SOMFunctionSettings) extends MiningAlgorithm(miningSettings) {

  override def initBlocks(): Unit = {
    blocks =
      MiningSequence(
        MiningLoopElement(INDEX_NEURONS)(
          MiningLoopElement(INDEX_ATTRIBUTE_SET)(InitNeuronByRandom())
        ),
        WhileChangeNeuronsLoop(
          MiningParallel(
            MiningLoopVectors(
              MiningLoopElement(INDEX_NEURONS)(
                MiningLoopElement(INDEX_ATTRIBUTE_SET)(
                  AccumulateDistanceFromVectorToNeuron()
                ),
                CalculateDistanceFromVectorToNeuron(),
              ),
              MiningLoopElement(INDEX_NEURONS)(ChooseWinnerNeuron()),
              MiningLoopElement(INDEX_ATTRIBUTE_SET)(AdjustNeuronWeights())
            )
          ), new MiningBlock(miningSettings) {
            override def execute(model: EMiningModel): EMiningModel = {
              model.asInstanceOf[SOMMiningModel].getNeuronSet().setChanged(true)
              model
            }
          }
        )
      )

    blocks.addListenerExecute(new BlockExecuteTimingListner())
  }

  override def createModel(miningInputStream: MiningInputStream): EMiningModel = {
    new SOMMiningModel(miningSettings)
  }
}
