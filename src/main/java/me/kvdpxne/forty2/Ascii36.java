package me.kvdpxne.forty2;

/**
 * Utility class for ASCII base-36 character operations.
 * <p>
 * This class provides constants and methods for handling
 * ASCII characters used in base-36 encoding. Base-36 encoding
 * uses digits 0-9 and letters a-z or A-Z.
 * </p>
 *
 * @since 0.2.0
 */
public final class Ascii36 {

  /**
   * Lowercase base-36 ASCII characters.
   * <p>
   * This array contains the characters '0'-'9' and 'a'-'z'
   * used for base-36 encoding with lowercase letters.
   * </p>
   */
  public static final char[] LOWER_ASCII_36 = {
    '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
    'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o',
    'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'
  };

  /**
   * Uppercase base-36 ASCII characters.
   * <p>
   * This array contains the characters '0'-'9' and 'A'-'Z'
   * used for base-36 encoding with uppercase letters.
   * </p>
   */
  public static final char[] UPPER_ASCII_36 = {
    '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
    'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O',
    'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'
  };

  /**
   * Checks if a character is an uppercase ASCII letter.
   * <p>
   * This method returns true if the provided character is between 'A' and 'Z'
   * (inclusive).
   * </p>
   *
   * @param character the character to check.
   * @return true if the character is an uppercase ASCII letter, false
   * otherwise.
   */
  public static boolean isUppercase(final char character) {
    return 0x41 <= character && 0x5b > character;
  }


  /**
   * Checks if a character is a lowercase ASCII letter.
   * <p>
   * This method returns true if the provided character is between 'a' and 'z'
   * (inclusive).
   * </p>
   *
   * @param character the character to check.
   * @return true if the character is a lowercase ASCII letter, false otherwise.
   */
  public static boolean isLowercase(final char character) {
    return 0x61 <= character && 0x7a >= character;
  }
}
