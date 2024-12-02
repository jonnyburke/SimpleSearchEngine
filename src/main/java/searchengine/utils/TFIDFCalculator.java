package searchengine.utils;

import lombok.experimental.UtilityClass;

/**
 * The TFIDFCalculator class provides a utility method to calculate the TF-IDF score for a term in a document.
 */
@UtilityClass
public class TFIDFCalculator {
  /**
   * Calculates the Term Frequency - Inverse Document Frequency (TF-IDF) score for a given term in a document.
   * The formula for TF-IDF is:
   * - TF = term frequency, which is the ratio of term frequency in the document to the total number of words in the document.
   * - IDF = inverse document frequency, calculated as the logarithm of the total number of documents divided by the number of documents containing the term.
   *
   * @param termFreq The frequency of the term in the document.
   * @param docLength The total number of words in the document.
   * @param totalDocs The total number of documents in the search engine.
   * @param docsWithWord The number of documents containing the term.
   * @return The TF-IDF score for the term in the document.
   */
  public double calculateTFIDF(int termFreq, int docLength, int totalDocs, int docsWithWord) {
    double tf = (double) termFreq / docLength;
    double idf = Math.log((double) totalDocs / docsWithWord);
    return tf * idf;
  }
}

