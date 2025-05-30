package com.starfish_studios.hamsters.client.model;

import com.starfish_studios.hamsters.block.entity.HamsterWheelBlockEntity;
import net.minecraft.class_1921;
import net.minecraft.class_2960;
import software.bernie.geckolib.model.DefaultedBlockGeoModel;

public class HamsterWheelModel extends DefaultedBlockGeoModel<HamsterWheelBlockEntity> {
   public HamsterWheelModel() {
      super(new class_2960("hamsters", "hamster_wheel"));
   }

   public class_2960 getAnimationResource(HamsterWheelBlockEntity hamsterWheel) {
      return new class_2960("hamsters", "animations/hamster_wheel.animation.json");
   }

   public class_1921 getRenderType(HamsterWheelBlockEntity animatable, class_2960 texture) {
      return class_1921.method_23576(this.getTextureResource(animatable));
   }
}
