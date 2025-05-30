package com.starfish_studios.hamsters.client.renderer;

import com.google.common.collect.Maps;
import com.starfish_studios.hamsters.client.model.HamsterModel;
import com.starfish_studios.hamsters.entity.Hamster;
import java.util.Map;
import net.minecraft.util.Util;
import net.minecraft.util.Identifier;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRendererFactory.Context;
import org.jetbrains.annotations.NotNull;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class HamsterRenderer extends GeoEntityRenderer<Hamster> {
   private static final Map<Hamster.Variant, Identifier> TEXTURES = (Map)Util.method_654(Maps.newHashMap(), (hashMap) -> {
      hashMap.put(Hamster.Variant.WHITE, new Identifier("hamsters", "textures/entity/hamster/white.png"));
      hashMap.put(Hamster.Variant.PEACHES_AND_CREAM, new Identifier("hamsters", "textures/entity/hamster/peaches_and_cream.png"));
      hashMap.put(Hamster.Variant.ORANGE, new Identifier("hamsters", "textures/entity/hamster/orange.png"));
      hashMap.put(Hamster.Variant.GREY_WHITE, new Identifier("hamsters", "textures/entity/hamster/grey_white.png"));
      hashMap.put(Hamster.Variant.BROWN, new Identifier("hamsters", "textures/entity/hamster/brown.png"));
      hashMap.put(Hamster.Variant.BLACK_WHITE, new Identifier("hamsters", "textures/entity/hamster/black_white.png"));
      hashMap.put(Hamster.Variant.BLACK, new Identifier("hamsters", "textures/entity/hamster/black.png"));
   });

   public HamsterRenderer(Context context) {
      super(context, new HamsterModel());
      this.field_4673 = 0.3F;
   }

   @NotNull
   public Identifier getTextureLocation(Hamster entity) {
      return (Identifier)TEXTURES.get(entity.getVariant());
   }

   public void render(Hamster animatable, float entityYaw, float partialTick, MatrixStack poseStack, VertexConsumerProvider bufferSource, int packedLight) {
      if (animatable.method_6109()) {
         poseStack.method_22905(0.6F, 0.6F, 0.6F);
      } else {
         poseStack.method_22905(1.0F, 1.0F, 1.0F);
      }

      super.method_3936(animatable, entityYaw, partialTick, poseStack, bufferSource, packedLight);
   }
}
