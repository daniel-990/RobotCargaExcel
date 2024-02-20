package org.example.Conexion;

import org.example.Controller.Controller;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.util.Scanner;

public class ConexionBaseDeDatos {

    Scanner input = new Scanner(System.in);
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
            String consulta1 = properties.getProperty("consulta1"); //--> consulta de pruebas
            String consulta2 = properties.getProperty("consulta2");
            String consulta3 = properties.getProperty("consulta3");
            String consulta4 = properties.getProperty("consulta4");
            //consultas reales
            String sql1 = properties.getProperty("sql1");
            String sql2 = properties.getProperty("sql2");
            String sql3 = properties.getProperty("sql3");
            String sql4 = properties.getProperty("sql4");
            String sql5 = properties.getProperty("sql5");
            String sql6 = properties.getProperty("sql6");

            //ejecutar conexion
            ejecutarConexion("","",consulta4,consulta3,consulta2,consulta1,url,user,password);

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
            }
        } catch (SQLException e) {
            System.err.println("Error al conectar a la base de datos: " + e.getMessage());
        }
    }
}
