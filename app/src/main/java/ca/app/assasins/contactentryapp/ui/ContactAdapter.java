package ca.app.assasins.contactentryapp.ui;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import ca.app.assasins.contactentryapp.R;
import ca.app.assasins.contactentryapp.databinding.ContactRowBinding;
import ca.app.assasins.contactentryapp.model.Contact;

public class ContactAdapter extends RecyclerView.Adapter<ContactAdapter.ContactViewHolder> {
    private Context context;
    private final List<Contact> contacts;

    public ContactAdapter(List<Contact> contacts) {
        this.contacts = contacts;
    }


    @NonNull
    @Override
    public ContactViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //ContactRowBinding binding = ContactRowBinding.inflate(LayoutInflater.from(parent.getContext()));
        this.context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.contact_row, parent, false);
        //View view = binding.getRoot();
        return new ContactViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ContactViewHolder holder, int position) {
        holder.firstNameLabel.setText(contacts.get(position).getFirstName());
        holder.lastNameLabel.setText(contacts.get(position).getLastName());
        holder.phoneNumberLabel.setText(contacts.get(position).getPhoneNumber());

        holder.contactRowCardView.setOnClickListener(contactView -> {
            Intent contactDetailIntent = new Intent(context, MainActivity.class);
            contactDetailIntent.putExtra("contact", contacts.get(position));
            context.startActivity(contactDetailIntent);

        });
    }

    @Override
    public int getItemCount() {
        return contacts.size();
    }

    static class ContactViewHolder extends RecyclerView.ViewHolder {

        private final CardView contactRowCardView;

        private final TextView firstNameLabel;
        private final TextView lastNameLabel;
        private final TextView phoneNumberLabel;

        public ContactViewHolder(@NonNull View itemView) {
            super(itemView);

            firstNameLabel = itemView.findViewById(R.id.firstName_Label);
            lastNameLabel = itemView.findViewById(R.id.lastName_Lbl);
            phoneNumberLabel = itemView.findViewById(R.id.phoneNumber_Label);
            contactRowCardView = itemView.findViewById(R.id.contactRowCardView);
        }
    }
}
