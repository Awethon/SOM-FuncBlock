package wrapper

import org.eltech.ddm.miningcore.algorithms.MiningBlock
import org.eltech.ddm.miningcore.miningfunctionsettings.EMiningFunctionSettings

class MiningSequence(blocks: MiningBlock*)(settings: EMiningFunctionSettings) extends org.eltech.ddm.miningcore.algorithms.MiningSequence(settings, blocks: _*) {

}

object MiningSequence {
  def apply(blocks: MiningBlock*)(implicit settings: EMiningFunctionSettings): MiningSequence = new MiningSequence(blocks: _*)(settings)
}
