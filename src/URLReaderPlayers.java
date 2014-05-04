
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Image;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class URLReaderPlayers{

	static int numero;
	
	static URL pagina;
	static BufferedReader in;
	static String entradaOriginal; 
	static int lead, contador, fila;
	static Image imagecarta, imagejugador, imageequipo, imagenation;	
	static URL carta, jugadorface, equipo, nation;
	static JLabel label1, label2, label3, label4, label5, label6, label7, label8, label9, label10, label11, label12, label13;
	static JTextArea textArea, textArea2;
	static JPanel panelImagenPlayers = new JPanel();
	static Font font18 = new Font("Helvetica", Font.BOLD, 18);
	static Font font42 = new Font("Helvetica", Font.BOLD, 32);
	private final static String USER_AGENT = "Mozilla/5.0";
	
	//Carta	
	static String [] rating;
	static String [] rplayerid;
	static String [] nationpicture;
	static String [] picture;
	static String [] clubpicture;
	static String [] attr1;
	static String [] attr2;
	static String [] attr3;
	static String [] attr4;
	static String [] attr5;
	static String [] attr6;
	static String [] name;
	static String [] position;
	static String [] tarjeta;
	
	//Tabla busqueda
	static String [] nationId;
	static String [] clubId;
	static String [] revision;	
	static String [] level;	
	static int [] compra;
	static String [] rare;	

	URLReaderPlayers(){

	}
	
	static void marcarFila(){
		Gui.tablaBuscarJugadores.addMouseListener(AdaptadorRaton.adaptador1);
		Gui.tablaBuscarFitness.removeMouseListener(AdaptadorRaton.adaptador3);
		Gui.tablaBuscarContratos.removeMouseListener(AdaptadorRaton.adaptador2);
	}
	
	static void seleccionarJugadores (String playero){
		int idx = 0;
		rating = new String[100];
		rplayerid = new String[100];
		nationpicture = new String[100];
		nationId = new String[100];
		picture = new String[100];
		clubpicture = new String[100];
		clubId = new String[100];
		attr1 = new String[100];
		attr2 = new String[100];
		attr3 = new String[100];
		attr4 = new String[100];
		attr5 = new String[100];
		attr6 = new String[100];
		name = new String[100];
		position = new String[100];
		tarjeta = new String[100];
		revision = new String[100];
		level = new String[100];
		compra = new int[100];
		rare = new String[100];

		contador=Gui.modeloTablaBuscarJugadores.getRowCount();

		if(contador!=0){
			for(int x=0;x<contador;x++){
				Gui.modeloTablaBuscarJugadores.removeRow(0);
			}
			contador=0;
		}
		
		try {
			//Revision 2
			pagina = new URL("http://www.futhead.com/14/players/search/quick/?term="+playero);
			HttpURLConnection con = (HttpURLConnection)pagina.openConnection();
	        con.setRequestProperty("User-Agent", USER_AGENT);
			
	        InputStream is = con.getInputStream();
	        BufferedReader in = new BufferedReader(new InputStreamReader(is));
	        String entradaOriginal = in.readLine();		
	        //
			ObjectMapper mapper = new ObjectMapper();
			JsonNode rootNode = mapper.readValue(entradaOriginal.getBytes(), JsonNode.class); 
			JsonNode hits = rootNode.get(0);
			JsonNode dataObj, dataObj2, dataObj3, dataObj4, dataObj5, dataObj6, dataObj7, dataObj8, dataObj9, dataObj10, dataObj11, dataObj12, dataObj13, dataObj14, dataObj15, dataObj16, dataObj17;
			
			if (hits != null){
				while ((hits = rootNode.get(idx)) != null){
					//Carta
					dataObj = hits.get("rating");rating[idx] = dataObj.toString().replace("\"", "");			
					dataObj2 = hits.get("player_id");rplayerid[idx] = dataObj2.toString().replace("\"", "");
					dataObj3 = hits.get("nation_image");nationpicture[idx] = dataObj3.toString().replace("\"", "");
					nationId[idx] = dataObj3.toString().substring(dataObj3.toString().indexOf("s/")+2, dataObj3.toString().indexOf(".png"));
					dataObj4 = hits.get("image");picture[idx] = dataObj4.toString().replace("\"", "");
					dataObj5 = hits.get("club_image");clubpicture[idx] = dataObj5.toString().replace("\"", "");
					clubId[idx] = dataObj5.toString().substring(dataObj5.toString().indexOf("s/")+2, dataObj5.toString().indexOf(".png"));
					dataObj6 = hits.get("attr1");attr1[idx] = dataObj6.toString().replace("\"", "");
					dataObj7 = hits.get("attr2");attr2[idx] = dataObj7.toString().replace("\"", "");
					dataObj8 = hits.get("attr3");attr3[idx] = dataObj8.toString().replace("\"", "");
					dataObj9 = hits.get("attr4");attr4[idx] = dataObj9.toString().replace("\"", "");
					dataObj10 = hits.get("attr5");attr5[idx] = dataObj10.toString().replace("\"", "");
					dataObj11 = hits.get("attr6");attr6[idx] = dataObj11.toString().replace("\"", "");
					dataObj12 = hits.get("full_name");name[idx] = dataObj12.toString().replace("\"", "");
					dataObj13 = hits.get("position");position[idx] = dataObj13.toString().replace("\"", "");
					dataObj14 = hits.get("level");
					String lev = dataObj14.toString();
					switch(lev){
					case "0":
						lev = "gold";
						break;
					case "1":
						lev = "silver";
						break;
					case "2":
						lev = "bronze";
						break;
					}
					dataObj15 = hits.get("revision_type");	
					dataObj16 = hits.get("rare");
					rare[idx] = dataObj16.toString().replace("\"", "");

					//dataObj17 = hits.get("pc_auction_average");compra[idx] = dataObj17.asInt();
					//dataObj17 = hits.get("ps3_auction_average");compra[idx] = dataObj17.asInt();
					dataObj17 = hits.get("xbox_auction_average");
					
					
					compra[idx] = dataObj17.asInt();
					tarjeta[idx] = seleccionarCard(dataObj15.toString().replace("\"", ""), dataObj14.toString().replace("\"", ""), dataObj16.toString().replace("\"", ""));
					//Final carta 
					
					level[idx] = lev;
					revision[idx] = dataObj15.toString().replace("\"", "");
					
			    	Object nuevaFila[]= {"",dataObj12.toString().replace("\"", ""),dataObj.toString().replace("\"", ""),lev,dataObj13.toString().replace("\"", "")};
			    	Gui.modeloTablaBuscarJugadores.addRow(nuevaFila);
			    	Gui.tablaBuscarJugadores.setRowHeight(20);
			    	idx++;
				}    				
			}
		}catch (IOException e) {e.printStackTrace();}
	}   
	
	static String seleccionarCard(String card, String level, String rare){
		String tarjeta=null;
		switch(card){
//    	case "Upgrade":
//    		if(level.equalsIgnoreCase("0")==true && rare.equalsIgnoreCase("true")==true){
//	    		tarjeta="http://fh13.fhcdn.com/static/img/cards/card200_player-gold-rare.png";
//    		}
//    		else if(level.equalsIgnoreCase("1")==true && rare.equalsIgnoreCase("true")==true){
//	    		tarjeta="http://fh13.fhcdn.com/static/img/cards/card200_player-silver-rare.png";
//	    	}
//    		else if(level.equalsIgnoreCase("2")==true && rare.equalsIgnoreCase("true")==true){
//	    		tarjeta="http://fh13.fhcdn.com/static/img/cards/card200_player-bronze-rare.png";
//	    	}
//	   		if(level.equalsIgnoreCase("0")==true && rare.equalsIgnoreCase("false")==true){
//	    		tarjeta="http://fh13.fhcdn.com/static/img/cards/card200_player-gold-nonrare.png";
//	   		}
//	   		else if(level.equalsIgnoreCase("1")==true && rare.equalsIgnoreCase("false")==true){
//	    		tarjeta="http://fh13.fhcdn.com/static/img/cards/card200_player-silver-nonrare.png";
//	    	}
//	   		else if(level.equalsIgnoreCase("2")==true && rare.equalsIgnoreCase("false")==true){
//	    		tarjeta="http://fh13.fhcdn.com/static/img/cards/card200_player-bronze-nonrare.png";
//	    	}
//    		break;
//    	case "on":
//    		if(level.equalsIgnoreCase("0")==true && rare.equalsIgnoreCase("true")==true){
//	    		tarjeta="http://fh13.fhcdn.com/static/img/cards/card200_player-gold-rare.png";
//    		}
//	   		else if(level.equalsIgnoreCase("1")==true && rare.equalsIgnoreCase("true")==true){
//	    		tarjeta="http://fh13.fhcdn.com/static/img/cards/card200_player-silver-rare.png";
//	    	}
//	    	else if(level.equalsIgnoreCase("2")==true && rare.equalsIgnoreCase("true")==true){
//	    		tarjeta="http://fh13.fhcdn.com/static/img/cards/card200_player-bronze-rare.png";
//		   	}
//	    	if(level.equalsIgnoreCase("0")==true && rare.equalsIgnoreCase("false")==true){
//	    		tarjeta="http://fh13.fhcdn.com/static/img/cards/card200_player-gold-nonrare.png";
//	    	}
//	    	else if(level.equalsIgnoreCase("1")==true && rare.equalsIgnoreCase("false")==true){
//	    		tarjeta="http://fh13.fhcdn.com/static/img/cards/card200_player-silver-nonrare.png";
//		    }
//	    	else if(level.equalsIgnoreCase("2")==true && rare.equalsIgnoreCase("false")==true){
//	    		tarjeta="http://fh13.fhcdn.com/static/img/cards/card200_player-bronze-nonrare.png";
//		    }
//    		break;
//    	case "MOTM":
//    		tarjeta="http://fh13.fhcdn.com/static/img/cards/card200_player-orange.png";
//    		break;
//    	case "TOTS":
//    		if(level.equalsIgnoreCase("0")==true && rare.equalsIgnoreCase("true")==true){
//	    		tarjeta="http://fh13.fhcdn.com/static/img/cards/card200_player-blue-gold.png";
//	    	}
//	    	else if(level.equalsIgnoreCase("1")==true && rare.equalsIgnoreCase("true")==true){
//	    		tarjeta="http://fh13.fhcdn.com/static/img/cards/card200_player-blue-silver.png";
//		    }
//	    	else if(level.equalsIgnoreCase("2")==true && rare.equalsIgnoreCase("true")==true){
//	    		tarjeta="http://fh13.fhcdn.com/static/img/cards/card200_player-blue-bronze.png";
//		    }
//    		if(level.equalsIgnoreCase("0")==true && rare.equalsIgnoreCase("false")==true){
//    			tarjeta="http://fh13.fhcdn.com/static/img/cards/card200_player-blue-gold.png";
//		    }
//		    else if(level.equalsIgnoreCase("1")==true && rare.equalsIgnoreCase("false")==true){
//		    	tarjeta="http://fh13.fhcdn.com/static/img/cards/card200_player-blue-silver.png";
//			}
//		    else if(level.equalsIgnoreCase("2")==true && rare.equalsIgnoreCase("false")==true){
//		    	tarjeta="http://fh13.fhcdn.com/static/img/cards/card200_player-blue-bronze.png";
//			}
//    		break;
//    	case "Transfer":
//    		if(level.equalsIgnoreCase("0")==true && rare.equalsIgnoreCase("true")==true){
//	    		tarjeta="http://fh13.fhcdn.com/static/img/cards/card200_player-gold-rare.png";
//	    	}
//	    	else if(level.equalsIgnoreCase("1")==true && rare.equalsIgnoreCase("true")==true){
//	    		tarjeta="http://fh13.fhcdn.com/static/img/cards/card200_player-silver-rare.png";
//		    }
//	    	else if(level.equalsIgnoreCase("2")==true && rare.equalsIgnoreCase("true")==true){
//	    		tarjeta="http://fh13.fhcdn.com/static/img/cards/card200_player-bronze-rare.png";
//		    }
//	    	if(level.equalsIgnoreCase("0")==true && rare.equalsIgnoreCase("false")==true){
//	    		tarjeta="http://fh13.fhcdn.com/static/img/cards/card200_player-gold-nonrare.png";
//	    	}
//	    	else if(level.equalsIgnoreCase("1")==true && rare.equalsIgnoreCase("false")==true){
//	    		tarjeta="http://fh13.fhcdn.com/static/img/cards/card200_player-silver-nonrare.png";
//		    }
//	    	else if(level.equalsIgnoreCase("2")==true && rare.equalsIgnoreCase("false")==true){
//	    		tarjeta="http://fh13.fhcdn.com/static/img/cards/card200_player-bronze-nonrare.png";
//		    }
//    		break;
//    	case "TOTYR":
//    		if(level.equalsIgnoreCase("0")==true && rare.equalsIgnoreCase("true")==true){
//    			tarjeta="http://fh13.fhcdn.com/static/img/cards/card200_player-blue-red.png";
//    		}
//    		break;
    	case "":
	    	if(level.equalsIgnoreCase("0")==true && rare.equalsIgnoreCase("true")==true){
	    		tarjeta="http://fh13.fhcdn.com/static/img/14/cards/large-gold-rare.png";
	    	}
	    	else if(level.equalsIgnoreCase("1")==true && rare.equalsIgnoreCase("true")==true){
	    		tarjeta="http://fh13.fhcdn.com/static/img/14/cards/large-silver-rare.png";
		    }
	    	else if(level.equalsIgnoreCase("2")==true && rare.equalsIgnoreCase("true")==true){
	    		tarjeta="http://fh13.fhcdn.com/static/img/14/cards/large-bronze-rare.png";
		    }
	    	if(level.equalsIgnoreCase("0")==true && rare.equalsIgnoreCase("false")==true){
	    		tarjeta="http://fh13.fhcdn.com/static/img/14/cards/large-gold-nonrare.png";
	    	}
	    	else if(level.equalsIgnoreCase("1")==true && rare.equalsIgnoreCase("false")==true){
	    		tarjeta="http://fh13.fhcdn.com/static/img/14/cards/large-silver-nonrare.png";
		    }
	    	else if(level.equalsIgnoreCase("2")==true && rare.equalsIgnoreCase("false")==true){
	    		tarjeta="http://fh13.fhcdn.com/static/img/14/cards/large-bronze-nonrare.png";
		    }
    		break;
    	case "null":
	    	if(level.equalsIgnoreCase("0")==true && rare.equalsIgnoreCase("true")==true){
	    		tarjeta="http://fh13.fhcdn.com/static/img/14/cards/large-gold-rare.png";
	    	}
	    	else if(level.equalsIgnoreCase("1")==true && rare.equalsIgnoreCase("true")==true){
	    		tarjeta="http://fh13.fhcdn.com/static/img/14/cards/large-silver-rare.png";
		    }
	    	else if(level.equalsIgnoreCase("2")==true && rare.equalsIgnoreCase("true")==true){
	    		tarjeta="http://fh13.fhcdn.com/static/img/14/cards/large-bronze-rare.png";
		    }
	    	if(level.equalsIgnoreCase("0")==true && rare.equalsIgnoreCase("false")==true){
	    		tarjeta="http://fh13.fhcdn.com/static/img/14/cards/large-gold-nonrare.png";
	    	}
	    	else if(level.equalsIgnoreCase("1")==true && rare.equalsIgnoreCase("false")==true){
	    		tarjeta="http://fh13.fhcdn.com/static/img/14/cards/large-silver-nonrare.png";
		    }
	    	else if(level.equalsIgnoreCase("2")==true && rare.equalsIgnoreCase("false")==true){
	    		tarjeta="http://fh13.fhcdn.com/static/img/14/cards/large-bronze-nonrare.png";
		    }
    		break;
    	case "IF":
    		if(level.equalsIgnoreCase("0")==true && rare.equalsIgnoreCase("true")==true){
    			tarjeta="http://fh13.fhcdn.com/static/img/14/cards/large-gold-if.png";
    		}
    		else if(level.equalsIgnoreCase("1")==true && rare.equalsIgnoreCase("true")==true){
    			tarjeta="http://fh13.fhcdn.com/static/img/14/cards/large-silver-if.png";
    		}
    		else if(level.equalsIgnoreCase("2")==true && rare.equalsIgnoreCase("true")==true){
    			tarjeta="http://fh13.fhcdn.com/static/img/14/cards/large-bronze-if.png";
    		}
    		else if(level.equalsIgnoreCase("0")==true && rare.equalsIgnoreCase("false")==true){
    			tarjeta="http://fh13.fhcdn.com/static/img/14/cards/large-gold-if.png";
    		}
    		else if(level.equalsIgnoreCase("1")==true && rare.equalsIgnoreCase("false")==true){
    			tarjeta="http://fh13.fhcdn.com/static/img/14/cards/large-silver-if.png";
    		}
    		else if(level.equalsIgnoreCase("2")==true && rare.equalsIgnoreCase("false")==true){
    			tarjeta="http://fh13.fhcdn.com/static/img/14/cards/large-bronze-if.png";
    		}    		
    		break;
//    	case "TOTY":
//    		if(level.equalsIgnoreCase("0")==true && rare.equalsIgnoreCase("true")==true){
//    			tarjeta="http://fh13.fhcdn.com/static/img/cards/card200_player-blue.png";
//    		}
//    		break;
    	}
		return tarjeta;
	}

	static String seleccionarRevision(String revision, String rare){
		String revisionFinal=null;

		switch(revision){
//		case "Upgrade":
//			if (rare.equalsIgnoreCase("true")==true){
//				revisionFinal = "1";
//			}else{
//				revisionFinal = "0";
//			}
//			break;
//		case "on":
//			if (rare.equalsIgnoreCase("true")==true){
//				revisionFinal = "1";
//			}else{
//				revisionFinal = "0";
//			}
//			break;
//		case "MOTM":
//			revisionFinal = "8";
//			break;
//		case "TOTS":
//			revisionFinal = "11";
//			break;
//		case "TOTYR":
//			revisionFinal = "6";
//			break;
		case "IF":
			revisionFinal = "3";
			break;
//		case "TOTY":
//			revisionFinal = "5";
//			break;
//		case "Transfer":
//			if (rare.equalsIgnoreCase("true")==true){
//				revisionFinal = "1";
//			}else{
//				revisionFinal = "0";
//			}
//			break;
		case "null":
			if (rare.equalsIgnoreCase("true")==true){
				revisionFinal = "1";
			}else{
				revisionFinal = "0";
			}
			break;
		case "":
			if (rare.equalsIgnoreCase("true")==true){
				revisionFinal = "1";
			}else{
				revisionFinal = "0";
			}
			break;
		}
		
		return revisionFinal;
	}
	
	static void ver(int row){
		
		Gui.txtnombreJugadorSeleccionado.setText(name[row]);
		Gui.txtcat.setText("player");
		Gui.txtpos.setText(position[row]);
		Gui.txtid.setText(rplayerid[row]);
		Gui.txtrareflag.setText(seleccionarRevision(revision[row],rare[row]));
		Gui.txtlev.setText(level[row]);
		Gui.txtmaxb.setValue(compra[row]);
		Gui.txtprecioVenta.setText("");
		Gui.txtprecioVentaYa.setText("");

	}
	
	static void panelImagenPlayer(int lead){
		panelImagenPlayers.setLayout(null);
		panelImagenPlayers.removeAll();
		URLReaderContracs.panelImagenContract.setVisible(false);
		URLReaderFitness.panelImagenFitness.setVisible(false);
		URLReaderPlayers.panelImagenPlayers.setVisible(false);
		panelImagenPlayers.setBounds(475, 5, 220, 300);
		panelImagenPlayers.setBorder(null);
		
		Gui.estatus.add(panelImagenPlayers);panelImagenPlayers.setVisible(true);
		try {
			carta = new URL(tarjeta[lead]);
			imagecarta = ImageIO.read(carta);
			ImageIcon icono1=new ImageIcon(imagecarta);
			
		    jugadorface = new URL(picture[lead]);
		    imagejugador = ImageIO.read(jugadorface);
		    ImageIcon icono2  = new ImageIcon(imagejugador);
			ImageIcon iconor2 = new ImageIcon(icono2.getImage().getScaledInstance(115,115,Image.SCALE_SMOOTH));

		    equipo = new URL(clubpicture[lead]);
		    imageequipo = ImageIO.read(equipo);
		    ImageIcon icono3 = new ImageIcon(imageequipo);
		    ImageIcon iconor3 = new ImageIcon(icono3.getImage().getScaledInstance(35,35,Image.SCALE_SMOOTH));
		    
		    nation = new URL(nationpicture[lead]);
		    imagenation = ImageIO.read(nation);
		    ImageIcon icono4 = new ImageIcon(imagenation);
			ImageIcon iconor4 = new ImageIcon(icono4.getImage().getScaledInstance(41,25,Image.SCALE_SMOOTH));
		    
		    label1 = new JLabel(iconor4);
		    label2 = new JLabel(iconor2);
		    label3 = new JLabel(iconor3);
		    label4 = new JLabel(icono1);	
		    
		    label5 = new JLabel(attr1[lead]+" PAC");
		    label6 = new JLabel(attr2[lead]+" SHO");
		    label7 = new JLabel(attr3[lead]+" PAS");
		    label8 = new JLabel(attr4[lead]+" DRI");
		    label9 = new JLabel(attr5[lead]+" DEF");
		    label10 = new JLabel(attr6[lead]+" HEA");
		    label12 = new JLabel(position[lead]);
		    label13 = new JLabel(rating[lead]);
		    textArea = new JTextArea(name[lead]);
		    textArea2 = new JTextArea("");
		    
		}catch (Exception e){e.printStackTrace();}
		
	    label5.setBounds(30,185,100,40);panelImagenPlayers.add(label5);label5.setFont(font18);label5.setVisible(true);label5.setForeground(Color.white);//PAC
	    label6.setBounds(30,213,100,40);panelImagenPlayers.add(label6);label6.setFont(font18);label6.setVisible(true);label6.setForeground(Color.white);//SHO
	    label7.setBounds(30,240,100,40);panelImagenPlayers.add(label7);label7.setFont(font18);label7.setVisible(true);label7.setForeground(Color.white);//PAS
	    label8.setBounds(125,185,80,40);panelImagenPlayers.add(label8);label8.setFont(font18);label8.setVisible(true);label8.setForeground(Color.white);//DRI
	    label9.setBounds(125,213,80,40);panelImagenPlayers.add(label9);label9.setFont(font18);label9.setVisible(true);label9.setForeground(Color.white);//DEF
	    label10.setBounds(125,240,80,40);panelImagenPlayers.add(label10);label10.setFont(font18);label10.setVisible(true);label10.setForeground(Color.white);//HEA
	    
	    textArea.setBounds(33,23,175,100);textArea.setBorder(null);textArea.setFont(font18);textArea.setForeground(Color.white);//Name
	    textArea.setOpaque(false);
	    textArea.setLineWrap(true);textArea.setWrapStyleWord(true);//textArea.setEditable(false);
	    textArea.setFocusable(false);
	    textArea.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
	    textArea.setBorder(BorderFactory.createEmptyBorder());
	    panelImagenPlayers.add(textArea);textArea.setVisible(true);
	    
	    label12.setBounds(75,45,120,37);panelImagenPlayers.add(label12);label12.setFont(font18);label12.setVisible(true);label12.setForeground(Color.white);//Posicion
	    label13.setBounds(27,29,40,60);panelImagenPlayers.add(label13);label13.setFont(font42);label13.setVisible(true);label13.setForeground(Color.white);//Rating
		
	    label1.setBounds(20,125,60,37);panelImagenPlayers.add(label1);label1.setVisible(true);
	    label2.setBounds(56,17,175,175);panelImagenPlayers.add(label2);label2.setVisible(true);
	    label3.setBounds(25,80,50,50);panelImagenPlayers.add(label3);label3.setVisible(true);
	    label4.setBounds(5,0, 210, 300);panelImagenPlayers.add(label4);label4.setVisible(true);	
	    
	    textArea2.setBounds(5,5,210,290);textArea2.setBorder(null);textArea2.setFont(font18);textArea2.setForeground(Color.white);//Name
	    textArea2.setOpaque(false);
	    textArea2.setLineWrap(true);textArea2.setWrapStyleWord(true);//textArea.setEditable(false);
	    textArea2.setFocusable(false);
	    textArea2.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
	    textArea2.setBorder(BorderFactory.createEmptyBorder());
	    
	    panelImagenPlayers.add(textArea2);textArea2.setVisible(true);
	}

	
	static void getLead(int row){
		fila=row;
	}
	
	static int setLead(){
		return fila;
	}
	
}