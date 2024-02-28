package org.example.Model;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class DatosModel {
    int rutaConfig;
    String ruta;
    String rutaDev = "C:\\Users\\SSSA\\Documents\\NetBeansProjects\\RobotDescargaExcelDb\\Config\\Config.properties";
    Properties properties = new Properties();

    public String getRuta() {
        try (FileInputStream fis = new FileInputStream(rutaDev)) {
            properties.load(fis);
            rutaConfig = Integer.parseInt(properties.getProperty("tipoSis"));
            if(rutaConfig == 1){
                ruta = properties.getProperty("rutaConfgPc");
            }else{
                ruta = properties.getProperty("rutaConfgMac");
            }
        } catch (IOException e) {
            System.err.println("Error al leer el archivo de propiedades: " + e.getMessage());
        }
        return ruta;
    }
    public void setRuta(String ruta) {
        this.ruta = ruta;
    }
}
