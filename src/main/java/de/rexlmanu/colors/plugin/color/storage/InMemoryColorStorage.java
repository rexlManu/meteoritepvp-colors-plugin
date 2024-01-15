package de.rexlmanu.colors.plugin.color.storage;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class InMemoryColorStorage implements ColorStorage {
  // We assume that the color names are unique, so we use a Set instead of a List.
  private final Set<String> colors = new HashSet<>();

  @Override
  public void store(String colorName) {
    this.colors.add(colorName);
  }

  @Override
  public Collection<String> retrieveList() {
    return this.colors;
  }

  @Override
  public void remove(String colorName) {
    this.colors.remove(colorName);
  }
}
