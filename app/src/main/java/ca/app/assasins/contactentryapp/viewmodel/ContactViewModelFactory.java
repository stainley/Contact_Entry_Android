package ca.app.assasins.contactentryapp.viewmodel;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

public class ContactViewModelFactory implements ViewModelProvider.Factory {

    private final Context context;
    public ContactViewModelFactory(Context context) {
        this.context = context;
    }

    @SuppressWarnings("unchecked")
    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) new ContactViewModel(context);

    }
}
