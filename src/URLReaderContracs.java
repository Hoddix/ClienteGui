
import java.awt.Image;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
 
public class URLReaderContracs {

	static URL pagina;
	static BufferedReader in;
	static String entradaOriginal, entrada, entrada2, entrada3, entrada4; 

	static String desc="Desc";
	static String rare="\"Rare";
	static String itemType="ItemType";
	
	static String [] typeDesc, typeRare, typeItemType, card, contratos, lev, resourceId;
	static int idcontract;  
	static Image imagecarta;
	static URL carta;

	static JPanel panelImagenContract = new JPanel();
	static JLabel label;
	
	
	static void marcarFila(){
		Gui.tablaBuscarContratos.addMouseListener(AdaptadorRaton.adaptador2);
		Gui.tablaBuscarJugadores.removeMouseListener(AdaptadorRaton.adaptador1);
		Gui.tablaBuscarFitness.removeMouseListener(AdaptadorRaton.adaptador3);
	}
	
	static void seleccionarContracts (){
		int contador = Gui.modeloTablaBuscarContratos.getRowCount();

		if(contador!=0){
			for(int x=0;x<contador;x++){
				Gui.modeloTablaBuscarContratos.removeRow(0);
			}
			contador=0;
		}
		//lev, cat, minb, maxb, micr, macr
		typeDesc = new String [5001013];
		typeRare = new String [5001013];
		typeItemType = new String [5001013];
		contratos = new String [5001013];
		card = new String [5001013];
		lev = new String [5001013];
		resourceId = new String [5001013];
		
		for(int x=5001001;x<5001013;x++){
			
		try {
			pagina = new URL("http://cdn.content.easports.com/fifa/fltOnlineAssets/C74DDF38-0B11-49b0-B199-2E2A11D1CC13/2014/fut/items/web/"+x+".json");
			in = new BufferedReader (new InputStreamReader(pagina.openStream()));
			entradaOriginal = in.readLine();
			entrada=entradaOriginal;
			idcontract=x;

		}catch (IOException e) {e.printStackTrace();}

	    	entrada = entrada.substring(entrada.indexOf(desc)+desc.length(),entrada.length());
	    	entrada2 = entrada.substring(entrada.indexOf(rare)+rare.length(),entrada.length());
	    	entrada3 = entrada.substring(entrada.indexOf(itemType)+itemType.length(),entrada.length());
	    	
	    	typeDesc[x]=entrada.substring(entrada.indexOf(":")+2 , entrada.indexOf(",")-1);	    	
	    	typeRare[x]=entrada2.substring(entrada2.indexOf(":")+2 , entrada2.indexOf(",")-1);
	    	typeItemType[x]=entrada3.substring(entrada3.indexOf(":")+2 , entrada3.indexOf("}")-1);

	    	switch(typeRare[x]){
	    	case "0":
	    		if(idcontract==5001001){
		    		card[x]="img/contracts/player-bronze-nonrare.png";
		    		contratos[x] = "8-2-1";
		    		lev[x] = "bronze";
		    		resourceId[x] = "1615613737";
	    		}
	    		else if(idcontract==5001002){
		    		card[x]="img/contracts/player-silver-nonrare.png";
		    		contratos[x] = "10-10-8";
		    		lev[x] = "silver";
		    		resourceId[x] = "1615613738";
		    	}
	    		else if(idcontract==5001003){
		    		card[x]="img/contracts/player-gold-nonrare.png";
		    		contratos[x] = "15-11-13";
		    		lev[x] = "gold";
		    		resourceId[x] = "1615613739";
		    	}
		   		if(idcontract==5001007){
		    		card[x]="img/contracts/staff-bronze-nonrare.png";
		    		contratos[x] = "8-2-1";
		    		lev[x] = "bronze";
		    		resourceId[x] = "1615613743";
		   		}
		   		else if(idcontract==5001008){
		    		card[x]="img/contracts/staff-silver-nonrare.png";
		    		contratos[x] = "8-10-8";
		    		lev[x] = "silver";
		    		resourceId[x] = "1615613744";
		    	}
		   		else if(idcontract==5001009){
		    		card[x]="img/contracts/staff-gold-nonrare.png";
		    		contratos[x] = "11-11-13";
		    		lev[x] = "gold";
		    		resourceId[x] = "1615613745";
		    	}
	    		break;
	    	case "1":
	    		if(idcontract==5001004){
		    		card[x]="img/contracts/player-bronze-rare.png";
		    		contratos[x] = "15-6-3";
		    		lev[x] = "bronze";
		    		resourceId[x] = "1615613740";
	    		}
		   		else if(idcontract==5001005){
		    		card[x]="img/contracts/player-silver-rare.png";
		    		contratos[x] = "20-24-18";
		    		lev[x] = "silver";
		    		resourceId[x] = "1615613741";
		    	}
		    	else if(idcontract==5001006){
		    		card[x]="img/contracts/player-gold-rare.png";
		    		contratos[x] = "28-24-28";
		    		lev[x] = "gold";
		    		resourceId[x] = "1615613742";
			   	}
		    	if(idcontract==5001010){
		    		card[x]="img/contracts/staff-bronze-rare.png";
		    		contratos[x] = "15-6-3";
		    		lev[x] = "bronze";
		    		resourceId[x] = "1615613746";
		    	}
		    	else if(idcontract==5001011){
		    		card[x]="img/contracts/staff-silver-rare.png";
		    		contratos[x] = "18-24-18";
		    		lev[x] = "silver";
		    		resourceId[x] = "1615613747";
			    }
		    	else if(idcontract==5001012){
		    		card[x]="img/contracts/staff-gold-rare.png";
		    		contratos[x] = "24-24-28";
		    		lev[x] = "gold";
		    		resourceId[x] = "1615613748";
			    }
	    		break;
	    	}
	    	
	    	Object nuevaFila[]= {typeItemType[x],typeDesc[x],contratos[x]};
	    	Gui.modeloTablaBuscarContratos.addRow(nuevaFila);	    
		}
	}
	
	static void ver(int row){
		Gui.txtnombreJugadorSeleccionado.setText(typeItemType[5001001+row]);
		Gui.txtcat.setText("contract");
		Gui.txtrareflag.setText(typeDesc[5001001+row]);
		Gui.txtid.setText(resourceId[5001001+row]);
		Gui.txtlev.setText(lev[5001001+row]);
	}
	
	static void panelImagenContract(int lead){	
		panelImagenContract.setLayout(null);
		panelImagenContract.removeAll();
		
		URLReaderPlayers.panelImagenPlayers.setVisible(false);
		URLReaderContracs.panelImagenContract.setVisible(false);
		URLReaderFitness.panelImagenFitness.setVisible(false);
		
		ImageIcon carta=new ImageIcon(card[5001001+lead]);
		URLReaderContracs.panelImagenContract.setBounds(475, 5, 220, 300);Gui.estatus.add(URLReaderContracs.panelImagenContract);URLReaderContracs.panelImagenContract.setVisible(true);
		label = new JLabel(carta);label.setBounds(0, 0, 220, 300);panelImagenContract.add(label);label.setVisible(true);	
	}
    
}