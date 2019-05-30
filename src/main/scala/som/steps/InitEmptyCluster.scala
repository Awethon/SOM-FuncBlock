package som.steps

import org.eltech.ddm.clustering.ClusteringMiningModel
import org.eltech.ddm.miningcore.algorithms.MiningBlock
import org.eltech.ddm.miningcore.miningfunctionsettings.EMiningFunctionSettings
import org.eltech.ddm.miningcore.miningmodel.EMiningModel


class InitEmptyCluster(miningSettings: EMiningFunctionSettings) extends MiningBlock(miningSettings) {
  override protected def execute(model: EMiningModel): EMiningModel = {
    val clusteringMiningModel = model.asInstanceOf[ClusteringMiningModel]

    val iCluster = clusteringMiningModel.getCurrentClusterIndex
    val c = clusteringMiningModel.getCluster(iCluster)

    c.setVectorCount(0)
    clusteringMiningModel
  }
}

object InitEmptyCluster {
  def apply()(implicit miningSettings: EMiningFunctionSettings): InitEmptyCluster = new InitEmptyCluster(miningSettings)
}


