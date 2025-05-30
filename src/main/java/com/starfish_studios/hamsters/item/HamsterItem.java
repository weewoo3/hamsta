package com.starfish_studios.hamsters.item;

import com.starfish_studios.hamsters.entity.Hamster;
import com.starfish_studios.hamsters.registry.HamstersEntityType;
import java.util.List;
import java.util.Objects;
import net.minecraft.class_124;
import net.minecraft.util.ActionResult;
import net.minecraft.class_1657;
import net.minecraft.class_1750;
import net.minecraft.class_1792;
import net.minecraft.class_1799;
import net.minecraft.class_1836;
import net.minecraft.class_1838;
import net.minecraft.class_1937;
import net.minecraft.util.math.BlockPos;
import net.minecraft.class_2487;
import net.minecraft.class_2561;
import net.minecraft.class_3417;
import net.minecraft.class_1792.class_1793;
import org.jetbrains.annotations.Nullable;

public class HamsterItem extends class_1792 {
   public HamsterItem(class_1793 properties) {
      super(properties);
   }

   public ActionResult method_7884(class_1838 useOnContext) {
      class_1750 blockPlaceContext = new class_1750(useOnContext);
      BlockPos pos = blockPlaceContext.method_8037();
      class_1799 stack = useOnContext.method_8041();
      Hamster hamster = (Hamster)HamstersEntityType.HAMSTER.method_5883(useOnContext.method_8045());
      if (stack.method_7938()) {
         hamster.method_5665(stack.method_7964());
      }

      if (stack.method_7985()) {
         hamster.method_5651(stack.method_7969());
      }

      hamster.method_5808((double)pos.method_10263() + 0.5D, (double)pos.method_10264(), (double)pos.method_10260() + 0.5D, ((class_1657)Objects.requireNonNull(useOnContext.method_8036())).method_36454(), 0.0F);
      hamster.method_43077(class_3417.field_15219);
      useOnContext.method_8045().method_8649(hamster);
      useOnContext.method_8036().method_6122(useOnContext.method_20287(), class_1799.field_8037);
      return ActionResult.field_5812;
   }

   public void method_7851(class_1799 itemStack, @Nullable class_1937 level, List<class_2561> list, class_1836 tooltipFlag) {
      class_2487 compoundTag;
      if ((compoundTag = itemStack.method_7969()) != null && compoundTag.method_10573("Variant", 3)) {
         int i = compoundTag.method_10550("Variant");
         list.add(class_2561.method_43471("tooltip.hamsters." + Hamster.Variant.getTypeById(i).getName()).method_27695(new class_124[]{class_124.field_1056, class_124.field_1080}));
      }

      if ((compoundTag = itemStack.method_7969()) != null && compoundTag.method_10550("Age") < 0) {
         list.add(class_2561.method_43471("tooltip.hamsters.baby").method_27695(new class_124[]{class_124.field_1056, class_124.field_1078}));
      }

      if ((compoundTag = itemStack.method_7969()) != null && compoundTag.method_25928("Owner")) {
         list.add(class_2561.method_43471("tooltip.hamsters.tamed").method_27695(new class_124[]{class_124.field_1056, class_124.field_1078}));
      }

   }
}
