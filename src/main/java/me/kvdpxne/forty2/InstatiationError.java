package me.kvdpxne.forty2;

public final class InstatiationError extends Error {

  InstatiationError(final Class<?> clazz) {
    super(STR."Instantiation of \{clazz.getName()} class is not allowed.");
  }
}
