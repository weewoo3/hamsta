package com.starfish_studios.hamsters.registry;

import com.starfish_studios.hamsters.entity.Hamster;
import com.starfish_studios.hamsters.entity.SeatEntity;
import java.util.function.Supplier;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.minecraft.class_1297;
import net.minecraft.class_1299;
import net.minecraft.class_1311;
import net.minecraft.class_1429;
import net.minecraft.class_2378;
import net.minecraft.class_2960;
import net.minecraft.class_4048;
import net.minecraft.class_7923;
import net.minecraft.class_1299.class_4049;
import net.minecraft.class_1317.class_1319;
import net.minecraft.class_2902.class_2903;

public class HamstersEntityType {
   public static final class_1299<Hamster> HAMSTER;
   public static final Supplier<class_1299<SeatEntity>> SEAT;

   public static <T extends class_1297> Supplier<class_1299<T>> registerEntityType(String name, class_4049<T> factory, class_1311 category, float width, float height) {
      class_1299<T> registry = (class_1299)class_2378.method_10230(class_7923.field_41177, new class_2960("hamsters", name), FabricEntityTypeBuilder.create(category, factory).dimensions(class_4048.method_18385(width, height)).build());
      return () -> {
         return registry;
      };
   }

   private static <T extends class_1297> class_1299<T> register(String id, FabricEntityTypeBuilder<T> entityType) {
      return (class_1299)class_2378.method_10230(class_7923.field_41177, new class_2960("hamsters", id), entityType.build());
   }

   static {
      HAMSTER = register("hamster", FabricEntityTypeBuilder.createMob().entityFactory(Hamster::new).defaultAttributes(Hamster::createAttributes).spawnGroup(class_1311.field_6294).spawnRestriction(class_1319.field_6317, class_2903.field_13194, class_1429::method_20663).dimensions(class_4048.method_18384(0.5F, 0.5F)).trackRangeChunks(10));
      SEAT = registerEntityType("seat", (type, world) -> {
         return new SeatEntity(world);
      }, class_1311.field_17715, 0.0F, 0.0F);
      BiomeModifications.addSpawn(BiomeSelectors.tag(HamstersTags.HAS_HAMSTER), class_1311.field_6294, HAMSTER, 30, 1, 1);
   }
}
