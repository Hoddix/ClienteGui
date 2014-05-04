import java.io.File;
import java.io.FileWriter;
import java.util.List;

import org.jdom2.Attribute;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.input.SAXBuilder;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;

public class XmlConfig {
	
	static SAXBuilder builder=new SAXBuilder();
	static File xmlFile = new File("config.xml");
	
	static void guardarXML(){

		try{	
			//Abrir playersdb.xml
			Document doc = (Document) builder.build(xmlFile);
			Element rootNode = doc.getRootElement();
			
			List<?> list = rootNode.getChildren("config");	

			for(int x=0;x<list.size();x++){
				list.remove(x);						   
			}
			
			Element nuevoplayer = new Element("config");
			nuevoplayer.setAttribute(new Attribute ("usuario" ,Gui.txtcuser.getText()));
			rootNode.addContent(nuevoplayer);
			Element usuario = new Element("usuario").setText(Gui.txtcuser.getText());
			nuevoplayer.addContent(usuario);
			Element contraseña = new Element("contraseña").setText(Gui.txtcpass.getText());
			nuevoplayer.addContent(contraseña);
			Element secreta = new Element("secreta").setText(Gui.txtcrespuesta.getText());
			nuevoplayer.addContent(secreta);
			Element rpb = new Element("rpb").setText(Gui.txtcpbusqueda.getText());
			nuevoplayer.addContent(rpb);
			Element tdb = new Element("tdb").setText(Gui.txttiempo.getText());
			nuevoplayer.addContent(tdb);
			Element pdc = new Element("pdc").setText(Gui.txtporcentajecompra.getText());
			nuevoplayer.addContent(pdc);
			Element pdv = new Element("pdv").setText(Gui.txtporcentajeventa.getText());
			nuevoplayer.addContent(pdv);
			Element mdm = new Element("mdm").setText(Gui.txtmonedasminimas.getText());
			nuevoplayer.addContent(mdm);
			
			XMLOutputter xmlOutput = new XMLOutputter();
			xmlOutput.setFormat(Format.getPrettyFormat());
			xmlOutput.output(doc, new FileWriter("config.xml"));
			
		}catch(Exception b){}

	}
	
	static void cargarXML(){
		try {
			Document document =(Document) builder.build(xmlFile);
			Element rootNode = document.getRootElement();
			List<?> list=rootNode.getChildren("config");

			for (int i = 0; i < list.size(); i++) {
			Element node = (Element) list.get(i);
			Gui.txtcuser.setText(node.getChildText("usuario"));
			Gui.txtcpass.setText(node.getChildText("contraseña"));
			Gui.txtcrespuesta.setText(node.getChildText("secreta"));
			Gui.txtcpbusqueda.setText(node.getChildText("rpb"));
			Gui.txttiempo.setText(node.getChildText("tdb"));
			Gui.txtporcentajecompra.setText(node.getChildText("pdc"));
			Gui.txtporcentajeventa.setText(node.getChildText("pdv"));
			Gui.txtmonedasminimas.setText(node.getChildText("mdm"));
		
			}
		} catch (Exception b) {}
	}
}

