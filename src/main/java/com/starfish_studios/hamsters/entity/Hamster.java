package com.starfish_studios.hamsters.entity;

import com.starfish_studios.hamsters.block.HamsterWheelBlock;
import com.starfish_studios.hamsters.entity.common.SearchForItemsGoal;
import com.starfish_studios.hamsters.registry.HamstersBlocks;
import com.starfish_studios.hamsters.registry.HamstersEntityType;
import com.starfish_studios.hamsters.registry.HamstersItems;
import com.starfish_studios.hamsters.registry.HamstersSoundEvents;
import com.starfish_studios.hamsters.registry.HamstersTags;
import java.util.Arrays;
import java.util.Comparator;
import java.util.EnumSet;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;
import net.minecraft.world.LocalDifficulty;
import net.minecraft.util.Hand;
import net.minecraft.util.ActionResult;
import net.minecraft.util.crash.CrashReport;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.class_129;
import net.minecraft.entity.passive.PassiveEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.EntityData;
import net.minecraft.entity.passive.TameableEntity;
import net.minecraft.entity.ai.control.LookControl;
import net.minecraft.entity.ai.goal.AnimalMateGoal;
import net.minecraft.entity.ai.goal.SwimGoal;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.entity.ai.goal.FollowParentGoal;
import net.minecraft.entity.ai.goal.LookAtEntityGoal;
import net.minecraft.entity.ai.goal.EscapeDangerGoal;
import net.minecraft.entity.ai.goal.LookAroundGoal;
import net.minecraft.entity.ai.goal.SitGoal;
import net.minecraft.entity.ai.goal.TemptGoal;
import net.minecraft.entity.ai.goal.WanderAroundFarGoal;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.util.crash.CrashException;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.recipe.Ingredient;
import net.minecraft.world.World;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Box2;
import net.minecraft.util.math.Vec3d;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.block.BlockState;
import net.minecraft.state.property.Properties;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityPose;
import net.minecraft.entity.ai.TargetPredicate;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.world.ServerWorldAccess;
import net.minecraft.util.math.random.Random;
import net.minecraft.class_7;
import net.minecraft.entity.ai.goal.Goal.Control;
import net.minecraft.class_2183.class_2184;
import net.minecraft.util.math.BlockPos.class_2339;
import net.minecraft.class_5132.class_5133;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib.animatable.GeoEntity;
import software.bernie.geckolib.core.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.core.animation.AnimationController;
import software.bernie.geckolib.core.animation.AnimationState;
import software.bernie.geckolib.core.animation.RawAnimation;
import software.bernie.geckolib.core.animation.AnimatableManager.ControllerRegistrar;
import software.bernie.geckolib.core.object.PlayState;
import software.bernie.geckolib.util.GeckoLibUtil;

public class Hamster extends TameableEntity implements GeoEntity {
   protected static final RawAnimation IDLE = RawAnimation.begin().thenLoop("animation.sf_nba.hamster.idle");
   protected static final RawAnimation WALK = RawAnimation.begin().thenLoop("animation.sf_nba.hamster.walk");
   protected static final RawAnimation RUN = RawAnimation.begin().thenLoop("animation.sf_nba.hamster.run");
   protected static final RawAnimation SLEEP = RawAnimation.begin().thenLoop("animation.sf_nba.hamster.sleep");
   protected static final RawAnimation STANDING = RawAnimation.begin().thenLoop("animation.sf_nba.hamster.standing");
   private final AnimatableInstanceCache geoCache = GeckoLibUtil.createInstanceCache(this);
   private static final TrackedData<Integer> EAT_COUNTER;
   private static final Ingredient FOOD_ITEMS;
   private static final TrackedData<Boolean> DATA_INTERESTED;
   private static final TrackedData<Integer> DATA_VARIANT;
   private static final TrackedData<Boolean> FROM_HAND;
   private static final TrackedData<Integer> WAIT_TIME_BEFORE_RUN;
   private static final TrackedData<Integer> WAIT_TIME_WHEN_RUNNING;
   Hamster.HamsterGoToWheelGoal hamsterGoToWheelGoal;

