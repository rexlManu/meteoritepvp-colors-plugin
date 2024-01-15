package de.rexlmanu.colors.plugin;

import com.meteoritepvp.api.MeteoritePlugin;
import de.rexlmanu.colors.plugin.color.ColorService;
import de.rexlmanu.colors.plugin.color.storage.InMemoryColorStorage;
import de.rexlmanu.colors.plugin.color.command.ColorsCommand;
import java.util.ArrayList;

public final class ColorsPlugin extends MeteoritePlugin {
  private ColorService colorService;

  @Override
  protected void onInit() {
    super.onInit();
    this.colorService = new ColorService(new InMemoryColorStorage());

    this.registerCommandObject(new ColorsCommand(this.colorService));
  }

  @Override
  public void onEnable() {
    super.onEnable();

    this.saveDefaultConfig();
    this.reloadConfig();

    this.registerPlaceholderParameter("colors", sender -> new ArrayList<>(this.colorService.getColors()));
  }
}
