package com.db.app.travelersapp.view;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Paint.Align;

import org.achartengine.ChartFactory;
import org.achartengine.chart.BarChart;
import org.achartengine.model.CategorySeries;
import org.achartengine.model.XYMultipleSeriesDataset;
import org.achartengine.renderer.XYMultipleSeriesRenderer;
import org.achartengine.renderer.XYSeriesRenderer;

import java.util.List;

import com.db.app.travelersapp.util.Pair;

public class ChartGenerator {
    private static double yMaxNum = 0.0;
    /*
     * A Render defining char styles Including title, text color, background
     * colors, etc.
     */
    private static XYMultipleSeriesRenderer mRenderer = new XYMultipleSeriesRenderer();
    private static XYSeriesRenderer renderer = new XYSeriesRenderer();

    static {
        mRenderer.setChartTitleTextSize(30);
        mRenderer.setLabelsTextSize(30);
        mRenderer.setLegendTextSize(20);
        mRenderer.setZoomEnabled(false, false);
        mRenderer.setYLabelsAlign(Align.RIGHT);
        mRenderer.setShowGridY(true);
        mRenderer.setXLabelsColor(Color.RED);
        mRenderer.setYLabelsColor(0, Color.RED);
        mRenderer.setXLabels(0);
        String[] ratings = new String[] { "1 Star","2 Star","3 Star","4 Star","5 Star",""};
        for (int i = 0; i < ratings.length; i++) {
            mRenderer.addXTextLabel(i + 1, ratings[i]);
        }
        mRenderer.setShowGridX(true);
        mRenderer.setXTitle("Rating");
        mRenderer.setYTitle("Number of Customers");
        mRenderer.setAxisTitleTextSize(30);
        mRenderer.setMargins(new int[] { 80, 40, 60, 40 });
        mRenderer.setPanEnabled(false, false);
        mRenderer.setZoomButtonsVisible(false);
        mRenderer.setBarSpacing(0.6f);
        mRenderer.setBarWidth(30);
        renderer.setColor(Color.WHITE);
        renderer.setDisplayChartValues(true);
        renderer.setChartValuesTextAlign(Align.RIGHT);
        renderer.setChartValuesTextSize(30f);

        mRenderer.addSeriesRenderer(renderer);
    }

    private static XYMultipleSeriesDataset createDataSet(List<Pair> pairList) {
        XYMultipleSeriesDataset mDataset = new XYMultipleSeriesDataset();
        CategorySeries series = new CategorySeries("Number of Customers / Star Rating");
        for (Pair pair : pairList) {
            if (pair.getNumber() > yMaxNum) {
                yMaxNum = pair.getNumber();
            }
            series.add(pair.getMonth() + "", pair.getNumber());
        }
        mRenderer.setYAxisMax(yMaxNum + 1.0);
        mDataset.addSeries(series.toXYSeries());
        return mDataset;
    }

    /**
     * Get a bar chart
     *
     * @param context
     * @param pairList
     * @return
     */
    public static Intent getBarChart(Context context, String title,
                                     List<Pair> pairList) {
        mRenderer.setChartTitle(title);
        return ChartFactory.getBarChartIntent(context, createDataSet(pairList),
                mRenderer, BarChart.Type.DEFAULT);
    }

    /**
     * Get a line chart
     *
     * @param context
     * @param pairList
     * @return
     */
    public static Intent getLineChart(Context context, String title,
                                      List<Pair> pairList) {
        mRenderer.setChartTitle(title);
        return ChartFactory.getLineChartIntent(context,
                createDataSet(pairList), mRenderer);
    }

}
