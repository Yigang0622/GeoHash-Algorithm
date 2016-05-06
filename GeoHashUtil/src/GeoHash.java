/**
 * Created by mike on 5/4/16.
 *
 */
public class GeoHash {

    //经度 -90 ~ 90
    private double longitude = 0.0;

    //纬度 -180 ~ 180
    private double latitude  = 0.0;

    private int accuracy = 20;

    public GeoHash(double mLong,double mLat,int accuracy){
        this.longitude = mLong;
        this.latitude = mLat;
        this.accuracy = accuracy;

    }

    public String encodeHash(){

        Base32 base32 = new Base32(getCombinedStr());


        return base32.getBase32Str();
    }

    //Bin Divide
    private String getBitStr(double degree, double left,double right,int counter){

        StringBuilder buffer = new StringBuilder();

        double mRight = right;
        double mLeft = left;

        for (int i=0;i<counter;i++){

            double mid  = ((mLeft+mRight )/ 2);

            //System.out.println("mid:"+mid);

            if (degree < mid){
                buffer.append("0");
                mRight = mid;

            }else {
                buffer.append("1");
                 mLeft = mid;
            }

        }

        System.out.println(buffer.toString());
        return buffer.toString();

    }

    private String getCombinedStr(){

        String logStr = getBitStr(longitude,-90,90,accuracy);
        String latStr = getBitStr(latitude,-180,180,accuracy);

        StringBuilder combinedStr = new StringBuilder();

        for (int i=0;i<logStr.length();i++){

            //奇数纬度 偶数精度
            combinedStr.append(latStr.charAt(i));
            combinedStr.append(logStr.charAt(i));

        }

        System.out.println(combinedStr.toString());

        return combinedStr.toString();
    }

    public String toString(){

        return "The GeoHash for coordinate: " + longitude +", "+latitude
                +" is: "+encodeHash() + " Accuracy: "+accuracy;
    }

    public static void main(String[] args){



        double longitude = 31.222460;
        double latitude  = 121.443469;
        int accuracy = 20;

        GeoHash geoHash = new GeoHash(longitude,latitude,accuracy);
       // GeoHash geoHash2 = new GeoHash(39.80,116.4,accuracy);

       // geoHash.encodeHash();

        System.out.println(geoHash.toString());
       // System.out.println(geoHash2.toString());


    }





}
