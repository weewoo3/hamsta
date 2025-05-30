package com.starfish_studios.hamsters.registry;

import com.mojang.datafixers.types.Type;
import com.starfish_studios.hamsters.block.entity.HamsterWheelBlockEntity;
import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.minecraft.block.Block;
import net.minecraft.class_2378;
import net.minecraft.util.shape.VoxelShapes1;
import net.minecraft.class_7923;

public class HamstersBlockEntities {
   public static final VoxelShapes1<HamsterWheelBlockEntity> HAMSTER_WHEEL;

   static {
      HAMSTER_WHEEL = (VoxelShapes1)class_2378.method_10226(class_7923.field_41181, "hamsters:hamsters", FabricBlockEntityTypeBuilder.create(HamsterWheelBlockEntity::new, new Block[]{HamstersBlocks.HAMSTER_WHEEL}).build((Type)null));
   }
}
