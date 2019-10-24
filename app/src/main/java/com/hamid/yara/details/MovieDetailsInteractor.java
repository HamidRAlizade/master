package com.hamid.yara.details;

import com.hamid.yara.Video;

import io.reactivex.Observable;

/**
 * @author arun
 */
public interface MovieDetailsInteractor
{
    Observable<Video> getTrailers(String id);

}
