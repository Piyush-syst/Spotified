package com.example.spotified;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.spotify.android.appremote.api.ConnectionParams;
import com.spotify.android.appremote.api.Connector;
import com.spotify.android.appremote.api.PlayerApi;
import com.spotify.android.appremote.api.SpotifyAppRemote;

import com.spotify.protocol.client.CallResult;
import com.spotify.protocol.client.RequiredFeatures;
import com.spotify.protocol.client.Subscription;
import com.spotify.protocol.types.Empty;
import com.spotify.protocol.types.PlayerState;
import com.spotify.protocol.types.Track;
import com.spotify.sdk.android.authentication.AuthenticationClient;
import com.spotify.sdk.android.authentication.AuthenticationRequest;
import com.spotify.sdk.android.authentication.AuthenticationResponse;

public class Player extends AppCompatActivity {
    private static final String CLIENT_ID = "6642af4c9904486a80f9d62cc417168e";
    private static final String REDIRECT_URI = "https://com.example.spotified/callback";
    private static final int REQUEST_CODE = 1337;
    String MyPREFERENCES="MyPref";
    ConnectionParams connectionParams;
 private SpotifyAppRemote mSpotifyAppRemote;
    com.example.spotified.Track song;
    int i=2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player);
        Bundle extras = getIntent().getExtras();
        song = extras.getParcelable("song");
//        CallResult<Empty> play= mSpotifyAppRemote.getPlayerApi().play(song.getUri());


        AuthenticationRequest.Builder builder =
                new AuthenticationRequest.Builder(CLIENT_ID, AuthenticationResponse.Type.TOKEN, REDIRECT_URI);
        builder.setScopes(new String[]{"streaming", "playlist-read-private", "user-read-email", "user-read-playback-position"});
        AuthenticationRequest request = builder.build();

        AuthenticationClient.openLoginActivity(this, REQUEST_CODE, request);

    }
// On the begining : Authentication with Spotify
    @Override
    protected void onStart() {
        super.onStart();
        SharedPreferences sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        String token = sharedpreferences.getString("Spotifytoken", "EmptyToken");

        connectionParams =
                new ConnectionParams.Builder(CLIENT_ID)
                        .setRedirectUri(REDIRECT_URI)
                        .showAuthView(true)
                        .build();
        SpotifyAppRemote.connect(this, connectionParams,
                new Connector.ConnectionListener() {

                    @Override
                    public void onConnected(SpotifyAppRemote spotifyAppRemote) {
                        mSpotifyAppRemote = spotifyAppRemote;

                        connected();
                    }

                    @Override
                    public void onFailure(Throwable throwable) {
                        Log.e("whattf","   ffff");
                    }

                    private void connected() {
                        mSpotifyAppRemote.getPlayerApi().play(song.getUri());

                        Button plpause= findViewById(R.id.playpause);
                        plpause.setBackgroundResource(R.drawable.ic_pause);
                        plpause.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                if(i%2==0)
                                {
                                    plpause.setBackgroundResource(R.drawable.ic_play);
                                    CallResult<Empty> cl= mSpotifyAppRemote.getPlayerApi().pause();
                                   String cla= RequiredFeatures.FEATURES_V1;
                                   Log.e("Features", cla);
                                   i++;
                                }
                                else
                                {
                                    plpause.setBackgroundResource(R.drawable.ic_pause);
                                    CallResult<Empty> cl= mSpotifyAppRemote.getPlayerApi().resume();
                                        i++;
                                }
                            }
                        });

                    }

                    private void getList(String token) {

                    }
                });


    }





}