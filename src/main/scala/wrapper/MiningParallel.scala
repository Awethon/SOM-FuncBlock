package wrapper

import org.eltech.ddm.miningcore.algorithms.{MemoryType, MiningBlock}
import org.eltech.ddm.miningcore.miningfunctionsettings.EMiningFunctionSettings

class MiningParallel(memoryType: MemoryType, blocks: MiningBlock*)(settings: EMiningFunctionSettings) extends org.eltech.ddm.miningcore.algorithms.MiningParallel(settings, memoryType, blocks: _*) {

}

object MiningParallel {
  def apply(blocks: MiningBlock*)(implicit settings: EMiningFunctionSettings): MiningParallel = new MiningParallel(MemoryType.distributed, blocks: _*)(settings)
}
