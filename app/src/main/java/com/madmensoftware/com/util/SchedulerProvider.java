package com.madmensoftware.com.util;

import io.reactivex.Scheduler;

/**
 * Created by clj00 on 8/27/2017.
 */

public interface SchedulerProvider {

    Scheduler ui();

    Scheduler computation();

    Scheduler io();

}
