package com.starfish_studios.hamsters.block;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Maps;
import java.util.Map;
import net.minecraft.util.Hand;
import net.minecraft.util.ActionResult;
import net.minecraft.entity.Entity;
import net.minecraft.util.Util;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.world.BlockView;
import net.minecraft.world.WorldAccess;
import net.minecraft.world.World;
import net.minecraft.block.Block;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.block.BlockState;
import net.minecraft.state.property.Properties;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.Property;
import net.minecraft.block.ShapeContext;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.state.StateManager.Builder;
import net.minecraft.block.AbstractBlock.Settings;

public class TunnelBlock extends Block {
   public static final BooleanProperty WATERLOGGED;
   public static final BooleanProperty NORTH;
   public static final BooleanProperty EAST;
   public static final BooleanProperty SOUTH;
   public static final BooleanProperty WEST;
   public static final BooleanProperty UP;
   public static final BooleanProperty DOWN;
   public static final Map<Direction, BooleanProperty> PROPERTY_BY_DIRECTION;
   protected static final VoxelShape SOLID_LEFT;
   protected static final VoxelShape SOLID_RIGHT;
   protected static final VoxelShape SOLID_FRONT;
   protected static final VoxelShape SOLID_BACK;
   protected static final VoxelShape SOLID_TOP;
   protected static final VoxelShape SOLID_BOTTOM;
   protected static final VoxelShape SINGLE_Y;
   protected static final VoxelShape SINGLE_X;
   protected static final VoxelShape SINGLE_Z;

   public TunnelBlock(Settings properties) {
      super(properties);
      this.method_9590((BlockState)((BlockState)((BlockState)((BlockState)((BlockState)((BlockState)((BlockState)((BlockState)this.field_10647.method_11664()).method_11657(NORTH, false)).method_11657(EAST, false)).method_11657(SOUTH, false)).method_11657(WEST, false)).method_11657(UP, false)).method_11657(DOWN, false)).method_11657(WATERLOGGED, false));
   }

   public ActionResult method_9534(BlockState blockState, World level, BlockPos blockPos, PlayerEntity player, Hand interactionHand, BlockHitResult blockHitResult) {
      if (player.method_5998(interactionHand).method_7960() && !player.method_5715()) {
         player.method_5859((double)blockPos.method_10263() + 0.5D, (double)blockPos.method_10264() + 0.2D, (double)blockPos.method_10260() + 0.5D);
         return ActionResult.field_5812;
      } else {
         return ActionResult.field_5811;
      }
   }

   public void method_9548(BlockState blockState, World level, BlockPos blockPos, Entity entity) {
   }

   public VoxelShape method_9549(BlockState blockState, BlockView blockGetter, BlockPos blockPos, ShapeContext collisionContext) {
      VoxelShape shape = VoxelShapes.method_1073();
      if ((Boolean)blockState.method_11654(SOUTH) || (Boolean)blockState.method_11654(NORTH)) {
         shape = VoxelShapes.method_17786(shape, new VoxelShape[]{SOLID_LEFT, SOLID_RIGHT});
      }

      if ((Boolean)blockState.method_11654(EAST) || (Boolean)blockState.method_11654(WEST)) {
         shape = VoxelShapes.method_17786(shape, new VoxelShape[]{SOLID_FRONT, SOLID_BACK});
      }

      if (!(Boolean)blockState.method_11654(UP) || !(Boolean)blockState.method_11654(DOWN)) {
         shape = VoxelShapes.method_17786(shape, new VoxelShape[]{SOLID_TOP, SOLID_BOTTOM});
      }

      return shape;
   }

   public VoxelShape method_9530(BlockState blockState, BlockView blockGetter, BlockPos blockPos, ShapeContext collisionContext) {
      return VoxelShapes.method_1077();
   }

   public VoxelShape method_26159(BlockState blockState, BlockView blockGetter, BlockPos blockPos, ShapeContext collisionContext) {
      return VoxelShapes.method_1073();
   }

   public BlockState method_9605(ItemPlacementContext blockPlaceContext) {
      return this.getStateForPlacement(blockPlaceContext.method_8045(), blockPlaceContext.method_8037());
   }

