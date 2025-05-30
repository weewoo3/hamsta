package com.starfish_studios.hamsters.item;

import java.util.function.Consumer;
import java.util.function.Supplier;
import net.minecraft.class_1747;
import net.minecraft.class_2248;
import net.minecraft.class_2960;
import net.minecraft.class_756;
import net.minecraft.class_1792.class_1793;
import software.bernie.geckolib.animatable.GeoItem;
import software.bernie.geckolib.animatable.client.RenderProvider;
import software.bernie.geckolib.core.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.core.animation.AnimatableManager.ControllerRegistrar;
import software.bernie.geckolib.model.DefaultedBlockGeoModel;
import software.bernie.geckolib.renderer.GeoItemRenderer;
import software.bernie.geckolib.util.GeckoLibUtil;

public class HamsterWheelItem extends class_1747 implements GeoItem {
   private final AnimatableInstanceCache geoCache = GeckoLibUtil.createInstanceCache(this);
   private final Supplier<Object> renderProvider = GeoItem.makeRenderer(this);

   public HamsterWheelItem(class_2248 block, class_1793 properties) {
      super(block, properties);
   }

   public void createRenderer(Consumer<Object> consumer) {
      consumer.accept(new RenderProvider() {
         private GeoItemRenderer<HamsterWheelItem> renderer = null;

         public class_756 getCustomRenderer() {
            if (this.renderer == null) {
               this.renderer = new GeoItemRenderer(new DefaultedBlockGeoModel(new class_2960("hamsters", "hamster_wheel")));
            }

            return this.renderer;
         }
      });
   }

   public Supplier<Object> getRenderProvider() {
      return this.renderProvider;
   }

   public void registerControllers(ControllerRegistrar controllers) {
   }

   public AnimatableInstanceCache getAnimatableInstanceCache() {
      return this.geoCache;
   }
}
