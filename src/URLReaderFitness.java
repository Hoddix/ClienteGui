
import java.awt.Image;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
 
public class URLReaderFitness {

	static URL pagina;
	static BufferedReader in;
	static String entradaOriginal, entrada, entrada2, entrada3, entrada4; 

	static String desc="Desc";
	static String rare="\"Rare";
	static String itemType="ItemType";
	
	static String [] typeDesc, typeRare, typeItemType, card, fitness;
	static int idcontract;  
	static Image imagecarta;
	static URL carta;

	static JPanel panelImagenFitness = new JPanel();
	static JLabel label;
	
	static void marcarFila(){
		Gui.tablaBuscarFitness.addMouseListener(AdaptadorRaton.adaptador3);
		Gui.tablaBuscarContratos.removeMouseListener(AdaptadorRaton.adaptador2);
		Gui.tablaBuscarJugadores.removeMouseListener(AdaptadorRaton.adaptador1);
	}
	
	static void seleccionarFitness (){
		int contador = Gui.modeloTablaBuscarFitness.getRowCount();

		if(contador!=0){
			for(int x=0;x<contador;x++){
				Gui.modeloTablaBuscarFitness.removeRow(0);
			}
			contador=0;
		}
		
		typeDesc = new String [5002007];
		typeRare = new String [5002007];
		typeItemType = new String [5002007];
		fitness = new String [5002007];
		card = new String [5002007];
		
		for(int x=5002001;x<5002007;x++){

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
	    		if(idcontract==5002001){
		    		card[x]="img/fitness/fitness-bronze-nonrare.png";
		    		fitness[x] = "+20";
	    		}
	    		else if(idcontract==5002002){
		    		card[x]="img/fitness/fitness-silver-nonrare.png";
		    		fitness[x] = "+40";
		    	}
	    		else if(idcontract==5002003){
		    		card[x]="img/fitness/fitness-gold-nonrare.png";
		    		fitness[x] = "+60";
		    	}
	    		break;
	    	case "1":
	    		if(idcontract==5002004){
		    		card[x]="img/fitness/fitness-bronze-rare.png";
		    		fitness[x] = "Squad +10";
	    		}
		   		else if(idcontract==5002005){
		    		card[x]="img/fitness/fitness-silver-rare.png";
		    		fitness[x] = "Squad +20";
		    	}
		    	else if(idcontract==5002006){
		    		card[x]="img/fitness/fitness-gold-rare.png";
		    		fitness[x] = "Squad +30";
			   	}
	    		break;
	    	}
	    	
	    	Object nuevaFila[]= {typeItemType[x],typeDesc[x],fitness[x]};
	    	Gui.modeloTablaBuscarFitness.addRow(nuevaFila);	    

		}
	}
	
	static void ver(int row){
		Gui.txtnombreJugadorSeleccionado.setText(typeItemType[5002001+row]);
		Gui.txtpos.setText(typeDesc[5002001+row]);
		Gui.txtid.setText(fitness[5002001+row]);
	}
	
	static void panelImagenFitness(int lead){	
		panelImagenFitness.setLayout(null);
		panelImagenFitness.removeAll();
		
		URLReaderPlayers.panelImagenPlayers.setVisible(false);
		URLReaderContracs.panelImagenContract.setVisible(false);
		URLReaderFitness.panelImagenFitness.setVisible(false);
		
		ImageIcon carta=new ImageIcon(card[5002001+lead]);
		URLReaderFitness.panelImagenFitness.setBounds(475, 5, 220, 300);Gui.estatus.add(URLReaderFitness.panelImagenFitness);URLReaderFitness.panelImagenFitness.setVisible(true);
		label = new JLabel(carta);label.setBounds(0, 0, 220, 300);panelImagenFitness.add(label);label.setVisible(true);	
	}
    
}