   public Hamster(EntityType<? extends Hamster> entityType, World level) {
      super(entityType, level);
      if (!this.method_6113()) {
         this.method_5952(true);
      }

      this.field_6206 = new Hamster.HamsterLookControl();
      this.method_5941(class_7.field_14, 8.0F);
      this.method_5941(class_7.field_9, 1.0F);
      this.method_5941(class_7.field_3, 1.0F);
      this.method_5941(class_7.field_43351, 1.0F);
      this.method_5941(class_7.field_36432, 1.0F);
      this.method_5941(class_7.field_5, 1.0F);
      this.method_5941(class_7.field_17, 1.0F);
      this.method_5941(class_7.field_4, 1.0F);
   }

   protected void method_5959() {
      this.field_6201.method_6277(0, new SwimGoal(this));
      this.field_6201.method_6277(0, new EscapeDangerGoal(this, 1.3D));
      this.field_6201.method_6277(1, new SitGoal(this));
      this.field_6201.method_6277(2, new AnimalMateGoal(this, 1.0D));
      this.field_6201.method_6277(3, new Hamster.SleepGoal());
      this.hamsterGoToWheelGoal = new Hamster.HamsterGoToWheelGoal();
      this.field_6201.method_6277(4, this.hamsterGoToWheelGoal);
      this.field_6201.method_6277(4, new SearchForItemsGoal(this, 1.25D, FOOD_ITEMS, 8.0D, 8.0D));
      this.field_6201.method_6277(4, new TemptGoal(this, 1.25D, FOOD_ITEMS, false));
      this.field_6201.method_6277(5, new FollowParentGoal(this, 1.25D));
      this.field_6201.method_6277(6, new WanderAroundFarGoal(this, 1.0D));
      this.field_6201.method_6277(7, new LookAtEntityGoal(this, PlayerEntity.class, 6.0F));
      this.field_6201.method_6277(8, new LookAroundGoal(this));
      this.field_6201.method_6277(9, new Hamster.RunInWheelGoal());
   }

   public void method_5958() {
      if (this.method_5962().method_6241()) {
         this.method_5728(this.method_5962().method_6242() >= 1.3D);
      } else {
         this.method_5728(false);
      }

      super.method_5958();
   }

   public static class_5133 createAttributes() {
      return MobEntity.method_26828().method_26868(EntityAttributes.field_23716, 5.0D).method_26868(EntityAttributes.field_23719, 0.25D).method_26868(EntityAttributes.field_23721, 1.5D);
   }

   public ActionResult method_5992(PlayerEntity player, Hand interactionHand) {
      ItemStack itemStack = player.method_5998(interactionHand);
      if (this.method_37908().field_9236) {
         if (this.method_6181() && this.method_6171(player)) {
            return ActionResult.field_5812;
         } else {
            return this.method_6481(itemStack) && (this.method_6032() < this.method_6063() || !this.method_6181()) ? ActionResult.field_5812 : ActionResult.field_5811;
         }
      } else {
         if (this.method_6181()) {
            if (this.method_6171(player) && this.method_6481(itemStack) && this.method_6032() < this.method_6063()) {
               this.method_6475(player, interactionHand, itemStack);
               this.method_6025(2.0F);
               return ActionResult.field_21466;
            }

            if (this.method_6171(player)) {
               ActionResult interactionResult = super.method_5992(player, interactionHand);
               if (!interactionResult.method_23665() || this.method_6109()) {
                  this.method_24346(!this.method_24345());
               }

               if (this.method_6171(player) && player.method_5715()) {
                  this.catchHamster(player);
               }

               return interactionResult;
            }
         } else if (this.method_6481(itemStack)) {
            this.method_6475(player, interactionHand, itemStack);
            if (this.field_5974.method_43048(3) == 0) {
               this.method_6170(player);
               this.method_24346(true);
               this.method_37908().method_8421(this, (byte)7);
            } else {
               this.method_37908().method_8421(this, (byte)6);
            }

            this.method_5971();
            return ActionResult.field_21466;
         }

         return super.method_5992(player, interactionHand);
      }
   }

   protected float method_18394(EntityPose pose, EntityDimensions entityDimensions) {
      return this.method_6109() ? 0.2F : 0.3F;
   }

   public boolean method_6481(ItemStack itemStack) {
      return FOOD_ITEMS.method_8093(itemStack);
   }

