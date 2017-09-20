/*
 * Copyright 2017 Riyaz Ahamed
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.foo.umbrella.ui.adapter;

import android.content.Context;
import android.content.res.Resources;
import android.util.DisplayMetrics;

import com.ahamed.multiviewadapter.DataItemManager;
import com.ahamed.multiviewadapter.DataListManager;
import com.ahamed.multiviewadapter.RecyclerAdapter;
import com.ahamed.multiviewadapter.util.SimpleDividerDecoration;
import com.foo.umbrella.data.model.ResponseModel.WeatherItemModel;

import java.util.List;

public class WeatherListAdapter extends RecyclerAdapter {

    private DataListManager<WeatherItemModel> gridItemsManager;

    private DataListManager<WeatherItemModel> hourlyGridViewManager;

    private List<WeatherItemModel> gridDataList;

    public WeatherListAdapter(Context context) {
        SimpleDividerDecoration simpleItemDecoration = new SimpleDividerDecoration(context, SimpleDividerDecoration.VERTICAL);

        gridItemsManager = new DataListManager<>(this);
        hourlyGridViewManager = new DataListManager<>(this);

        addDataManager(new DataItemManager<>(this, new Header("Today")));

        addDataManager(gridItemsManager);

        addDataManager(new DataItemManager<>(this, new Header("Tommorrow")));

        addDataManager(hourlyGridViewManager);

        registerBinder(new WeatherItemHeader());
        registerBinder(new WeatherGridBinder(convertDpToPixel(4, context), context));

    }

    public static int convertDpToPixel(float dp, Context context) {
        Resources resources = context.getResources();
        DisplayMetrics metrics = resources.getDisplayMetrics();
        return (int) (dp * (metrics.densityDpi / DisplayMetrics.DENSITY_DEFAULT));
    }


    public void addGridItem(List<WeatherItemModel> dataList) {
        this.gridDataList = dataList;
        gridItemsManager.addAll(gridDataList);
    }

    public void addHourlyGridView(List<WeatherItemModel> dataList) {
        this.gridDataList.clear();
        this.gridDataList = dataList;
        hourlyGridViewManager.addAll(gridDataList);
    }
}