package som

import org.eltech.ddm.clustering.cdbase.CDBaseModelTest
import org.eltech.ddm.clustering.{AggregationFunction, ClusteringMiningModel}
import org.eltech.ddm.handlers.thread.{MultiThreadedExecutionEnvironment, ThreadSettings}
import org.eltech.ddm.miningcore.algorithms.MiningAlgorithm
import org.eltech.ddm.clustering.ClusteringMiningModel
import org.eltech.ddm.handlers.ExecutionSettings
import org.eltech.ddm.handlers.thread.MultiThreadedExecutionEnvironment
import org.eltech.ddm.handlers.thread.ThreadSettings
import org.eltech.ddm.inputdata.DataSplitType
import org.eltech.ddm.miningcore.algorithms.MiningAlgorithm
import org.eltech.ddm.miningcore.miningfunctionsettings.DataProcessingStrategy
import org.eltech.ddm.miningcore.miningfunctionsettings.MiningModelProcessingStrategy
import org.eltech.ddm.miningcore.miningtask.EMiningBuildTask
import org.scalatest.Ignore

class SOMMiningModelParallelTest extends CDBaseModelTest {
  private val NUMBER_HANDLERS = 4
  val miningAlgorithmSettings: SOMAlgorithmSettings = new SOMAlgorithmSettings()
  miningAlgorithmSettings.setAlgorithm("SOM")
  miningAlgorithmSettings.setMaxNumberOfIterations(200)
  miningAlgorithmSettings.setEps(0.01)


  "SOMMiningModel hor parallel" should "work correctly on Iris Dataset" in {
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


    miningAlgorithmSettings.setDataSplitType(DataSplitType.block)
    miningAlgorithmSettings.setDataProcessingStrategy(DataProcessingStrategy.SeparatedDataSet)
    miningAlgorithmSettings.setModelProcessingStrategy(MiningModelProcessingStrategy.SeparatedMiningModel)
    miningAlgorithmSettings.setNumberHandlers(NUMBER_HANDLERS)

    val executionSettings = new ThreadSettings
    executionSettings.setNumberHandlers(NUMBER_HANDLERS)
    executionSettings.setDataSet(inputData)

    val algorithm: MiningAlgorithm = new SelfOrganizingMapHorParallelAlgorithm()(miningSettings)
    val environment = new MultiThreadedExecutionEnvironment(executionSettings, algorithm)
    miningAlgorithmSettings.setEnvironment(environment)

    val buildTask = new EMiningBuildTask
    buildTask.setInputStream(inputData)
    buildTask.setMiningAlgorithm(algorithm)
    buildTask.setMiningSettings(miningSettings)
    buildTask.setExecutionEnvironment(environment)
    model = buildTask.execute.asInstanceOf[SOMMiningModel]


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

  "SOMMiningModel ver parallel" should "work correctly on Iris Dataset" in {
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


    miningAlgorithmSettings.setDataSplitType(DataSplitType.block)
    miningAlgorithmSettings.setDataProcessingStrategy(DataProcessingStrategy.SingleDataSet)
    miningAlgorithmSettings.setModelProcessingStrategy(MiningModelProcessingStrategy.SingleMiningModel)
    miningAlgorithmSettings.setNumberHandlers(NUMBER_HANDLERS)

    val executionSettings = new ThreadSettings
    executionSettings.setNumberHandlers(NUMBER_HANDLERS)
    executionSettings.setDataSet(inputData)

    val algorithm: MiningAlgorithm = new SelfOrganizingMapVerParallelAlgorithm()(miningSettings)
    val environment = new MultiThreadedExecutionEnvironment(executionSettings, algorithm)
    miningAlgorithmSettings.setEnvironment(environment)

    val buildTask = new EMiningBuildTask
    buildTask.setInputStream(inputData)
    buildTask.setMiningAlgorithm(algorithm)
    buildTask.setMiningSettings(miningSettings)
    buildTask.setExecutionEnvironment(environment)
    model = buildTask.execute.asInstanceOf[SOMMiningModel]


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

  "SOMMiningModel bad ver parallel" should "work correctly on Iris Dataset" in {
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


    miningAlgorithmSettings.setDataSplitType(DataSplitType.block)
    miningAlgorithmSettings.setDataProcessingStrategy(DataProcessingStrategy.SingleDataSet)
    miningAlgorithmSettings.setModelProcessingStrategy(MiningModelProcessingStrategy.SingleMiningModel)
    miningAlgorithmSettings.setNumberHandlers(NUMBER_HANDLERS)

    val executionSettings = new ThreadSettings
    executionSettings.setNumberHandlers(NUMBER_HANDLERS)
    executionSettings.setDataSet(inputData)

    val algorithm: MiningAlgorithm = new SelfOrganizingMapBadVerParallelAlgorithm()(miningSettings)
    val environment = new MultiThreadedExecutionEnvironment(executionSettings, algorithm)
    miningAlgorithmSettings.setEnvironment(environment)

    val buildTask = new EMiningBuildTask
    buildTask.setInputStream(inputData)
    buildTask.setMiningAlgorithm(algorithm)
    buildTask.setMiningSettings(miningSettings)
    buildTask.setExecutionEnvironment(environment)
    model = buildTask.execute.asInstanceOf[SOMMiningModel]


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

  "SOMMiningModel hor parallel" should "work correctly on Wine Dataset" ignore {
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

    miningAlgorithmSettings.setDataSplitType(DataSplitType.block)
    miningAlgorithmSettings.setDataProcessingStrategy(DataProcessingStrategy.SingleDataSet)
    miningAlgorithmSettings.setModelProcessingStrategy(MiningModelProcessingStrategy.SingleMiningModel)
    miningAlgorithmSettings.setNumberHandlers(NUMBER_HANDLERS)

    val executionSettings = new ThreadSettings
    executionSettings.setNumberHandlers(NUMBER_HANDLERS)
    executionSettings.setDataSet(inputData)

    val algorithm: MiningAlgorithm = new SelfOrganizingMapVerParallelAlgorithm()(miningSettings)
    val environment = new MultiThreadedExecutionEnvironment(executionSettings, algorithm)
    miningAlgorithmSettings.setEnvironment(environment)

    val buildTask = new EMiningBuildTask
    buildTask.setInputStream(inputData)
    buildTask.setMiningAlgorithm(algorithm)
    buildTask.setMiningSettings(miningSettings)
    buildTask.setExecutionEnvironment(environment)
    model = buildTask.execute.asInstanceOf[SOMMiningModel]
    //    verifyModel4Iris(model)

    val wine = (0 until 9780).foldLeft(Seq.empty[Int]) { (results, i) =>
      val res = model.asInstanceOf[SOMMiningModel].getWinnerNeuronForVector(i).getIndexOfNeuron
      res +: results
    }

    val results = wine.groupBy(i => i).mapValues(_.size)

    println(s"Wine: $results")


  }
}