   public ActionResult catchHamster(PlayerEntity player) {
      ItemStack output = this.getCaughtItemStack();
      saveDefaultDataToItemTag(this, output);
      if (!player.method_31548().method_7394(output)) {
         ItemEntity itemEntity = new ItemEntity(this.method_37908(), this.method_23317(), this.method_23318() + 0.5D, this.method_23321(), output);
         itemEntity.method_6982(0);
         itemEntity.method_18799(itemEntity.method_18798().method_18805(0.0D, 1.0D, 0.0D));
         this.method_37908().method_8649(itemEntity);
      }

      this.method_31472();
      player.method_31548().method_7394(output);
      return ActionResult.method_29236(true);
   }

   private static void saveDefaultDataToItemTag(Hamster mob, ItemStack itemStack) {
      NbtCompound compoundTag = itemStack.method_7948();
      if (mob.method_16914()) {
         itemStack.method_7977(mob.method_5797());
      }

      try {
         compoundTag.method_10575("Air", (short)mob.method_5669());
         compoundTag.method_10556("Invulnerable", mob.method_5655());
         if (mob.method_5807()) {
            compoundTag.method_10556("CustomNameVisible", mob.method_5807());
         }

         if (mob.method_5701()) {
            compoundTag.method_10556("Silent", mob.method_5701());
         }

         if (mob.method_5740()) {
            compoundTag.method_10556("NoGravity", mob.method_5740());
         }

         if (mob.method_36361()) {
            compoundTag.method_10556("Glowing", true);
         }

         mob.method_5652(compoundTag);
      } catch (Throwable var6) {
         CrashReport crashReport = CrashReport.method_560(var6, "Saving entity NBT");
         class_129 crashReportCategory = crashReport.method_562("Entity being saved");
         mob.method_5819(crashReportCategory);
         throw new CrashException(crashReport);
      }
   }

   public boolean method_17326() {
      return super.method_17326() || this.fromHand();
   }

   public boolean method_5974(double distanceToClosestPlayer) {
      return false;
   }

   public ItemStack getCaughtItemStack() {
      return new ItemStack(HamstersItems.HAMSTER);
   }

   protected SoundEvent method_5994() {
      return HamstersSoundEvents.HAMSTER_AMBIENT;
   }

   protected SoundEvent method_6011(DamageSource damageSource) {
      return HamstersSoundEvents.HAMSTER_HURT;
   }

   protected SoundEvent method_6002() {
      return HamstersSoundEvents.HAMSTER_DEATH;
   }

   protected void method_5712(BlockPos blockPos, BlockState blockState) {
      this.method_5783(SoundEvents.field_14772, 0.15F, 3.0F);
   }

   protected void playBegSound() {
      this.method_5783(HamstersSoundEvents.HAMSTER_BEG, this.method_6107(), this.method_6017());
   }

   protected float method_6107() {
      return 0.4F;
   }

   void clearStates() {
      this.setIsInterested(false);
      this.method_6179(false);
      this.setSleeping(false);
   }

   public void setIsInterested(boolean bl) {
      this.field_6011.method_12778(DATA_INTERESTED, bl);
   }

   public boolean isInterested() {
      return (Boolean)this.field_6011.method_12789(DATA_INTERESTED);
   }

   protected void method_5693() {
      super.method_5693();
      this.field_6011.method_12784(EAT_COUNTER, 0);
      this.field_6011.method_12784(DATA_INTERESTED, false);
      this.field_6011.method_12784(DATA_VARIANT, 2);
      this.field_6011.method_12784(WAIT_TIME_BEFORE_RUN, 0);
      this.field_6011.method_12784(WAIT_TIME_WHEN_RUNNING, 0);
      this.field_6011.method_12784(FROM_HAND, false);
   }

   public void method_5749(NbtCompound compoundTag) {
      super.method_5749(compoundTag);
      this.setVariant(Hamster.Variant.BY_ID[compoundTag.method_10550("Variant")]);
      this.setWaitTimeBeforeRunTicks(compoundTag.method_10550("RunTicks"));
      this.setWaitTimeWhenRunningTicks(compoundTag.method_10550("RunningTicks"));
      this.setFromHand(compoundTag.method_10577("FromHand"));
   }

