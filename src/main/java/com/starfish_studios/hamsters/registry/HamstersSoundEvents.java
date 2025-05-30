package com.starfish_studios.hamsters.registry;

import net.minecraft.class_2378;
import net.minecraft.class_2498;
import net.minecraft.class_2960;
import net.minecraft.class_3414;
import net.minecraft.class_7923;

public interface HamstersSoundEvents {
   class_3414 HAMSTER_AMBIENT = register("entity.hamster.ambient");
   class_3414 HAMSTER_HURT = register("entity.hamster.hurt");
   class_3414 HAMSTER_DEATH = register("entity.hamster.death");
   class_3414 HAMSTER_BEG = register("entity.hamster.beg");
   class_3414 HAMSTER_SLEEP = register("entity.hamster.sleep");

   private static class_2498 register(String name, float volume, float pitch) {
      return new class_2498(volume, pitch, register("block." + name + ".break"), register("block." + name + ".step"), register("block." + name + ".place"), register("block." + name + ".hit"), register("block." + name + ".fall"));
   }

   static class_3414 register(String name) {
      class_2960 id = new class_2960("hamsters", name);
      return (class_3414)class_2378.method_10230(class_7923.field_41172, id, class_3414.method_47908(id));
   }
}
