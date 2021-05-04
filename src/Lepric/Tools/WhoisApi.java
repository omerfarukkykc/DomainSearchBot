package Lepric.Tools;

import Lepric.Interfaceses.IGetInfo;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import org.apache.commons.net.whois.WhoisClient;

public class WhoisApi implements IGetInfo {
    WhoisClient whois = new WhoisClient();
    Map<String, Object[]> data = new HashMap<>();
    PrintTo printTo = new PrintTo();
    int timeOut;
    public WhoisApi(int timeOut) {
        this.timeOut=1000*timeOut;
    }
    public String getWhois(String domain,String extension){
        String result = "A";
        
	try {
            whois.setConnectTimeout(timeOut);
            whois.setDefaultTimeout(timeOut);
            whois.connect(WhoisClient.DEFAULT_HOST);
	    result=whois.query(domain+extension);
            whois.disconnect();
            
	} catch (IOException e) {
            System.err.println(e);
	}
        return result;
    }

    public String checkWhois(String domain,String extension){
        if(getWhois(domain,extension).charAt(0)=='N'){
            return domain+extension+" Adlı domain alınabilir";
        }else{
            return null;
        }
    }

    @Override
    public void PrintInfo() {
        System.out.println("Host: " +WhoisClient.DEFAULT_HOST+"\nTimeout: "+timeOut);
    }    
}
    
