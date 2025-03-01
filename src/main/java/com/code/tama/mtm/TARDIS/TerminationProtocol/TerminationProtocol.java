package com.code.tama.mtm.TARDIS.TerminationProtocol;

import com.code.tama.mtm.Capabilities.Interfaces.ITARDISLevel;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;

public abstract class TerminationProtocol {
    public BlockPos landPos;
    /** Called when the TARDIS flight sequence is terminated (Right before the exterior tile is placed)
     * @param blockPos the destination position
     * @param itardisLevel the capability of the TARDIS interior level
     * @param level The level that the Exterior will be placed in
     **/
    abstract public void OnLand(ITARDISLevel itardisLevel, BlockPos blockPos, Level level);

    public void SetLandPos(BlockPos pos) {
        this.landPos = pos;
    }

    public BlockPos GetLandPos() {
        return this.landPos;
    }
}
