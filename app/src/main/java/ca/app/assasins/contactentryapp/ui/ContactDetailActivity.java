package ca.app.assasins.contactentryapp.ui;

import android.os.Bundle;
import android.view.LayoutInflater;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import ca.app.assasins.contactentryapp.databinding.ActivityContactDetailBinding;

public class ContactDetailActivity extends AppCompatActivity {

    private ActivityContactDetailBinding binding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ActivityContactDetailBinding inflate = ActivityContactDetailBinding.inflate(LayoutInflater.from(getApplicationContext()));
        setContentView(inflate.getRoot());

        System.out.println(binding.firstNameTxt.getText());
    }

    @Override
    protected void onRestart() {
        super.onRestart();
    }
}
