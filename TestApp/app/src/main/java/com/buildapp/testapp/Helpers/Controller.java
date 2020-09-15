package com.buildapp.testapp.Helpers;


import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.text.Editable;

import com.buildapp.testapp.Data.ApiResponse;
import com.buildapp.testapp.Data.Song;
import com.buildapp.testapp.MainActivity;
import com.buildapp.testapp.R;
import com.buildapp.testapp.ui.Adapters.SongAdapter;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;
import java.util.function.Consumer;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class Controller implements Callback<ApiResponse>
{
    private Context context;
    private Activity activity;
    private final static String PREF_FILE = "Preferences";
    private ApiResponse Searchresponse = new ApiResponse();
    private static final String BASE_URL ="https://itunes.apple.com/";


    public Controller(Context context, Activity activity)
    {
        this.context=context;
        this.activity=activity;
    }

    public void start(String searchValue) {
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        ItunesApi itunesApi = retrofit.create(ItunesApi.class);

        Call<ApiResponse> call = itunesApi.getSongByTerm(searchValue);
        call.enqueue(this);
    }

    @Override
    public void onResponse(Call<ApiResponse> call, Response<ApiResponse> response)
    {
        if(response.isSuccessful())
        {

            ApiResponse serviceResponse = response.body();
            FillSongs(serviceResponse.getSongList());

        } else {
            System.out.println(response.errorBody());
        }
    }

    public void FillSongs(List<Song> songList)
    {
        SongAdapter adapter = new SongAdapter(songList);
        RecyclerView rvSongs = (RecyclerView) activity.findViewById(R.id.rvSongs);
        rvSongs.setAdapter(adapter);
        rvSongs.setLayoutManager(new LinearLayoutManager(context));

    }

    @Override
    public void onFailure(Call<ApiResponse> call, Throwable t) {

    }

    public ApiResponse getSearchresponse() {
        return Searchresponse;
    }

}
