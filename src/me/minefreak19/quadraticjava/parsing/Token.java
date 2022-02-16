package me.minefreak19.quadraticjava.parsing;

public record Token(String text) {
    
    /**
     * Converts a Token object to a String with some formatting.
     * To get the text directly, use {@link Token#text() Token.text()}.
     * @return Formatted String representing the Token object
     */
    @Override
    public String toString() {
        return "`" + this.text + "`";
    }
}
