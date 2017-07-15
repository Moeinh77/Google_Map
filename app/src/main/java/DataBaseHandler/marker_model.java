package DataBaseHandler;

import java.io.Serializable;

/**
 * Created by Moein on 7/8/2017.
 */

public class marker_model implements Serializable {
    private String title;
    private int _Id;
    private String description;

    public int get_Id() {
        return _Id;
    }

    public void set_Id(int _Id) {
        this._Id = _Id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
