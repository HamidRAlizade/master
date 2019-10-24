package com.hamid.yara.details;

import com.hamid.yara.Movie;
import com.hamid.yara.Review;
import com.hamid.yara.Video;

import java.util.List;

/**
 * @author arun
 */
interface MovieDetailsView
{
    void showDetails(Movie movie);
    void showTrailers(Video trailers);
    void showReviews(List<Review> reviews);
    void showFavorited();
    void showUnFavorited();
}
