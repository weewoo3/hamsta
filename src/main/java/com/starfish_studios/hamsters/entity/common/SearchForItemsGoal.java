package com.starfish_studios.hamsters.entity.common;

import com.starfish_studios.hamsters.entity.Hamster;
import java.util.EnumSet;
import java.util.List;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.entity.ItemEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.recipe.Ingredient;
import net.minecraft.entity.ai.goal.Goal.Control;

public class SearchForItemsGoal extends Goal {
   private final Hamster mob;
   private final double speedModifier;
   private final double horizontalSearchRange;
   private final double verticalSearchRange;
   private final Ingredient ingredient;

   public SearchForItemsGoal(Hamster mob, double speedModifier, Ingredient ingredient, double horizontalSearchRange, double verticalSearchRange) {
      this.method_6265(EnumSet.of(Control.field_18405));
      this.mob = mob;
      this.speedModifier = speedModifier;
      this.ingredient = ingredient;
      this.horizontalSearchRange = horizontalSearchRange;
      this.verticalSearchRange = verticalSearchRange;
   }

   public boolean method_6264() {
      if (this.mob.method_6118(EquipmentSlot.field_6173).method_7960() && !this.mob.method_6172()) {
         List<ItemEntity> list = this.mob.method_37908().method_8390(ItemEntity.class, this.mob.method_5829().method_1009(this.horizontalSearchRange, this.verticalSearchRange, this.horizontalSearchRange), (itemEntity) -> {
            return this.ingredient.method_8093(itemEntity.method_6983());
         });
         return !list.isEmpty() && this.mob.method_6118(EquipmentSlot.field_6173).method_7960();
      } else {
         return false;
      }
   }

   public boolean method_6266() {
      return super.method_6266() && !this.mob.method_6172();
   }

   public void method_6268() {
      List<ItemEntity> list = this.mob.method_37908().method_8390(ItemEntity.class, this.mob.method_5829().method_1009(this.horizontalSearchRange, this.verticalSearchRange, this.horizontalSearchRange), (itemEntity) -> {
         return this.ingredient.method_8093(itemEntity.method_6983());
      });
      ItemStack itemstack = this.mob.method_6118(EquipmentSlot.field_6173);
      if (itemstack.method_7960() && !list.isEmpty()) {
         this.mob.method_5942().method_6335((Entity)list.get(0), this.speedModifier);
      }

   }

   public void method_6269() {
      List<ItemEntity> list = this.mob.method_37908().method_8390(ItemEntity.class, this.mob.method_5829().method_1009(this.horizontalSearchRange, this.verticalSearchRange, this.horizontalSearchRange), (itemEntity) -> {
         return this.ingredient.method_8093(itemEntity.method_6983());
      });
      if (!list.isEmpty()) {
         this.mob.method_5942().method_6335((Entity)list.get(0), this.speedModifier);
      }

   }
}
