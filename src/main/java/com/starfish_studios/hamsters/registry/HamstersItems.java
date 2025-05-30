package com.starfish_studios.hamsters.registry;

import com.starfish_studios.hamsters.item.HamsterItem;
import com.starfish_studios.hamsters.item.HamsterWheelItem;
import java.util.function.Supplier;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.class_1747;
import net.minecraft.class_1792;
import net.minecraft.class_1826;
import net.minecraft.class_2378;
import net.minecraft.class_2960;
import net.minecraft.class_7923;

public class HamstersItems {
   public static final class_1792 HAMSTER_SPAWN_EGG;
   public static final class_1792 HAMSTER;
   public static final class_1747 HAMSTER_WHEEL;

   public static <T extends class_1792> Supplier<T> registerItem(String name, Supplier<T> item) {
      T registry = (class_1792)class_2378.method_10230(class_7923.field_41178, new class_2960("hamsters", name), (class_1792)item.get());
      return () -> {
         return registry;
      };
   }

   private static class_1792 register(String id, class_1792 item) {
      return (class_1792)class_2378.method_10230(class_7923.field_41178, new class_2960("hamsters", id), item);
   }

   static {
      HAMSTER_SPAWN_EGG = register("hamster_spawn_egg", new class_1826(HamstersEntityType.HAMSTER, 16747824, 16775119, new FabricItemSettings()));
      HAMSTER = register("hamster", new HamsterItem((new FabricItemSettings()).method_7889(1)));
      HAMSTER_WHEEL = (class_1747)register("hamster_wheel", new HamsterWheelItem(HamstersBlocks.HAMSTER_WHEEL, new FabricItemSettings()));
   }
}
