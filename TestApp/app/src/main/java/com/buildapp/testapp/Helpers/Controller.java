package com.buildapp.testapp.Helpers;


import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.widget.Toast;

import com.buildapp.testapp.Data.ApiResponse;
import com.buildapp.testapp.Data.Song;
import com.buildapp.testapp.R;
import com.buildapp.testapp.ui.Adapters.SongAdapter;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;

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

            if(serviceResponse.getSongList().size()!=0)
            {
                FillSongs(serviceResponse.getSongList());
                activity.findViewById(R.id.loadingPanel).setVisibility(View.GONE);
            }
            else {
                activity.findViewById(R.id.loadingPanel).setVisibility(View.GONE);
                Toast toast = Toast.makeText(context, "Busqueda no encontrada", Toast.LENGTH_SHORT);
                toast.show();
            }

        } else {
            activity.findViewById(R.id.loadingPanel).setVisibility(View.GONE);
            Toast toast = Toast.makeText(context, "Problema obteniendo resultado", Toast.LENGTH_SHORT);
            toast.show();
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
    public void onFailure(Call<ApiResponse> call, Throwable t)
    {
        Toast toast = Toast.makeText(context, "Problema de conexion", Toast.LENGTH_SHORT);
        toast.show();
    }

    public ApiResponse getSearchresponse() {
        return Searchresponse;
    }

}