   public void method_5652(NbtCompound compoundTag) {
      super.method_5652(compoundTag);
      compoundTag.method_10569("Variant", this.getVariant().getId());
      compoundTag.method_10569("RunTicks", this.getWaitTimeBeforeRunTicks());
      compoundTag.method_10569("RunningTicks", this.getWaitTimeWhenRunningTicks());
      compoundTag.method_10556("FromHand", this.fromHand());
   }

   public int getWaitTimeBeforeRunTicks() {
      return (Integer)this.field_6011.method_12789(WAIT_TIME_BEFORE_RUN);
   }

   public void setWaitTimeBeforeRunTicks(int ticks) {
      this.field_6011.method_12778(WAIT_TIME_BEFORE_RUN, ticks);
   }

   public int getWaitTimeWhenRunningTicks() {
      return (Integer)this.field_6011.method_12789(WAIT_TIME_WHEN_RUNNING);
   }

   public void setWaitTimeWhenRunningTicks(int ticks) {
      this.field_6011.method_12778(WAIT_TIME_WHEN_RUNNING, ticks);
   }

   public boolean method_6113() {
      return this.getFlag(32);
   }

   public void setSleeping(boolean bl) {
      this.setFlag(32, bl);
   }

   private void setFlag(int i, boolean bl) {
      if (bl) {
         this.field_6011.method_12778(field_6322, (byte)((Byte)this.field_6011.method_12789(field_6322) | i));
      } else {
         this.field_6011.method_12778(field_6322, (byte)((Byte)this.field_6011.method_12789(field_6322) & ~i));
      }

   }

   private boolean getFlag(int i) {
      return ((Byte)this.field_6011.method_12789(field_6322) & i) != 0;
   }

   void wakeUp() {
      this.setSleeping(false);
   }

   public void method_5773() {
      super.method_5773();
      if (this.method_6034()) {
         if (this.method_5799() || this.method_5968() != null || this.method_37908().method_8546()) {
            this.wakeUp();
         }

         if (this.method_5799() || this.method_6113()) {
            this.method_6179(false);
         }
      }

   }

   public void method_6007() {
      super.method_6007();
      this.method_37908();
      this.method_24515();
      if (this.method_37908().method_8320(this.method_24515()).method_27852(HamstersBlocks.HAMSTER_WHEEL)) {
         this.method_18800(0.0D, 0.0D, 0.0D);
      }

      if (this.getWaitTimeWhenRunningTicks() > 0) {
         this.setWaitTimeWhenRunningTicks(this.getWaitTimeWhenRunningTicks() - 1);
      }

      if (this.method_5765() && this.method_5854() instanceof SeatEntity && this.getWaitTimeWhenRunningTicks() == 0) {
         this.setWaitTimeBeforeRunTicks(this.field_5974.method_43048(400) + 1200);
         this.method_5848();
         this.clearStates();
      }

      if (this.isInterested() && this.field_6012 % 40 == 0) {
         this.playBegSound();
      }

      List list;
      Iterator var2;
      PlayerEntity player;
      if (this.method_5805() && !this.isInterested() && !this.method_6113() && !this.method_6062() && this.method_5968() == null) {
         list = this.method_37908().method_18467(PlayerEntity.class, this.method_5829().method_1009(8.0D, 4.0D, 8.0D));
         var2 = list.iterator();

         while(var2.hasNext()) {
            player = (PlayerEntity)var2.next();
            if (!player.method_7325() && player.method_24520(FOOD_ITEMS) && this.method_5858(player) < 2.0D) {
               this.setIsInterested(true);
               this.method_5942().method_6340();
            }
         }
      }

      if (this.isInterested() && this.method_5968() == null) {
         list = this.method_37908().method_18467(PlayerEntity.class, this.method_5829().method_1009(8.0D, 4.0D, 8.0D));
         var2 = list.iterator();

         while(var2.hasNext()) {
            player = (PlayerEntity)var2.next();
            if (!player.method_7325() && player.method_24520(FOOD_ITEMS) && this.method_5858(player) > 2.0D) {
               this.setIsInterested(false);
            }
         }
      }

      if (this.method_6113() || this.method_6062()) {
         this.field_6282 = false;
         this.field_6212 = 0.0F;
         this.field_6250 = 0.0F;
      }

   }

