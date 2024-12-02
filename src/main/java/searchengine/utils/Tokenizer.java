package searchengine.utils;

import lombok.experimental.UtilityClass;

import java.util.*;

/**
 * The Tokenizer class provides a utility method for tokenizing strings.
 */
@UtilityClass
public class Tokenizer {
  /**
   * Tokenizes the given content into a list of lowercase words.
   * It splits the content into words based on non-word characters, removes empty words, and returns the list of words.
   *
   * @param content The text content to be tokenized.
   * @return A list of lowercase words from the input content.
   */
  public List<String> tokenize(String content) {
    return Arrays.stream(content.toLowerCase().split("\\W+"))
        .filter(word -> !word.isEmpty())
        .toList();
  }
}
