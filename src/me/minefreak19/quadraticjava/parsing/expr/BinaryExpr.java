package me.minefreak19.quadraticjava.parsing.expr;

import me.minefreak19.quadraticjava.parsing.BinaryOp;
import me.minefreak19.quadraticjava.parsing.EvaluationContext;

public class BinaryExpr extends Expr {
    
    private final Expr lhs, rhs;
    private final BinaryOp op;
    
    public BinaryExpr(Expr lhs, Expr rhs, BinaryOp op) {
        this.lhs = lhs;
        this.rhs = rhs;
        this.op = op;
    }
    
    @Override
    public double eval(EvaluationContext ctx) {
        return op.op.apply(lhs.eval(ctx), rhs.eval(ctx));
    }
    
    @Override public String toString() {
        return String.format("{ name: \"%s\", lhs: %s, rhs: %s }", op.toString(), lhs, rhs);
    }
}
