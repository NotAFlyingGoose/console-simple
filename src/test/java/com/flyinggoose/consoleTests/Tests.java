package com.flyinggoose.consoleTests;

import java.io.Console;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Tests {

    public static void main(String[] args) throws IOException, InterruptedException {
        test();
    }

    public static void test() throws IOException, InterruptedException {
        String[] cmd = {"/bin/sh", "-c", "stty raw </dev/tty"};
        Runtime.getRuntime().exec(cmd).waitFor();
        Console console = System.console();
        Reader reader = console.reader();
        ArrayList<Long> timeStamps = new ArrayList<Long>();
        StringBuilder password = new StringBuilder();
        timeStamps.add(System.currentTimeMillis());
        System.out.println("Enter your 8 character password");
        for(int i = 0;i<8;i++) {
            password.append(reader.read());
            timeStamps.add(System.currentTimeMillis());
        }
        System.out.println("Timestamps: ");
        System.out.println(timeStamps);
        cmd = new String[] {"/bin/sh", "-c", "stty sane </dev/tty"};
        Runtime.getRuntime().exec(cmd).waitFor();
    }

    public static void raw() {
        RawConsoleInput GC = new RawConsoleInput();
        int CharRead = 0;
        for (;;) {
            try {
                CharRead = GC.read(true);
            } catch (IOException ex) {
                Logger.getLogger(RawConsoleInput.class.getName()).log(Level.SEVERE, null, ex);
            }
            if (CharRead == -1 || CharRead == 27 || CharRead == 3 || CharRead == 4)    // ^c, ^d, or Esc)
                break;

            System.out.println((char) CharRead);
        }
    }
}
