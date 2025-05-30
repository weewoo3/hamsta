package com.starfish_studios.hamsters.block;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Maps;
import java.util.Map;
import net.minecraft.class_1268;
import net.minecraft.util.ActionResult;
import net.minecraft.class_1297;
import net.minecraft.class_156;
import net.minecraft.class_1657;
import net.minecraft.class_1750;
import net.minecraft.class_1922;
import net.minecraft.class_1936;
import net.minecraft.class_1937;
import net.minecraft.class_2248;
import net.minecraft.util.math.BlockPos;
import net.minecraft.class_2350;
import net.minecraft.class_259;
import net.minecraft.class_265;
import net.minecraft.block.BlockState;
import net.minecraft.class_2741;
import net.minecraft.class_2746;
import net.minecraft.class_2769;
import net.minecraft.class_3726;
import net.minecraft.class_3965;
import net.minecraft.class_2689.class_2690;
import net.minecraft.class_4970.class_2251;

public class TunnelBlock extends class_2248 {
   public static final class_2746 WATERLOGGED;
   public static final class_2746 NORTH;
   public static final class_2746 EAST;
   public static final class_2746 SOUTH;
   public static final class_2746 WEST;
   public static final class_2746 UP;
   public static final class_2746 DOWN;
   public static final Map<class_2350, class_2746> PROPERTY_BY_DIRECTION;
   protected static final class_265 SOLID_LEFT;
   protected static final class_265 SOLID_RIGHT;
   protected static final class_265 SOLID_FRONT;
   protected static final class_265 SOLID_BACK;
   protected static final class_265 SOLID_TOP;
   protected static final class_265 SOLID_BOTTOM;
   protected static final class_265 SINGLE_Y;
   protected static final class_265 SINGLE_X;
   protected static final class_265 SINGLE_Z;

   public TunnelBlock(class_2251 properties) {
      super(properties);
      this.method_9590((BlockState)((BlockState)((BlockState)((BlockState)((BlockState)((BlockState)((BlockState)((BlockState)this.field_10647.method_11664()).method_11657(NORTH, false)).method_11657(EAST, false)).method_11657(SOUTH, false)).method_11657(WEST, false)).method_11657(UP, false)).method_11657(DOWN, false)).method_11657(WATERLOGGED, false));
   }

   public ActionResult method_9534(BlockState blockState, class_1937 level, BlockPos blockPos, class_1657 player, class_1268 interactionHand, class_3965 blockHitResult) {
      if (player.method_5998(interactionHand).method_7960() && !player.method_5715()) {
         player.method_5859((double)blockPos.method_10263() + 0.5D, (double)blockPos.method_10264() + 0.2D, (double)blockPos.method_10260() + 0.5D);
         return ActionResult.field_5812;
      } else {
         return ActionResult.field_5811;
      }
   }

   public void method_9548(BlockState blockState, class_1937 level, BlockPos blockPos, class_1297 entity) {
   }

   public class_265 method_9549(BlockState blockState, class_1922 blockGetter, BlockPos blockPos, class_3726 collisionContext) {
      class_265 shape = class_259.method_1073();
      if ((Boolean)blockState.method_11654(SOUTH) || (Boolean)blockState.method_11654(NORTH)) {
         shape = class_259.method_17786(shape, new class_265[]{SOLID_LEFT, SOLID_RIGHT});
      }

      if ((Boolean)blockState.method_11654(EAST) || (Boolean)blockState.method_11654(WEST)) {
         shape = class_259.method_17786(shape, new class_265[]{SOLID_FRONT, SOLID_BACK});
      }

      if (!(Boolean)blockState.method_11654(UP) || !(Boolean)blockState.method_11654(DOWN)) {
         shape = class_259.method_17786(shape, new class_265[]{SOLID_TOP, SOLID_BOTTOM});
      }

      return shape;
   }

   public class_265 method_9530(BlockState blockState, class_1922 blockGetter, BlockPos blockPos, class_3726 collisionContext) {
      return class_259.method_1077();
   }

   public class_265 method_26159(BlockState blockState, class_1922 blockGetter, BlockPos blockPos, class_3726 collisionContext) {
      return class_259.method_1073();
   }

