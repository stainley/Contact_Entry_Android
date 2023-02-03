package ca.app.assasins.contactentryapp.dao;


import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import ca.app.assasins.contactentryapp.model.Contact;

@Dao
public interface ContactDao {
    @Insert
    void save(Contact contact);

    @Query("SELECT * FROM Contact")
    LiveData<List<Contact>> fetchAll();

    @Delete
    void delete(Contact contact);

    @Query("DELETE FROM Contact WHERE id = :id")
    void deleteById(Long id);

    @Update
    void update(Contact contact);
}
