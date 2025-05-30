package com.starfish_studios.hamsters;

import com.starfish_studios.hamsters.client.renderer.HamsterRenderer;
import com.starfish_studios.hamsters.client.renderer.SeatRenderer;
import com.starfish_studios.hamsters.registry.HamstersEntityType;
import java.util.function.Supplier;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.minecraft.class_1297;
import net.minecraft.class_1299;
import net.minecraft.class_5617;

public class HamstersVanillaIntegration {
   public static void serverInit() {
   }

   public static <T extends class_1297> void registerEntityRenderers(Supplier<class_1299<T>> type, class_5617<T> renderProvider) {
      EntityRendererRegistry.register((class_1299)type.get(), renderProvider);
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
