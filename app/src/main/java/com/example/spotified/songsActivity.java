package com.example.spotified;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
public class songsActivity extends AppCompatActivity {
    APIInterface apiInterface;
String MyPREFERENCES="MyPref";
    private RecyclerView recyclerView2;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_songs);
        recyclerView2 = (RecyclerView) findViewById(R.id.recyclerView2);

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        recyclerView2.setHasFixedSize(true);

        // use a linear layout manager
        layoutManager = new LinearLayoutManager(this);


        Bundle extras = getIntent().getExtras();
        String play_id = extras.getString("playListId");
        Log.e("PlayKaroIse",play_id);
       apiInterface= APIClient.getClient().create(APIInterface.class);

        SharedPreferences sharedPreferences=getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        String token= sharedPreferences.getString("Spotifytoken"," ");
        /**
         GET List Resources
         **/
        Call<TrackListActivity> call = apiInterface.doGetListResources(play_id, "Bearer "+token);
        call.enqueue(new Callback<TrackListActivity>() {
            @Override
            public void onResponse(Call<TrackListActivity> call, Response<TrackListActivity> response) {
                TrackListActivity resource = response.body();
                recyclerView2.setLayoutManager(layoutManager);
                mAdapter = new MyNewAdapter(resource,songsActivity.this);
                recyclerView2.setAdapter(mAdapter);
            }

            @Override
            public void onFailure(Call<TrackListActivity> call, Throwable t) {
            call.cancel();
            }
        });
    }
}