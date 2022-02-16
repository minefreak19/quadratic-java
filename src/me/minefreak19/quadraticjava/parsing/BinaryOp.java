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
    
    final String name;
    final BinaryPrecedence precedence;
    final BinaryOperator<Double> op;
    
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
    
    public static BinaryOp fromString(String s) {
        return BINARY_OPS.get(s);
    }
}
