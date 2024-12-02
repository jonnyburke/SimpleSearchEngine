package searchengine.handler;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import searchengine.model.Document;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
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

  @Test
  void testMainMethod() {
    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    System.setOut(new PrintStream(outputStream));

    SimpleSearchEngine.main(new String[]{});
    String output = outputStream.toString();

    log.debug("Output: \n{}", output);
    assertTrue(output.contains("Search for 'brown': [doc1, doc2]"));
    assertTrue(output.contains("Search for 'fox': [doc3, doc1]"));
  }
}
