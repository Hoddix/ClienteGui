import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;


public class Vender {

    //Venta
    static String tradePile() throws Exception{
        String lineTradePile = null;
        URL urlTradePile;HttpsURLConnection connectionTradePile;
        String setCookie = ThreadStart.futweb + "; " + ThreadStart.hl + "; " + ThreadStart.XSRFTOKEN + "; " + ThreadStart.EASFCWEBSESSION + ";" + ThreadStart.device_view;
        try{
            urlTradePile = new URL(ThreadStart.urlBase+"/ut/game/fifa13/tradepile");
            connectionTradePile = (HttpsURLConnection)urlTradePile.openConnection();
            connectionTradePile.setRequestMethod("POST");
            connectionTradePile.setRequestProperty("User-Agent", ThreadStart.user_agent); //Hacemos creer a EA que somos un navegador
            connectionTradePile.setRequestProperty("Cookie", setCookie); //Enviamos las 3 cookies
            connectionTradePile.setRequestProperty("Content-Type", "application/json");       
            connectionTradePile.setRequestProperty("X-HTTP-Method-Override", "GET"); //Para poder eliminar un dato cambiamos de GET a DELETE
            connectionTradePile.setRequestProperty("X-UT-Embed-Error", "true");
            connectionTradePile.setRequestProperty("X-UT-PHISHING-TOKEN", ThreadStart.TOKEN);
            connectionTradePile.setRequestProperty("X-UT-SID", ThreadStart.XUTSID);   
            connectionTradePile.setUseCaches (false);connectionTradePile.setDoInput(true);connectionTradePile.setDoOutput(true);          
            
            //Get Response  
            InputStream isTradePile = connectionTradePile.getInputStream();             
            BufferedReader rdTradePile = new BufferedReader(new InputStreamReader(isTradePile));                
            lineTradePile = rdTradePile.readLine();
            
        }catch (Exception e) {
            ThreadStart.conexionCorrecta=false;
            ThreadStart.conectado();
            ThreadStart.reConectar();
        }        
        return lineTradePile;
   } 
   
    static void actionHouse(String itemId, int fila) throws Exception{
    	int precioventapuja = Integer.parseInt(Gui.modeloTablaLista.getValueAt(fila, 6).toString());
        URL urlActionHouse;HttpsURLConnection connectionActionHouse;
        String setCookie = ThreadStart.futweb + "; " + ThreadStart.hl + "; " + ThreadStart.XSRFTOKEN + "; " + ThreadStart.EASFCWEBSESSION + ";" + ThreadStart.device_view;      
        String data="{\"startingBid\":"+precioventapuja+",\"duration\":3600,\"itemData\":{\"id\":"+itemId+"},\"buyNowPrice\":0}";
        try{
            urlActionHouse = new URL("https://utas.fut.ea.com/ut/game/fifa13/auctionhouse");
            connectionActionHouse = (HttpsURLConnection)urlActionHouse.openConnection();
            connectionActionHouse.setRequestMethod("POST");
            connectionActionHouse.setRequestProperty("User-Agent", ThreadStart.user_agent); //Hacemos creer a EA que somos un navegador
            connectionActionHouse.setRequestProperty("Cookie", setCookie); //Enviamos las 3 cookies
            connectionActionHouse.setRequestProperty("Content-Type", "application/json");       
            connectionActionHouse.setRequestProperty("X-HTTP-Method-Override", "POST"); //Para poder eliminar un dato cambiamos de GET a DELETE
            connectionActionHouse.setRequestProperty("X-UT-Embed-Error", "true");
            connectionActionHouse.setRequestProperty("X-UT-PHISHING-TOKEN", ThreadStart.TOKEN);
            connectionActionHouse.setRequestProperty("X-UT-SID", ThreadStart.XUTSID);   
            connectionActionHouse.setUseCaches (false);connectionActionHouse.setDoInput(true);connectionActionHouse.setDoOutput(true);          
            
            //Send request
            DataOutputStream wrActionHouse = new DataOutputStream (connectionActionHouse.getOutputStream ());
            wrActionHouse.writeBytes (data);wrActionHouse.flush ();wrActionHouse.close ();
            
            //Get Response  
            InputStream isActionHouse = connectionActionHouse.getInputStream();             
            BufferedReader rdActionHouse = new BufferedReader(new InputStreamReader(isActionHouse));                
            String lineActionHouse = rdActionHouse.readLine();             
            
        }catch (Exception e) {
            ThreadStart.conexionCorrecta=false;
            ThreadStart.conectado();
            ThreadStart.reConectar();
        }
    } 
    
