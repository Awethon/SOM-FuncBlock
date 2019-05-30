package org.eltech.ddm.clustering

import org.eltech.ddm.clustering.cdbase.CDBasedClusteringMiningModel
import org.eltech.ddm.inputdata.MiningArrayStream
import org.eltech.ddm.inputdata.MiningInputStream
import org.eltech.ddm.inputdata.file.MiningArffStream
import org.eltech.ddm.miningcore.MiningException
import org.eltech.ddm.miningcore.miningdata.ELogicalAttribute
import org.eltech.ddm.miningcore.miningdata.ELogicalData
import org.eltech.ddm.miningcore.miningdata.PhysicalAttribute
import org.eltech.ddm.miningcore.miningdata.assignment.AssignmentManager
import org.eltech.ddm.miningcore.miningdata.assignment.AttributeAssignmentType
import org.eltech.ddm.miningcore.miningfunctionsettings.EMiningAlgorithmSettings
import org.omg.java.cwm.analysis.datamining.miningcore.miningdata.AttributeType
import java.util
import java.util.{HashMap, Map}

import som.SOMFunctionSettings


class ClusteringMiningModelTest extends org.scalatest.FlatSpec {
  protected var inputData: MiningInputStream = null
  protected var logicalData: ELogicalData = null
  implicit protected var miningSettings: SOMFunctionSettings = null
  protected var model: ClusteringMiningModel = null

  // ==== Methods for data attributes Iris ===============
  @throws[MiningException]
  protected def setInputData4Iris(): Unit = { // Load input data
    inputData = new MiningArffStream("C:\\Users\\mi\\iris.arff")
    //get map to user interface (direct) - for settings
    val autoGeneratedAttributes = inputData.getMapAttributes
    //assignment settings
    val assignmentManager = new AssignmentManager(AttributeAssignmentType.DirectAttributeAssignment)
    val physatt_1 = new PhysicalAttribute
    //		physatt_1.setDataType(AttributeDataType.stringType);
    physatt_1.setName("sepallength")
    val physatt_2 = new PhysicalAttribute
    //		physatt_2.setDataType(AttributeDataType.stringType);
    physatt_2.setName("sepalwidth")
    val physatt_3 = new PhysicalAttribute
    //		physatt_3.setDataType(AttributeDataType.stringType);
    physatt_3.setName("petallength")
    val physatt_4 = new PhysicalAttribute
    //		physatt_4.setDataType(AttributeDataType.doubleType);
    physatt_4.setName("petalwidth")
    val attribute_l_1 = new ELogicalAttribute("sepallength", AttributeType.numerical)
    val attribute_l_2 = new ELogicalAttribute("sepalwidth", AttributeType.numerical)
    val attribute_l_3 = new ELogicalAttribute("petallength", AttributeType.numerical)
    val attribute_l_4 = new ELogicalAttribute("petalwidth", AttributeType.numerical)
    val new_user_map_attributes = new util.HashMap[PhysicalAttribute, ELogicalAttribute]
    new_user_map_attributes.put(physatt_1, attribute_l_1)
    new_user_map_attributes.put(physatt_2, attribute_l_2)
    new_user_map_attributes.put(physatt_3, attribute_l_3)
    new_user_map_attributes.put(physatt_4, attribute_l_4)
    //assignmentManager.putLogicalAttributes(new_user_map_attributes);
    assignmentManager.setAttributeSettings(new_user_map_attributes)
    inputData.setAssignmentManager(assignmentManager)
    inputData = new MiningArrayStream(inputData)
  }

  @throws[MiningException]
  protected def setMiningSettings4Iris(algorithmSettings: EMiningAlgorithmSettings): Unit = {
    val logicalData = inputData.getLogicalData
    //Create settings for classification
    miningSettings = new SOMFunctionSettings(logicalData)
    miningSettings.setAlgorithmSettings(algorithmSettings)
    miningSettings.verify
  }



