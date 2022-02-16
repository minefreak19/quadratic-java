package me.minefreak19.quadraticjava.parsing.expr;

import me.minefreak19.quadraticjava.parsing.EvaluationContext;

public abstract class Expr {
    public abstract double eval(EvaluationContext context);
    @Override public abstract String toString();
}
