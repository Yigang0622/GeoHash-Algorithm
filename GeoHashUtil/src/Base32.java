import java.awt.*;
import java.math.BigInteger;
import java.util.ArrayList;

/**
 * Created by mike on 5/5/16.
 */
public class Base32 {

    private String originStr = "";

    public String getBase32Str() {
        return base32Str;
    }

    private String base32Str = "";

    private String[] base32Table =
                {"0","1","2","3","4","5",
                    "6","7","8","9","b",
                    "c","d","e","f","g",
                    "h","j","k","m","n",
                    "p","q","r","s","t",
                    "u","v","w","x","y","z"};

    public Base32(String input){
        this.originStr = input;
        String[] arr = convert2Dec();
        base32Str = dec2Base32(arr);
    }

    private String[] convert2Dec(){
        String[] str = new String[originStr.length()/5];

        ArrayList list = new ArrayList();

        for (int i = 0;i<originStr.length();i++){
            if (((i+1)%5) == 0){
                String binStr = originStr.substring(i-4,i+1);
                System.out.println(binStr);
                BigInteger src = new BigInteger(binStr, 2);
                list.add(src.toString());
            }
        }

        System.out.println(list);

        for (int i=0;i<list.size();i++){
            str[i] = (String) list.get(i);
        }
        return str;
    }

    private String dec2Base32(String[] arr){

        StringBuilder buffer = new StringBuilder();

        for (int i =0;i<arr.length;i++){
            buffer.append(base32Table[Integer.parseInt(arr[i])]);
        }

        return buffer.toString();
    }

    public static void main(String[] args){
        Base32 base32 = new Base32("1110011001111000001101101011010101010010");
        System.out.println(base32.getBase32Str());

    }

}
