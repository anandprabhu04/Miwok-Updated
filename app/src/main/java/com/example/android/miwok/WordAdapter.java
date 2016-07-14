package com.example.android.miwok;

import android.app.Activity;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Custom array adapter for Word
 */
public class WordAdapter extends ArrayAdapter<Word> {

    private int mListTextBackgroundColor;

    public WordAdapter(Activity context, ArrayList<Word> words, int colorId) {
        super(context, 0, words);
        mListTextBackgroundColor = colorId;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Check if the existing view is being reused, otherwise inflate the view
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.list_item, parent, false);
        }

        // Get the {@link Word} object located at this position in the list
        Word currentWord = getItem(position);

        // Find the ImageView in the list_item.xml layout with the ID image
        ImageView imageView = (ImageView) listItemView.findViewById(R.id.image);

        // Check if the word has an image associated or not
        if (currentWord.hasImage()) {
            // Get the image from the Word object and display it in the ImageView
            imageView.setImageResource(currentWord.getmImageResourceId());

            // Set the view to be VISIBLE
            imageView.setVisibility(View.VISIBLE);
        } else {
            // Hide the image view completely
            imageView.setVisibility(View.GONE);
        }

        // Find the list item text container and set the background color
        View listTextContainer = listItemView.findViewById(R.id.text_container);

        // Find the color that the resource ID maps to
        int color = ContextCompat.getColor(getContext(), mListTextBackgroundColor);

        // Set the background of the text container
        listTextContainer.setBackgroundColor(color);

        // Find the TextView in the list_item.xml layout with the ID version_name
        TextView nameTextView = (TextView) listItemView.findViewById(R.id.miwok_text);
        // Get the version name from the current AndroidFlavor object and
        // set this text on the name TextView
        nameTextView.setText(currentWord.getmMiwokTranslation());

        // Find the TextView in the list_item.xml layout with the ID version_number
        TextView numberTextView = (TextView) listItemView.findViewById(R.id.english_text);
        // Get the version number from the current AndroidFlavor object and
        // set this text on the number TextView
        numberTextView.setText(currentWord.getmDefaultTranslation());

        // Return the whole list item layout (containing 2 TextViews)
        // so that it can be shown in the ListView
        return listItemView;
    }

}
