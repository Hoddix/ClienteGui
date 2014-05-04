import java.awt.Color;
import java.awt.Component;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.text.DecimalFormat;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;

import org.jvnet.substance.SubstanceLookAndFeel;

@SuppressWarnings("serial")
public class Gui extends JFrame implements ActionListener{
	
		static String url;
		
	//Imagenes
		static Image monedas;
	
	//Barra
		JMenuBar menuBarra;
		JMenu menuFut, menuPrefer,menuProceso;
		JMenuItem menuFutStatus, salir, menuConfig,menuEstadoProceso;
	
	//Colores
		Color verdeOscuro = new Color(0,166,81);
		Color amarilloOscuro = new Color(255,246,0);
		Color rojo = new Color(255,0,0);
	
		Color oro = new Color(231,220,6);
		Color plata = new Color(185,185,185);
		Color bronce = new Color(167,99,44);
	//Panel STATUS//
		//Panel
			static JPanel estatus;
		
		//Varios
			static JLabel estado,conectado,noConectado,log,imagen_monedas,saldo;

		static JPanel panelMonedas;
			
		//Login
			static JLabel user, pass, respuesta;
			static JTextField txtuser,txtpass,txtrespuesta;
			
		//Busqueda
			static JLabel jugadores, nombreJugador;
			static JTextField txtnombreJugador;
			
		//JComboBox
			@SuppressWarnings("rawtypes")
			static JComboBox comboSeleccion;
			
		//Seleccion
			static JLabel tituloJugador, tituloPrecio, tituloTablaDatos,nombreJugadorSeleccionado,pos,maxb,precioVenta,precioVentaYa;
			static JTextField txtnombreJugadorSeleccionado,txttype,txtpos,txtcat,txtrareflag,txtteam,txtnat,txtid,txtlev,txtprecioVenta,txtprecioVentaYa;
		
		//JSpinner
			static JSpinner txtmaxb;
		
		//Tabla buscar
			static JTable tablaBuscarJugadores, tablaBuscarContratos, tablaBuscarFitness;
			static DefaultTableModel modeloTablaBuscarJugadores, modeloTablaBuscarContratos, modeloTablaBuscarFitness;
			static JScrollPane tablaBuscarScrollJugadores, tablaBuscarScrollContratos, tablaBuscarScrollFitness;
			
		//Tabla lista
			static JTable tablaLista;
			static DefaultTableModel modeloTablaLista;
			static JScrollPane tablaListaScroll;
		
		//Tabla log
			static JTable tablaStatus;
			static DefaultTableModel modeloTablaStatus;
			static JScrollPane tablaStatusScroll;
			
		//Botones
			JButton stop, start, buscar, añadir;
			
			static String seleccionado = "Players";
			
		//Panel de configuracion
			//Panel
			static JPanel config;
			
			//Config
			static JLabel tiempo, cpbusqueda, porcentajecompra, porcentajeventa, proteccionmonedas, monedasminimas;
			static JTextField txttiempo, txtcpbusqueda, txtporcentajecompra, txtporcentajeventa, txtmonedasminimas;
			static JCheckBox cbproteccionmonedas;
			
			//Login
			static JLabel cuser, cpass, crespuesta;
			static JTextField txtcuser,txtcpass,txtcrespuesta;
			
			static JButton guardar;
		
		//Panel de proceso
			//Panel
			static JPanel proceso;
			