  // ==== Methods for data attributes Wine ===============
  @throws[MiningException]
  protected def setInputData4Wine(): Unit = { // Load input data
    inputData = new MiningArffStream("C:\\Users\\mi\\wine-quality-white.arff")
    //get map to user interface (direct) - for settings
    val autoGeneratedAttributes = inputData.getMapAttributes
    //assignment settings
    val assignmentManager = new AssignmentManager(AttributeAssignmentType.DirectAttributeAssignment)

    val new_user_map_attributes = new util.HashMap[PhysicalAttribute, ELogicalAttribute]

    (1 to 11).foreach { i =>
      val physatt_i = new PhysicalAttribute
      //		physatt_1.setDataType(AttributeDataType.stringType);
      physatt_i.setName(s"V$i")
      val attribute_l_i = new ELogicalAttribute(s"V$i", AttributeType.numerical)
      new_user_map_attributes.put(physatt_i, attribute_l_i)
    }

    //assignmentManager.putLogicalAttributes(new_user_map_attributes);
    assignmentManager.setAttributeSettings(new_user_map_attributes)
    inputData.setAssignmentManager(assignmentManager)
    inputData = new MiningArrayStream(inputData)
  }

  @throws[MiningException]
  protected def setMiningSettings4Wine(algorithmSettings: EMiningAlgorithmSettings): Unit = {
    val logicalData = inputData.getLogicalData
    //Create settings for classification
    miningSettings = new SOMFunctionSettings(logicalData)
    miningSettings.setAlgorithmSettings(algorithmSettings)
    miningSettings.verify
  }


  // ==== Methods for data attributes Azure Iris ===============
  @throws[MiningException]
  protected def setInputData4AzureIris(): Unit = {
    inputData = new MiningArffStream("..\\data\\arff\\Azure\\Iris Two Class Data(V100A5).arff")
    val autoGeneratedAttributes = inputData.getMapAttributes
    val assignmentManager = new AssignmentManager(AttributeAssignmentType.DirectAttributeAssignment)
    val physatt_1 = new PhysicalAttribute
    physatt_1.setName("sepal-length")
    val physatt_2 = new PhysicalAttribute
    physatt_2.setName("sepal-width")
    val physatt_3 = new PhysicalAttribute
    physatt_3.setName("petal-length")
    val physatt_4 = new PhysicalAttribute
    physatt_4.setName("petal-width")
    val attribute_l_1 = new ELogicalAttribute("sepallength", AttributeType.numerical)
    val attribute_l_2 = new ELogicalAttribute("sepalwidth", AttributeType.numerical)
    val attribute_l_3 = new ELogicalAttribute("petallength", AttributeType.numerical)
    val attribute_l_4 = new ELogicalAttribute("petalwidth", AttributeType.numerical)
    val new_user_map_attributes = new util.HashMap[PhysicalAttribute, ELogicalAttribute]
    new_user_map_attributes.put(physatt_1, attribute_l_1)
    new_user_map_attributes.put(physatt_2, attribute_l_2)
    new_user_map_attributes.put(physatt_3, attribute_l_3)
    new_user_map_attributes.put(physatt_4, attribute_l_4)
    assignmentManager.setAttributeSettings(new_user_map_attributes)
    inputData.setAssignmentManager(assignmentManager)
    inputData = new MiningArrayStream(inputData)
  }

