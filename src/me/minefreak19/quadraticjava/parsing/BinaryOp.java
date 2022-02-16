package me.minefreak19.quadraticjava.parsing;

import java.util.HashMap;
import java.util.Map;
import java.util.function.BinaryOperator;

public enum BinaryOp {
    PLUS("+", BinaryPrecedence.ADDITIVE, Double::sum),
    MINUS("-", BinaryPrecedence.ADDITIVE, (x, y)-> x-y),
    MUL("*", BinaryPrecedence.MULTIPLICATIVE, (x, y)-> x*y),
    DIV("/", BinaryPrecedence.MULTIPLICATIVE, (x, y) -> x / y),
    POW("^", BinaryPrecedence.EXPONENTIAL, Math::pow),
    
    ;
    
    public final String name;
    public final BinaryPrecedence precedence;
    /**
     * Applied on operands during evaluation.
     * @see me.minefreak19.quadraticjava.parsing.expr.BinaryExpr#eval(EvaluationContext)
     */
    public final BinaryOperator<Double> op;
    
    BinaryOp(String name, BinaryPrecedence precedence, BinaryOperator<Double> op) {
        this.name = name;
        this.precedence = precedence;
        this.op = op;
    }
    
    private static final Map<String, BinaryOp> BINARY_OPS;
    static {
        BINARY_OPS = new HashMap<>();
        
        for (var bop : BinaryOp.values()) {
            BINARY_OPS.put(bop.name, bop);
        }
    }
    
    /**
     * Looks up a BinaryOp by a String as it would appear in tokenized input.
     *
     * Not to be confused with {@link #valueOf}
     *
     * {@code fromString("+") -> BinaryOp.PLUS}
     * {@code valueOf("PLUS") -> BinaryOp.PLUS}
     * @param s String to lookup by
     * @return BinaryOp represented by s
     */
    public static BinaryOp fromString(String s) {
        return BINARY_OPS.get(s);
    }
}
