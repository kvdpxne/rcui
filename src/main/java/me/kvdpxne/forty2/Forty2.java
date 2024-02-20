package me.kvdpxne.forty2;

import java.security.SecureRandom;
import java.util.Random;

public final class Forty2 {

  /**
   * Default number generator used for generating random identifiers.
   */
  public static final Random DEFAULT_NUMBER_GENERATOR = new SecureRandom();

  /**
   * Default set of alphanumeric characters used for generating random
   * identifiers.
   */
  public static final char[] DEFAULT_ALPHANUMERIC_CHARACTERS = {
    0x30, 0x31, 0x32, 0x33, 0x34, 0x35, 0x36, 0x37, 0x38, 0x39, 0x41, 0x42,
    0x43, 0x44, 0x45, 0x46, 0x47, 0x48, 0x49, 0x4a, 0x4b, 0x4c, 0x4d, 0x4e,
    0x4f, 0x50, 0x51, 0x52, 0x53, 0x54, 0x55, 0x56, 0x57, 0x58, 0x59, 0x5a,
    0x61, 0x62, 0x63, 0x64, 0x65, 0x66, 0x67, 0x68, 0x69, 0x6a, 0x6b, 0x6c,
    0x6d, 0x6e, 0x6f, 0x70, 0x71, 0x72, 0x73, 0x74, 0x75, 0x76, 0x77, 0x78,
    0x79, 0x7a
  };

  /**
   * Default set of characters used for generating random identifiers,
   * including special characters.
   */
  public static final char[] DEFAULT_CHARACTERS = {
    0x21, 0x22, 0x23, 0x24, 0x25, 0x26, 0x27, 0x28, 0x29, 0x2a, 0x2b, 0x2c,
    0x2d, 0x2e, 0x2f, 0x30, 0x31, 0x32, 0x33, 0x34, 0x35, 0x36, 0x37, 0x38,
    0x39, 0x3a, 0x3b, 0x3c, 0x3d, 0x3e, 0x3f, 0x40, 0x41, 0x42, 0x43, 0x44,
    0x45, 0x46, 0x47, 0x48, 0x49, 0x4a, 0x4b, 0x4c, 0x4d, 0x4e, 0x4f, 0x50,
    0x51, 0x52, 0x53, 0x54, 0x55, 0x56, 0x57, 0x58, 0x59, 0x5a, 0x5b, 0x5c,
    0x5d, 0x5e, 0x5f, 0x60, 0x61, 0x62, 0x63, 0x64, 0x65, 0x66, 0x67, 0x68,
    0x69, 0x6a, 0x6b, 0x6c, 0x6d, 0x6e, 0x6f, 0x70, 0x71, 0x72, 0x73, 0x74,
    0x75, 0x76, 0x77, 0x78, 0x79, 0x7a, 0x7b, 0x7c, 0x7d, 0x7e,
  };

  /**
   * Default size of the generated random identifiers.
   */
  public static final int DEFAULT_SIZE = 14;

  // Private constructor to prevent instantiation.
  private Forty2() {
    throw new InstatiationError(this.getClass());
  }

  /**
   * Generates a random string of specified size using the provided characters
   * and a random number generator.
   * <p>
   * Note: This method is considered unsafe as it does not perform any
   * validation checks, such as checking for null or empty values.
   * It assumes that the provided parameters are valid and does not handle
   * potential edge cases.
   * </p>
   *
   * @param random     The Random object used to generate random bytes.
   * @param characters The characters to be used for generating the random
   *                   string.
   * @param size       The size of the random string to be generated.
   * @return A random string of the specified size.
   */
  public static String _create(
    final Random random,
    final char[] characters,
    final int size
  ) {
    // Assign the length of the character array.
    final int length = characters.length;

    // Calculate the mask to ensure generated random values are within the
    // bounds of the character array.
    final int mask = (2 << 31 - Integer.numberOfLeadingZeros(length - 1)) - 1;

    // Calculate the step size for generating random bytes based on the
    // specified size and length of a character array.
    final int step = 16384 - (int) (16384f - 1.6f * mask * size / length);

    // Initialize the array to store the random string and the array to
    // store random bytes.
    final char[] result = new char[size];
    final byte[] bytes = new byte[step];

    // Index variables for iteration.
    int i;
    int j;
    int k;

    // Infinite loop to generate random string.
    while (true) {
      // Generate random bytes.
      random.nextBytes(bytes);

      // Initialize index variables.
      i = 0;
      j = 0;

      // Iterate through the generated random bytes.
      while (step > i) {
        // Mask the random byte to ensure it's within the bounds of the
        // character array.
        k = bytes[i] & mask;

        // Check if the index is within the length of the character array.
        if (length > k) {
          result[j] = characters[k];
          ++j;

          // Check if the desired size of the random string has been reached.
          if (size == j) {
            return new String(result, 0, size);
          }
        }
        ++i;
      }
    }
  }

