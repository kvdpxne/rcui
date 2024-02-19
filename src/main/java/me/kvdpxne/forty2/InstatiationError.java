package me.kvdpxne.forty2;

public final class InstatiationError extends Error {

  InstatiationError(final Class<?> clazz) {
    super(String.format(
      "Instantiation of %s class is not allowed.",
      clazz.getName()
    ));
  }
}
