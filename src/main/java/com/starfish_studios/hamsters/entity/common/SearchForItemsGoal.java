package com.starfish_studios.hamsters.entity.common;

import com.starfish_studios.hamsters.entity.Hamster;
import java.util.EnumSet;
import java.util.List;
import net.minecraft.class_1297;
import net.minecraft.class_1304;
import net.minecraft.class_1352;
import net.minecraft.class_1542;
import net.minecraft.class_1799;
import net.minecraft.class_1856;
import net.minecraft.class_1352.class_4134;

public class SearchForItemsGoal extends class_1352 {
   private final Hamster mob;
   private final double speedModifier;
   private final double horizontalSearchRange;
   private final double verticalSearchRange;
   private final class_1856 ingredient;

   public SearchForItemsGoal(Hamster mob, double speedModifier, class_1856 ingredient, double horizontalSearchRange, double verticalSearchRange) {
      this.method_6265(EnumSet.of(class_4134.field_18405));
      this.mob = mob;
      this.speedModifier = speedModifier;
      this.ingredient = ingredient;
      this.horizontalSearchRange = horizontalSearchRange;
      this.verticalSearchRange = verticalSearchRange;
   }

   public boolean method_6264() {
      if (this.mob.method_6118(class_1304.field_6173).method_7960() && !this.mob.method_6172()) {
         List<class_1542> list = this.mob.method_37908().method_8390(class_1542.class, this.mob.method_5829().method_1009(this.horizontalSearchRange, this.verticalSearchRange, this.horizontalSearchRange), (itemEntity) -> {
            return this.ingredient.method_8093(itemEntity.method_6983());
         });
         return !list.isEmpty() && this.mob.method_6118(class_1304.field_6173).method_7960();
      } else {
         return false;
      }
   }

   public boolean method_6266() {
      return super.method_6266() && !this.mob.method_6172();
   }

   public void method_6268() {
      List<class_1542> list = this.mob.method_37908().method_8390(class_1542.class, this.mob.method_5829().method_1009(this.horizontalSearchRange, this.verticalSearchRange, this.horizontalSearchRange), (itemEntity) -> {
         return this.ingredient.method_8093(itemEntity.method_6983());
      });
      class_1799 itemstack = this.mob.method_6118(class_1304.field_6173);
      if (itemstack.method_7960() && !list.isEmpty()) {
         this.mob.method_5942().method_6335((class_1297)list.get(0), this.speedModifier);
      }

   }

   public void method_6269() {
      List<class_1542> list = this.mob.method_37908().method_8390(class_1542.class, this.mob.method_5829().method_1009(this.horizontalSearchRange, this.verticalSearchRange, this.horizontalSearchRange), (itemEntity) -> {
         return this.ingredient.method_8093(itemEntity.method_6983());
      });
      if (!list.isEmpty()) {
         this.mob.method_5942().method_6335((class_1297)list.get(0), this.speedModifier);
      }

   }
}
