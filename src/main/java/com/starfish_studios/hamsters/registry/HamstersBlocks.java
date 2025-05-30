package com.starfish_studios.hamsters.registry;

import com.starfish_studios.hamsters.block.HamsterWheelBlock;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.class_2378;
import net.minecraft.util.Identifier;
import net.minecraft.class_7923;

public class HamstersBlocks {
   public static final Block HAMSTER_WHEEL = register("hamster_wheel", new HamsterWheelBlock(FabricBlockSettings.create().strength(0.6F).method_22488().method_26243((state, world, pos) -> {
      return false;
   })));

   private static Block register(String id, Block block) {
      return (Block)class_2378.method_10230(class_7923.field_41175, new Identifier("hamsters", id), block);
   }
}
