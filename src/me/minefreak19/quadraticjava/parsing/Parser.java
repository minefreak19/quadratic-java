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
                } catch (NumberFormatException ignoreD) {
                    return new IdentifierExpr(token.text());
                }
            }
        }
    }
    
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
        return new BinaryExpr(lhs, rhs, bop.op, bop.name());
    }
    
    public Expr parse() {
        return parse(0);
    }
}
