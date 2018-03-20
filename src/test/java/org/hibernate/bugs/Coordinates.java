package org.hibernate.bugs;

import java.io.Serializable;
import java.util.Objects;

public class Coordinates implements Serializable {

    private Double latitude;

    private Double longitude;

    @SuppressWarnings("unused")
    private Coordinates() {
    }

    /**
     * Creates a new set of coordinates.
     *
     * @param latitude
     *            the latitude
     * @param longitude
     *            the longitude
     */
    public Coordinates(double latitude, double longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }

    /**
     * Gets the latitude.
     *
     * @return the latitude
     */
    public Double getLatitude() {
        return this.latitude;
    }

    /**
     * Sets the latitude.
     *
     * @param latitude
     *            the new latitude
     */
    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    /**
     * Gets the longitude.
     *
     * @return the longitude
     */
    public Double getLongitude() {
        return this.longitude;
    }

    /**
     * Sets the longitude.
     *
     * @param longitude
     *            the new longitude
     */
    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.latitude, this.longitude);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Coordinates) {
            Coordinates other = (Coordinates) obj;
            return Objects.equals(this.latitude, other.latitude)
                    && Objects.equals(this.longitude, other.longitude);
        } else {
            return false;
        }
    }

}
