package com.madmensoftware.com.injection;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Scope;

/**
 * Created by clj00 on 9/13/2017.
 */

@Scope
@Retention(RetentionPolicy.RUNTIME)
public @interface PerService {

}