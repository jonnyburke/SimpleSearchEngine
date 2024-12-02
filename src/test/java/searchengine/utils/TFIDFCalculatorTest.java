package searchengine.utils;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TFIDFCalculatorTest {

  @Test
  void testCalculateTFIDF() {

    int termFreq = 3;
    int docLength = 10;
    int totalDocs = 5;
    int docsWithWord = 2;

    double tfidf = TFIDFCalculator.calculateTFIDF(termFreq, docLength, totalDocs, docsWithWord);

    double expectedTf = 0.3;
    double expectedIdf = Math.log(5.0 / 2.0);
    double expectedTfidf = expectedTf * expectedIdf;

    assertEquals(expectedTfidf, tfidf, 1e-6);
  }
}


