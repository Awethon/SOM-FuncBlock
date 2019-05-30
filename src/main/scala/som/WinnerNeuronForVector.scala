package som

import org.eltech.ddm.miningcore.miningmodel.MiningModelElement
import java.util

class WinnerNeuronForVector(val id: String) extends MiningModelElement(id) {
  set = new util.ArrayList[MiningModelElement]
  private var iNeuron = 0
  private var distanceToNeuron = Double.MaxValue

  override def createNewCopyElement = new WinnerNeuronForVector(getID)

  override protected def propertiesToString: String = ",cluster=" + iNeuron + ",distToCenter=" + distanceToNeuron

  override def merge(elements: util.List[MiningModelElement]): Unit = {
    var va = elements.get(0).asInstanceOf[WinnerNeuronForVector]
    iNeuron = va.iNeuron
    distanceToNeuron = va.distanceToNeuron
    var i = 1
    while ( {
      i < elements.size
    }) {
      va = elements.get(i).asInstanceOf[WinnerNeuronForVector]
      if (va.distanceToNeuron < distanceToNeuron) {
        iNeuron = va.iNeuron
        distanceToNeuron = va.distanceToNeuron
      }
      {
        i += 1; i - 1
      }
    }
  }

  def getIndexOfNeuron: Int = iNeuron

  def setIndexOfNeuron(iNeuron: Int): Unit = this.iNeuron = iNeuron

  def getDistanceToNeuron: Double = distanceToNeuron

  def setDistanceToNeuron(distanceToNeuron: Double): Unit = this.distanceToNeuron = distanceToNeuron
}