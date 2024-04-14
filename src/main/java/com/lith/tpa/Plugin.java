package com.lith.tpa;

import com.lith.lithcore.abstractClasses.AbstractPlugin;
import com.lith.tpa.commands.TpaCommand;
import com.lith.tpa.commands.TpacceptCommand;
import com.lith.tpa.commands.TpdenyCommand;
import com.lith.tpa.config.ConfigManager;

public class Plugin extends AbstractPlugin<Plugin, ConfigManager> {
  @Override
  public void onEnable() {
    configs = new ConfigManager(this);
    super.onEnable();
  }

  @Override
  protected void registerCommands() {
    new TpaCommand(this);
    new TpacceptCommand(this);
    new TpdenyCommand(this);
  }
}