	@SuppressWarnings("rawtypes")
	Gui(){
		//SKin de la ventana
//		try {
//			SubstanceLookAndFeel.setSkin("org.jvnet.substance.skin.RavenGraphiteGlassSkin");
//			//UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
//		}
//		catch (Exception e) {}
		
		//Menu
		menuBarra=new JMenuBar();
		setJMenuBar(menuBarra);
		menuBarra.setVisible(false);
		menuFut=new JMenu("AU14");
		menuBarra.add(menuFut);
		menuFutStatus=new JMenuItem("AU14 Status");
		menuFut.add(menuFutStatus);
		menuFutStatus.addActionListener(this);
		salir=new JMenuItem("Exit");
		menuFut.add(salir);
		salir.addActionListener(this);
		menuProceso = new JMenu("Proceso");
		menuBarra.add(menuProceso);
		menuEstadoProceso = new JMenuItem("Estado del proceso");
		menuProceso.add(menuEstadoProceso);
		menuEstadoProceso.addActionListener(this);
		menuPrefer=new JMenu("Preferencias");
		menuBarra.add(menuPrefer);
		menuConfig=new JMenuItem("Configuracion");
		menuPrefer.add(menuConfig);
		menuConfig.addActionListener(this);

		//Imagenes
		try{
		File coins_icon = new File("coins_icon.png");
		monedas = ImageIO.read(coins_icon);
	    ImageIcon coins_icon_=new ImageIcon(monedas);
	    ImageIcon coins_icon_d = new ImageIcon(coins_icon_.getImage().getScaledInstance(30,30,Image.SCALE_SMOOTH));
	    imagen_monedas = new JLabel(coins_icon_d);
		}catch(Exception e){}
		
		//Panel
		estatus = new JPanel();
		
		//Varios
		estado = new JLabel("Estatus:");
		conectado = new JLabel("Conectado");
		noConectado = new JLabel("No conectado");
		log = new JLabel("Log: ");
		panelMonedas = new JPanel();
		saldo = new JLabel("");
		
		//Login
//		user = new JLabel("Usuario:");
//		pass = new JLabel("Contraseña:");
//		respuesta = new JLabel("Hash respuesta:");
//		txtuser = new JTextField(); 
//		txtpass = new JTextField(); 
//		txtrespuesta = new JTextField(); 
		
		//Busqueda
		jugadores = new JLabel("Buscar:");
		nombreJugador = new JLabel("Nombre jugador:");
		txtnombreJugador = new JTextField();

		//JComboBox
		comboSeleccion = new JComboBox();
		
		//Seleccion
		nombreJugadorSeleccionado = new JLabel("Nombre");
		pos = new JLabel("Posicion");
		tituloJugador = new JLabel("Jugador seleccionado:");
		tituloPrecio = new JLabel("Establecer precios:");
		tituloTablaDatos = new JLabel("Lista de jugadores:");
		maxb = new JLabel("Precio compralo ya");
		precioVenta = new JLabel("Precio venta");
		precioVentaYa = new JLabel("Precio venta ya");
		
		txtnombreJugadorSeleccionado = new JTextField();
		txttype = new JTextField();
		txtpos = new JTextField();
		txtcat = new JTextField();
		txtrareflag = new JTextField();
		txtteam = new JTextField();
		txtnat = new JTextField();
		txtid = new JTextField();
		txtlev = new JTextField();
		txtmaxb = new JSpinner(new SpinnerNumberModel(0, 0, 400000000, 100));
		txtprecioVenta = new JTextField();
		txtprecioVentaYa = new JTextField();
		
		//Botones
		stop = new JButton("Stop");
		start = new JButton("Start");
		buscar = new JButton("Buscar");
		añadir = new JButton("Añadir");
		guardar = new JButton("Guardar");
		
		//Tabla buscar jugadores
		String calumnasJugadores[]={"Revision","Nombre","Rating","Level","Posicion"}; 
		modeloTablaBuscarJugadores = new DefaultTableModel(calumnasJugadores,0);  
		tablaBuscarJugadores = new JTable(modeloTablaBuscarJugadores);
		tablaBuscarScrollJugadores = new JScrollPane(tablaBuscarJugadores);
		TableColumn calumnasJugadores1 = tablaBuscarJugadores.getColumn("Rating");
		TableColumn calumnasJugadores2 = tablaBuscarJugadores.getColumn("Posicion");
		TableColumn calumnasJugadores3 = tablaBuscarJugadores.getColumn("Level");
		calumnasJugadores1.setMaxWidth(40);
		calumnasJugadores2.setMaxWidth(55);
		calumnasJugadores3.setMaxWidth(55);
		
		//Tabla buscar contratos
		String calumnasContratos[]={"Nombre","Level","Contratos"}; 
		modeloTablaBuscarContratos = new DefaultTableModel(calumnasContratos,0);  
		tablaBuscarContratos = new JTable(modeloTablaBuscarContratos);
		tablaBuscarScrollContratos = new JScrollPane(tablaBuscarContratos);
		
		//Tabla buscar fitness
		String calumnasFitness[]={"Nombre","Level","Fitness"}; 
		modeloTablaBuscarFitness = new DefaultTableModel(calumnasFitness,0);  
		tablaBuscarFitness = new JTable(modeloTablaBuscarFitness);
		tablaBuscarScrollFitness = new JScrollPane(tablaBuscarFitness);
		
		//Tabla lista
		String calumnas[]={"tipo","categoria","Nombre","id","card","P.Compra","P.Venta", "P.Venta Ya","Level","Posicion"}; 
		modeloTablaLista = new DefaultTableModel(calumnas,0){public boolean isCellEditable(int row, int column){
				return false;//This causes all cells to be not editable
		}};  
		tablaLista = new JTable(modeloTablaLista); 			
		tablaListaScroll=new JScrollPane(tablaLista); 
		
		//Tabla log
		String col[]={"Log"}; 
		modeloTablaStatus=new DefaultTableModel(col,0);   
		tablaStatus=new JTable(modeloTablaStatus){
			
			public Component prepareRenderer(TableCellRenderer renderer,int row,int column){
					Component c = super.prepareRenderer(renderer,row,column);
					String type=(String) getModel().getValueAt(row,0);
					if(type.indexOf("Buscando") != -1){
						c.setForeground(Color.BLUE);
						return c;
					}else if(type.indexOf("encontrados") != -1){
						c.setForeground(Color.black);
						return c;
					}else if(type.indexOf("Contratos") != -1){
						c.setForeground(Color.BLACK);
						return c;
					}else if(type.indexOf("Moviendo") != -1){
						c.setForeground(Color.BLACK);
						return c;
					}else if(type.equalsIgnoreCase("No comprado, has llegado tarde") == true){
						c.setForeground(Color.RED);
						return c;
					}else if(type.equalsIgnoreCase("Comprado") == true){
						c.setForeground(Color.GREEN);
						return c;
					}else if(type.indexOf("compras") != -1){
						c.setForeground(Color.BLACK);
						return c;
					}else if(type.indexOf("Monedas") != -1){
					    c.setForeground(amarilloOscuro);
						return c;
					}else if(type.indexOf("Colocado") != -1){
					    c.setForeground(Color.ORANGE);
						return c;
					}else if(type.indexOf("Eliminando") != -1){
    				    c.setForeground(Color.CYAN);
						return c;
					}else if(type.indexOf("tradepile") != -1){
						c.setForeground(Color.BLACK);
						return c;
					}else if(type.indexOf("Conexion") != -1){
						c.setForeground(Color.GREEN);
						return c;
					}else{
						c.setForeground(Color.BLACK);
						return c;
					}
				}
			}; 
		tablaStatus.setPreferredScrollableViewportSize(tablaStatus.getPreferredSize()); 			
		tablaStatusScroll=new JScrollPane(tablaStatus); 
		
		//Panel de configuracion
			//Panel
			config = new JPanel();
			
			//Config
			cpbusqueda = new JLabel("Resultados por busqueda: ");
			tiempo = new JLabel("Tiempo de busqueda (ms): ");
			porcentajecompra = new JLabel("Porcentaje de compra: ");
			porcentajeventa = new JLabel("Porcentaje de venta: ");
			proteccionmonedas = new JLabel("Proteccion de monedas: ");
			monedasminimas = new JLabel("Minimo de monedas: ");
			
			txtcpbusqueda = new JTextField(); 
			txttiempo = new JTextField();
			txtporcentajecompra = new JTextField();
			txtporcentajeventa = new JTextField();
			txtmonedasminimas = new JTextField();
			
			cbproteccionmonedas = new JCheckBox();
			//Login
			cuser = new JLabel("Usuario:");
			cpass = new JLabel("Contraseña:");
			crespuesta = new JLabel("Hash respuesta:");
			txtcuser = new JTextField(); 
			txtcpass = new JTextField(); 
			txtcrespuesta = new JTextField(); 
			
		//Panel de proceso
			//Panel
			proceso = new JPanel();
		
	}
    
