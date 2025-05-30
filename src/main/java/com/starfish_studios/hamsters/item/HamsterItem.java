package com.starfish_studios.hamsters.item;

import com.starfish_studios.hamsters.entity.Hamster;
import com.starfish_studios.hamsters.registry.HamstersEntityType;
import java.util.List;
import java.util.Objects;
import net.minecraft.class_124;
import net.minecraft.util.ActionResult;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.class_1792;
import net.minecraft.item.ItemStack;
import net.minecraft.class_1836;
import net.minecraft.class_1838;
import net.minecraft.world.World;
import net.minecraft.util.math.BlockPos;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.text.Text;
import net.minecraft.sound.SoundEvents;
import net.minecraft.class_1792.class_1793;
import org.jetbrains.annotations.Nullable;

public class HamsterItem extends class_1792 {
   public HamsterItem(class_1793 properties) {
      super(properties);
   }

   public ActionResult method_7884(class_1838 useOnContext) {
      ItemPlacementContext blockPlaceContext = new ItemPlacementContext(useOnContext);
      BlockPos pos = blockPlaceContext.method_8037();
      ItemStack stack = useOnContext.method_8041();
      Hamster hamster = (Hamster)HamstersEntityType.HAMSTER.method_5883(useOnContext.method_8045());
      if (stack.method_7938()) {
         hamster.method_5665(stack.method_7964());
      }

      if (stack.method_7985()) {
         hamster.method_5651(stack.method_7969());
      }

      hamster.method_5808((double)pos.method_10263() + 0.5D, (double)pos.method_10264(), (double)pos.method_10260() + 0.5D, ((PlayerEntity)Objects.requireNonNull(useOnContext.method_8036())).method_36454(), 0.0F);
      hamster.method_43077(SoundEvents.field_15219);
      useOnContext.method_8045().method_8649(hamster);
      useOnContext.method_8036().method_6122(useOnContext.method_20287(), ItemStack.field_8037);
      return ActionResult.field_5812;
   }

   public void method_7851(ItemStack itemStack, @Nullable World level, List<Text> list, class_1836 tooltipFlag) {
      NbtCompound compoundTag;
      if ((compoundTag = itemStack.method_7969()) != null && compoundTag.method_10573("Variant", 3)) {
         int i = compoundTag.method_10550("Variant");
         list.add(Text.method_43471("tooltip.hamsters." + Hamster.Variant.getTypeById(i).getName()).method_27695(new class_124[]{class_124.field_1056, class_124.field_1080}));
      }

      if ((compoundTag = itemStack.method_7969()) != null && compoundTag.method_10550("Age") < 0) {
         list.add(Text.method_43471("tooltip.hamsters.baby").method_27695(new class_124[]{class_124.field_1056, class_124.field_1078}));
      }

      if ((compoundTag = itemStack.method_7969()) != null && compoundTag.method_25928("Owner")) {
         list.add(Text.method_43471("tooltip.hamsters.tamed").method_27695(new class_124[]{class_124.field_1056, class_124.field_1078}));
      }

   }
}
