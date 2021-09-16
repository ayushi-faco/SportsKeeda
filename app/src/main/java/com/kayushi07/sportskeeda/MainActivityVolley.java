package com.kayushi07.sportskeeda;

import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivityVolley extends AppCompatActivity{
        private static final String JSON_URL = "https://s3.ap-southeast-1.amazonaws.com/data.sportskeeda.com/app-sample/v1.json";

        //listview object
        ListView listView;

        //the card list where we will store all the card objects after parsing json
        List<Cards> cardList;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);

            //initializing listview and card list
            listView = (ListView) findViewById(R.id.listView);
            cardList = new ArrayList<>();

            //this method will fetch and parse the data
            loadCardsList();
        }

        private void loadCardsList() {
            //getting the progressbar
            final ProgressBar progressBar = (ProgressBar) findViewById(R.id.progressBar);

            //making the progressbar visible
            progressBar.setVisibility(View.VISIBLE);

            //creating a string request to send request to the url
            StringRequest stringRequest = new StringRequest(Request.Method.GET, JSON_URL,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            //hiding the progressbar after completion
                            progressBar.setVisibility(View.INVISIBLE);


                            try {
                                //getting the whole json object from the response
                                JSONObject obj = new JSONObject(response);

                                //we have the array named card inside the object
                                //so here we are getting that json array
                                JSONArray cardArray = obj.getJSONArray("cards");

                                //now looping through all the elements of the json array
                                for (int i = 0; i < cardArray.length(); i++) {
                                    //getting the json object of the particular index inside the array
                                    JSONObject cardObject = cardArray.getJSONObject(i);

                                    //creating a card object and giving them the values from json object
                                    Cards card = new Cards(cardObject.getString("title"), cardObject.getString("thumbnail"), cardObject.getString("published_date"));

                                    //adding the card to cardlist
                                    cardList.add(card);
                                }

                                //creating custom adapter object
                                ListViewAdapter adapter = new ListViewAdapter(cardList, getApplicationContext());

                                //adding the adapter to listview
                                listView.setAdapter(adapter);

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            //displaying the error in toast if occurrs
                            Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });

            //creating a request queue
            RequestQueue requestQueue = Volley.newRequestQueue(this);

            //adding the string request to request queue
            requestQueue.add(stringRequest);
        }
}
