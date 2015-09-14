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
	
	private void systemExit(Scanner scanner) throws Exception{
		//if user presses ctrl-z, throw an exception that has leads to a system exit
		//yet to be implemented
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
		IdentifierCollection newCollection = new IdentifierCollection();
		boolean exceptionThrown;
		
		do{
			try {
				exceptionThrown = false;
				
				out.printf(MSG_GIVE_COLLECTION_NU, i);
				inputLine = input.nextLine();
				newCollection = processLine(inputLine);
			} catch (Exception e) {
				exceptionThrown = true;
				
				out.println(e.getMessage());
			}
		}while(exceptionThrown);
		
		return newCollection;
	}

	private IdentifierCollection processLine(String line) throws Exception {
		String collectionLine = checkValidationOfLine(line);
		//The String 'line' contains everything that's between the brackets { ... }
		
		return processCollection(collectionLine);
	}

	private IdentifierCollection processCollection(String collectionLine) throws Exception {
		// The collectionLine includes every identifier filled in by the user: { ... }
		
		IdentifierCollection newCollection = new IdentifierCollection();
		Scanner collectionScanner = new Scanner(collectionLine);
		String inputIdentifier;
		
		while (collectionScanner.hasNext()) {
			inputIdentifier = checkValidationOfWord(collectionScanner);
			newCollection.add(new Identifier(inputIdentifier));
		}
		
		return newCollection;
	}

	private String checkValidationOfLine(String line) throws Exception{
		//This method returns the input between the brackets if the general input is valid, and throws an exception if it is not.		
		Scanner lineScanner = new Scanner(line);
		lineScanner.useDelimiter("");

		if (!lineScanner.hasNext()) {
			throw new Exception(EXC_EMPTY_INPUT);
			
		} else if (lineScanner.next().charAt(0) != '{') {
			throw new Exception(EXC_BEGIN_WITH_BRACKET);

		} else if (line.charAt(line.length() - 1) != '}') {
			throw new Exception(EXC_END_WITH_BRACKET);
		}
		
		lineScanner.useDelimiter("}");
		return lineScanner.next();
	}
	
	private String checkValidationOfWord(Scanner input) throws Exception {
		//This method returns the next input identifier if it is valid, and throws an exception if it is not.
		String inputIdentifier = input.next();
		Scanner inputIdentifierScanner = new Scanner(inputIdentifier);

		if(!nextCharIsLetter(inputIdentifierScanner)){
			throw new Exception(EXC_FIRST_NOT_LETTER + inputIdentifier);
		}
		
		for (int i = 0; i < inputIdentifier.length(); i++) {
			if (!nextCharIsLetter(inputIdentifierScanner) && !nextCharIsDigit(inputIdentifierScanner)) {
				throw new Exception(EXC_NON_ALPHANUMERIC_INPUT + inputIdentifier);
			}
			inputIdentifierScanner.next();
		}
		
		return inputIdentifier;
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
		out.println("{" + collection + " }");
	}

	public static void main(String[] argv) throws Exception {
		new Main().start();
	}
}
