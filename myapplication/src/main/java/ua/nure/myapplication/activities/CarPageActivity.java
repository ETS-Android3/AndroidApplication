package ua.nure.myapplication.activities;

import androidx.appcompat.app.AppCompatActivity;
import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import ua.nure.domain.entity.Car;
import ua.nure.myapplication.R;
import ua.nure.myapplication.commands.ClientCommand;
import ua.nure.myapplication.commands.MakeContractClientCommand;
import ua.nure.myapplication.commands.MakeTestDriveClientCommand;
import ua.nure.myapplication.fragments.WarningDialogAddedTestDrive;
import ua.nure.myapplication.fragments.WarningDialogAlreadyClicked;
import ua.nure.myapplication.fragments.WarningDialogErrorOccurred;
import utility.CommandsList;

public class CarPageActivity extends AppCompatActivity {
    private Boolean testDriveButtonClicked = false;
    private Boolean buyButtonClicked = false;
    private Car car;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_car_page);
        Bundle bundle = getIntent().getExtras();
        car = (Car) bundle.getSerializable(Car.class.getName());

        TextView carTopicTextView = findViewById(R.id.carTopicTextView);
        ImageView carImageView = findViewById(R.id.carImageView);
        TextView brandTextView = findViewById(R.id.brandTextView);
        TextView carLineTextView = findViewById(R.id.carLineTextView);
        TextView manufacturerCountryTextView = findViewById(R.id.manufacturerCountryTextView);
        TextView manufactureDateTextView = findViewById(R.id.manufactureDateTextView);
        TextView engineBrandTextView = findViewById(R.id.engineBrandTextView);
        TextView engineModelTextView = findViewById(R.id.engineModelTextView);
        TextView engineTypeTextView = findViewById(R.id.engineTypeTextView);
        TextView engineVolumeTextView = findViewById(R.id.engineVolumeTextView);
        TextView carBodyModelTextView = findViewById(R.id.carBodyModelTextView);
        TextView carBodyTypeTextView = findViewById(R.id.carBodyTypeTextView);
        TextView carBodyUsabilityTextView = findViewById(R.id.carBodyUsabilityTextView);
        TextView carBodyLengthTextView = findViewById(R.id.carBodyLengthTextView);
        TextView carBodyHeightTextView = findViewById(R.id.carBodyHeightTextView);
        TextView carBodyWeightTextView = findViewById(R.id.carBodyWeightTextView);
        TextView carBodyWidthTextView = findViewById(R.id.carBodyWidthTextView);
        TextView carBodyPurposeTextView = findViewById(R.id.carBodyPurposeTextView);
        TextView carPrice = findViewById(R.id.carPrice);

        carTopicTextView.setText(car.getBrand() + CommandsList.GAP_STRING + car.getCarLine());
        carImageView.setImageResource(Integer.parseInt(car.getPhotoPath()));
        brandTextView.setText("Brand:" + CommandsList.GAP_STRING + car.getBrand());
        carLineTextView.setText("Line:" + CommandsList.GAP_STRING + car.getCarLine());
        manufacturerCountryTextView.setText("Manufacturer Country:" + CommandsList.GAP_STRING + car.getManufacturerCountry());
        manufactureDateTextView.setText("Manufacturer Country:" + CommandsList.GAP_STRING + car.getManufactureDate());
        engineBrandTextView.setText("Brand:" + CommandsList.GAP_STRING + car.getEngine().getBrand());
        engineModelTextView.setText("Model:" + CommandsList.GAP_STRING + car.getEngine().getModel());
        engineTypeTextView.setText("Type:" + CommandsList.GAP_STRING + car.getEngine().getType());
        engineVolumeTextView.setText("Volume:" + CommandsList.GAP_STRING + car.getEngine().getVolume().toString());
        carBodyModelTextView.setText("Model:" + CommandsList.GAP_STRING + car.getBody().getModel());
        carBodyTypeTextView.setText("Type:" + CommandsList.GAP_STRING + car.getBody().getType());
        carBodyUsabilityTextView.setText("Usability:" + CommandsList.GAP_STRING + car.getBody().getUsability());
        carBodyLengthTextView.setText("Length:" + CommandsList.GAP_STRING + car.getBody().getLength().toString());
        carBodyHeightTextView.setText("Height:" + CommandsList.GAP_STRING + car.getBody().getHeight().toString());
        carBodyWeightTextView.setText("Weight:" + CommandsList.GAP_STRING + car.getBody().getWeight().toString());
        carBodyWidthTextView.setText("Width:" + CommandsList.GAP_STRING + car.getBody().getWeight().toString());
        carBodyPurposeTextView.setText("Description:"+ CommandsList.GAP_STRING + car.getBody().getPurpose());
        carPrice.setText("Price:" + CommandsList.GAP_STRING + car.getPrice().toString());
    }

    public void testDriveButtonOnClick(View view) {
        if (!testDriveButtonClicked) {
            ClientCommand command = MainActivity.getClientCommandsHolder().getCommand(MakeTestDriveClientCommand.class.getName());
            ((MakeTestDriveClientCommand)command).setCar(car);
            ((MakeTestDriveClientCommand)command).setClient(MainActivity.getClient());
            new Thread(() -> {
                if (command.execute().equals(ClientCommand.POSITIVE_ANSWER)) {
                    MainActivity.getWarningsHelper().showFragment(this, WarningDialogAddedTestDrive.class.getName());
                    testDriveButtonClicked = true;
                } else {
                    MainActivity.getWarningsHelper().showFragment(this, WarningDialogErrorOccurred.class.getName());
                }
            }).start();
        } else {
            MainActivity.getWarningsHelper().showFragment(this, WarningDialogAlreadyClicked.class.getName());
        }
    }

    public void makeContractButtonOnClick(View view) {
        if (!buyButtonClicked) {
            ClientCommand command = MainActivity.getClientCommandsHolder().getCommand(MakeContractClientCommand.class.getName());
            ((MakeContractClientCommand)command).setCar(car);
            ((MakeContractClientCommand)command).setClient(MainActivity.getClient());
            new Thread(() -> {
                if (command.execute().equals(ClientCommand.POSITIVE_ANSWER)) {
                    MainActivity.getWarningsHelper().showFragment(this, WarningDialogAddedTestDrive.class.getName());
                    buyButtonClicked = true;
                } else {
                    MainActivity.getWarningsHelper().showFragment(this, WarningDialogErrorOccurred.class.getName());
                }
            }).start();
        } else {
            MainActivity.getWarningsHelper().showFragment(this, WarningDialogAlreadyClicked.class.getName());
        }
    }
}