package com.flyinggoose.consolesimple.consoles.swing;

import com.flyinggoose.consolesimple.display.ConsoleCell;

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
        for (List<ConsoleCell> row : console.rows) {
            for (ConsoleCell cc : row) {
                Font charFont = fontConfig.getFontForCharacter(cc.getTextCharacter());
                g2d.setFont(charFont);
                g2d.setColor(new Color(cc.getBackground().toRGB().getRed(), cc.getBackground().toRGB().getGreen(), cc.getBackground().toRGB().getBlue()));
                g2d.fillRect(cc.getPos().getX() * cellWidth, cc.getPos().getY() * cellHeight, cellWidth, cellHeight);
                g2d.setColor(new Color(cc.getForeground().toRGB().getRed(), cc.getForeground().toRGB().getGreen(), cc.getForeground().toRGB().getBlue()));
                g2d.drawString(String.valueOf(cc.getTextCharacter().getCharacter()), cc.getPos().getX() * cellWidth, (cc.getPos().getY() * cellHeight) + getFontMetrics(charFont).getAscent());
            }
        }
    }

    @Override
    public void update() {

    }
}
