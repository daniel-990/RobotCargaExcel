package org.example.Conexion;

import org.example.Controller.Controller;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.util.Scanner;

/*
    Daniel Arango Villegas
* */

public class ConexionBaseDeDatos {
    Properties properties = new Properties();
    String ruta = "/Users/xorroperro/Documents/robotCargaExcel/Config/Config.properties";
    public void dataBase(){
        try (FileInputStream fis = new FileInputStream(ruta)) {
            properties.load(fis);

            // Obtener propiedades
            String url = properties.getProperty("database.url");
            String user = properties.getProperty("database.user");
            String password = properties.getProperty("database.password");

            //consultas
            String consulta1 = properties.getProperty("consulta1"); //--> consultas
            String consulta2 = properties.getProperty("consulta2");
            String consulta3 = properties.getProperty("consulta3");
            String consulta4 = properties.getProperty("consulta4");
            String consulta5 = properties.getProperty("consulta5");
            String consulta6 = properties.getProperty("consulta6");

            //ejecutar conexion
            ejecutarConexion(consulta6,consulta5,consulta4,consulta3,consulta2,consulta1,url,user,password);

        } catch (IOException e) {
            System.err.println("Error al leer el archivo de propiedades: " + e.getMessage());
        }
    }

    public void ejecutarConexion(String consulta6,String consulta5,String consulta4,String consulta3,String consulta2,String consulta1, String urlDb, String user, String pass){

        String url = urlDb;
        String usuario = user;
        String contrasena = pass;

        // Establecer la conexión
        try (Connection conn = DriverManager.getConnection(url, usuario, contrasena)) {
            if (conn != null) {
                System.out.println("se conecto a la base de datos");
                Controller ejecutar = new Controller();
                //ejecutar las consultas
                ejecutar.ejecutarConsulta1("sql1",consulta1,url, usuario, contrasena);
                ejecutar.ejecutarConsulta1("sql2",consulta2,url, usuario, contrasena);
                ejecutar.ejecutarConsulta1("sql3",consulta3,url, usuario, contrasena);
                ejecutar.ejecutarConsulta1("sql4",consulta4,url, usuario, contrasena);
                ejecutar.ejecutarConsulta1("sql5",consulta5,url, usuario, contrasena);
                ejecutar.ejecutarConsulta1("sql6",consulta6,url, usuario, contrasena);
            }
        } catch (SQLException e) {
            System.err.println("Error al conectar a la base de datos: " + e.getMessage());
        }
    }
}
