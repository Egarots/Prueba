package com.buildapp.testapp;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.buildapp.testapp.Helpers.Controller;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;


public class MainActivity extends AppCompatActivity {

    Controller controller= null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.loadingPanel).setVisibility(View.GONE);
            }

    public void onClick(View v)
    {
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.rvSongs);
        recyclerView.setAdapter(null);
        findViewById(R.id.loadingPanel).setVisibility(View.VISIBLE);
        EditText  searchText = (EditText) findViewById(R.id.editTextTextSearch);
        String text= searchText.getText().toString();
        String newText= text.replace(" ","+");
        controller= new Controller(this,this);
        controller.start(newText);
    }

    public void onClickAlbum(View v)
    {
        //TO DO
    }


}