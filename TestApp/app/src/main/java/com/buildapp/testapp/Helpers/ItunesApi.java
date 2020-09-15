package com.buildapp.testapp.Helpers;

import com.buildapp.testapp.Data.ApiResponse;
import com.buildapp.testapp.Data.Song;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ItunesApi
{
    @GET("search?mediaType=music&limit=100")
    Call<ApiResponse> getSongByTerm(@Query("term") String termValue);

}
