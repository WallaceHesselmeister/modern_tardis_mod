package com.code.tama.mtm.misc.Interfaces;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;

public abstract class TickingTile extends BlockEntity {
    public TickingTile(BlockEntityType<?> p_155228_, BlockPos p_155229_, BlockState p_155230_) {
        super(p_155228_, p_155229_, p_155230_);
    }

    public static <T extends BlockEntity> void tick(Level level, BlockPos blockPos, BlockState state, T tile) {
        ((TickingTile) tile).tick();
    }

    public abstract void tick();
}
