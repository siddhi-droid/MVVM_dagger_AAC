package com.example.siddhi.mvvm_saleh1.di;

import com.example.siddhi.mvvm_saleh1.view.ui.MainActivity;
import dagger.Module;
import dagger.android.ContributesAndroidInjector;

/**
 * Created by siddhi on 3/5/2018.
 */

@Module public abstract class MainActivityModule {
  @ContributesAndroidInjector(modules = FragmentBuildersModule.class)
  abstract MainActivity contributeMainActivity();
}
