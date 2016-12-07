package com.spb.kbv.chart;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

public class MainActivity extends FragmentActivity  /*extends DemoBase implements SeekBar.OnSeekBarChangeListener,
        */ {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_piechart_with_fragment);

    }


}
