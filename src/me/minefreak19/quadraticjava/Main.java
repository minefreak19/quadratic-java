package me.minefreak19.quadraticjava;

import me.minefreak19.quadraticjava.parsing.EvaluationContext;
import me.minefreak19.quadraticjava.parsing.Lexer;
import me.minefreak19.quadraticjava.parsing.Parser;

import java.util.HashMap;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        var sc = new Scanner(System.in);
        
        System.out.println("Enter your expression:");
        String input = sc.nextLine();
    
        System.out.printf("Input: %s\n", input);
    
        var lexer = new Lexer(input);
        var tokens = lexer.tokens();
        System.out.printf("Tokenized: %s\n", tokens);
        
        var parser = new Parser(tokens);
        var expr = parser.parse();
        System.out.printf("Parsed: %s\n", expr);
   
        var evalContext = new EvaluationContext(new HashMap<>());
        evalContext.identifiers().put("pi", Math.PI);
        evalContext.identifiers().put("e", Math.E);
        double result = expr.eval(evalContext);
        System.out.printf("\nRESULT: \033[34;1m%s\n", result);
        
        sc.close();
    }
}
