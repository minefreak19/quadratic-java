package me.minefreak19.quadraticjava.parsing;

import me.minefreak19.quadraticjava.parsing.expr.*;

import java.util.List;

public record Parser(List<Token> tokens) {
    
    private static <T> T shift(List<T> ts) {
        return ts.remove(0);
    }
    
    private static boolean isParen(String text) {
        return text.equals("(") || text.equals(")");
    }
    
    /**
     * Primary parser in the recursive descent algorithm.
     * Parses a single operand to an operation
     * (ie a number, identifier, or parenthesized full expression).
     *
     * Since unary operators are handled here,
     * they have a higher precedence than all binary operators.
     *
     * By parsing parentheses along with the operands themselves,
     * their behaviour regarding precedence gets handled automatically.
     * @return An expression that acts as an operand to a larger operation.
     *
     * @see #parse(int)
     */
    private Expr parsePrimary() {
        var token = shift(tokens);
        switch (token.text()) {
            case "-" -> {
                return new UnaryExpr(parsePrimary(), x -> -x, "NEG");
            }
            
            case ")" -> throw new SyntaxException("Unexpected closing paren");
            
            case "(" -> {
                Expr result = parse();
                Token closeParen = shift(tokens);
                if (!closeParen.text().equals(")")) {
                    throw new SyntaxException("Expected `)` but got " + closeParen);
                }
                return result;
            }
            
            default -> {
                // if we're here, it must be a number literal or identifier
                try {
                    return new PrimaryExpr(Double.parseDouble(token.text()));
                } catch (NumberFormatException ignored) {
                    return new IdentifierExpr(token.text());
                }
            }
        }
    }
    
    /**
     * Underlying implementation of the parser.
     * Not exposed as under normal circumstances
     * this method should only be externally called with precedence `0`.
     * <br />
     * This uses a simple form of <a href="https://en.wikipedia.org/wiki/Recursive_descent_parser">
     *     recursive descent parsing</a>.
     * Operator precedence is handled with a form of
     * <a href="https://en.wikipedia.org/wiki/Operator-precedence_parser#Precedence_climbing_method">
     *     precedence climbing.</a>
     * @param precedence The precedence level with which to parse (See {@linkplain BinaryPrecedence})
     * @return Expression parsed from input tokens of parser.
     *
     * @see #parse()
     * @see #parsePrimary()
     */
    private Expr parse(int precedence) {
        if (precedence >= BinaryPrecedence.MAX.ordinal())
            return parsePrimary();
        
        Expr lhs = parse(precedence + 1);
        
        if (tokens.size() == 0 || isParen(tokens.get(0).text())) return lhs;
        
        var op = tokens.get(0);

        BinaryOp bop = BinaryOp.fromString(op.text());
        
        if (bop == null) {
            throw new SyntaxException("Unknown binary operator " + op);
        }
        
        if (bop.precedence.ordinal() != precedence) {
            return lhs;
        }
        
        shift(tokens); // yoink the operator out
        
        Expr rhs = parse(precedence);
        return new BinaryExpr(lhs, rhs, bop);
    }
    
    /**
     * Parses an expression from input tokens of the Parser object.
     * Only exposed interface for parsing.
     *
     * For implementation details, see {@link Parser#parse(int)}
     * @return Expression object parsed from tokens field
     *
     * @see #parse(int)
     */
    public Expr parse() {
        return parse(0);
    }
}