   public boolean method_6474(AnimalEntity animal) {
      if (!this.method_6181()) {
         return false;
      } else if (!(animal instanceof Hamster)) {
         return false;
      } else {
         Hamster hamster = (Hamster)animal;
         return hamster.method_6181() && super.method_6474(animal);
      }
   }

   public Hamster.Variant getVariant() {
      return Hamster.Variant.BY_ID[(Integer)this.field_6011.method_12789(DATA_VARIANT)];
   }

   public void setVariant(Hamster.Variant variant) {
      this.field_6011.method_12778(DATA_VARIANT, variant.getId());
   }

   public boolean fromHand() {
      return (Boolean)this.field_6011.method_12789(FROM_HAND);
   }

   public void setFromHand(boolean fromHand) {
      this.field_6011.method_12778(FROM_HAND, fromHand);
   }

   @Nullable
   public Hamster getBreedOffspring(@NotNull ServerWorld level, @NotNull PassiveEntity ageableMob) {
      Hamster hamster = (Hamster)HamstersEntityType.HAMSTER.method_5883(level);
      if (hamster != null && ageableMob instanceof Hamster) {
         Hamster hamster1 = (Hamster)ageableMob;
         if (this.field_5974.method_43056()) {
            hamster.setVariant(this.getVariant());
         } else {
            hamster.setVariant(hamster1.getVariant());
         }

         if (this.method_6181()) {
            hamster.method_6174(this.method_6139());
            hamster.method_6173(true);
         }
      }

      return hamster;
   }

   public boolean method_18397(ItemStack pItemstack) {
      EquipmentSlot slot = method_32326(pItemstack);
      if (!this.method_6118(slot).method_7960()) {
         return false;
      } else {
         return slot == EquipmentSlot.field_6173 && super.method_18397(pItemstack);
      }
   }

   protected void method_5949(ItemEntity pItemEntity) {
      ItemStack stack = pItemEntity.method_6983();
      if (!this.method_6113() && this.method_6047().method_7960() && FOOD_ITEMS.method_8093(stack)) {
         this.method_29499(pItemEntity);
         this.method_5673(EquipmentSlot.field_6173, stack);
         this.field_6187[EquipmentSlot.field_6173.method_5927()] = 2.0F;
         this.method_6103(pItemEntity, stack.method_7947());
         pItemEntity.method_31472();
      }

   }

   public boolean method_5643(DamageSource pSource, float pAmount) {
      if (!this.method_6047().method_7960() && !this.method_37908().field_9236) {
         ItemEntity itemEntity = new ItemEntity(this.method_37908(), this.method_23317() + this.method_5720().field_1352, this.method_23318() + 1.0D, this.method_23321() + this.method_5720().field_1350, this.method_6047());
         itemEntity.method_6982(40);
         itemEntity.method_6981(this.method_5667());
         this.method_5783(SoundEvents.field_18054, 1.0F, 1.0F);
         this.method_37908().method_8649(itemEntity);
         this.method_5673(EquipmentSlot.field_6173, ItemStack.field_8037);
      }

      return super.method_5643(pSource, pAmount);
   }

   public EntityData method_5943(ServerWorldAccess pLevel, LocalDifficulty pDifficulty, SpawnReason pReason, @Nullable EntityData pSpawnData, @Nullable NbtCompound pDataTag) {
      this.method_5964(this.field_5974, pDifficulty);
      if (pSpawnData == null) {
         Random randomSource = pLevel.method_8409();
         this.setVariant(Hamster.Variant.values()[randomSource.method_43048(Hamster.Variant.values().length)]);
      }

      return pSpawnData;
   }

   protected void method_5964(Random random, LocalDifficulty pDifficulty) {
      if (random.method_43057() < 0.2F) {
         float chance = random.method_43057();
         ItemStack stack;
         if (chance < 0.1F) {
            stack = new ItemStack(Items.field_8309);
         } else if (chance < 0.15F) {
            stack = new ItemStack(Items.field_8706);
         } else if (chance < 0.3F) {
            stack = new ItemStack(Items.field_8188);
         } else {
            stack = new ItemStack(Items.field_8317);
         }

         this.method_5673(EquipmentSlot.field_6173, stack);
      }

   }

