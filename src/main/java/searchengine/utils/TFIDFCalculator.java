package searchengine.utils;

import lombok.experimental.UtilityClass;

@UtilityClass
public class TFIDFCalculator {
  public double calculateTFIDF(int termFreq, int docLength, int totalDocs, int docsWithWord) {
    double tf = (double) termFreq / docLength;
    double idf = Math.log((double) totalDocs / docsWithWord);
    return tf * idf;
  }
}

