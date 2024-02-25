package me.kvdpxne.forty2;

/**
 * Error thrown when instantiation of a specific class is not allowed.
 */
public final class InstatiationError
  extends Error {

  /**
   * Constructs an instance of {@code InstatiationError} with a message
   * indicating that instantiation of the specified class is not allowed.
   *
   * @param clazz The Class object representing the class that cannot be
   *              instantiated.
   */
  InstatiationError(final Class<?> clazz) {
    super(String.format(
      "Instantiation of %s class is not allowed.",
      clazz.getName()
    ));
  }
}