   public void registerControllers(ControllerRegistrar controllerRegistrar) {
      controllerRegistrar.add(new AnimationController[]{new AnimationController(this, "controller", 0, this::animController)});
   }

   protected <E extends Hamster> PlayState animController(AnimationState<E> event) {
      if (this.method_6113()) {
         event.setAnimation(SLEEP);
      } else if (!this.isInterested() && !this.method_6172()) {
         if (event.isMoving()) {
            if (this.method_5624()) {
               event.setControllerSpeed(1.3F);
               event.setAnimation(RUN);
            } else {
               event.setControllerSpeed(1.1F);
               event.setAnimation(WALK);
            }
         } else if (this.method_5765() && this.method_5854() instanceof SeatEntity) {
            event.setControllerSpeed(1.4F);
            event.setAnimation(WALK);
         } else {
            event.setAnimation(IDLE);
         }
      } else {
         event.setAnimation(STANDING);
      }

      return PlayState.CONTINUE;
   }

   public AnimatableInstanceCache getAnimatableInstanceCache() {
      return this.geoCache;
   }

   static {
      EAT_COUNTER = DataTracker.method_12791(Hamster.class, TrackedDataHandlerRegistry.field_13327);
      FOOD_ITEMS = Ingredient.method_8106(HamstersTags.HAMSTER_FOOD);
      DATA_INTERESTED = DataTracker.method_12791(Hamster.class, TrackedDataHandlerRegistry.field_13323);
      DATA_VARIANT = DataTracker.method_12791(Hamster.class, TrackedDataHandlerRegistry.field_13327);
      FROM_HAND = DataTracker.method_12791(Hamster.class, TrackedDataHandlerRegistry.field_13323);
      WAIT_TIME_BEFORE_RUN = DataTracker.method_12791(Hamster.class, TrackedDataHandlerRegistry.field_13327);
      WAIT_TIME_WHEN_RUNNING = DataTracker.method_12791(Hamster.class, TrackedDataHandlerRegistry.field_13327);
   }

   public class HamsterLookControl extends LookControl {
      public HamsterLookControl() {
         super(Hamster.this);
      }

      public void method_6231() {
         if (!(Hamster.this.method_5854() instanceof SeatEntity)) {
            super.method_6231();
         } else {
            BlockState state = Hamster.this.method_37908().method_8320(Hamster.this.method_24515());
            if (state.method_27852(HamstersBlocks.HAMSTER_WHEEL)) {
               BlockPos pos1;
               if (state.method_11654(HamsterWheelBlock.FACING) == Direction.field_11035) {
                  pos1 = Hamster.this.method_24515().method_10089(1);
               } else if (state.method_11654(HamsterWheelBlock.FACING) == Direction.field_11043) {
                  pos1 = Hamster.this.method_24515().method_10088(1);
               } else if (state.method_11654(HamsterWheelBlock.FACING) == Direction.field_11034) {
                  pos1 = Hamster.this.method_24515().method_10076(1);
               } else {
                  pos1 = Hamster.this.method_24515().method_10077(1);
               }

               Hamster.this.setSleeping(false);
               Hamster.this.method_6179(false);
               Hamster.this.method_5702(class_2184.field_9853, new Vec3d((double)((float)pos1.method_10263() + 0.5F), (double)((float)pos1.method_10264() + 0.5F), (double)((float)pos1.method_10260() + 0.5F)));
            }
         }

      }

      protected boolean method_20433() {
         return !(Hamster.this.method_5854() instanceof SeatEntity);
      }
   }

   private class SleepGoal extends Goal {
      private final TargetPredicate alertableTargeting = TargetPredicate.method_36626().method_18418(6.0D).method_36627().method_18420(new Hamster.HamsterAlertableEntitiesSelector());
      private final int WAIT_TIME_BEFORE_SLEEP;
      private int countdown;

      public SleepGoal() {
         this.WAIT_TIME_BEFORE_SLEEP = Hamster.this.field_5974.method_43048(100) + 100;
         this.countdown = Hamster.this.field_5974.method_43048(this.WAIT_TIME_BEFORE_SLEEP);
         this.method_6265(EnumSet.of(Control.field_18405, Control.field_18406, Control.field_18407));
      }

