package com.tgpgamez.pictureperfect_h4;

import android.graphics.Bitmap;
import android.graphics.Color;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Class for ColorProcessor
 *
 * This class is used to process a bitmap and sort after dominance
 *
 * @author Tobias
 * @version 1.0
 */
public class ColorProcessor implements Runnable {

    ArrayList<ColorRGBCounter> colorRGBCounters;
    Bitmap bitmap;
    List<ProcessorListener> listeners;


    public ColorProcessor(Bitmap bitmap) {
        this.bitmap = bitmap;
        this.listeners = new ArrayList<>();
    }

    public ColorProcessor() {
        this.listeners = new ArrayList<>();
    }

    @Override
    public void run() {
        process();
    }


    /**
     * Method for processing the bitmap and getting
     * all the colors while sorting them with a distance difference
     */
    private void process() {
        colorRGBCounters = new ArrayList<>();
        bitmap = downScaleBitmap(10, 500000);

        for (int i = 0; i < bitmap.getWidth(); i++) {
            for (int j = 0; j < bitmap.getHeight(); j++) {
                //Get rgb int out from pixel coordinate
                int rgb = bitmap.getPixel(i, j);
                //Parse rgb to a color
                ColorRGB color = ColorRGB.parse(rgb);

                //Check if colorRGBCounters is empty
                if (colorRGBCounters.isEmpty()) {
                    colorRGBCounters.add(new ColorRGBCounter(color, 1));
                } else {
                    boolean result = false;
                    //For each colorRGBCounter then check if the color distance is higher than 30
                    for (ColorRGBCounter colorRGBCounter : colorRGBCounters) {
                        int distance = colorRGBCounter.getColor().distance(color);
                        //If distance is less or equal to 30 then add to colorRGBCounter count
                        if (distance <= 30) {
                            colorRGBCounter.addToCount(1);
                            result = true;
                            break;
                        }
                    }
                    //if the color wasn't in the colorRGBCounters, then add new.
                    if (!result) {
                        colorRGBCounters.add(new ColorRGBCounter(color, 1));
                    }
                }
            }
        }
        //Sort the list with our ColorCounterComparator
        Collections.sort(colorRGBCounters, new ColorCounterComparator());
        //Notify all listeners
        notifyListenersOnSorted();
    }

    /**
     * Method is used to down scale Bitmap
     * @param size How much to down scale
     * @param scaleDownAt Where to scale down when the width * height of the bitmap reaches
     * @return down scaled bitmap or old bitmap
     */
    private Bitmap downScaleBitmap(int size, int scaleDownAt) {
        if (bitmap.getWidth() * bitmap.getHeight() >= scaleDownAt) {
            return Bitmap.createScaledBitmap(bitmap, bitmap.getWidth() / size, bitmap.getHeight() / size, true);
        }
        return bitmap;
    }

    /**
     * Method to add a listener
     * @param listener Listener to add
     */
    public void addListener(ProcessorListener listener) {
        listeners.add(listener);
    }

    /**
     * Method to set the bitmap
     * @param bitmap The bitmap to set
     */
    public void setBitmap(Bitmap bitmap) {
        this.bitmap = bitmap;
    }

    /**
     * Method to notify all the listeners
     */
    private void notifyListenersOnSorted() {
        for (ProcessorListener l : listeners) {
            l.onSorted(colorRGBCounters);
        }
    }
}
