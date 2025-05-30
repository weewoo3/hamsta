package com.starfish_studios.hamsters.registry;

import net.minecraft.class_2378;
import net.minecraft.class_2498;
import net.minecraft.util.Identifier;
import net.minecraft.sound.SoundEvent;
import net.minecraft.class_7923;

public interface HamstersSoundEvents {
   SoundEvent HAMSTER_AMBIENT = register("entity.hamster.ambient");
   SoundEvent HAMSTER_HURT = register("entity.hamster.hurt");
   SoundEvent HAMSTER_DEATH = register("entity.hamster.death");
   SoundEvent HAMSTER_BEG = register("entity.hamster.beg");
   SoundEvent HAMSTER_SLEEP = register("entity.hamster.sleep");

   private static class_2498 register(String name, float volume, float pitch) {
      return new class_2498(volume, pitch, register("block." + name + ".break"), register("block." + name + ".step"), register("block." + name + ".place"), register("block." + name + ".hit"), register("block." + name + ".fall"));
   }

   static SoundEvent register(String name) {
      Identifier id = new Identifier("hamsters", name);
      return (SoundEvent)class_2378.method_10230(class_7923.field_41172, id, SoundEvent.method_47908(id));
   }
}