   public BlockState method_9605(class_1750 blockPlaceContext) {
      return this.getStateForPlacement(blockPlaceContext.method_8045(), blockPlaceContext.method_8037());
   }

   public BlockState getStateForPlacement(class_1922 blockGetter, BlockPos blockPos) {
      BlockState blockState = blockGetter.method_8320(blockPos.method_10074());
      BlockState blockState2 = blockGetter.method_8320(blockPos.method_10084());
      BlockState blockState3 = blockGetter.method_8320(blockPos.method_10095());
      BlockState blockState4 = blockGetter.method_8320(blockPos.method_10078());
      BlockState blockState5 = blockGetter.method_8320(blockPos.method_10072());
      BlockState blockState6 = blockGetter.method_8320(blockPos.method_10067());
      return (BlockState)((BlockState)((BlockState)((BlockState)((BlockState)((BlockState)this.method_9564().method_11657(DOWN, blockState.method_27852(this))).method_11657(UP, blockState2.method_27852(this))).method_11657(NORTH, blockState3.method_27852(this))).method_11657(EAST, blockState4.method_27852(this))).method_11657(SOUTH, blockState5.method_27852(this))).method_11657(WEST, blockState6.method_27852(this));
   }

   public BlockState method_9559(BlockState blockState, class_2350 direction, BlockState blockState2, class_1936 levelAccessor, BlockPos blockPos, BlockPos blockPos2) {
      return (BlockState)blockState.method_11657((class_2769)PROPERTY_BY_DIRECTION.get(direction), blockState2.method_27852(this));
   }

   public boolean method_9522(BlockState blockState, BlockState blockState2, class_2350 direction) {
      return blockState2.method_27852(this) || super.method_9522(blockState, blockState2, direction);
   }

   protected void method_9515(class_2690<class_2248, BlockState> builder) {
      builder.method_11667(new class_2769[]{NORTH, EAST, SOUTH, WEST, UP, DOWN, WATERLOGGED});
   }

   static {
      WATERLOGGED = class_2741.field_12508;
      NORTH = class_2741.field_12489;
      EAST = class_2741.field_12487;
      SOUTH = class_2741.field_12540;
      WEST = class_2741.field_12527;
      UP = class_2741.field_12519;
      DOWN = class_2741.field_12546;
      PROPERTY_BY_DIRECTION = ImmutableMap.copyOf((Map)class_156.method_654(Maps.newEnumMap(class_2350.class), (enumMap) -> {
         enumMap.put(class_2350.field_11043, NORTH);
         enumMap.put(class_2350.field_11034, EAST);
         enumMap.put(class_2350.field_11035, SOUTH);
         enumMap.put(class_2350.field_11039, WEST);
         enumMap.put(class_2350.field_11036, UP);
         enumMap.put(class_2350.field_11033, DOWN);
      }));
      SOLID_LEFT = class_2248.method_9541(0.0D, 0.0D, 0.0D, 2.0D, 16.0D, 16.0D);
      SOLID_RIGHT = class_2248.method_9541(14.0D, 0.0D, 0.0D, 16.0D, 16.0D, 16.0D);
      SOLID_FRONT = class_2248.method_9541(0.0D, 0.0D, 0.0D, 16.0D, 16.0D, 2.0D);
      SOLID_BACK = class_2248.method_9541(0.0D, 0.0D, 14.0D, 16.0D, 16.0D, 16.0D);
      SOLID_TOP = class_2248.method_9541(0.0D, 14.0D, 0.0D, 16.0D, 16.0D, 16.0D);
      SOLID_BOTTOM = class_2248.method_9541(0.0D, 0.0D, 0.0D, 16.0D, 2.0D, 16.0D);
      SINGLE_Y = class_259.method_17786(SOLID_LEFT, new class_265[]{SOLID_RIGHT, SOLID_FRONT, SOLID_BACK});
      SINGLE_X = class_259.method_17786(SOLID_FRONT, new class_265[]{SOLID_BACK, SOLID_TOP, SOLID_BOTTOM});
      SINGLE_Z = class_259.method_17786(SOLID_LEFT, new class_265[]{SOLID_RIGHT, SOLID_TOP, SOLID_BOTTOM});
   }
}
