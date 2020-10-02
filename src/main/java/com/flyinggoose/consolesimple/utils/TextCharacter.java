package com.flyinggoose.consolesimple.utils;

public class TextCharacter {

    // regular characters
    public static final TextCharacter SPACE = new TextCharacter(' ');
    public static final TextCharacter CHAR_A = new TextCharacter('A');
    public static final TextCharacter CHAR_B = new TextCharacter('B');
    public static final TextCharacter CHAR_C = new TextCharacter('C');
    public static final TextCharacter CHAR_D = new TextCharacter('D');
    public static final TextCharacter CHAR_E = new TextCharacter('E');
    public static final TextCharacter CHAR_F = new TextCharacter('F');
    public static final TextCharacter CHAR_G = new TextCharacter('G');
    public static final TextCharacter CHAR_H = new TextCharacter('H');
    public static final TextCharacter CHAR_I = new TextCharacter('I');
    public static final TextCharacter CHAR_J = new TextCharacter('J');
    public static final TextCharacter CHAR_K = new TextCharacter('K');
    public static final TextCharacter CHAR_L = new TextCharacter('L');
    public static final TextCharacter CHAR_M = new TextCharacter('M');
    public static final TextCharacter CHAR_N = new TextCharacter('N');
    public static final TextCharacter CHAR_O = new TextCharacter('O');
    public static final TextCharacter CHAR_P = new TextCharacter('P');
    public static final TextCharacter CHAR_Q = new TextCharacter('Q');
    public static final TextCharacter CHAR_R = new TextCharacter('R');
    public static final TextCharacter CHAR_S = new TextCharacter('S');
    public static final TextCharacter CHAR_T = new TextCharacter('T');
    public static final TextCharacter CHAR_U = new TextCharacter('U');
    public static final TextCharacter CHAR_V = new TextCharacter('V');
    public static final TextCharacter CHAR_W = new TextCharacter('W');
    public static final TextCharacter CHAR_X = new TextCharacter('X');
    public static final TextCharacter CHAR_Y = new TextCharacter('Y');
    public static final TextCharacter CHAR_Z = new TextCharacter('Z');

    //blocks
    public static final TextCharacter FULL_BLOCK = new TextCharacter('█'); // U+2588

    //boxes
    //single
    public static final TextCharacter BOX_VERTICAL = new TextCharacter('│'); // U+2502
    public static final TextCharacter BOX_VERTICAL_RIGHT = new TextCharacter('├'); // U+251C
    public static final TextCharacter BOX_VERTICAL_LEFT = new TextCharacter('┤'); // U+2524
    public static final TextCharacter BOX_CROSS = new TextCharacter('┼'); // U+253C
    public static final TextCharacter BOX_HORIZONTAL = new TextCharacter('─'); // U+2500
    public static final TextCharacter BOX_HORIZONTAL_UP = new TextCharacter('┴'); // U+2534
    public static final TextCharacter BOX_HORIZONTAL_DOWN = new TextCharacter('┬'); // U+252C
    public static final TextCharacter BOX_RIGHT_DOWN = new TextCharacter('┌'); // U+250C
    public static final TextCharacter BOX_LEFT_DOWN = new TextCharacter('┐'); // U+2510
    public static final TextCharacter BOX_RIGHT_UP = new TextCharacter('└'); // U+2514
    public static final TextCharacter BOX_LEFT_UP = new TextCharacter('┘'); // U+2518
    //double
    public static final TextCharacter BOX_DOUBLE_VERTICAL = new TextCharacter('║'); // U+2551
    public static final TextCharacter BOX_DOUBLE_VERTICAL_RIGHT = new TextCharacter('╠'); // U+2560
    public static final TextCharacter BOX_DOUBLE_VERTICAL_LEFT = new TextCharacter('╣'); // U+2563
    public static final TextCharacter BOX_DOUBLE_CROSS = new TextCharacter('╬'); // U+256C
    public static final TextCharacter BOX_DOUBLE_HORIZONTAL = new TextCharacter('═'); // U+2550
    public static final TextCharacter BOX_DOUBLE_HORIZONTAL_UP = new TextCharacter('╩'); // U+2569
    public static final TextCharacter BOX_DOUBLE_HORIZONTAL_DOWN = new TextCharacter('╦'); // U+2566
    public static final TextCharacter BOX_DOUBLE_RIGHT_DOWN = new TextCharacter('╔'); // U+250C
    public static final TextCharacter BOX_DOUBLE_LEFT_DOWN = new TextCharacter('╗'); // U+2510
    public static final TextCharacter BOX_DOUBLE_RIGHT_UP = new TextCharacter('╚'); // U+2514
    public static final TextCharacter BOX_DOUBLE_LEFT_UP = new TextCharacter('╝'); // U+2518

    private final boolean bold;
    private final boolean italic;
    private final char character;
    private final int code;

    public TextCharacter(char character) {
        this(character, false, false);
    }

    public TextCharacter(int code) {
        this(code, false, false);
    }

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

    public TextCharacter toUpperCase() {
        return new TextCharacter(Character.toUpperCase(character), bold, italic);
    }

    public TextCharacter toLowerCase() {
        return new TextCharacter(Character.toLowerCase(character), bold, italic);
    }

    public int getCode() {
        return code;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof TextCharacter textCharacter)
            return character == textCharacter.character &&
                    bold == textCharacter.bold &&
                    italic == textCharacter.italic;
        return false;
    }
}
