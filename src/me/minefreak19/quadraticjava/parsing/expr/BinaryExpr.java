package me.minefreak19.quadraticjava.parsing.expr;

import me.minefreak19.quadraticjava.parsing.EvaluationContext;

import java.util.function.BinaryOperator;

public class BinaryExpr extends Expr {
    
    private final Expr lhs, rhs;
    private final BinaryOperator<Double> op;
    
    private final String name;
    
    public BinaryExpr(Expr lhs, Expr rhs, BinaryOperator<Double> op, String name) {
        this.lhs = lhs;
        this.rhs = rhs;
        this.op = op;
        this.name = name;
    }
    
    public BinaryExpr(Expr lhs, Expr rhs, BinaryOperator<Double> op) {
        this(lhs, rhs, op, null);
    }
    
    /**
     * @deprecated will be replaced by use of BinaryOp
     * human-readable name of operation
     */
    @Deprecated
    private static String humanName(String name) {
        return switch (name) {
            case "*" -> "mul";
            case "+" -> "add";
            case "-" -> "sub";
            case "/" -> "div";
            default  -> name;
        };
    }
    
    @Override
    public double eval(EvaluationContext ctx) {
        return op.apply(lhs.eval(ctx), rhs.eval(ctx));
    }
    
    @Override public String toString() {
        return String.format("{ name: \"%s\", lhs: %s, rhs: %s }", humanName(name), lhs, rhs);
    }
}
