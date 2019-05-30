package som

import org.eltech.ddm.clustering.{ClusterSet, ClusteringFunctionSettings}
import org.eltech.ddm.clustering.cdbase.{CDBasedClusteringMiningModel => NeuronBasedClusteringMiningModel, Coordinate}
import org.eltech.ddm.miningcore.miningfunctionsettings.EMiningFunctionSettings
import org.eltech.ddm.miningcore.miningmodel.EMiningModel._


object SOMMiningModel {
  private[som] val CLUSTER_VECTOR = 2
  private[som] val INDEX_CLUSTER_VECTOR = Array(CLUSTER_VECTOR)
}

class SOMMiningModel(settings: EMiningFunctionSettings) extends NeuronBasedClusteringMiningModel(settings) {

  sets.add(SOMMiningModel.CLUSTER_VECTOR, new VectrosDistribution("Vectors distribution"))

  override def clone: SOMMiningModel = super.clone.asInstanceOf[SOMMiningModel]


  override def initModel(): Unit = {
    super.initModel()
    val numberOfNeurons = this.settings.asInstanceOf[ClusteringFunctionSettings].getMaxNumberOfClusters
    val numberOfVectors = this.getNumberVectors

    (0 until numberOfVectors).foreach { iCurrentVector =>
      this.addWinnerNeuronForVector(new WinnerNeuronForVector("v" + iCurrentVector))
      (0 until numberOfNeurons).foreach { iCurrentNeuron =>
        this.addVectorToNeuronDistance(iCurrentVector, new VectorToNeuronDistance("v" + iCurrentVector + ":c" + iCurrentNeuron))
      }
    }

  }

  def getWinnerNeuronForVector(iCurrentVector: Int): WinnerNeuronForVector = {
    val index1 = index(SOMMiningModel.CLUSTER_VECTOR, iCurrentVector)
    getElement(index1).asInstanceOf[WinnerNeuronForVector]
  }

  def addWinnerNeuronForVector(vec: WinnerNeuronForVector): Unit = {
    val index1 = index(SOMMiningModel.CLUSTER_VECTOR)
    addElement(index1, vec)
  }

  def getVectorToNeuronDistance(iCurrentVector: Int, iCurrentNeuron: Int): VectorToNeuronDistance = {
    val index1 = index(SOMMiningModel.CLUSTER_VECTOR, iCurrentVector, iCurrentNeuron)
    getElement(index1).asInstanceOf[VectorToNeuronDistance]
  }

  def addVectorToNeuronDistance(iCurrentVector: Int, vc: VectorToNeuronDistance): Unit = {
    val index1 = index(SOMMiningModel.CLUSTER_VECTOR, iCurrentVector)
    addElement(index1, vc)
  }

  def getNeuronWeight(iCurrentNeuron: Int, iAttr: Int): Coordinate = {
    super.getClusterCenterCoordinate(iCurrentNeuron, iAttr)
  }

  def getCurrentNeuronIndex: Int = super.getCurrentClusterIndex

  def getNeuronSet(): ClusterSet = this.getClusterSet
}