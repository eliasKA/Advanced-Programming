package programs;

import implementations.*;
import implementations.Number;
//Why explicitly import Number ? 
import specifications.*;

import java.io.PrintStream;
import java.util.Scanner;
import java.util.regex.Pattern;

public class Parser {

	private static final char PRINT_STATEMENT_START = '?', COMMENT_START = '/',
			EQUALS_SIGN = '=';

	private static final String INPUT_ERROR = "INPUT_ERROR";

	private static final char UNION = '+', SYMM_DIFFERENCE = '|',
			COMPLEMENT = '-', INTERSECTION = '*', CURLY_BRACKET_OPEN = '{',
			CURLY_BRACKET_CLOSE = '}', BRACKET_OPEN = '(', BRACKET_CLOSE = ')',
			COMMA = ',';

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

		while (true) {
			try {
				inputLine = in.nextLine();
				
				lineScanner = new Scanner(inputLine);
				lineScanner.useDelimiter("");
				
				program(lineScanner);
			} catch (APException e) {
				out.println(e.getMessage());
			}
		}
	}

	private void program(Scanner in) throws APException {
			statement(in);
	}

	private void statement(Scanner in) throws APException {
		if (nextCharIs(in, COMMENT_START)) {
			comment(in);
		} else if (nextCharIs(in, PRINT_STATEMENT_START)) {
			printStatement(in);
		} else {// if check than an else throws an error example question mark
			assignment(in);
		}
	}

	private void comment(Scanner in) {
		// Skips everything on that line
		in.nextLine();
	}

	private void assignment(Scanner in) throws APException {
		Identifier variableIdentifier = identifier(in);
		character(in, EQUALS_SIGN);
		SetInterface<NumberInterface> variableValue = expression(in);
		
		varMap.add(variableIdentifier, variableValue);
		eoln(in);
	}

	private void printStatement(Scanner in) throws APException {
		out.println(expression(in).toString());
		eoln(in);
	}

	private void eoln(Scanner in) throws APException {
		if(in.hasNext()){
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

	private SetInterface<NumberInterface> expression(Scanner in) throws APException {
		char operator;
		SetInterface<NumberInterface> result, set2;

		result = term(in);

		while (hasNextAdditiveOperator(in)) {
			operator = additiveOperator(in);
			set2 = term(in);
			result = doOperationOnSets(operator, result, set2);
		}

		return result;
	}

	private SetInterface<NumberInterface> term(Scanner in) throws APException {
		SetInterface<NumberInterface> result, set2;

		result = factor(in);

		while (nextCharIs(in, INTERSECTION)) {
			character(in, INTERSECTION);
			set2 = factor(in);

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

	private SetInterface<NumberInterface> complexFactor(Scanner in) throws APException {
		SetInterface<NumberInterface> result;

		character(in, BRACKET_OPEN);
		result = expression(in);
		character(in, BRACKET_CLOSE);

		return result;
	}

	private SetInterface<NumberInterface> set(Scanner in) throws APException {
		character(in, CURLY_BRACKET_OPEN);

		SetInterface<NumberInterface> result = rowOfNaturalNumbers(in);

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

	private SetInterface<NumberInterface> doOperationOnSets(char operator, SetInterface<NumberInterface> set1, SetInterface<NumberInterface> set2) {
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

	private SetInterface<NumberInterface> rowOfNaturalNumbers(Scanner in) throws APException {
		SetInterface<NumberInterface> result = new Set<NumberInterface>();
		
		if(nextCharIsNumber(in)) {
			result.add(naturalNumber(in));
			while (nextCharIs(in, COMMA)) {
				character(in, COMMA);
				result.add(naturalNumber(in));
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

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new Parser().start();
	}

}
