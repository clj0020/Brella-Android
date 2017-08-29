package com.madmensoftware.com.injection.module;

import com.madmensoftware.com.data.remote.ParseService;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

import retrofit2.Retrofit;

/**
 * Created by shivam on 29/5/17.
 */
@Module(includes = {NetworkModule.class})
public class ApiModule {
//
//    @Provides
//    @Singleton
//    ParseService providePokemonApi(Retrofit retrofit) {
//        return retrofit.create(ParseService.class);
//    }
}
