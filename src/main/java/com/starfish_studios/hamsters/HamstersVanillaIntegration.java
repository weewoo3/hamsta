package com.starfish_studios.hamsters;

import com.starfish_studios.hamsters.client.renderer.HamsterRenderer;
import com.starfish_studios.hamsters.client.renderer.SeatRenderer;
import com.starfish_studios.hamsters.registry.HamstersEntityType;
import java.util.function.Supplier;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.client.render.entity.EntityRendererFactory;

public class HamstersVanillaIntegration {
   public static void serverInit() {
   }

   public static <T extends Entity> void registerEntityRenderers(Supplier<EntityType<T>> type, EntityRendererFactory<T> renderProvider) {
      EntityRendererRegistry.register((EntityType)type.get(), renderProvider);
   }

   @Environment(EnvType.CLIENT)
   public static class Client {
      public static void clientInit() {
         registerModelLayers();
         registerBlockRenderLayers();
         registerRenderers();
      }

      private static void registerRenderers() {
         HamstersVanillaIntegration.registerEntityRenderers(HamstersEntityType.SEAT, SeatRenderer::new);
      }

      private static void registerModelLayers() {
         EntityRendererRegistry.register(HamstersEntityType.HAMSTER, HamsterRenderer::new);
      }

      private static void registerBlockRenderLayers() {
      }
   }
}
