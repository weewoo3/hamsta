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
import net.minecraft.client.render.RenderLayer;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.util.Identifier;
import net.minecraft.client.item.ModelPredicateProviderRegistry;
import software.bernie.geckolib.GeckoLib;

@Environment(EnvType.CLIENT)
public class HamstersClient implements ClientModInitializer {
   public void onInitializeClient() {
      HamstersVanillaIntegration.Client.clientInit();
      registerRenderers();
      GeckoLib.initialize();
      ModelPredicateProviderRegistry.method_27879(HamstersItems.HAMSTER, new Identifier("variant"), (stack, world, entity, num) -> {
         NbtCompound compoundTag = stack.method_7969();
         return compoundTag != null && compoundTag.method_10545("Variant") ? (float)compoundTag.method_10550("Variant") / 7.0F : 0.333F;
      });
   }

   public static void registerRenderers() {
      BlockEntityRendererRegistry.register(HamstersBlockEntities.HAMSTER_WHEEL, (context) -> {
         return new HamsterWheelRenderer();
      });
      BlockRenderLayerMapImpl.INSTANCE.putBlock(HamstersBlocks.HAMSTER_WHEEL, RenderLayer.method_23581());
   }
}
