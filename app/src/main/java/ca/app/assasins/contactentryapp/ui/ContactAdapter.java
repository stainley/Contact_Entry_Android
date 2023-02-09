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

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import ca.app.assasins.contactentryapp.R;
import ca.app.assasins.contactentryapp.model.Contact;

public class ContactAdapter extends RecyclerView.Adapter<ContactAdapter.ContactViewHolder> {
    private Context context;
    private final List<Contact> contacts;
    private List<Contact> filteredContacts = new ArrayList<>();

    public ContactAdapter(List<Contact> contacts) {
        this.contacts = contacts;
        if (contacts != null) {
            filteredContacts.addAll(contacts);
        }
    }

    @NonNull
    @Override
    public ContactViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        this.context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.contact_row, parent, false);

        return new ContactViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ContactViewHolder holder, int position) {
        holder.firstNameLabel.setText(filteredContacts.get(position).getFirstName());
        holder.lastNameLabel.setText(filteredContacts.get(position).getLastName());
        holder.phoneNumberLabel.setText(filteredContacts.get(position).getPhoneNumber());

        holder.contactRowCardView.setOnClickListener(contactView -> {
            Intent contactDetailIntent = new Intent(context, ContactEntryActivity.class);
            contactDetailIntent.putExtra("contact", filteredContacts.get(position));
            contactDetailIntent.putExtra("saveBtn", false);
            context.startActivity(contactDetailIntent);

        });
    }

    @Override
    public int getItemCount() {
        return filteredContacts.size();
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

    // Tell the IDE don't show this warning message
    @SuppressWarnings("notifyDataSetChanged")
    public void filter(String newText) {
        newText = newText.toLowerCase();

        if (newText.length() == 0) {
            filteredContacts.clear();
            filteredContacts.addAll(contacts);
        } else {
            StringBuilder sb = new StringBuilder();
            sb.append(newText);
            filteredContacts = contacts.stream().filter(contact -> contact.getFirstName().toLowerCase().contains(sb))
                    .collect(Collectors.toList());

        }
        notifyDataSetChanged();
    }
}
