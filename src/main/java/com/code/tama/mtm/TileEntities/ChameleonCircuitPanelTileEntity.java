package com.code.tama.mtm.TileEntities;

import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

public class ChameleonCircuitPanelTileEntity extends BlockEntity {

    public ChameleonCircuitPanelTileEntity(BlockPos pos, BlockState state) {
        super(MTileEntities.CHAMELEON_CIRCUIT_PANEL.get(), pos, state);
    }

    @Override
    public void load(CompoundTag tag) {
        super.load(tag);
    }

    @Override
    protected void saveAdditional(CompoundTag tag) {
        super.saveAdditional(tag);
    }
}

