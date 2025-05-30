package com.starfish_studios.hamsters.block;

import com.starfish_studios.hamsters.entity.Hamster;
import com.starfish_studios.hamsters.entity.SeatEntity;
import com.starfish_studios.hamsters.registry.HamstersBlockEntities;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import net.minecraft.util.Hand;
import net.minecraft.util.ActionResult;
import net.minecraft.entity.Entity;
import net.minecraft.entity.mob.MobEntity;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.block.BlockWithEntity;
import net.minecraft.block.Block;
import net.minecraft.util.math.BlockPos;
import net.minecraft.block.BlockEntityProvider;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Box;
import net.minecraft.block.BlockRenderType;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.block.BlockState;
import net.minecraft.state.property.Properties;
import net.minecraft.state.property.DirectionProperty;
import net.minecraft.state.property.Property;
import net.minecraft.block.ShapeContext;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.world.WorldView;
import net.minecraft.state.StateManager.Builder;
import net.minecraft.block.AbstractBlock.Settings;
import org.jetbrains.annotations.Nullable;

public class HamsterWheelBlock extends BlockWithEntity implements BlockEntityProvider {
   public static final DirectionProperty FACING;
   protected static final VoxelShape NORTH;
   protected static final VoxelShape SOUTH;
   protected static final VoxelShape EAST;
   protected static final VoxelShape WEST;

   public HamsterWheelBlock(Settings properties) {
      super(properties);
      this.method_9590((BlockState)((BlockState)this.field_10647.method_11664()).method_11657(FACING, Direction.field_11043));
   }

   public boolean isMountable(BlockState state) {
      return true;
   }

   public BlockPos primaryDismountLocation(World level, BlockState state, BlockPos pos) {
      return pos;
   }

   public float setRiderRotation(BlockState state, Entity entity) {
      return entity.method_36454();
   }

   public static boolean isOccupied(World level, BlockPos pos) {
      level.method_8320(pos);
      return !level.method_18467(SeatEntity.class, new Box(pos)).isEmpty();
   }

   public float seatHeight(BlockState state) {
      return 0.0F;
   }

   public static Optional<Entity> getLeashed(PlayerEntity player) {
      List<Entity> entities = player.method_37908().method_8333((Entity)null, player.method_5829().method_1014(10.0D), (ex) -> {
         return true;
      });
      Iterator var2 = entities.iterator();

      while(var2.hasNext()) {
         Entity e = (Entity)var2.next();
         if (e instanceof MobEntity) {
            MobEntity mob = (MobEntity)e;
            if (mob.method_5933() == player && canBePickedUp(e)) {
               return Optional.of(mob);
            }
         }
      }

      return Optional.empty();
   }

   public static boolean ejectSeatedExceptPlayer(World level, SeatEntity seatEntity) {
      List<Entity> passengers = seatEntity.method_5685();
      if (passengers.isEmpty()) {
         return false;
      } else {
         if (!level.field_9236) {
            seatEntity.method_5772();
         }

         return true;
      }
   }

   public static boolean canBePickedUp(Entity passenger) {
      return passenger instanceof PlayerEntity ? false : passenger instanceof LivingEntity;
   }

   public static void sitDown(World level, BlockPos pos, Entity entity) {
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

   public int method_9572(BlockState state, World level, BlockPos pos) {
      return isOccupied(level, pos) ? 15 : 0;
   }

   public BlockRenderType method_9604(BlockState state) {
      return BlockRenderType.field_11456;
   }

   protected void method_9515(Builder<Block, BlockState> builder) {
      builder.method_11667(new Property[]{FACING});
   }

   @Nullable
   public BlockState method_9605(ItemPlacementContext context) {
      return (BlockState)this.method_9564().method_11657(FACING, context.method_8042().method_10153());
   }

   @Nullable
   public BlockEntity method_10123(BlockPos blockPos, BlockState blockState) {
      return HamstersBlockEntities.HAMSTER_WHEEL.method_11032(blockPos, blockState);
   }

   public ActionResult method_9534(BlockState blockState, World level, BlockPos blockPos, PlayerEntity player, Hand interactionHand, BlockHitResult blockHitResult) {
      if (player.method_5998(interactionHand).method_7960() || !player.method_5998(interactionHand).method_7960() && !player.method_5715()) {
         if (!level.method_8505(player, blockPos)) {
            return ActionResult.field_5811;
         } else if (this.isMountable(blockState) && !player.method_5765() && !player.method_18276()) {
            if (isOccupied(level, blockPos)) {
               List<SeatEntity> seats = level.method_18467(SeatEntity.class, new Box(blockPos));
               Entity var9 = ((SeatEntity)seats.get(0)).method_31483();
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

   public VoxelShape method_9530(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
      VoxelShape var10000;
      switch((Direction)state.method_11654(FACING)) {
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

   public boolean method_9558(BlockState state, WorldView world, BlockPos pos) {
      return world.method_8320(pos.method_10074()).method_26206(world, pos.method_10074(), Direction.field_11036);
   }

   static {
      FACING = Properties.field_12525;
      NORTH = Block.method_9541(1.0D, 0.0D, 3.0D, 15.0D, 16.0D, 16.0D);
      SOUTH = Block.method_9541(1.0D, 0.0D, 0.0D, 15.0D, 16.0D, 13.0D);
      EAST = Block.method_9541(0.0D, 0.0D, 1.0D, 13.0D, 16.0D, 15.0D);
      WEST = Block.method_9541(3.0D, 0.0D, 1.0D, 16.0D, 16.0D, 15.0D);
   }
}
