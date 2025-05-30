package com.starfish_studios.hamsters.entity;

import com.starfish_studios.hamsters.block.HamsterWheelBlock;
import com.starfish_studios.hamsters.registry.HamstersEntityType;
import java.util.List;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.passive.TameableEntity;
import net.minecraft.world.World;
import net.minecraft.block.Block;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Vec3d;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.util.shape.VoxelShapes6;
import net.minecraft.class_2602;
import net.minecraft.class_2604;
import net.minecraft.block.BlockState;
import net.minecraft.class_5275;

public class SeatEntity extends Entity {
   public SeatEntity(World level) {
      super((EntityType)HamstersEntityType.SEAT.get(), level);
      this.field_5960 = true;
   }

   public SeatEntity(World level, BlockPos pos) {
      this(level);
      this.method_5814((double)pos.method_10263() + 0.5D, (double)pos.method_10264() + 0.01D, (double)pos.method_10260() + 0.5D);
   }

   public void method_5773() {
      if (!this.method_37908().field_9236) {
         BlockState state = this.method_37908().method_8320(this.method_24515());
         Block var4 = state.method_26204();
         boolean canMount;
         if (var4 instanceof HamsterWheelBlock) {
            HamsterWheelBlock hamsterWheelBlock = (HamsterWheelBlock)var4;
            canMount = hamsterWheelBlock.isMountable(state);
         } else {
            canMount = false;
         }

         if (!this.method_5782() || !canMount) {
            this.method_31472();
            this.method_37908().method_8455(this.method_24515(), this.method_37908().method_8320(this.method_24515()).method_26204());
         }
      }
   }

   protected void method_5693() {
   }

   protected void method_5749(NbtCompound compound) {
   }

   protected void method_5652(NbtCompound compound) {
   }

   public double method_5621() {
      List<Entity> passengers = this.method_5685();
      if (passengers.isEmpty()) {
         return 0.0D;
      } else {
         double seatHeight = 0.0D;
         BlockState state = this.method_37908().method_8320(this.method_24515());
         Block var6 = state.method_26204();
         if (var6 instanceof HamsterWheelBlock) {
            HamsterWheelBlock hamsterWheelBlock = (HamsterWheelBlock)var6;
            seatHeight = (double)hamsterWheelBlock.seatHeight(state);
         }

         return seatHeight + getEntitySeatOffset((Entity)passengers.get(0));
      }
   }

   public static double getEntitySeatOffset(Entity entity) {
      return 0.0D;
   }

   protected boolean method_5860(Entity entity) {
      return true;
   }

   public VoxelShapes6<class_2602> method_18002() {
      return new class_2604(this);
   }

   public Vec3d method_24829(LivingEntity entity) {
      BlockPos pos = this.method_24515();
      BlockState state = this.method_37908().method_8320(pos);
      Block var6 = state.method_26204();
      Vec3d safeVec;
      if (var6 instanceof HamsterWheelBlock) {
         HamsterWheelBlock hamsterWheelBlock = (HamsterWheelBlock)var6;
         safeVec = class_5275.method_30769(entity.method_5864(), this.method_37908(), hamsterWheelBlock.primaryDismountLocation(this.method_37908(), state, pos), false);
         if (safeVec != null) {
            return safeVec.method_1031(0.0D, 0.25D, 0.0D);
         }
      }

      Direction original = this.method_5735();
      Direction[] offsets = new Direction[]{original, original.method_10170(), original.method_10160(), original.method_10153()};
      Direction[] var7 = offsets;
      int var8 = offsets.length;

      for(int var9 = 0; var9 < var8; ++var9) {
         Direction dir = var7[var9];
         safeVec = class_5275.method_30769(entity.method_5864(), this.method_37908(), pos.method_10093(dir), false);
         if (safeVec != null) {
            return safeVec.method_1031(0.0D, 0.25D, 0.0D);
         }
      }

      return super.method_24829(entity);
   }

   protected void method_5627(Entity passenger) {
      BlockPos pos = this.method_24515();
      BlockState state = this.method_37908().method_8320(pos);
      Block var5 = state.method_26204();
      if (var5 instanceof HamsterWheelBlock) {
         HamsterWheelBlock hamsterWheelBlock = (HamsterWheelBlock)var5;
         passenger.method_36456(hamsterWheelBlock.setRiderRotation(state, passenger));
      }

      super.method_5627(passenger);
   }

   protected void method_5793(Entity entity) {
      super.method_5793(entity);
      if (entity instanceof TameableEntity) {
         TameableEntity ta = (TameableEntity)entity;
         ta.method_6179(false);
      }

   }
}
