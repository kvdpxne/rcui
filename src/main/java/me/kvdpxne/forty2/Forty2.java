package me.kvdpxne.forty2;

public final class Forty2 {

  /**
   *
   */
  private final long number;

  /**
   *
   */
  Forty2(final long number) {
    this.number = number;
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
    if (-9 <= number && number <= -1) {
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
   *
   */
  public static Forty2 from(
    final String string
  ) {
    final int minusIndex = string.indexOf('-');
    //
    //
    if (0 == minusIndex) {
      return _from(string);
    }
    // 2 scenariusze
    // albo liczba nie jest ujemna i jest typem javy
    // albo liczba jest ujemna i jest typem Forty2
    if (-1 == minusIndex) {
      final int length = string.length();
      // jezeli ciag znakow jest o dlugosci 1 to nie ma znaczenia czy liczba
      // nie jest ujemna i jest typem java czy jest ujemna i jest typem Forty2
      // poniewaz liczba bedzie zawsze dodatnia, poniewaz Forty2 nie wspiera
      // liczb ujemnych w zakresie od -9 do -1
      if (1 == length) {
        return _from(string);
      }
      int i = 0;
      do {
        //
        //
        if (Ascii36.isUppercase(string.charAt(i))) {
          return _from("-" + string.toUpperCase());
        }
        ++i;
      } while (length > i);
      //
      //
      return _from(string);
    }
    //
    throw new IllegalArgumentException();
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
  private String toUppercaseBase36() {
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
