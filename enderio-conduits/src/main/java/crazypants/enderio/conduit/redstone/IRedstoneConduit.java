package crazypants.enderio.conduit.redstone;

import java.util.Collection;
import java.util.Set;

import javax.annotation.Nonnull;

import com.enderio.core.common.util.DyeColor;

import crazypants.enderio.base.conduit.ConnectionMode;
import crazypants.enderio.base.conduit.IConduit;
import crazypants.enderio.base.conduit.redstone.signals.Signal;
import net.minecraft.util.EnumFacing;

// TODO Javadocs

public interface IRedstoneConduit extends IConduit {

  public static final String KEY_CONDUIT_ICON = "enderio:blocks/redstone_conduit";
  public static final String KEY_TRANSMISSION_ICON = "enderio:blocks/redstone_conduit_transmission";

  public static final String KEY_INS_CONDUIT_ICON = "enderio:blocks/redstone_insulated_conduit";
  public static final String KEY_INS_CORE_OFF_ICON = "enderio:blocks/redstone_insulated_conduit_core_off";
  public static final String KEY_INS_CORE_ON_ICON = "enderio:blocks/redstone_insulated_conduit_core_on";

  // External redstone interface

  int isProvidingStrongPower(@Nonnull EnumFacing toDirection);

  int isProvidingWeakPower(@Nonnull EnumFacing toDirection);

  Set<Signal> getNetworkInputs(@Nonnull EnumFacing side);

  Collection<Signal> getNetworkOutputs(@Nonnull EnumFacing side);

  DyeColor getSignalColor(@Nonnull EnumFacing dir);

  void updateNetwork();

  // Old insulated interface

  public static final String COLOR_CONTROLLER_ID = "ColorController";

  void onInputsChanged(@Nonnull EnumFacing side, int[] inputValues);

  void onInputChanged(@Nonnull EnumFacing side, int inputValue);

  void forceConnectionMode(@Nonnull EnumFacing dir, @Nonnull ConnectionMode mode);

  void setSignalColor(@Nonnull EnumFacing dir, @Nonnull DyeColor col);

  boolean isOutputStrong(@Nonnull EnumFacing dir);

  void setOutputStrength(@Nonnull EnumFacing dir, boolean isStrong);
}
