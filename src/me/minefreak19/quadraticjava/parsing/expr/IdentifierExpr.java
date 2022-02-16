package me.minefreak19.quadraticjava.parsing.expr;

import me.minefreak19.quadraticjava.parsing.EvaluationContext;

public class IdentifierExpr extends Expr {
    
    private final String name;
    
    public IdentifierExpr(String name) {
        this.name = name;
    }
    
    @Override
    public double eval(EvaluationContext context) {
        Double value = context.identifiers().get(name);
        if (value == null) {
            throw new RuntimeException(String.format("Unknown identifier `%s`", name));
        }
        return value;
    }
    
    @Override
    public String toString() {
        return String.format("{ id: \"%s\" }", this.name);
    }
}
