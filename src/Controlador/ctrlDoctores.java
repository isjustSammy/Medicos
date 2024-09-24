package Controlador;

import Modelo.Doctores;
import Vista.JFRMedicos;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;


public class ctrlDoctores implements MouseListener, KeyListener{
    
    private Doctores modelo;
    private JFRMedicos vista;
   
    //2- Crear el constructor
    public ctrlDoctores(Doctores modelo, JFRMedicos vista){
        this.modelo = modelo;
        this.vista = vista;

        vista.btnAgregar.addMouseListener(this);   
        modelo.Mostrar(vista.jtbDoctores);
        vista.btnEliminar.addMouseListener(this);
        vista.jtbDoctores.addMouseListener(this);
        vista.btnActualizar.addMouseListener(this);
        vista.btnLimpiar.addMouseListener(this);
    
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        
         if(e.getSource() == vista.btnAgregar){
            modelo.setNombre_Doctor(vista.txtNombreDoctor.getText());
            modelo.setEdad_Doctor(Integer.parseInt(vista.txtEdadDoctor.getText()));
            modelo.setCorreo_Doctor(vista.txtCorreoDoctor.getText());
            modelo.setPeso_Doctor(Double.parseDouble(vista.txtPesoDoctor.getText()));
            
            modelo.Guardar();
            modelo.Mostrar(vista.jtbDoctores);
        }
        
        if(e.getSource() == vista.btnEliminar){
            modelo.Eliminar(vista.jtbDoctores);
            modelo.Mostrar(vista.jtbDoctores);
        }
        
        if(e.getSource() == vista.jtbDoctores){
            modelo.cargarDatosTabla(vista);
        }
        
        if(e.getSource() == vista.btnActualizar){
            modelo.setNombre_Doctor(vista.txtNombreDoctor.getText());
            modelo.setEdad_Doctor(Integer.parseInt(vista.txtEdadDoctor.getText()));
            modelo.setCorreo_Doctor(vista.txtCorreoDoctor.getText());
            modelo.setPeso_Doctor(Double.parseDouble(vista.txtPesoDoctor.getText()));
            
            modelo.Actualizar(vista.jtbDoctores);
            modelo.Mostrar(vista.jtbDoctores);
        }
        
        if(e.getSource() == vista.btnLimpiar){
            modelo.limpiar(vista);
        }
        
    }

    @Override
    public void mousePressed(MouseEvent e) {
        
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        
    }

    @Override
    public void mouseEntered(MouseEvent e) {
       
    }

    @Override
    public void mouseExited(MouseEvent e) {
       
    }

    @Override
    public void keyTyped(KeyEvent e) {
        
        if(e.getSource() == vista.txtBuscar){
            modelo.Buscar(vista.jtbDoctores, vista.txtBuscar);
        }
        
    }

    @Override
    public void keyPressed(KeyEvent e) {
       
    }

    @Override
    public void keyReleased(KeyEvent e) {
        
    }
    
    
    
   
    
}
