package ca.app.assasins.contactentryapp.repository;

import android.content.Context;

import androidx.lifecycle.LiveData;

import java.util.List;

import ca.app.assasins.contactentryapp.dao.ContactDao;
import ca.app.assasins.contactentryapp.db.ContactRoom;
import ca.app.assasins.contactentryapp.model.Contact;

public class ContactRepository {

    private final ContactDao contactDao;

    public ContactRepository(Context context) {
        ContactRoom contactRoom = ContactRoom.getDatabase(context);
        contactDao = contactRoom.contactDao();
    }


    public LiveData<List<Contact>> fetchContacts() {
        return contactDao.fetchAll();
    }

    public void save(Contact contact) {
        contactDao.save(contact);
    }

    public void delete(Contact contact) {
        this.contactDao.delete(contact);
    }

    public void update(Contact contact) {
        this.contactDao.update(contact);
    }
}
