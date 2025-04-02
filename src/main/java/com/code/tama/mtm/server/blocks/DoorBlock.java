package com.code.tama.mtm.server.blocks;

import com.code.tama.mtm.MTMMod;
import com.code.tama.mtm.data.DoorData;
import com.code.tama.mtm.server.capabilities.CapabilityConstants;
import com.code.tama.mtm.server.misc.SpaceTimeCoordinate;
import com.code.tama.mtm.server.tileentities.DoorTile;
import com.code.tama.mtm.server.tileentities.ExteriorTile;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;
import java.util.Set;
import java.util.function.Supplier;

@SuppressWarnings("deprecation")
public class DoorBlock extends Block implements EntityBlock {
    public static final VoxelRotatedShape SHAPE = new VoxelRotatedShape(CreateShape());
    public static final DirectionProperty FACING = BlockStateProperties.HORIZONTAL_FACING;
    private final Supplier<? extends BlockEntityType<? extends DoorTile>> doorBlock;
    public boolean IsMarkedForRemoval = false;

    public DoorBlock(Properties p_49795_, Supplier<? extends BlockEntityType<? extends DoorTile>> factory) {
        super(p_49795_);
        this.doorBlock = factory;
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(@NotNull BlockPos pos, @NotNull BlockState state) {
        return this.doorBlock.get().create(pos, state);
    }
    @Override
    public @NotNull VoxelShape getShape(BlockState blockState, @NotNull BlockGetter blockGetter, @NotNull BlockPos blockPos, @NotNull CollisionContext collisionContext) {
        return SHAPE.GetShapeFromRotation(blockState.getValue(FACING));
    }

    public static VoxelShape CreateShape() {
        return Block.box(0, 0, 15, 16, 32, 16);
    }


    @Override
    protected void createBlockStateDefinition(StateDefinition.@NotNull Builder<Block, BlockState> StateDefinition) {
        super.createBlockStateDefinition(StateDefinition);
        StateDefinition.add(FACING);
    }

    @Override
    public void onPlace(@NotNull BlockState state, Level level, @NotNull BlockPos blockPos, @NotNull BlockState blockState, boolean p_60570_) {
        level.getCapability(CapabilityConstants.TARDIS_LEVEL_CAPABILITY).ifPresent(cap -> {
            cap.SetDoorBlock(new SpaceTimeCoordinate(this.GetPosForTeleport(state, blockPos)));
            Direction direction = state.getValue(FACING);
            float yRot = switch (direction) {
                case EAST -> 90;
                case SOUTH -> 180;
                case WEST -> 270;
                default -> 0;
            };
            cap.SetDoorData(new DoorData(yRot, new SpaceTimeCoordinate(blockPos)));
        });

        super.onPlace(state, level, blockPos, blockState, p_60570_);
    }

    @Override
    public void entityInside(@NotNull BlockState state, @NotNull Level level, net.minecraft.core.@NotNull BlockPos pos, net.minecraft.world.entity.@NotNull Entity entity) {
        this.TeleportToExterior(entity, level);
    }

    public void TeleportToExterior(Entity EntityToTeleport, Level Interior) {
        if (EntityToTeleport.level().isClientSide) return;
        try {
            Interior.getCapability(CapabilityConstants.TARDIS_LEVEL_CAPABILITY).ifPresent((cap) -> {
                BlockPos pos = cap.GetExteriorLocation().GetBlockPos().north(1);
                if(Interior.getServer().getLevel(cap.GetCurrentLevel()).getBlockEntity(cap.GetExteriorLocation().GetBlockPos()) instanceof ExteriorTile exteriorTile) {
                    exteriorTile.SetInteriorAndSyncWithBlock(Interior.dimension());
                }
                EntityToTeleport.teleportTo(Interior.getServer().getLevel(cap.GetCurrentLevel()),
                        pos.getX(), pos.getY(), pos.getZ(), Set.of(), 0, 0);
            });
        } catch (Exception e) {
            MTMMod.LOGGER.error("EXTERIOR NOT FOUND");
        }
    }

    public BlockPos GetPosForTeleport(BlockState state, BlockPos pos) {
        Direction dir = state.getValue(FACING);

        switch (dir) {
            case NORTH -> pos = pos.north();
            case EAST -> pos = pos.east();
            case WEST -> pos = pos.west();
            default -> pos = pos.south();
        }
        return pos;
    }

    public float GetRotationForTeleport(BlockState state) {
        Direction dir = state.getValue(FACING);
        float f;
        switch (dir) {
            case NORTH -> f = 180f;
            case EAST -> f = 270f;
            case WEST -> f = 90f;
            default -> f = 0f;
        }
        return f;
    }
}
