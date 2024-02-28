package org.example.Controller;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.example.Model.DatosModel;

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

public class ControllerMysql {
    //ruta del archivo
    String rutaDelExcel;
    String rutaDefinitivaExcel;
    String logFolderPath;
    String logFolderPathDefinitiva;
    String nombrePestana;
    Properties properties = new Properties();
    DatosModel datosModel = new DatosModel();
    LocalDateTime fechaActual = LocalDateTime.now();
    DateTimeFormatter formato = DateTimeFormatter.ofPattern("yyyy-MM-dd HH-mm-ss");
    //String ruta = "C:\\Users\\SSSA\\Documents\\NetBeansProjects\\RobotDescargaExcelDb\\Config\\Config.properties";
    String ruta = datosModel.getRuta();
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
                    writeHeaderLine(sheet,tipoConsulta);
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
                    writeHeaderLine(sheet,tipoConsulta); //consulta 1
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
                    writeHeaderLine(sheet,tipoConsulta);
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
                    writeHeaderLine(sheet,tipoConsulta);
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
                    writeHeaderLine(sheet,tipoConsulta);
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
                    writeHeaderLine(sheet,tipoConsulta);
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
                    writeHeaderLine(sheet,tipoConsulta);
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
                    writeHeaderLine(sheet,tipoConsulta);
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
                    writeHeaderLine(sheet,tipoConsulta);
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
                    writeHeaderLine(sheet,tipoConsulta);
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
                    writeHeaderLine(sheet,tipoConsulta);
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
                    writeHeaderLine(sheet,tipoConsulta);
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

