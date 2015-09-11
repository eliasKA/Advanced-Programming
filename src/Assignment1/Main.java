package Assignment1;

import java.io.PrintStream;
import java.util.Scanner;
import java.util.regex.Pattern;

public class Main {
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

	Main() {
		out = new PrintStream(System.out);
	}

	private void executeCollectionOperations(IdentifierCollection firstCollection, IdentifierCollection secondColletction ) throws Exception{
		
		out.println();
		out.printf(MSG_COLLECTION_NU, 1);
		printCollection(firstCollection);
		out.printf(MSG_COLLECTION_NU, 2);
		printCollection(secondColletction);
		
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
	
	private void eol(Scanner scanner){
		if(!scanner.hasNext()){
			out.printf("Exit");
			System.exit(1);
		}
	}
	
	
	private void start() throws Exception {
		IdentifierCollection 	collection1,
								collection2;
		Scanner in = new Scanner(System.in);
		
		do{
			collection1 = makeNewCollection(1, in);
			collection2 = makeNewCollection(2, in);

			executeCollectionOperations(collection1, collection2);
			out.println();
		}while(true);
	}

	private IdentifierCollection makeNewCollection(int i, Scanner input) {
		String inputLine;
		IdentifierCollection newCollection;
		
		try {
			out.printf(MSG_GIVE_COLLECTION_NU, i);
			
			inputLine = input.nextLine();
			newCollection = processLine(inputLine);
		} catch (Exception e) {
			out.println(e.getMessage());
			return makeNewCollection(i, input);
		}
		
		return newCollection;
	}

	private IdentifierCollection processLine(String line) throws Exception {
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
			return new IdentifierCollection();
		
		} else {
			lineScanner.useDelimiter("}");
			String collectionLine = lineScanner.next();
			return processCollection(collectionLine);
		}
	}

	private IdentifierCollection processCollection(String line) throws Exception {
		IdentifierCollection newCollection = new IdentifierCollection();
		Scanner lineScanner = new Scanner(line);

		while (lineScanner.hasNext()) {
		
			String idString = lineScanner.next();

			checkValidation(idString);

			newCollection.add(new Identifier(idString));
		}
		
		return newCollection;
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

	private void printCollection(IdentifierCollection collection) {
		out.printf("{"+collection+" }\n");
	}

	public static void main(String[] argv) throws Exception {
		new Main().start();
	}
}
