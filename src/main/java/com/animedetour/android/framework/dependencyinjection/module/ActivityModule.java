/*
 * This file is part of the Anime Detour Android application
 *
 * Copyright (c) 2014 Anime Twin Cities, Inc. All rights Reserved.
 */
package com.animedetour.android.framework.dependencyinjection.module;

import android.app.Activity;
import dagger.Module;
import dagger.Provides;

import javax.inject.Singleton;

@Module(
    includes = {

    },
    complete = false,
    library = true
)
final public class ActivityModule
{
    final private Activity activity;

    public ActivityModule(Activity activity)
    {
        this.activity = activity;
    }

    @Provides @Singleton Activity provideActivity()
    {
        return activity;
    }
}