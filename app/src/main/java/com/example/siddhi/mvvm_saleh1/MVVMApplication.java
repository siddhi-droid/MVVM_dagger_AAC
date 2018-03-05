package com.example.siddhi.mvvm_saleh1;

import android.app.Activity;
import android.app.Application;
import com.example.siddhi.mvvm_saleh1.di.AppInjector;
import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.HasActivityInjector;
import javax.inject.Inject;

/**
 * Created by siddhi on 3/5/2018.
 */

public class MVVMApplication extends Application implements HasActivityInjector{

  @Inject DispatchingAndroidInjector<Activity> dispatchingAndroidInjector;

  @Override public void onCreate() {
    super.onCreate();
    AppInjector.init(this);
  }

  /*@Override public AndroidInjector<Activity> activityInjector() {
    return dispatchingAndroidInjector;
  }*/

  @Override public DispatchingAndroidInjector<Activity> activityInjector() {
    return dispatchingAndroidInjector;
  }
}
