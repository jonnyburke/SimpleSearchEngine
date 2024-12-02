package searchengine.handler;

import searchengine.model.Document;
import searchengine.utils.TFIDFCalculator;
import searchengine.utils.Tokenizer;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * SimpleSearchEngine is a basic in-memory search engine implementation using an inverted index.
 * It supports the indexing of documents and searching for terms using the TF-IDF (Term Frequency-Inverse Document Frequency)
 * ranking mechanism to rank search results. The engine maintains an inverted index where each term maps to documents
 * containing that term, and also keeps track of document lengths to calculate TF-IDF scores for search results.
 */

public class SimpleSearchEngine {
  private final Map<String, Map<String, Integer>> invertedIndex = new HashMap<>();
  private final Map<String, Integer> documentLengths = new HashMap<>();
  private int totalDocuments = 0;

  /**
   * Adds a document to the search engine, tokenizing the document content and updating the inverted index.
   * For each token in the document, the term frequency is calculated and added to the inverted index.
   * Additionally, the document's length is stored for later use in TF-IDF calculation.
   *
   * @param doc The document to be added to the search engine.
   */
  public void addDocument(Document doc) {
    List<String> tokens = Tokenizer.tokenize(doc.content());
    totalDocuments++;
    documentLengths.put(doc.id(), tokens.size());

    for (String token : tokens) {
      invertedIndex.putIfAbsent(token, new HashMap<>());
      Map<String, Integer> postings = invertedIndex.get(token);
      postings.put(doc.id(), postings.getOrDefault(doc.id(), 0) + 1);
    }
  }

  /**
   * Searches the indexed documents for a given search term, calculates the TF-IDF scores for the term across all
   * documents containing it, and returns a list of document IDs sorted by their respective TF-IDF score.
   *
   * @param term The term to search for in the documents.
   * @return A list of document IDs sorted by the TF-IDF score, from highest to lowest.
   */
  public List<String> search(String term) {
    term = term.toLowerCase();
    if (!invertedIndex.containsKey(term)) {
      return Collections.emptyList();
    }

    Map<String, Integer> postings = invertedIndex.get(term);

    Map<String, Double> tfIdfScores = new HashMap<>();
    for (Map.Entry<String, Integer> entry : postings.entrySet()) {
      String docId = entry.getKey();
      int termFrequency = entry.getValue();
      int docLength = documentLengths.get(docId);

      double tfIdf = TFIDFCalculator.calculateTFIDF(
          termFrequency, docLength, totalDocuments, postings.size()
      );
      tfIdfScores.put(docId, tfIdf);
    }

    return tfIdfScores.entrySet().stream()
        .sorted((a, b) -> Double.compare(b.getValue(), a.getValue()))
        .map(Map.Entry::getKey)
        .toList();
  }
}
