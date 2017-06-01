package com.nurmuhammadsexample.itserver.dompetdigital;

/**
 * Created by IT Server on 6/1/2017.
 */

import android.content.Context;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.animation.AlphaAnimation;

import com.androidquery.AQuery;

public class BaseApp extends AppCompatActivity {
    public Context context;
    public AQuery aQuery;
    public AlphaAnimation BtnAnimasi = new AlphaAnimation(1F, 0.1F);

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = this;
        aQuery = new AQuery(context);
    }
}
