package som

import org.eltech.ddm.miningcore.miningmodel.MiningModelElement
import java.util
import scala.collection.JavaConversions._

import org.eltech.ddm.clustering.cdbase.kmeans.VectorCluster

class VectorToNeuronDistance(val id: String) extends MiningModelElement(id) {
  private var distance = .0

  override def createNewCopyElement = new VectorCluster(getID)

  override def merge(elements: util.List[MiningModelElement]): Unit = {
    var delta = 0.0


    for (elem <- elements) {
      delta += (elem.asInstanceOf[VectorToNeuronDistance].distance - distance)
    }
    distance += delta
  }

  def getDistance: Double = distance

  def addDistance(distance: Double): Unit = this.distance += distance

  def setDistance(distance: Double): Unit = this.distance = distance

  override protected def propertiesToString: String = ",d=" + distance
}