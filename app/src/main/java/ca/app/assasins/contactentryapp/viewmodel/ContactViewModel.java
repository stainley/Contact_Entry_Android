package ca.app.assasins.contactentryapp.viewmodel;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

import ca.app.assasins.contactentryapp.model.Contact;
import ca.app.assasins.contactentryapp.repository.ContactRepository;

public class ContactViewModel extends ViewModel {
    private final ContactRepository repository;
    private LiveData<List<Contact>> contactLiveData;

    public ContactViewModel(Context context) {
        repository = new ContactRepository(context);

        contactLiveData = new MutableLiveData<>();

    }

    // Save contact to the DB
    public void saveContact(Contact contact) {
        repository.save(contact);
    }


    public LiveData<List<Contact>> getContactLiveData() {
        contactLiveData = repository.fetchContacts();
        return contactLiveData;
    }

    public void deleteContact(Contact contact) {
        repository.delete(contact);
    }

    public void updateContact(Contact contact) {
        repository.update(contact);
    }
}
