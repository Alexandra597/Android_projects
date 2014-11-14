package com.carparking;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA.
 * User: user
 * Date: 07.10.14
 * Time: 12:56
 * To change this template use File | Settings | File Templates.
 */
public class CarsAdapter extends BaseAdapter {
    Context context;
    LayoutInflater lInflater;
    ArrayList<Car> cars;

    public CarsAdapter(Context ctx, ArrayList<Car> cars_list) {
        context = ctx;
        cars = cars_list;
        lInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public Context getContext() {
        return context;
    }

    @Override
    public int getCount() {
        return cars.size();
    }

    @Override
    public Object getItem(int position) {
        return cars.get(position);
    }

    public Car getCar(int position) {
        return (Car) getItem(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View view;
        if (convertView == null) {
            view = lInflater.inflate(R.layout.cars_list_item, parent, false);
        } else {
            view = convertView;
        }

        Car car = getCar(position);

        ((TextView) view.findViewById(R.id.cars_numberplate)).setText(car.getNumberplate());
        ((TextView) view.findViewById(R.id.cars_model)).setText(car.getModel());
        ((TextView) view.findViewById(R.id.cars_color)).setText(car.getColorName());

         view.setBackgroundColor(car.getColorCode());

        convertView = view;
        return view;
    }
}
