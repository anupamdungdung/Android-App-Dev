package com.app.firebase;

import com.google.firebase.firestore.GeoPoint;
import com.google.firebase.firestore.auth.User;

public class UserLocation {
    private GeoPoint geoPoint;
    public UserLocation(){

    }

    public UserLocation(GeoPoint geoPoint) {
        this.geoPoint = geoPoint;
    }

    public GeoPoint getGeoPoint() {
        return geoPoint;
    }

    public void setGeoPoint(GeoPoint geoPoint) {
        this.geoPoint = geoPoint;
    }
}