  // ==== Methods for data attributes Azure Weather ===============
  @throws[MiningException]
  protected def setInputData4AzureWeather(): Unit = {
    inputData = new MiningArffStream("..\\data\\arff\\Azure\\Weather Dataset(V406516A26).arff")
    //		//get map to user interface (direct) - for settings
    //		Map<PhysicalAttribute, ELogicalAttribute> autoGeneratedAttributes = inputData.getMapAttributes();
    //
    //		//assignment settings
    //		AssignmentManager assignmentManager = new AssignmentManager(AttributeAssignmentType.DirectAttributeAssignment);
    //
    //		PhysicalAttribute physatt_1 = new PhysicalAttribute();
    ////		physatt_1.setDataType(AttributeDataType.stringType);
    //		physatt_1.setName("sepal-length");
    //
    //		PhysicalAttribute physatt_2 = new PhysicalAttribute();
    ////		physatt_2.setDataType(AttributeDataType.stringType);
    //		physatt_2.setName("sepal-width");
    //
    //		PhysicalAttribute physatt_3 = new PhysicalAttribute();
    ////		physatt_3.setDataType(AttributeDataType.stringType);
    //		physatt_3.setName("petal-length");
    //
    //		PhysicalAttribute physatt_4 = new PhysicalAttribute();
    ////		physatt_4.setDataType(AttributeDataType.doubleType);
    //		physatt_4.setName("petal-width");
    //
    //		ELogicalAttribute attribute_l_1 = new ELogicalAttribute("sepallength", AttributeType.numerical);
    //		ELogicalAttribute attribute_l_2 = new ELogicalAttribute("sepalwidth", AttributeType.numerical);
    //		ELogicalAttribute attribute_l_3 = new ELogicalAttribute("petallength", AttributeType.numerical);
    //		ELogicalAttribute attribute_l_4 = new ELogicalAttribute("petalwidth", AttributeType.numerical);
    //
    //		Map<PhysicalAttribute, ELogicalAttribute> new_user_map_attributes = new HashMap<PhysicalAttribute, ELogicalAttribute>();
    //		new_user_map_attributes.put(physatt_1, attribute_l_1);
    //		new_user_map_attributes.put(physatt_2, attribute_l_2);
    //		new_user_map_attributes.put(physatt_3, attribute_l_3);
    //		new_user_map_attributes.put(physatt_4, attribute_l_4);
    //
    //		//assignmentManager.putLogicalAttributes(new_user_map_attributes);
    //		assignmentManager.setAttributeSettings(new_user_map_attributes);
    //
    //		inputData.setAssignmentManager(assignmentManager);
    inputData = new MiningArrayStream(inputData)
  }

  @throws[MiningException]
  protected def setInputData4TimeSeries(): Unit = {
    inputData = new MiningArffStream("..\\data\\arff\\Azure\\Time series(V126A1).arff")
    val autoGeneratedAttributes = inputData.getMapAttributes
    val assignmentManager = new AssignmentManager(AttributeAssignmentType.DirectAttributeAssignment)
    val physatt_1 = new PhysicalAttribute
    physatt_1.setName("N1725")
    val attribute_l_1 = new ELogicalAttribute("N1725", AttributeType.numerical)
    val new_user_map_attributes = new util.HashMap[PhysicalAttribute, ELogicalAttribute]
    new_user_map_attributes.put(physatt_1, attribute_l_1)
    assignmentManager.setAttributeSettings(new_user_map_attributes)
    inputData.setAssignmentManager(assignmentManager)
    inputData = new MiningArrayStream(inputData)
  }

