package me.minefreak19.quadraticjava.parsing.expr;

import me.minefreak19.quadraticjava.parsing.EvaluationContext;

public class PrimaryExpr extends Expr {
    private double value;
    
    public PrimaryExpr(double value) {
        this.value = value;
    }
    
    @Override
    public double eval(EvaluationContext _ctx) {
        return this.value;
    }
    
    @Override
    public String toString() {
        return String.format("{ value: %f }", value);
    }
}
