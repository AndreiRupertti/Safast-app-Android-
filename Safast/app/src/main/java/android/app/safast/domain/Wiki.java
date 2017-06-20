package android.app.safast.domain;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by viniciusthiengo on 4/5/15.
 */
public class Wiki implements Parcelable {

    private int id;
    private String category;
    private String description;
    private int photo;

    public Wiki(){}
    public Wiki(int id, String cat, String d, int p){
        this.id = id;
        category = cat;
        description = d;
        photo = p;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getPhoto() {
        return photo;
    }

    public void setPhoto(int photo) {
        this.photo = photo;
    }



    // PARCELABLE
    public Wiki(Parcel parcel){
        setId(parcel.readInt());
        setCategory(parcel.readString());
        setDescription(parcel.readString());
        setPhoto(parcel.readInt());



    }
    @Override
    public int describeContents() {
        return 0;
    }
    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt( getId() );
        dest.writeString( getCategory() );
        dest.writeString( getDescription() );
        dest.writeInt( getPhoto() );


    }
    public static final Creator<Wiki> CREATOR = new Creator<Wiki>(){
        @Override
        public Wiki createFromParcel(Parcel source) {
            return new Wiki(source);
        }
        @Override
        public Wiki[] newArray(int size) {
            return new Wiki[size];
        }
    };



}
