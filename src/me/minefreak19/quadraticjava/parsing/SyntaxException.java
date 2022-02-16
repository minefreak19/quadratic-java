package me.minefreak19.quadraticjava.parsing;

public class SyntaxException extends RuntimeException {
    public SyntaxException(String s) {
        super(s);
    }
    
    public SyntaxException() { super(); }
}
