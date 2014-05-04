import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;


public class Buscar {

    //Busqueda
    static String busquedaContrato(String url) throws Exception{
        String lineSearchUser=null;
        URL urlsearchUser;HttpsURLConnection connectionSearchUser;
        String setCookie = ThreadStart.futweb + "; " + ThreadStart.hl + "; " + ThreadStart.XSRFTOKEN + "; " + ThreadStart.EASFCWEBSESSION + "; " + ThreadStart.device_view + "; " + ThreadStart.FUTWebPhishing;
        try{   
        	urlsearchUser = new URL(url);
            connectionSearchUser = (HttpsURLConnection)urlsearchUser.openConnection();
            connectionSearchUser.setRequestMethod("POST");
            connectionSearchUser.setRequestProperty("User-Agent", ThreadStart.user_agent); //Hacemos creer a EA que somos un navegador
            connectionSearchUser.setRequestProperty("Cookie", setCookie); //Enviamos las 3 cookies
            connectionSearchUser.setRequestProperty("Content-Type", "application/json");
            connectionSearchUser.addRequestProperty("Content-Length", "1");
            connectionSearchUser.setRequestProperty("X-HTTP-Method-Override", "GET");
            connectionSearchUser.setRequestProperty("X-UT-Embed-Error", "true");
            connectionSearchUser.setRequestProperty("X-UT-PHISHING-TOKEN", ThreadStart.TOKEN);
            connectionSearchUser.setRequestProperty("X-UT-SID", ThreadStart.XUTSID);   
            connectionSearchUser.setUseCaches(false);connectionSearchUser.setDoInput(true);connectionSearchUser.setDoOutput(true);      
            
            //Get Response  
            InputStream isSearchUser = connectionSearchUser.getInputStream();   
            BufferedReader rdSearchUser = new BufferedReader(new InputStreamReader(isSearchUser));  
            lineSearchUser = rdSearchUser.readLine();
              
        }catch (Exception e) {
            e.getMessage();
            ThreadStart.conexionCorrecta=false;
            ThreadStart.conectado();
            ThreadStart.reConectar();
        }
        return lineSearchUser;             
    }

}
