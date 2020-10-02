package com.flyinggoose.consoleTests;

import com.flyinggoose.consolesimple.consoles.Console;
import com.flyinggoose.consolesimple.consoles.swing.SwingConsole;
import com.flyinggoose.consolesimple.display.ConsoleGraphics;
import com.flyinggoose.consolesimple.display.ConsolePosition;
import com.flyinggoose.consolesimple.display.ConsoleSize;
import com.flyinggoose.consolesimple.display.shapes.ConsoleOval;
import com.flyinggoose.consolesimple.display.shapes.ConsoleRectangle;
import com.flyinggoose.consolesimple.utils.CharColor;
import com.flyinggoose.consolesimple.utils.TextCharacter;

import java.util.Random;

public class Main {
    public static void main(String[] args) {
        System.out.print(CharColor.ANSI.GREEN);
        System.out.println("wow this is really cool");
        System.out.print(CharColor.ANSI.RESET);
        CharColor.RGB check = new CharColor.RGB(128, 155, 200);
        System.out.println("nearest to " + check + " is " + check.toANSI().getCode());

        Console console = new SwingConsole("Console");
        console.start();
        console.setConsoleSize(new ConsoleSize(50, 30));

        ConsoleGraphics g = new ConsoleGraphics(console);

        Random random = new Random();
        String typed = "Typed: ''";
        while (true) {
            for (int i = 0; i < 10; i++) {
                g.setBackgroundColor(new CharColor.RGB(random.nextInt(255), random.nextInt(255), random.nextInt(255)));
                g.drawChar(new ConsolePosition(random.nextInt(console.getConsoleSize().getWidth()), random.nextInt(console.getConsoleSize().getHeight())), TextCharacter.SPACE);
            }
            g.setBackgroundColor(new CharColor.RGB(128, 128, 128));
            g.setForegroundColor(CharColor.ANSI.WHITE);
            g.draw(new ConsoleRectangle.DoubleBox(new ConsolePosition(1, 1), typed.length() + 2, 3));
            g.drawString(new ConsolePosition(2, 2), typed);
            String line = console.getLastLine();
            if (line != null) {
                typed = "Typed: '" + line + "'";
            }
            g.setBackgroundColor(CharColor.ANSI.GREEN);
            g.setForegroundColor(CharColor.ANSI.BLUE);
            g.drawLine(new ConsolePosition(10, 5), new ConsolePosition(25, 25), new TextCharacter('A', false, true));

            ConsolePosition test1 = new ConsolePosition(20, 9);
            ConsolePosition test2 = new ConsolePosition(20, 18);
            g.setBackgroundColor(CharColor.ANSI.BLUE);
            g.setForegroundColor(CharColor.ANSI.GREEN);
            ConsoleOval c = new ConsoleOval(test1, 5, 5, new TextCharacter('$'));
            g.draw(c);
            c.setPos(test2);
            g.fill(c);
            g.setBackgroundColor(CharColor.ANSI.GREEN);
            g.setForegroundColor(CharColor.ANSI.BLUE);
            g.drawChar(test1, TextCharacter.CHAR_C);
            g.drawChar(test2, TextCharacter.CHAR_C);

            g.flush();

            //System.out.println(console.getCharAt(new ConsolePosition(10, 2)));
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }
}
