import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;


public class EstadoPuja {

    static String estadoPuja(String tradeId) throws Exception{
        String lineEstadoPuja=null;
        URL urlEstadoPuja;HttpsURLConnection connectionEstadoPuja;
        String setCookie = ThreadStart.futweb + "; " + ThreadStart.hl + "; " + ThreadStart.XSRFTOKEN + "; " + ThreadStart.EASFCWEBSESSION + ";" + ThreadStart.device_view;
        try{     
            urlEstadoPuja = new URL(ThreadStart.urlBase+"/ut/game/fifa14/trade/status?tradeIds="+tradeId);
            connectionEstadoPuja = (HttpsURLConnection)urlEstadoPuja.openConnection();
            connectionEstadoPuja.setRequestMethod("POST");
            connectionEstadoPuja.setRequestProperty("User-Agent", ThreadStart.user_agent);
            connectionEstadoPuja.setRequestProperty("Cookie", setCookie);
            connectionEstadoPuja.setRequestProperty("Content-Type", "application/json");    
            connectionEstadoPuja.setRequestProperty("X-HTTP-Method-Override", "GET");
            connectionEstadoPuja.setRequestProperty("X-UT-Embed-Error", "true");
            connectionEstadoPuja.setRequestProperty("X-UT-PHISHING-TOKEN", ThreadStart.TOKEN);
            connectionEstadoPuja.setRequestProperty("X-UT-SID", ThreadStart.XUTSID);   
            connectionEstadoPuja.setUseCaches (false);connectionEstadoPuja.setDoInput(true);connectionEstadoPuja.setDoOutput(true);     
            
            //Get Response  
            InputStream isEstadoPuja = connectionEstadoPuja.getInputStream();       
            BufferedReader rdEstadoPuja = new BufferedReader(new InputStreamReader(isEstadoPuja));      
            lineEstadoPuja = rdEstadoPuja.readLine();      

        }catch (Exception e) {
            ThreadStart.conexionCorrecta=false;
            ThreadStart.conectado();
            ThreadStart.reConectar();
        }
        
        System.out.println("*********" + lineEstadoPuja);
        return lineEstadoPuja;
    }

}
