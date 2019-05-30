package org.eltech.ddm.clustering.cdbase

import org.eltech.ddm.clustering.{Cluster, ClusterSet, ClusteringMiningModel, ClusteringMiningModelTest}
import org.eltech.ddm.miningcore.miningmodel.EMiningModel
import org.scalatest.Matchers


class CDBaseModelTest extends ClusteringMiningModelTest with Matchers {

  protected def verifyModel4Iris(model: ClusteringMiningModel): Unit = { // Show the clusters:
    showClusters(model.asInstanceOf[CDBasedClusteringMiningModel])
    model.getElement(ClusteringMiningModel.INDEX_CLUSTERS).size shouldBe 3
    model.getElement(EMiningModel.index(ClusteringMiningModel.CLUSTERS, 0)).asInstanceOf[Cluster].getVectorCount should be > 35L
    model.getElement(EMiningModel.index(ClusteringMiningModel.CLUSTERS, 1)).asInstanceOf[Cluster].getVectorCount should be > 35L
    model.getElement(EMiningModel.index(ClusteringMiningModel.CLUSTERS, 2)).asInstanceOf[Cluster].getVectorCount should be > 35L
  }

  protected def verifyModel4AzureIris(model: ClusteringMiningModel): Unit = {
    showClusters(model.asInstanceOf[CDBasedClusteringMiningModel])
    //        Assert.assertEquals(2, ((CDBasedClusteringMiningModel) model).getNumberOfClusters());
    //        Assert.assertTrue(((CDBasedClusteringMiningModel) model).getClusters()[0].getVectorCount() > 30);
    //        Assert.assertTrue(((CDBasedClusteringMiningModel) model).getClusters()[1].getVectorCount() > 30);
  }

  protected def verifyModel4TelescopeData(model: ClusteringMiningModel): Unit = {
    showClusters(model.asInstanceOf[CDBasedClusteringMiningModel])
    //		Assert.assertEquals(2, ((CDBasedClusteringMiningModel)model).getNumberOfClusters());
    //		Assert.assertTrue(((CDBasedClusteringMiningModel)model).getClusters()[0].getVectorCount() > 30);
    //		Assert.assertTrue(((CDBasedClusteringMiningModel)model).getClusters()[1].getVectorCount() > 30);
  }

  protected def verifyModel4MovieRatings(model: ClusteringMiningModel): Unit = {
    showClusters(model.asInstanceOf[CDBasedClusteringMiningModel])
  }

  protected def verifyModel4FlightOnTimePerformance(model: ClusteringMiningModel): Unit = {
    showClusters(model.asInstanceOf[CDBasedClusteringMiningModel])
  }

  protected def verifyModel4FlightDelaysData(model: ClusteringMiningModel): Unit = {
    showClusters(model.asInstanceOf[CDBasedClusteringMiningModel])
  }

  def showClusters(clustModel: CDBasedClusteringMiningModel): Unit = {
    System.out.println("number of iterations: " + model.getElement(ClusteringMiningModel.INDEX_CLUSTERS).asInstanceOf[ClusterSet].getNumberOfIterations)
    //        MiningModelElement set =  model.getElement(KMeansMiningModel.INDEX_CLUSTER_VECTOR);
    //        for(int i = 0; i < set.size(); i++){
    //            MiningModelElement elem = set.getElement(i);
    //            System.out.println(elem);
    //        }
    super.showClusters(clustModel)
  }
}
