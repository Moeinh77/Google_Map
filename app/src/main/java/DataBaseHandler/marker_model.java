package DataBaseHandler;

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
   // private ArrayList<Marker> model_markerList=new ArrayList<>();


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
