package com.starfish_studios.hamsters.block;

import com.starfish_studios.hamsters.entity.Hamster;
import com.starfish_studios.hamsters.entity.SeatEntity;
import com.starfish_studios.hamsters.registry.HamstersBlockEntities;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import net.minecraft.class_1268;
import net.minecraft.util.ActionResult;
import net.minecraft.class_1297;
import net.minecraft.class_1308;
import net.minecraft.class_1309;
import net.minecraft.class_1657;
import net.minecraft.class_1750;
import net.minecraft.class_1922;
import net.minecraft.class_1937;
import net.minecraft.class_2237;
import net.minecraft.class_2248;
import net.minecraft.util.math.BlockPos;
import net.minecraft.class_2343;
import net.minecraft.class_2350;
import net.minecraft.class_238;
import net.minecraft.class_2464;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.class_265;
import net.minecraft.block.BlockState;
import net.minecraft.class_2741;
import net.minecraft.class_2753;
import net.minecraft.class_2769;
import net.minecraft.class_3726;
import net.minecraft.class_3965;
import net.minecraft.class_4538;
import net.minecraft.class_2689.class_2690;
import net.minecraft.class_4970.class_2251;
import org.jetbrains.annotations.Nullable;

public class HamsterWheelBlock extends class_2237 implements class_2343 {
   public static final class_2753 FACING;
   protected static final class_265 NORTH;
   protected static final class_265 SOUTH;
   protected static final class_265 EAST;
   protected static final class_265 WEST;

   public HamsterWheelBlock(class_2251 properties) {
      super(properties);
      this.method_9590((BlockState)((BlockState)this.field_10647.method_11664()).method_11657(FACING, class_2350.field_11043));
   }

   public boolean isMountable(BlockState state) {
      return true;
   }

   public BlockPos primaryDismountLocation(class_1937 level, BlockState state, BlockPos pos) {
      return pos;
   }

   public float setRiderRotation(BlockState state, class_1297 entity) {
      return entity.method_36454();
   }

   public static boolean isOccupied(class_1937 level, BlockPos pos) {
      level.method_8320(pos);
      return !level.method_18467(SeatEntity.class, new class_238(pos)).isEmpty();
   }

   public float seatHeight(BlockState state) {
      return 0.0F;
   }

   public static Optional<class_1297> getLeashed(class_1657 player) {
      List<class_1297> entities = player.method_37908().method_8333((class_1297)null, player.method_5829().method_1014(10.0D), (ex) -> {
         return true;
      });
      Iterator var2 = entities.iterator();

      while(var2.hasNext()) {
         class_1297 e = (class_1297)var2.next();
         if (e instanceof class_1308) {
            class_1308 mob = (class_1308)e;
            if (mob.method_5933() == player && canBePickedUp(e)) {
               return Optional.of(mob);
            }
         }
      }

      return Optional.empty();
   }

   public static boolean ejectSeatedExceptPlayer(class_1937 level, SeatEntity seatEntity) {
      List<class_1297> passengers = seatEntity.method_5685();
      if (passengers.isEmpty()) {
         return false;
      } else {
         if (!level.field_9236) {
            seatEntity.method_5772();
         }

         return true;
      }
   }

   public static boolean canBePickedUp(class_1297 passenger) {
      return passenger instanceof class_1657 ? false : passenger instanceof class_1309;
   }

   public static void sitDown(class_1937 level, BlockPos pos, class_1297 entity) {
      if (!level.field_9236) {
         if (entity != null) {
            SeatEntity seat = new SeatEntity(level, pos);
            level.method_8649(seat);
            entity.method_5804(seat);
            level.method_8455(pos, level.method_8320(pos).method_26204());
         }
      }
   }

   public boolean method_9498(BlockState state) {
      return true;
   }

   public int method_9572(BlockState state, class_1937 level, BlockPos pos) {
      return isOccupied(level, pos) ? 15 : 0;
   }

   public class_2464 method_9604(BlockState state) {
      return class_2464.field_11456;
   }

   protected void method_9515(class_2690<class_2248, BlockState> builder) {
      builder.method_11667(new class_2769[]{FACING});
   }

   @Nullable
   public BlockState method_9605(class_1750 context) {
      return (BlockState)this.method_9564().method_11657(FACING, context.method_8042().method_10153());
   }

   @Nullable
   public BlockEntity method_10123(BlockPos blockPos, BlockState blockState) {
      return HamstersBlockEntities.HAMSTER_WHEEL.method_11032(blockPos, blockState);
   }

   public ActionResult method_9534(BlockState blockState, class_1937 level, BlockPos blockPos, class_1657 player, class_1268 interactionHand, class_3965 blockHitResult) {
      if (player.method_5998(interactionHand).method_7960() || !player.method_5998(interactionHand).method_7960() && !player.method_5715()) {
         if (!level.method_8505(player, blockPos)) {
            return ActionResult.field_5811;
         } else if (this.isMountable(blockState) && !player.method_5765() && !player.method_18276()) {
            if (isOccupied(level, blockPos)) {
               List<SeatEntity> seats = level.method_18467(SeatEntity.class, new class_238(blockPos));
               class_1297 var9 = ((SeatEntity)seats.get(0)).method_31483();
               if (var9 instanceof Hamster) {
                  Hamster hamster = (Hamster)var9;
                  hamster.setWaitTimeWhenRunningTicks(0);
                  hamster.setWaitTimeBeforeRunTicks(hamster.method_6051().method_43048(200) + 600);
               }

               return ejectSeatedExceptPlayer(level, (SeatEntity)seats.get(0)) ? ActionResult.field_5812 : ActionResult.field_5811;
            } else {
               if (getLeashed(player).isPresent()) {
                  Object var8 = getLeashed(player).get();
                  if (var8 instanceof Hamster) {
                     Hamster hamster = (Hamster)var8;
                     hamster.setWaitTimeBeforeRunTicks(0);
                     sitDown(level, blockPos, hamster);
                  }
               }

               return ActionResult.field_5812;
            }
         } else {
            return ActionResult.field_5811;
         }
      } else {
         return super.method_9534(blockState, level, blockPos, player, interactionHand, blockHitResult);
      }
   }

   public class_265 method_9530(BlockState state, class_1922 world, BlockPos pos, class_3726 context) {
      class_265 var10000;
      switch((class_2350)state.method_11654(FACING)) {
      case field_11035:
         var10000 = SOUTH;
         break;
      case field_11034:
         var10000 = EAST;
         break;
      case field_11039:
         var10000 = WEST;
         break;
      default:
         var10000 = NORTH;
      }

      return var10000;
   }

   public boolean method_9558(BlockState state, class_4538 world, BlockPos pos) {
      return world.method_8320(pos.method_10074()).method_26206(world, pos.method_10074(), class_2350.field_11036);
   }

   static {
      FACING = class_2741.field_12525;
      NORTH = class_2248.method_9541(1.0D, 0.0D, 3.0D, 15.0D, 16.0D, 16.0D);
      SOUTH = class_2248.method_9541(1.0D, 0.0D, 0.0D, 15.0D, 16.0D, 13.0D);
      EAST = class_2248.method_9541(0.0D, 0.0D, 1.0D, 13.0D, 16.0D, 15.0D);
      WEST = class_2248.method_9541(3.0D, 0.0D, 1.0D, 16.0D, 16.0D, 15.0D);
   }
}
