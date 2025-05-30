package com.starfish_studios.hamsters.registry;

import java.util.Objects;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.class_1761;
import net.minecraft.class_1792;
import net.minecraft.class_1799;
import net.minecraft.class_2378;
import net.minecraft.class_2561;
import net.minecraft.class_2960;
import net.minecraft.class_7923;
import net.minecraft.class_1761.class_7913;

public class HamstersCreativeModeTab {
   public static final class_1761 ITEM_GROUP;

   private static class_1761 register(String id, class_1761 tab) {
      return (class_1761)class_2378.method_10230(class_7923.field_44687, new class_2960("hamsters", id), tab);
   }

   static {
      class_7913 var10001 = FabricItemGroup.builder();
      class_1792 var10002 = HamstersItems.HAMSTER_SPAWN_EGG;
      Objects.requireNonNull(var10002);
      ITEM_GROUP = register("item_group", var10001.method_47320(var10002::method_7854).method_47321(class_2561.method_43471("itemGroup.hamsters.tab")).method_47317((featureFlagSet, output) -> {
         output.method_45421(HamstersItems.HAMSTER_WHEEL);
         output.method_45421(HamstersItems.HAMSTER_SPAWN_EGG);
         class_1799 item0 = new class_1799(HamstersItems.HAMSTER);
         item0.method_7948().method_10569("Variant", 0);
         output.method_45420(item0);
         class_1799 item1 = new class_1799(HamstersItems.HAMSTER);
         item1.method_7948().method_10569("Variant", 1);
         output.method_45420(item1);
         class_1799 item2 = new class_1799(HamstersItems.HAMSTER);
         item2.method_7948().method_10569("Variant", 2);
         output.method_45420(item2);
         class_1799 item3 = new class_1799(HamstersItems.HAMSTER);
         item3.method_7948().method_10569("Variant", 3);
         output.method_45420(item3);
         class_1799 item4 = new class_1799(HamstersItems.HAMSTER);
         item4.method_7948().method_10569("Variant", 4);
         output.method_45420(item4);
         class_1799 item5 = new class_1799(HamstersItems.HAMSTER);
         item5.method_7948().method_10569("Variant", 5);
         output.method_45420(item5);
         class_1799 item6 = new class_1799(HamstersItems.HAMSTER);
         item6.method_7948().method_10569("Variant", 6);
         output.method_45420(item6);
      }).method_47324());
   }
}
