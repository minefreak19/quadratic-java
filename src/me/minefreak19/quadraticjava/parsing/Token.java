package me.minefreak19.quadraticjava.parsing;

public record Token(String text) {
    @Override
    public String toString() {
        return String.format("`%s`", text);
    }
}
