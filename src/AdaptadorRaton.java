import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


public class AdaptadorRaton {

	static void iniciarFilas(){
		
	switch(Gui.seleccionado){
	case "Players":
		URLReaderPlayers.marcarFila();
		break;
	case "Contracts":
		URLReaderContracs.marcarFila();
		break;
	case "Fitness":
		URLReaderFitness.marcarFila();
		break;
	}
	
	}
	
	
	static MouseAdapter adaptador1 = new MouseAdapter() { public void mouseClicked(MouseEvent evt) {
		int row = Gui.tablaBuscarJugadores.rowAtPoint(evt.getPoint());
		if (row >= 0) {
			switch(Gui.seleccionado){
			case "Players":
				URLReaderPlayers.panelImagenPlayer(row);
				URLReaderPlayers.ver(row);
				break;
			}
		}	
	}};

	static MouseAdapter adaptador2 = new MouseAdapter() { public void mouseClicked(MouseEvent evt) {
		int row = Gui.tablaBuscarContratos.rowAtPoint(evt.getPoint());
		if (row >= 0) {
			switch(Gui.seleccionado){
			case "Contracts":
				URLReaderContracs.panelImagenContract(row);
				URLReaderContracs.ver(row);
				break;
			}
		}	
	}};
	
	static MouseAdapter adaptador3 = new MouseAdapter() { public void mouseClicked(MouseEvent evt) {
		int row = Gui.tablaBuscarFitness.rowAtPoint(evt.getPoint());
		if (row >= 0) {
			switch(Gui.seleccionado){
			case "Fitness":
				URLReaderFitness.panelImagenFitness(row);
				URLReaderFitness.ver(row);
				break;
			}
		}	
	}};

}
