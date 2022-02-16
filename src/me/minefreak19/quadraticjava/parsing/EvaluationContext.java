package me.minefreak19.quadraticjava.parsing;

import java.util.Map;

/**
 * Context for evaluation of expressions.
 * Contains constant values of identifiers.
 *
 * Primarily in place to be more future-proof,
 * as further context that needs to be preserved
 * during evaluation can be added here.
 */
public record EvaluationContext(Map<String, Double> identifiers) {
}
