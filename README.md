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

## Installation

Below are the instructions for installing **Forty2** using
[Gradle](https://gradle.org) with [JitPack](https://jitpack.io):

### Gradle

1. Open your `build.gradle` file.
2. Add the [JitPack](https://jitpack.io) repository to your `repositories`
   block:

```groovy
repositories {
  maven {
    url = "https://jitpack.io"
    content {
      includeGroup("com.github.kvdpxne")
    }
  }
}
```

3. Add the **Forty2** dependency to your `dependencies` section:

```groovy
dependencies {
  implementation("com.github.kvdpxne:forty2:<VERSION>")
}
```

4. Replace `<VERSION>` with the version of **Forty2** you want to use.

Now, your project will fetch the **Forty2** dependency from
[JitPack](https://jitpack.io) while excluding other repositories from the
dependency search.

For more information about [JitPack](https://jitpack.io) and how it works,
you can refer to the [JitPack documentation](https://docs.jitpack.io).