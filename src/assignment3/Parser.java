package assignment3;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;

public class Parser {

	//

	private static final String EXC_INPUT_ERROR = "Error on input. ", EMPTY_STRING = "", SPACE = " ",
			EXC_EMPTY_INPUT = "Error, empty input-line. ", EXC_ON_CHAR = "Input error at: ",
			EXC_EXPECTED = "Expected: ", EOLN = "End of line", EXC_INVALID_OPERATOR = "Input error, invalid operator: ",
			LETTER = "Letter (A-Z,a-z).", NATURAL_NUMBER = "Natural number.", NON_ZERO = "Non-zero digit (1-9).",
			COMMA = ";", EXC_ZERO = "Error: invalid natural number: 0";
	//

	private static final String ARGS_INPUT_FORMAT = "Inputformat: [ commandLineOptions ] files", ERROR = "Error: ",
			DEC_OPTION = "d", CASE_OPTION = "i", ARGUMENT_ERROR = " ";
	private static final char START_OPTION = '-', BRACKET_OPEN = '[', BRACKET_CLOSE = ']';

	PrintStream out;
	
	BSTreeInterface<IdentifierInterface> tree;
	boolean optionCas, optionDec; // Should this be switches?

	Parser() {
		out = new PrintStream(System.out);
		
		tree=new BSTree<IdentifierInterface>();
		optionCas = optionDec = false;
	}

	private void start(String[] args) {
		try {
			parseArguments(args);
			printResult();
		} catch (APException e) {
			out.println(e.getMessage());
		}
	}

	private void printResult() {
		Iterator<IdentifierInterface> it = tree.ascendingIterator();
		while(it.hasNext()){
			out.println(it.next().toString());
			
		}

	}
	
	private void parseArguments(String[] args) throws APException {
		int numberOfFiles = 0;
		for (int i = 0; i < args.length; i++) {
			if (args[i].startsWith("-") && numberOfFiles == 0) {
				setOption(args[i]);
			} else {
				numberOfFiles += 1;
				readFile(args[i]);
			}
		}

		if (args.length == 0 || numberOfFiles == 0) {
			throw new APException(ARGS_INPUT_FORMAT);
		}

	}

	private void setOption(String option) {
		switch (option) {
		case "-d":
			optionDec = true;
			break;
		case "-i":
			optionCas = true;
			break;
		}
	}

	private void readFile(String file) throws APException {

		try {
			Scanner fileScanner = new Scanner(new File(file));
			out.println(fileScanner.nextLine());
			
			//parseFile(fileScanner.useDelimiter(EMPTY_STRING));
			//fileScanner.close();
		} catch (FileNotFoundException e) {
			System.out.println("Unable to find file '" + file + "'.");
			System.exit(1);
		}

	}

	private void parseFile(Scanner in) throws APException {
		rowOfDelimiters(in);
		rowOfVariables(in);

	}

	private void rowOfVariables(Scanner in) throws APException {
		if (nextCharIsLetter(in) || nextCharIsDigit(in)) {
			variable(in);
			while (nextCharIsDelimiter(in)) {
				rowOfDelimiters(in);
				if (nextCharIsLetter(in) || nextCharIsDigit(in)) {
					variable(in);
				}
			}

		}

	}
	
	private void variable(Scanner in) throws APException {
		if (nextCharIsLetter(in)){
			//out.println(identifier(in, optionCas).toString());}
			tree.add(identifier(in, optionCas));}
		else if (nextCharIsDigit(in))
			nonIdentifier(in);
	}
	
	private void rowOfDelimiters(Scanner in) throws APException {
		delimiter(in);
		while (nextCharIsDelimiter(in)) {
			delimiter(in);
		}
	}

	private void delimiter(Scanner in) throws APException {
		if (nextCharIsDelimiter(in)) {
			in.next();
		} else {
			throw new APException("Delimiter");
		}
	}

	private void nonIdentifier(Scanner in) throws APException {
		digit(in);
		while (nextCharIsLetter(in) || nextCharIsDigit(in)) {
			if (nextCharIsLetter(in)) {
				letter(in);
			} else if (nextCharIsDigit(in)) {
				digit(in);
			}
		}
	}

	private IdentifierInterface identifier(Scanner in, boolean optionCas) throws APException {
		StringBuffer buffer = new StringBuffer();
		buffer.append(letter(in));
		while (nextCharIsLetter(in) || nextCharIsDigit(in)) {
			if (nextCharIsLetter(in)) {
				buffer.append(letter(in));
			} else if (nextCharIsDigit(in)) {
				buffer.append(digit(in));
			}
		}

		if(optionCas){
			return new Identifier(buffer.toString().toLowerCase());
		}
		return new Identifier(buffer.toString());
	}

	private char digit(Scanner in) throws APException {
		if (nextCharIsDigit(in)) {
			return nextChar(in);
		} else {
			throw new APException("Digit ");
		}
	}

	private char letter(Scanner in) throws APException {
		if (nextCharIsLetter(in)) {
			return nextChar(in);
		} else {
			throw new APException(EXC_INPUT_ERROR + EXC_EXPECTED + LETTER);
		}
	}

	private boolean nextCharIsLetter(Scanner in) {
		return (in.hasNext("[a-zA-Z]"));
	}

	private boolean nextCharIsDigit(Scanner in) {
		return (in.hasNext("[0-9]"));
	}

	boolean nextCharIsDelimiter(Scanner in) {
		return !nextCharIsLetter(in) && !nextCharIsDigit(in);
	}

	boolean eof(Scanner in) {
		return !in.hasNextLine();
	}

	private char nextChar(Scanner in) {
		return in.next().charAt(0);
	}

	public static void main(String[] args) {
		new Parser().start(args);
	}
}
