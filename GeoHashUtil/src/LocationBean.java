/**
 * Created by mike on 5/5/16.
 */
public class LocationBean {

    private String name = "";

    private double longitude = 0.0;
    private double latitude  = 0.0;

    private String geoHash = "";

    private String address = "";
    public LocationBean(String name, double longitude, double latitude,String address) {
        this.name = name;
        this.longitude = longitude;
        this.latitude = latitude;
        this.address = address;

        this.geoHash = new GeoHash(longitude,latitude,20).encodeHash();

        System.out.println(geoHash);

    }

    public String getGeoHash() {
        return geoHash;
    }





}
