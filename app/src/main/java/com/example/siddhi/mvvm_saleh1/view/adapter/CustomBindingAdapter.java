package com.example.siddhi.mvvm_saleh1.view.adapter;

import android.databinding.BindingAdapter;
import android.util.Log;
import android.view.View;

/**
 * Created by siddhi on 2/22/2018.
 */

public class CustomBindingAdapter {
  @BindingAdapter("visibleGone") public static void showHide(View view, boolean show) {
    view.setVisibility(show ? View.VISIBLE : View.GONE);
    Log.e("show","" + show);
    Log.d("show2","" + view.getClass().toString());
  }
}
