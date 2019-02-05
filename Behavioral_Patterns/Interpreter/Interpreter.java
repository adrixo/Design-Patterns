//In computer programming, the interpreter pattern is a design pattern that specifies how to evaluate sentences in a language. The basic idea is to have a class for each symbol (terminal or nonterminal) in a specialized computer language. The syntax tree of a sentence in the language is an instance of the composite pattern and is used to evaluate (interpret) the sentence for a client.[1]:243 See also Composite pattern.


//Following the interpreter pattern, we need to implement the interface Expr with a lambda (it can be a class) for each grammar rule.

public class Interpreter {
  @FunctionalInterface
  public interface Expr {
    int interpret(Map<String, Integer> context);

    static Expr number(int number) {
      return context -> number;
    }

    static Expr plus(Expr left, Expr right) {
      return context -> left.interpret(context) + right.interpret(context);
    }

    static Expr minus(Expr left, Expr right) {
      return context -> left.interpret(context) - right.interpret(context);
    }

    static Expr variable(String name) {
      return context -> context.getOrDefault(name, 0);
    }
  }
...
//While the interpreter pattern does not address parsing[1]:247 a parser is provided for completeness.

...
  private static Expr parseToken(String token, ArrayDeque<Expr> stack) {
    Expr left, right;
    switch(token) {
    case "+":
      // it's necessary to remove first the right operand from the stack
      right = stack.pop();
      // ..and then the left one
      left = stack.pop();
      return Expr.plus(left, right);
    case "-":
      right = stack.pop();
      left = stack.pop();
      return Expr.minus(left, right);
    default:
      return Expr.variable(token);
    }
  }
  public static Expr parse(String expression) {
    ArrayDeque<Expr> stack = new ArrayDeque<Expr>();
        for (String token : expression.split(" ")) {
            stack.push(parseToken(token, stack));
        }
        return stack.pop();
  }
...

//Finally evaluating the expression "w x z - +" with w = 5, x = 10, and z = 42.

...
  public static void main(final String[] args) {
    Expr expr = parse("w x z - +");
    Map context = new HashMap();
    context.put("w", 5);
    context.put("x", 10);
    context.put("z", 42);
    int result = expr.interpret(context);
    System.out.println(result);    // -27
  }
}
