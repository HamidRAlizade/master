package com.hamid.yara.network;

import com.hamid.yara.MoviesWraper;
import com.hamid.yara.Video;

import retrofit2.http.GET;
import io.reactivex.Observable;
import retrofit2.http.Query;

/**
 * Created by ivan on 8/20/2017.
 */

public interface TmdbWebService {

    @GET(" ")
    Observable<MoviesWraper> popularMovies(@Query("s")String s);

    @GET(" ")
    Observable<Video> trailers(@Query("i") String movieId);



}
