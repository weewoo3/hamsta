package com.starfish_studios.hamsters.registry;

import com.starfish_studios.hamsters.block.HamsterWheelBlock;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.class_2248;
import net.minecraft.class_2378;
import net.minecraft.class_2960;
import net.minecraft.class_7923;

public class HamstersBlocks {
   public static final class_2248 HAMSTER_WHEEL = register("hamster_wheel", new HamsterWheelBlock(FabricBlockSettings.create().strength(0.6F).method_22488().method_26243((state, world, pos) -> {
      return false;
   })));

   private static class_2248 register(String id, class_2248 block) {
      return (class_2248)class_2378.method_10230(class_7923.field_41175, new class_2960("hamsters", id), block);
   }
}
