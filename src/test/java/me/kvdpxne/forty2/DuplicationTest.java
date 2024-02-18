package me.kvdpxne.forty2;

import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * Unit test class to check for duplication in generated identifiers.
 */
final class DuplicationTest {

  /**
   * Method to test for duplication in generated identifiers.
   *
   * @throws InterruptedException If any thread is interrupted while waiting
   */
  @Test
  void checkDuplication() throws InterruptedException {
    // Number of threads to generate identifiers concurrently.
    final int threadCount = 24;

    // The Number of iterations each thread will perform.
    final int iterationCount = 167_150;

    // Set to store generated identifiers concurrently.
    final Set<String> identifiers = ConcurrentHashMap.newKeySet();

    // Counter to keep track of duplicated identifiers.
    final AtomicInteger counter = new AtomicInteger();

    // CountDownLatch to synchronize the end of all threads.
    final CountDownLatch endLatch = new CountDownLatch(threadCount);

    // Spawn threads to generate identifiers concurrently.
    for (int i = 0; threadCount > i; ++i) {
      new Thread(() -> {

        // Generate identifiers in each thread.
        for (int j = 0; iterationCount > j; ++j) {
          final String identifier = Forty2.create();

          // Check for duplication and increment counter if found.
          if (!identifiers.add(identifier)) {
            counter.incrementAndGet();
            break;
          }
        }

        // Notify the end of thread execution.
        endLatch.countDown();
      }).start();
    }

    // Wait for all threads to finish execution.
    endLatch.await();

    // Assert that no duplication is detected.
    Assertions.assertFalse(counter.intValue() != 0);
  }
}
