package com.kayushi07.sportskeeda;

import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.JsonElement;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RetrofitActivity extends AppCompatActivity {

    ListView superListView;
    List<Cards> cardList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        superListView = findViewById(R.id.listView);
        cardList = new ArrayList<>();

        Call<JsonElement> jsonCall = RetroClient.getInstance().getMyApi().getsuperCards();
        final ProgressBar progressBar = (ProgressBar) findViewById(R.id.progressBar);

        //making the progressbar visible
        progressBar.setVisibility(View.VISIBLE);

        jsonCall.enqueue(new Callback<JsonElement>() {
            @Override
            public void onResponse(Call<JsonElement> call, Response<JsonElement> response) {

                progressBar.setVisibility(View.INVISIBLE);

                try {
                    //getting the whole json object from the response
                    JSONObject obj = new JSONObject(response.body().toString());

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
                    superListView.setAdapter(adapter);

                } catch (JSONException e) {
                    progressBar.setVisibility(View.INVISIBLE);
                    e.printStackTrace();
                    Toast.makeText(getApplicationContext(), "An error has occurred :" + e.getMessage(), Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<JsonElement> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "An error has occurred :" + t.toString(), Toast.LENGTH_LONG).show();
            }
        });
    }
}