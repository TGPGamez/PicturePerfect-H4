package com.tgpgamez.pictureperfect_h4;

import android.graphics.Color;

/**
 * Class for ColorRGB
 *
 * This class is used to all regarding RGB colors
 *
 * @author Tobias
 * @version 1.0
 */
public class ColorRGB {
    private int alpha;
    public int getAlpha() {
        return alpha;
    }

    private int red;
    public int getRed() {
        return red;
    }

    private int green;
    public int getGreen() {
        return green;
    }

    private int blue;
    public int getBlue() {
        return blue;
    }

    public ColorRGB(int alpha, int red, int green, int blue) {
        this.alpha = alpha;
        this.red = red;
        this.green = green;
        this.blue = blue;
    }

    /**
     * This method is used to get the hex
     * @return The color as hex
     */
    public String asHex() {
        return String.format("#%02x%02x%02x", red, green, blue);
    }

    @Override
    public String toString() {
        return "\nColorRGB" +
                "\nRed = " + red +
                "\nGreen = " + green +
                "\nBlue = " + blue + "\n";
    }

    /**
     * Method is used to find the distance to another color
     * @param color Compare color
     * @return Color difference between this color and compare color
     */
    public int distance(ColorRGB color) {
        return (int) Math.sqrt((Math.pow((color.getRed() - this.red), 2)
                + Math.pow((color.getGreen() - this.green), 2)
                + Math.pow((color.getBlue() - this.blue), 2)));
    }

    /**
     * Method is used to find the distance between two colors
     * @param color1 First color
     * @param color2 Second color
     * @return Color difference between color1 and color2
     */
    public static int distance(ColorRGB color1, ColorRGB color2) {
        return (int) Math.sqrt((Math.pow((color2.getRed() - color1.getRed()), 2)
                + Math.pow((color2.getGreen() - color1.getGreen()), 2)
                + Math.pow((color2.getBlue() - color1.getBlue()), 2)));
    }

    /**
     * Method is used to parse rgb into ColorRGB object
     * @param rgb int for rgb
     * @return new ColorRGB object out from rgb
     */
    public static ColorRGB parse(int rgb) {
        int alpha = (rgb >> 24) & 0xff;
        int red = (rgb >> 16) & 0xff;
        int green = (rgb >> 8) & 0xff;
        int blue = (rgb) & 0xff;
        return new ColorRGB(alpha, red, green, blue);
    }

    /**
     * Method is used to manipulate a color to be darker or lighter
     * @param factor if 1.0f or higher, then it will be lighter, if lower then it will be darker
     * @return Color as int
     */
    public int manipulateColor(float factor) {
        return Color.argb(this.alpha,
                Math.min(Math.round(this.red * factor), 255),
                Math.min(Math.round(this.green * factor), 255),
                Math.min(Math.round(this.blue * factor), 255));
    }
}
