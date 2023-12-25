package com.lith.tpa;

import java.util.logging.Logger;
import org.bukkit.plugin.java.JavaPlugin;

public class Plugin extends JavaPlugin {
  private static final Logger LOGGER = Logger.getLogger("tpa");

  public void onEnable() {
    LOGGER.info("tpa enabled");
  }

  public void onDisable() {
    LOGGER.info("tpa disabled");
  }
}
