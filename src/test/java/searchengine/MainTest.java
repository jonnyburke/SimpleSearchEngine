package searchengine;

import static org.junit.jupiter.api.Assertions.assertTrue;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

@Slf4j
class MainTest {
  @Test
  void testMainMethod() {
    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    System.setOut(new PrintStream(outputStream));

    String simulatedUserInput = """
                the brown fox jumped over the brown dog
                the lazy brown dog sat in the corner
                the red fox bit the lazy dog
                done
                brown
                fox
                exit
                """;
    System.setIn(new ByteArrayInputStream(simulatedUserInput.getBytes()));

    Main.main(new String[]{});

    String output = outputStream.toString();

    assertTrue(output.contains("Documents matching 'brown': [doc1, doc2]"));
    assertTrue(output.contains("Documents matching 'fox': [doc3, doc1]"));

    System.setOut(System.out);
    System.setIn(System.in);
  }

  @Test
  void testMainMethodWithNoResults() {
    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    System.setOut(new PrintStream(outputStream));

    String simulatedUserInput = """
                the brown fox jumped over the brown dog
                the lazy brown dog sat in the corner
                the red fox bit the lazy dog
                done
                unicorn
                exit
                """;
    System.setIn(new ByteArrayInputStream(simulatedUserInput.getBytes()));

    Main.main(new String[]{});

    String output = outputStream.toString();

    assertTrue(output.contains("No documents found for term: unicorn"));
    assertTrue(output.contains("Exiting the search engine. Goodbye!"));

    System.setOut(System.out);
    System.setIn(System.in);
  }
}