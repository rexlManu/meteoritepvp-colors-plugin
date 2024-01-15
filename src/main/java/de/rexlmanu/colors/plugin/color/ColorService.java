package de.rexlmanu.colors.plugin.color;

import de.rexlmanu.colors.plugin.color.storage.ColorStorage;
import java.util.Arrays;
import java.util.Collection;
import org.bukkit.ChatColor;

/** Represents a service for color names. */
public class ColorService {
  // we cache the values of ChatColor.values() to avoid copying the array every time
  private static final ChatColor[] CHAT_COLORS = ChatColor.values();
  private final ColorStorage colorStorage;

  public ColorService(ColorStorage colorStorage) {
    this.colorStorage = colorStorage;
  }

  /**
   * Checks if a color name is valid. A color name is valid if it is a valid ChatColor value.
   *
   * @param colorName the color name to check.
   * @return true if the color name is valid, otherwise false.
   */
  public boolean isValidColor(String colorName) {
    return Arrays.stream(CHAT_COLORS)
        .anyMatch(chatColor -> chatColor.name().equalsIgnoreCase(colorName));
  }

  /**
   * Checks if a color name is stored.
   *
   * @param colorName the color name to check.
   * @return true if the color name is stored, otherwise false.
   */
  public boolean isColorStored(String colorName) {
    return this.colorStorage.retrieveList().contains(colorName);
  }

  /**
   * Adds a color name to the storage.
   *
   * @param colorName the color name to add.
   */
  public void addColor(String colorName) {
    this.colorStorage.store(colorName);
  }

  /**
   * Removes a color name from the storage.
   *
   * @param colorName the color name to remove.
   */
  public void removeColor(String colorName) {
    this.colorStorage.remove(colorName);
  }

  /**
   * Returns a list of all stored color names.
   *
   * @return a list of all stored color names.
   */
  public Collection<String> getColors() {
    return this.colorStorage.retrieveList();
  }
}
