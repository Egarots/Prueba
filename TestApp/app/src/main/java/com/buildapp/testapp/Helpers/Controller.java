package com.buildapp.testapp.Helpers;


import com.buildapp.testapp.Data.ApiResponse;
import com.buildapp.testapp.Data.Song;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;
import java.util.function.Consumer;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Controller implements Callback<ApiResponse>
{
    private static final String BASE_URL ="https://itunes.apple.com/";

    public void start() {
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        ItunesApi itunesApi = retrofit.create(ItunesApi.class);

        Call<ApiResponse> call = itunesApi.getSongByTerm("Los+jaivas");
        call.enqueue(this);

    }

    @Override
    public void onResponse(Call<ApiResponse> call, Response<ApiResponse> response)
    {
        if(response.isSuccessful())
        {
            ApiResponse serviceResponse = response.body();

            serviceResponse.getSongList().forEach(new Consumer<Song>() {
                @Override
                public void accept(Song song) {
                    System.out.println(song.getTrackId());
                }
            });

        } else {
            System.out.println(response.errorBody());
        }
    }

    @Override
    public void onFailure(Call<ApiResponse> call, Throwable t) {

    }

}
