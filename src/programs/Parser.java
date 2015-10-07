package programs;

import implementations.*;
import implementations.Number;
import specifications.*;

import java.io.PrintStream;
import java.util.Scanner;
import java.util.regex.Pattern;

public class Parser {

	private static final char PRINT_STATEMENT_START = '?', COMMENT_START = '/',
			EQUALS_SIGN = '=', UNION = '+', SYMM_DIFFERENCE = '|',
			COMPLEMENT = '-', INTERSECTION = '*', CURLY_BRACKET_OPEN = '{',
			CURLY_BRACKET_CLOSE = '}', BRACKET_OPEN = '(', BRACKET_CLOSE = ')',
			COMMA = ',';

	private static final String INPUT_ERROR = "INPUT_ERROR ",
			END_PROGRAM = "PROGRAM TERMINATED", EMPTY_STRING = "", SPACE = " ",
			EXC_EOLN = "No line found", EXC_EMPTY_INPUT = "ERROR: EMPTY INPUT";

	PrintStream out;
	VariableMap<IdentifierInterface, SetInterface<NumberInterface>> varMap;

	Parser() {
		out = new PrintStream(System.out);
		varMap = new VariableMap<IdentifierInterface, SetInterface<NumberInterface>>();
	}

	private void start() {
		Scanner in = new Scanner(System.in);

		String inputLine;
		Scanner lineScanner;

		do {
			try {
				inputLine = in.nextLine();

				lineScanner = new Scanner(inputLine);
				lineScanner.useDelimiter(EMPTY_STRING);

				program(lineScanner);
			} catch (Exception e) {
				endProgram(e);
				out.println(e.getMessage());
			}
		} while(true);
	}

	private void printSet(SetInterface<NumberInterface> set) {
		SetInterface<?> cloneSet = set.clone();
		StringBuffer result = new StringBuffer();

		result.append(CURLY_BRACKET_OPEN);
		while (!cloneSet.isEmpty()) {
			result.append(cloneSet.getElement().toString());
			if (!cloneSet.isEmpty()) {
				result.append(COMMA);
			}
		}
		result.append(CURLY_BRACKET_CLOSE);

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

	private void program(Scanner in) throws APException {
		if (!in.hasNext()) {
			throw new APException(EXC_EMPTY_INPUT);
		}
		statement(in);
	}

	private void statement(Scanner in) throws APException {
		ignoreSpaces(in);
		if (nextCharIs(in, COMMENT_START)) {
			comment(in);
		} else if (nextCharIs(in, PRINT_STATEMENT_START)) {
			printStatement(in);
		} else {// if check than an else throws an error example question mark
			assignment(in);
		}
		ignoreSpaces(in);
	}

	private void comment(Scanner in) {
		// Skips everything on that line
		in.nextLine();
	}

	private void assignment(Scanner in) throws APException {
		Identifier variableIdentifier = identifier(in);
		ignoreSpaces(in);
		character(in, EQUALS_SIGN);
		ignoreSpaces(in);
		SetInterface<NumberInterface> variableValue = expression(in);
		varMap.add(variableIdentifier, variableValue);
		ignoreSpaces(in);
		eoln(in);
	}

	private void printStatement(Scanner in) throws APException {
		character(in, PRINT_STATEMENT_START);
		ignoreSpaces(in);
		printSet(expression(in));
		ignoreSpaces(in);
		eoln(in);
	}

	private void eoln(Scanner in) throws APException {
		if (in.hasNext()) {
			throw new APException(INPUT_ERROR);
		}
	}

	private Identifier identifier(Scanner in) throws APException {
		StringBuffer buffer = new StringBuffer();
		buffer.append(letter(in));
		while (nextCharIsLetter(in) || nextCharIsNumber(in)) {
			if (nextCharIsLetter(in)) {
				buffer.append(letter(in));
			} else if (nextCharIsNumber(in)) {
				buffer.append(naturalNumber(in));
			}
		}

		return new Identifier(buffer.toString());
	}

	private SetInterface<NumberInterface> expression(Scanner in)
			throws APException {
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
			throw new APException(INPUT_ERROR);
		}
	}

	private SetInterface<NumberInterface> factor(Scanner in) throws APException {
		if (nextCharIs(in, CURLY_BRACKET_OPEN)) {
			return set(in);
		} else if (nextCharIs(in, BRACKET_OPEN)) {
			return complexFactor(in);
		} else {
			return varMap.getValue(identifier(in));
		}

	}

	private SetInterface<NumberInterface> complexFactor(Scanner in)
			throws APException {
		SetInterface<NumberInterface> result;

		character(in, BRACKET_OPEN);
		ignoreSpaces(in);

		result = expression(in);
		ignoreSpaces(in);

		character(in, BRACKET_CLOSE);
		return result;
	}

	private SetInterface<NumberInterface> set(Scanner in) throws APException {
		character(in, CURLY_BRACKET_OPEN);
		ignoreSpaces(in);

		SetInterface<NumberInterface> result = rowOfNaturalNumbers(in);
		ignoreSpaces(in);

		character(in, CURLY_BRACKET_CLOSE);
		return result;
	}

	private char letter(Scanner in) throws APException {
		if (nextCharIsLetter(in)) {
			return nextChar(in);
		} else {
			throw new APException(INPUT_ERROR);
		}
	}

	private NumberInterface naturalNumber(Scanner in) throws APException {
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
		} else {
			throw new APException(INPUT_ERROR);
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
		} else {
			throw new APException(INPUT_ERROR);
		}
	}

	private char zero(Scanner in) throws APException {
		return character(in, '0');
	}

	private boolean nextCharIsZero(Scanner in) {
		return nextCharIs(in, '0');
	}

	private SetInterface<NumberInterface> doOperationOnSets(char operator,
			SetInterface<NumberInterface> set1,
			SetInterface<NumberInterface> set2) {
		if (operator == UNION) {
			return set1.union(set2);
		} else if (operator == INTERSECTION) {
			return set1.intersection(set2);
		} else if (operator == COMPLEMENT) {
			return set1.complement(set2);
		} else if (operator == SYMM_DIFFERENCE) {
			return set1.symmetricDifference(set2);
		} else {
			return null;
		}
	}

	private SetInterface<NumberInterface> rowOfNaturalNumbers(Scanner in)
			throws APException {
		SetInterface<NumberInterface> result = new Set<NumberInterface>();

		if (nextCharIsNumber(in)) {
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

	private boolean hasNextAdditiveOperator(Scanner in) {
		if (nextCharIs(in, UNION) || nextCharIs(in, COMPLEMENT)
				|| nextCharIs(in, SYMM_DIFFERENCE)) {
			return true;
		} else {
			return false;
		}
	}

	private char character(Scanner in, char c) throws APException {
		if (!nextCharIs(in, c)) {
			throw new APException(INPUT_ERROR);
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

	private void endProgram(Exception e) {
		// Checks if ctrl-d/ctrl-z has been pressed causing the console to close

		if (e.getMessage().equals(EXC_EOLN)) {
			out.println();
			out.println(END_PROGRAM);

			System.exit(1);
		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new Parser().start();
	}

}
