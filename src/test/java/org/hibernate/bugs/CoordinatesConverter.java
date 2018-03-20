package org.hibernate.bugs;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import org.geolatte.geom.G2D;
import org.geolatte.geom.Point;
import org.geolatte.geom.crs.CoordinateReferenceSystems;

/**
 * A converter from {@link Coordinates} to {@link Point}.
 */
@Converter
public class CoordinatesConverter
        implements AttributeConverter<Coordinates, Point<G2D>> {

    @Override
    public Point<G2D> convertToDatabaseColumn(Coordinates attribute) {
        if (attribute == null) {
            return null;
        } else {
            double lat = attribute.getLatitude();
            double lon = attribute.getLongitude();
            G2D g2d = new G2D(lon, lat);
            return new Point<>(g2d, CoordinateReferenceSystems.WGS84);
        }
    }

    @Override
    public Coordinates convertToEntityAttribute(Point<G2D> dbData) {
        if (dbData == null) {
            return null;
        } else {
            double lat = dbData.getPosition().getLat();
            double lon = dbData.getPosition().getLon();
            return new Coordinates(lat, lon);
        }
    }

}
