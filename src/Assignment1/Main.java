package Assignment1;

import java.io.PrintStream;
import java.util.Scanner;
import java.util.regex.Pattern;

public class Main {

	private static final int MAX_COLLECTIONS = 2;

	private static final String EXC_BEGIN_WITH_BRACKET = "ERROR, INPUT MUST BEGIN WITH '{'",
								EXC_END_WITH_BRACKET = "ERROR, INPUT MUST END WITH '}'", 
								EXC_EMPTY_INPUT = "",
								EXC_NON_ALPHANUMERIC_INPUT = "ERROR, NON-ALPHANUMERIC INPUT : ",
								EXC_FIRST_NOT_LETTER = "ERROR, THE FIRST CHARACTER HAS TO BE A LETTER : ",
								EXC_END_PROGRAM = "PROGRAM EXIT";

	private static final String MSG_GIVE_COLLECTION_NU = "Please enter collection #%d : ",
								MSG_COLLECTION_NU = "Collection #%d:\t\t",
								MSG_UNION = "Union:\t\t\t",
								MSG_DIFFERENCE = "Difference:\t\t",
								MSG_INTERSECTION = "Intersection:\t\t",
								MSG_SYMMETRICDIFFERENCE = "Symmetric difference:\t";
	
	private PrintStream out;
	private IdentifierCollectionRow collectionRow;

	Main() {
		out = new PrintStream(System.out);
	}

	private void executeCollectionOperations() throws Exception{
		IdentifierCollection firstCollection = new IdentifierCollection(collectionRow.getCollectionAtIndex(0));
		IdentifierCollection secondColletction = new IdentifierCollection(collectionRow.getCollectionAtIndex(1));
		
		out.println();
		out.printf(MSG_COLLECTION_NU, 1);
		printCollection(collectionRow.getCollectionAtIndex(0));
		out.printf(MSG_COLLECTION_NU, 2);
		printCollection(collectionRow.getCollectionAtIndex(1));
		
		out.println();
		
		out.printf(MSG_UNION);
		printCollection(firstCollection.union(secondColletction));
		out.printf(MSG_DIFFERENCE);
		printCollection(firstCollection.difference(secondColletction));
		out.printf(MSG_INTERSECTION);
		printCollection(firstCollection.intersection(secondColletction));
		out.printf(MSG_SYMMETRICDIFFERENCE);
		printCollection(firstCollection.symmetricDifference(secondColletction));
	}
	
	private void start() throws Exception {
		IdentifierCollection newCollection = new IdentifierCollection();
		Scanner in = new Scanner(System.in);
		
		while(true){
			newCollection.init();
			collectionRow = new IdentifierCollectionRow(MAX_COLLECTIONS);
			
			for (int i = 0; i < MAX_COLLECTIONS; i++) {
				newCollection.init();
				makeNewCollection(i, newCollection, in);
			}

			executeCollectionOperations();
			out.println();
		}
	}

	private void makeNewCollection(int i, IdentifierCollection idColl, Scanner input) {
		String inputLine;
		
		//if(!input.hasNextLine()){
		//	out.println("");
		//}
		
		try {
			out.printf(MSG_GIVE_COLLECTION_NU, i + 1);
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
		
		} else if (line.length() == 2){
			//This is one of the two cases in which the collection is empty 
			collectionRow.addCollection(new IdentifierCollection());
		
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

		if(!nextCharIsLetter(wordScanner)){
			throw new Exception(EXC_FIRST_NOT_LETTER + word);
		}
		
		for (int i = 0; i < word.length(); i++) {
			if (!nextCharIsLetter(wordScanner) && !nextCharIsDigit(wordScanner)) {
				throw new Exception(EXC_NON_ALPHANUMERIC_INPUT + word);
			}

			wordScanner.next();
		}
	}

	private boolean nextCharIsLetter(Scanner input) {
		input.useDelimiter("");
		return (input.hasNext("[a-zA-Z]"));
	}
	
	private boolean nextCharIsDigit(Scanner input) {
		input.useDelimiter("");
		return (input.hasNext("[0-9]"));
	}

	private void printCollection(IdentifierCollection collection) throws Exception {
		IdentifierCollection copiedCollection  = new IdentifierCollection(collection);
		int size = copiedCollection.size();
		
		out.printf("{ ");
		for (int i = 0; i < size; i++) {
			out.printf("%s ",copiedCollection.getIdentifier());
		}
		out.printf("}\n");
	}

	public static void main(String[] argv) throws Exception {
		new Main().start();
	}
}
