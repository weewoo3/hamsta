package com.starfish_studios.hamsters.client.model;

import com.starfish_studios.hamsters.block.entity.HamsterWheelBlockEntity;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.util.Identifier;
import software.bernie.geckolib.model.DefaultedBlockGeoModel;

public class HamsterWheelModel extends DefaultedBlockGeoModel<HamsterWheelBlockEntity> {
   public HamsterWheelModel() {
      super(new Identifier("hamsters", "hamster_wheel"));
   }

   public Identifier getAnimationResource(HamsterWheelBlockEntity hamsterWheel) {
      return new Identifier("hamsters", "animations/hamster_wheel.animation.json");
   }

   public RenderLayer getRenderType(HamsterWheelBlockEntity animatable, Identifier texture) {
      return RenderLayer.method_23576(this.getTextureResource(animatable));
   }
}
