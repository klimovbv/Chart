package com.spb.kbv.chart;

import android.content.Context;
import android.graphics.drawable.GradientDrawable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
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
import java.util.HashMap;
import java.util.Map;

public class ChartAndLegendView extends LinearLayout {

    private PieChart mChart;
    private LinearLayout mLegend;
    private LayoutInflater mInflater;


    private HashMap<String, Integer> checkPoints () {
        HashMap<String, Integer> map = new HashMap<>();
        map.put("Отменено", 5);
        map.put("Завершено", 10);
        map.put("Начато", 2);
        map.put("Не начато", 3);
        return map;
    }

    public ChartAndLegendView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initComponent();
    }

    private void initComponent() {
        mInflater = (LayoutInflater) getContext()
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mInflater.inflate(R.layout.chart_with_legend, this);
        mChart = (PieChart) findViewById(R.id.pie_chart);
        mLegend = (LinearLayout) findViewById(R.id.legend);
        setupChartOptions();

    }

    private void setupChartOptions() {
        mChart.setRotationEnabled(false);

        mChart.setOnChartValueSelectedListener(new OnChartValueSelectedListener() {
            @Override
            public void onValueSelected(Entry e, Highlight h) {
                Toast.makeText(getContext(), "Touch Event", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected() {

            }
        });

        mChart.setCenterTextSize(10f);
        mChart.setHoleRadius(60f);
        mChart.setDescription(null);
        mChart.setDrawEntryLabels(false);
        mChart.getLegend().setEnabled(false);
        mChart.setData(populatePieData());

        populateLegend();
    }

    private void populateLegend() {
        Legend chartLegend = mChart.getLegend();
        /*chartLegend.setForm(Legend.LegendForm.CIRCLE);*/
        PieData data = mChart.getData();
        int colorCodes[] = data.getColors();
        String labels[] = chartLegend.getLabels();
        Log.d("myLogs", " number of colors = " + chartLegend.getColors().length +
        " values = " + labels[0]);
        for (int i = 0; i < colorCodes.length; i++) {

            View item = mInflater.inflate(R.layout.legend_item, null);
            ImageView color = (ImageView) item.findViewById(R.id.circle);
            TextView description = (TextView) item.findViewById(R.id.item_text);

            GradientDrawable bgShape = (GradientDrawable)color.getBackground();
            bgShape.setColor(colorCodes[i]);
            description.setText(labels[i]);

            mLegend.addView(item);

        }
    }

    private PieData populatePieData() {

        ArrayList<PieEntry> entries = new ArrayList<>();

        int numberOfCheckpoints = 0;
        for (Map.Entry entry : checkPoints().entrySet()) {
            int value = (Integer) entry.getValue();
            if (value > 0) {
                entries.add(new PieEntry(value, value + " " + entry.getKey().toString()));
                numberOfCheckpoints += (Integer) entry.getValue();
            }
        }

        mChart.setCenterText(String.valueOf(numberOfCheckpoints));

        PieDataSet pieData = new PieDataSet(entries, null);
        pieData.setColors(ColorTemplate.MATERIAL_COLORS);
        pieData.setSliceSpace(2f);
        pieData.setSelectionShift(0);
        pieData.setDrawValues(false);

        return new PieData(pieData);
    }

}
