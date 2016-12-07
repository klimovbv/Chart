package com.spb.kbv.chart;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.text.style.RelativeSizeSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;

public class ChartFragment extends Fragment {
    public static Fragment newInstance() {
        Fragment chartFragment = new ChartFragment();
        Bundle args = new Bundle();


        return new ChartFragment();
    }

    private PieChart mChart;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.frag_simple_pie, container, false);
/*
        if (getArguments() != null && getArguments().containsKey(*//*BUNDLE_CONTENT*//*)) {
            getArguments().getString(*//*BUNDLE_CONTENT*//*);
        } else {
            throw new IllegalArgumentException("Must be created through newInstance(...)");
        }*/

        mChart = (PieChart) v.findViewById(R.id.pieChart1);
        mChart.getDescription().setEnabled(true);
        mChart.setRotationEnabled(false);

        mChart.setOnChartValueSelectedListener(new OnChartValueSelectedListener() {
            @Override
            public void onValueSelected(Entry e, Highlight h) {
                Toast.makeText(getActivity(), "Touch Event", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected() {

            }
        });


        /*Typeface tf = Typeface.createFromAsset(getActivity().getAssets(), "OpenSans-Light.ttf");*/

        /*mChart.setCenterTextTypeface(tf);*/
        mChart.setCenterText(generateCenterText());
        mChart.setCenterTextSize(10f);
        /*mChart.setCenterTextTypeface(tf);*/

        // radius of the center hole in percent of maximum radius
        /*mChart.setHoleRadius(30f);*/
        /*mChart.setTransparentCircleRadius(100f);*/

        Legend l = mChart.getLegend();
        l.setVerticalAlignment(Legend.LegendVerticalAlignment.BOTTOM);
        l.setHorizontalAlignment(Legend.LegendHorizontalAlignment.LEFT);
        l.setOrientation(Legend.LegendOrientation.VERTICAL);
        l.setDrawInside(false);

        mChart.setData(generatePieData());


        return v;
    }

    private PieData generatePieData() {
        int count = 4;

        ArrayList<PieEntry> entries1 = new ArrayList<PieEntry>();

        for(int i = 0; i < count; i++) {
            entries1.add(new PieEntry((float) ((Math.random() * 60) + 40), "Quarter " + (i+1)));
        }

        PieDataSet ds1 = new PieDataSet(entries1, "Quarterly Revenues 2015");
        ds1.setColors(ColorTemplate.VORDIPLOM_COLORS);
        ds1.setSliceSpace(2f);
        ds1.setValueTextColor(Color.WHITE);
        ds1.setValueTextSize(12f);
        ds1.setSelectionShift(0);


        PieData d = new PieData(ds1);

        /*d.setValueTypeface(tf);*/

        return d;
    }

    private SpannableString generateCenterText() {
        SpannableString s = new SpannableString("Revenues\nQuarters 2015");
        s.setSpan(new RelativeSizeSpan(2f), 0, 8, 0);
        s.setSpan(new ForegroundColorSpan(Color.GRAY), 8, s.length(), 0);
        return s;
    }
}
