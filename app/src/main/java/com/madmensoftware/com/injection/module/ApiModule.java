package com.madmensoftware.com.injection.module;

import com.madmensoftware.com.data.remote.BarService;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

import retrofit2.Retrofit;

/**
 * Created by shivam on 29/5/17.
 */
@Module(includes = {NetworkModule.class})
public class ApiModule {

    @Provides
    @Singleton
    BarService providePokemonApi(Retrofit retrofit) {
        return retrofit.create(BarService.class);
    }
}
