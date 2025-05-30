package com.starfish_studios.hamsters.client.renderer;

import com.google.common.collect.Maps;
import com.starfish_studios.hamsters.client.model.HamsterModel;
import com.starfish_studios.hamsters.entity.Hamster;
import java.util.Map;
import net.minecraft.class_156;
import net.minecraft.class_2960;
import net.minecraft.class_4587;
import net.minecraft.class_4597;
import net.minecraft.class_5617.class_5618;
import org.jetbrains.annotations.NotNull;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class HamsterRenderer extends GeoEntityRenderer<Hamster> {
   private static final Map<Hamster.Variant, class_2960> TEXTURES = (Map)class_156.method_654(Maps.newHashMap(), (hashMap) -> {
      hashMap.put(Hamster.Variant.WHITE, new class_2960("hamsters", "textures/entity/hamster/white.png"));
      hashMap.put(Hamster.Variant.PEACHES_AND_CREAM, new class_2960("hamsters", "textures/entity/hamster/peaches_and_cream.png"));
      hashMap.put(Hamster.Variant.ORANGE, new class_2960("hamsters", "textures/entity/hamster/orange.png"));
      hashMap.put(Hamster.Variant.GREY_WHITE, new class_2960("hamsters", "textures/entity/hamster/grey_white.png"));
      hashMap.put(Hamster.Variant.BROWN, new class_2960("hamsters", "textures/entity/hamster/brown.png"));
      hashMap.put(Hamster.Variant.BLACK_WHITE, new class_2960("hamsters", "textures/entity/hamster/black_white.png"));
      hashMap.put(Hamster.Variant.BLACK, new class_2960("hamsters", "textures/entity/hamster/black.png"));
   });

   public HamsterRenderer(class_5618 context) {
      super(context, new HamsterModel());
      this.field_4673 = 0.3F;
   }

   @NotNull
   public class_2960 getTextureLocation(Hamster entity) {
      return (class_2960)TEXTURES.get(entity.getVariant());
   }

   public void render(Hamster animatable, float entityYaw, float partialTick, class_4587 poseStack, class_4597 bufferSource, int packedLight) {
      if (animatable.method_6109()) {
         poseStack.method_22905(0.6F, 0.6F, 0.6F);
      } else {
         poseStack.method_22905(1.0F, 1.0F, 1.0F);
      }

      super.method_3936(animatable, entityYaw, partialTick, poseStack, bufferSource, packedLight);
   }
}
