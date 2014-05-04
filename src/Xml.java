import java.io.File;
import java.io.FileWriter;
import java.util.List;

import org.jdom2.Attribute;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.input.SAXBuilder;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;

public class Xml {
	
	static SAXBuilder builder=new SAXBuilder();
	static File xmlFile = new File("playersdb.xml");
	
	static void guardarXML(){

		try{	
			//Abrir playersdb.xml
			Document doc = (Document) builder.build(xmlFile);
			Element rootNode = doc.getRootElement();
			
			//tipo 0","categoria 1","Nombre 2","id 3","card 4","P.Compra 5","P.Venta 6","P.Venta Ya 7","Level 8","Posicion 9"
			Element nuevoplayer = new Element("player");
			nuevoplayer.setAttribute(new Attribute ("id" ,(String) Gui.modeloTablaLista.getValueAt(Gui.modeloTablaLista.getRowCount()-1, 3)));
			rootNode.addContent(nuevoplayer);
			Element tipo = new Element("tipo").setText((String) Gui.modeloTablaLista.getValueAt(Gui.modeloTablaLista.getRowCount()-1, 0));
			nuevoplayer.addContent(tipo);
			Element categoria = new Element("categoria").setText((String) Gui.modeloTablaLista.getValueAt(Gui.modeloTablaLista.getRowCount()-1, 1));
			nuevoplayer.addContent(categoria);
			Element playername = new Element("name").setText((String) Gui.modeloTablaLista.getValueAt(Gui.modeloTablaLista.getRowCount()-1, 2));
			nuevoplayer.addContent(playername);
			Element playerid = new Element("id").setText((String) Gui.modeloTablaLista.getValueAt(Gui.modeloTablaLista.getRowCount()-1, 3));
			nuevoplayer.addContent(playerid);
			Element playercard = new Element("card").setText((String) Gui.modeloTablaLista.getValueAt(Gui.modeloTablaLista.getRowCount()-1, 4));
			nuevoplayer.addContent(playercard);
			Element playerprecio = new Element("preciocompra").setText((String) Gui.modeloTablaLista.getValueAt(Gui.modeloTablaLista.getRowCount()-1, 5));
			nuevoplayer.addContent(playerprecio);
			Element playerventa = new Element("precioventa").setText((String) Gui.modeloTablaLista.getValueAt(Gui.modeloTablaLista.getRowCount()-1, 6));
			nuevoplayer.addContent(playerventa);
			Element precioventaya = new Element("precioventaya").setText((String) Gui.modeloTablaLista.getValueAt(Gui.modeloTablaLista.getRowCount()-1, 7));
			nuevoplayer.addContent(precioventaya);
			Element playerlevel = new Element("level").setText((String) Gui.modeloTablaLista.getValueAt(Gui.modeloTablaLista.getRowCount()-1, 8));
			nuevoplayer.addContent(playerlevel);
			Element playerposicion = new Element("posicion").setText((String) Gui.modeloTablaLista.getValueAt(Gui.modeloTablaLista.getRowCount()-1, 9));
			nuevoplayer.addContent(playerposicion);
			List<?> list = rootNode.getChildren("player");		
			int[]indiceborrado;		
			indiceborrado=new int[list.size()];
	
			for (int i = 0; i < list.size(); i++) {
				Element node = (Element) list.get(i);
				if(node.getAttributeValue("id").equals("")==true){
					indiceborrado[i]=i;
				}
			}
			for(int x=0;x<list.size();x++){
				if(indiceborrado[x]!=0){
					list.remove(x);	
				}					   
			}
			
			XMLOutputter xmlOutput = new XMLOutputter();
			xmlOutput.setFormat(Format.getPrettyFormat());
			xmlOutput.output(doc, new FileWriter("playersdb.xml"));
			
		}catch(Exception b){}

	}
	
	static void cargarXML(){
		try {
			Document document =(Document) builder.build(xmlFile);
			Element rootNode = document.getRootElement();
			List<?> list=rootNode.getChildren("player");

			for (int i = 0; i < list.size(); i++) {
			Element node = (Element) list.get(i);
			Object nuevaFila[]= {node.getChildText("tipo"), node.getChildText("categoria"), node.getChildText("name"),node.getChildText("id"),node.getChildText("card"),node.getChildText("preciocompra"),node.getChildText("precioventa"),node.getChildText("precioventaya"),node.getChildText("level"),node.getChildText("posicion")};
			Gui.modeloTablaLista.addRow(nuevaFila);
			
			}
		} catch (Exception b) {}
	}
}
