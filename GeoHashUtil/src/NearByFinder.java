import java.io.*;
import java.util.ArrayList;

/**
 * Created by mike on 5/5/16.
 */
public class NearByFinder {


    private String geoHash = "";
    private ArrayList<Bean> dataBase;


    private double latitude = 0;
     private double longitude = 0;

    public NearByFinder(double latitude, double longitude) {
        geoHash = new GeoHash(longitude,latitude,20).encodeHash();
        dataBase = new ArrayList();
        this.dataBase =readDataBase();

        this.latitude = latitude;
        this.longitude =longitude;

        System.out.println("DataBase read complete! " + dataBase.size() + "item added\n");
    }

    public String locationToString(){
        return "坐标:" +  latitude +", " +longitude + " GeoHash: "+geoHash + "\n";
    }

    private ArrayList readDataBase(){

        ArrayList list = new ArrayList();
        String filePath ="/Users/mike/Desktop/DB.txt";
        BufferedReader reader = null;

        try {
            reader = new BufferedReader(new InputStreamReader(new FileInputStream(filePath), "UTF-8")); // 指定读取文件的编码格式，要和写入的格式一致，以免出现中文乱码,

           for (int i=0;i<1988;i++){
               String name = reader.readLine();
               String address = reader.readLine();
               String location = reader.readLine();
               String hash = reader.readLine();

               Bean bean = new Bean(name,address,location,hash);

               list.add(bean);

               reader.readLine();
               reader.readLine();
           }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


        return list;
    }

    private boolean isNearBy(String hash){

        String sub = geoHash.substring(0,6);

       // System.out.println("Sub:" + hash.contains(sub));

        return hash.contains(sub);
    }


    public ArrayList getNearByList(){

        ArrayList<Bean> nearByList = new ArrayList<>();

        for (int i=0;i<dataBase.size();i++){

            Bean bean = dataBase.get(i);
            if (isNearBy(bean.hash)){
                nearByList.add(bean);
            }
        }

        return nearByList;

    }

    public static void main(String[] args){

        NearByFinder nearByFinder = new NearByFinder(121.455649,31.217747);

        ArrayList<Bean> list = nearByFinder.getNearByList();


        System.out.println(nearByFinder.locationToString());

        System.out.println("周围有:"+list.size()+" 家餐馆:\n");

        for (int i=0;i<list.size();i++){
            System.out.println(list.get(i).toString()+"\n");
        }

    }





}
