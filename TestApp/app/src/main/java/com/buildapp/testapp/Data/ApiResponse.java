package com.buildapp.testapp.Data;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ApiResponse
{
    @SerializedName("results")
    private List<Song> songList;

    public List<Song> getSongList() {
        return songList;
    }

}
