package com.tgpgamez.pictureperfect_h4;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    static final int REQUEST_IMAGE_CAPTURE = 1;

    ImageView imageView;
    ListView listView;
    ArrayList<ColorRGBCounter> colorRGBCounters;
    ColorProcessor colorProcessor;

    /**
     * Make a ProcessorListener to update the UI with most dominant colors
     */
    ProcessorListener processorListener = new ProcessorListener() {
        @Override

        public void onSorted(ArrayList<ColorRGBCounter> colorRGBCounters) {
            //Make a new Adapter using the ColorAdapter
            ColorAdapter adapter = new ColorAdapter(MainActivity.this, colorRGBCounters);

            //Set the adapter of the listView
            listView.setAdapter(adapter);
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imageView = findViewById(R.id.imageView);
        listView = findViewById(R.id.viewlist_Color);
        //Make new instance of the ColorProcessor
        colorProcessor = new ColorProcessor();
        //Add listener
        colorProcessor.addListener(processorListener);
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            imageView.setImageBitmap(imageBitmap);
            colorProcessor.setBitmap(imageBitmap);
            //runOnUiThread is needed to update the UI with a Thread/Runnable because UI can only be updated on it's thread
            runOnUiThread(colorProcessor);
        }
    }

    public void takePhoto(View view) {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        try {
            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
        } catch (ActivityNotFoundException e) {
            // display error state to the user
        }
    }
}