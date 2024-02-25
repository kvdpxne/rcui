## About

**Forty2** is a lightweight and versatile **Java** library designed to generate
unique identifiers with customizable length and character sets.
Originally based on the concept of [Nano ID](https://github.com/ai/nanoid),
it provides a simple yet powerful solution for generating unique identifiers
tailored to your specific needs.

### Features

- **Customizable**: Generate identifiers of any desired length and from a wide
  range of characters, providing flexibility and adaptability to diverse
  requirements.
- **Universal Uniqueness**: Utilizes algorithms to ensure uniqueness across
  generated identifiers, guaranteeing minimal collision probability.
- **Easy Integration**: Simple setup and easy integration into your projects,
  facilitating smooth adoption and usage.

### Note

If you came here by accident and are **looking for a more efficient** way to
generate unique identifiers than the natively built-in solution of
``java.util.UUID``, please see
[this repository](https://github.com/f4b6a3/tsid-creator).

## Benchmark

```text
Benchmark                     Mode  Cnt     Score    Error   Units
Forty2_alphanumeric          thrpt   25  1259.872 ±  2.109  ops/ms
Forty2_all                   thrpt   25  1392.449 ±  4.583  ops/ms
Forty2_alphanumeric_size_21  thrpt   25  1070.119 ± 11.064  ops/ms
Forty2_all_size_21           thrpt   25  1104.755 ±  3.084  ops/ms
Forty2_create                thrpt   25  1261.872 ±  1.560  ops/ms
UUID_randomUUID              thrpt   25  1509.737 ±  1.545  ops/ms
UUID_randomUUID_toString     thrpt   25  1223.936 ±  6.679  ops/ms
```

Number of threads used in this the benchmark: 4.  
System: AMD Ryzen 5 2600, 16 GB RAM, Windows 10 22H2, JVM 21.