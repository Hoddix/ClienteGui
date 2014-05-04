import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;


public class Monedas {

    static int credits;
    
    static int monedas() throws Exception{
        URL urlUser;HttpsURLConnection connectionMonedas;
        String setCookie = ThreadStart.futweb + "; " + ThreadStart.hl + "; " + ThreadStart.XSRFTOKEN + "; " + ThreadStart.EASFCWEBSESSION + ";" + ThreadStart.device_view;	
        credits=0;
        try{                
            urlUser = new URL("https://utas.fut.ea.com/ut/game/fifa14/user/credits");       
            connectionMonedas = (HttpsURLConnection)urlUser.openConnection();       
            connectionMonedas.setRequestMethod("POST");     
            connectionMonedas.setRequestProperty("User-Agent", ThreadStart.user_agent); //Hacemos creer a EA que somos un navegador
            connectionMonedas.setRequestProperty("Cookie", setCookie);
            connectionMonedas.setRequestProperty("Content-Type", "application/json");
            connectionMonedas.setRequestProperty("Content-Length", "1");
            connectionMonedas.setRequestProperty("X-HTTP-Method-Override", "GET");      
            connectionMonedas.setRequestProperty("X-UT-Embed-Error", "true");
            connectionMonedas.setRequestProperty("X-UT-PHISHING-TOKEN", ThreadStart.TOKEN);
            connectionMonedas.setRequestProperty("X-UT-SID", ThreadStart.XUTSID);   
            connectionMonedas.setUseCaches (false);connectionMonedas.setDoInput(true);connectionMonedas.setDoOutput(true);      

            //Get Response  
            InputStream isMonedas = connectionMonedas.getInputStream(); 
            BufferedReader rdMonedas = new BufferedReader(new InputStreamReader(isMonedas));        
            String lineMonedas = rdMonedas.readLine();
            lineMonedas = lineMonedas.substring(lineMonedas.indexOf("credits")+"credits".length(),lineMonedas.length());
            
            String monedas=lineMonedas.substring(lineMonedas.indexOf(":")+1 , lineMonedas.indexOf(","));
            credits = Integer.parseInt(monedas);
            
            rdMonedas.close(); //Cerramos conexion con la URL
            
        }catch (Exception e) {
            ThreadStart.conexionCorrecta=false;
            ThreadStart.conectado();
            ThreadStart.reConectar();
        }
        
        return credits;
    }

}
