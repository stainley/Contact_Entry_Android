package ca.app.assasins.contactentryapp.model;


import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity
public class Contact implements Serializable {
    @PrimaryKey
    private Long id;
    @ColumnInfo(name = "FIRST_NAME")
    private String firstName;
    @ColumnInfo(name = "LAST_NAME")
    private String lastName;
    @ColumnInfo(name = "PHONE_NUMBER")
    private String phoneNumber;
    private String email;

    public Contact() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Contact(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public Contact(String firstName, String lastName, String phoneNumber) {
        this(firstName, lastName);
        this.phoneNumber = phoneNumber;
    }

    public Contact(String firstName, String lastName, String phoneNumber, String email) {
        this(firstName, lastName, phoneNumber);
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}
