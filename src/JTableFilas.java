import java.awt.Rectangle;


public class JTableFilas {
   
    static void filas(String contenidoFila){
        Object nuevaFila0[]= {contenidoFila};
        Gui.modeloTablaStatus.addRow(nuevaFila0);
        int row0 = Gui.tablaStatus.getRowCount ();
        Rectangle rect0 = Gui.tablaStatus.getCellRect(row0, 0, true);
        Gui.tablaStatus.scrollRectToVisible(rect0);
        Gui.tablaStatus.clearSelection();
    }

}