      public boolean method_6264() {
         if (Hamster.this.field_6212 == 0.0F && Hamster.this.field_6227 == 0.0F && Hamster.this.field_6250 == 0.0F) {
            return this.canSleep() || Hamster.this.method_6113();
         } else {
            return false;
         }
      }

      public boolean method_6266() {
         return this.canSleep();
      }

      private boolean canSleep() {
         if (this.countdown > 0) {
            --this.countdown;
            return false;
         } else {
            return (Hamster.this.method_37908().method_8532() >= 1000L && Hamster.this.method_37908().method_8532() <= 10000L || Hamster.this.method_37908().method_8532() >= 16000L && Hamster.this.method_37908().method_8532() <= 21000L) && !Hamster.this.field_27857 && !this.alertable() && !Hamster.this.method_5765();
         }
      }

      public void method_6270() {
         this.countdown = Hamster.this.field_5974.method_43048(this.WAIT_TIME_BEFORE_SLEEP);
         Hamster.this.clearStates();
      }

      public void method_6269() {
         Hamster.this.method_6179(false);
         Hamster.this.setIsInterested(false);
         Hamster.this.method_6100(false);
         Hamster.this.setSleeping(true);
         Hamster.this.method_5942().method_6340();
         Hamster.this.method_5962().method_6239(Hamster.this.method_23317(), Hamster.this.method_23318(), Hamster.this.method_23321(), 0.0D);
      }

      protected boolean alertable() {
         return !Hamster.this.method_37908().method_18466(LivingEntity.class, this.alertableTargeting, Hamster.this, Hamster.this.method_5829().method_1009(12.0D, 6.0D, 12.0D)).isEmpty();
      }
   }

   class HamsterGoToWheelGoal extends Goal {
      private final Predicate<BlockState> VALID_GATHERING_BLOCKS = (blockState) -> {
         if (!blockState.method_27852(HamstersBlocks.HAMSTER_WHEEL)) {
            return false;
         } else {
            return !blockState.method_28498(Properties.field_12508) || !(Boolean)blockState.method_11654(Properties.field_12508);
         }
      };
      @Nullable
      private Vec3d wheelPos;

      HamsterGoToWheelGoal() {
         this.method_6265(EnumSet.of(Control.field_18405));
      }

      public boolean method_6264() {
         Optional<BlockPos> optional = this.findNearbyResource();
         if (optional.isPresent() && !HamsterWheelBlock.isOccupied(Hamster.this.method_37908(), (BlockPos)optional.get()) && Hamster.this.getWaitTimeBeforeRunTicks() == 0) {
            Hamster.this.field_6189.method_6337((double)((BlockPos)optional.get()).method_10263() + 0.5D, (double)((BlockPos)optional.get()).method_10264(), (double)((BlockPos)optional.get()).method_10260() + 0.5D, 1.2000000476837158D);
            return !Hamster.this.method_37908().method_8419() && !Hamster.this.method_6113() && !Hamster.this.method_6172();
         } else {
            return false;
         }
      }

      public boolean method_6266() {
         Optional<BlockPos> optional = this.findNearbyResource();
         if (optional.isPresent() && !HamsterWheelBlock.isOccupied(Hamster.this.method_37908(), (BlockPos)optional.get()) && Hamster.this.getWaitTimeBeforeRunTicks() == 0) {
            return !Hamster.this.method_37908().method_8419() && !Hamster.this.method_6113() && !Hamster.this.method_6172();
         } else {
            return false;
         }
      }

      public void method_6270() {
         Hamster.this.field_6189.method_6340();
      }

      public boolean method_38846() {
         return true;
      }

      public void method_6268() {
         Optional<BlockPos> optional = this.findNearbyResource();
         if (HamsterWheelBlock.isOccupied(Hamster.this.method_37908(), (BlockPos)optional.get())) {
            this.method_6270();
         }

         if (!HamsterWheelBlock.isOccupied(Hamster.this.method_37908(), (BlockPos)optional.get()) && Hamster.this.getWaitTimeBeforeRunTicks() == 0) {
            Vec3d vec3 = Vec3d.method_24955((Box2)optional.get());
            if (vec3.method_1022(Hamster.this.method_19538()) > 1.4D) {
               this.wheelPos = vec3;
               this.setWantedPos();
               return;
            }

            if (this.wheelPos == null) {
               this.wheelPos = vec3;
            }

            if (Hamster.this.method_19538().method_1022(this.wheelPos) <= 1.4D) {
               Hamster.this.setWaitTimeWhenRunningTicks(Hamster.this.field_5974.method_43048(300) + 100);
               HamsterWheelBlock.sitDown(Hamster.this.method_37908(), (BlockPos)optional.get(), Hamster.this);
               this.method_6270();
            }
         }

      }

