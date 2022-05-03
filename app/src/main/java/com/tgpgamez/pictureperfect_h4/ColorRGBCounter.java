package com.tgpgamez.pictureperfect_h4;

import java.util.Comparator;

/**
 * Class for ColorRGBCounter
 *
 * This class is used to combine a color and a counter
 *
 * @author Tobias
 * @version 1.0
 */
public class ColorRGBCounter {

    private ColorRGB color;
    public ColorRGB getColor() {
        return color;
    }

    private int counter;
    public int getCounter() {
        return counter;
    }

    /**
     * This method is used to add to the count
     * @param amount How much to add
     */
    public void addToCount(int amount) {
        counter += amount;
    }

    public ColorRGBCounter(ColorRGB color, int count) {
        this.color = color;
        this.counter = count;
    }
}
