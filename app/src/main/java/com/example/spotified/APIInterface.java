package  com.example.spotified;

import com.example.spotified.Item;
import com.example.spotified.SpotifyResult;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

interface APIInterface {

    @GET("v1/me/playlists")
    Call<SpotifyResult> doGetListResources(@Header("Authorization") String Token);
    @GET("v1/playlists/{playlist_id}/tracks")
    Call<TrackListActivity> doGetListResources(@Path ("playlist_id") String play_id,@Header("Authorization") String Token);
}