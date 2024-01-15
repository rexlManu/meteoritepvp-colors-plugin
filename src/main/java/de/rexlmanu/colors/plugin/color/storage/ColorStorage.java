package de.rexlmanu.colors.plugin.color.storage;

import java.util.Collection;

/** Represents a storage for color names. */
public interface ColorStorage {
  /**
   * Stores a color name.
   *
   * @param colorName the color name to store.
   */
  void store(String colorName);

  /**
   * Retrieves a list of all stored color names.
   *
   * @return a list of all stored color names.
   */
  Collection<String> retrieveList();

  /**
   * Removes a color name.
   *
   * @param colorName the color name to remove.
   */
  void remove(String colorName);
}
