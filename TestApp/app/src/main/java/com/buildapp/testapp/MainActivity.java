package com.buildapp.testapp;

import android.os.Bundle;
import android.util.Log;

import com.buildapp.testapp.Data.Song;
import com.buildapp.testapp.Helpers.Controller;
import com.buildapp.testapp.Helpers.ItunesApi;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BottomNavigationView navView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_notifications)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(navView, navController);

        Controller controller= new Controller();
        controller.start();
        //REST API
        /*Retrofit retrofit = new Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create()).build();
        ItunesApi itunesAPI = retrofit.create(ItunesApi.class);

        Call<Song> call = itunesAPI.getSongByTerm("Los+Jaivas");
        call.enqueue(new Callback<Song>() {
            @Override
            public void onResponse(Call<Song> call, Response<Song> response)
            {
                if(response.isSuccessful())
                {
                    Song songs = response.body();
                }
                else {
                Log.e("ERROR-APP", "Error en la busqueda");
                }
            }
            @Override
            public void onFailure(Call<Song> call, Throwable t)
            {
                Log.e("ERROR-APP", "No se pudo conectar al servidor");
            }
        });*/


    }

}