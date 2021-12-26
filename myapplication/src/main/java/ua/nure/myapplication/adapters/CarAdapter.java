package ua.nure.myapplication.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import ua.nure.myapplication.R;

public class CarAdapter extends ArrayAdapter<CarListItem> {
    private final LayoutInflater inflater;
    private final int layout;
    private final List<CarListItem> carListItems;

    public CarAdapter(Context context, int resource, List<CarListItem> carListItems) {
        super(context, resource, carListItems);
        this.carListItems = carListItems;
        this.layout = resource;
        this.inflater = LayoutInflater.from(context);
    }

    public View getView(int position, View convertView, ViewGroup parent) {

        @SuppressLint("ViewHolder") View view=inflater.inflate(this.layout, parent, false);

        CarListItem car = carListItems.get(position);

        TextView carTopic = view.findViewById(R.id.carTopic);
        ImageView carView = view.findViewById(R.id.carView);


        carView.setImageResource(car.getResource());
        carTopic.setText(car.toString());

        return view;
    }
}
