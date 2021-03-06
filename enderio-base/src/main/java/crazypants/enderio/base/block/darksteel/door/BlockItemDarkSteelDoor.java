package crazypants.enderio.base.block.darksteel.door;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import crazypants.enderio.base.EnderIOTab;
import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemDoor;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockItemDarkSteelDoor extends ItemDoor {

  public BlockItemDarkSteelDoor(@Nonnull Block block) {
    super(block);
    setCreativeTab(EnderIOTab.tabEnderIO);
  }

  @SuppressWarnings("null")
  public BlockItemDarkSteelDoor(@Nonnull Block block, @Nullable CreativeTabs tab) {
    super(block);
    setCreativeTab(tab);
  }

  @Override
  @SideOnly(Side.CLIENT)
  public void getSubItems(@Nonnull Item itemIn, @Nullable CreativeTabs tab, @Nonnull NonNullList<ItemStack> subItems) {
    if (tab != null) {
      subItems.add(new ItemStack(itemIn));
    }
  }

}

// TODO 1.12 add getBurnTime() {return 0; }
