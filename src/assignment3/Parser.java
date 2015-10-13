package assignment3;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;

import java.util.Iterator;
import java.util.Scanner;

public class Parser {

	private static final String EXC_INPUT_ERROR = "Error on input. ", EMPTY_STRING = "", EXC_EXPECTED = "Expected: ",
			LETTER = "Letter (A-Z,a-z).";

	private static final String ARGS_INPUT_FORMAT = "Inputformat: [ commandLineOptions ] files", DEC_OPTION = "-d",
			CASE_OPTION = "-i";

	PrintStream out;

	BSTreeInterface<IdentifierInterface> tree;
	boolean optionCas, optionDec; // Should this be switches?

	Parser() {
		out = new PrintStream(System.out);

		tree = new BSTree<IdentifierInterface>();
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

	private Iterator<IdentifierInterface> getIterator() {
		if (optionDec)
			return tree.descendingIterator();
		return tree.ascendingIterator();
	}

	private void printResult() {

		Iterator<IdentifierInterface> iterator = getIterator();
		if (!iterator.hasNext()) {
			return;
		}

		IdentifierInterface previous = iterator.next();
		IdentifierInterface current;

		int counter = 1;

		while (iterator.hasNext()) {
			current = iterator.next();
			if (current.equals(previous)) {
				counter += 1;
			} else {
				if (!isEven(counter)) {
					out.println(previous);
				}
				previous = current;
				counter = 1;
			}
		}

		if (!isEven(counter)) {
			out.println(previous);
		}
	}

	boolean isEven(int number) {
		return number % 2 == 0;
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

		if (option.equals(DEC_OPTION))
			optionDec = true;
		else if (option.equals(CASE_OPTION))
			optionCas = true;

	}

	private void readFile(String file) throws APException {

		try {
			Scanner fileScanner = new Scanner(new File(file));
			fileScanner.useDelimiter(EMPTY_STRING);
			parseFile(fileScanner);

			fileScanner.close();
		} catch (FileNotFoundException e) {
			System.out.println("Unable to find file '" + file + "'.");
			System.exit(1);
		}

	}

	private void parseFile(Scanner in) throws APException {
		while (in.hasNext()) {
			if (nextCharIsDelimiter(in)) {
				delimiter(in);
			} else if (nextCharIsDigit(in) || nextCharIsLetter(in)) {
				variable(in);
			}
		}

	}

	private void variable(Scanner in) throws APException {
		if (nextCharIsLetter(in)) {
			tree.add(identifier(in, optionCas));
		} else if (nextCharIsDigit(in))
			nonIdentifier(in);
	}

	private void delimiter(Scanner in) {
		if (nextCharIsDelimiter(in))
			in.next();

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

		if (optionCas) {
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