      private void setWantedPos() {
         Hamster.this.method_5962().method_6239(this.wheelPos.method_10216(), this.wheelPos.method_10214(), this.wheelPos.method_10215(), 0.699999988079071D);
      }

      private Optional<BlockPos> findNearbyResource() {
         return this.findNearestBlock(this.VALID_GATHERING_BLOCKS);
      }

      private Optional<BlockPos> findNearestBlock(Predicate<BlockState> predicate) {
         BlockPos blockPos = Hamster.this.method_24515();
         class_2339 mutableBlockPos = new class_2339();

         for(int i = 0; (double)i <= 5.0D; i = i > 0 ? -i : 1 - i) {
            for(int j = 0; (double)j < 5.0D; ++j) {
               for(int k = 0; k <= j; k = k > 0 ? -k : 1 - k) {
                  for(int l = k < j && k > -j ? j : 0; l <= j; l = l > 0 ? -l : 1 - l) {
                     mutableBlockPos.method_25504(blockPos, k, i - 1, l);
                     if (blockPos.method_19771(mutableBlockPos, 5.0D) && predicate.test(Hamster.this.method_37908().method_8320(mutableBlockPos))) {
                        return Optional.of(mutableBlockPos);
                     }
                  }
               }
            }
         }

         return Optional.empty();
      }
   }

   private class RunInWheelGoal extends Goal {
      public RunInWheelGoal() {
         this.method_6265(EnumSet.of(Control.field_18405, Control.field_18406, Control.field_18407));
      }

      public boolean method_6264() {
         return !Hamster.this.method_6113() && !Hamster.this.field_27857 && Hamster.this.method_5765() && Hamster.this.method_5854() instanceof SeatEntity;
      }

      public boolean method_6266() {
         return Hamster.this.method_5765() && Hamster.this.method_5854() instanceof SeatEntity;
      }
   }

   public static enum Variant {
      WHITE(0, "white"),
      PEACHES_AND_CREAM(1, "peaches_and_cream"),
      ORANGE(2, "orange"),
      GREY_WHITE(3, "grey_white"),
      BROWN(4, "brown"),
      BLACK_WHITE(5, "black_white"),
      BLACK(6, "black");

      public static final Hamster.Variant[] BY_ID = (Hamster.Variant[])Arrays.stream(values()).sorted(Comparator.comparingInt(Hamster.Variant::getId)).toArray((x$0) -> {
         return new Hamster.Variant[x$0];
      });
      private final int id;
      private final String name;

      private Variant(int j, String string2) {
         this.id = j;
         this.name = string2;
      }

      public int getId() {
         return this.id;
      }

      public String getName() {
         return this.name;
      }

      public static Hamster.Variant getTypeById(int id) {
         Hamster.Variant[] var1 = values();
         int var2 = var1.length;

         for(int var3 = 0; var3 < var2; ++var3) {
            Hamster.Variant type = var1[var3];
            if (type.id == id) {
               return type;
            }
         }

         return ORANGE;
      }

      // $FF: synthetic method
      private static Hamster.Variant[] $values() {
         return new Hamster.Variant[]{WHITE, PEACHES_AND_CREAM, ORANGE, GREY_WHITE, BROWN, BLACK_WHITE, BLACK};
      }
   }

   public static class HamsterAlertableEntitiesSelector implements Predicate<LivingEntity> {
      public boolean test(LivingEntity livingEntity) {
         if (livingEntity instanceof Hamster) {
            return false;
         } else if (livingEntity instanceof PlayerEntity && (livingEntity.method_7325() || ((PlayerEntity)livingEntity).method_7337())) {
            return false;
         } else {
            return !livingEntity.method_6113() && !livingEntity.method_21751();
         }
      }
   }
}
