import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.List;
import java.util.Map;

import javax.net.ssl.HttpsURLConnection;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ThreadStart implements Runnable{

	static boolean reconectar;
	static boolean conexionCorrecta, parar=true;
    
    static int monedasRestantes;
    static int cont=0; 
    static int contador;
    
	static String usuario;
	static String password;
	static String hash;
	
	static String precio;
	static long tiempo;
	static String cplayerVenta;
	static String cplayerRaroVenta;
	static String cmanagerVenta;
	static String cmanagerRaroVenta;
	
	
	ThreadStart(){	
		usuario = Gui.txtcuser.getText();
		password = Gui.txtcpass.getText();
		hash = Gui.txtcrespuesta.getText();
	}

    //Conexion
	static String user_agent = "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_8_3) AppleWebKit/537.31 (KHTML, like Gecko) Chrome/26.0.1410.65 Safari/537.31";
	static String cookies;
	static String personaId, personaName, platform, hl, XSRFTOKEN, EASFCWEBSESSION, JSESSIONID, remid, sid, device_view, futweb, XUTSID, TOKEN, FUTWebPhishing, nuc;
	static String urlActualSTR;
	static String urlSiguienteSTR;
	static String urlBase = "https://utas.fut.ea.com"; 
	static String EAweb;
	
	static void paso1(){
		
		try {
			System.out.println("PASO 1");
			String url = "http://www.easports.com/pl/fifa/football-club/ultimate-team";
		  
			URL obj = new URL(url);
			HttpURLConnection connection = (HttpURLConnection) obj.openConnection();
			connection.setRequestMethod("GET");
			connection.addRequestProperty("User-Agent", user_agent);
 
			//Get Response  
			InputStream is = connection.getInputStream();
			BufferedReader rd = new BufferedReader(new InputStreamReader(is));
			String line = rd.readLine();
						
			URL urlActual = connection.getURL();
			urlActualSTR = urlActual.toString();
			System.out.println("URL Actual: "+urlActualSTR);
			
			// get the cookie if need, for login
			Map<String,List<String>> headers = connection.getHeaderFields();  
			System.out.println("Headers: " + headers);
			List<String> sc = headers.get("Set-Cookie");
            int lengthCookies = sc.size();
            for (int i=0; i<lengthCookies;i++){
                int index;
                String cookies = sc.get(i);
                if((index=cookies.indexOf("XSRF-TOKEN=")) != -1){
                    int index2 = cookies.indexOf(";", index);
                    XSRFTOKEN = cookies.substring(index, index2);
                    System.out.println(XSRFTOKEN);
                }
                if((index=cookies.indexOf("EASFC-WEB-SESSION=")) != -1){
                    int index2 = cookies.indexOf(";", index);
                    EASFCWEBSESSION = cookies.substring(index, index2);
                    System.out.println(EASFCWEBSESSION);
                }
                if((index=cookies.indexOf("hl=")) != -1){
                    int index2 = cookies.indexOf(";", index);
                    hl = cookies.substring(index, index2);
                    System.out.println(hl);
                }
            }
			
			// get redirect url from "location" header field
			urlSiguienteSTR = connection.getHeaderField("Location");
			System.out.println("Location: " + urlSiguienteSTR);
			
			rd.close();
		
		} catch (Exception e) {e.printStackTrace();}
		
	}
	
	static void paso2(){
		System.out.println("\nPASO 2");
		String setCookie = ""+XSRFTOKEN+"; "+EASFCWEBSESSION+"";
		HttpsURLConnection.setFollowRedirects(false);
		
		try{
			URL obj = new URL(urlSiguienteSTR);
			HttpsURLConnection connection = (HttpsURLConnection) obj.openConnection();
			connection.setRequestMethod("GET");
			connection.addRequestProperty("User-Agent", user_agent);
			connection.addRequestProperty("Referer", "http://www.easports.com/pl/fifa/football-club/ultimate-team");
			connection.addRequestProperty("Cookie", setCookie);
			
			//Get Response  
			InputStream is = connection.getInputStream();
			BufferedReader rd = new BufferedReader(new InputStreamReader(is));
			String line = rd.readLine();
						
			URL urlActual = connection.getURL();
			urlActualSTR = urlActual.toString();
			System.out.println("URL Actual: "+urlActualSTR);
			
			// get the cookie if need, for login
			Map<String,List<String>> headers = connection.getHeaderFields();  
			System.out.println("Headers: " + headers);
			
			// get redirect url from "location" header field
			urlSiguienteSTR = connection.getHeaderField("Location");
			System.out.println("Location: " + urlSiguienteSTR);
			
		}catch(Exception e){}
	}
	
	static void paso3(){
		System.out.println("\nPASO 3");	
		URL url; HttpURLConnection connection;
		try{
			url = new URL(urlSiguienteSTR);
			connection = (HttpURLConnection)url.openConnection();
			connection.setRequestMethod("GET");
			connection.setRequestProperty("User-Agent", user_agent);
	        connection.setUseCaches (false);connection.setDoInput(true);connection.setDoOutput(true);
	
	        //Get Response  
	        InputStream is = connection.getInputStream();
	        BufferedReader rd = new BufferedReader(new InputStreamReader(is));
	        String line;
	
			// get the cookie if need, for login
			Map<String,List<String>> headers = connection.getHeaderFields();  
			System.out.println("Headers: " + headers);
			List<String> sc = headers.get("Set-Cookie");
            int lengthCookies = sc.size();
            for (int i=0; i<lengthCookies;i++){
                int index;
                String cookies = sc.get(i);
                if((index=cookies.indexOf("JSESSIONID=")) != -1){
                    int index2 = cookies.indexOf(";", index);
                    JSESSIONID = cookies.substring(index, index2);
                    System.out.println(JSESSIONID);
                }
            }
	        
			// get redirect url from "location" header field
			urlSiguienteSTR = connection.getHeaderField("Location");
			System.out.println("Location: " + urlSiguienteSTR);
	        
	        rd.close(); //Cerramos conexion con la URL
		}catch(Exception e){}
	}
	
	static void paso4(String parametros){
		System.out.println("\nPASO 4");	
		String setCookie = JSESSIONID;
		URL url; HttpsURLConnection connection;
		try{
			url = new URL(urlSiguienteSTR);
			connection = (HttpsURLConnection)url.openConnection();
			connection.setRequestMethod("POST");
            connection.setRequestProperty("User-Agent", user_agent);
            connection.addRequestProperty("Cookie", setCookie);
            connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            connection.setRequestProperty("Referer", urlActualSTR);
            connection.setRequestProperty("Content-Length", Integer.toString(parametros.getBytes().length));
            connection.setUseCaches (false);connection.setDoInput(true);connection.setDoOutput(true);

            //Send request
            DataOutputStream wr = new DataOutputStream (connection.getOutputStream ());
            wr.writeBytes (parametros);wr.flush ();wr.close ();
            
	        //Get Response  
	        InputStream is = connection.getInputStream();
	        BufferedReader rd = new BufferedReader(new InputStreamReader(is));
	        String line;
	
            URL urlActual = connection.getURL();
            urlActualSTR = urlActual.toString();
            System.out.println("URL Actual: "+urlActualSTR);
	        
	        // Obtenemos la cabecera para extraer las cookies
	        Map<String,List<String>> cm = connection.getHeaderFields();
	        System.out.println("Cabeceras: " + cm);
			List<String> urlSiguiente = cm.get("Location");
			
			// get redirect url from "location" header field
			urlSiguienteSTR = connection.getHeaderField("Location");
			System.out.println("Location: " + urlSiguienteSTR);
	        
	        
	        rd.close(); //Cerramos conexion con la URL
		}catch(Exception e){}
	}
	
	static void paso5(){
		System.out.println("\nPASO 5");		
		//String setCookie = ""+XSRFTOKEN+"; "+EASFCWEBSESSION+"";
		
		URL urlLogin;HttpsURLConnection connection;  
        try {
            urlLogin = new URL(urlSiguienteSTR);
            connection = (HttpsURLConnection)urlLogin.openConnection();
            connection.setRequestMethod("GET");
            connection.setRequestProperty("User-Agent", user_agent);
            //connection.addRequestProperty("Cookie", setCookie);
            connection.setRequestProperty("Referer", urlActualSTR);
            connection.setUseCaches (false);connection.setDoInput(true);connection.setDoOutput(true);

            //Get Response  
            InputStream is = connection.getInputStream();
            BufferedReader rd = new BufferedReader(new InputStreamReader(is));
            String line;
         
            while((line = rd.readLine()) != null) {
            	System.out.println(line);
            }

			// get the cookie if need, for login
			Map<String,List<String>> headers = connection.getHeaderFields();  
			System.out.println("Headers: " + headers);
			List<String> sc = headers.get("Set-Cookie");
            int lengthCookies = sc.size();
            for (int i=0; i<lengthCookies;i++){
                int index;
                String cookies = sc.get(i);
                if((index=cookies.indexOf("remid=")) != -1){
                    int index2 = cookies.indexOf(";", index);
                    remid = cookies.substring(index, index2);
                    System.out.println(remid);
                }
                if((index=cookies.indexOf("sid=")) != -1){
                    int index2 = cookies.indexOf(";", index);
                    sid = cookies.substring(index, index2);
                    System.out.println(sid);
                }
            }
            
			// get redirect url from "location" header field
			urlSiguienteSTR = connection.getHeaderField("Location");
			System.out.println("Location: " + urlSiguienteSTR);
            
            rd.close(); //Cerramos conexion con la URL
        }catch (Exception e) {       } 
	}
	
	static void paso6(){
		System.out.println("\nPASO 6");		
		String setCookie = hl+"; "+XSRFTOKEN+"; "+EASFCWEBSESSION+"";
		
		URL urlLogin;HttpURLConnection connection;  
        try {
            urlLogin = new URL(urlSiguienteSTR);
            connection = (HttpURLConnection)urlLogin.openConnection();
            connection.setRequestMethod("GET");
            connection.setRequestProperty("User-Agent", user_agent);
            connection.addRequestProperty("Cookie", setCookie);
            connection.setUseCaches (false);connection.setDoInput(true);connection.setDoOutput(true);

            //Get Response  
            InputStream is = connection.getInputStream();
            BufferedReader rd = new BufferedReader(new InputStreamReader(is));
            String line = rd.readLine();

            // Obtenemos la cabecera para extraer las cookies
			// get the cookie if need, for login
			Map<String,List<String>> headers = connection.getHeaderFields();  
			System.out.println("Headers: " + headers);
			List<String> sc = headers.get("Set-Cookie");
            int lengthCookies = sc.size();
            for (int i=0; i<lengthCookies;i++){
                int index;
                String cookies = sc.get(i);
                if((index=cookies.indexOf("EASFC-WEB-SESSION=")) != -1){
                    int index2 = cookies.indexOf(";", index);
                    EASFCWEBSESSION = cookies.substring(index, index2);
                    System.out.println(EASFCWEBSESSION);
                }
            }
            
			// get redirect url from "location" header field
			urlSiguienteSTR = connection.getHeaderField("Location");
			System.out.println("Location: " + urlSiguienteSTR);
            
            rd.close(); //Cerramos conexion con la URL
        }catch (Exception e) {       } 
	}
	
	static void paso7(){
		System.out.println("\nPASO 7");		
		String setCookie = hl+"; "+XSRFTOKEN+"; "+EASFCWEBSESSION+"";
		
		URL urlLogin;HttpURLConnection connection;  
        try {
            urlLogin = new URL(urlSiguienteSTR);
            connection = (HttpURLConnection)urlLogin.openConnection();
            connection.setRequestMethod("GET");
            connection.setRequestProperty("User-Agent", user_agent);
            connection.addRequestProperty("Cookie", setCookie);
            connection.setUseCaches (false);connection.setDoInput(true);connection.setDoOutput(true);

            //Get Response  
            InputStream is = connection.getInputStream();
            BufferedReader rd = new BufferedReader(new InputStreamReader(is));
            String line, lineFinal = null;
            while((line = rd.readLine()) != null) {
            	lineFinal += line;
            	System.out.println(line);
            }

            //Extraemos el Nuc
            String nucA = lineFinal;
            String nucB = nucA.substring(nucA.indexOf("userid"));
            nuc = nucB.substring(nucB.indexOf(": \"")+3 , nucB.indexOf("\" "));
            System.out.println("nuccleusID:" + nuc);
            
			// get the cookie if need, for login
			Map<String,List<String>> headers = connection.getHeaderFields();  
			System.out.println("Headers: " + headers);
			List<String> sc = headers.get("Set-Cookie");
            int lengthCookies = sc.size();
            for (int i=0; i<lengthCookies;i++){
                int index;
                String cookies = sc.get(i);
                if((index=cookies.indexOf("device_view=")) != -1){
                    int index2 = cookies.indexOf(";", index);
                    device_view = cookies.substring(index, index2);
                    System.out.println(device_view);
                }
            }
            
			// get redirect url from "location" header field
			urlSiguienteSTR = connection.getHeaderField("Location");
			System.out.println("Location: " + urlSiguienteSTR);
            
            rd.close(); //Cerramos conexion con la URL
        }catch (Exception e) {       } 
	}
	
	static void paso8(){
		System.out.println("\nPASO 8");		
		String setCookie = hl+"; "+XSRFTOKEN+"; "+EASFCWEBSESSION+"; "+device_view;
		
		URL urlLogin;HttpURLConnection connection;  
        try {
            urlLogin = new URL("http://www.easports.com/iframe/fut/?locale=es_ES&baseShowoffUrl=http%3A%2F%2Fwww.easports.com%2Fes%2Ffifa%2Ffootball-club%2Fultimate-team%2Fshow-off&guest_app_uri=http%3A%2F%2Fwww.easports.com%2Fes%2Ffifa%2Ffootball-club%2Fultimate-team");
            connection = (HttpURLConnection)urlLogin.openConnection();
            connection.setRequestMethod("GET");
            connection.setRequestProperty("User-Agent", user_agent);
            connection.addRequestProperty("Cookie", setCookie);
            connection.addRequestProperty("Referer", "http://www.easports.com/es/fifa/football-club/ultimate-team");
            connection.setUseCaches (false);connection.setDoInput(true);connection.setDoOutput(true);

            //Get Response  
            InputStream is = connection.getInputStream();
            BufferedReader rd = new BufferedReader(new InputStreamReader(is));
            String line;
            while((line = rd.readLine()) != null) {
            	System.out.println(line);
            }
            
			// get the cookie if need, for login
			Map<String,List<String>> headers = connection.getHeaderFields();  
			System.out.println("Headers: " + headers);
			List<String> sc = headers.get("Set-Cookie");
            int lengthCookies = sc.size();
            for (int i=0; i<lengthCookies;i++){
                int index;
                String cookies = sc.get(i);
                if((index=cookies.indexOf("futweb=")) != -1){
                    int index2 = cookies.indexOf(";", index);
                    futweb = cookies.substring(index, index2);
                    System.out.println(futweb);
                }
            }
            
			// get redirect url from "location" header field
			urlSiguienteSTR = connection.getHeaderField("Location");
			System.out.println("Location: " + urlSiguienteSTR);
            
            rd.close(); //Cerramos conexion con la URL
        }catch (Exception e) {       } 
	}
	
	static void paso9(){
		System.out.println("\nPASO 9");		
		String setCookie = sid + "; " + remid;
		
		URL urlLogin;HttpURLConnection connection;  
        try {
            urlLogin = new URL(urlSiguienteSTR);
            connection = (HttpURLConnection)urlLogin.openConnection();
            connection.setRequestMethod("GET");
            connection.setRequestProperty("User-Agent", user_agent);
            connection.addRequestProperty("Cookie", setCookie);
            connection.addRequestProperty("Referer", "http://www.easports.com/es/fifa/football-club/ultimate-team");
            connection.setUseCaches (false);connection.setDoInput(true);connection.setDoOutput(true);

            //Get Response  
            InputStream is = connection.getInputStream();
            BufferedReader rd = new BufferedReader(new InputStreamReader(is));
            String line;
            while((line = rd.readLine()) != null) {
            	System.out.println(line);
            }
            
			// get the cookie if need, for login
			Map<String,List<String>> headers = connection.getHeaderFields();  
			System.out.println("Headers: " + headers);
			List<String> sc = headers.get("Set-Cookie");
            int lengthCookies = sc.size();
            for (int i=0; i<lengthCookies;i++){
                int index;
                String cookies = sc.get(i);
                if((index=cookies.indexOf("sid=")) != -1){
                    int index2 = cookies.indexOf(";", index);
                    sid = cookies.substring(index, index2);
                    System.out.println(sid);
                }
            }
            
			// get redirect url from "location" header field
			urlSiguienteSTR = connection.getHeaderField("Location");
			System.out.println("Location: " + urlSiguienteSTR);
            
            rd.close(); //Cerramos conexion con la URL
        }catch (Exception e) {       } 
	}
	
	static void paso10(){
		System.out.println("\nPASO 10");		
		String setCookie = futweb + "; " + hl + "; " + XSRFTOKEN + "; " + EASFCWEBSESSION + ";" + device_view;
		
		URL urlLogin;HttpURLConnection connection;  
        try {
            urlLogin = new URL(urlSiguienteSTR);
            connection = (HttpURLConnection)urlLogin.openConnection();
            connection.setRequestMethod("GET");
            connection.setRequestProperty("User-Agent", user_agent);
            connection.addRequestProperty("Cookie", setCookie);
            connection.addRequestProperty("Referer", "http://www.easports.com/es/fifa/football-club/ultimate-team");
            connection.setUseCaches (false);connection.setDoInput(true);connection.setDoOutput(true);

            //Get Response  
            InputStream is = connection.getInputStream();
            BufferedReader rd = new BufferedReader(new InputStreamReader(is));
            String line;
            while((line = rd.readLine()) != null) {
            	System.out.println(line);
            }
            
			// get the cookie if need, for login
			Map<String,List<String>> headers = connection.getHeaderFields();  
			System.out.println("Headers: " + headers);
			List<String> sc = headers.get("Set-Cookie");
            int lengthCookies = sc.size();
            for (int i=0; i<lengthCookies;i++){
                int index;
                String cookies = sc.get(i);
                if((index=cookies.indexOf("futweb=")) != -1){
                    int index2 = cookies.indexOf(";", index);
                    futweb = cookies.substring(index, index2);
                    System.out.println(futweb);
                }
            }
            
			// get redirect url from "location" header field
			urlSiguienteSTR = connection.getHeaderField("Location");
			System.out.println("Location: " + urlSiguienteSTR);
            
            rd.close(); //Cerramos conexion con la URL
        }catch (Exception e) {       } 
	}
	
	static void paso11(){
		System.out.println("\nPASO 11");		
		String setCookie = futweb + "; " + hl + "; " + XSRFTOKEN + "; " + EASFCWEBSESSION + ";" + device_view;
		
		URL urlLogin;HttpURLConnection connection;  
        try {
            urlLogin = new URL(urlSiguienteSTR);
            connection = (HttpURLConnection)urlLogin.openConnection();
            connection.setRequestMethod("GET");
            connection.setRequestProperty("User-Agent", user_agent);
            connection.addRequestProperty("Cookie", setCookie);
            connection.addRequestProperty("Referer", "http://www.easports.com/es/fifa/football-club/ultimate-team");
            connection.setUseCaches (false);connection.setDoInput(true);connection.setDoOutput(true);

            //Get Response  
            InputStream is = connection.getInputStream();
            BufferedReader rd = new BufferedReader(new InputStreamReader(is));
            String line;
            while((line = rd.readLine()) != null) {
            	System.out.println(line);
            }
            
			// get the cookie if need, for login
			Map<String,List<String>> headers = connection.getHeaderFields();  
			System.out.println("Headers: " + headers);
            
            rd.close(); //Cerramos conexion con la URL
        }catch (Exception e) {       } 
	}
	
	static void paso12(){
		System.out.println("\nPASO 12");
		String setCookie = futweb + "; " + hl + "; " + XSRFTOKEN + "; " + EASFCWEBSESSION + ";" + device_view;
		
		URL urlLogin;HttpURLConnection connection;  
		long tiempoUnix = System.currentTimeMillis()/1000; //Tiempo Unix
        try {
            urlLogin = new URL("http://www.easports.com/iframe/fut/p/ut/shards?_=" + tiempoUnix);
            connection = (HttpURLConnection)urlLogin.openConnection();
            connection.setRequestMethod("GET");
            connection.setRequestProperty("User-Agent", user_agent);
            connection.addRequestProperty("Content-Type", "application/json");
            connection.addRequestProperty("Easw-Session-Data-Nucleus-Id", nuc);
            connection.addRequestProperty("Cookie", setCookie);
            connection.addRequestProperty("Referer", "http://www.easports.com/iframe/fut/?baseShowoffUrl=http%3A%2F%2Fwww.easports.com%2Fes%2Ffifa%2Ffootball-club%2Fultimate-team%2Fshow-off&guest_app_uri=http%3A%2F%2Fwww.easports.com%2Fes%2Ffifa%2Ffootball-club%2Fultimate-team&locale=es_ES");
            connection.addRequestProperty("X-Requested-With", "XMLHttpRequest");
            connection.addRequestProperty("X-UT-Embed-Error", "true");
            connection.addRequestProperty("X-UT-Route", urlBase);
            connection.setUseCaches (false);connection.setDoInput(true);connection.setDoOutput(true);

            //Get Response  
            InputStream is = connection.getInputStream();
            BufferedReader rd = new BufferedReader(new InputStreamReader(is));
            String line;
            while((line = rd.readLine()) != null) {
            	System.out.println(line);
            }
            
			// get the cookie if need, for login
			Map<String,List<String>> headers = connection.getHeaderFields();  
			System.out.println("Headers: " + headers);
            
            rd.close(); //Cerramos conexion con la URL
        }catch (Exception e) {       } 
	}
	
	static void paso13XBOX(){
		System.out.println("\nPASO 13");
		String setCookie = futweb + "; " + hl + "; " + XSRFTOKEN + "; " + EASFCWEBSESSION + ";" + device_view;
		
		URL urlLogin;HttpURLConnection connection;  
		long tiempoUnix = System.currentTimeMillis()/1000; //Tiempo Unix
        try {
            urlLogin = new URL("http://www.easports.com/iframe/fut/p/ut/game/fifa14/user/accountinfo?_=" + tiempoUnix);
            connection = (HttpURLConnection)urlLogin.openConnection();
            connection.setRequestMethod("GET");
            connection.setRequestProperty("User-Agent", user_agent);
            connection.addRequestProperty("Content-Type", "application/json");
            connection.addRequestProperty("Easw-Session-Data-Nucleus-Id", nuc);
            connection.addRequestProperty("Cookie", setCookie);
            connection.addRequestProperty("Referer", "http://www.easports.com/iframe/fut/?baseShowoffUrl=http%3A%2F%2Fwww.easports.com%2Fes%2Ffifa%2Ffootball-club%2Fultimate-team%2Fshow-off&guest_app_uri=http%3A%2F%2Fwww.easports.com%2Fes%2Ffifa%2Ffootball-club%2Fultimate-team&locale=es_ES");
            connection.addRequestProperty("X-Requested-With", "XMLHttpRequest");
            connection.addRequestProperty("X-UT-Embed-Error", "true");
            connection.addRequestProperty("X-UT-Route", "https://utas.fut.ea.com:443");
            connection.setUseCaches (false);connection.setDoInput(true);connection.setDoOutput(true);

            //Get Response  
            InputStream is = connection.getInputStream();
            BufferedReader rd = new BufferedReader(new InputStreamReader(is));
            String line = rd.readLine();
            System.out.println(line);

            //Get Parametros
            ObjectMapper mapper = new ObjectMapper();
    		JsonNode rootNode = mapper.readValue(line.getBytes(), JsonNode.class); 
    		JsonNode hits = rootNode.get("userAccountInfo");
    		JsonNode dataObj, dataObj2, dataObj3, dataObj4, dataObj5, dataObj6, dataObj7;
    		dataObj = hits.get("personas");		
    		dataObj2 = dataObj.get(0);		
    		dataObj3 = dataObj2.get("personaId");		
    		personaId = dataObj3.toString();
    		dataObj4 = dataObj2.get("personaName");		
    		personaName = dataObj4.toString().replace("\"", "");
    		dataObj5 = dataObj2.get("userClubList");		
    		dataObj6 = dataObj5.get(0);
    		dataObj7 = dataObj6.get("platform");
    		platform = dataObj7.toString().replace("\"", "");
            System.out.println(platform);
			// get the cookie if need, for login
			Map<String,List<String>> headers = connection.getHeaderFields();  
			System.out.println("Headers: " + headers);
            
            rd.close(); //Cerramos conexion con la URL
        }catch (Exception e) {       } 
	}
	
	static void paso13PS3(){
		System.out.println("\nPASO 13");
		String setCookie = futweb + "; " + hl + "; " + XSRFTOKEN + "; " + EASFCWEBSESSION + ";" + device_view;
		
		URL urlLogin;HttpURLConnection connection;  
		long tiempoUnix = System.currentTimeMillis()/1000; //Tiempo Unix
        try {
            urlLogin = new URL("http://www.easports.com/iframe/fut/p/ut/game/fifa14/user/accountinfo?_=" + tiempoUnix);
            connection = (HttpURLConnection)urlLogin.openConnection();
            connection.setRequestMethod("GET");
            connection.setRequestProperty("User-Agent", user_agent);
            connection.addRequestProperty("Content-Type", "application/json");
            connection.addRequestProperty("Easw-Session-Data-Nucleus-Id", nuc);
            connection.addRequestProperty("Cookie", setCookie);
            connection.addRequestProperty("Referer", "http://www.easports.com/iframe/fut/?baseShowoffUrl=http%3A%2F%2Fwww.easports.com%2Fes%2Ffifa%2Ffootball-club%2Fultimate-team%2Fshow-off&guest_app_uri=http%3A%2F%2Fwww.easports.com%2Fes%2Ffifa%2Ffootball-club%2Fultimate-team&locale=es_ES");
            connection.addRequestProperty("X-Requested-With", "XMLHttpRequest");
            connection.addRequestProperty("X-UT-Embed-Error", "true");
            connection.addRequestProperty("X-UT-Route", "https://utas.s2.fut.ea.com:443");
            connection.setUseCaches (false);connection.setDoInput(true);connection.setDoOutput(true);

            //Get Response  
            InputStream is = connection.getInputStream();
            BufferedReader rd = new BufferedReader(new InputStreamReader(is));
            String line = rd.readLine();
            System.out.println(line);

            //Get Parametros
            ObjectMapper mapper = new ObjectMapper();
    		JsonNode rootNode = mapper.readValue(line.getBytes(), JsonNode.class); 
    		JsonNode hits = rootNode.get("userAccountInfo");
    		JsonNode dataObj, dataObj2, dataObj3, dataObj4, dataObj5, dataObj6, dataObj7;
    		dataObj = hits.get("personas");		
    		dataObj2 = dataObj.get(0);		
    		dataObj3 = dataObj2.get("personaId");		
    		personaId = dataObj3.toString();
    		dataObj4 = dataObj2.get("personaName");		
    		personaName = dataObj4.toString().replace("\"", "");
    		dataObj5 = dataObj2.get("userClubList");		
    		dataObj6 = dataObj5.get(0);
    		dataObj7 = dataObj6.get("platform");
    		platform = dataObj7.toString().replace("\"", "");
            System.out.println(platform);
			// get the cookie if need, for login
			Map<String,List<String>> headers = connection.getHeaderFields();  
			System.out.println("Headers: " + headers);
            
            rd.close(); //Cerramos conexion con la URL
        }catch (Exception e) {       } 
	}
	
	static void seleccionUrl(){
		if(platform.equalsIgnoreCase("360")==true || platform.equalsIgnoreCase("ios")==true || platform.equalsIgnoreCase("and")==true){
			urlBase = "https://utas.fut.ea.com:443";
			EAweb = "https://utas.fut.ea.com";
		}else{
			urlBase = "https://utas.s2.fut.ea.com:443";
			EAweb = "https://utas.s2.fut.ea.com";
		}
	}
	
	static void paso14(){
		System.out.println("\nPASO 14");
		String setCookie = futweb + "; " + hl + "; " + XSRFTOKEN + "; " + EASFCWEBSESSION + ";" + device_view;	
 
		String dataAuth = "{ \"isReadOnly\": false, \"sku\": \"FUT14WEB\", \"clientVersion\": 1, \"nuc\": " + nuc + ", \"nucleusPersonaId\": " + personaId + ", \"nucleusPersonaDisplayName\": \"" + personaName + "\", \"nucleusPersonaPlatform\": \"" + platform + "\", \"locale\": \"es-ES\", \"method\": \"authcode\", \"priorityLevel\":4, \"identification\": { \"authCode\": \"\" } }";
		URL urlLogin;HttpURLConnection connection;  
        try {
            urlLogin = new URL("http://www.easports.com/iframe/fut/p/ut/auth");
            connection = (HttpURLConnection)urlLogin.openConnection();
            connection.setRequestMethod("POST");
            connection.setRequestProperty("User-Agent", user_agent);
            connection.addRequestProperty("Content-Type", "application/json");
            connection.addRequestProperty("Easw-Session-Data-Nucleus-Id", nuc);
            connection.addRequestProperty("Cookie", setCookie);
            connection.setRequestProperty("Content-Length", "" +Integer.toString(dataAuth.getBytes().length));
            connection.addRequestProperty("Referer", "http://www.easports.com/iframe/fut/?baseShowoffUrl=http%3A%2F%2Fwww.easports.com%2Fes%2Ffifa%2Ffootball-club%2Fultimate-team%2Fshow-off&guest_app_uri=http%3A%2F%2Fwww.easports.com%2Fes%2Ffifa%2Ffootball-club%2Fultimate-team&locale=es_ES");
            connection.addRequestProperty("X-Requested-With", "XMLHttpRequest");
            connection.addRequestProperty("X-UT-Embed-Error", "true");
            connection.addRequestProperty("X-UT-Route", urlBase);
            connection.setUseCaches (false);connection.setDoInput(true);connection.setDoOutput(true);

            //Send request
            DataOutputStream wrAuth = new DataOutputStream (connection.getOutputStream ());
            wrAuth.writeBytes (dataAuth);wrAuth.flush ();wrAuth.close ();
            
            //Get Response  
            InputStream is = connection.getInputStream();
            BufferedReader rd = new BufferedReader(new InputStreamReader(is));
            String line = rd.readLine();
            System.out.println(line);
            
            //Extraemos el SID
            String XUTSIDA = line;
            String XUTSIDB = XUTSIDA.substring(XUTSIDA.indexOf("sid"));
            XUTSID = XUTSIDB.substring(XUTSIDB.indexOf(":\"")+2 , XUTSIDB.indexOf("}")-1);
            System.out.println(XUTSID);
            
			// get the cookie if need, for login
			Map<String,List<String>> headers = connection.getHeaderFields();  
			System.out.println("Headers: " + headers);
            
            rd.close(); //Cerramos conexion con la URL
        }catch (Exception e) {       } 
	}
	
	static void paso15(){
		System.out.println("\nPASO 15");
		String setCookie = futweb + "; " + hl + "; " + XSRFTOKEN + "; " + EASFCWEBSESSION + ";" + device_view;

		URL urlLogin;HttpURLConnection connection;  
		long tiempoUnix = System.currentTimeMillis()/1000; //Tiempo Unix
        try {
            urlLogin = new URL("http://www.easports.com/iframe/fut/p/ut/game/fifa14/phishing/question?_=" + tiempoUnix);
            connection = (HttpURLConnection)urlLogin.openConnection();
            connection.setRequestMethod("GET");
            connection.setRequestProperty("User-Agent", user_agent);
            connection.addRequestProperty("Content-Type", "application/json");
            connection.addRequestProperty("Easw-Session-Data-Nucleus-Id", nuc);
            connection.addRequestProperty("Cookie", setCookie);
            connection.addRequestProperty("Referer", "http://www.easports.com/iframe/fut/?baseShowoffUrl=http%3A%2F%2Fwww.easports.com%2Fes%2Ffifa%2Ffootball-club%2Fultimate-team%2Fshow-off&guest_app_uri=http%3A%2F%2Fwww.easports.com%2Fes%2Ffifa%2Ffootball-club%2Fultimate-team&locale=es_ES");
            connection.addRequestProperty("X-Requested-With", "XMLHttpRequest");
            connection.addRequestProperty("X-UT-Embed-Error", "true");
            connection.addRequestProperty("X-UT-Route", urlBase);
            connection.addRequestProperty("X-UT-SID", XUTSID);
            connection.setUseCaches (false);connection.setDoInput(true);connection.setDoOutput(true);

            //Get Response  
            InputStream is = connection.getInputStream();
            BufferedReader rd = new BufferedReader(new InputStreamReader(is));
            String line;
            while((line = rd.readLine()) != null) {
            	System.out.println(line);
            }

			// get the cookie if need, for login
			Map<String,List<String>> headers = connection.getHeaderFields();  
			System.out.println("Headers: " + headers);
            
            rd.close(); //Cerramos conexion con la URL
        }catch (Exception e) {       } 
	}
	
	static String hashRespuesta(String respuesta){
		URL urlLogin;HttpURLConnection connection;  
		String dataAuth = "nombre=" + respuesta;
		String hash = null;
        try {
            urlLogin = new URL("URL DEL HASH");//No existe
            connection = (HttpURLConnection)urlLogin.openConnection();
            connection.setRequestMethod("POST");
            connection.setRequestProperty("User-Agent", user_agent);
            connection.setRequestProperty("Content-Length", Integer.toString(dataAuth.getBytes().length));
            connection.setRequestProperty("Referer", "http://vicahi.com/1/hash.php");
            connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");

            connection.setUseCaches (false);connection.setDoInput(true);connection.setDoOutput(true);

            //Send request
            DataOutputStream wrAuth = new DataOutputStream (connection.getOutputStream ());
            wrAuth.writeBytes (dataAuth);wrAuth.flush ();wrAuth.close ();
            
            //Get Response  
            InputStream is = connection.getInputStream();
            BufferedReader rd = new BufferedReader(new InputStreamReader(is));
            String line = rd.readLine();
            
			hash = line;
            
            rd.close(); //Cerramos conexion con la URL
        }catch (Exception e) {       } 
		return hash;
	}
	
	static void paso16(String hash){
		System.out.println("\nPASO 16");
		String setCookie = futweb + "; " + hl + "; " + XSRFTOKEN + "; " + EASFCWEBSESSION + ";" + device_view;	

		String dataAuth = "answer=" + hashRespuesta(hash);
		System.out.println(dataAuth);
		URL urlLogin;HttpURLConnection connection;  
        try {
            urlLogin = new URL("http://www.easports.com/iframe/fut/p/ut/game/fifa14/phishing/validate");
            connection = (HttpURLConnection)urlLogin.openConnection();
            connection.setRequestMethod("POST");
            connection.setRequestProperty("User-Agent", user_agent);
            connection.addRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            connection.addRequestProperty("Easw-Session-Data-Nucleus-Id", nuc);
            connection.addRequestProperty("Cookie", setCookie);
            connection.setRequestProperty("Content-Length", "" +Integer.toString(dataAuth.getBytes().length));
            connection.addRequestProperty("Referer", "http://www.easports.com/iframe/fut/?baseShowoffUrl=http%3A%2F%2Fwww.easports.com%2Fes%2Ffifa%2Ffootball-club%2Fultimate-team%2Fshow-off&guest_app_uri=http%3A%2F%2Fwww.easports.com%2Fes%2Ffifa%2Ffootball-club%2Fultimate-team&locale=es_ES");
            connection.addRequestProperty("X-Requested-With", "XMLHttpRequest");
            connection.addRequestProperty("X-UT-Embed-Error", "true");
            connection.addRequestProperty("X-UT-Route", urlBase);
            connection.addRequestProperty("X-UT-SID", XUTSID);
            connection.setUseCaches (false);connection.setDoInput(true);connection.setDoOutput(true);

            //Send request
            DataOutputStream wrAuth = new DataOutputStream (connection.getOutputStream ());
            wrAuth.writeBytes (dataAuth);wrAuth.flush ();wrAuth.close ();
            
            //Get Response  
            InputStream is = connection.getInputStream();
            BufferedReader rd = new BufferedReader(new InputStreamReader(is));
            String line = rd.readLine();
            System.out.println(line);
            
            //Extraemos el SID
            String XUTSIDA = line;
            String XUTSIDB = XUTSIDA.substring(XUTSIDA.indexOf("token"));
            TOKEN = XUTSIDB.substring(XUTSIDB.indexOf(":\"")+2 , XUTSIDB.indexOf(",")-1);
            System.out.println(TOKEN);
			
			// get the cookie if need, for login
			Map<String,List<String>> headers = connection.getHeaderFields();  
			System.out.println("Headers: " + headers);
			List<String> sc = headers.get("Set-Cookie");
            int lengthCookies = sc.size();
            for (int i=0; i<lengthCookies;i++){
                int index;
                String cookies = sc.get(i);
                if((index=cookies.indexOf("FUTWebPhishing")) != -1){
                    int index2 = cookies.indexOf(";", index);
                    FUTWebPhishing = cookies.substring(index, index2);
                    System.out.println(FUTWebPhishing);
                }
            }
            
            rd.close(); //Cerramos conexion con la URL
            
            JTableFilas.filas("Conexion realizada con exito");
	        monedasRestantes=Monedas.monedas();
//        	JTableFilas.filas("Monedas: "+monedasRestantes);
        	Gui.mostrarMonedas(monedasRestantes);
            conexionCorrecta=true;
            conectado();
        }catch (Exception e) {
            conexionCorrecta=false;
            conectado();
			reConectar();
        }

    }
    
    static void conectado(){
        if(conexionCorrecta==true){
            Gui.noConectado.setVisible(false);
            Gui.conectado.setVisible(true);
        }else{
        	Gui.conectado.setVisible(false);
            Gui.noConectado.setVisible(true);
        }
    }
    
    //Busqueda
    static void busqueda() throws Exception{
        while(parar==true){             
        	try{
        		for(int x=0;x<Gui.modeloTablaLista.getRowCount();x++){
//		String calumnas[]={"tipo","categoria","Nombre","id","card","P.Compra","P.Venta","P.Venta Ya","Level","Posicion"}; 
        			String nombreJugador = Gui.modeloTablaLista.getValueAt(x, 2).toString();
        			String assetId = Gui.modeloTablaLista.getValueAt(x, 3).toString();
        			String rareflag = Gui.modeloTablaLista.getValueAt(x, 4).toString();
        			String maxb = Gui.modeloTablaLista.getValueAt(x, 5).toString();
			       
        			JTableFilas.filas("Buscando a "+ nombreJugador +"...");
        			
    				String resto = CrearURL.playerURL(assetId,maxb);

        			ObjectMapper mapper = new ObjectMapper();
        			JsonNode rootNode;									
        			rootNode = mapper.readValue(Buscar.busquedaContrato(EAweb + resto).getBytes(), JsonNode.class);
        			JsonNode hits = rootNode.get("auctionInfo");
        			JsonNode oneHit, dataObj, dataObj2, dataObj3, dataObj4;
        			int idx = 0;
        			if (hits != null){
        				while ((oneHit = hits.get(idx)) != null){
        					dataObj = oneHit.get("tradeId");
        					dataObj2 = oneHit.get("itemData");
        					dataObj3 = dataObj2.get("assetId");
        					dataObj4 = dataObj2.get("rareflag");
        					if(dataObj3.toString().equalsIgnoreCase(assetId) && dataObj4.toString().equalsIgnoreCase(rareflag)){
        						estadoPuja(dataObj.toString()); 
        					}
        					idx++;
        				}    
        			}
        			
        			//if(Monedas.monedas()<1000){System.exit(0);}  
        			
        			Thread.sleep(Integer.parseInt(Gui.txttiempo.getText()));
        		}
        	}catch (Exception e) {
        		e.getMessage();
        		reConectar();
        	}
        }
    }
    
    static void estadoPuja(String tradeId) throws Exception{ 
    	
    	String lineEstadoPuja = EstadoPuja.estadoPuja(tradeId);
    	
    	ObjectMapper mapper = new ObjectMapper();
    	JsonNode rootNode = mapper.readValue(lineEstadoPuja.getBytes(), JsonNode.class); 
		JsonNode hits = rootNode.get("auctionInfo");
		JsonNode oneHit, dataObj = null, dataObj2 = null, dataObj3 = null;
		int idx = 0;
		if (hits != null){
			while ((oneHit = hits.get(idx)) != null){
				dataObj = oneHit.get("tradeId");
		    	dataObj2 = oneHit.get("buyNowPrice");
		    	dataObj3 = oneHit.get("expires");
				idx++;
			}    
		}	
    	if(dataObj3.asInt()!=-1) {
    		Comprar.comprarContrato(dataObj.toString(), dataObj2.toString());
    	}    
    }
    
    static void comprarContrato(String lineComprarContrato) throws Exception{  
    	
        if(lineComprarContrato.indexOf("debug")!= -1){  
            JTableFilas.filas("No Comprado, Has llegado tarde");
        }
        else{
            JTableFilas.filas("Comprado");
            Gui.mostrarMonedas(monedasRestantes);
            Thread.sleep(6000);
            //listaCompra(fila);
        }       
    }
    
	static void reConectar(){
		try {
			Thread.sleep(10000);
			String parametros = "email=" + URLEncoder.encode(usuario, "UTF-8") +"&password=" + URLEncoder.encode(password, "UTF-8") + "&_rememberMe=on&rememberMe=on&_eventId=submit&facebookAuth=";
			paso1();
			paso2();
			paso3();
			paso4(parametros);
			paso5();
			paso6();
			paso7();
			paso8();
			paso9();
			paso10();
			paso11();
			paso12();
			paso13XBOX();
			paso13PS3();
			seleccionUrl();
			paso14();
			paso15();
//			hashRespuesta(hash);
			paso16(hash);
			busqueda();
		}
		catch(Exception c) {c.printStackTrace(); }
	}
		
	//Ejecutar clase
	public void run() {
		new ThreadStart();
		String usuario=Gui.txtcuser.getText();
		String password = Gui.txtcpass.getText();
		String hash = Gui.txtcrespuesta.getText();
		try {
			String parametros = "email=" + URLEncoder.encode(usuario, "UTF-8") +"&password=" + URLEncoder.encode(password, "UTF-8") + "&_rememberMe=on&rememberMe=on&_eventId=submit&facebookAuth=";
			paso1();
			paso2();
			paso3();
			paso4(parametros);
			paso5();
			paso6();
			paso7();
			paso8();
			paso9();
			paso10();
			paso11();
			paso12();
			paso13XBOX();
			paso13PS3();
			seleccionUrl();
			paso14();
			paso15();
	//		hashRespuesta(hash);
			paso16(hash);
			busqueda();
		} catch (Exception e) {e.printStackTrace();}
	}

}