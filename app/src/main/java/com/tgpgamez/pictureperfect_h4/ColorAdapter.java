package com.tgpgamez.pictureperfect_h4;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Class for ColorAdapter
 *
 * This class is used so we can get colorAdapter object
 *
 * @author Tobias
 * @version 1.0
 */
public class ColorAdapter extends ArrayAdapter<ColorRGBCounter> {
    public ColorAdapter(Context context, ArrayList<ColorRGBCounter> colors) {
        super(context, 0, colors);
    }

    /**
     * Method to get the view
     *
     * This gets the view that displays data at a specified position in data set
     *
     * @param position The position of the item in adapters data set of the item whose view we want
     * @param convertView The old view to reuse, if possible
     * @param parent The parent this view will eventually get attached to
     * @return The view to the data at the specified position
     */
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //Get the item
        ColorRGBCounter colorRGBCounter = getItem(position);

        //Check if there is a view, if not then inflate a layout
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.top_color_display, parent, false);
        }

        //Find the imageView
        ImageView imageView = (ImageView) convertView.findViewById(R.id.imageview_color);
        //Find the TextView that will be displaying position
        TextView tv_position = (TextView) convertView.findViewById(R.id.position_number);
        //Find the TextView that will be displaying color counter
        TextView tv_counter = (TextView)convertView.findViewById(R.id.color_count) ;

        //Set the background to RGB color
        imageView.setBackgroundColor(android.graphics.Color.rgb(colorRGBCounter.getColor().getRed(),
                                                                colorRGBCounter.getColor().getGreen(),
                                                                colorRGBCounter.getColor().getBlue()));
        //Set the position
        tv_position.setText("" + (position + 1));
        tv_counter.setText("Count: " + colorRGBCounter.getCounter());

        return convertView;
    }
}
