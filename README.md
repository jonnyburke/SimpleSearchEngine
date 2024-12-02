# Simple Search Engine

A simple in-memory search engine implemented in Java, using an inverted index and ranking results using TF-IDF scoring. This project supports basic functionality like indexing documents and searching for single terms in the indexed document set through a command-line interface (CLI) for interactive queries.

---

## Features

- Indexes a set of documents provided as strings by the user.
- Tokenizes document content into individual terms.
- Creates an **inverted index** to efficiently store and retrieve documents for specific terms.
- Ranks search results based on **TF-IDF** scores:
  - **TF (Term Frequency):** Adjusted for document length.
  - **IDF (Inverse Document Frequency):** Reflects the rarity of terms across documents.
- Returns search results sorted by relevance.

---

## Prerequisites:
- **Java 17+**
- **Gradle 7.5+**

## Example

### Indexed Documents:
1. "the brown fox jumped over the brown dog"
2. "the lazy brown dog sat in the corner"
3. "the red fox bit the lazy dog"

### Usage

### Run the Application:
To run the search engine, execute the **'Main.main'** method. Once the application starts, you will be prompted to input documents. After entering documents, type **'done'** to finish the input and start searching. Then, you can enter search terms interactively through the command-line interface (CLI). Simply type a search term and press Enter to view the results. Type **'exit'** to quit the application.

#### Example Interaction:

1. Run the program:
   `java Main`

2. Enter documents one by one, then type **'done'** when finished:  
   `Enter content for document 1 (or type 'done'):` `the brown fox jumped over the brown dog`  
   `Enter content for document 2 (or type 'done'):` `the lazy brown dog sat in the corner`  
   `Enter content for document 3 (or type 'done'):` `the red fox bit the lazy dog`  
   `Enter content for document 4 (or type 'done'):` `done`

3. Enter a search term.
   `Enter search term:` `brown`

4. View the list of matching documents.  
   `Documents matching 'brown': [doc1, doc2]`

5. Type `exit` to exit the program:  
   `Enter search term:` `exit`  
   `Exiting the search engine. Goodbye!`

---

## Technical Details

### Inverted Index
An **inverted index** is a data structure mapping terms to the documents they appear in. This enables efficient lookup for search queries.

### TF-IDF Scoring
- **TF (Term Frequency):** Measures the frequency of a term in a document, adjusted for document length:
  TF = (frequency of term in document) / (total terms in document)
- **IDF (Inverse Document Frequency):** Measures how rare a term is across all documents:
  IDF = log(total documents / documents containing term)
- **TF-IDF Score:** Combines TF and IDF to rank relevance:
  TF-IDF = TF * IDF

---
