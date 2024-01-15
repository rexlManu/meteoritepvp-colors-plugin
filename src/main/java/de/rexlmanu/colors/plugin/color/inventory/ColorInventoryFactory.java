package de.rexlmanu.colors.plugin.color.inventory;

import com.meteoritepvp.api.MeteoritePlugin;
import com.meteoritepvp.api.inventory.MeteoriteInventory;
import com.meteoritepvp.api.inventory.presets.BasicInventory;
import com.meteoritepvp.api.utils.Message;
import java.util.Collection;
import org.bukkit.ChatColor;
import org.bukkit.Material;

public class ColorInventoryFactory {
  public static MeteoriteInventory create(MeteoritePlugin plugin, Collection<String> colors) {
    MeteoriteInventory inventory =
        new MeteoriteInventory(
            plugin, Message.fromMainConfig(plugin, "message.inventory-title"), 9, 3, true);

    BasicInventory page = new BasicInventory(9, 3);
    int slot = 0;
    for (String colorName : colors) {
      page.setItem(slot, Material.WOOL, ChatColor.valueOf(colorName) + colorName);
      slot++;
    }
    page.update();
    inventory.applyPage(page);

    return inventory;
  }
}
