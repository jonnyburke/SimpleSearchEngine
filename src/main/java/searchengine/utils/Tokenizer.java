package searchengine.utils;

import lombok.experimental.UtilityClass;

import java.util.*;

@UtilityClass
public class Tokenizer {
  public List<String> tokenize(String content) {
    return Arrays.stream(content.toLowerCase().split("\\W+"))
        .filter(word -> !word.isEmpty())
        .toList();
  }
}
