import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;


public class MoverCompras {

    //Mover a Mazo de cambio
    static String listaCompra() throws Exception{
        String lineListaCompra=null;
            URL urlListaCompra;HttpsURLConnection connectionListaCompra;
            String setCookie = ThreadStart.futweb + "; " + ThreadStart.hl + "; " + ThreadStart.XSRFTOKEN + "; " + ThreadStart.EASFCWEBSESSION + ";" + ThreadStart.device_view;
            try{
                urlListaCompra = new URL(ThreadStart.urlBase+"/ut/game/fifa14/purchased/items");
                connectionListaCompra = (HttpsURLConnection)urlListaCompra.openConnection();
                connectionListaCompra.setRequestMethod("POST");
                connectionListaCompra.setRequestProperty("User-Agent", ThreadStart.user_agent); //Hacemos creer a EA que somos un navegador
                connectionListaCompra.setRequestProperty("Cookie", setCookie); //Enviamos las 3 cookies
                connectionListaCompra.setRequestProperty("Content-Type", "application/json");       
                connectionListaCompra.setRequestProperty("X-HTTP-Method-Override", "GET"); //Para poder eliminar un dato cambiamos de GET a DELETE
                connectionListaCompra.setRequestProperty("X-UT-Embed-Error", "true");
                connectionListaCompra.setRequestProperty("X-UT-SID", ThreadStart.XUTSID);   
                connectionListaCompra.setRequestProperty("X-UT-PHISHING-TOKEN", ThreadStart.TOKEN);
                connectionListaCompra.setUseCaches (false);connectionListaCompra.setDoInput(true);connectionListaCompra.setDoOutput(true);          
                                          
                //Get Response  
                InputStream isListaCompra = connectionListaCompra.getInputStream();             
                BufferedReader rdListaCompra = new BufferedReader(new InputStreamReader(isListaCompra));                
                lineListaCompra = rdListaCompra.readLine();

            }catch (Exception e) {
                ThreadStart.conexionCorrecta=false;
                ThreadStart.conectado();
                ThreadStart.reConectar();
            }
            return lineListaCompra;
        }
    
    static String moverCompra(String id) throws Exception{
        String lineMoverContrato=null;
            URL urlMoverContrato;HttpsURLConnection connectionMoverContrato;
            String setCookie = ThreadStart.futweb + "; " + ThreadStart.hl + "; " + ThreadStart.XSRFTOKEN + "; " + ThreadStart.EASFCWEBSESSION + ";" + ThreadStart.device_view;
            String data="{\"itemData\":[{\"pile\":\"trade\",\"id\":\""+id+"\"}]}";
            try{
                urlMoverContrato = new URL(ThreadStart.urlBase+"/ut/game/fifa14/item");
                connectionMoverContrato = (HttpsURLConnection)urlMoverContrato.openConnection();
                connectionMoverContrato.setRequestMethod("POST");
                connectionMoverContrato.setRequestProperty("User-Agent", ThreadStart.user_agent);
                connectionMoverContrato.setRequestProperty("Cookie", setCookie);
                connectionMoverContrato.setRequestProperty("Content-Type", "application/json");       
                connectionMoverContrato.setRequestProperty("X-HTTP-Method-Override", "PUT");
                connectionMoverContrato.setRequestProperty("X-UT-Embed-Error", "true");
                connectionMoverContrato.setRequestProperty("X-UT-PHISHING-TOKEN", ThreadStart.TOKEN);
                connectionMoverContrato.setRequestProperty("X-UT-SID", ThreadStart.XUTSID);   
                connectionMoverContrato.setUseCaches (false);connectionMoverContrato.setDoInput(true);connectionMoverContrato.setDoOutput(true);          
                    
                //Send request
                DataOutputStream wrMoverContrato = new DataOutputStream (connectionMoverContrato.getOutputStream ());
                wrMoverContrato.writeBytes (data);wrMoverContrato.flush ();wrMoverContrato.close ();
                             
                //Get Response  
                InputStream isMoverContrato = connectionMoverContrato.getInputStream();             
                BufferedReader rdMoverContrato = new BufferedReader(new InputStreamReader(isMoverContrato));                
                lineMoverContrato = rdMoverContrato.readLine();             

            }catch (Exception e) {
                ThreadStart.conexionCorrecta=false;
                ThreadStart.conectado();
                ThreadStart.reConectar();
            }
            return lineMoverContrato;
        }

}
