package me.minefreak19.quadraticjava.parsing;

import java.util.ArrayList;
import java.util.List;

public record Lexer(String src) {
    
    // TODO: no error reporting/Token class
    public List<Token> tokens() {
        var tokens = new ArrayList<Token>();
    
        if (src.length() == 0) throw new IllegalStateException("Empty source for lexer");
        
        var sb = new StringBuilder();
        for (char c : src.toCharArray()) {
            
            switch (c) {
                case '+', '-', '*', '/', '^', '=', '(', ')' -> {
                    if (sb.length() > 0) {
                        tokens.add(new Token(sb.toString()));
                        sb.setLength(0);
                    }
                    tokens.add(new Token("" + c));
                }
                
                case ' ', '\t', '\n' -> {
                    if (sb.length() > 0) {
                        tokens.add(new Token(sb.toString()));
                        sb.setLength(0);
                    }
                }
                
                default -> sb.append(c);
            }
            
        }
        
        if (sb.length() > 0) tokens.add(new Token(sb.toString()));
        return tokens;
    }
}
