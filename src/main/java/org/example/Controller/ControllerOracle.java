package org.example.Controller;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;
import java.sql.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Properties;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

/*
    Daniel Arango Villegas
* */

public class ControllerOracle {
    //ruta del archivo
    String rutaDelExcel;
    String rutaDefinitivaExcel;
    String logFolderPath;
    String logFolderPathDefinitiva;
    String nombrePestana;
    int tipoConexion;
    Properties properties = new Properties();
    LocalDateTime fechaActual = LocalDateTime.now();
    DateTimeFormatter formato = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    String ruta = "/Users/xorroperro/Documents/robotCargaExcel/Config/Config.properties";
    Logger logger = Logger.getLogger("MiRobotLoger");

    Connection conn = null;
    Statement stmt = null;
    ResultSet rs = null;

    public void ejecutarConsulta1(String tipoConsulta,String parametroConsulta, String url, String user, String password){
        try (FileInputStream fis = new FileInputStream(ruta)){
            properties.load(fis);
            rutaDelExcel = properties.getProperty("rutaNas");
            logFolderPathDefinitiva = properties.getProperty("rutaLogs");
            nombrePestana = properties.getProperty("nombrePestanaExcel");

            rutaDefinitivaExcel = rutaDelExcel+"/"+tipoConsulta;

            //enviar todos los datos en excel
            XSSFWorkbook workbook = new XSSFWorkbook();
            XSSFSheet sheet = workbook.createSheet(nombrePestana);

            if(tipoConsulta.equals("sql1")){
                //excel
                new File(rutaDefinitivaExcel).mkdirs();
                //logs
                logFolderPath = logFolderPathDefinitiva;
                // Creamos la carpeta de logs si no existe
                new File(logFolderPath).mkdirs();
                FileHandler fileHandler = new FileHandler(logFolderPath+"/logs_"+fechaActual.format(formato)+".txt");
                SimpleFormatter formatoLogs = new SimpleFormatter();
                fileHandler.setFormatter(formatoLogs);
                logger.addHandler(fileHandler);
                logger.setLevel(java.util.logging.Level.ALL);

                //ejecutar consultas
                conn = DriverManager.getConnection(url, user, password);
                stmt = conn.createStatement();
                rs = stmt.executeQuery(parametroConsulta);

                //se valida que exista el archivo
                File excelFile = new File(rutaDelExcel);
                if (excelFile.exists()) {
                    String acumulador2 = fechaActual.format(formato);
                    System.out.println("se crea excel con nombre: "+acumulador2);
                    logger.info("Se ejecuta ROBOT\nse genera el registro de datos\n" +
                            "se crea excel con nombre: datos_v"+acumulador2+"\n" +
                            "ruta del archivo guardado: "+rutaDefinitivaExcel+"\n" +
                            "se termina el proceso");
                    writeHeaderLine(sheet);
                    writeDataLines(rs, sheet);
                    FileOutputStream outputStream = new FileOutputStream(rutaDefinitivaExcel+"/datos_v"+acumulador2+".xlsx");
                    workbook.write(outputStream);
                } else {
                    String acumulador3 = fechaActual.format(formato);
                    System.out.println("se crea el primer documento: "+acumulador3);
                    logger.info("Segenera el registro de datos\n" +
                            "se crea excel con nombre: datos_v_1_"+acumulador3+"\n" +
                            "ruta del archivo guardado: "+rutaDefinitivaExcel+"\n" +
                            "se termina el proceso");
                    writeHeaderLine(sheet);
                    writeDataLines(rs, sheet);
                    FileOutputStream outputStream = new FileOutputStream(rutaDefinitivaExcel+"/datos_v_1_"+acumulador3+".xlsx");
                    workbook.write(outputStream);
                }
                // Cerrar la conexión
                rs.close();
                stmt.close();
                workbook.close();
                conn.close();
            }else if(tipoConsulta.equals("sql2")){
                //excel
                new File(rutaDefinitivaExcel).mkdirs();
                //logs
                logFolderPath = logFolderPathDefinitiva;
                // Creamos la carpeta de logs si no existe
                new File(logFolderPath).mkdirs();
                FileHandler fileHandler = new FileHandler(logFolderPath+"/logs_"+fechaActual.format(formato)+".txt");
                SimpleFormatter formatoLogs = new SimpleFormatter();
                fileHandler.setFormatter(formatoLogs);
                logger.addHandler(fileHandler);
                logger.setLevel(java.util.logging.Level.ALL);

                //conexion a la base de datos
                conn = DriverManager.getConnection(url, user, password);
                stmt = conn.createStatement();
                //ejecutar consultas
                rs = stmt.executeQuery(parametroConsulta);

                //se valida que exista el archivo
                File excelFile = new File(rutaDelExcel);
                if (excelFile.exists()) {
                    String acumulador2 = fechaActual.format(formato);
                    System.out.println("se crea excel con nombre: "+acumulador2);
                    logger.info("Se ejecuta ROBOT\nse genera el registro de datos\n" +
                            "se crea excel con nombre: datos_v"+acumulador2+"\n" +
                            "ruta del archivo guardado: "+rutaDefinitivaExcel+"\n" +
                            "se termina el proceso");
                    writeHeaderLine(sheet);
                    writeDataLines(rs, sheet);
                    FileOutputStream outputStream = new FileOutputStream(rutaDefinitivaExcel+"/datos_v"+acumulador2+".xlsx");
                    workbook.write(outputStream);
                } else {
                    String acumulador3 = fechaActual.format(formato);
                    System.out.println("se crea el primer documento: "+acumulador3);
                    logger.info("Segenera el registro de datos\n" +
                            "se crea excel con nombre: datos_v_1_"+acumulador3+"\n" +
                            "ruta del archivo guardado: "+rutaDefinitivaExcel+"\n" +
                            "se termina el proceso");
                    writeHeaderLine(sheet);
                    writeDataLines(rs, sheet);
                    FileOutputStream outputStream = new FileOutputStream(rutaDefinitivaExcel+"/datos_v_1_"+acumulador3+".xlsx");
                    workbook.write(outputStream);
                }
                // Cerrar la conexión
                rs.close();
                stmt.close();
                workbook.close();
                conn.close();
            }else if(tipoConsulta.equals("sql3")){
                //excel
                new File(rutaDefinitivaExcel).mkdirs();
                //logs
                logFolderPath = logFolderPathDefinitiva;
                // Creamos la carpeta de logs si no existe
                new File(logFolderPath).mkdirs();
                FileHandler fileHandler = new FileHandler(logFolderPath+"/logs_"+fechaActual.format(formato)+".txt");
                SimpleFormatter formatoLogs = new SimpleFormatter();
                fileHandler.setFormatter(formatoLogs);
                logger.addHandler(fileHandler);
                logger.setLevel(java.util.logging.Level.ALL);

                //conexion a la base de datos
                conn = DriverManager.getConnection(url, user, password);
                stmt = conn.createStatement();
                //ejecutar consultas
                rs = stmt.executeQuery(parametroConsulta);

                //se valida que exista el archivo
                File excelFile = new File(rutaDelExcel);
                if (excelFile.exists()) {
                    String acumulador2 = fechaActual.format(formato);
                    System.out.println("se crea excel con nombre: "+acumulador2);
                    logger.info("Se ejecuta ROBOT\nse genera el registro de datos\n" +
                            "se crea excel con nombre: datos_v"+acumulador2+"\n" +
                            "ruta del archivo guardado: "+rutaDefinitivaExcel+"\n" +
                            "se termina el proceso");
                    writeHeaderLine(sheet);
                    writeDataLines(rs, sheet);
                    FileOutputStream outputStream = new FileOutputStream(rutaDefinitivaExcel+"/datos_v"+acumulador2+".xlsx");
                    workbook.write(outputStream);
                } else {
                    String acumulador3 = fechaActual.format(formato);
                    System.out.println("se crea el primer documento: "+acumulador3);
                    logger.info("Segenera el registro de datos\n" +
                            "se crea excel con nombre: datos_v_1_"+acumulador3+"\n" +
                            "ruta del archivo guardado: "+rutaDefinitivaExcel+"\n" +
                            "se termina el proceso");
                    writeHeaderLine(sheet);
                    writeDataLines(rs, sheet);
                    FileOutputStream outputStream = new FileOutputStream(rutaDefinitivaExcel+"/datos_v_1_"+acumulador3+".xlsx");
                    workbook.write(outputStream);
                }
                // Cerrar la conexión
                rs.close();
                stmt.close();
                workbook.close();
                conn.close();
            }else if(tipoConsulta.equals("sql4")){
                //excel
                new File(rutaDefinitivaExcel).mkdirs();
                //logs
                logFolderPath = logFolderPathDefinitiva;
                // Creamos la carpeta de logs si no existe
                new File(logFolderPath).mkdirs();
                FileHandler fileHandler = new FileHandler(logFolderPath+"/logs_"+fechaActual.format(formato)+".txt");
                SimpleFormatter formatoLogs = new SimpleFormatter();
                fileHandler.setFormatter(formatoLogs);
                logger.addHandler(fileHandler);
                logger.setLevel(java.util.logging.Level.ALL);

                //conexion a la base de datos
                conn = DriverManager.getConnection(url, user, password);
                stmt = conn.createStatement();
                //ejecutar consultas
                rs = stmt.executeQuery(parametroConsulta);

                //se valida que exista el archivo
                File excelFile = new File(rutaDelExcel);
                if (excelFile.exists()) {
                    String acumulador2 = fechaActual.format(formato);
                    System.out.println("se crea excel con nombre: "+acumulador2);
                    logger.info("Se ejecuta ROBOT\nse genera el registro de datos\n" +
                            "se crea excel con nombre: datos_v"+acumulador2+"\n" +
                            "ruta del archivo guardado: "+rutaDefinitivaExcel+"\n" +
                            "se termina el proceso");
                    writeHeaderLine(sheet);
                    writeDataLines(rs, sheet);
                    FileOutputStream outputStream = new FileOutputStream(rutaDefinitivaExcel+"/datos_v"+acumulador2+".xlsx");
                    workbook.write(outputStream);
                } else {
                    String acumulador3 = fechaActual.format(formato);
                    System.out.println("se crea el primer documento: "+acumulador3);
                    logger.info("Segenera el registro de datos\n" +
                            "se crea excel con nombre: datos_v_1_"+acumulador3+"\n" +
                            "ruta del archivo guardado: "+rutaDefinitivaExcel+"\n" +
                            "se termina el proceso");
                    writeHeaderLine(sheet);
                    writeDataLines(rs, sheet);
                    FileOutputStream outputStream = new FileOutputStream(rutaDefinitivaExcel+"/datos_v_1_"+acumulador3+".xlsx");
                    workbook.write(outputStream);
                }
                // Cerrar la conexión
                rs.close();
                stmt.close();
                workbook.close();
                conn.close();
            }else if(tipoConsulta.equals("sql5")){
                //excel
                new File(rutaDefinitivaExcel).mkdirs();
                //logs
                logFolderPath = logFolderPathDefinitiva;
                // Creamos la carpeta de logs si no existe
                new File(logFolderPath).mkdirs();
                FileHandler fileHandler = new FileHandler(logFolderPath+"/logs_"+fechaActual.format(formato)+".txt");
                SimpleFormatter formatoLogs = new SimpleFormatter();
                fileHandler.setFormatter(formatoLogs);
                logger.addHandler(fileHandler);
                logger.setLevel(java.util.logging.Level.ALL);

                //conexion a la base de datos
                conn = DriverManager.getConnection(url, user, password);
                stmt = conn.createStatement();
                //ejecutar consultas
                rs = stmt.executeQuery(parametroConsulta);

                //se valida que exista el archivo
                File excelFile = new File(rutaDelExcel);
                if (excelFile.exists()) {
                    String acumulador2 = fechaActual.format(formato);
                    System.out.println("se crea excel con nombre: "+acumulador2);
                    logger.info("Se ejecuta ROBOT\nse genera el registro de datos\n" +
                            "se crea excel con nombre: datos_v"+acumulador2+"\n" +
                            "ruta del archivo guardado: "+rutaDefinitivaExcel+"\n" +
                            "se termina el proceso");
                    writeHeaderLine(sheet);
                    writeDataLines(rs, sheet);
                    FileOutputStream outputStream = new FileOutputStream(rutaDefinitivaExcel+"/datos_v"+acumulador2+".xlsx");
                    workbook.write(outputStream);
                } else {
                    String acumulador3 = fechaActual.format(formato);
                    System.out.println("se crea el primer documento: "+acumulador3);
                    logger.info("Segenera el registro de datos\n" +
                            "se crea excel con nombre: datos_v_1_"+acumulador3+"\n" +
                            "ruta del archivo guardado: "+rutaDefinitivaExcel+"\n" +
                            "se termina el proceso");
                    writeHeaderLine(sheet);
                    writeDataLines(rs, sheet);
                    FileOutputStream outputStream = new FileOutputStream(rutaDefinitivaExcel+"/datos_v_1_"+acumulador3+".xlsx");
                    workbook.write(outputStream);
                }
                // Cerrar la conexión
                rs.close();
                stmt.close();
                workbook.close();
                conn.close();
            }else if(tipoConsulta.equals("sql6")){
                //excel
                new File(rutaDefinitivaExcel).mkdirs();
                //logs
                logFolderPath = logFolderPathDefinitiva;
                // Creamos la carpeta de logs si no existe
                new File(logFolderPath).mkdirs();
                FileHandler fileHandler = new FileHandler(logFolderPath+"/logs_"+fechaActual.format(formato)+".txt");
                SimpleFormatter formatoLogs = new SimpleFormatter();
                fileHandler.setFormatter(formatoLogs);
                logger.addHandler(fileHandler);
                logger.setLevel(java.util.logging.Level.ALL);

                //conexion a la base de datos
                conn = DriverManager.getConnection(url, user, password);
                stmt = conn.createStatement();
                //ejecutar consultas
                rs = stmt.executeQuery(parametroConsulta);

                //se valida que exista el archivo
                File excelFile = new File(rutaDelExcel);
                if (excelFile.exists()) {
                    String acumulador2 = fechaActual.format(formato);
                    System.out.println("se crea excel con nombre: "+acumulador2);
                    logger.info("Se ejecuta ROBOT\nse genera el registro de datos\n" +
                            "se crea excel con nombre: datos_v"+acumulador2+"\n" +
                            "ruta del archivo guardado: "+rutaDefinitivaExcel+"\n" +
                            "se termina el proceso");
                    writeHeaderLine(sheet);
                    writeDataLines(rs, sheet);
                    FileOutputStream outputStream = new FileOutputStream(rutaDefinitivaExcel+"/datos_v"+acumulador2+".xlsx");
                    workbook.write(outputStream);
                } else {
                    String acumulador3 = fechaActual.format(formato);
                    System.out.println("se crea el primer documento: "+acumulador3);
                    logger.info("Segenera el registro de datos\n" +
                            "se crea excel con nombre: datos_v_1_"+acumulador3+"\n" +
                            "ruta del archivo guardado: "+rutaDefinitivaExcel+"\n" +
                            "se termina el proceso");
                    writeHeaderLine(sheet);
                    writeDataLines(rs, sheet);
                    FileOutputStream outputStream = new FileOutputStream(rutaDefinitivaExcel+"/datos_v_1_"+acumulador3+".xlsx");
                    workbook.write(outputStream);
                }
                // Cerrar la conexión
                rs.close();
                stmt.close();
                workbook.close();
                conn.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            logger.severe("error:" +e);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private static void writeHeaderLine(XSSFSheet sheet) {
        Row headerRow = sheet.createRow(0);

        Cell headerCell = headerRow.createCell(0);
        headerCell.setCellValue("ID");

        headerCell = headerRow.createCell(1);
        headerCell.setCellValue("Nombre");

        headerCell = headerRow.createCell(2);
        headerCell.setCellValue("País");

        headerCell = headerRow.createCell(3);
        headerCell.setCellValue("Población");
    }

    private static void writeDataLines(ResultSet result, XSSFSheet sheet) throws Exception {
        int rowNumber = 1;

        while (result.next()) {
            int id = result.getInt("ID");
            String name = result.getString("Name");
            String country = result.getString("CountryCode");
            int population = result.getInt("Population");

            Row row = sheet.createRow(rowNumber++);

            int columnNumber = 0;

            Cell cell = row.createCell(columnNumber++);
            cell.setCellValue(id);

            cell = row.createCell(columnNumber++);
            cell.setCellValue(name);

            cell = row.createCell(columnNumber++);
            cell.setCellValue(country);

            cell = row.createCell(columnNumber);
            cell.setCellValue(population);
        }
    }

}
