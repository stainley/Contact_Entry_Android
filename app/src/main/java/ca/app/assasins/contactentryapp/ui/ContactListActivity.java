package ca.app.assasins.contactentryapp.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import ca.app.assasins.contactentryapp.databinding.ActivityListContactBinding;
import ca.app.assasins.contactentryapp.model.Contact;
import ca.app.assasins.contactentryapp.viewmodel.ContactViewModel;
import ca.app.assasins.contactentryapp.viewmodel.ContactViewModelFactory;


public class ContactListActivity extends AppCompatActivity implements SearchView.OnQueryTextListener {

    private static final String TAG = ContactListActivity.class.getName();

    ActivityListContactBinding binding;

    private List<Contact> contacts = new ArrayList<>();
    private ContactAdapter contactAdapter;

    private ContactViewModel contactViewModel;

    private final ActivityResultLauncher<Intent> launcher =
            registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), result -> {
                Log.i(TAG, "onActivityResult");

                if (result.getResultCode() == Activity.RESULT_OK) {
                    if (result.getData() != null) {
                        Intent data = result.getData();
                        String firstName = data.getStringExtra("firstname");
                        String lastName = data.getStringExtra("lastname");
                        String phone = data.getStringExtra("phone");
                        String email = data.getStringExtra("email");

                        Contact contact = new Contact();
                        contact.setFirstName(firstName);
                        contact.setLastName(lastName);
                        contact.setEmail(email);
                        contact.setPhoneNumber(phone);
                        Log.i("Contact Result:", contact.toString());
                        contactViewModel.saveContact(contact);
                        //Save into Database
                    }
                } else if (result.getResultCode() == 5) {
                    if (result.getData() != null) {
                        Contact contact = (Contact) result.getData().getSerializableExtra("contact");
                        contactViewModel.deleteContact(contact);
                    }
                }
            });

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityListContactBinding.inflate(LayoutInflater.from(this));
        setContentView(binding.getRoot());

        binding.addContactBtn.setOnClickListener(this::createNewContact);

        RecyclerView recyclerView = binding.contactList;
        contactViewModel = new ViewModelProvider(this, new ContactViewModelFactory(getApplicationContext())).get(ContactViewModel.class);

        binding.search.setOnQueryTextListener(this);

        contactViewModel.getContactLiveData().observe(this, contactResult -> {
            if (contactResult != null) {
                contacts = contactResult;
                contactAdapter = new ContactAdapter(contacts);

                recyclerView.setLayoutManager(new LinearLayoutManager(this));
                recyclerView.setAdapter(contactAdapter);
            }
        });
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        contactAdapter.notifyItemChanged(contacts.size());
    }

    private void createNewContact(View view) {

        Intent newContactIntent = new Intent(this, MainActivity.class);
        launcher.launch(newContactIntent);
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        contactAdapter.filter(newText);
        return false;
    }
}
