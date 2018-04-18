package protocolosirvent;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;


/**
 *
 * @author Miguel Angel Flores Sirvent
 */
public class IpPublica {
    String ip;

    public IpPublica() throws MalformedURLException, IOException{
        
        URL whatismyip = new URL("http://checkip.amazonaws.com");
        BufferedReader in = new BufferedReader(new InputStreamReader(whatismyip.openStream()));
            
        this.ip = in.readLine();
        
        in.close();
    }
    
    public String getIp() {
        return ip;
    }
    
}
