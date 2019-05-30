package som

import org.eltech.ddm.clustering.ClusteringFunctionSettings
import org.eltech.ddm.miningcore.miningdata.ELogicalData

class SOMFunctionSettings(ld: ELogicalData) extends ClusteringFunctionSettings(ld) {
  private var neuronNeighbours: Array[Array[Int]] = null

  override def setMaxNumberOfClusters(maxClusters: Int): Unit = {
    super.setMaxNumberOfClusters(maxClusters)
    neuronNeighbours = Array.ofDim(maxClusters)

  }

  def setNeighbours(iNeuron: Int, neighbours: Array[Int]): Unit = {
    neuronNeighbours(iNeuron) = neighbours
  }

  def getNeighbours(iNeuron: Int): Array[Int] = {
    neuronNeighbours(iNeuron)
  }
}

