package me.minefreak19.quadraticjava.parsing;

import java.util.ArrayList;
import java.util.List;

/**
 * Performs <a href="https://en.wikipedia.org/wiki/Lexical_analysis">Lexical analysis</a>.
 */
public record Lexer(String src) {
    
    // TODO(#1): no properly located error reporting
    
    /**
     * Only method of Lexer.
     * Takes the input String, and tokenizes it, splitting it into smaller, meaningful chunks
     * and so producing a List of {@linkplain Token Tokens}.
     * @return List of Tokens
     *
     * @see Token
     */
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