  // ==== Methods for data attributes Telescope Data ===============
  @throws[MiningException]
  protected def setInputData4TelescopeData(): Unit = {
    inputData = new MiningArffStream("..\\data\\arff\\Azure\\Telescope data(V19020A11).arff")
    val autoGeneratedAttributes = inputData.getMapAttributes
    val assignmentManager = new AssignmentManager(AttributeAssignmentType.DirectAttributeAssignment)
    val physatt_1 = new PhysicalAttribute
    physatt_1.setName("fLength")
    val physatt_2 = new PhysicalAttribute
    physatt_2.setName("fWidth")
    val physatt_3 = new PhysicalAttribute
    physatt_3.setName("fSize")
    val physatt_4 = new PhysicalAttribute
    physatt_4.setName("fConc")
    val physatt_5 = new PhysicalAttribute
    //		physatt_5.setDataType(AttributeDataType.doubleType);
    physatt_5.setName("fConcl")
    val physatt_6 = new PhysicalAttribute
    //		physatt_6.setDataType(AttributeDataType.doubleType);
    physatt_6.setName("fAsym")
    val physatt_7 = new PhysicalAttribute
    //		physatt_7.setDataType(AttributeDataType.doubleType);
    physatt_7.setName("fM3Long")
    val physatt_8 = new PhysicalAttribute
    //		physatt_8.setDataType(AttributeDataType.doubleType);
    physatt_8.setName("fM3Trans")
    val physatt_9 = new PhysicalAttribute
    //		physatt_9.setDataType(AttributeDataType.doubleType);
    physatt_9.setName("fAlpha")
    val physatt_10 = new PhysicalAttribute
    //		physatt_10.setDataType(AttributeDataType.doubleType);
    physatt_10.setName("fDist")
    val attribute_l_1 = new ELogicalAttribute("fLength", AttributeType.numerical)
    val attribute_l_2 = new ELogicalAttribute("fWidth", AttributeType.numerical)
    val attribute_l_3 = new ELogicalAttribute("fSize", AttributeType.numerical)
    val attribute_l_4 = new ELogicalAttribute("fConc", AttributeType.numerical)
    val attribute_l_5 = new ELogicalAttribute("fConcl", AttributeType.numerical)
    val attribute_l_6 = new ELogicalAttribute("fAsym", AttributeType.numerical)
    val attribute_l_7 = new ELogicalAttribute("fM3Long", AttributeType.numerical)
    val attribute_l_8 = new ELogicalAttribute("fM3Trans", AttributeType.numerical)
    val attribute_l_9 = new ELogicalAttribute("fAlpha", AttributeType.numerical)
    val attribute_l_10 = new ELogicalAttribute("fDist", AttributeType.numerical)
    val new_user_map_attributes = new util.HashMap[PhysicalAttribute, ELogicalAttribute]
    new_user_map_attributes.put(physatt_1, attribute_l_1)
    new_user_map_attributes.put(physatt_2, attribute_l_2)
    new_user_map_attributes.put(physatt_3, attribute_l_3)
    new_user_map_attributes.put(physatt_4, attribute_l_4)
    new_user_map_attributes.put(physatt_5, attribute_l_5)
    new_user_map_attributes.put(physatt_6, attribute_l_6)
    new_user_map_attributes.put(physatt_7, attribute_l_7)
    new_user_map_attributes.put(physatt_8, attribute_l_8)
    new_user_map_attributes.put(physatt_9, attribute_l_9)
    new_user_map_attributes.put(physatt_10, attribute_l_10)
    assignmentManager.setAttributeSettings(new_user_map_attributes)
    inputData.setAssignmentManager(assignmentManager)
    inputData = new MiningArrayStream(inputData)
  }

  @throws[MiningException]
  protected def setInputData4BreastCancerInfo(): Unit = {
    inputData = new MiningArffStream("..\\data\\arff\\Azure\\Breast Cancer Info (V102294A12).arff")
    val autoGeneratedAttributes = inputData.getMapAttributes
    val assignmentManager = new AssignmentManager(AttributeAssignmentType.DirectAttributeAssignment)
    val physatt_1 = new PhysicalAttribute
    physatt_1.setName("Col5")
    val physatt_2 = new PhysicalAttribute
    physatt_2.setName("Col8")
    val physatt_3 = new PhysicalAttribute
    physatt_3.setName("Col9")
    val physatt_4 = new PhysicalAttribute
    physatt_4.setName("Col10")
    val physatt_5 = new PhysicalAttribute
    physatt_5.setName("Col11")
    val attribute_l_1 = new ELogicalAttribute("Col5", AttributeType.numerical)
    val attribute_l_2 = new ELogicalAttribute("Col8", AttributeType.numerical)
    val attribute_l_3 = new ELogicalAttribute("Col9", AttributeType.numerical)
    val attribute_l_4 = new ELogicalAttribute("Col10", AttributeType.numerical)
    val attribute_l_5 = new ELogicalAttribute("Col11", AttributeType.numerical)
    val new_user_map_attributes = new util.HashMap[PhysicalAttribute, ELogicalAttribute]
    new_user_map_attributes.put(physatt_1, attribute_l_1)
    new_user_map_attributes.put(physatt_2, attribute_l_2)
    new_user_map_attributes.put(physatt_3, attribute_l_3)
    new_user_map_attributes.put(physatt_4, attribute_l_4)
    new_user_map_attributes.put(physatt_5, attribute_l_5)
    assignmentManager.setAttributeSettings(new_user_map_attributes)
    inputData.setAssignmentManager(assignmentManager)
    inputData = new MiningArrayStream(inputData)
  }

  @throws[MiningException]
  protected def setInputData4BreastCancerFeatures(): Unit = {
    inputData = new MiningArffStream("..\\data\\arff\\Azure\\Breast Cancer Features (V102294A118).arff")
    inputData = new MiningArrayStream(inputData)
  }

