package com.flyinggoose.consoleTests;

import com.flyinggoose.consolesimple.consoles.Console;
import com.flyinggoose.consolesimple.consoles.TerminalConsole;
import com.flyinggoose.consolesimple.consoles.swing.SwingConsole;
import com.flyinggoose.consolesimple.display.ConsoleGraphics;
import com.flyinggoose.consolesimple.display.ConsolePosition;
import com.flyinggoose.consolesimple.display.ConsoleSize;
import com.flyinggoose.consolesimple.display.shapes.ConsoleOval;
import com.flyinggoose.consolesimple.display.shapes.ConsoleRectangle;
import com.flyinggoose.consolesimple.utils.ANSI;

import java.awt.*;
import java.util.Random;

public class Main {
    public static void main(String[] args) {
        System.out.print(ANSI.GREEN);
        System.out.println("wow this is really cool");
        System.out.print(ANSI.RESET);
        System.out.println("nearest to " + new Color(155, 123, 55) + " is " + ANSI.getNearestAnsi(new Color(155, 123, 55)).name());
        /*
        Console console = new TerminalConsole();
        console.setConsoleSize(new ConsoleSize(50, 30));
        console.start();

        ConsoleGraphics g = new ConsoleGraphics(console);

        Random random = new Random();
        String typed = "Typed: ''";
        while (true) {
            for (int i = 0; i < 10; i++) {
                g.setBackgroundColor(new Color(random.nextInt(255), random.nextInt(255), random.nextInt(255)));
                g.drawChar(new ConsolePosition(random.nextInt(console.getConsoleSize().getWidth()), random.nextInt(console.getConsoleSize().getHeight())), ' ');
            }
            g.setBackgroundColor(Color.GRAY);
            g.setForegroundColor(Color.WHITE);
            g.draw(new ConsoleRectangle.DoubleBox(new ConsolePosition(1, 1), typed.length() + 2, 3));
            g.drawString(new ConsolePosition(2, 2), typed);
            String line = console.getLastLine();
            if (line != null) {
                typed = "Typed: '" + line + "'";
            }
            g.setBackgroundColor(Color.GREEN);
            g.setForegroundColor(Color.BLUE);
            g.drawLine(new ConsolePosition(10, 5), new ConsolePosition(25, 25), 'a');

            ConsolePosition test1 = new ConsolePosition(20, 9);
            ConsolePosition test2 = new ConsolePosition(20, 18);
            g.setBackgroundColor(Color.BLUE);
            g.setForegroundColor(Color.GREEN);
            ConsoleOval c = new ConsoleOval(test1, 5, 5, '$');
            g.draw(c);
            c.setPos(test2);
            g.fill(c);
            g.setBackgroundColor(Color.GREEN);
            g.setForegroundColor(Color.BLUE);
            g.drawChar(test1, 'c');
            g.drawChar(test2, 'c');

            g.flush();

            //System.out.println(console.getCharAt(new ConsolePosition(10, 2)));
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }*/

    }
}
