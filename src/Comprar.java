import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;


public class Comprar {

    static void comprarContrato(String tradeId, String puja) throws Exception{  

        URL urlComprarContrato;HttpsURLConnection connectionComprar;
        String data="{\"bid\":"+puja+"}";
        String setCookie = ThreadStart.futweb + "; " + ThreadStart.hl + "; " + ThreadStart.XSRFTOKEN + "; " + ThreadStart.EASFCWEBSESSION + ";" + ThreadStart.device_view;
        try{
            urlComprarContrato = new URL(ThreadStart.urlBase + "/ut/game/fifa14/trade/"+tradeId+"/bid");
            connectionComprar = (HttpsURLConnection)urlComprarContrato.openConnection();
            connectionComprar.setRequestMethod("POST");
            connectionComprar.setRequestProperty("User-Agent", ThreadStart.user_agent);
            connectionComprar.setRequestProperty("Cookie", setCookie);
            connectionComprar.setRequestProperty("Content-Type", "application/json");
            connectionComprar.setRequestProperty("Content-Length", "" +Integer.toString(data.getBytes().length)); 
            connectionComprar.setRequestProperty("X-HTTP-Method-Override", "PUT");
            connectionComprar.setRequestProperty("X-UT-Embed-Error", "true");
            connectionComprar.setRequestProperty("X-UT-PHISHING-TOKEN", ThreadStart.TOKEN);
            connectionComprar.setRequestProperty("X-UT-SID", ThreadStart.XUTSID);   
            connectionComprar.setUseCaches (false);connectionComprar.setDoInput(true);connectionComprar.setDoOutput(true);      

            //Send request
            DataOutputStream wrComprarContrato = new DataOutputStream (connectionComprar.getOutputStream ());
            wrComprarContrato.writeBytes (data);wrComprarContrato.flush ();wrComprarContrato.close ();
                          
            //Get Response  
            InputStream isComprarContrato = connectionComprar.getInputStream();     
            BufferedReader rdComprarContrato = new BufferedReader(new InputStreamReader(isComprarContrato));        
            String lineComprarContrato = rdComprarContrato.readLine();  
     
            ThreadStart.comprarContrato(lineComprarContrato);
        }catch (Exception e) {
            ThreadStart.conexionCorrecta=false;
            ThreadStart.conectado();
            ThreadStart.reConectar();
        }
    }

}