	@SuppressWarnings("unchecked")
	void statusVentana(){	
		estatus.setLayout(null);
		
		config.setVisible(false);estatus.setVisible(false);proceso.setVisible(false);
		config.removeAll();
		estatus.removeAll();
		proceso.removeAll();
		estatus.repaint();
		
		//Panel
			estatus.setBounds(0, 0, 1000, 700);add(estatus);estatus.setVisible(true);
			menuBarra.setVisible(true);
			
		//Butones
			buscar.setBounds(375, 60, 100, 25);estatus.add(buscar);buscar.setVisible(true);buscar.addActionListener(this);
			añadir.setBounds(375, 450, 100, 25);estatus.add(añadir);añadir.setVisible(true);añadir.addActionListener(this);
			
		//Login	
//			user.setBounds(815, 60, 200, 25);estatus.add(user);user.setVisible(true);
//			pass.setBounds(815, 100, 200, 25);estatus.add(pass);pass.setVisible(true);
//			respuesta.setBounds(815, 140, 200, 25);estatus.add(respuesta);respuesta.setVisible(true);
//			txtuser.setBounds(930, 60, 200, 25);estatus.add(txtuser);txtuser.setVisible(true);
//			txtpass.setBounds(930, 100, 200, 25);estatus.add(txtpass);txtpass.setVisible(true);
//			txtrespuesta.setBounds(930, 140, 200, 25);estatus.add(txtrespuesta);txtrespuesta.setVisible(true);
			
		//Busqueda
			jugadores.setBounds(20, 20, 200, 25);estatus.add(jugadores);jugadores.setVisible(true);
			nombreJugador.setBounds(20, 60, 200, 25);estatus.add(nombreJugador);nombreJugador.setVisible(true);
			txtnombreJugador.setBounds(130, 60, 200, 25);estatus.add(txtnombreJugador);txtnombreJugador.setVisible(true);txtnombreJugador.addActionListener(this);

		//JComboBox
			//ComboBox
			comboSeleccion.setBounds(130, 20, 200, 25);estatus.add(comboSeleccion);comboSeleccion.setVisible(true);
			comboSeleccion.addItem("Players");
			comboSeleccion.addItem("Contracts");
			comboSeleccion.addItem("Fitness");
			comboSeleccion.addActionListener(this);
			
		//Seleccion
			tituloJugador.setBounds(20, 260, 200, 25);estatus.add(tituloJugador);tituloJugador.setVisible(true);
			tituloPrecio.setBounds(20, 340, 200, 25);estatus.add(tituloPrecio);tituloPrecio.setVisible(true);
			tituloTablaDatos.setBounds(20, 500, 200, 25); estatus.add(tituloTablaDatos); tituloTablaDatos.setVisible(true);
			
			nombreJugadorSeleccionado.setBounds(20, 295, 200, 25);estatus.add(nombreJugadorSeleccionado);nombreJugadorSeleccionado.setVisible(true);
			
			maxb.setBounds(20, 370, 200, 25);estatus.add(maxb);maxb.setVisible(true);
			precioVenta.setBounds(20, 410, 200, 25);estatus.add(precioVenta);precioVenta.setVisible(true);
			precioVentaYa.setBounds(20, 450, 200, 25);estatus.add(precioVentaYa);precioVentaYa.setVisible(true);
			
			txtnombreJugadorSeleccionado.setBounds(90, 295, 175, 25);estatus.add(txtnombreJugadorSeleccionado);txtnombreJugadorSeleccionado.setVisible(true);
			txtcat.setBounds(110, 380, 150, 25);estatus.add(txtcat);txtcat.setVisible(false);
			txtrareflag.setBounds(110, 410, 150, 25);estatus.add(txtrareflag);txtrareflag.setVisible(false);
			txtid.setBounds(410, 350, 150, 25);estatus.add(txtid);txtid.setVisible(false);
			txtmaxb.setBounds(150, 370, 150, 25); estatus.add(txtmaxb);txtmaxb.setVisible(true);
			txtprecioVenta.setBounds(150, 410, 150, 25);estatus.add(txtprecioVenta);txtprecioVenta.setVisible(true);
			txtprecioVentaYa.setBounds(150, 450, 150, 25);estatus.add(txtprecioVentaYa);txtprecioVentaYa.setVisible(true);
			
			tablaBuscarScrollJugadores.setBounds(20,100,450,150); estatus.add(tablaBuscarScrollJugadores); tablaBuscarScrollJugadores.setVisible(true);
			
			tablaBuscarScrollContratos.setBounds(20,100,450,150); estatus.add(tablaBuscarScrollContratos); tablaBuscarScrollContratos.setVisible(false);
			
			tablaBuscarScrollFitness.setBounds(20,100,450,150); estatus.add(tablaBuscarScrollFitness); tablaBuscarScrollFitness.setVisible(false);
			
			tablaListaScroll.setBounds(20,540,650,200); estatus.add(tablaListaScroll); tablaListaScroll.setVisible(true);
//			tablaLista.getColumnModel().getColumn(0).setMaxWidth(0);
//			tablaLista.getColumnModel().getColumn(0).setMinWidth(0);
//			tablaLista.getColumnModel().getColumn(0).setPreferredWidth(0);
//			tablaLista.getColumnModel().getColumn(1).setMaxWidth(0);
//			tablaLista.getColumnModel().getColumn(1).setMinWidth(0);
//			tablaLista.getColumnModel().getColumn(1).setPreferredWidth(0);
//			tablaLista.getColumnModel().getColumn(3).setMaxWidth(0);
//			tablaLista.getColumnModel().getColumn(3).setMinWidth(0);
//			tablaLista.getColumnModel().getColumn(3).setPreferredWidth(0);
//			tablaLista.getColumnModel().getColumn(4).setMaxWidth(0);
//			tablaLista.getColumnModel().getColumn(4).setMinWidth(0);
//			tablaLista.getColumnModel().getColumn(4).setPreferredWidth(0);
								
	}	
	
