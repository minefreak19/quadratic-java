package me.minefreak19.quadraticjava.parsing.expr;

import me.minefreak19.quadraticjava.parsing.EvaluationContext;
import me.minefreak19.quadraticjava.parsing.UnaryOp;

public class UnaryExpr extends Expr {
    
    private final Expr expr;
    private final UnaryOp op;
    
    public UnaryExpr(Expr expr, UnaryOp op) {
        this.expr = expr;
        this.op = op;
    }
    
    @Override
    public double eval(EvaluationContext context) {
        return op.op.apply(expr.eval(context));
    }
    
    @Override
    public String toString() {
        return String.format("{ un: \"%s\", val: %s }", op.toString(), expr);
    }
}
