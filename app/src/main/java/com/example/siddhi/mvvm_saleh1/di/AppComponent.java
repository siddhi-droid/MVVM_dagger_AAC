package com.example.siddhi.mvvm_saleh1.di;

import android.app.Application;
import com.example.siddhi.mvvm_saleh1.MVVMApplication;
import com.example.siddhi.mvvm_saleh1.view.ui.MainActivity;
import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjection;
import dagger.android.AndroidInjectionModule;
import javax.inject.Singleton;

/**
 * Created by siddhi on 3/5/2018.
 */
@Singleton @Component(modules = {
    AndroidInjectionModule.class, AppModule.class, MainActivityModule.class
})

public interface AppComponent {
  @Component.Builder
  interface Builder {
    @BindsInstance Builder application(Application application);
    AppComponent build();
  }
  void inject(MVVMApplication mvvmApplication);
}
