import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Scanner;

/**
 * @author Anton Solovev
 * @since 25.10.16.
 */
public class Calculator {

	private static class Operators {
		static final String PLUS = "+";
		static final String MINUS = "-";
		static final String DIVIDE = "/";
		static final String MULTIPLY = "*";
	}

	public int calculate(String line) {
		String expression = compile(line);
		return postfixEvaluate(expression);
	}

	public String compile(String line) {
		return infixToPostfix(line);
	}

	/**
	 * @param exp proper postfix format such as "2 3 - 4 *", consists only of integers, +, -, *, \/.
	 * @return evaluated int.
	 */
	public int postfixEvaluate(String exp) {
		Deque<Integer> operands = new ArrayDeque<>();
		Scanner tokens = new Scanner(exp);

		while (tokens.hasNext()) {
			if (tokens.hasNextInt()) {
				operands.push(tokens.nextInt());
			} else {
				int num2 = operands.pop();
				int num1 = operands.pop();

				switch (tokens.next()) {
					case Operators.PLUS:
						operands.push(num1 + num2);
						break;
					case Operators.MINUS:
						operands.push(num1 - num2);
						break;
					case Operators.MULTIPLY:
						operands.push(num1 * num2);
						break;
					case Operators.DIVIDE:
						operands.push(num1 / num2);
						break;
					default:
						throw new RuntimeException("this operand: " + tokens.next() + " is not correct");
				}
			}
		}
		return operands.pop();
	}

	//function that converts from infix to postfix notation
	public static String infixToPostfix(String line) {
		Deque<String> operators = new ArrayDeque<>();
		StringBuilder postfixBuilder = new StringBuilder();

		String tokens[] = line.split(" ");

		for (String t : tokens) {
			if (isOperator(t)) {
				while (!operators.isEmpty() && isPriorityHigher(operators.peek(), t)) {
					append(postfixBuilder, operators.pop());
				}
				operators.push(t);
			} else {
				append(postfixBuilder, t);
			}
		}
		while (!operators.isEmpty()) {
			append(postfixBuilder, operators.pop());
		}
		return postfixBuilder.toString().trim();
	}

	private static boolean isPriorityHigher(String previousOperator, String operator) {
		return getOperatorPriority(previousOperator) >= getOperatorPriority(operator);
	}

	private static void append(StringBuilder postfixBuilder, String s) {
		postfixBuilder.append(String.format(" %s", s));
	}

	//function that tests if the parameter is an operator
	private static boolean isOperator(String operator) {
		return operator.equals(Operators.PLUS) || operator.equals(Operators.MINUS)
				|| operator.equals(Operators.MULTIPLY) || operator.equals(Operators.DIVIDE);
	}

	//function that returns the priority level of the passed operator parameter
	private static int getOperatorPriority(String operator) {
		int retval = 0;
		if (operator.equals(Operators.PLUS) || operator.equals(Operators.MINUS)) {
			retval = 1;
		} else if (operator.equals(Operators.MULTIPLY) || operator.equals(Operators.DIVIDE)) {
			retval = 2;
		}
		return retval;
	}
}