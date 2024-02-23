package org.example.Conexion;
import org.example.Controller.ControllerMysql;
import org.example.Controller.ControllerOracle;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;

/*
    Daniel Arango Villegas
* */

public class ConexionBaseDeDatos {
    Properties properties = new Properties();
    String ruta = "/Users/xorroperro/Documents/robotCargaExcel/Config/Config.properties";
    private static String url;
    private static String user;
    private static String pass;
    private static String consulta1;
    private static String consulta2;
    private static String consulta3;
    private static String consulta4;
    private static String consulta5;
    private static String consulta6;

    Connection connection = null;
    public void dataBase(){
        try (FileInputStream fis = new FileInputStream(ruta)) {
            properties.load(fis);

            // Obtener propiedades
            url = properties.getProperty("database.url");
            user = properties.getProperty("database.user");
            pass = properties.getProperty("database.password");
            int tipoConexion = Integer.parseInt(properties.getProperty("tipoConexion"));

            //consultas
            consulta1 = properties.getProperty("consulta1"); //--> consultas
            consulta2 = properties.getProperty("consulta2");
            consulta3 = properties.getProperty("consulta3");
            consulta4 = properties.getProperty("consulta4");
            consulta5 = properties.getProperty("consulta5");
            consulta6 = properties.getProperty("consulta6");

            //ejecutar conexion
            ejecutarConexion(tipoConexion);

        } catch (IOException e) {
            System.err.println("Error al leer el archivo de propiedades: " + e.getMessage());
        }
    }

    public void ejecutarConexion(int tipoConexion){
        switch (tipoConexion){
            case 1:
                Mysql();
            break;
            case 2:
                Oracle();
            break;
            case 3:
                sqlServer();
            break;
            default:
                System.out.println("no se tiene tipo de conexion: {tipoConexion}");
        }
    }
    public static void Mysql(){
        // Establecer la conexión Mysql
        try (Connection conn = DriverManager.getConnection(url, user, pass)){
            if (conn != null) {
                System.out.println("Conexión exitosa a la base de datos Mysql.");
                ControllerMysql ejecutar = new ControllerMysql();
                //ejecutar las consultas
                ejecutar.ejecutarConsulta1("sql1",consulta1,url, user, pass);
                ejecutar.ejecutarConsulta1("sql2",consulta2,url, user, pass);
                ejecutar.ejecutarConsulta1("sql3",consulta3,url, user, pass);
                ejecutar.ejecutarConsulta1("sql4",consulta4,url, user, pass);
                ejecutar.ejecutarConsulta1("sql5",consulta5,url, user, pass);
                ejecutar.ejecutarConsulta1("sql6",consulta6,url, user, pass);
            }
        } catch (SQLException e) {
            System.err.println("Error al conectar a la base de datos: " + e.getMessage());
        }
    }

    public static void Oracle(){
        // Establecer la conexión Oracle
        Connection conn = null;
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            conn = DriverManager.getConnection(url, user, pass);

            if (conn != null) {
                System.out.println("Conexión exitosa a la base de datos Oracle.");
                ControllerOracle ejecutar = new ControllerOracle();
                //ejecutar las consultas
                ejecutar.ejecutarConsulta1("sql1",consulta1,url, user, pass);
                ejecutar.ejecutarConsulta1("sql2",consulta2,url, user, pass);
                ejecutar.ejecutarConsulta1("sql3",consulta3,url, user, pass);
                ejecutar.ejecutarConsulta1("sql4",consulta4,url, user, pass);
                ejecutar.ejecutarConsulta1("sql5",consulta5,url, user, pass);
                ejecutar.ejecutarConsulta1("sql6",consulta6,url, user, pass);
            } else {
                System.out.println("No se pudo establecer la conexión.");
            }
        } catch (ClassNotFoundException e) {
            System.out.println("Error al cargar el controlador JDBC: " + e.getMessage());
        } catch (SQLException e) {
            System.out.println("Error al conectar a la base de datos: " + e.getMessage());
        } finally {
            // Cerrar la conexión
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    System.out.println("Error al cerrar la conexión: " + e.getMessage());
                }
            }
        }
    }

    public static void sqlServer(){
        System.out.println("datos de sql server");
    }
}

