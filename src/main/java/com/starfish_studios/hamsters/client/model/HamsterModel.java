package com.starfish_studios.hamsters.client.model;

import com.starfish_studios.hamsters.entity.Hamster;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.util.Identifier;
import software.bernie.geckolib.core.animatable.model.CoreGeoBone;
import software.bernie.geckolib.core.animation.AnimationState;
import software.bernie.geckolib.model.DefaultedEntityGeoModel;

public class HamsterModel extends DefaultedEntityGeoModel<Hamster> {
   public HamsterModel() {
      super(new Identifier("hamsters", "hamster"), true);
   }

   public Identifier getTextureResource(Hamster animatable) {
      return new Identifier("hamsters", "textures/entity/hamster/orange.png");
   }

   public Identifier getAnimationResource(Hamster animatable) {
      return new Identifier("hamsters", "animations/hamster.animation.json");
   }

   public RenderLayer getRenderType(Hamster animatable, Identifier texture) {
      return RenderLayer.method_23578(texture);
   }

   public void setCustomAnimations(Hamster animatable, long instanceId, AnimationState<Hamster> animationState) {
      super.setCustomAnimations(animatable, instanceId, animationState);
      if (animationState != null) {
         CoreGeoBone head = this.getAnimationProcessor().getBone("head");
         CoreGeoBone sleep = this.getAnimationProcessor().getBone("sleep");
         CoreGeoBone cheeks = this.getAnimationProcessor().getBone("cheeks");
         cheeks.setHidden(animatable.method_6047().method_7960());
         if (animatable.method_6113()) {
            sleep.setHidden(false);
         } else {
            sleep.setHidden(true);
         }

         if (animatable.method_6109()) {
            head.setScaleX(1.4F);
            head.setScaleY(1.4F);
            head.setScaleZ(1.4F);
         } else {
            head.setPosY(0.0F);
            head.setScaleX(1.0F);
            head.setScaleY(1.0F);
            head.setScaleZ(1.0F);
         }

      }
   }
}
