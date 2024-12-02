package searchengine.utils;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TokenizerTest {

  @Test
  void testTokenize() {
    String input = "The quick, brown fox!";

    List<String> tokens = Tokenizer.tokenize(input);

    assertEquals(List.of("the", "quick", "brown", "fox"), tokens);
  }

  @Test
  void testTokenizeEmptyString() {
    List<String> tokens = Tokenizer.tokenize("");

    assertTrue(tokens.isEmpty());
  }

  @Test
  void testTokenizeWithSpecialCharacters() {
    String input = "Hello, World! 123.";

    List<String> tokens = Tokenizer.tokenize(input);

    assertEquals(List.of("hello", "world", "123"), tokens);
  }
}
