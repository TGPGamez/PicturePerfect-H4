package com.tgpgamez.pictureperfect_h4;

import java.util.ArrayList;

/**
 * Class for ProcessorListener
 *
 * This interface is used to implement observable methods for the processor
 *
 * @author Tobias
 * @version 1.0
 */
public interface ProcessorListener {

    /**
     * Method is used to when the colorRGBCounter's is sorted, then it will activate
     * @param colorRGBCounters list of alle the colorRGBCounter's
     */
    public void onSorted(ArrayList<ColorRGBCounter> colorRGBCounters);
}
