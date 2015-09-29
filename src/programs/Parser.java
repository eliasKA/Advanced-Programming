package programs;

import implementations.*;
import implementations.Number;
import specifications.NumberInterface;

import java.io.PrintStream;
import java.util.Scanner;
import java.util.regex.Pattern;

/*
 * QUESTIONS
 * 
 * - how to input multi-line
 * - how to read everythin input
 * - how to program eof
 * - line 224
 * - how to program generic classes
 * - Variable extends DATA ?? it does not have to be comparable
 * 
 * 
 */

public class Parser {

	private static final char PRINT_STATEMENT_START = '?', COMMENT_START = '/',
			EQUALS_SIGN = '=';

	private static final String INPUT_ERROR = "INPUT_ERROR";

	private static final char UNION = '+', SYMM_DIFFERENCE = '|',
			COMPLEMENT = '-', INTERSECTION = '*', CURLY_BRACKET_OPEN = '{',
			CURLY_BRACKET_CLOSE = '}', BRACKET_OPEN = '(', BRACKET_CLOSE = ')', COMMA = ',';

	PrintStream out;
	VariableMap varMap;

	Parser() {
		out = new PrintStream(System.out);
		varMap = new VariableMap();
	}

	private void start() {
		Scanner in = new Scanner(System.in);
		in.useDelimiter("");

		try {
			program(in);
		} catch (APException e) {
			out.println(e.getMessage());
		}
	}

	
	private void program(Scanner in) throws APException {
		while (!eof(in)) {
			statement(in);
		}
	}

	private void statement(Scanner in) throws APException {
		if (nextCharIs(in, COMMENT_START)) {
			comment(in);
		} else if (nextCharIs(in, PRINT_STATEMENT_START)) {
			printStatement(in);
		} else {//if check  than an else throws an error example question mark 
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

		Set variableValue = expression(in);

		varMap.add(new Variable(variableIdentifier, variableValue));

		in.nextLine();
	}

	private void printStatement(Scanner in) throws APException {
		out.println(expression(in).toString());
		in.nextLine();
	}

	
	private Identifier identifier(Scanner in) throws APException {
		StringBuffer buffer = new StringBuffer();

		buffer.append(letter(in));

		while (nextCharIsLetter(in) || nextCharIsNaturalNumber(in)) {
			if (nextCharIsLetter(in)) {
				buffer.append(letter(in));
			} else if (nextCharIsNaturalNumber(in)) {
				buffer.append(naturalNumber(in));
			}
		}

		return new Identifier(buffer.toString());
	}

	private Set expression(Scanner in) throws APException {
		char operator;
		Set result, set2;

		result = term(in);

		while (hasNextAdditiveOperator(in)) {
			operator = additiveOperator(in);
			set2 = term(in);
			result = doOperationOnSets(operator, result, set2);
		}

		return result;
	}	

	
	private Set<NumberInterface> term(Scanner in) throws APException {
		Set<NumberInterface> result, set2;

		result = factor(in);
//
		while (nextCharIs(in, INTERSECTION)) {
			character(in, INTERSECTION);
			set2 = factor(in);

			result = (Set) result.intersection(set2);
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
	
	
	private Set factor(Scanner in) throws APException {
		if (nextCharIs(in, CURLY_BRACKET_OPEN)) {
			return set(in);
		}else if (nextCharIs(in, BRACKET_OPEN)){
			return complexFactor(in);
		}else{
			return (Set) varMap.getVariable(identifier(in));
		}
	}
	
	
	private Set complexFactor(Scanner in) throws APException {
		Set result;
		
		character(in, BRACKET_OPEN);
		result = expression(in);
		character(in, BRACKET_CLOSE);
		
		return result;
	}
	
	private Set set(Scanner in) throws APException {
		character(in,CURLY_BRACKET_OPEN);
		
		Set result = rowOfNaturalNumbers(in);
		
		character(in,CURLY_BRACKET_CLOSE);
		
		return result;
	}

	
	private char letter(Scanner in) throws APException {
		if (nextCharIsLetter(in)) {
			return nextChar(in);
		} else {
			throw new APException(INPUT_ERROR);
		}
	}
	
	private String naturalNumber(Scanner in) throws APException {
		StringBuffer result = new StringBuffer();
		
		result.append(notZero(in));
		
		while(nextCharIsNumber(in)){
			result.append(number(in));
		}
		
		return result.toString();
	}
	
	private boolean nextCharIsNumber(Scanner in) {
		return nextCharIsZero(in)||nextCharIsNotZero(in);
	}

	private char number(Scanner in ) throws APException{
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

	private boolean nextCharIsNaturalNumber(Scanner in) {
		return nextCharIsNotZero(in) || nextCharIsZero(in);
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
		return (nextCharIs(in, '0'));
	}

	private Set doOperationOnSets(char operator, Set set1, Set set2) {
		if (operator == UNION) {
			return (Set) set1.union(set2);
		} else if (operator == INTERSECTION) {
			return (Set) set1.intersection(set2);
		} else if (operator == COMPLEMENT) {
			return (Set) set1.difference(set2);
		} else if (operator == SYMM_DIFFERENCE) {
			return (Set) set1.complement(set2);
		} else {
			return null;
		}
	}
	
	private Set rowOfNaturalNumbers(Scanner in) throws APException {
		Set result = new Set();
		
		if(nextCharIsNaturalNumber(in)){
			naturalNumber(in);
			while(nextCharIs(in, COMMA)){
				Character c = character(in, COMMA);
				//result.add(new Number(c.toString()));
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

	private boolean eof(Scanner in) {
		return in.hasNext();
	}

	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new Parser().start();
	}

}
