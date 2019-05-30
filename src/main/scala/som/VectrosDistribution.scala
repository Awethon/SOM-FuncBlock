package som

import org.eltech.ddm.miningcore.MiningException
import org.eltech.ddm.miningcore.miningmodel.MiningModelElement
import java.util

import scala.collection.JavaConversions._

class VectrosDistribution(val id: String) extends MiningModelElement(id) {
  set = new util.ArrayList[MiningModelElement]
  private var mapVectors = new util.HashMap[String, Integer]

  override def merge(elements: util.List[MiningModelElement]): Unit = {
    for (elem <- elements) {
      mapVectors.putAll(elem.asInstanceOf[VectrosDistribution].mapVectors)
    }
  }

  override def getElement(index: Int): MiningModelElement = {
    val indx = mapVectors.get("v" + index)
    if (indx != null) super.getElement(indx)
    else null
  }

  override protected def add(element: MiningModelElement): Unit = {
    super.add(element)
    mapVectors.put(element.getID, set.size - 1)
  }

  override protected def replace(pos: Int, element: MiningModelElement): Unit = {
    val indx = mapVectors.get("v" + pos)
    if (indx != null) super.replace(indx, element)
  }

  override protected def remove(index: Int): Unit = {
    val indx = mapVectors.get("v" + index)
    if (indx != null) {
      super.remove(indx)
      mapVectors.remove("v" + index)
    }
  }

  /**
    * Clone mining model's element for parallel processing
    * O(n) = n where n - number of elements of this set
    *
    * @return
    */
  override def clone: Object = {
    val o = super.clone.asInstanceOf[VectrosDistribution]
    if (mapVectors != null) {
      o.mapVectors = new util.HashMap[String, Integer]
      o.mapVectors.putAll(mapVectors)
    }
    o
  }

  override protected def propertiesToString = ""
}