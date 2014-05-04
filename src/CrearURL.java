 
public class CrearURL {

	/* Player
	 * &micr=150 //Precio minimo puja
	 * &macr=350 //Precio maximo puja
	 * &minb=200 //Precio minimo compralo ya
	 * &maxb=550 //Precio maximo compralo ya
	 * &pos=CF //Posicion
	 * &num=16 //Numero maximo de resultado por busqueda
	 * &nat=52 //Nacionalidad
	 * &team=241 //Equipo
	 * &type=player //Tipo de carta
	 * &leag=53 //Liga
	 * &start=0 //Comienza desde 0
	 */
	static String playerURL(String assedID, String bind){
		
		String num = Gui.txtcpbusqueda.getText();
		String url = "/ut/game/fifa14/transfermarket?maxb=" + bind + "&start=0&type=player&maskedDefId=" + assedID + "&num=" + num;
		
		return url;
    }
	
	/*		String url = "/ut/game/fifa14/transfermarket?start=0&num=" + num + "&type=player";	
	 * 		if(nat != "" && nat != "todos"){url += "&nat=" + nat;}
	 *		if(lev != "" && lev != "todos"){url += "&lev=" + lev;}
	 * 		if(leag != "" && leag != "todos"){url += "&leag=" + leag;}
	 * 	if(team != "" && team != "todos"){url += "&team=" + team;}
	 * 	if(playStyle != "" && playStyle != "todos"){url += "&playStyle=" + playStyle;}
	 * 	if (pos != "" && pos != "todos"){
	 * 		if (pos.equalsIgnoreCase("defense") || pos.equalsIgnoreCase("midfield") || pos.equalsIgnoreCase("attacker")){
	 * 			url += "&zone=" + pos;
	 * 		}else{
	 * 			url += "&pos=" + pos;
	 * 		}
	 * 	}
	 * 	
	 * 	if(micr != "" && micr != "todos"){url += "&micr=" + micr;}
	 * 	if(macr != "" && macr != "todos"){url += "&macr=" + macr;}
	 * 	if(minb != "" && minb != "todos"){url += "&minb=" + minb;}
	 * 	if(maxb != "" && maxb != "todos"){url += "&maxb=" + maxb;}
	*/
	
	
	/*Development
	 * https://utas.fut.ea.com/ut/game/fifa14/transfermarket?
	 * lev=gold
	 * &type=development
	 * &cat=contract
	 */
	static String developmentURL(int start, String lev, String cat, String minb, String maxb, String micr, String macr){

		String num = Gui.txtcpbusqueda.getText();
		String url = "/ut/game/fifa14/transfermarket?start="+start+"&num=" + num + "&type=development";
		
		if(cat != "" && cat != "todos"){url += "&cat=" + cat;}
		if(lev != "" && lev != "todos"){url += "&lev=" + lev;}
		
		if(micr != "" && micr != "todos"){url += "&micr=" + micr;}
		if(macr != "" && macr != "todos"){url += "&macr=" + macr;}
		if(minb != "" && minb != "todos"){url += "&minb=" + minb;}
		if(maxb != "" && maxb != "todos"){url += "&maxb=" + maxb;}

		return url;
		
	}
	
	/* Training
	 * &cat=playerTraining
	 * &type=training
	 * &lev=gold
	 * &pos=LWB%2DLB
	 * &playStyle=271
	 */
	
//	static String trainingURL(String pos, String playStyle, String lev, String cat, String minb, String maxb, String micr, String macr){
//		String num = "100";
//		String url = "/ut/game/fifa14/transfermarket?start=0&num=" + num + "&type=training";
//		
//		if(cat != "" && cat != "todos"){url += "&cat=" + cat;}
//		if(lev != "" && lev != "todos"){url += "&lev=" + lev;}
//		if(pos != "" && pos != "todos"){url += "&pos=" + pos;}
//		if(playStyle != "" && playStyle != "todos"){url += "&playStyle=" + playStyle;}
//		
//		if(micr != "" && micr != "todos"){url += "&micr=" + micr;}
//		if(macr != "" && macr != "todos"){url += "&macr=" + macr;}
//		if(minb != "" && minb != "todos"){url += "&minb=" + minb;}
//		if(maxb != "" && maxb != "todos"){url += "&maxb=" + maxb;}
//		
//		System.out.println("Direccion buscar entrenamiento jugadores: " + url);
//		
//		return url;
//		
//	}
	
	/* Staff
	 * nat=52
	 * &type=staff
	 * &leag=53
	 * &cat=manager
	 * &lev=gold
	 */
//	static String staffURL(String leag, String lev, String cat, String minb, String maxb, String micr, String macr){
//		String num = "100";
//		String url = "/ut/game/fifa14/transfermarket?start=0&num=" + num + "&type=staff";
//		
//		if(cat != "" && cat != "todos"){url += "&cat=" + cat;}
//		if(lev != "" && lev != "todos"){url += "&lev=" + lev;}
//		if(leag != "" && leag != "todos"){url += "&leag=" + leag;}
//		
//		if(micr != "" && micr != "todos"){url += "&micr=" + micr;}
//		if(macr != "" && macr != "todos"){url += "&macr=" + macr;}
//		if(minb != "" && minb != "todos"){url += "&minb=" + minb;}
//		if(maxb != "" && maxb != "todos"){url += "&maxb=" + maxb;}
//		
//		System.out.println("Direccion buscar staff: " + url);
//		
//		return url;
//		
//	}
	
}