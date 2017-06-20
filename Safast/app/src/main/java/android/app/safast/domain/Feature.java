package android.app.safast.domain;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by viniciusthiengo on 4/5/15.
 */
public class Feature implements Parcelable {

    private int id;
    private String feature;
    private String date;



    public Feature(){}
    public Feature(int id, String f, String d){
        this.id = id;
        feature = f;
        date = d;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFeature() {
        return feature;
    }

    public void setFeature(String feature) {
        this.feature = feature;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }


    // PARCELABLE
    public Feature(Parcel parcel){
        setId(parcel.readInt());
        setFeature(parcel.readString());
        setDate(parcel.readString());


    }
    @Override
    public int describeContents() {
        return 0;
    }
    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt( getId() );
        dest.writeString( getFeature() );
        dest.writeString( getDate() );


    }
    public static final Creator<Feature> CREATOR = new Creator<Feature>(){
        @Override
        public Feature createFromParcel(Parcel source) {
            return new Feature(source);
        }
        @Override
        public Feature[] newArray(int size) {
            return new Feature[size];
        }
    };



}
