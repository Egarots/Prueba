package com.buildapp.testapp.Helpers;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RestHelpers {

    public class TMDB_Response {

        @SerializedName("results")
        @Expose
        private ArrayList<Song> results;

        public ArrayList<Movie> getResults() {
            return results;
        }

        @NonNull
        @Override
        public String toString() {
            return results.toString();
        }
    }

}
