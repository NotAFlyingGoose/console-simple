package com.flyinggoose.consolesimple.consoles;

import com.flyinggoose.consolesimple.display.ConsoleCell;
import com.flyinggoose.consolesimple.utils.CharColor;

import java.util.List;

public class StandardConsole extends VirtualConsole implements Runnable {
    private Thread thread;

    @Override
    public void run() {
        while (running) {
            System.out.println("Console");
            for (List<ConsoleCell> row : rows) {
                for (ConsoleCell character : row) {
                    System.out.print(CharColor.ANSI.front + character.getBackground().toANSI().getCode() + ";1m");
                    System.out.print(character.getForeground().toANSI());
                    System.out.print(character.getTextCharacter().getCharacter());
                    System.out.print(CharColor.ANSI.RESET);
                }
                System.out.println();
            }

            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public synchronized void start() {
        super.start();
        if (!stopped) {
            this.thread = new Thread(this, "Console");
            this.thread.start();
        }
    }

    @Override
    public synchronized void stop() {
        super.stop();
        try {
            this.thread.join();
            this.thread = null;
        } catch (InterruptedException e) {

        }
    }

}
