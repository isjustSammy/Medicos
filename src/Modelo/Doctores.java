/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

import Vista.JFRMedicos;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.UUID;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Estudiante
 */
public class Doctores {
    
    private String UUID_Doctor;
    private String Nombre_Doctor;
    private int Edad_Doctor;
    private double Peso_Doctor;
    private String Correo_Doctor;

    /**
     * @return the UUID_Doctor
     */
    public String getUUID_Doctor() {
        return UUID_Doctor;
    }

    /**
     * @param UUID_Doctor the UUID_Doctor to set
     */
    public void setUUID_Doctor(String UUID_Doctor) {
        this.UUID_Doctor = UUID_Doctor;
    }

    /**
     * @return the Nombre_Doctor
     */
    public String getNombre_Doctor() {
        return Nombre_Doctor;
    }

    /**
     * @param Nombre_Doctor the Nombre_Doctor to set
     */
    public void setNombre_Doctor(String Nombre_Doctor) {
        this.Nombre_Doctor = Nombre_Doctor;
    }

    /**
     * @return the Edad_Doctor
     */
    public int getEdad_Doctor() {
        return Edad_Doctor;
    }

    /**
     * @param Edad_Doctor the Edad_Doctor to set
     */
    public void setEdad_Doctor(int Edad_Doctor) {
        this.Edad_Doctor = Edad_Doctor;
    }

    /**
     * @return the Peso_Doctor
     */
    public double getPeso_Doctor() {
        return Peso_Doctor;
    }

    /**
     * @param Peso_Doctor the Peso_Doctor to set
     */
    public void setPeso_Doctor(double Peso_Doctor) {
        this.Peso_Doctor = Peso_Doctor;
    }

    /**
     * @return the Correo_Doctor
     */
    public String getCorreo_Doctor() {
        return Correo_Doctor;
    }

    /**
     * @param Correo_Doctor the Correo_Doctor to set
     */
    public void setCorreo_Doctor(String Correo_Doctor) {
        this.Correo_Doctor = Correo_Doctor;
    }
    
    
     public void Guardar() {
        //Creamos una variable igual a ejecutar el método de la clase de conexión
        Connection conexion = ClaseConexion.getConexion();
        try {
            //Creamos el PreparedStatement que ejecutará la Query
            PreparedStatement addDoctor = conexion.prepareStatement("INSERT INTO tbDoctor(UUID_Doctor, Nombre_Doctor, Edad_Doctor, Correo_Doctor, Peso_Doctor) VALUES (?, ?, ?, ?, ?)");
            //Establecer valores de la consulta SQL
            addDoctor.setString(1, UUID.randomUUID().toString());
            addDoctor.setString(2, getNombre_Doctor());
            addDoctor.setInt(3, getEdad_Doctor());
            addDoctor.setString(4, getCorreo_Doctor());
            addDoctor.setDouble(5, getPeso_Doctor());
            
 
            //Ejecutar la consulta
            addDoctor.executeUpdate();
 
        } catch (SQLException ex) {
            System.out.println("este es el error en el modelo:metodo guardar " + ex);
        }
    }
     
     
      public void Mostrar(JTable tabla) {
        //Creamos una variable de la clase de conexion
        Connection conexion = ClaseConexion.getConexion();
        //Definimos el modelo de la tabla
        DefaultTableModel modeloDeDatos = new DefaultTableModel();
        
        modeloDeDatos.setColumnIdentifiers(new Object[]{"UUID_Doctor", "Nombre_Doctor", "Edad_Doctor", "Correo_Doctor", "Peso_Doctor"});
        try {
            //Creamos un Statement
            Statement statement = conexion.createStatement();
            //Ejecutamos el Statement con la consulta y lo asignamos a una variable de tipo ResultSet
            ResultSet rs = statement.executeQuery("SELECT * FROM tbDoctor");
            //Recorremos el ResultSet
            while (rs.next()) {
                //Llenamos el modelo por cada vez que recorremos el resultSet
                modeloDeDatos.addRow(new Object[]{rs.getString("UUID_Doctor"), 
                    rs.getString("Nombre_Doctor"), 
                    rs.getInt("Edad_Doctor"), 
                    rs.getString("Correo_Doctor"),
                    rs.getString("Peso_Doctor")});
            }
           
            tabla.setModel(modeloDeDatos);
        } catch (Exception e) {
            System.out.println("Este es el error en el modelo, metodo mostrar " + e);
        }
    }
   
      
      public void Eliminar(JTable tabla) {
        //Creamos una variable igual a ejecutar el método de la clase de conexión
        Connection conexion = ClaseConexion.getConexion();

        //obtenemos que fila seleccionó el usuario
        int filaSeleccionada = tabla.getSelectedRow();
        //Obtenemos el id de la fila seleccionada
        String miId = tabla.getValueAt(filaSeleccionada, 0).toString();
        
        //borramos 
        try {
            PreparedStatement deleteDoctor= conexion.prepareStatement("delete from tbDoctor where UUID_Doctor = ?");
            deleteDoctor.setString(1, miId);
            deleteDoctor.executeUpdate();
        } catch (Exception e) {
            System.out.println("este es el error metodo de eliminar" + e);
        }
    }
    
      
       public void cargarDatosTabla(JFRMedicos vista) {
        // Obtén la fila seleccionada 
        int filaSeleccionada = vista.jtbDoctores.getSelectedRow();

        // Debemos asegurarnos que haya una fila seleccionada antes de acceder a sus valores
        if (filaSeleccionada != -1) {
            String UUIDDeTb = vista.jtbDoctores.getValueAt(filaSeleccionada, 0).toString();
            String NombreDeTB = vista.jtbDoctores.getValueAt(filaSeleccionada, 1).toString();
            String EdadDeTb = vista.jtbDoctores.getValueAt(filaSeleccionada, 2).toString();
            String CorreoDeTB = vista.jtbDoctores.getValueAt(filaSeleccionada, 3).toString();
            String PesoDeTB = vista.jtbDoctores.getValueAt(filaSeleccionada, 4).toString();

            // Establece los valores en los campos de texto
            vista.txtNombreDoctor.setText(NombreDeTB);
            vista.txtEdadDoctor.setText(EdadDeTb);
            vista.txtCorreoDoctor.setText(CorreoDeTB);
             vista.txtPesoDoctor.setText(PesoDeTB);
        }
    }
    
       
       
