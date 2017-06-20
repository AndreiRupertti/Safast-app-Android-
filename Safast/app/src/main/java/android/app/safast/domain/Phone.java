package android.app.safast.domain;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by viniciusthiengo on 4/5/15.
 */
public class Phone implements Parcelable {

    private int id;
    private String name;
    private String phone;
    private String color;



    public Phone(){}
    public Phone(int id, String n, String p, String c){
        this.id = id;
        name = n;
        phone = p;
        color = c;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    // PARCELABLE
    public Phone(Parcel parcel){
        setId(parcel.readInt());
        setName(parcel.readString());
        setPhone(parcel.readString());
        setColor(parcel.readString());

    }
    @Override
    public int describeContents() {
        return 0;
    }
    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt( getId() );
        dest.writeString( getName() );
        dest.writeString( getPhone() );
        dest.writeString( getColor() );

    }
    public static final Creator<Phone> CREATOR = new Creator<Phone>(){
        @Override
        public Phone createFromParcel(Parcel source) {
            return new Phone(source);
        }
        @Override
        public Phone[] newArray(int size) {
            return new Phone[size];
        }
    };



}
