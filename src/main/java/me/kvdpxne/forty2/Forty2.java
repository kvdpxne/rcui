package me.kvdpxne.forty2;

import java.security.SecureRandom;
import java.util.Random;

public class Forty2 {

  /**
   * Default number generator used for generating random identifiers.
   */
  public static final Random DEFAULT_NUMBER_GENERATOR;

  /**
   * Default set of alphanumeric characters used for generating random
   * identifiers.
   */
  public static final char[] DEFAULT_ALPHANUMERIC_CHARACTERS;

  /**
   * Default set of characters used for generating random identifiers,
   * including special characters.
   */
  public static final char[] DEFAULT_CHARACTERS;

  /**
   * Default size of the generated random identifiers.
   */
  public static final int DEFAULT_SIZE = 27;

  static {
    // Initialize the default number generator as a secure random instance.
    DEFAULT_NUMBER_GENERATOR = new SecureRandom();

    // Define the default set of alphanumeric characters (0-9, A-Z, a-z).
    DEFAULT_ALPHANUMERIC_CHARACTERS = new char[]{
      // 0-9
      0x30, 0x31, 0x32, 0x33, 0x34, 0x35, 0x36, 0x37, 0x38, 0x39,
      // A-Z
      0x41, 0x42, 0x43, 0x44, 0x45, 0x46, 0x47, 0x48, 0x49, 0x4a, 0x4b, 0x4c,
      0x4d, 0x4e, 0x4f, 0x50, 0x51, 0x52, 0x53, 0x54, 0x55, 0x56, 0x57, 0x58,
      0x59, 0x5a,
      // a-z
      0x61, 0x62, 0x63, 0x64, 0x65, 0x66, 0x67, 0x68, 0x69, 0x6a, 0x6b, 0x6c,
      0x6d, 0x6e, 0x6f, 0x70, 0x71, 0x72, 0x73, 0x74, 0x75, 0x76, 0x77, 0x78,
      0x79, 0x7a
    };

    // Define the default set of characters including alphanumeric and special
    // characters.
    DEFAULT_CHARACTERS = new char[]{
      // 0-9
      0x30, 0x31, 0x32, 0x33, 0x34, 0x35, 0x36, 0x37, 0x38, 0x39,
      // A-Z
      0x41, 0x42, 0x43, 0x44, 0x45, 0x46, 0x47, 0x48, 0x49, 0x4a, 0x4b, 0x4c,
      0x4d, 0x4e, 0x4f, 0x50, 0x51, 0x52, 0x53, 0x54, 0x55, 0x56, 0x57, 0x58,
      0x59, 0x5a,
      // a-z
      0x61, 0x62, 0x63, 0x64, 0x65, 0x66, 0x67, 0x68, 0x69, 0x6a, 0x6b, 0x6c,
      0x6d, 0x6e, 0x6f, 0x70, 0x71, 0x72, 0x73, 0x74, 0x75, 0x76, 0x77, 0x78,
      0x79, 0x7a,
      // Special characters !$*()-_+,.'
      0x21, 0x24, 0x27, 0x28, 0x29, 0x2a, 0x2b, 0x2c, 0x2d, 0x2e, 0x5f
    };
  }

  private Forty2() {
    // Do nothing.
  }

  public static String create(final Random random, final char[] chars, final int size) {
    // Length of the character array.
    final int length;

    // Mask for filtering random values.
    final int mask;

    // Step size for generating random bytes.
    final int step;

    // Array to store the generated random string.
    final char[] result;

    // Array to store random bytes for generating characters.
    final byte[] bytes;

    // Index variables for iteration.
    int i;
    int j;
    int k;

    // Assign the length of the character array.
    length = chars.length;

    // Calculate the mask to ensure generated random values are within the
    // bounds of the character array.
    mask = (2 << 31 - Integer.numberOfLeadingZeros(length - 1)) - 1;

    // Calculate the step size for generating random bytes based on the
    // specified size and length of a character array.
    step = (int) Math.ceil(1.6 * mask * size / length);

    // Initialize the array to store the random string and the array to
    // store random bytes.
    result = new char[size];
    bytes = new byte[step];

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
          result[j] = chars[k];
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

  public static String create(final char[] chars, final int size) {
    return create(DEFAULT_NUMBER_GENERATOR, chars, size);
  }

  public static String create(final int size) {
    return create(DEFAULT_CHARACTERS, size);
  }

  public static String create() {
    return create(DEFAULT_SIZE);
  }
}
