package wrapper

import org.eltech.ddm.miningcore.algorithms.MiningBlock
import org.eltech.ddm.miningcore.miningfunctionsettings.EMiningFunctionSettings
import org.eltech.ddm.miningcore.miningmodel.EMiningModel

class MiningLoopVectors(blocks: MiningBlock*)
                       (implicit settings: EMiningFunctionSettings) extends org.eltech.ddm.miningcore.algorithms.MiningLoopVectors(settings, blocks: _*) {
}

object MiningLoopVectors {
  def apply(blocks: MiningBlock*)(implicit settings: EMiningFunctionSettings): MiningLoopVectors = new MiningLoopVectors(blocks: _*)
}
