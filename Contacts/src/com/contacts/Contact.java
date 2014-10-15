package com.contacts;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created with IntelliJ IDEA.
 * User: user
 * Date: 07.10.14
 * Time: 12:55
 * To change this template use File | Settings | File Templates.
 */
public class Contact implements Parcelable {
    private String name;
    private String surname;
    private String phone;
    private long dbID;

    public Contact(String given_name, String given_surname, String given_phone) {
        name = given_name;
        surname = given_surname;
        phone = given_phone;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getPhone() {
        return phone;
    }

    public void setDbID(long id) {
        this.dbID = id;
    }

    public long getDbID() {
        return dbID;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int flags) {
        parcel.writeString(name);
        parcel.writeString(surname);
        parcel.writeString(phone);
        parcel.writeLong(dbID);
    }

    public static final Parcelable.Creator<Contact> CREATOR = new Parcelable.Creator<Contact>() {
        public Contact createFromParcel(Parcel in) {
            return new Contact(in);
        }

        public Contact[] newArray(int size) {
            return new Contact[size];
        }
    };

    private Contact(Parcel parcel) {
        name = parcel.readString();
        surname = parcel.readString();
        phone = parcel.readString();
        dbID = parcel.readLong();
    }
}
