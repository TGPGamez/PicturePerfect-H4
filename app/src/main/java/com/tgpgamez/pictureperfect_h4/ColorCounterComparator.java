package com.tgpgamez.pictureperfect_h4;

import java.util.Comparator;

/**
 * Class for ColorCounterComparator
 *
 * This class is used so we can compare ColorRGBCounter's
 *
 * @author Tobias
 * @version 1.0
 */
public class ColorCounterComparator implements Comparator<ColorRGBCounter> {
    /**
     * This method compares if one of the objects counter is higher than the other
     * @return -1 if the object a counter is greater than object b counter --> (Then put object a before object b)
     *          0 if the object a counter is the same as b counter --> (Then order stays the same)
     *          1 if the object a counter is less than object b counter --> (Then put object a after object b)
     */
    @Override
    public int compare(ColorRGBCounter a, ColorRGBCounter b) {
        return a.getCounter() > b.getCounter() ? -1 : a.getCounter() == b.getCounter() ? 0 : 1;
    }
}
