package ua.nure.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.List;
import ua.nure.domain.entity.Car;
import ua.nure.myapplication.adapters.CarAdapter;
import ua.nure.myapplication.adapters.CarListItem;
import ua.nure.myapplication.commands.ClientCommand;
import ua.nure.myapplication.commands.GetAllCarsClientCommands;

public class CarsListActivity extends AppCompatActivity {
    private final ArrayList<CarListItem> carListItems = new ArrayList<>();
    private List<Car> cars;
    private ListView carsList;
    private CarAdapter carAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cars_list);

        carsList = findViewById(R.id.carsList);

        new Thread(() -> {
            ClientCommand command = MainActivity.getClientCommandsHolder().getCommand(GetAllCarsClientCommands.class.getName());
            command.execute();
            cars = ((GetAllCarsClientCommands) command).getCars();

            int index = 0;
            int size = cars.size();
            Car car;
            while (index < size) {
                car = cars.get(index);
                carListItems.add(new CarListItem(car.getCarLine(), car.getBrand(), Integer.parseInt(car.getPhotoPath())));
                index++;
            }

            this.runOnUiThread(() -> {
                carAdapter = new CarAdapter(this, R.layout.car_list_item, carListItems);
                AdapterView.OnItemClickListener itemListener = (parent, view, position, id) -> {
                    CarListItem selectedCarListItem = (CarListItem) parent.getItemAtPosition(position);
                    Toast.makeText(getApplicationContext(), "Был выбран пункт " + selectedCarListItem.toString(),
                            Toast.LENGTH_SHORT).show();
                };
                carsList.setAdapter(carAdapter);
                carsList.setOnItemClickListener(itemListener);
            });
        }).start();
    }
}