    private static void writeHeaderLine(XSSFSheet sheet, String tipoRow) {
        Row headerRow = sheet.createRow(0);
        Cell headerCell = headerRow.createCell(0);
        switch (tipoRow) {
            case "sql1":
                headerCell.setCellValue("Item0");
                headerCell = headerRow.createCell(1);
                headerCell.setCellValue("Item1");
                headerCell = headerRow.createCell(2);
                headerCell.setCellValue("Item2");
                headerCell = headerRow.createCell(3);
                headerCell.setCellValue("Item3");
                headerCell = headerRow.createCell(4);
                headerCell.setCellValue("Item4");
                break;
            case "sql2":
                headerCell.setCellValue("Item0");
                headerCell = headerRow.createCell(1);
                headerCell.setCellValue("Item1");
                headerCell = headerRow.createCell(2);
                headerCell.setCellValue("Item2");
                headerCell = headerRow.createCell(3);
                headerCell.setCellValue("Item3");
                headerCell = headerRow.createCell(4);
                headerCell.setCellValue("Item4");
                headerCell = headerRow.createCell(5);
                headerCell.setCellValue("Item5");
                headerCell = headerRow.createCell(6);
                headerCell.setCellValue("Item6");
                headerCell = headerRow.createCell(7);
                headerCell.setCellValue("Item7");
                headerCell = headerRow.createCell(8);
                headerCell.setCellValue("Item8");
                headerCell = headerRow.createCell(9);
                headerCell.setCellValue("Item9");
                headerCell = headerRow.createCell(10);
                headerCell.setCellValue("Item10");
                headerCell = headerRow.createCell(11);
                headerCell.setCellValue("Item11");
                headerCell = headerRow.createCell(12);
                headerCell.setCellValue("Item12");
                headerCell = headerRow.createCell(13);
                headerCell.setCellValue("Item13");
                headerCell = headerRow.createCell(14);
                headerCell.setCellValue("Item14");
                headerCell = headerRow.createCell(15);
                headerCell.setCellValue("Item15");
                headerCell = headerRow.createCell(16);
                headerCell.setCellValue("Item16");
                headerCell = headerRow.createCell(17);
                headerCell.setCellValue("Item17");
                headerCell = headerRow.createCell(18);
                headerCell.setCellValue("Item18");
                headerCell = headerRow.createCell(19);
                headerCell.setCellValue("Item19");
                headerCell = headerRow.createCell(20);
                headerCell.setCellValue("Item20");
                headerCell = headerRow.createCell(21);
                headerCell.setCellValue("Item21");
                headerCell = headerRow.createCell(22);
                headerCell.setCellValue("Item22");
                headerCell = headerRow.createCell(23);
                headerCell.setCellValue("Item23");
                headerCell = headerRow.createCell(24);
                headerCell.setCellValue("Item24");
                headerCell = headerRow.createCell(25);
                headerCell.setCellValue("Item25");
                headerCell = headerRow.createCell(26);
                headerCell.setCellValue("Item26");
                headerCell = headerRow.createCell(27);
                headerCell.setCellValue("Item27");
                headerCell = headerRow.createCell(28);
                headerCell.setCellValue("Item28");
                headerCell = headerRow.createCell(29);
                headerCell.setCellValue("Item29");
                break;
            case "sql3":
                headerCell.setCellValue("Item0");
                headerCell = headerRow.createCell(1);
                headerCell.setCellValue("Item1");
                headerCell = headerRow.createCell(2);
                headerCell.setCellValue("Item2");
                headerCell = headerRow.createCell(3);
                headerCell.setCellValue("Item3");
                headerCell = headerRow.createCell(4);
                headerCell.setCellValue("Item4");
                headerCell = headerRow.createCell(5);
                headerCell.setCellValue("Item5");
                headerCell = headerRow.createCell(6);
                headerCell.setCellValue("Item6");
                headerCell = headerRow.createCell(7);
                headerCell.setCellValue("Item7");
                headerCell = headerRow.createCell(8);
                headerCell.setCellValue("Item8");
                headerCell = headerRow.createCell(9);
                headerCell.setCellValue("Item9");
                headerCell = headerRow.createCell(10);
                headerCell.setCellValue("Item10");
                headerCell = headerRow.createCell(11);
                headerCell.setCellValue("Item11");
                headerCell = headerRow.createCell(12);
                headerCell.setCellValue("Item12");
                headerCell = headerRow.createCell(13);
                headerCell.setCellValue("Item13");
                headerCell = headerRow.createCell(14);
                headerCell.setCellValue("Item14");
                headerCell = headerRow.createCell(15);
                headerCell.setCellValue("Item15");
                headerCell = headerRow.createCell(16);
                headerCell.setCellValue("Item16");
                headerCell = headerRow.createCell(17);
                headerCell.setCellValue("Item17");
                headerCell = headerRow.createCell(18);
                headerCell.setCellValue("Item18");
                headerCell = headerRow.createCell(19);
                headerCell.setCellValue("Item19");
                headerCell = headerRow.createCell(20);
                headerCell.setCellValue("Item20");
                headerCell = headerRow.createCell(21);
                headerCell.setCellValue("Item21");
                break;
            case "sql4":
                headerCell.setCellValue("Item0");
                headerCell = headerRow.createCell(1);
                headerCell.setCellValue("Item1");
                headerCell = headerRow.createCell(2);
                headerCell.setCellValue("Item2");
                headerCell = headerRow.createCell(3);
                headerCell.setCellValue("Item3");
                headerCell = headerRow.createCell(4);
                headerCell.setCellValue("Item4");
                headerCell = headerRow.createCell(5);
                headerCell.setCellValue("Item5");
                headerCell = headerRow.createCell(6);
                headerCell.setCellValue("Item6");
                headerCell = headerRow.createCell(7);
                headerCell.setCellValue("Item7");
                headerCell = headerRow.createCell(8);
                headerCell.setCellValue("Item8");
                headerCell = headerRow.createCell(9);
                headerCell.setCellValue("Item9");
                headerCell = headerRow.createCell(10);
                headerCell.setCellValue("Item10");
                headerCell = headerRow.createCell(11);
                headerCell.setCellValue("Item11");
                headerCell = headerRow.createCell(12);
                headerCell.setCellValue("Item12");
                headerCell = headerRow.createCell(13);
                headerCell.setCellValue("Item13");
                headerCell = headerRow.createCell(14);
                headerCell.setCellValue("Item14");
                headerCell = headerRow.createCell(15);
                headerCell.setCellValue("Item15");
                headerCell = headerRow.createCell(16);
                headerCell.setCellValue("Item16");
                headerCell = headerRow.createCell(17);
                headerCell.setCellValue("Item17");
                headerCell = headerRow.createCell(18);
                headerCell.setCellValue("Item18");
                headerCell = headerRow.createCell(19);
                headerCell.setCellValue("Item19");
                headerCell = headerRow.createCell(20);
                headerCell.setCellValue("Item20");
                headerCell = headerRow.createCell(21);
                headerCell.setCellValue("Item21");
                break;
            case "sql5":
                headerCell.setCellValue("Item0");
                headerCell = headerRow.createCell(1);
                headerCell.setCellValue("Item1");
                headerCell = headerRow.createCell(2);
                headerCell.setCellValue("Item2");
                headerCell = headerRow.createCell(3);
                headerCell.setCellValue("Item3");
                headerCell = headerRow.createCell(4);
                headerCell.setCellValue("Item4");
                headerCell = headerRow.createCell(5);
                headerCell.setCellValue("Item5");
                headerCell = headerRow.createCell(6);
                headerCell.setCellValue("Item6");
                headerCell = headerRow.createCell(7);
                headerCell.setCellValue("Item7");
                headerCell = headerRow.createCell(8);
                headerCell.setCellValue("Item8");
                headerCell = headerRow.createCell(9);
                headerCell.setCellValue("Item9");
                break;
            case "sql6":
                headerCell.setCellValue("ID");

                headerCell = headerRow.createCell(1);
                headerCell.setCellValue("Item1");

                headerCell = headerRow.createCell(2);
                headerCell.setCellValue("Item2");

                headerCell = headerRow.createCell(3);
                headerCell.setCellValue("Item4");
                break;
            default:
                System.out.println("no hay lugar para el excel");
        }
    }

    private static void writeDataLines(ResultSet result, XSSFSheet sheet) throws Exception {
        int rowNumber = 1;

        while (result.next()) {
            int id = result.getInt("ID");
            String name = result.getString("Name");
            String country = result.getString("CountryCode");
            int population = result.getInt("Population");

            int columnNumber = 0;

            Row row = sheet.createRow(rowNumber++);
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
