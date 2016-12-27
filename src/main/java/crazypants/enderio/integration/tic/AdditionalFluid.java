package crazypants.enderio.integration.tic;

import java.util.List;

import org.apache.commons.lang3.StringUtils;

import crazypants.enderio.Log;
import crazypants.enderio.material.PowderIngot;
import crazypants.util.Things;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.event.FMLInterModComms;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

import static crazypants.enderio.ModObject.itemPowderIngot;

public class AdditionalFluid {

  static void init(FMLPreInitializationEvent event) {
    if (!Loader.isModLoaded("ThermalFoundation")) {
      glowstone(event);
      redstone(event);
      ender(event);
    }
  }

  static void init(FMLPostInitializationEvent event) {
    glowstone(event);
    redstone(event);
    ender(event);
  }

  private static boolean registerVanillaRecipesForGlowstone = false;

  private static boolean registerVanillaRecipesForRedstone = false;

  private static boolean registerVanillaRecipesForEnder = false;

  private static Fluid glowstone(FMLPreInitializationEvent event) {
    Fluid f = new Fluid("glowstone", TicProxy.TEX_FLOWING, TicProxy.TEX_STILL) {
      @Override
      public int getColor() {
        return 0xFF000000 | 0xffbc5e;
      }
    };
    f.setDensity(-500);
    f.setGaseous(true);
    f.setLuminosity(15);
    f.setTemperature(1500 + 273);
    f.setViscosity(100);
    if (FluidRegistry.registerFluid(f)) {
      // HL: Disabled because I don't have a nice texture for it
      // MoltenGlowstone block = new crazypants.enderio.fluid.BlockFluidEio.MoltenGlowstone(f, Material.WATER, 0xffbc5e);
      // block.init();
      // f.setBlock(block);
      // if (!EnderIO.proxy.isDedicatedServer()) {
      // EnderIO.fluids.registerFluidBlockRendering(f, f.getName());
      // }
      if (FluidRegistry.isUniversalBucketEnabled()) {
        FluidRegistry.addBucketForFluid(f);
      }

      NBTTagCompound tag = new NBTTagCompound();
      tag.setString("fluid", f.getName());
      tag.setString("ore", StringUtils.capitalize("glowstone"));
      tag.setBoolean("toolforge", true);
      FMLInterModComms.sendMessage("tconstruct", "integrateSmeltery", tag);

      registerVanillaRecipesForGlowstone = true;
    }
    return f;
  }

  private static void glowstone(FMLPostInitializationEvent event) {
    Fluid f = FluidRegistry.getFluid("glowstone");
    if (f == null) {
      Log.warn("Thermal Foundation fluid 'glowstone' is unexpectedly missing. Late registering our own.");
      f = glowstone((FMLPreInitializationEvent) null);
    }
    if (registerVanillaRecipesForGlowstone) {
      // Note: We match the old TE amounts
      TicProxy.registerSmelterySmelting(new ItemStack(Items.GLOWSTONE_DUST), f, 250);
      TicProxy.registerSmelterySmelting(new ItemStack(Blocks.GLOWSTONE), f, 1000);
      TicProxy.registerBasinCasting(new ItemStack(Blocks.GLOWSTONE), null, f, 1000);
    }
  }

