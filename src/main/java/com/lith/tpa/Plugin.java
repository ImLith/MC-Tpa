package com.lith.tpa;

import com.lith.lithcore.abstractClasses.MainPlugin;
import com.lith.tpa.commands.TpaCommand;
import com.lith.tpa.commands.TpacceptCommand;
import com.lith.tpa.config.ConfigManager;

public class Plugin extends MainPlugin<ConfigManager> {
  public static Plugin plugin;

  public void onEnable() {
    Plugin.plugin = this;

    new ConfigManager(this);
    new TpaCommand();
    new TpacceptCommand();

    Static.log.info("Plugin enabled");
  }

  public void onDisable() {
    Static.log.info("Plugin disabled");
  }
}