	void configPanel(){
		config.setLayout(null);
		
		config.setVisible(false);estatus.setVisible(false);proceso.setVisible(false);
		config.removeAll();
		estatus.removeAll();
		proceso.removeAll();
		config.repaint();
		//Panel
			config.setBounds(0, 0, 1000, 700);add(config);config.setVisible(true);
		
		//Botones
			guardar.setBounds(375, 450, 100, 25);config.add(guardar);guardar.setVisible(true);guardar.addActionListener(this);
			
		//Config
			cpbusqueda.setBounds(20, 180, 200, 25);config.add(cpbusqueda);cpbusqueda.setVisible(true);
			tiempo.setBounds(20, 220, 200, 25);config.add(tiempo);tiempo.setVisible(true);
			porcentajecompra.setBounds(20, 260, 200, 25);config.add(porcentajecompra);porcentajecompra.setVisible(true);
			porcentajeventa.setBounds(20, 300, 200, 25);config.add(porcentajeventa);porcentajeventa.setVisible(true);
			proteccionmonedas.setBounds(20, 340, 200, 25);config.add(proteccionmonedas);proteccionmonedas.setVisible(true);
			monedasminimas.setBounds(20, 380, 200, 25);config.add(monedasminimas);monedasminimas.setVisible(true);
			
			txtcpbusqueda.setBounds(190, 180, 100, 25);config.add(txtcpbusqueda);txtcpbusqueda.setVisible(true);
			txttiempo.setBounds(190, 220, 100, 25);config.add(txttiempo);txttiempo.setVisible(true);
			txtporcentajecompra.setBounds(190, 260, 100, 25);config.add(txtporcentajecompra);txtporcentajecompra.setVisible(true); 
			txtporcentajeventa.setBounds(190, 300, 100, 25);config.add(txtporcentajeventa);txtporcentajeventa.setVisible(true);
			txtmonedasminimas.setBounds(190, 340, 100, 25);config.add(txtmonedasminimas);txtmonedasminimas.setVisible(true);
			
			cbproteccionmonedas.setBounds(190, 380, 100, 25);config.add(cbproteccionmonedas);cbproteccionmonedas.setVisible(true);
		
		//Login	
			cuser.setBounds(20, 60, 200, 25);config.add(cuser);cuser.setVisible(true);
			cpass.setBounds(20, 100, 200, 25);config.add(cpass);cpass.setVisible(true);
			crespuesta.setBounds(20, 140, 200, 25);config.add(crespuesta);crespuesta.setVisible(true);
			txtcuser.setBounds(190, 60, 200, 25);config.add(txtcuser);txtcuser.setVisible(true);
			txtcpass.setBounds(190, 100, 200, 25);config.add(txtcpass);txtcpass.setVisible(true);
			txtcrespuesta.setBounds(190, 140, 200, 25);config.add(txtcrespuesta);txtcrespuesta.setVisible(true);

	}
	