  private static Fluid redstone(FMLPreInitializationEvent event) {
    Fluid f = new Fluid("redstone", TicProxy.TEX_FLOWING, TicProxy.TEX_STILL) {
      @Override
      public int getColor() {
        return 0xFF000000 | 0xff0000;
      }
    };
    f.setDensity(1200);
    f.setLuminosity(7);
    f.setTemperature(1700 + 273);
    f.setViscosity(1500);
    if (FluidRegistry.registerFluid(f)) {
      // HL: Disabled because I don't have a nice texture for it
      // MoltenRedstone block = new crazypants.enderio.fluid.BlockFluidEio.MoltenRedstone(f, Material.WATER, 0xff0000);
      // block.init();
      // f.setBlock(block);
      // if (!EnderIO.proxy.isDedicatedServer()) {
      // EnderIO.fluids.registerFluidBlockRendering(f, f.getName());
      // }
      if (FluidRegistry.isUniversalBucketEnabled()) {
        FluidRegistry.addBucketForFluid(f);
      }

      NBTTagCompound tag = new NBTTagCompound();
      tag.setString("fluid", f.getName());
      tag.setString("ore", StringUtils.capitalize("redstone"));
      tag.setBoolean("toolforge", true);
      FMLInterModComms.sendMessage("tconstruct", "integrateSmeltery", tag);

      registerVanillaRecipesForRedstone = true;
    }
    return f;
  }

  private static void redstone(FMLPostInitializationEvent event) {
    Fluid f = FluidRegistry.getFluid("redstone");
    if (f == null) {
      Log.warn("Thermal Foundation fluid 'redstone' is unexpectedly missing. Late registering our own.");
      f = redstone((FMLPreInitializationEvent) null);
    }
    if (registerVanillaRecipesForRedstone) {
      // Note: We match the old TE amounts
      TicProxy.registerSmelterySmelting(new ItemStack(Items.REDSTONE), f, 100);
      TicProxy.registerSmelterySmelting(new ItemStack(Blocks.REDSTONE_BLOCK), f, 900);
      TicProxy.registerBasinCasting(new ItemStack(Blocks.REDSTONE_BLOCK), null, f, 900);
    }
  }

  private static Fluid ender(FMLPreInitializationEvent event) {
    Fluid f = new Fluid("ender", TicProxy.TEX_FLOWING, TicProxy.TEX_STILL) {
      @Override
      public int getColor() {
        return 0xFF000000 | 0x1b7b6b;
      }
    };
    f.setDensity(4000);
    f.setLuminosity(3);
    f.setTemperature(1000 + 273);
    f.setViscosity(3500);
    if (FluidRegistry.registerFluid(f)) {
      // HL: Disabled because I don't have a nice texture for it
      // MoltenEnder block = new crazypants.enderio.fluid.BlockFluidEio.MoltenEnder(f, Material.WATER, 0x1b7b6b);
      // block.init();
      // f.setBlock(block);
      // if (!EnderIO.proxy.isDedicatedServer()) {
      // EnderIO.fluids.registerFluidBlockRendering(f, f.getName());
      // }
      if (FluidRegistry.isUniversalBucketEnabled()) {
        FluidRegistry.addBucketForFluid(f);
      }

      NBTTagCompound tag = new NBTTagCompound();
      tag.setString("fluid", f.getName());
      tag.setString("ore", StringUtils.capitalize("ender"));
      tag.setBoolean("toolforge", true);
      FMLInterModComms.sendMessage("tconstruct", "integrateSmeltery", tag);

      registerVanillaRecipesForEnder = true;
    }
    return f;
  }

  private static void ender(FMLPostInitializationEvent event) {
    Fluid f = FluidRegistry.getFluid("ender");
    if (f == null) {
      Log.warn("Thermal Foundation fluid 'ender' is unexpectedly missing. Late registering our own.");
      f = ender((FMLPreInitializationEvent) null);
    }
    if (registerVanillaRecipesForEnder) {
      // Note: We match the old TE amounts
      TicProxy.registerSmelterySmelting(new ItemStack(Items.ENDER_PEARL), f, 250);
      // Need to do this later because of the cast
      Things cast = new Things("tconstruct:cast_custom:2");
      List<ItemStack> casts = cast.getItemStacks();
      if (!casts.isEmpty()) {
        TicProxy.registerTableCast(new ItemStack(Items.ENDER_PEARL), casts.get(0), f, 250);
      }
    }
    TicProxy.registerSmelterySmelting(new ItemStack(itemPowderIngot.getItem(), 1, PowderIngot.POWDER_ENDER.ordinal()), f, 250 / 9);
  }

}