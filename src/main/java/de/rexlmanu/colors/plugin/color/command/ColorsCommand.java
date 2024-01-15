package de.rexlmanu.colors.plugin.color.command;

import com.meteoritepvp.api.MeteoritePlugin;
import com.meteoritepvp.api.command.Command;
import com.meteoritepvp.api.command.CommandClass;
import com.meteoritepvp.api.utils.Message;
import de.rexlmanu.colors.plugin.color.ColorService;
import de.rexlmanu.colors.plugin.color.inventory.ColorInventoryFactory;
import java.util.Collection;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ColorsCommand implements CommandClass {

  private final ColorService colorService;

  public ColorsCommand(ColorService colorService) {
    this.colorService = colorService;
  }

  @Command(
      name = "colors",
      args = {"add"},
      description = "Add a color to the list.",
      aliases = {"c"},
      params = {"text"})
  public void addColorCommand(CommandSender sender, MeteoritePlugin plugin, String[] parameters) {
    String colorName = parameters[0];

    if (!this.colorService.isValidColor(colorName)) {
      sender.sendMessage(Message.fromMainConfig(plugin, "message.invalid-color"));
      return;
    }

    if (this.colorService.isColorStored(colorName)) {
      sender.sendMessage(Message.fromMainConfig(plugin, "message.color-already-stored"));
      return;
    }

    this.colorService.addColor(colorName);
    sender.sendMessage(
        Message.fromMainConfig(plugin, "message.color-added").replace("%color%", colorName));
  }

  @Command(
      name = "colors",
      args = {"remove"},
      description = "Remove a color to the list.",
      aliases = {"c"},
      params = {"@colors"})
  public void removeColorCommand(
      CommandSender sender, MeteoritePlugin plugin, String[] parameters) {
    String colorName = parameters[0];

    if (!this.colorService.isValidColor(colorName)) {
      sender.sendMessage(Message.fromMainConfig(plugin, "message.invalid-color"));
      return;
    }

    if (!this.colorService.isColorStored(colorName)) {
      sender.sendMessage(Message.fromMainConfig(plugin, "message.color-not-stored"));
      return;
    }

    this.colorService.removeColor(colorName);
    sender.sendMessage(
        Message.fromMainConfig(plugin, "message.color-removed").replace("%color%", colorName));
  }

  @Command(
      name = "colors",
      description = "Open a inventory with all stored colors.",
      aliases = {"c"})
  public void openColorInventoryCommand(
      CommandSender sender, Player player, MeteoritePlugin plugin, String[] parameters) {
    if (player == null) {
      sender.sendMessage(Message.fromMainConfig(plugin, "message.only-players"));
      return;
    }
    Collection<String> colors = this.colorService.getColors();
    if (colors.isEmpty()) {
      player.sendMessage(Message.fromMainConfig(plugin, "message.no-colors-stored"));
      return;
    }

    ColorInventoryFactory.create(plugin, colors).show(player);
  }
}
