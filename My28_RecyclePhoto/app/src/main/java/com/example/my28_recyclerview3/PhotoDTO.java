package com.example.my28_recyclerview3;

import android.net.Uri;
import android.widget.ImageView;

import java.io.Serializable;
public class PhotoDTO implements Serializable {
    Uri photo;

    public PhotoDTO(Uri photo) {
        this.photo = photo;
    }

    public Uri getPhoto() {
        return photo;
    }

    public void setPhoto(Uri photo) {
        this.photo = photo;
    }
}
