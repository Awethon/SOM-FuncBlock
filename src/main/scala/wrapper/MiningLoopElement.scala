package wrapper

import org.eltech.ddm.miningcore.algorithms.MiningBlock
import org.eltech.ddm.miningcore.miningfunctionsettings.EMiningFunctionSettings

class MiningLoopElement(indexSet: Array[Int])(blocks: MiningBlock*)
                       (implicit settings: EMiningFunctionSettings) extends org.eltech.ddm.miningcore.algorithms.MiningLoopElement(settings, indexSet, blocks: _*) {}

object MiningLoopElement {
  def apply(indexSet: Array[Int])(blocks: MiningBlock*)
           (implicit settings: EMiningFunctionSettings): MiningLoopElement = new MiningLoopElement(indexSet)(blocks: _*)(settings)
}
