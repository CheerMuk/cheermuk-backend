package cheermuk.cheermukbackend.global.utils;

import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.GeometryFactory;
import org.locationtech.jts.geom.Point;
import org.locationtech.jts.geom.PrecisionModel;

public class GeomUtils {
    private static final GeometryFactory geometryfactory = new GeometryFactory(new PrecisionModel(), 4326);

    public static Point createPoint(Double latitude, Double longitude) {
        return geometryfactory.createPoint(new Coordinate(latitude, longitude));
    }
}
