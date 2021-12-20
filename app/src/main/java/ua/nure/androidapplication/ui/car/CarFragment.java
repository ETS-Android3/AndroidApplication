package ua.nure.androidapplication.ui.car;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import ua.nure.androidapplication.databinding.FragmentCarBinding;


public class CarFragment extends Fragment {
    private CarViewModel carViewModel;
    private FragmentCarBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        carViewModel =
                new ViewModelProvider(this).get(CarViewModel.class);

        binding = FragmentCarBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView textView = binding.textCar;
        carViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
