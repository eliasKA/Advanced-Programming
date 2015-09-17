package Assignment1;

import java.io.PrintStream;
import java.util.Scanner;
import java.util.regex.Pattern;

/**
 * 
 * @author Alae & Elias 
 *
 */

public class Main {
	private static final int 	MAX_AMOUNT_OF_ELEMENTS = 10;
	
	private static final String EXC_BEGIN_WITH_BRACKET = "ERROR, INPUT MUST BEGIN WITH '{'",
								EXC_END_WITH_BRACKET = "ERROR, INPUT MUST END WITH '}'", 
								EXC_EMPTY_INPUT = "Empty Input",
								EXC_NON_ALPHANUMERIC_INPUT = "ERROR, NON-ALPHANUMERIC INPUT : ",
								EXC_FIRST_NOT_LETTER = "ERROR, THE FIRST CHARACTER HAS TO BE A LETTER : ",
								EXC_MAX_AMOUNT_OF_ELEMENTS = "ERROR, MAX AMOUNT OF INPUT ELEMENTS EXCEEDED: " + MAX_AMOUNT_OF_ELEMENTS,
								EXC_EOLN = "No line found";

	private static final String MSG_GIVE_COLLECTION_NU = "Please enter collection #%d : ",
								MSG_COLLECTION_NU = "Collection #%d:\t\t",
								MSG_UNION = "Union:\t\t\t",
								MSG_DIFFERENCE = "Difference:\t\t",
								MSG_INTERSECTION = "Intersection:\t\t",
								MSG_SYMMETRICDIFFERENCE = "Symmetric difference:\t",
								MSG_SYSTEM_EXIT = "PROGRAM TERMINATED";
	
	private static final String CURLY_BRACKET_OPEN = "{",
								CURLY_BRACKET_CLOSE = "}",
								SPACE = " ",
								EMPTY_STRING = "";
	 
	private PrintStream out;

	Main() {
		out = new PrintStream(System.out);
	}

	private void start() {
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

	public static void main(String[] argv) {
		new Main().start();
	}

	private IdentifierCollection makeNewCollection(int i, Scanner input) {
		String inputLine;
		IdentifierCollection resultCollection = new IdentifierCollection();
		boolean exceptionThrown = false;
		
		//do-while loop repeats if an exception is thrown, giving the user a new chance to enter input
		do{
			try {
				exceptionThrown = false;
				out.printf(MSG_GIVE_COLLECTION_NU, i);
				
				inputLine = input.nextLine();
				resultCollection = parseLine(inputLine);
				
			} catch (Exception e) {
				eoln(e);
				exceptionThrown = true;
				
				if(e.getMessage().equals(EXC_EMPTY_INPUT)){
					//Do nothing
				}else{
					out.println(e.getMessage());	
				}
			}	
			
		}while(exceptionThrown);
		
		return resultCollection;
	}
	
	private void executeCollectionOperations(IdentifierCollection firstCollection, IdentifierCollection secondColletction ){
		out.println();
		
		out.printf(MSG_COLLECTION_NU, 1);
		printCollection(firstCollection);
		
		out.printf(MSG_COLLECTION_NU, 2);
		printCollection(secondColletction);
		
		out.println();
		
		try{
			
			out.printf(MSG_UNION);
			printCollection(firstCollection.union(secondColletction));
			
			out.printf(MSG_DIFFERENCE);
			printCollection(firstCollection.difference(secondColletction));
			
			out.printf(MSG_INTERSECTION);
			printCollection(firstCollection.intersection(secondColletction));
			
			out.printf(MSG_SYMMETRICDIFFERENCE);
			printCollection(firstCollection.symmetricDifference(secondColletction));	
		
		}catch (Exception e){
			out.println(e.getMessage());
		}
	}

	private IdentifierCollection parseLine(String line) throws Exception{
		Scanner input = new Scanner(line);
		input.useDelimiter(EMPTY_STRING);
		
		ignoreSpaces(input);
		
		checkForEmpty(input);
		checkForOpenBracket(input);
		ignoreSpaces(input);
		
		return parseCollection(input);
	}

	private IdentifierCollection parseCollection(Scanner input) throws Exception {
		StringBuffer identifierBuffer = new StringBuffer();
		IdentifierCollection resultCollection = new IdentifierCollection();
		boolean isFirstChar = true;
		
		while(!input.hasNext(CURLY_BRACKET_CLOSE)){
			validateNextChar(input, isFirstChar);
			identifierBuffer.append(input.next().charAt(0));
			isFirstChar = false;
			
			if(!input.hasNext()){
				// The input doesn't end with }
				throw new Exception(EXC_END_WITH_BRACKET);
			}
			
			if(ignoreSpaces(input) && identifierBuffer.length() > 0){
				resultCollection.add(new Identifier(identifierBuffer.toString()));
				identifierBuffer = new StringBuffer();
				
				isFirstChar = true;
			}
		}
		
		input.next();
		ignoreSpaces(input);
		
		if(input.hasNext()){
			// There is still input after the '}'
			throw new Exception(EXC_END_WITH_BRACKET);
		}
		
		checkInputSize(resultCollection);
		return resultCollection;
	}

	
	private void eoln(Exception e){
		//Checks if ctrl-d/ctrl-z has been pressed causing the console to close
		
		if(e.getMessage().equals(EXC_EOLN)){
			out.println();
			out.println(MSG_SYSTEM_EXIT);
			
			System.exit(1);
		}
	}
	
	private void checkInputSize(IdentifierCollection collection) throws Exception{
		if(collection.size() > MAX_AMOUNT_OF_ELEMENTS){
			throw new Exception(EXC_MAX_AMOUNT_OF_ELEMENTS);
		}
	}
	
	private boolean ignoreSpaces(Scanner input){
		boolean result = false;
		
		while(input.hasNext(SPACE)){
			// Skips all the spaces and returns whether there were any spaces
			input.next();
			result = true;
		}
		
		return result;
	}
	
	private void validateNextChar(Scanner input, boolean isFirstChar) throws Exception{
		// Checks if the next character is valid, and throws an exception if it's not
			
		if (!nextCharIsLetter(input) && !nextCharIsDigit(input)){
			input.useDelimiter(" ");
			throw new Exception(EXC_NON_ALPHANUMERIC_INPUT + input.next());
			
		}else if(isFirstChar && !nextCharIsLetter(input)){
			input.useDelimiter(" ");
			throw new Exception(EXC_FIRST_NOT_LETTER + input.next());
		}
	}
	
	private void checkForOpenBracket(Scanner input) throws Exception {
		if(!input.next().equals(CURLY_BRACKET_OPEN)){
			// The input doesn't begin with {
			throw new Exception(EXC_BEGIN_WITH_BRACKET);
		}
	}

	private void checkForEmpty(Scanner input) throws Exception {
		if(!input.hasNext()){
			// The input is empty
			throw new Exception(EXC_EMPTY_INPUT);	
		}
	}

	private boolean nextCharIsLetter(Scanner input) {
		input.useDelimiter(EMPTY_STRING);
		return (input.hasNext("[a-zA-Z]"));
	}
	
	private boolean nextCharIsDigit(Scanner input) {
		input.useDelimiter(EMPTY_STRING);
		return (input.hasNext("[0-9]"));
	}

	private void printCollection(IdentifierCollection collection) {
		out.println(CURLY_BRACKET_OPEN + collection + SPACE + CURLY_BRACKET_CLOSE);
	}
}