        public void Actualizar(JTable tabla) {
        //Creamos una variable igual a ejecutar el método de la clase de conexión
        Connection conexion = ClaseConexion.getConexion();

        //obtenemos que fila seleccionó el usuario
        int filaSeleccionada = tabla.getSelectedRow();
        if (filaSeleccionada != -1) {
            //Obtenemos el id de la fila seleccionada
            String miUUId = tabla.getValueAt(filaSeleccionada, 0).toString();
            try { 
                //Ejecutamos la Query
                PreparedStatement updateUser = conexion.prepareStatement("update tbDoctor set Nombre_Doctor= ?, Edad_Doctor = ?, Correo_Doctor = ?,  Peso_Doctor = ? where UUID_Doctor = ?");

                updateUser.setString(1, getNombre_Doctor());
                updateUser.setInt(2, getEdad_Doctor());
                updateUser.setString(3, getCorreo_Doctor());
                updateUser.setDouble(4, getPeso_Doctor());
                updateUser.setString(5, miUUId);
                updateUser.executeUpdate();
            } catch (Exception e) {
                System.out.println("este es el error en el metodo de actualizar" + e);
            }
        } else {
            System.out.println("no");
        }
    }
        
        
         public void limpiar(JFRMedicos vista) {
        vista.txtNombreDoctor.setText("");
        vista.txtEdadDoctor.setText("");
        vista.txtCorreoDoctor.setText("");
        vista.txtPesoDoctor.setText("");
    }
      
      
      public void Buscar(JTable tabla, JTextField txtBuscar) {
        //Creamos una variable igual a ejecutar el método de la clase de conexión
        Connection conexion = ClaseConexion.getConexion();
        //Definimos el modelo de la tabla
        DefaultTableModel modelo = new DefaultTableModel();
        modelo.setColumnIdentifiers(new Object[]{"UUID_Doctor", "Nombre_Doctor", "Edad_Doctor", "Correo_Docotor", "Peso_Doctor"});
        try {
            PreparedStatement deleteEstudiante = conexion.prepareStatement("SELECT * FROM tbDoctor WHERE Nombre_Doctor LIKE ? || '%'");
            deleteEstudiante.setString(1, txtBuscar.getText());
            ResultSet rs = deleteEstudiante.executeQuery();
 
             while (rs.next()) {
                //Llenamos el modelo por cada vez que recorremos el resultSet
                modelo.addRow(new Object[]{rs.getString("UUID_Doctor"), 
                    rs.getString("Nombre_Doctor"), 
                    rs.getInt("Edad_Doctor"), 
                    rs.getString("Correo_Doctor")});
            }
            //Asignamos el nuevo modelo lleno a la tabla
            tabla.setModel(modelo);
 
        } catch (Exception e) {
            System.out.println("Este es el error en el modelo, metodo de buscar " + e);
        }
    }
}
