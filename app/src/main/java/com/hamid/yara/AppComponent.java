package com.hamid.yara;

import com.hamid.yara.details.DetailsComponent;
import com.hamid.yara.details.DetailsModule;
import com.hamid.yara.favorites.FavoritesModule;
import com.hamid.yara.listing.ListingComponent;
import com.hamid.yara.listing.ListingModule;
import com.hamid.yara.network.NetworkModule;

import javax.inject.Singleton;

import dagger.Component;

/**
 * @author arunsasidharan
 * @author pulkitkumar
 */
@Singleton
@Component(modules = {
        AppModule.class,
        NetworkModule.class,
        FavoritesModule.class})
public interface AppComponent
{
    DetailsComponent plus(DetailsModule detailsModule);

    ListingComponent plus(ListingModule listingModule);
}
