package searchengine.handler;

import lombok.extern.slf4j.Slf4j;
import searchengine.model.Document;
import searchengine.utils.TFIDFCalculator;
import searchengine.utils.Tokenizer;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
public class SimpleSearchEngine {
  private final Map<String, Map<String, Integer>> invertedIndex = new HashMap<>();
  private final Map<String, Integer> documentLengths = new HashMap<>();
  private int totalDocuments = 0;

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

  public static void main(String[] args) {
    SimpleSearchEngine engine = new SimpleSearchEngine();

    engine.addDocument(new Document("doc1", "the brown fox jumped over the brown dog"));
    engine.addDocument(new Document("doc2", "the lazy brown dog sat in the corner"));
    engine.addDocument(new Document("doc3", "the red fox bit the lazy dog"));

    log.info("Search for 'brown': {}", engine.search("brown"));
    log.info("Search for 'fox': {}", engine.search("fox"));
  }
}
