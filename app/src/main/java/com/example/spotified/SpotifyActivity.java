package com.example.spotified;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;


import com.spotify.android.appremote.api.ConnectionParams;
import com.spotify.android.appremote.api.Connector;
import com.spotify.android.appremote.api.SpotifyAppRemote;
import com.spotify.protocol.client.Subscription;
import com.spotify.protocol.types.PlayerState;
import com.spotify.protocol.types.Track;
import com.spotify.sdk.android.authentication.AuthenticationClient;
import com.spotify.sdk.android.authentication.AuthenticationRequest;
import com.spotify.sdk.android.authentication.AuthenticationResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SpotifyActivity extends AppCompatActivity {
    String MyPREFERENCES = "MyPref";
    private static final String CLIENT_ID = "0bdd186913574cd3ae5a119af38f54a1";
    private static final String REDIRECT_URI = "http://com.example.spotified/callback";
    private static final int REQUEST_CODE = 1337;
    private SpotifyAppRemote mSpotifyAppRemote;
    ConnectionParams connectionParams;
    APIInterface apiInterface;
    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spotify);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        recyclerView.setHasFixedSize(true);

        // use a linear layout manager
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        // specify an adapter (see also next example)



        connectionParams =
                new ConnectionParams.Builder(CLIENT_ID)
                        .setRedirectUri(REDIRECT_URI)
                        .showAuthView(true)
                        .build();
        AuthenticationRequest.Builder builder =
                new AuthenticationRequest.Builder(CLIENT_ID, AuthenticationResponse.Type.TOKEN, REDIRECT_URI);
        builder.setScopes(new String[]{"streaming", "playlist-read-private", "user-read-email", "user-read-playback-position"});
        AuthenticationRequest request = builder.build();

        AuthenticationClient.openLoginActivity(this, REQUEST_CODE, request);
       }
            @Override
    protected void onStart() {
        super.onStart();
        SharedPreferences sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        String token = sharedpreferences.getString("Spotifytoken", "EmptyToken");


        if (token.equals("EmptyToken")) {
            Toast.makeText(getApplicationContext(), "NO token", Toast.LENGTH_LONG).show();
        } else {

        }
        SpotifyAppRemote.connect(this, connectionParams,
                new Connector.ConnectionListener() {

                    @Override
                    public void onConnected(SpotifyAppRemote spotifyAppRemote) {
                        mSpotifyAppRemote = spotifyAppRemote;
                        Log.d("MainActivity3", "Connected! Yay!");
                        spotifyAppRemote.getContentApi();
                        // Now you can start interacting with App Remote
                        connected();
                    }

                    @Override
                    public void onFailure(Throwable throwable) {

                    }

                    private void connected() {
                        mSpotifyAppRemote.getPlayerApi().play("spotify:playlist:37i9dQZF1DX2sUQwD7tbmL");
                        mSpotifyAppRemote.getPlayerApi()
                                .subscribeToPlayerState()
                                .setEventCallback(new Subscription.EventCallback<PlayerState>() {
                                    @Override
                                    public void onEvent(PlayerState playerState) {
                                        final Track track = playerState.track;
                                        if (track != null) {
                                            Log.d("MainActivity1", track.name + " by " + track.artist.name);
                                        }
                                    }
                                });
                    }

                    private void getList(String token) {

                    }
                });
    }


    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

            super.onActivityResult(requestCode, resultCode, data);



        if (requestCode == REQUEST_CODE) {
            AuthenticationResponse response = AuthenticationClient.getResponse(resultCode, data);

            switch (response.getType()) {
                // Response was successful and contains auth token
                case TOKEN:
                    Log.d("Token", "" + response.getAccessToken());
                    SharedPreferences sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedpreferences.edit();
                    editor.putString("Spotifytoken", response.getAccessToken());
                    editor.commit();
                    apiInterface = APIClient.getClient().create(APIInterface.class);
                    Call<SpotifyResult> call = apiInterface.doGetListResources("Bearer "+response.getAccessToken());
                    call.enqueue(new Callback<SpotifyResult>() {
                        @Override
                        public void onResponse(Call<SpotifyResult> call, Response<SpotifyResult> response) {


                            Log.d("TAG", response.code() + "");

                            String displayResponse = "";

                            SpotifyResult resource = response.body();
//                responseText.setText(displayResponse);

                            mAdapter = new MyAdapter(resource,SpotifyActivity.this);
                            recyclerView.setAdapter(mAdapter);
                        }

                        @Override
                        public void onFailure(Call<SpotifyResult> call, Throwable t) {
                            call.cancel();;

                        }
                    });
                    // Handle successful response
                    break;

                // Auth flow returned an error
                case ERROR:
                    // Handle error response
                    break;

                // Most likely auth flow was cancelled
                default:
                    break;
                // Handle other cases
            }
        }
    }
}
