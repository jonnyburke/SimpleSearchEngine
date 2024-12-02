package searchengine.handler;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import searchengine.model.Document;

import java.util.List;

class SimpleSearchEngineTest {

  private SimpleSearchEngine engine;

  @BeforeEach
  void setUp() {
    engine = new SimpleSearchEngine();

    engine.addDocument(new Document("doc1", "the brown fox jumped over the brown dog"));
    engine.addDocument(new Document("doc2", "the lazy brown dog sat in the corner"));
    engine.addDocument(new Document("doc3", "the red fox bit the lazy dog"));
  }

  @Test
  void testSearchTermExists() {
    List<String> brownResults = engine.search("brown");
    assertEquals(List.of("doc1", "doc2"), brownResults);

    List<String> foxResults = engine.search("fox");
    assertEquals(List.of("doc3", "doc1"), foxResults);
  }

  @Test
  void testSearchTermDoesNotExist() {
    List<String> results = engine.search("elephant");
    assertTrue(results.isEmpty());
  }

  @Test
  void testSearchCaseInsensitive() {
    List<String> results = engine.search("BROWN");
    assertEquals(List.of("doc1", "doc2"), results);
  }

  @Test
  void testAddDocument() {
    engine.addDocument(new Document("doc4", "this is a new document"));

    List<String> results = engine.search("new");
    assertEquals(List.of("doc4"), results);
  }
}
