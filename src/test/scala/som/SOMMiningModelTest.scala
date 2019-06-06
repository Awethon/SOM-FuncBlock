package som

import org.eltech.ddm.clustering.cdbase.CDBaseModelTest
import org.eltech.ddm.clustering.{AggregationFunction, ClusteringMiningModel}
import org.eltech.ddm.handlers.{ExecutionEnvironment, ExecutionSettings}
import org.eltech.ddm.handlers.thread.{MultiThreadedExecutionEnvironment, ThreadSettings}
import org.eltech.ddm.miningcore.algorithms.MiningAlgorithm


class SOMMiningModelTest extends CDBaseModelTest {
  val miningAlgorithmSettings: SOMAlgorithmSettings = new SOMAlgorithmSettings()
  miningAlgorithmSettings.setAlgorithm("SOM")
  miningAlgorithmSettings.setMaxNumberOfIterations(50)
  miningAlgorithmSettings.setEps(0.01)

  "SOMMiningModel" should "work correctly on Iris Dataset" in {
    // Open data source and get metadata:

    setInputData4Iris()
    setMiningSettings4Iris(miningAlgorithmSettings)

    // Assign settings:
    miningSettings.setMaxNumberOfClusters(3)
    miningSettings.setNeighbours(0, Array(1, 2))
    miningSettings.setNeighbours(1, Array(0, 2))
    miningSettings.setNeighbours(2, Array(0, 1))
    miningSettings.setAggregationFunction(AggregationFunction.euclidian)
    miningSettings.verify()

    // Create algorithm object with default values:
    val algorithm: MiningAlgorithm = new SelfOrganizingMapAlgorithm()(miningSettings)
    //algorithm.verify();

    // Build the mining model:
    model = algorithm.buildModel(inputData).asInstanceOf[ClusteringMiningModel]

//    verifyModel4Iris(model)

    val first = (0 until 50).foldLeft(Seq.empty[Int]) { (results, i) =>
      val res = model.asInstanceOf[SOMMiningModel].getWinnerNeuronForVector(i).getIndexOfNeuron
      res +: results
    }

    val second = (50 until 100).foldLeft(Seq.empty[Int]) { (results, i) =>
      val res = model.asInstanceOf[SOMMiningModel].getWinnerNeuronForVector(i).getIndexOfNeuron
      res +: results
    }

    val third = (100 until 150).foldLeft(Seq.empty[Int]) { (results, i) =>
      val res = model.asInstanceOf[SOMMiningModel].getWinnerNeuronForVector(i).getIndexOfNeuron
      res +: results
    }

    val firstResults = first.groupBy(i => i).mapValues(_.size)
    val secondResults = second.groupBy(i => i).mapValues(_.size)
    val thirdResults = third.groupBy(i => i).mapValues(_.size)

    println(s"first: $firstResults")
    println(s"second: $secondResults")
    println(s"third: $thirdResults")

  }

  "SOMMiningModel" should "work correctly on Wine Dataset" in {
    // Open data source and get metadata:

    setInputData4Wine()
    setMiningSettings4Wine(miningAlgorithmSettings)

    // Assign settings:
    miningSettings.setMaxNumberOfClusters(7)
    miningSettings.setNeighbours(0, Array(1))
    miningSettings.setNeighbours(1, Array(0, 2))
    miningSettings.setNeighbours(2, Array(1, 3))
    miningSettings.setNeighbours(3, Array(2, 4))
    miningSettings.setNeighbours(4, Array(3, 5))
    miningSettings.setNeighbours(5, Array(4, 6))
    miningSettings.setNeighbours(6, Array(5))
    miningSettings.setAggregationFunction(AggregationFunction.euclidian)
    miningSettings.verify()

    // Create algorithm object with default values:
    val algorithm: MiningAlgorithm = new SelfOrganizingMapAlgorithm()(miningSettings)
    //algorithm.verify();

    // Build the mining model:
    model = algorithm.buildModel(inputData).asInstanceOf[ClusteringMiningModel]

    //    verifyModel4Iris(model)

    val wine = (0 until 9780).foldLeft(Seq.empty[Int]) { (results, i) =>
      val res = model.asInstanceOf[SOMMiningModel].getWinnerNeuronForVector(i).getIndexOfNeuron
      res +: results
    }

    val results = wine.groupBy(i => i).mapValues(_.size)

    println(s"Wine: $results")


  }
}