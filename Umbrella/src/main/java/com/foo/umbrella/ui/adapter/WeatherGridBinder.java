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
import android.content.SharedPreferences;
import android.os.Build;
import android.preference.PreferenceManager;
import android.support.annotation.RequiresApi;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.ahamed.multiviewadapter.BaseViewHolder;
import com.ahamed.multiviewadapter.ItemBinder;
import com.foo.umbrella.R;
import com.foo.umbrella.data.model.ResponseModel.WeatherItemModel;
import com.foo.umbrella.utils.Utils;

public class WeatherGridBinder extends ItemBinder<WeatherItemModel, WeatherGridBinder.ItemViewHolder> {

    private Context context;

    public WeatherGridBinder(int insetInPixels, Context context) {
        super(new GridInsetDecoration(insetInPixels));
        this.context = context;
    }

    @Override
    public ItemViewHolder create(LayoutInflater layoutInflater, ViewGroup parent) {
        return new ItemViewHolder(layoutInflater.inflate(R.layout.item_grid, parent, false));
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void bind(ItemViewHolder holder, WeatherItemModel item) {
        //holder.itemView.sett;
        holder.time.setText(item.getTime());
        if (item.getCondition().equalsIgnoreCase("cloudy")) {
            holder.ivIcon.setImageResource(R.drawable.weather_cloudy);
        } else if (item.getCondition().equalsIgnoreCase("rain")) {
            holder.ivIcon.setImageResource(R.drawable.weather_rainy);

        } else if (item.getCondition().equalsIgnoreCase("chance of rain")) {
            holder.ivIcon.setImageResource(R.drawable.weather_rainy);

        } else {
            holder.ivIcon.setImageResource(R.drawable.weather_sunny);
        }

        if (item.isCoolTint()) {
            holder.ivIcon.setColorFilter(ContextCompat.getColor(context, R.color.weather_cool));
        } else if (item.isHotTint()) {
            holder.ivIcon.setColorFilter(ContextCompat.getColor(context, R.color.weather_warm));
        } else {
            holder.ivIcon.setColorFilter(ContextCompat.getColor(context, R.color.text_color_primary));
        }
        holder.temperature.setText(Utils.getSharedPreferenceUnits(context).equals("0") ? item.getMetric()+ (char) 0x00B0 : item.getFahrenheit()+(char) 0x00B0);

    }

    @Override
    public boolean canBindData(Object item) {
        return item instanceof WeatherItemModel;
    }

    static class ItemViewHolder extends BaseViewHolder<WeatherItemModel> {

        private TextView time;
        private AppCompatImageView ivIcon;

        private TextView temperature;

        ItemViewHolder(View itemView) {
            super(itemView);
            time = (TextView) itemView.findViewById(R.id.time);
            ivIcon = (AppCompatImageView) itemView.findViewById(R.id.iv_icon);
            temperature = (TextView) itemView.findViewById(R.id.temp);
        }

        @Override
        public int getDragDirections() {
            return ItemTouchHelper.LEFT
                    | ItemTouchHelper.UP
                    | ItemTouchHelper.RIGHT
                    | ItemTouchHelper.DOWN;
        }
    }
}