package com.kayushi07.sportskeeda;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

public class ListViewAdapter extends ArrayAdapter<Cards> {

    //the card list that will be displayed
    private List<Cards> cardList;

    //the context object
    private Context mCtx;

    //here we are getting the cardlist and context 
    //so while creating the object of this adapter class we need to give cardlist and context
    public ListViewAdapter(List<Cards> cardList, Context mCtx) {
        super(mCtx, R.layout.list_item, cardList);
        this.cardList = cardList;
        this.mCtx = mCtx;
    }

    //this method will return the list item 
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //getting the layoutinflater
        LayoutInflater inflater = LayoutInflater.from(mCtx);

        //creating a view with our xml layout
        View listViewItem = inflater.inflate(R.layout.list_item, null, true);

        //getting text views
        TextView textViewName = listViewItem.findViewById(R.id.textViewName);
        TextView textView = listViewItem.findViewById(R.id.textViewDate);
        ImageView  imageView = listViewItem.findViewById(R.id.thumb);

        //Getting the card for the specified position
        Cards card = cardList.get(position);

        //setting card values to textviews
        textViewName.setText(card.getName());
        textView.setText(card.getPublishedDate());
        Glide.with(mCtx)
                .load(card.getImageUrl())
                .into(imageView);

        //returning the listitem 
        return listViewItem;
    }
}