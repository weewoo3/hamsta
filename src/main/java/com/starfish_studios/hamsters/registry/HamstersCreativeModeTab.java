package com.starfish_studios.hamsters.registry;

import java.util.Objects;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.class_1761;
import net.minecraft.class_1792;
import net.minecraft.item.ItemStack;
import net.minecraft.class_2378;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.minecraft.class_7923;
import net.minecraft.class_1761.class_7913;

public class HamstersCreativeModeTab {
   public static final class_1761 ITEM_GROUP;

   private static class_1761 register(String id, class_1761 tab) {
      return (class_1761)class_2378.method_10230(class_7923.field_44687, new Identifier("hamsters", id), tab);
   }

   static {
      class_7913 var10001 = FabricItemGroup.builder();
      class_1792 var10002 = HamstersItems.HAMSTER_SPAWN_EGG;
      Objects.requireNonNull(var10002);
      ITEM_GROUP = register("item_group", var10001.method_47320(var10002::method_7854).method_47321(Text.method_43471("itemGroup.hamsters.tab")).method_47317((featureFlagSet, output) -> {
         output.method_45421(HamstersItems.HAMSTER_WHEEL);
         output.method_45421(HamstersItems.HAMSTER_SPAWN_EGG);
         ItemStack item0 = new ItemStack(HamstersItems.HAMSTER);
         item0.method_7948().method_10569("Variant", 0);
         output.method_45420(item0);
         ItemStack item1 = new ItemStack(HamstersItems.HAMSTER);
         item1.method_7948().method_10569("Variant", 1);
         output.method_45420(item1);
         ItemStack item2 = new ItemStack(HamstersItems.HAMSTER);
         item2.method_7948().method_10569("Variant", 2);
         output.method_45420(item2);
         ItemStack item3 = new ItemStack(HamstersItems.HAMSTER);
         item3.method_7948().method_10569("Variant", 3);
         output.method_45420(item3);
         ItemStack item4 = new ItemStack(HamstersItems.HAMSTER);
         item4.method_7948().method_10569("Variant", 4);
         output.method_45420(item4);
         ItemStack item5 = new ItemStack(HamstersItems.HAMSTER);
         item5.method_7948().method_10569("Variant", 5);
         output.method_45420(item5);
         ItemStack item6 = new ItemStack(HamstersItems.HAMSTER);
         item6.method_7948().method_10569("Variant", 6);
         output.method_45420(item6);
      }).method_47324());
   }
}
