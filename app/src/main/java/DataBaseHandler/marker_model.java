package DataBaseHandler;

import android.net.Uri;

import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by Moein on 7/8/2017.
 */

public class marker_model  {
    private String title;
    private String description;
    private LatLng mlatLng;
    private int Id;
    private Uri imageaddress;
    private String date;

    public String getDate() {
        return date;
    }

    public void setDate(String data) {
        this.date = data;
    }

    public Uri getImageaddress() {
        return imageaddress;
    }

    public void setImageaddress(Uri imageaddress) {
        this.imageaddress = imageaddress;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public double getLat(){return mlatLng.latitude;}
    public double getLng(){return mlatLng.longitude;}

    public void setPosition(LatLng latLng) {
        mlatLng=latLng;
    }

    public LatLng getLatLng(){
        return mlatLng;
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String Title) {title = Title;}



    public String getDescription() {
        return description;
    }

    public void setDescription(String Description) {
        description = Description;
    }
}
