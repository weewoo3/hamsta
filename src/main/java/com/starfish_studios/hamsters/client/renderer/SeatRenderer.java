package com.starfish_studios.hamsters.client.renderer;

import com.starfish_studios.hamsters.entity.SeatEntity;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRenderer;
import net.minecraft.client.render.entity.EntityRendererFactory.Context;

@Environment(EnvType.CLIENT)
public class SeatRenderer extends EntityRenderer<SeatEntity> {
   public SeatRenderer(Context context) {
      super(context);
   }

   public Identifier getTextureLocation(SeatEntity seatEntity) {
      return null;
   }

   protected void renderNameTag(SeatEntity entity, Text component, MatrixStack stack, VertexConsumerProvider source, int light) {
   }
}
