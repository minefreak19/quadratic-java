package me.minefreak19.quadraticjava;

import me.minefreak19.quadraticjava.parsing.EvaluationContext;
import me.minefreak19.quadraticjava.parsing.Lexer;
import me.minefreak19.quadraticjava.parsing.Parser;

import java.util.HashMap;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        boolean debug = false;
     
        for (var arg : args) {
            if (arg.equalsIgnoreCase("--debug")) {
                debug = true;
                break;
            }
        }
     
        var sc = new Scanner(System.in);
        
        System.out.println("Enter your expression:");
        String input = sc.nextLine();
        if (debug) System.out.println("Input: " + input);
        
        String[] sources = input.split("\\|");
        double[] results = new double[sources.length];
    
        for (int i = 0; i < sources.length; i++) {
            String source = sources[i];
            var lexer = new Lexer(source);
            var tokens = lexer.tokens();
            if (debug) System.out.println("Tokenized: " + tokens);
            
            var parser = new Parser(tokens);
            var expr = parser.parse();
            if (debug) System.out.println("Parsed: " + expr);
       
            var evalContext = new EvaluationContext(new HashMap<>());
            evalContext.identifiers().put("pi", Math.PI);
            evalContext.identifiers().put("e", Math.E);
            double result = expr.eval(evalContext);
            results[i] = result;
        }
    
        for (double result : results)
            System.out.println("\nRESULT: \033[34;1m" + result + "\033[0m");
        
        sc.close();
    }
}
