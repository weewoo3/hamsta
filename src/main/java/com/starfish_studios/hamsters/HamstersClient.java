package com.starfish_studios.hamsters;

import com.starfish_studios.hamsters.client.renderer.HamsterWheelRenderer;
import com.starfish_studios.hamsters.registry.HamstersBlockEntities;
import com.starfish_studios.hamsters.registry.HamstersBlocks;
import com.starfish_studios.hamsters.registry.HamstersItems;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.rendering.v1.BlockEntityRendererRegistry;
import net.fabricmc.fabric.impl.blockrenderlayer.BlockRenderLayerMapImpl;
import net.minecraft.class_1921;
import net.minecraft.class_2487;
import net.minecraft.class_2960;
import net.minecraft.class_5272;
import software.bernie.geckolib.GeckoLib;

@Environment(EnvType.CLIENT)
public class HamstersClient implements ClientModInitializer {
   public void onInitializeClient() {
      HamstersVanillaIntegration.Client.clientInit();
      registerRenderers();
      GeckoLib.initialize();
      class_5272.method_27879(HamstersItems.HAMSTER, new class_2960("variant"), (stack, world, entity, num) -> {
         class_2487 compoundTag = stack.method_7969();
         return compoundTag != null && compoundTag.method_10545("Variant") ? (float)compoundTag.method_10550("Variant") / 7.0F : 0.333F;
      });
   }

   public static void registerRenderers() {
      BlockEntityRendererRegistry.register(HamstersBlockEntities.HAMSTER_WHEEL, (context) -> {
         return new HamsterWheelRenderer();
      });
      BlockRenderLayerMapImpl.INSTANCE.putBlock(HamstersBlocks.HAMSTER_WHEEL, class_1921.method_23581());
   }
}