  @throws[MiningException]
  protected def setInputData4MovieRatings(): Unit = {
    inputData = new MiningArffStream("..\\data\\arff\\Azure\\Movie Ratings(V227472A4).arff")
    inputData = new MiningArrayStream(inputData)
  }

  @throws[MiningException]
  protected def setInputData4FlightDelaysData(): Unit = {
    inputData = new MiningArffStream("..\\data\\arff\\Azure\\Flight Delays Data(V2719418A14).arff")
    val autoGeneratedAttributes = inputData.getMapAttributes
    val assignmentManager = new AssignmentManager(AttributeAssignmentType.DirectAttributeAssignment)
    val physatt_1 = new PhysicalAttribute
    physatt_1.setName("OriginAirportID")
    val physatt_2 = new PhysicalAttribute
    physatt_2.setName("DestAirportID")
    val physatt_3 = new PhysicalAttribute
    physatt_3.setName("CRSDepTime")
    //		physatt_4.setName("DepDelay");
    val physatt_5 = new PhysicalAttribute
    physatt_5.setName("CRSArrTime")
    val attribute_l_1 = new ELogicalAttribute("OriginAirportID", AttributeType.numerical)
    val attribute_l_2 = new ELogicalAttribute("DestAirportID", AttributeType.numerical)
    val attribute_l_3 = new ELogicalAttribute("CRSDepTime", AttributeType.numerical)
    //		ELogicalAttribute attribute_l_4 = new ELogicalAttribute("DepDelay", AttributeType.numerical);
    val attribute_l_5 = new ELogicalAttribute("CRSArrTime", AttributeType.numerical)
    val new_user_map_attributes = new util.HashMap[PhysicalAttribute, ELogicalAttribute]
    new_user_map_attributes.put(physatt_1, attribute_l_1)
    new_user_map_attributes.put(physatt_2, attribute_l_2)
    new_user_map_attributes.put(physatt_3, attribute_l_3)
    new_user_map_attributes.put(physatt_5, attribute_l_5)
    assignmentManager.setAttributeSettings(new_user_map_attributes)
    inputData.setAssignmentManager(assignmentManager)
    inputData = new MiningArrayStream(inputData)
  }

  @throws[MiningException]
  protected def setInputData4FlightOnTimePerformance(): Unit = {
    inputData = new MiningArffStream("..\\data\\arff\\Azure\\Flight on-time performance (Raw)(V504397A18).arff")
    //		physatt_1.setName("OriginAirportID");
    //		physatt_2.setName("DestAirportID");
    //		physatt_3.setName("CRSDepTime");
    ////		PhysicalAttribute physatt_4 = new PhysicalAttribute();
    //////		physatt_4.setDataType(AttributeDataType.doubleType);
    ////		physatt_4.setName("DepDelay");
    //
    //		PhysicalAttribute physatt_5 = new PhysicalAttribute();
    ////		physatt_5.setDataType(AttributeDataType.doubleType);
    //		physatt_5.setName("CRSArrTime");
    //		ELogicalAttribute attribute_l_1 = new ELogicalAttribute("OriginAirportID", AttributeType.numerical);
    //		ELogicalAttribute attribute_l_2 = new ELogicalAttribute("DestAirportID", AttributeType.numerical);
    //		ELogicalAttribute attribute_l_3 = new ELogicalAttribute("CRSDepTime", AttributeType.numerical);
    ////		ELogicalAttribute attribute_l_4 = new ELogicalAttribute("DepDelay", AttributeType.numerical);
    //		ELogicalAttribute attribute_l_5 = new ELogicalAttribute("CRSArrTime", AttributeType.numerical);
    ////		new_user_map_attributes.put(physatt_4, attribute_l_4);
    //		new_user_map_attributes.put(physatt_5, attribute_l_5);
    inputData = new MiningArrayStream(inputData)
  }

  /**
    * Shows clusters.
    *
    * @param clustModel
    * clustering model to show
    * @throws MiningException
    * cannot show clusters
    */
  @throws[MiningException]
  def showClusters(clustModel: ClusteringMiningModel): Unit = {
    System.out.println("number of clusters: " + clustModel.getElement(ClusteringMiningModel.INDEX_CLUSTERS).size)
    System.out.println("cluster model: " + clustModel)
  }
}