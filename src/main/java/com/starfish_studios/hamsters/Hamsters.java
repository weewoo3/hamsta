package com.starfish_studios.hamsters;

import com.google.common.reflect.Reflection;
import com.starfish_studios.hamsters.registry.HamstersBlockEntities;
import com.starfish_studios.hamsters.registry.HamstersBlocks;
import com.starfish_studios.hamsters.registry.HamstersCreativeModeTab;
import com.starfish_studios.hamsters.registry.HamstersEntityType;
import com.starfish_studios.hamsters.registry.HamstersItems;
import com.starfish_studios.hamsters.registry.HamstersSoundEvents;
import net.fabricmc.api.ModInitializer;

public class Hamsters implements ModInitializer {
   public static final String MOD_ID = "hamsters";

   public void onInitialize() {
      Reflection.initialize(new Class[]{HamstersCreativeModeTab.class, HamstersSoundEvents.class, HamstersItems.class, HamstersBlocks.class, HamstersEntityType.class, HamstersBlockEntities.class});
      HamstersVanillaIntegration.serverInit();
   }
}
