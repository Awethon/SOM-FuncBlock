package som.steps

import org.eltech.ddm.inputdata.MiningInputStream
import org.eltech.ddm.miningcore.algorithms.MiningBlock
import org.eltech.ddm.miningcore.miningfunctionsettings.EMiningFunctionSettings
import org.eltech.ddm.miningcore.miningmodel.EMiningModel
import som.SOMMiningModel

class DebugIrisClusterMovement(settings: EMiningFunctionSettings) extends MiningBlock(settings) {
  override def execute(dataSet: MiningInputStream, model: EMiningModel): EMiningModel = {
    val somModel = model.asInstanceOf[SOMMiningModel]

    val iCluster = somModel.getCurrentClusterIndex

    val v0 = somModel.getClusterCenterCoordinate(iCluster, 0).getValue
    val v1 = somModel.getClusterCenterCoordinate(iCluster, 1).getValue
    val v2 = somModel.getClusterCenterCoordinate(iCluster, 2).getValue
    val v3 = somModel.getClusterCenterCoordinate(iCluster, 3).getValue

    println(s"Cluster $iCluster --- $v0, $v1, $v2, $v3")

    model
  }
}