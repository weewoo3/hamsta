package com.starfish_studios.hamsters.client.model;

import com.starfish_studios.hamsters.entity.Hamster;
import net.minecraft.class_1921;
import net.minecraft.class_2960;
import software.bernie.geckolib.core.animatable.model.CoreGeoBone;
import software.bernie.geckolib.core.animation.AnimationState;
import software.bernie.geckolib.model.DefaultedEntityGeoModel;

public class HamsterModel extends DefaultedEntityGeoModel<Hamster> {
   public HamsterModel() {
      super(new class_2960("hamsters", "hamster"), true);
   }

   public class_2960 getTextureResource(Hamster animatable) {
      return new class_2960("hamsters", "textures/entity/hamster/orange.png");
   }

   public class_2960 getAnimationResource(Hamster animatable) {
      return new class_2960("hamsters", "animations/hamster.animation.json");
   }

   public class_1921 getRenderType(Hamster animatable, class_2960 texture) {
      return class_1921.method_23578(texture);
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