  /**
   * Checks if the provided Random object is not null.
   *
   * @param random The Random object to be checked.
   * @throws NullPointerException If the Random object is null.
   */
  public static void _checkRandom(
    final Random random
  ) {
    if (null == random) {
      throw new NullPointerException(
        "Random object instance cannot be null!"
      );
    }
  }

  /**
   * Checks if the provided character array is not null, not empty, and does
   * not contain duplicates.
   *
   * @param characters The character array to be checked.
   * @throws NullPointerException     If the character array is null.
   * @throws IllegalArgumentException If the character array is empty or
   *                                  contains duplicates.
   */
  public static void _checkCharacters(
    final char[] characters
  ) {
    if (null == characters) {
      throw new NullPointerException(
        "Character array cannot be null!"
      );
    }

    if (0 == characters.length) {
      throw new IllegalArgumentException(
        "Character array cannot be empty!"
      );
    }

    final boolean[] duplications = new boolean[256];
    for (final char character : characters) {
      if (duplications[character]) {
        throw new IllegalArgumentException(
          "Character array cannot have duplicates!"
        );
      }
      duplications[character] = true;
    }
  }

  /**
   * Checks if the provided size is greater than or equal to 9.
   *
   * @param size The size to be checked.
   * @throws IllegalArgumentException If the size is less than 9.
   */
  public static void _checkSize(
    final int size
  ) {
    if (5 > size) {
      throw new IllegalArgumentException(
        "The length of the generated string cannot be less than 9."
      );
    }
  }

  /**
   * Generates a random string of specified size using the provided characters
   * and a random number generator.
   *
   * @param random     The Random object used to generate random bytes.
   * @param characters The characters to be used for generating the random
   *                   string.
   * @param size       The size of the random string to be generated.
   * @return A random string of the specified size.
   * @throws NullPointerException     If the Random object or the character
   *                                  array is null.
   * @throws IllegalArgumentException If the character array is empty or
   *                                  contains duplicates, or if the size is
   *                                  less than 9.
   */
  public static String create(
    final Random random,
    final char[] characters,
    final int size
  ) {
    _checkRandom(random);
    _checkCharacters(characters);
    _checkSize(size);

    return _create(random, characters, size);
  }

  /**
   * Generates a random string of specified size using the provided characters
   * and a default random number generator.
   *
   * @param characters The characters to be used for generating the random
   *                   string.
   * @param size       The size of the random string to be generated.
   * @return A random string of the specified size.
   * @throws NullPointerException     If the character array is null.
   * @throws IllegalArgumentException If the character array is empty or
   *                                  contains duplicates, or if the size is
   *                                  less than 9.
   */
  public static String create(
    final char[] characters,
    final int size
  ) {
    _checkCharacters(characters);
    _checkSize(size);

    return _create(
      DEFAULT_NUMBER_GENERATOR,
      characters,
      size
    );
  }

  public static String alphanumeric(
    final int size
  ) {
    _checkSize(size);

    return _create(
      DEFAULT_NUMBER_GENERATOR,
      DEFAULT_ALPHANUMERIC_CHARACTERS,
      size
    );
  }

  /**
   * Generates a random string of specified size using default characters and a
   * default random number generator.
   *
   * @param size The size of the random string to be generated.
   * @return A random string of the specified size.
   * @throws IllegalArgumentException If the size is less than 9.
   */
  public static String all(
    final int size
  ) {
    _checkSize(size);

    return _create(
      DEFAULT_NUMBER_GENERATOR,
      DEFAULT_CHARACTERS,
      size
    );
  }

  /**
   * Generates a random string of default size using default characters and a
   * default random number generator.
   *
   * @return A random string of default size.
   */
  public static String alphanumeric() {
    return _create(
      DEFAULT_NUMBER_GENERATOR,
      DEFAULT_ALPHANUMERIC_CHARACTERS,
      DEFAULT_SIZE
    );
  }

  public static String all() {
    return _create(
      DEFAULT_NUMBER_GENERATOR,
      DEFAULT_CHARACTERS,
      DEFAULT_SIZE
    );
  }
}
