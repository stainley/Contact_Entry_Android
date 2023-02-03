package ca.app.assasins.contactentryapp.db;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import ca.app.assasins.contactentryapp.dao.ContactDao;
import ca.app.assasins.contactentryapp.model.Contact;

@Database(entities = {Contact.class}, version = 1, exportSchema = false)
public abstract class ContactRoom extends RoomDatabase {

    private static volatile ContactRoom INSTANCE;
    private static final int NUMBER_OF_THREADS = 4;
    public static final ExecutorService databaseWriterExecutor = Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    public abstract ContactDao contactDao();

    public static ContactRoom getDatabase(Context context) {
        if (INSTANCE == null) {
            synchronized (ContactRoom.class) {
                INSTANCE = Room.databaseBuilder(context, ContactRoom.class, "contact_db").allowMainThreadQueries().build();
            }
        }
        return INSTANCE;
    }
}
