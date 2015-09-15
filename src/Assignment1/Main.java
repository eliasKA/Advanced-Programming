package Assignment1;

import java.io.PrintStream;
import java.util.Scanner;
import java.util.regex.Pattern;

public class Main {
	private static final String EXC_BEGIN_WITH_BRACKET = "ERROR, INPUT MUST BEGIN WITH '{'",
								EXC_END_WITH_BRACKET = "ERROR, INPUT MUST END WITH '}'", 
								EXC_EMPTY_INPUT = "Please re-enter input",
								EXC_NON_ALPHANUMERIC_INPUT = "ERROR, NON-ALPHANUMERIC INPUT : ",
								EXC_FIRST_NOT_LETTER = "ERROR, THE FIRST CHARACTER HAS TO BE A LETTER : ",
								//This is the exception-message that the Scanner object throws when ctrl-z (windows) or ctrl-d (linux) is pressed.
								EXC_CONSOLE_EXIT = "No line found";

	private static final String MSG_GIVE_COLLECTION_NU = "Please enter collection #%d : ",
								MSG_COLLECTION_NU = "Collection #%d:\t\t",
								MSG_UNION = "Union:\t\t\t",
								MSG_DIFFERENCE = "Difference:\t\t",
								MSG_INTERSECTION = "Intersection:\t\t",
								MSG_SYMMETRICDIFFERENCE = "Symmetric difference:\t",
								MSG_SYSTEM_EXIT = "PROGRAM TERMINATED";
	
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
	
	private void start() {
		IdentifierCollection 	collection1,
								collection2;
		Scanner in = new Scanner(System.in);
		
		do{
			collection1 = makeNewCollection(1, in);
			collection2 = makeNewCollection(2, in);

			try {
				executeCollectionOperations(collection1, collection2);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				out.println(e.getMessage());
			}
			out.println();
		}while(in.hasNext());
		
		out.println("exit");
	}
	
	private void eoln(Exception e){
		if(e.getMessage().equals(EXC_CONSOLE_EXIT)){
			out.println();
			out.println(MSG_SYSTEM_EXIT);
			System.exit(1);
		}
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
				eoln(e);
				out.println(e.getMessage());
			}
		}while(exceptionThrown);
		
		return newCollection;
	}

	private IdentifierCollection processLine(String line) throws Exception {
		String collectionLine = checkValidationOfLine(line);
		//The String 'line' contains the whole input line, including the brackets, ex: "{ a b c }"
		
		return processCollection(collectionLine);
	}

	private IdentifierCollection processCollection(String collectionLine) throws Exception {
		// The argument includes everything filled in by the user between the brackets, ex: "a b c"
		
		IdentifierCollection newCollection = new IdentifierCollection();
		Scanner collectionScanner = new Scanner(collectionLine);
		String inputIdentifier;
		
		while (collectionScanner.hasNext()) {
			inputIdentifier = checkValidationOfNextWord(collectionScanner);
			newCollection.add(new Identifier(inputIdentifier));
		}
		
		return newCollection;
	}

	private String checkValidationOfLine(String line) throws Exception{
		//This method returns the input between the brackets if the general input is valid, and throws an exception if it is not.	
		//"{a b c}" --(check if valid)--> "a b c"
		
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
		
		if(lineScanner.hasNext()){
			return lineScanner.next();
		}else{
			return " "; 
		}
		
	}
	
	private String checkValidationOfNextWord(Scanner input) throws Exception {
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

	public static void main(String[] argv) {
		new Main().start();
	}
}
