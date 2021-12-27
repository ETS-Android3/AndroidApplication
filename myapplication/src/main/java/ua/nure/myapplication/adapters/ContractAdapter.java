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
import utility.CommandsList;

public class ContractAdapter extends ArrayAdapter<ContractListItem> {
    private final LayoutInflater inflater;
    private final int layout;
    private final List<ContractListItem> contractListItems;

    public ContractAdapter(Context context, int resource, List<ContractListItem> contractListItems) {
        super(context, resource, contractListItems);
        this.contractListItems = contractListItems;
        this.layout = resource;
        this.inflater = LayoutInflater.from(context);
    }

    @SuppressLint("SetTextI18n")
    public View getView(int position, View convertView, ViewGroup parent) {

        @SuppressLint("ViewHolder") View view=inflater.inflate(this.layout, parent, false);

        ContractListItem contractListItem = contractListItems.get(position);

        TextView carTopic = view.findViewById(R.id.carTopic);
        ImageView carView = view.findViewById(R.id.carImage);
        TextView carPrice = view.findViewById(R.id.carPrice);


        carView.setImageResource(contractListItem.getResource());
        carTopic.setText(contractListItem.toString());
        carPrice.setText("Car Price:" + CommandsList.GAP_STRING + contractListItem.getPrice().toString());

        return view;
    }
}
