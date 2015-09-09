package Assignment1;

import java.io.PrintStream;
import java.util.Scanner;
import java.util.regex.Pattern;

public class Main {

	private static final int MAX_COLLECTIONS = 2;

	private static final String EXC_BEGIN_WITH_BRACKET = "ERROR, INPUT MUST BEGIN WITH '{'",
			EXC_END_WITH_BRACKET = "ERROR, INPUT MUST END WITH '}'", EXC_EMPTY_INPUT = "",
			EXC_NON_ALPHANUMERIC_INPUT = "ERROR, NON-ALPHANUMERIC INPUT : ";

	private PrintStream out;
	private IdentifierCollectionRow collectionRow;

	Main() {
		out = new PrintStream(System.out);
		collectionRow = new IdentifierCollectionRow(MAX_COLLECTIONS);
	}

	private void start() throws Exception {
		IdentifierCollection newCollection = new IdentifierCollection();
		Scanner in = new Scanner(System.in);

		for (int i = 0; i < MAX_COLLECTIONS; i++) {
			newCollection.init();
			makeNewCollection(i, newCollection, in);
		}
		IdentifierCollection firstCollection = new IdentifierCollection(collectionRow.getCollectionAtIndex(0));
		IdentifierCollection secondCollection = new IdentifierCollection(collectionRow.getCollectionAtIndex(1));
		//printCollection(firstCollection.union(secondCollection));
		//printCollection(firstCollection.intersection(secondCollection));
		printCollection(firstCollection.difference(secondCollection));
		//printCollection(firstCollection.symmetricDifference(secondCollection));
	}

	private void makeNewCollection(int i, IdentifierCollection idColl, Scanner input) {
		String inputLine;
		try {
			out.printf("Give collection #%d : ", i + 1);
			inputLine = input.nextLine();
			processLine(inputLine, idColl);
		} catch (Exception e) {
			out.println(e.getMessage());
			makeNewCollection(i, idColl, input);
		}
	}

	private void processLine(String line, IdentifierCollection idColl) throws Exception {
		Scanner lineScanner = new Scanner(line);
		lineScanner.useDelimiter("");

		if (!lineScanner.hasNext()) {
			// Case in which nothing is filled in
			throw new Exception(EXC_EMPTY_INPUT);

		} else if (lineScanner.next().charAt(0) != '{') {
			throw new Exception(EXC_BEGIN_WITH_BRACKET);

		} else if (line.charAt(line.length() - 1) != '}') {
			throw new Exception(EXC_END_WITH_BRACKET);

		} else {
			lineScanner.useDelimiter("}");

			String collectionLine = lineScanner.next();

			collectionRow.addCollection(processCollection(collectionLine, new IdentifierCollection(idColl)));
		}
	}

	private IdentifierCollection processCollection(String line, IdentifierCollection idColl) throws Exception {
		Scanner lineScanner = new Scanner(line);

		while (lineScanner.hasNext()) {
			String idString = lineScanner.next();

			checkValidation(idString);

			idColl.add(new Identifier(idString));
		}
		return idColl;
	}

	private void checkValidation(String word) throws Exception {
		Scanner wordScanner = new Scanner(word);

		for (int i = 0; i < word.length(); i++) {
			if (!nextCharIsValid(wordScanner)) {
				throw new Exception(EXC_NON_ALPHANUMERIC_INPUT + word);
			}

			wordScanner.next();
		}
	}

	private boolean nextCharIsValid(Scanner input) {
		input.useDelimiter("");
		return (input.hasNext("[0-9]") || input.hasNext("[a-zA-Z]"));
	}

	private void printCollection(IdentifierCollection collection) throws Exception {
		int size = collection.size();
		IdentifierCollection tempCollection = new IdentifierCollection(collection);
		for (int i = 0; i < size; i++) {
			out.print(tempCollection.getIdentifier() + " - ");
		}
		out.println();
	}

	public static void main(String[] argv) throws Exception {
		new Main().start();
	}
}