	void procesoPanel(){
		proceso.setLayout(null);
		
		config.setVisible(false);estatus.setVisible(false);proceso.setVisible(false);
		config.removeAll();
		estatus.removeAll();
		proceso.removeAll();
		proceso.repaint();
		
		//Panel
		proceso.setBounds(0, 0, 1000, 700);add(proceso);proceso.setVisible(true);
		
		//Botones
		start.setBounds(20, 60, 100, 25);proceso.add(start);start.setVisible(true);start.addActionListener(this);
		stop.setBounds(20, 100, 100, 25);proceso.add(stop);stop.setVisible(true);stop.addActionListener(this);
		
		//Varios
		estado.setBounds(20, 20, 200, 25);proceso.add(estado);estado.setVisible(true);
		conectado.setBounds(115, 20, 200, 25);proceso.add(conectado);conectado.setForeground(verdeOscuro);
		noConectado.setBounds(115, 20, 200, 25);proceso.add(noConectado);noConectado.setForeground(rojo);
		ThreadStart.conectado();
		
		log.setBounds(20, 280, 200, 25);proceso.add(log);log.setVisible(true);
		imagen_monedas.setBounds(425, 20, 30, 25);proceso.add(imagen_monedas);imagen_monedas.setVisible(true);
	
		//Tabla log 
		tablaStatusScroll.setBounds(20,320,450,420); proceso.add(tablaStatusScroll); tablaStatusScroll.setVisible(true);
	}
	
