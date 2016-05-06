/**
 * Created by mike on 5/5/16.
 */
public class Bean {

    public String name = "";
    public String address = "";
    public String location = "";
    public String hash = "";

    public Bean(String name, String address, String location, String hash) {
        this.name = name;
        this.address = address;
        this.location = location;
        this.hash = hash;
    }

    public String toString() {
        return name+"\n"+address+"\n"+hash;
    }
}
