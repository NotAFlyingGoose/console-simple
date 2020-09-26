package com.flyinggoose.consolesimple.consoles;

import com.flyinggoose.consolesimple.display.ConsolePosition;
import com.flyinggoose.consolesimple.display.ConsoleSize;

import java.awt.*;

public interface Console {

    /**
     * resets the character at a point on the console to it's default character (usually a space ' ')
     *
     * @param pos point to reset the character at
     */
    void resetCharAt(ConsolePosition pos);

    /**
     * resets the background color at a point on the console to it's default color
     *
     * @param pos point to reset the background at
     */
    void resetBackgroundAt(ConsolePosition pos);

    /**
     * resets the foreground color at a point on the console to it's default color.
     *
     * @param pos point to reset the foreground at.
     */
    void resetForegroundAt(ConsolePosition pos);

    /**
     * set the character at a certain point on the console.
     * also sets a last edit variable witch can be accessed with <code>Console.getLastEdit()</code>
     *
     * @see #getLastEdit();
     * @param pos point to set the character at.
     * @param c   character to change the point to.
     */
    void setCharAt(ConsolePosition pos, char c);

    /**
     * set the background color at a certain point on the console.
     *
     * @param pos point to set the background at.
     * @param c   color to change the background to.
     */
    void setBackgroundAt(ConsolePosition pos, Color c);

    /**
     * set the text color at a certain point on the console.
     *
     * @param pos point to set the foreground at.
     * @param c   color to change the foreground to.
     */
    void setForegroundAt(ConsolePosition pos, Color c);

    /**
     * gets the character at a certain point on the console.
     *
     * @param pos point to get the character from.
     * @return character at pos.
     */
    char getCharAt(ConsolePosition pos);

    /**
     * gets the color of the background at a certain point on the console.
     *
     * @param pos point to get the color from.
     * @return Color of the background at pos.
     */
    Color getBackgroundAt(ConsolePosition pos);

    /**
     * gets the color of text at a certain point on the console.
     *
     * @param pos point to get the color from.
     * @return Color of the foreground at pos.
     */
    Color getForegroundAt(ConsolePosition pos);

    /**
     * gets the last point at witch the console was edited.
     * This position is changed every time the Console.setCharAt() method is called.
     *
     * @see #setCharAt(ConsolePosition, char);
     * @return ConsolePosition of the last edit.
     */
    ConsolePosition getLastEdit();

    /**
     * get the size (width, height) of the console.
     *
     * @return ConsoleSize with the size of the console.
     */
    ConsoleSize getConsoleSize();

    /**
     * sets the size (width, height) of the console.
     * NOTE: for some consoles this does not work, as they have fixed sizes.
     *
     * @param size ConsoleSize to set the console's size to.
     */
    void setConsoleSize(ConsoleSize size);

    /**
     * clears the screen. removing all characters from view.
     */
    void clearScreen();

    /**
     * Starts the console, allowing for edits.
     */
    void start();

    /**
     * Stops the console.
     */
    void stop();

    /**
     * Get's a string witch the user entered. If the user has not entered a string or the string has already been returned, it will return null.
     * The string is continually added to until the user presses the enter key at witch point this method will be able to return it.
     *
     * @return string of the users input
     */
    String getNextLine();

    /**
     * Get's the current string being typed by the user. If the user has not entered a string, it will return null.
     * This string is continually added to until the user presses the enter key at witch point the string will be set to "".
     *
     * @return string of the users input
     */
    String getLastLine();

    /**
     * Get's every character that was entered and puts in into a string that is returned here.
     *
     * @return string of all the users input
     */
    String getAllLines();

    /**
     * Checks if a key is being pressed by the user
     *
     * @param key char to check.
     * @return boolean is the key being pressed
     */
    boolean isPressing(char key);

    /**
     * Checks if a key is being pressed by the user
     *
     * @param key key code to check
     * @return boolean is the key being pressed
     */
    boolean isPressing(int key);

}
