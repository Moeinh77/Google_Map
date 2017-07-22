package Map_Utilities;

import com.google.android.gms.maps.model.LatLng;
import com.google.maps.android.clustering.ClusterItem;

/**
 * Created by Moein on 7/21/2017.
 */

public class My_Cluster_Item implements ClusterItem {

    private final LatLng mPosition;
    private String mTitle;
    private String mSnippet;

    public My_Cluster_Item(double lat, double lng) {
        mPosition = new LatLng(lat, lng);
    }

    public My_Cluster_Item(double lat, double lng, String title, String snippet) {
        mPosition = new LatLng(lat, lng);
        mTitle = title;
        mSnippet = snippet;
    }

    @Override
    public LatLng getPosition() {
        return null;
    }

    @Override
    public String getTitle() {
        return null;
    }

    @Override
    public String getSnippet() {
        return null;
    }
}
