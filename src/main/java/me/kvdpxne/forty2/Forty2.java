package me.kvdpxne.forty2;

public final class Forty2 {

  /**
   *
   */
  private final long number;

  /**
   *
   */
  private Forty2(final long number) {
    this.number = number;
  }

  /**
   *
   */
  public static Forty2 from(final long number) {
    if (-9 <= number && number <= -1) {
      throw new IllegalArgumentException();
    }
    return new Forty2(number);
  }

  /**
   *
   */
  public static Forty2 from(String string) {
    int len = string.length();
    if (1 == len) {
      long number = Long.parseLong(string, 36);
      return new Forty2(number);
    }
    for (int i = 0; i < len; i++) {
      char ch = string.charAt(i);
      if (ch > 64 && ch < 91) {
        string = "-" + string.toUpperCase();
        break;
      }
    }
    long number = Long.parseLong(string, 36);
    return new Forty2(number);
  }

  /**
   *
   */
  public long toLong() {
    return this.number;
  }

  /**
   *
   */
  private String toLowerString() {
    return Long.toString(this.number, 36);
  }

  /**
   *
   */
  private String toUpperString() {
    return this.toLowerString().substring(1).toUpperCase();
  }

  /**
   *
   */
  @Override
  public String toString() {
    if (0 < this.number) {
      return this.toLowerString();
    }
    if (0 > this.number) {
      return this.toUpperString();
    }
    return "0";
  }
}
