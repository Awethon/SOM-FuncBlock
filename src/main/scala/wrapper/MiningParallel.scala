package wrapper

import org.eltech.ddm.miningcore.algorithms.{MemoryType, MiningBlock}
import org.eltech.ddm.miningcore.miningfunctionsettings.EMiningFunctionSettings

object MiningParallel {
  type MiningParallel = org.eltech.ddm.miningcore.algorithms.MiningParallel

  def apply(blocks: MiningBlock*)(implicit settings: EMiningFunctionSettings): MiningParallel = new org.eltech.ddm.miningcore.algorithms.MiningParallel(settings, MemoryType.distributed, blocks: _*)
  def apply(block: MiningBlock)(implicit settings: EMiningFunctionSettings): MiningParallel = new org.eltech.ddm.miningcore.algorithms.MiningParallel(settings, MemoryType.distributed, block)
}