	void blanquear(){
		txtcat.setText("");
		txtnombreJugadorSeleccionado.setText("");
		txtid.setText("");
		txtrareflag.setText("");
		txtteam.setText("");
		txtmaxb.setValue(0);
		txtprecioVenta.setText("");
		txtprecioVentaYa.setText("");
		txtnat.setText("");
		txtlev.setText("");
		txtpos.setText("");
	}
	
	static void mostrarMonedas(int monedasRestantes){
	    panelMonedas.setLayout(null);
	    panelMonedas.removeAll();
	    int unidad;
	    String tamañoMonedas=null;
	    try {
			DecimalFormat df = new DecimalFormat("###,###,###,###.##"); 
			tamañoMonedas = df.format(monedasRestantes); 
	    }catch (Exception e) {e.printStackTrace();}

	    int sitioLabel = (tamañoMonedas.length()*5);
	    if(tamañoMonedas.length()>10){
	        unidad = 40;
	    }else if(tamañoMonedas.length()>6){
	        unidad = 30;
	    }else if((tamañoMonedas.length()>3)){
	        unidad = 20;
	    }else{
	        unidad = 10;
	    }
	    
	    panelMonedas.setVisible(false);
	    panelMonedas.setBounds(420-(sitioLabel+unidad), 20,150, 30);proceso.add(panelMonedas);panelMonedas.setVisible(true);
	    
	    saldo.setBounds(0, 0,150, 30);panelMonedas.add(saldo);saldo.setVisible(true);saldo.setHorizontalAlignment(SwingConstants.LEFT);
	    saldo.setText(tamañoMonedas);
	}
	
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == menuFutStatus){
			statusVentana();
		}
		if(e.getSource() ==	menuConfig){
			configPanel();
		}
		if(e.getSource() == menuEstadoProceso){
			procesoPanel();
		}
		if(e.getSource()==stop){
			System.exit(0);
		}
		if(e.getSource()==start){
	        new Thread ( new ThreadStart()).start();
		}
		if(e.getSource()==añadir){
			
			String tipo = seleccionado.toLowerCase();
			String categoria = txtcat.getText();
			String nombre = txtnombreJugadorSeleccionado.getText();
			String id = txtid.getText();
			String card = txtrareflag.getText();
			String pcompra = txtmaxb.getValue().toString();
			String pventa = txtprecioVenta.getText();
			String pventaya = txtprecioVentaYa.getText();
			String level = txtlev.getText();
			String posicion = txtpos.getText();
			
			if(nombre.equalsIgnoreCase("")!=true){
				Object colTablaAnadir[]= {tipo, categoria, nombre, id, card, pcompra, pventa, pventaya, level, posicion};
				modeloTablaLista.addRow(colTablaAnadir);
				Xml.guardarXML();
				blanquear();
			}
		}
		
		if(e.getSource() == guardar){
			XmlConfig.guardarXML();
		}
		
		if(e.getSource()==buscar || e.getSource()==txtnombreJugador){
			AdaptadorRaton.iniciarFilas();
			switch(seleccionado){
			case "Players":
				pulsadoBuscarPlayers();
				break;
			case "Contracts":
				pulsadoBuscarContracts();
				break;
			case "Fitness":
				pulsadoBuscarFitness();
				break;
			}
		}
		
		if(e.getSource()==comboSeleccion){
			seleccionado=(String)comboSeleccion.getSelectedItem();
		
			switch(seleccionado){
			case "Players":
				txtnombreJugador.setText("");txtnombreJugador.setEnabled(true);
				tablaBuscarScrollContratos.setVisible(false);
				tablaBuscarScrollJugadores.setVisible(true);
				tablaBuscarScrollFitness.setVisible(false);
				int contador=Gui.modeloTablaBuscarJugadores.getRowCount();

				if(contador!=0){
					for(int x=0;x<contador;x++){
						Gui.modeloTablaBuscarJugadores.removeRow(0);
					}
					contador=0;
				}
				blanquear();				
				break;
			case "Contracts":
				txtnombreJugador.setText("Contracts");txtnombreJugador.setEnabled(false);
				tablaBuscarScrollJugadores.setVisible(false);
				tablaBuscarScrollContratos.setVisible(true);
				tablaBuscarScrollFitness.setVisible(false);
				contador = Gui.modeloTablaBuscarContratos.getRowCount();

				if(contador!=0){
					for(int x=0;x<contador;x++){
						Gui.modeloTablaBuscarContratos.removeRow(0);
					}
					contador=0;
				}
				blanquear();
				break;
			case "Fitness":
				txtnombreJugador.setText("Fitness");txtnombreJugador.setEnabled(false);
				tablaBuscarScrollJugadores.setVisible(false);
				tablaBuscarScrollContratos.setVisible(false);
				tablaBuscarScrollFitness.setVisible(true);
				contador = Gui.modeloTablaBuscarFitness.getRowCount();

				if(contador!=0){
					for(int x=0;x<contador;x++){
						Gui.modeloTablaBuscarFitness.removeRow(0);
					}
					contador=0;
				}
				blanquear();
				break;
			}
		}
		e.setSource(null);
	}
	
	void pulsadoBuscarPlayers(){
		URLReaderPlayers.seleccionarJugadores(txtnombreJugador.getText());
	}
	
	void pulsadoBuscarContracts(){
		URLReaderContracs.seleccionarContracts();
	}
	
	void pulsadoBuscarFitness(){
		URLReaderFitness.seleccionarFitness();
	}
	
	public static void main(String[] args) {
		Gui ventana=new Gui();
		ventana.setBounds(100, 100, 700, 790);
		ventana.setVisible(true);
		ventana.setResizable(false);
		ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		ventana.statusVentana();
		ventana.setTitle("Autobuyer");
		
		//Cargamos la base de datos	
		Xml.cargarXML();
		XmlConfig.cargarXML();
	}

}
