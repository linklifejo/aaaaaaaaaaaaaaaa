package com.example.my35_captureintent.photo;

import android.net.Uri;

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
