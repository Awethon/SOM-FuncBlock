package som.steps

import org.eltech.ddm.clustering.{ClusterSet, ClusteringMiningModel}
import org.eltech.ddm.miningcore.algorithms.{MiningBlock, MiningLoop}
import org.eltech.ddm.miningcore.miningfunctionsettings.EMiningFunctionSettings
import org.eltech.ddm.miningcore.miningmodel.EMiningModel
import som.SOMAlgorithmSettings

class WhileChangeNeuronsLoop(blocks: MiningBlock*)
                            (settings: EMiningFunctionSettings) extends MiningLoop(settings, blocks: _*) {

  private var maxNumberOfIterations = this.functionSettings.getAlgorithmSettings.asInstanceOf[SOMAlgorithmSettings].getMaxNumberOfIterations

  override protected def conditionLoop(model: EMiningModel): Boolean = {
    val cs = model.asInstanceOf[ClusteringMiningModel].getClusterSet
    cs.isChanged && cs.getNumberOfIterations < maxNumberOfIterations
  }

  override protected def initLoop(model: EMiningModel): EMiningModel = {
    val cs = model.asInstanceOf[ClusteringMiningModel].getClusterSet
    cs.setNumberOfIterations(0)
    cs.setChanged(true)
    model
  }

  override protected def afterIteration(model: EMiningModel): EMiningModel = {
    val cs = model.asInstanceOf[ClusteringMiningModel].getClusterSet
    cs.incNumberOfIterations()
    model
  }

  override protected def beforeIteration(model: EMiningModel): EMiningModel = {
    val cs = model.asInstanceOf[ClusteringMiningModel].getClusterSet
    cs.setChanged(false)
    model
  }
}

object WhileChangeNeuronsLoop {
  def apply(blocks: MiningBlock*)
           (implicit settings: EMiningFunctionSettings) = new WhileChangeNeuronsLoop(blocks: _*)(settings)
}
