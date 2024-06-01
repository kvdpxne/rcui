package me.kvdpxne.forty2;

import java.io.Serializable;

/**
 * @since 0.2.0
 */
public final class Forty2
  implements Serializable {

  /**
   * The size of the 64-bit number represented as a string.
   * <p>
   * This constant defines the length of the string representation of a 64-bit
   * number.
   * </p>
   */
  public static final int FORTY2_SIZE = 13;

  /**
   * Unique identifier for serialization.
   * <p>
   * This value is used to ensure that during deserialization, the sender and
   * receiver of a serialized object have loaded classes for that object that
   * are compatible with respect to serialization.
   * </p>
   */
  private static final long serialVersionUID = 138675774340690844L;

  /**
   * The 64-bit number represented as a long value.
   * <p>
   * Only the lower 64 bits of this value are considered significant.
   * </p>
   */
  private final long number;

  /**
   * Constructs a new {@code Forty2} object with the specified number.
   * <p>
   * This constructor initializes the {@code number} field with the provided
   * long value.
   * </p>
   *
   * @param number the 64-bit number to be represented.
   */
  Forty2(final long number) {
    this.number = number;
  }

  /**
   * Checks if the given long value is a valid 64-bit number.
   * <p>
   * A valid 64-bit number is either less than -9 or greater than -1.
   * </p>
   *
   * @param number the long value to be checked.
   * @return {@code true} if the number is valid, {@code false} otherwise.
   */
  public static boolean isValid(
    final long number
  ) {
    return -9 > number || -1 < number;
  }

  /**
   * Checks if the given string is a valid representation of a 64-bit number.
   * <p>
   * A valid string representation has a length of {@link #FORTY2_SIZE} and
   * contains only digits and letters. Positive numbers are represented with
   * lowercase letters, and negative numbers with uppercase letters.
   * </p>
   *
   * @param string the string to be checked.
   * @return {@code true} if the string is a valid representation, {@code false}
   * otherwise.
   */
  public static boolean isValid(
    final String string
  ) {
    final int length;
    if (null == string || FORTY2_SIZE != (length = string.length())) {
      return false;
    }

    boolean isPositive = false;
    boolean isNegative = false;

    for (int i = 0; i < length; i++) {
      final int codePoint = string.charAt(i);

      // Check if both positive and negative flags are set
      if (isPositive && isNegative) {
        return false;
      }

      // Check if the character is a digit (0-9)
      if (codePoint >= 48 && codePoint <= 57) {
        continue;
      }

      // Check if the character is a lowercase letter (a-z)
      if (0x61 <= codePoint && 0x7a >= codePoint) {
        if (!isPositive) {
          isPositive = true;
        }
        continue;
      }

      // Check if the character is an uppercase letter (A-Z)
      if (0x41 <= codePoint && 0x5b > codePoint) {
        if (!isNegative) {
          isNegative = true;
        }
        continue;
      }

      // Invalid character found
      return false;
    }

    return true;
  }

  /**
   * Creates a {@link Forty2} object from a long value.
   * <p>
   * This method takes a long value and wraps it into a new {@link Forty2}
   * object.
   * </p>
   *
   * @param number the long value to be converted.
   * @return a new {@link Forty2} object representing the given long value.
   * @see #from(long)
   */
  public static Forty2 _from(
    final long number
  ) {
    return new Forty2(number);
  }

  /**
   * Creates a {@link Forty2} object from a long value with validation.
   * <p>
   * This method takes a long value and performs validation to ensure that the
   * value is not between -9 and -1, inclusive. If the value is within this
   * range, an {@link IllegalArgumentException} is thrown. Otherwise, it wraps
   * the value into a new {@link Forty2} object.
   * </p>
   *
   * @param number the long value to be converted.
   * @return a new {@link Forty2} object representing the given long value.
   * @throws IllegalArgumentException if the number is between -9 and -1,
   *                                  inclusive.
   * @see #_from(long)
   */
  public static Forty2 from(
    final long number
  ) {
    if (!isValid(number)) {
      throw new IllegalArgumentException();
    }
    return _from(number);
  }

  /**
   * Converts a base-36 encoded string into a {@link Forty2} object.
   * <p>
   * This method takes a string representing a number in base-36 and converts it
   * into a {@link Forty2} object, where the base-36 number is first parsed into
   * a long value.
   * </p>
   *
   * @param content the base-36 encoded string to be converted.
   * @return a new {@link Forty2} object representing the parsed long value.
   * @throws NumberFormatException if the content string cannot be parsed as a
   *                               base-36 number.
   * @see #from(String)
   */
  public static Forty2 _from(
    final String content
  ) {
    return new Forty2(Long.parseLong(content, 36));
  }

  /**
   * Creates a {@link Forty2} object from a valid base-36 encoded string.
   * <p>
   * This method checks if the string is a valid representation of a 64-bit
   * number before converting it.
   * </p>
   *
   * @param string the base-36 encoded string to be converted.
   * @return a new {@link Forty2} object representing the parsed long value.
   * @throws IllegalArgumentException if the string is not a valid
   *                                  representation.
   * @see #_from(String)
   */
  public static Forty2 from(
    final String string
  ) {
    if (!isValid(string)) {
      throw new IllegalArgumentException();
    }

    int next = 0;
    for (int i = 0; i < FORTY2_SIZE; ++i) {
      if (48 != string.charAt(i)) {
        next = i;
        break;
      }
    }

    if (1 == FORTY2_SIZE - next) {
      return _from(string);
    }

    int i = 0;
    do {
      final int character = string.charAt(next);
      if (65 <= character && 91 > character) {
        return _from("-" + string);
      }
      next += i;
      ++i;
    } while (next < FORTY2_SIZE);

    return _from(string);
  }

  /**
   * Converts this {@link Forty2} object to a long value.
   * <p>
   * This method returns the underlying long value represented by this
   * {@link Forty2} object.
   * </p>
   *
   * @return the long value represented by this {@link Forty2} object.
   */
  public long toLong() {
    return this.number;
  }

  /**
   * Converts the internal number to a base-36 string representation.
   * <p>
   * This method converts the stored long value to a base-36 string.
   * </p>
   *
   * @return the base-36 string representation of the internal number.
   */
  public String toBase36() {
    return Long.toString(this.number, 36);
  }

  /**
   * Converts the internal number to an uppercase base-36 string
   * representation.
   * <p>
   * This private method converts the stored long value to a base-36 string,
   * removes the first character, and converts the rest to uppercase.
   * </p>
   *
   * @return the uppercase base-36 string representation of the internal number.
   */
  public String toUppercaseBase36() {
    return this.toBase36().substring(1).toUpperCase();
  }

  /**
   * Converts the internal number to an unsigned base-36 string representation.
   * <p>
   * This method converts the stored long value to a base-36 string. If the
   * number is positive, it returns the base-36 string as is. If the number is
   * negative, it returns the uppercase base-36 string representation without
   * the first character.
   * </p>
   * <p>
   * If the number is zero, it returns "0".
   * </p>
   *
   * @return the unsigned base-36 string representation of the internal number.
   */
  public String toUnsignedBase36() {
    if (0 < this.number) {
      return this.toBase36();
    }
    if (0 > this.number) {
      return this.toUppercaseBase36();
    }
    return "0";
  }

  @Override
  public boolean equals(final Object o) {
    if (this == o) {
      return true;
    }

    if (null == o || this.getClass() != o.getClass()) {
      return false;
    }

    final Forty2 that = (Forty2) o;
    return this.number == that.number;
  }

  @Override
  public int hashCode() {
    return Long.hashCode(this.number);
  }

  /**
   * Complements the given string with leading zeros to a fixed length of 13
   * characters.
   * <p>
   * This method ensures that the provided string is extended to a length of 13
   * characters by adding leading zeros if necessary. If the string is already
   * 13 characters long, it is returned as is.
   * </p>
   *
   * @param string the input string to be complemented.
   * @return the complemented string with a length of 13 characters.
   */
  private String complement(
    final String string
  ) {
    final int length = string.length();
    final int diff = 13 - length;

    if (0 == diff) {
      return string;
    }

    final StringBuilder builder = new StringBuilder(length + diff);
    for (int i = 0; diff > i; ++i) {
      builder.insert(i, '0');
    }

    builder.append(string);
    return builder.toString();
  }

  /**
   * Converts this {@link Forty2} object to its string representation.
   * <p>
   * This method returns a string representation of the {@link Forty2} object.
   * For positive numbers, it returns the base-36 representation complemented
   * to 13 characters. For negative numbers, it returns the uppercase base-36
   * representation complemented to 13 characters. For zero, it returns a string
   * of 13 zeros ("0000000000000").
   * </p>
   *
   * @return the string representation of this {@link Forty2} object.
   */
  @Override
  public String toString() {
    if (0 < this.number) {
      return this.complement(this.toBase36());
    }
    if (0 > this.number) {
      return this.complement(this.toUppercaseBase36());
    }
    return "0000000000000";
  }
}
