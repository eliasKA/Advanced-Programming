package assignment3;

import java.io.PrintStream;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;

import assignment2.APException;

public class Parser {

	//
	

	private static final String EXC_INPUT_ERROR = "Error on input. ", EMPTY_STRING = "", SPACE = " ",
			EXC_EMPTY_INPUT = "Error, empty input-line. ", EXC_ON_CHAR = "Input error at: ",
			EXC_EXPECTED = "Expected: ", EOLN = "End of line", EXC_INVALID_OPERATOR = "Input error, invalid operator: ",
			LETTER = "Letter (A-Z,a-z).", NATURAL_NUMBER = "Natural number.", NON_ZERO = "Non-zero digit (1-9).",COMMA=";",
			EXC_ZERO = "Error: invalid natural number: 0";
	//

	private static final String ARGS_INPUT_FORMAT = "Inputformat: [ commandLineOptions ] files", ERROR = "Error: ",
			DEC_OPTION = "-d", CASE_OPTION = "-i";
	private static final char START_OPTION = '-', BRACKET_OPEN = '[', BRACKET_CLOSE = ']';

	PrintStream out;
	CountMap<IdentifierInterface> countMap;
	String argsString;
	BSTreeInterface<IdentifierInterface> tree;
	boolean optionCas, optionDec; // Should this be switches?

	Parser() {
		out = new PrintStream(System.out);
		countMap = new CountMap<IdentifierInterface>();

		optionCas = optionDec = false;
	}

	private void start(String[] args) {
		try {
			argsString = args.toString();
			Scanner in = new Scanner(argsString);
			parseArguments(in.useDelimiter(EMPTY_STRING));
		} catch (APException e) {
			out.println(e.getMessage());
		}
	}

	private void parseArguments(Scanner in) throws APException {
		character(in, BRACKET_OPEN);

		if (nextCharIs(in, START_OPTION)) {
			commandLineOptions(in);
		}
		character(in, COMMA);

		files(in,"-i");

		character(in, BRACKET_CLOSE);
	}

	private void files(Scanner in,String option) {
	
	IdentifierInterface identifier = new Identifier("a");
		if (tree.contains(identifier,"-i")) {
			countMap.add(identifier);

		} else {
			tree.add(identifier);
			countMap.add(identifier);
		}

	}

	private void printResutl(String option){
		IdentifierInterface identifier = new Identifier("a");
		Iterator<IdentifierInterface> treeIterator;
		if(countMap.getCount(identifier)%2==0){
			tree.remove(identifier);
			
		}
		if(option.equals("-d")){
			
			treeIterator = tree.clone().descendingIterator();	
		}
		else{
			treeIterator=tree.clone().ascendingIterator();
		}
		
	
	
	
	
	
	
	}
	private void commandLineOptions(Scanner in) {
		// todo
	}

	private char character(Scanner in, char c) throws APException {
		if (!in.hasNext()) {
			throw new APException(EXC_INPUT_ERROR + EXC_EXPECTED + c);
		} else if (!nextCharIs(in, c)) {
			throw new APException(EXC_ON_CHAR + in.next() + SPACE + EXC_EXPECTED + c);
		} else {
			return nextChar(in);
		}
	}

	private char nextChar(Scanner in) {
		return in.next().charAt(0);
	}

	private boolean nextCharIs(Scanner in, char c) {
		return in.hasNext(Pattern.quote(c + ""));
	}

	public static void main(String[] args) {
		new Parser().start(args);
	}
}
