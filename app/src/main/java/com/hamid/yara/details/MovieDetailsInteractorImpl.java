package com.hamid.yara.details;

import com.hamid.yara.Video;
import com.hamid.yara.network.TmdbWebService;

import io.reactivex.Observable;

/**
 * @author arun
 */
class MovieDetailsInteractorImpl implements MovieDetailsInteractor {

    private TmdbWebService tmdbWebService;

    MovieDetailsInteractorImpl(TmdbWebService tmdbWebService) {
        this.tmdbWebService = tmdbWebService;
    }

    @Override
    public Observable<Video> getTrailers(final String id) {
        return tmdbWebService.trailers(id);
    }



}
