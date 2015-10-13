package assignment3;

import java.io.PrintStream;
import java.util.Iterator;
import java.util.Scanner;
import java.util.regex.Pattern;

import assignment2.Number;
import assignment2.NumberInterface;

public class mainTest {
	private static final char PRINT_STATEMENT_START = '?', COMMENT_START = '/', EQUALS_SIGN = '=', UNION = '+',
			SYMM_DIFFERENCE = '|', COMPLEMENT = '-', INTERSECTION = '*', START_OF_SET = '{', END_OF_SET = '}',
			START_COMPLEX_FACTOR = '(', END_COMPLEX_FACTOR = ')';

	private static final String EXC_INPUT_ERROR = "Error on input. ", EMPTY_STRING = "", SPACE = " ", COMMA = ",",

			EXC_EMPTY_INPUT = "Error, empty input-line. ", EXC_ON_CHAR = "Input error at: ",
			EXC_EXPECTED = "Expected: ", EOLN = "End of line", EXC_INVALID_OPERATOR = "Input error, invalid operator: ",
			LETTER = "Letter (A-Z,a-z).", NATURAL_NUMBER = "Natural number.", NON_ZERO = "Non-zero digit (1-9).",
			EXC_ZERO = "Error: invalid natural number: 0";

	private static final int COMMENT = 0, PRINT = 1, ASSIGN = 2;
	static String input, output, option;
	PrintStream out;
	BSTreeInterface<IdentifierInterface> binaryTree;

	mainTest() {
		out = new PrintStream(System.out);
		binaryTree = new BSTree<IdentifierInterface>();
	}

	void test() {
		BSTree<IdentifierInterface> tree = new BSTree<IdentifierInterface>();

		Identifier a = new Identifier("a");
		Identifier b = new Identifier("b");
		Identifier c = new Identifier("c");
		Identifier d = new Identifier("d");
		Identifier x = new Identifier("x");
		Identifier y = new Identifier("y");
		Identifier z = new Identifier("z");

		tree.add(y);
		tree.add(d);
		tree.add(b);
		tree.add(x);
		tree.add(c);
		tree.add(a);
		tree.add(z);
		tree.add(a);

		Iterator<IdentifierInterface> up = tree.clone().ascendingIterator();
		Iterator<IdentifierInterface> down = tree.clone().descendingIterator();

		while (up.hasNext()) {
			out.print(up.next() + "-");
		}

		out.println();

		while (down.hasNext()) {
			out.print(down.next() + "-");
		}
		out.println();

		if (!tree.contains(new Identifier("d"),"-i"))
			out.println("not there");
		if (tree.contains(new Identifier("a"),"-i"))
			out.println(" there");
	}

	static void parseArguments(String[] args) {
		if (args.length == 0) {
			System.out.println("Please provide at least one arguments:<files>");
			System.out.println("    <input file> <options> <heap type>");
			System.out.println("where <heap type> is 'b' iff the heap should be binary");
			System.out.println("or 't' iff the heap should be ternary.");
			System.exit(1);
		}

		input = args[0];
		output = args[1];

	}

	private void program(String[] args) {
		
		parseArguments(args);
		Scanner in = new Scanner(System.in);

		String inputLine;
		
	}

	boolean eof(Scanner in) {
		return !in.hasNextLine();
	}

	private boolean ignoreSpaces(Scanner input) {
		boolean result = false;

		while (input.hasNext(SPACE)) {
			// Skips all the spaces and returns whether there were any spaces
			input.next();
			result = true;
		}

		return result;
	}

	private void textParser(Scanner in) throws APException {
		in.useDelimiter(COMMA);
		String sentence;
		Scanner lineScanner;

		while (!eof(in)) {
			sentence = in.nextLine();

			lineScanner = new Scanner(sentence);
			
			try {
				sentenceParser(lineScanner);

			} catch (APException e) {
				out.println(e.getMessage());
			}
		}

		in.close();

	}

	private void sentenceParser(Scanner in) throws APException {
		in.useDelimiter(SPACE);
		String word;
		Scanner wordScanner;
		while(in.hasNext()){
			word=in.next();
			wordScanner=new Scanner(word);
			wordParser(wordScanner);
			
		}

		
	}
	private void wordParser(Scanner in){
		
		
	
	}

	private void comment(Scanner in) {
		// Skips everything on that line
		in.nextLine();
	}

	private Identifier identifier(Scanner in) throws APException {
		StringBuffer buffer = new StringBuffer();
		buffer.append(letter(in));
		while (nextCharIsLetter(in) || nextCharIsNumber(in)) {
			if (nextCharIsLetter(in)) {
				buffer.append(letter(in));
			} else if (nextCharIsNumber(in)) {
				buffer.append(positiveNumber(in));
			}
		}

		return new Identifier(buffer.toString());
	}

	private char letter(Scanner in) throws APException {
		if (nextCharIsLetter(in)) {
			return nextChar(in);
		} else if (in.hasNext()) {
			throw new APException(EXC_ON_CHAR + in.next() + SPACE + EXC_EXPECTED + LETTER);
		} else {
			throw new APException(EXC_INPUT_ERROR + EXC_EXPECTED + LETTER);
		}
	}

	private NumberInterface positiveNumber(Scanner in) throws APException {
		StringBuffer result = new StringBuffer();

		result.append(notZero(in));

		while (nextCharIsNumber(in)) {
			result.append(number(in));
		}

		return new Number(result.toString());
	}

	private boolean nextCharIsNumber(Scanner in) {
		return nextCharIsZero(in) || nextCharIsNotZero(in);
	}

	private char number(Scanner in) throws APException {
		if (nextCharIsZero(in)) {
			return zero(in);
		} else if (nextCharIsNotZero(in)) {
			return notZero(in);
		} else if (in.hasNext()) {
			throw new APException(EXC_ON_CHAR + in.next() + SPACE + EXC_EXPECTED + NATURAL_NUMBER);
		} else {
			throw new APException(EXC_INPUT_ERROR + EXC_EXPECTED + NATURAL_NUMBER);
		}
	}

	private boolean nextCharIsLetter(Scanner in) {
		return (in.hasNext("[a-zA-Z]"));
	}

	private boolean nextCharIsNotZero(Scanner in) {
		return (in.hasNext("[1-9]"));
	}

	private char notZero(Scanner in) throws APException {
		if (nextCharIsNotZero(in)) {
			return nextChar(in);
		} else if (in.hasNext()) {
			throw new APException(EXC_ON_CHAR + in.next() + SPACE + EXC_EXPECTED + NON_ZERO);
		} else {
			throw new APException(EXC_INPUT_ERROR + EXC_EXPECTED + NON_ZERO);
		}
	}

	private char zero(Scanner in) throws APException {
		return character(in, '0');
	}

	private boolean nextCharIsZero(Scanner in) {
		return nextCharIs(in, '0');
	}

	private NumberInterface naturalNumber(Scanner in) throws APException {
		StringBuffer result = new StringBuffer();

		if (nextCharIsZero(in)) {
			result.append(nextChar(in));
			if (nextCharIsNotZero(in) || nextCharIsZero(in)) {
				throw new APException(EXC_ZERO + nextChar(in));
			}
		} else if (nextCharIsNotZero(in)) {
			result.append(nextChar(in));
			while (nextCharIsNotZero(in) || nextCharIsZero(in)) {
				result.append(nextChar(in));
			}
		}

		return new Number(result.toString());
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
		// TODO Auto-generated method stub
		new mainTest().test();
	}

}
