package ca.app.assasins.contactentryapp.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import java.util.Objects;

import ca.app.assasins.contactentryapp.databinding.ActivityMainBinding;
import ca.app.assasins.contactentryapp.model.Contact;
import ca.app.assasins.contactentryapp.viewmodel.ContactViewModel;
import ca.app.assasins.contactentryapp.viewmodel.ContactViewModelFactory;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;

    private Contact contact;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(LayoutInflater.from(this));
        setContentView(binding.getRoot());

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        binding.saveBtn.setOnClickListener(this::saveContact);
        binding.deleteBtn.setOnClickListener(this::deleteContact);

        Intent intent = getIntent();

        contact = (Contact) intent.getSerializableExtra("contact");

        binding.firstNameTxt.setText(contact.getFirstName());
        binding.lastNameTxt.setText(contact.getLastName());
        binding.phoneNumberTxt.setText(contact.getPhoneNumber());
        binding.emailTxt.setText(contact.getEmail());
    }

    private void saveContact(View view) {
        Intent resultContact = new Intent();

        resultContact.putExtra("firstname", Objects.requireNonNull(binding.firstNameTxt.getText()).toString());
        resultContact.putExtra("lastname", Objects.requireNonNull(binding.lastNameTxt.getText()).toString());
        resultContact.putExtra("phone", Objects.requireNonNull(binding.phoneNumberTxt.getText()).toString());
        resultContact.putExtra("email", Objects.requireNonNull(binding.emailTxt.getText()).toString());
        setResult(Activity.RESULT_OK, resultContact);

        finish();
    }

    private void deleteContact(View view) {

        ContactViewModel contactViewModel = new ViewModelProvider(this, new ContactViewModelFactory(getApplicationContext())).get(ContactViewModel.class);
        contactViewModel.deleteContact(contact);
        finish();
    }

}