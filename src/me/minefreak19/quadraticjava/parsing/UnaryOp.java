package me.minefreak19.quadraticjava.parsing;

import java.util.HashMap;
import java.util.Map;
import java.util.function.UnaryOperator;

// TODO: no proper precedence for unary ops
//  Currently they are simply applied right to left
public enum UnaryOp {
    NEG("-", x -> -x);
    
    public final String name;
    public final UnaryOperator<Double> op;
    
    UnaryOp(String name, UnaryOperator<Double> op) {
        this.name = name;
        this.op = op;
    }
   
    private static final Map<String, UnaryOp> UNARY_OPS;
    static {
        UNARY_OPS = new HashMap<>();
        
        for (var uop : UnaryOp.values()) {
            UNARY_OPS.put(uop.name, uop);
        }
    }
    
    /**
     * @see BinaryOp#fromString(String) 
     */
    public static UnaryOp fromString(String s) {
        return UNARY_OPS.get(s);
    }
}
