package com.starfish_studios.hamsters.registry;

import com.mojang.datafixers.types.Type;
import com.starfish_studios.hamsters.block.entity.HamsterWheelBlockEntity;
import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.minecraft.class_2248;
import net.minecraft.class_2378;
import net.minecraft.class_2591;
import net.minecraft.class_7923;

public class HamstersBlockEntities {
   public static final class_2591<HamsterWheelBlockEntity> HAMSTER_WHEEL;

   static {
      HAMSTER_WHEEL = (class_2591)class_2378.method_10226(class_7923.field_41181, "hamsters:hamsters", FabricBlockEntityTypeBuilder.create(HamsterWheelBlockEntity::new, new class_2248[]{HamstersBlocks.HAMSTER_WHEEL}).build((Type)null));
   }
}
