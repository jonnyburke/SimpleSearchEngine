package searchengine;

import lombok.extern.slf4j.Slf4j;
import searchengine.handler.SimpleSearchEngine;
import searchengine.model.Document;

import java.util.List;
import java.util.Scanner;

/**
 * The Main class serves as the entry point for the SimpleSearchEngine application.
 * It initializes the search engine with a few sample documents and provides a command-line interface (CLI)
 * for the user to input search queries interactively.
 * ---
 * The program uses a scanner to accept user input, performs a search for the entered term, and outputs the results.
 * Users can type a term to search, and the results will be displayed based on the documents indexed by the search engine.
 * The program will continue to prompt for input until the user types 'exit' to quit the application.
 */
@Slf4j
public class Main {
  public static void main(String[] args) {
    SimpleSearchEngine engine = new SimpleSearchEngine();
    Scanner scanner = new Scanner(System.in);

    log.info("Welcome to the Simple Search Engine!");
    log.info("Please enter your documents one by one. Type 'done' when finished:");

    int docCounter = 1;
    while (true) {
      log.info("Enter content for document {} (or type 'done'):", docCounter);
      String content = scanner.nextLine().trim();

      if ("done".equalsIgnoreCase(content)) {
        log.info("Finished adding documents.");
        break;
      }

      engine.addDocument(new Document("doc" + docCounter, content));
      docCounter++;
    }

    log.info("Simple Search Engine initialized with {} documents.", docCounter - 1);
    log.info("Type a term to search (or type 'exit' to quit):");

    while (true) {
      log.info("Enter search term: ");
      String query = scanner.nextLine().trim();

      if ("exit".equalsIgnoreCase(query)) {
        log.info("Exiting the search engine. Goodbye!");
        break;
      }

      List<String> results = engine.search(query);
      if (results.isEmpty()) {
        log.info("No documents found for term: {}", query);
      } else {
        log.info("Documents matching '{}': {}", query, results);
      }
    }
    scanner.close();
  }
}
