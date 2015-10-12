package assignment2;

import java.io.PrintStream;
import java.util.Scanner;
import java.util.regex.Pattern;

public class Parser {

	private static final char PRINT_STATEMENT_START = '?', COMMENT_START = '/', EQUALS_SIGN = '=', UNION = '+',
			SYMM_DIFFERENCE = '|', COMPLEMENT = '-', INTERSECTION = '*', START_OF_SET = '{', END_OF_SET = '}',
			START_COMPLEX_FACTOR = '(', END_COMPLEX_FACTOR = ')', COMMA = ',';

	private static final String EXC_INPUT_ERROR = "Error on input. ", EMPTY_STRING = "", SPACE = " ",
			EXC_EMPTY_INPUT = "Error, empty input-line. ", EXC_ON_CHAR = "Input error at: ",
			EXC_EXPECTED = "Expected: ", EOLN = "End of line", EXC_INVALID_OPERATOR = "Input error, invalid operator: ",
			LETTER = "Letter (A-Z,a-z).", NATURAL_NUMBER = "Natural number.", NON_ZERO = "Non-zero digit (1-9).",
			EXC_ZERO = "Error: invalid natural number: 0";

	private static final int COMMENT = 0, PRINT = 1, ASSIGN = 2;

	PrintStream out;
	VariableMap<IdentifierInterface, SetInterface<NumberInterface>> varMap;

	private int currentStatement;
	private SetInterface<NumberInterface> currentValue;
	private IdentifierInterface currentKey;

	Parser() {
		out = new PrintStream(System.out);
		varMap = new VariableMap<IdentifierInterface, SetInterface<NumberInterface>>();
	}

	private void program() {
		Scanner in = new Scanner(System.in);

		String inputLine;
		Scanner lineScanner;
		
		while (!eof(in)) {
			inputLine = in.nextLine();

			lineScanner = new Scanner(inputLine);
			lineScanner.useDelimiter(EMPTY_STRING);
			try {
				statement(lineScanner);
				executeOperations();
			} catch (APException e) {
				out.println(e.getMessage());
			}
		}

		in.close();
	}

	private void executeOperations() {
		if(currentStatement == COMMENT){
			// do nothing
		}else if(currentStatement == ASSIGN){
			varMap.add(currentKey, currentValue);
		}else if(currentStatement == PRINT){
			printSet(currentValue);
		}	
	}

	boolean eof(Scanner in) {
		return !in.hasNextLine();
	}

