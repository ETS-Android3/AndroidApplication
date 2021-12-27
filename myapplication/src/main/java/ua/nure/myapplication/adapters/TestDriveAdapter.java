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

public class TestDriveAdapter extends ArrayAdapter<TestDriveListItem> {
    private final LayoutInflater inflater;
    private final int layout;
    private final List<TestDriveListItem> testDriveListItems;

    public TestDriveAdapter(Context context, int resource, List<TestDriveListItem> testDriveListItems) {
        super(context, resource, testDriveListItems);
        this.testDriveListItems = testDriveListItems;
        this.layout = resource;
        this.inflater = LayoutInflater.from(context);
    }

    @SuppressLint("SetTextI18n")
    public View getView(int position, View convertView, ViewGroup parent) {

        @SuppressLint("ViewHolder") View view=inflater.inflate(this.layout, parent, false);

        TestDriveListItem testDriveListItem = testDriveListItems.get(position);

        TextView carTopic = view.findViewById(R.id.carTopic);
        ImageView carView = view.findViewById(R.id.carImage);
        TextView score = view.findViewById(R.id.score);


        carView.setImageResource(testDriveListItem.getResource());
        carTopic.setText(testDriveListItem.toString());
        score.setText("Score:" + CommandsList.GAP_STRING + testDriveListItem.getScore().toString());

        return view;
    }
}
