package com.flyinggoose.consolesimple.consoles.swing;

import com.flyinggoose.consolesimple.display.ConsoleCharacter;

import java.awt.*;
import java.util.List;

public class SwingConsoleDisplay extends SwingDisplay {
    private final SwingConsole console;
    private final SwingConsoleFontConfig fontConfig;
    int cellWidth;
    int cellHeight;

    public SwingConsoleDisplay(String title, SwingConsole console) {
        super(title);
        this.console = console;
        this.fontConfig = console.fontConfig;
    }

    @Override
    public void init() {

    }

    @Override
    public void render(Graphics2D g2d) {
        if (fontConfig.isAntiAliased())
            g2d.setRenderingHint(
                    RenderingHints.KEY_TEXT_ANTIALIASING,
                    RenderingHints.VALUE_TEXT_ANTIALIAS_LCD_HRGB);

        // black background
        g2d.setColor(Color.black);
        g2d.fillRect(0, 0, getWidth() * 2, getHeight() * 2);

        // ready for drawing
        for (List<ConsoleCharacter> row : console.rows) {
            for (ConsoleCharacter cc : row) {
                Font charFont = fontConfig.getFontForCharacter(cc.getTextCharacter());
                g2d.setFont(charFont);
                g2d.setColor(cc.getBackground());
                g2d.fillRect(cc.getPos().getX() * cellWidth, cc.getPos().getY() * cellHeight, cellWidth, cellHeight);
                g2d.setColor(cc.getForeground());
                g2d.drawString(String.valueOf(cc.getTextCharacter().getCharacter()), cc.getPos().getX() * cellWidth, (cc.getPos().getY() * cellHeight) + getFontMetrics(charFont).getAscent());
            }
        }
    }

    @Override
    public void update() {

    }
}