   public BlockState getStateForPlacement(BlockView blockGetter, BlockPos blockPos) {
      BlockState blockState = blockGetter.method_8320(blockPos.method_10074());
      BlockState blockState2 = blockGetter.method_8320(blockPos.method_10084());
      BlockState blockState3 = blockGetter.method_8320(blockPos.method_10095());
      BlockState blockState4 = blockGetter.method_8320(blockPos.method_10078());
      BlockState blockState5 = blockGetter.method_8320(blockPos.method_10072());
      BlockState blockState6 = blockGetter.method_8320(blockPos.method_10067());
      return (BlockState)((BlockState)((BlockState)((BlockState)((BlockState)((BlockState)this.method_9564().method_11657(DOWN, blockState.method_27852(this))).method_11657(UP, blockState2.method_27852(this))).method_11657(NORTH, blockState3.method_27852(this))).method_11657(EAST, blockState4.method_27852(this))).method_11657(SOUTH, blockState5.method_27852(this))).method_11657(WEST, blockState6.method_27852(this));
   }

   public BlockState method_9559(BlockState blockState, Direction direction, BlockState blockState2, WorldAccess levelAccessor, BlockPos blockPos, BlockPos blockPos2) {
      return (BlockState)blockState.method_11657((Property)PROPERTY_BY_DIRECTION.get(direction), blockState2.method_27852(this));
   }

   public boolean method_9522(BlockState blockState, BlockState blockState2, Direction direction) {
      return blockState2.method_27852(this) || super.method_9522(blockState, blockState2, direction);
   }

   protected void method_9515(Builder<Block, BlockState> builder) {
      builder.method_11667(new Property[]{NORTH, EAST, SOUTH, WEST, UP, DOWN, WATERLOGGED});
   }

   static {
      WATERLOGGED = Properties.field_12508;
      NORTH = Properties.field_12489;
      EAST = Properties.field_12487;
      SOUTH = Properties.field_12540;
      WEST = Properties.field_12527;
      UP = Properties.field_12519;
      DOWN = Properties.field_12546;
      PROPERTY_BY_DIRECTION = ImmutableMap.copyOf((Map)Util.method_654(Maps.newEnumMap(Direction.class), (enumMap) -> {
         enumMap.put(Direction.field_11043, NORTH);
         enumMap.put(Direction.field_11034, EAST);
         enumMap.put(Direction.field_11035, SOUTH);
         enumMap.put(Direction.field_11039, WEST);
         enumMap.put(Direction.field_11036, UP);
         enumMap.put(Direction.field_11033, DOWN);
      }));
      SOLID_LEFT = Block.method_9541(0.0D, 0.0D, 0.0D, 2.0D, 16.0D, 16.0D);
      SOLID_RIGHT = Block.method_9541(14.0D, 0.0D, 0.0D, 16.0D, 16.0D, 16.0D);
      SOLID_FRONT = Block.method_9541(0.0D, 0.0D, 0.0D, 16.0D, 16.0D, 2.0D);
      SOLID_BACK = Block.method_9541(0.0D, 0.0D, 14.0D, 16.0D, 16.0D, 16.0D);
      SOLID_TOP = Block.method_9541(0.0D, 14.0D, 0.0D, 16.0D, 16.0D, 16.0D);
      SOLID_BOTTOM = Block.method_9541(0.0D, 0.0D, 0.0D, 16.0D, 2.0D, 16.0D);
      SINGLE_Y = VoxelShapes.method_17786(SOLID_LEFT, new VoxelShape[]{SOLID_RIGHT, SOLID_FRONT, SOLID_BACK});
      SINGLE_X = VoxelShapes.method_17786(SOLID_FRONT, new VoxelShape[]{SOLID_BACK, SOLID_TOP, SOLID_BOTTOM});
      SINGLE_Z = VoxelShapes.method_17786(SOLID_LEFT, new VoxelShape[]{SOLID_RIGHT, SOLID_TOP, SOLID_BOTTOM});
   }
}
