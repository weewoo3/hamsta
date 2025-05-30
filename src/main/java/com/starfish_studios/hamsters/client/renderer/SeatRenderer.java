package com.starfish_studios.hamsters.client.renderer;

import com.starfish_studios.hamsters.entity.SeatEntity;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.class_2561;
import net.minecraft.class_2960;
import net.minecraft.class_4587;
import net.minecraft.class_4597;
import net.minecraft.class_897;
import net.minecraft.class_5617.class_5618;

@Environment(EnvType.CLIENT)
public class SeatRenderer extends class_897<SeatEntity> {
   public SeatRenderer(class_5618 context) {
      super(context);
   }

   public class_2960 getTextureLocation(SeatEntity seatEntity) {
      return null;
   }

   protected void renderNameTag(SeatEntity entity, class_2561 component, class_4587 stack, class_4597 source, int light) {
   }
}
