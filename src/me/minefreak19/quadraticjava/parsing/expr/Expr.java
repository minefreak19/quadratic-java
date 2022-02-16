package me.minefreak19.quadraticjava.parsing.expr;

import me.minefreak19.quadraticjava.parsing.EvaluationContext;

public abstract class Expr {
    /**
     * Evaluates an expression.
     * @param context Context within expression is evaluated
     * @return result of evaluation
     * @see EvaluationContext
     */
    public abstract double eval(EvaluationContext context);
    @Override public abstract String toString();
}
