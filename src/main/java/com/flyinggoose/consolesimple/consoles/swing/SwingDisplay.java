package com.flyinggoose.consolesimple.consoles.swing;

import com.flyinggoose.consolesimple.input.KeyBoard;
import com.flyinggoose.consolesimple.input.Mouse;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferStrategy;

public abstract class SwingDisplay extends Canvas implements Runnable {
    private final JFrame frame;
    private final Mouse mouse;
    private final KeyBoard keyboard;
    private Thread thread;
    private boolean running = false;

    public SwingDisplay(String title) {
        this.frame = new JFrame();

        this.mouse = new Mouse();
        this.addMouseListener(this.mouse);
        this.addMouseMotionListener(this.mouse);
        this.addMouseWheelListener(this.mouse);

        this.keyboard = new KeyBoard();
        this.addKeyListener(this.keyboard);

        this.frame.setTitle(title);
        this.frame.add(this);
        this.frame.pack();
        this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.frame.setLocationRelativeTo(null);
        this.frame.setResizable(true);

        init();
    }

    public JFrame getWindow() {
        return this.frame;
    }

    public Mouse getMouse() {
        return this.mouse;
    }

    public KeyBoard getKeyboard() {
        return this.keyboard;
    }

    public synchronized void start() {
        running = true;
        this.frame.setVisible(true);
        this.thread = new Thread(this, "Display");
        this.thread.start();
    }

    public synchronized void stop() {
        running = false;
        try {
            this.thread.join();
        } catch (InterruptedException e) {

        }
    }

    @Override
    public void run() {
        long lastTime = System.nanoTime();
        long timer = System.currentTimeMillis();
        final double ns = 1000000000.0 / 60;
        double delta = 0;
        int fps = 0;

        while (this.running) {
            long now = System.nanoTime();
            delta += (now - lastTime) / ns;
            lastTime = now;

            while (delta >= 1) {
                update();

                BufferStrategy bs = this.getBufferStrategy();
                if (bs == null) {
                    this.createBufferStrategy(3);
                } else {
                    Graphics2D g2d = (Graphics2D) bs.getDrawGraphics();
                    render(g2d);
                    g2d.dispose();
                    bs.show();
                }
                fps++;
                delta--;
            }

            if (System.currentTimeMillis() - timer > 1000) {
                timer += 1000;
                System.out.println(fps + " fps");
                fps = 0;
            }
        }
    }

    public abstract void init();

    public abstract void render(Graphics2D g2d);

    public abstract void update();

}