	private void printSet(SetInterface<NumberInterface> set) {
		SetInterface<?> cloneSet = set.clone();
		StringBuffer result = new StringBuffer();

		while (!cloneSet.isEmpty()) {
			result.append(cloneSet.getElement().toString());
			result.append(SPACE);
		}

		out.println(result);
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

	private void statement(Scanner in) throws APException {
		ignoreSpaces(in);

		if (eof(in)) {
			throw new APException(EXC_EMPTY_INPUT);
		}else if (nextCharIs(in, COMMENT_START)) {
			currentStatement = COMMENT;
			comment(in);
		} else if (nextCharIs(in, PRINT_STATEMENT_START)) {
			currentStatement = PRINT;
			printStatement(in);
		} else if (nextCharIsLetter(in)) {
			currentStatement = ASSIGN;
			assignment(in);
		} else {
			throw new APException(EXC_INPUT_ERROR);
		}

		ignoreSpaces(in);
	}

	private void comment(Scanner in) {
		// Skips everything on that line
		in.nextLine();
	}

	private void assignment(Scanner in) throws APException {
		currentKey = identifier(in);
		ignoreSpaces(in);
		character(in, EQUALS_SIGN);
		ignoreSpaces(in);
		currentValue = expression(in);
		ignoreSpaces(in);
		eoln(in);
	}

	private void printStatement(Scanner in) throws APException {
		character(in, PRINT_STATEMENT_START);
		ignoreSpaces(in);
		currentValue = expression(in);
		ignoreSpaces(in);
		eoln(in);
	}

	private void eoln(Scanner in) throws APException {
		if (in.hasNext()) {
			throw new APException(EXC_ON_CHAR + in.next() + SPACE + EXC_EXPECTED + EOLN);
		}
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

	private SetInterface<NumberInterface> expression(Scanner in) throws APException {
		char operator;
		SetInterface<NumberInterface> result, set2;

		result = term(in);
		ignoreSpaces(in);

		while (hasNextAdditiveOperator(in)) {
			operator = additiveOperator(in);
			ignoreSpaces(in);
			set2 = term(in);
			result = doOperationOnSets(operator, result, set2);
			ignoreSpaces(in);
		}

		return result;
	}

	private SetInterface<NumberInterface> term(Scanner in) throws APException {
		SetInterface<NumberInterface> result, set2;

		result = factor(in);
		ignoreSpaces(in);

		while (nextCharIs(in, INTERSECTION)) {
			character(in, INTERSECTION);
			ignoreSpaces(in);

			set2 = factor(in);
			ignoreSpaces(in);
			result = result.intersection(set2);
		}

		return result;
	}

	private char additiveOperator(Scanner in) throws APException {
		if (nextCharIs(in, UNION)) {
			return nextChar(in);
		} else if (nextCharIs(in, COMPLEMENT)) {
			return nextChar(in);
		} else if (nextCharIs(in, SYMM_DIFFERENCE)) {
			return nextChar(in);
		} else {
			throw new APException(EXC_INVALID_OPERATOR + in.next());
		}
	}

	private SetInterface<NumberInterface> factor(Scanner in) throws APException {
		if (nextCharIs(in, START_OF_SET)) {
			return set(in);
		} else if (nextCharIs(in, START_COMPLEX_FACTOR)) {
			return complexFactor(in);
		} else {
			return varMap.getValue(identifier(in));
		}

	}

	private SetInterface<NumberInterface> complexFactor(Scanner in) throws APException {
		SetInterface<NumberInterface> result;

		character(in, START_COMPLEX_FACTOR);
		ignoreSpaces(in);

		result = expression(in);
		ignoreSpaces(in);

		character(in, END_COMPLEX_FACTOR);
		return result;
	}

	private SetInterface<NumberInterface> set(Scanner in) throws APException {
		character(in, START_OF_SET);
		ignoreSpaces(in);

		SetInterface<NumberInterface> result = rowOfNaturalNumbers(in);
		ignoreSpaces(in);

		character(in, END_OF_SET);
		return result;
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

	private SetInterface<NumberInterface> doOperationOnSets(char operator, SetInterface<NumberInterface> set1,
			SetInterface<NumberInterface> set2) {

		switch (operator) {
		case UNION:
			return set1.union(set2);
		case INTERSECTION:
			return set1.intersection(set2);
		case COMPLEMENT:
			return set1.complement(set2);
		case SYMM_DIFFERENCE:
			return set1.symmetricDifference(set2);
		}

		return null;
	}

	private SetInterface<NumberInterface> rowOfNaturalNumbers(Scanner in) throws APException {
		SetInterface<NumberInterface> result = new Set<NumberInterface>();

		if (nextCharIsNotZero(in) || nextCharIsZero(in)) {
			result.add(naturalNumber(in));
			ignoreSpaces(in);

			while (nextCharIs(in, COMMA)) {
				character(in, COMMA);
				ignoreSpaces(in);

				result.add(naturalNumber(in));
				ignoreSpaces(in);
			}
		}

		return result;
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
		} else{
			throw new APException(EXC_INPUT_ERROR);
		}

		return new Number(result.toString());
	}

	private boolean hasNextAdditiveOperator(Scanner in) {
		if (nextCharIs(in, UNION) || nextCharIs(in, COMPLEMENT) || nextCharIs(in, SYMM_DIFFERENCE)) {
			return true;
		} else {
			return false;
		}
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
		new Parser().program();
	}

}
