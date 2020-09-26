package com.flyinggoose.consolesimple.utils;

public class TextCharacter {
    private final boolean bold;
    private final boolean italic;
    private final char character;
    private final int code;

    public TextCharacter(char character, boolean bold, boolean italic) {
        this((int) character, bold, italic);
    }

    public TextCharacter(int code, boolean bold, boolean italic) {
        this.character = (char) code;
        this.code = code;
        this.bold = bold;
        this.italic = italic;
    }

    public boolean isBold() {
        return bold;
    }

    public boolean isItalic() {
        return italic;
    }

    public char getCharacter() {
        return character;
    }

    public int getCode() {
        return code;
    }
}