    static void actionHouseNoComprado(String itemId, int startingBid, int buyNowPrice) throws Exception{
        URL urlActionHouse;HttpsURLConnection connectionActionHouse;
        String setCookie = ThreadStart.futweb + "; " + ThreadStart.hl + "; " + ThreadStart.XSRFTOKEN + "; " + ThreadStart.EASFCWEBSESSION + ";" + ThreadStart.device_view;           
        String data="{\"startingBid\":"+startingBid+",\"duration\":3600,\"itemData\":{\"id\":"+itemId+"},\"buyNowPrice\":"+buyNowPrice+"}";
        try{
            urlActionHouse = new URL("https://utas.fut.ea.com/ut/game/fifa13/auctionhouse");
            connectionActionHouse = (HttpsURLConnection)urlActionHouse.openConnection();
            connectionActionHouse.setRequestMethod("POST");
            connectionActionHouse.setRequestProperty("User-Agent", ThreadStart.user_agent); //Hacemos creer a EA que somos un navegador
            connectionActionHouse.setRequestProperty("Cookie", setCookie); //Enviamos las 3 cookies
            connectionActionHouse.setRequestProperty("Content-Type", "application/json");       
            connectionActionHouse.setRequestProperty("X-HTTP-Method-Override", "POST"); //Para poder eliminar un dato cambiamos de GET a DELETE
            connectionActionHouse.setRequestProperty("X-UT-Embed-Error", "true");
            connectionActionHouse.setRequestProperty("X-UT-PHISHING-TOKEN", ThreadStart.TOKEN);
            connectionActionHouse.setRequestProperty("X-UT-SID", ThreadStart.XUTSID);   
            connectionActionHouse.setUseCaches (false);connectionActionHouse.setDoInput(true);connectionActionHouse.setDoOutput(true);          
            
            //Send request
            DataOutputStream wrActionHouse = new DataOutputStream (connectionActionHouse.getOutputStream ());
            wrActionHouse.writeBytes (data);wrActionHouse.flush ();wrActionHouse.close ();
            
            //Get Response  
            InputStream isActionHouse = connectionActionHouse.getInputStream();             
            BufferedReader rdActionHouse = new BufferedReader(new InputStreamReader(isActionHouse));                
            String lineActionHouse = rdActionHouse.readLine();             
            
        }catch (Exception e) {
            ThreadStart.conexionCorrecta=false;
            ThreadStart.conectado();
            ThreadStart.reConectar();
        }
    } 
   
   //Eliminar vendidos
   static void eliminarContrato(String tradeId) throws Exception{
       URL urlEliminarContrato;HttpsURLConnection connectionEliminarContrato;
       String setCookie = ThreadStart.futweb + "; " + ThreadStart.hl + "; " + ThreadStart.XSRFTOKEN + "; " + ThreadStart.EASFCWEBSESSION + ";" + ThreadStart.device_view;
       try{
           urlEliminarContrato = new URL("https://utas.fut.ea.com/ut/game/fifa14/trade/"+tradeId);
           connectionEliminarContrato = (HttpsURLConnection)urlEliminarContrato.openConnection();
           connectionEliminarContrato.setRequestMethod("POST");
           connectionEliminarContrato.setRequestProperty("User-Agent", ThreadStart.user_agent); //Hacemos creer a EA que somos un navegador
           connectionEliminarContrato.setRequestProperty("Cookie", setCookie); //Enviamos las 3 cookies
           connectionEliminarContrato.setRequestProperty("Content-Type", "application/json");       
           connectionEliminarContrato.setRequestProperty("X-HTTP-Method-Override", "DELETE"); //Para poder eliminar un dato cambiamos de GET a DELETE
           connectionEliminarContrato.setRequestProperty("X-UT-Embed-Error", "true");
           connectionEliminarContrato.setRequestProperty("X-UT-PHISHING-TOKEN", ThreadStart.TOKEN);
           connectionEliminarContrato.setRequestProperty("X-UT-SID", ThreadStart.XUTSID);   
           connectionEliminarContrato.setUseCaches (false);connectionEliminarContrato.setDoInput(true);connectionEliminarContrato.setDoOutput(true);          
                                     
           //Get Response  
           InputStream isEliminarContrato = connectionEliminarContrato.getInputStream();             
           BufferedReader rdEliminarContrato = new BufferedReader(new InputStreamReader(isEliminarContrato));                
           String lineEliminarContrato = rdEliminarContrato.readLine();             

       }catch (Exception e) {
           ThreadStart.conexionCorrecta=false;
           ThreadStart.conectado();
           ThreadStart.reConectar();
       }
   }

}
