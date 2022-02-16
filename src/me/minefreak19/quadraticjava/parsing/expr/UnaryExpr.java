package me.minefreak19.quadraticjava.parsing.expr;

import me.minefreak19.quadraticjava.parsing.EvaluationContext;

import java.util.function.UnaryOperator;

// TODO(#2): A UnaryOp enum similar to BinaryOp so the names don't have to be provided
public class UnaryExpr extends Expr {
    
    private final UnaryOperator<Double> op;
    private final Expr expr;
    private final String name;
    
    public UnaryExpr(Expr expr, UnaryOperator<Double> op, String name) {
        this.op = op;
        this.expr = expr;
        this.name = name;
    }
    
    public UnaryExpr(Expr expr, UnaryOperator<Double> op) {
        this(expr, op, null);
    }
    
    @Override
    public double eval(EvaluationContext context) {
        return op.apply(expr.eval(context));
    }
    
    @Override
    public String toString() {
        return String.format("{ un: \"%s\", val: %s }", name, expr);
    }
}
