package org.example.Controller;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.example.Model.DatosModel;
import org.example.Model.ModelDatosExcel;

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
                stmt = conn.prepareStatement(parametroConsulta);
                rs = stmt.executeQuery(parametroConsulta);

                //se valida que exista el archivo
                File excelFile = new File(rutaDelExcel);
                if (excelFile.exists()) {
                    String acumulador2 = "hora_"+fechaActual.format(formato);
                    System.out.println("se crea excel con nombre: "+acumulador2);
                    logger.info("Se ejecuta ROBOT\nse genera el registro de datos\n" +
                            "se crea excel con nombre: datos_v"+acumulador2+"\n" +
                            "ruta del archivo guardado: "+rutaDefinitivaExcel+"\n" +
                            "se termina el proceso");
                    writeHeaderLine(sheet,tipoConsulta);
                    writeDataLines(rs, sheet, tipoConsulta);
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
                    writeDataLines(rs, sheet, tipoConsulta);
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

                //ejecutar consultas
                conn = DriverManager.getConnection(url, user, password);
                stmt = conn.prepareStatement(parametroConsulta);
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
                    writeDataLines(rs, sheet, tipoConsulta);
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
                    writeDataLines(rs, sheet, tipoConsulta);
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

                //ejecutar consultas
                conn = DriverManager.getConnection(url, user, password);
                stmt = conn.prepareStatement(parametroConsulta);
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
                    writeDataLines(rs, sheet, tipoConsulta);
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
                    writeDataLines(rs, sheet, tipoConsulta);
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

                //ejecutar consultas
                conn = DriverManager.getConnection(url, user, password);
                stmt = conn.prepareStatement(parametroConsulta);
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
                    writeDataLines(rs, sheet, tipoConsulta);
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
                    writeDataLines(rs, sheet, tipoConsulta);
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

                //ejecutar consultas
                conn = DriverManager.getConnection(url, user, password);
                stmt = conn.prepareStatement(parametroConsulta);
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
                    writeDataLines(rs, sheet, tipoConsulta);
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
                    writeDataLines(rs, sheet, tipoConsulta);
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

                //ejecutar consultas
                conn = DriverManager.getConnection(url, user, password);
                stmt = conn.prepareStatement(parametroConsulta);
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
                    writeDataLines(rs, sheet, tipoConsulta);
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
                    writeDataLines(rs, sheet, tipoConsulta);
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
                headerCell.setCellValue("IDDOCUMENTO");
                headerCell = headerRow.createCell(1);
                headerCell.setCellValue("FECDOCUMENTO");
                headerCell = headerRow.createCell(2);
                headerCell.setCellValue("DESDOCUMENTO");
                headerCell = headerRow.createCell(3);
                headerCell.setCellValue("NOMENTIDAD");
                break;
            case "sql2":
                headerCell.setCellValue("FECDOCUMENTO");
                headerCell = headerRow.createCell(1);
                headerCell.setCellValue("DEPENDENCIA");
                headerCell = headerRow.createCell(2);
                headerCell.setCellValue("TEMA");
                headerCell = headerRow.createCell(3);
                headerCell.setCellValue("CLASE_SOLICITUD");
                headerCell = headerRow.createCell(4);
                headerCell.setCellValue("IDDOCUMENTO");
                headerCell = headerRow.createCell(5);
                headerCell.setCellValue("SOLICITANTE");
                headerCell = headerRow.createCell(6);
                headerCell.setCellValue("INFO_ADI_SOL");
                headerCell = headerRow.createCell(7);
                headerCell.setCellValue("NOMBRE_SOL");
                headerCell = headerRow.createCell(8);
                headerCell.setCellValue("DIRIGIDO_A");
                headerCell = headerRow.createCell(9);
                headerCell.setCellValue("NOMBRE_SERVIDOR");
                headerCell = headerRow.createCell(10);
                headerCell.setCellValue("TIEMPOS");
                headerCell = headerRow.createCell(11);
                headerCell.setCellValue("FORMATO_DIAS_HORAS");
                headerCell = headerRow.createCell(12);
                headerCell.setCellValue("FECENTRADA");
                headerCell = headerRow.createCell(13);
                headerCell.setCellValue("NMBRE_USRIO");
                headerCell = headerRow.createCell(14);
                headerCell.setCellValue("ESTDOCUMENTO");
                headerCell = headerRow.createCell(15);
                headerCell.setCellValue("FECSALIDA");
                headerCell = headerRow.createCell(16);
                headerCell.setCellValue("FECHA_RESPUESTA");
                headerCell = headerRow.createCell(17);
                headerCell.setCellValue("RESPUESTA");
                headerCell = headerRow.createCell(18);
                headerCell.setCellValue("IDUSUARIO_RAD");
                headerCell = headerRow.createCell(19);
                headerCell.setCellValue("CANTIDAD");
                headerCell = headerRow.createCell(20);
                headerCell.setCellValue("CEDULA_CIUDADANO");
                headerCell = headerRow.createCell(21);
                headerCell.setCellValue("NOMBRE_CIUDADANO");
                headerCell = headerRow.createCell(22);
                headerCell.setCellValue("TELEFONO");
                headerCell = headerRow.createCell(23);
                headerCell.setCellValue("PAIS");
                headerCell = headerRow.createCell(24);
                headerCell.setCellValue("DEPARTAMENTO");
                headerCell = headerRow.createCell(25);
                headerCell.setCellValue("CIUDAD");
                headerCell = headerRow.createCell(26);
                headerCell.setCellValue("DIRECCION");
                headerCell = headerRow.createCell(27);
                headerCell.setCellValue("DIRECCION_DE_HECHO");
                headerCell = headerRow.createCell(28);
                headerCell.setCellValue("DIRECCION_COMPLETA_DEL_PREDIO");
                break;
            case "sql3":
                headerCell.setCellValue("FECDOCUMENTO");
                headerCell = headerRow.createCell(1);
                headerCell.setCellValue("DEPENDENCIA");
                headerCell = headerRow.createCell(2);
                headerCell.setCellValue("TEMA");
                headerCell = headerRow.createCell(3);
                headerCell.setCellValue("TIPO_SOLICITUD");
                headerCell = headerRow.createCell(4);
                headerCell.setCellValue("TIEMPOS");
                headerCell = headerRow.createCell(5);
                headerCell.setCellValue("FORMATO_DIAS_HORAS");
                headerCell = headerRow.createCell(6);
                headerCell.setCellValue("INFORMATIVA");
                headerCell = headerRow.createCell(7);
                headerCell.setCellValue("JUSTIFICACION_RESPUESTA");
                headerCell = headerRow.createCell(8);
                headerCell.setCellValue("INFORMACION_ADICIONAL");
                headerCell = headerRow.createCell(9);
                headerCell.setCellValue("RADICADO");
                headerCell = headerRow.createCell(10);
                headerCell.setCellValue("FECENTRADA");
                headerCell = headerRow.createCell(11);
                headerCell.setCellValue("NMBRE_USRIO");
                headerCell = headerRow.createCell(12);
                headerCell.setCellValue("ESTDOCUMENTO");
                headerCell = headerRow.createCell(13);
                headerCell.setCellValue("FECSALIDA");
                headerCell = headerRow.createCell(14);
                headerCell.setCellValue("IDUSUARIO_RAD");
                headerCell = headerRow.createCell(15);
                headerCell.setCellValue("FECHA_RESPUESTA");
                headerCell = headerRow.createCell(16);
                headerCell.setCellValue("RESPUESTA");
                headerCell = headerRow.createCell(17);
                headerCell.setCellValue("IDUSUARIO_RAD2");
                headerCell = headerRow.createCell(18);
                headerCell.setCellValue("FECHA_RESPUESTA_INICIAL");
                headerCell = headerRow.createCell(19);
                headerCell.setCellValue("RESPUESTA_INICIAL");
                headerCell = headerRow.createCell(20);
                headerCell.setCellValue("IDUSUARIO_RAD_INICIAL");
                break;
            case "sql4":
                headerCell.setCellValue("IDDOCUMENTO");
                headerCell = headerRow.createCell(1);
                headerCell.setCellValue("FECDOCUMENTO");
                headerCell = headerRow.createCell(2);
                headerCell.setCellValue("DESDOCUMENTO");
                headerCell = headerRow.createCell(3);
                headerCell.setCellValue("OBSDOCUMENTO");
                headerCell = headerRow.createCell(4);
                headerCell.setCellValue("NOMRUTA");
                headerCell = headerRow.createCell(5);
                headerCell.setCellValue("FECENTRADA");
                headerCell = headerRow.createCell(6);
                headerCell.setCellValue("FECSALIDA");
                headerCell = headerRow.createCell(7);
                headerCell.setCellValue("VALPASO");
                headerCell = headerRow.createCell(8);
                headerCell.setCellValue("ID_USRIO");
                headerCell = headerRow.createCell(9);
                headerCell.setCellValue("NMBRE_USRIO");
                headerCell = headerRow.createCell(10);
                headerCell.setCellValue("ESTDOCUMENTO");
                headerCell = headerRow.createCell(11);
                headerCell.setCellValue("IDRESPUESTA");
                headerCell = headerRow.createCell(12);
                headerCell.setCellValue("FECHA_EXTERNO");
                headerCell = headerRow.createCell(13);
                headerCell.setCellValue("NOMDEPENDENCIA_AREA");
                headerCell = headerRow.createCell(14);
                headerCell.setCellValue("NOMDEPENDENCIA_DEPENDENCIA");
                headerCell = headerRow.createCell(15);
                headerCell.setCellValue("PAIS");
                headerCell = headerRow.createCell(16);
                headerCell.setCellValue("DEPARTAMENTO");
                headerCell = headerRow.createCell(17);
                headerCell.setCellValue("CIUDAD");
                headerCell = headerRow.createCell(18);
                headerCell.setCellValue("DIRECCION");
                headerCell = headerRow.createCell(19);
                headerCell.setCellValue("DIRECCION_DE_HECHO");
                headerCell = headerRow.createCell(20);
                headerCell.setCellValue("DIRECCION_COMPLETA_DEL_PREDIO");
                break;
            case "sql5":
                headerCell.setCellValue("FECDOCUMENTO");
                headerCell = headerRow.createCell(1);
                headerCell.setCellValue("NOMRUTA");
                headerCell = headerRow.createCell(2);
                headerCell.setCellValue("IDDOCUMENTO");
                headerCell = headerRow.createCell(3);
                headerCell.setCellValue("FECENTRADA");
                headerCell = headerRow.createCell(4);
                headerCell.setCellValue("NMBRE_USRIO");
                headerCell = headerRow.createCell(5);
                headerCell.setCellValue("ESTDOCUMENTO");
                headerCell = headerRow.createCell(6);
                headerCell.setCellValue("FECSALIDA");
                headerCell = headerRow.createCell(7);
                headerCell.setCellValue("RESPUESTA");
                headerCell = headerRow.createCell(8);
                headerCell.setCellValue("FECHA_RESPUESTA");
                break;
            case "sql6":
                headerCell.setCellValue("FECHA_RADICACION");
                headerCell = headerRow.createCell(1);
                headerCell.setCellValue("ASUNTO");
                headerCell = headerRow.createCell(2);
                headerCell.setCellValue("OBSERVACION_1");
                headerCell = headerRow.createCell(3);
                headerCell.setCellValue("OBSERVACION_2");
                headerCell = headerRow.createCell(4);
                headerCell.setCellValue("IDDOCUMENTO");
                headerCell = headerRow.createCell(5);
                headerCell.setCellValue("FECHA_ENTRADA");
                headerCell = headerRow.createCell(6);
                headerCell.setCellValue("ULTIMO_USUARIO");
                headerCell = headerRow.createCell(7);
                headerCell.setCellValue("ESTADO");
                headerCell = headerRow.createCell(8);
                headerCell.setCellValue("FECHA_EVACUADO");
                headerCell = headerRow.createCell(9);
                headerCell.setCellValue("FECHA_RESPUESTA");
                headerCell = headerRow.createCell(10);
                headerCell.setCellValue("RESPUESTA");
                headerCell = headerRow.createCell(11);
                headerCell.setCellValue("IDUSUARIO_RAD");
                headerCell = headerRow.createCell(12);
                headerCell.setCellValue("IDUSUARIO_RADICADOR");
                break;
            default:
                System.out.println("no hay lugar para el excel");
        }
    }

    private static void writeDataLines(ResultSet result, XSSFSheet sheet, String tipoRow) throws Exception {
        int rowNumber = 1;

        switch (tipoRow){
            case "sql1":
                while (result.next()) {
                    String id = result.getString("IDDOCUMENTO");
                    String fecha = result.getString("FECDOCUMENTO");
                    String descripcion = result.getString("DESDOCUMENTO");
                    String actividad = result.getString("NOMENTIDAD");
                    int columnNumber = 0;

                    Row row = sheet.createRow(rowNumber++);
                    Cell cell = row.createCell(columnNumber++);
                    cell.setCellValue(id  == null ? "No registra" : id);

                    cell = row.createCell(columnNumber++);
                    cell.setCellValue(fecha  == null ? "No registra" : fecha);

                    cell = row.createCell(columnNumber++);
                    cell.setCellValue(descripcion  == null ? "No registra" : descripcion);

                    cell = row.createCell(columnNumber);
                    cell.setCellValue(actividad  == null ? "No registra" : actividad);
                }
                break;
            case "sql2":
                while (result.next()) {
                    String FECDOCUMENTO = result.getString("FECDOCUMENTO");
                    String DEPENDENCIA = result.getString("DEPENDENCIA");
                    String TEMA = result.getString("TEMA");
                    String CLASE_SOLICITUD = result.getString("CLASE_SOLICITUD");
                    String IDDOCUMENTO = result.getString("IDDOCUMENTO");
                    String SOLICITANTE = result.getString("SOLICITANTE");
                    String INFO_ADI_SOL = result.getString("INFO_ADI_SOL");
                    String NOMBRE_SOL = result.getString("NOMBRE_SOL");
                    String DIRIGIDO_A = result.getString("DIRIGIDO_A");
                    String NOMBRE_SERVIDOR = result.getString("NOMBRE_SERVIDOR");
                    String TIEMPOS = result.getString("TIEMPOS");
                    String FORMATO_DIAS_HORAS = result.getString("FORMATO_DIAS_HORAS");
                    String FECENTRADA = result.getString("FECENTRADA");
                    String NMBRE_USRIO = result.getString("NMBRE_USRIO");
                    String ESTDOCUMENTO = result.getString("ESTDOCUMENTO");
                    String FECSALIDA = result.getString("FECSALIDA");
                    String FECHA_RESPUESTA = result.getString("FECHA_RESPUESTA");
                    String RESPUESTA = result.getString("RESPUESTA");
                    String IDUSUARIO_RAD = result.getString("IDUSUARIO_RAD");
                    String CANTIDAD = result.getString("CANTIDAD");
                    String CEDULA_CIUDADANO = result.getString("CEDULA_CIUDADANO");
                    String NOMBRE_CIUDADANO = result.getString("NOMBRE_CIUDADANO");
                    String TELEFONO = result.getString("TELEFONO");
                    String PAIS = result.getString("PAIS");
                    String DEPARTAMENTO = result.getString("DEPARTAMENTO");
                    String CIUDAD = result.getString("CIUDAD");
                    String DIRECCION = result.getString("DIRECCION");
                    String DIRECCION_DE_HECHO = result.getString("DIRECCION_DE_HECHO");
                    String DIRECCION_COMPLETA_DEL_PREDIO = result.getString("DIRECCION_COMPLETA_DEL_PREDIO");
                    int columnNumber = 0;

                    Row row = sheet.createRow(rowNumber++);
                    Cell cell = row.createCell(columnNumber++);
                    cell.setCellValue(FECDOCUMENTO == null ? "No registra" : FECDOCUMENTO);

                    cell = row.createCell(columnNumber++);
                    cell.setCellValue(DEPENDENCIA == null ? "No registra" : DEPENDENCIA);

                    cell = row.createCell(columnNumber++);
                    cell.setCellValue(TEMA == null ? "No registra" : TEMA);

                    cell = row.createCell(columnNumber++);
                    cell.setCellValue(CLASE_SOLICITUD == null ? "No registra" : CLASE_SOLICITUD);

                    cell = row.createCell(columnNumber++);
                    cell.setCellValue(IDDOCUMENTO == null ? "No registra" : IDDOCUMENTO);

                    cell = row.createCell(columnNumber++);
                    cell.setCellValue(SOLICITANTE == null ? "No registra" : SOLICITANTE);

                    cell = row.createCell(columnNumber++);
                    cell.setCellValue(DIRIGIDO_A == null ? "No registra" : DIRIGIDO_A);

                    cell = row.createCell(columnNumber++);
                    cell.setCellValue(NOMBRE_SOL == null ? "No registra" : NOMBRE_SOL);

                    cell = row.createCell(columnNumber++);
                    cell.setCellValue(INFO_ADI_SOL == null ? "No registra" : INFO_ADI_SOL);

                    cell = row.createCell(columnNumber++);
                    cell.setCellValue(NOMBRE_SERVIDOR == null ? "No registra" : NOMBRE_SERVIDOR);

                    cell = row.createCell(columnNumber++);
                    cell.setCellValue(TIEMPOS == null ? "No registra" : TIEMPOS);

                    cell = row.createCell(columnNumber++);
                    cell.setCellValue(FORMATO_DIAS_HORAS == null ? "No registra" : FORMATO_DIAS_HORAS);

                    cell = row.createCell(columnNumber++);
                    cell.setCellValue(FECENTRADA == null ? "No registra" : FECENTRADA);

                    cell = row.createCell(columnNumber++);
                    cell.setCellValue(NMBRE_USRIO == null ? "No registra" : NMBRE_USRIO);

                    cell = row.createCell(columnNumber++);
                    cell.setCellValue(ESTDOCUMENTO == null ? "No registra" : ESTDOCUMENTO);

                    cell = row.createCell(columnNumber++);
                    cell.setCellValue(FECSALIDA == null ? "No registra" : FECSALIDA);

                    cell = row.createCell(columnNumber++);
                    cell.setCellValue(FECHA_RESPUESTA == null ? "No registra" : FECHA_RESPUESTA);

                    cell = row.createCell(columnNumber++);
                    cell.setCellValue(RESPUESTA == null ? "No registra" : RESPUESTA);

                    cell = row.createCell(columnNumber++);
                    cell.setCellValue(IDUSUARIO_RAD == null ? "No registra" : IDUSUARIO_RAD);

                    cell = row.createCell(columnNumber++);
                    cell.setCellValue(CANTIDAD == null ? "No registra" : CANTIDAD);

                    cell = row.createCell(columnNumber++);
                    cell.setCellValue(CEDULA_CIUDADANO == null ? "No registra" : CLASE_SOLICITUD);

                    cell = row.createCell(columnNumber++);
                    cell.setCellValue(NOMBRE_CIUDADANO == null ? "No registra" : NOMBRE_CIUDADANO);

                    cell = row.createCell(columnNumber++);
                    cell.setCellValue(TELEFONO == null ? "No registra" : TELEFONO);

                    cell = row.createCell(columnNumber++);
                    cell.setCellValue(PAIS == null ? "No registra" : PAIS);

                    cell = row.createCell(columnNumber++);
                    cell.setCellValue(DEPARTAMENTO == null ? "No registra" : DEPARTAMENTO);

                    cell = row.createCell(columnNumber++);
                    cell.setCellValue(CIUDAD == null ? "No registra" : CIUDAD);

                    cell = row.createCell(columnNumber++);
                    cell.setCellValue(DIRECCION == null ? "No registra" : DIRECCION);

                    cell = row.createCell(columnNumber++);
                    cell.setCellValue(DIRECCION_DE_HECHO == null ? "No registra" : DIRECCION_DE_HECHO);

                    cell = row.createCell(columnNumber);
                    cell.setCellValue(DIRECCION_COMPLETA_DEL_PREDIO == null ? "No registra" : DIRECCION_COMPLETA_DEL_PREDIO);
                }
                break;
            case "sql3":
                while (result.next()) {
                    String FECDOCUMENTO = result.getString("FECDOCUMENTO");
                    String DEPENDENCIA = result.getString("DEPENDENCIA");
                    String TEMA = result.getString("TEMA");
                    String TIPO_SOLICITUD = result.getString("TIPO_SOLICITUD");
                    String TIMEPO = result.getString("TIEMPOS");
                    String FORMATO_DIAS_HORAS = result.getString("FORMATO_DIAS_HORAS");
                    String INFORMATIVA = result.getString("INFORMATIVA");
                    String JUSTIFICACION_RESPUESTA = result.getString("JUSTIFICACION_RESPUESTA");
                    String INFORMACION_ADICIONAL = result.getString("INFORMACION_ADICIONAL");
                    String RADICADO = result.getString("RADICADO");
                    String FECENTRADA = result.getString("FECENTRADA");
                    String NMBRE_USRIO = result.getString("NMBRE_USRIO");
                    String ESTDOCUMENTO = result.getString("ESTDOCUMENTO");
                    String FECSALIDA = result.getString("FECSALIDA");
                    String FECHA_RESPUESTA = result.getString("FECHA_RESPUESTA");
                    String RESPUESTA = result.getString("RESPUESTA");
                    String IDUSUARIO_RAD = result.getString("IDUSUARIO_RAD");
                    String IDUSUARIO_RAD2 = result.getString("IDUSUARIO_RAD2");
                    String FECHA_RESPUESTA_INICIAL = result.getString("FECHA_RESPUESTA_INICIAL");
                    String RESPUESTA_INICIAL = result.getString("RESPUESTA_INICIAL");
                    String IDUSUARIO_RAD_INICIAL = result.getString("IDUSUARIO_RAD_INICIAL");
                    int columnNumber = 0;

                    Row row = sheet.createRow(rowNumber++);
                    Cell cell = row.createCell(columnNumber++);
                    cell.setCellValue(FECDOCUMENTO == null ? "No registra" : FECDOCUMENTO);

                    cell = row.createCell(columnNumber++);
                    cell.setCellValue(DEPENDENCIA == null ? "No registra" : DEPENDENCIA);

                    cell = row.createCell(columnNumber++);
                    cell.setCellValue(TEMA == null ? "No registra" : TEMA);

                    cell = row.createCell(columnNumber++);
                    cell.setCellValue(TIPO_SOLICITUD == null ? "No registra" : TIPO_SOLICITUD);

                    cell = row.createCell(columnNumber++);
                    cell.setCellValue(TIMEPO == null ? "No registra" : TIMEPO);

                    cell = row.createCell(columnNumber++);
                    cell.setCellValue(INFORMATIVA == null ? "No registra" : INFORMATIVA);

                    cell = row.createCell(columnNumber++);
                    cell.setCellValue(JUSTIFICACION_RESPUESTA == null ? "No registra" :JUSTIFICACION_RESPUESTA);

                    cell = row.createCell(columnNumber++);
                    cell.setCellValue(INFORMACION_ADICIONAL == null ? "No registra" : INFORMACION_ADICIONAL);

                    cell = row.createCell(columnNumber++);
                    cell.setCellValue(RADICADO == null ? "No registra" : RADICADO);

                    cell = row.createCell(columnNumber++);
                    cell.setCellValue(IDUSUARIO_RAD2 == null ? "No registra" : IDUSUARIO_RAD2);

                    cell = row.createCell(columnNumber++);
                    cell.setCellValue(FECHA_RESPUESTA_INICIAL == null ? "No registra" : FECHA_RESPUESTA_INICIAL);

                    cell = row.createCell(columnNumber++);
                    cell.setCellValue(FORMATO_DIAS_HORAS == null ? "No registra" : FORMATO_DIAS_HORAS);

                    cell = row.createCell(columnNumber++);
                    cell.setCellValue(FECENTRADA == null ? "No registra" : FECENTRADA);

                    cell = row.createCell(columnNumber++);
                    cell.setCellValue(NMBRE_USRIO == null ? "No registra" : NMBRE_USRIO);

                    cell = row.createCell(columnNumber++);
                    cell.setCellValue(ESTDOCUMENTO == null ? "No registra" : ESTDOCUMENTO);

                    cell = row.createCell(columnNumber++);
                    cell.setCellValue(FECSALIDA == null ? "No registra" : FECSALIDA);

                    cell = row.createCell(columnNumber++);
                    cell.setCellValue(FECHA_RESPUESTA == null ? "No registra" : FECHA_RESPUESTA);

                    cell = row.createCell(columnNumber++);
                    cell.setCellValue(RESPUESTA == null ? "No registra" : RESPUESTA);

                    cell = row.createCell(columnNumber++);
                    cell.setCellValue(IDUSUARIO_RAD == null ? "No registra" : IDUSUARIO_RAD);

                    cell = row.createCell(columnNumber++);
                    cell.setCellValue(RESPUESTA_INICIAL == null ? "No registra" : RESPUESTA_INICIAL);

                    cell = row.createCell(columnNumber);
                    cell.setCellValue(IDUSUARIO_RAD_INICIAL == null ? "No registra" : IDUSUARIO_RAD_INICIAL);
                }
                break;
            case "sql4":
                while (result.next()) {
                    String IDDOCUMENTO = result.getString("IDDOCUMENTO");
                    String FECDOCUMENTO = result.getString("FECDOCUMENTO");
                    String DESDOCUMENTO = result.getString("DESDOCUMENTO");
                    String OBSDOCUMENTO = result.getString("OBSDOCUMENTO");
                    String NOMRUTA = result.getString("NOMRUTA");
                    String FECENTRADA = result.getString("FECENTRADA");
                    String FECSALIDA = result.getString("FECSALIDA");
                    String VALPASO = result.getString("VALPASO");
                    String ID_USRIO = result.getString("ID_USRIO");
                    String NMBRE_USRIO = result.getString("NMBRE_USRIO");
                    String ESTDOCUMENTO = result.getString("ESTDOCUMENTO");
                    String IDRESPUESTA = result.getString("IDRESPUESTA");
                    String FECHA_EXTERNO = result.getString("FECHA_EXTERNO");
                    String NOMDEPENDENCIA_AREA = result.getString("NOMDEPENDENCIA_AREA");
                    String NOMDEPENDENCIA_DEPENDENCIA = result.getString("NOMDEPENDENCIA_DEPENDENCIA");
                    String PAIS = result.getString("PAIS");
                    String DEPARTAMENTO = result.getString("DEPARTAMENTO");
                    String CIUDAD = result.getString("CIUDAD");
                    String DIRECCION = result.getString("DIRECCION");
                    String DIRECCION_DE_HECHO = result.getString("DIRECCION_DE_HECHO");
                    String DIRECCION_COMPLETA_DEL_PREDIO = result.getString("DIRECCION_COMPLETA_DEL_PREDIO");
                    int columnNumber = 0;

                    Row row = sheet.createRow(rowNumber++);
                    Cell cell = row.createCell(columnNumber++);
                    cell.setCellValue(FECDOCUMENTO == null ? "No registra" : FECDOCUMENTO);

                    cell = row.createCell(columnNumber++);
                    cell.setCellValue(IDDOCUMENTO == null ? "No registra" : IDDOCUMENTO);

                    cell = row.createCell(columnNumber++);
                    cell.setCellValue(DESDOCUMENTO == null ? "No registra" : DESDOCUMENTO);

                    cell = row.createCell(columnNumber++);
                    cell.setCellValue(OBSDOCUMENTO == null ? "No registra" : OBSDOCUMENTO);

                    cell = row.createCell(columnNumber++);
                    cell.setCellValue(NOMRUTA == null ? "No registra" : NOMRUTA);

                    cell = row.createCell(columnNumber++);
                    cell.setCellValue(FECENTRADA == null ? "No registra" : FECENTRADA);

                    cell = row.createCell(columnNumber++);
                    cell.setCellValue(FECSALIDA == null ? "No registra" : FECSALIDA);

                    cell = row.createCell(columnNumber++);
                    cell.setCellValue(VALPASO == null ? "No registra" : VALPASO);

                    cell = row.createCell(columnNumber++);
                    cell.setCellValue(ID_USRIO == null ? "No registra" : ID_USRIO);

                    cell = row.createCell(columnNumber++);
                    cell.setCellValue(NMBRE_USRIO == null ? "No registra" : NMBRE_USRIO);

                    cell = row.createCell(columnNumber++);
                    cell.setCellValue(ESTDOCUMENTO == null ? "No registra" : ESTDOCUMENTO);

                    cell = row.createCell(columnNumber++);
                    cell.setCellValue(IDRESPUESTA == null ? "No registra" : IDRESPUESTA);

                    cell = row.createCell(columnNumber++);
                    cell.setCellValue(FECHA_EXTERNO == null ? "No registra" : FECHA_EXTERNO);

                    cell = row.createCell(columnNumber++);
                    cell.setCellValue(NOMDEPENDENCIA_AREA == null ? "No registra" : NOMDEPENDENCIA_AREA);

                    cell = row.createCell(columnNumber++);
                    cell.setCellValue(NOMDEPENDENCIA_DEPENDENCIA == null ? "No registra" : NOMDEPENDENCIA_DEPENDENCIA);

                    cell = row.createCell(columnNumber++);
                    cell.setCellValue(PAIS == null ? "No registra" : PAIS);

                    cell = row.createCell(columnNumber++);
                    cell.setCellValue(DEPARTAMENTO == null ? "No registra" : DEPARTAMENTO);

                    cell = row.createCell(columnNumber++);
                    cell.setCellValue(CIUDAD == null ? "No registra" : CIUDAD);

                    cell = row.createCell(columnNumber++);
                    cell.setCellValue(DIRECCION == null ? "No registra" : DIRECCION);

                    cell = row.createCell(columnNumber++);
                    cell.setCellValue(DIRECCION_DE_HECHO == null ? "No registra" : DIRECCION_DE_HECHO);

                    cell = row.createCell(columnNumber);
                    cell.setCellValue(DIRECCION_COMPLETA_DEL_PREDIO == null ? "No registra" : DIRECCION_COMPLETA_DEL_PREDIO);
                }
                break;
            case "sql5":
                while (result.next()) {
                    String FECDOCUMENTO = result.getString("FECDOCUMENTO");
                    String NOMRUTA = result.getString("NOMRUTA");
                    String IDDOCUMENTO = result.getString("IDDOCUMENTO");
                    String NMBRE_USRIO = result.getString("NMBRE_USRIO");
                    String ESTDOCUMENTO = result.getString("ESTDOCUMENTO");
                    String FECSALIDA = result.getString("FECSALIDA");
                    String RESPUESTA = result.getString("RESPUESTA");
                    String FECHA_RESPUESTA = result.getString("FECHA_RESPUESTA");
                    String FECENTRADA = result.getString("FECENTRADA");

                    int columnNumber = 0;

                    Row row = sheet.createRow(rowNumber++);
                    Cell cell = row.createCell(columnNumber++);
                    cell.setCellValue(FECDOCUMENTO == null ? "No registra" :FECDOCUMENTO);

                    cell = row.createCell(columnNumber++);
                    cell.setCellValue(NOMRUTA == null ? "No registra" : NOMRUTA);

                    cell = row.createCell(columnNumber++);
                    cell.setCellValue(IDDOCUMENTO == null ? "No registra" : IDDOCUMENTO);

                    cell = row.createCell(columnNumber++);
                    cell.setCellValue(NMBRE_USRIO == null ? "No registra" : NMBRE_USRIO);

                    cell = row.createCell(columnNumber++);
                    cell.setCellValue(ESTDOCUMENTO == null ? "No registra" : ESTDOCUMENTO);

                    cell = row.createCell(columnNumber++);
                    cell.setCellValue(FECSALIDA == null ? "No registra" : FECSALIDA);

                    cell = row.createCell(columnNumber++);
                    cell.setCellValue(RESPUESTA == null ? "No registra" : RESPUESTA);

                    cell = row.createCell(columnNumber++);
                    cell.setCellValue(FECHA_RESPUESTA == null ? "No registra" : FECHA_RESPUESTA);

                    cell = row.createCell(columnNumber);
                    cell.setCellValue(FECENTRADA == null ? "No registra" : FECENTRADA);
                }
                break;
            case "sql6":
                while (result.next()) {
                    String FECHA_RADICACION = result.getString("FECHA_RADICACION");
                    String ASUNTO = result.getString("ASUNTO");
                    String OBSERVACION_1 = result.getString("OBSERVACION_1");
                    String OBSERVACION_2 = result.getString("OBSERVACION_2");
                    String IDDOCUMENTO = result.getString("IDDOCUMENTO");
                    String FECHA_ENTRADA = result.getString("FECHA_ENTRADA");
                    String ULTIMO_USUARIO = result.getString("ULTIMO_USUARIO");
                    String ESTADO = result.getString("ESTADO");
                    String FECHA_EVACUADO = result.getString("FECHA_EVACUADO");
                    String FECHA_RESPUESTA = result.getString("FECHA_RESPUESTA");
                    String RESPUESTA = result.getString("RESPUESTA");
                    String IDUSUARIO_RAD = result.getString("IDUSUARIO_RAD");
                    String IDUSUARIO_RADICADOR = result.getString("IDUSUARIO_RADICADOR");

                    int columnNumber = 0;

                    Row row = sheet.createRow(rowNumber++);
                    Cell cell = row.createCell(columnNumber++);
                    cell.setCellValue(FECHA_RADICACION == null ? "No registra" : FECHA_RADICACION);

                    cell = row.createCell(columnNumber++);
                    cell.setCellValue(ASUNTO == null ? "No registra" : ASUNTO);

                    cell = row.createCell(columnNumber++);
                    cell.setCellValue(OBSERVACION_1 == null ? "No registra" : OBSERVACION_1);

                    cell = row.createCell(columnNumber);
                    cell.setCellValue(OBSERVACION_2 == null ? "No registra" : OBSERVACION_2);

                    cell = row.createCell(columnNumber++);
                    cell.setCellValue(IDDOCUMENTO == null ? "No registra" : IDDOCUMENTO);

                    cell = row.createCell(columnNumber++);
                    cell.setCellValue(FECHA_ENTRADA == null ? "No registra" : FECHA_ENTRADA);

                    cell = row.createCell(columnNumber++);
                    cell.setCellValue(ULTIMO_USUARIO == null ? "No registra" : ULTIMO_USUARIO);

                    cell = row.createCell(columnNumber++);
                    cell.setCellValue(ESTADO == null ? "No registra" : ESTADO);

                    cell = row.createCell(columnNumber++);
                    cell.setCellValue(FECHA_EVACUADO == null ? "No registra" : FECHA_EVACUADO);

                    cell = row.createCell(columnNumber++);
                    cell.setCellValue(RESPUESTA == null ? "No registra" : RESPUESTA);

                    cell = row.createCell(columnNumber++);
                    cell.setCellValue(IDUSUARIO_RAD == null ? "No registra" : IDUSUARIO_RAD);

                    cell = row.createCell(columnNumber++);
                    cell.setCellValue(IDUSUARIO_RADICADOR == null ? "No registra" : IDUSUARIO_RADICADOR);

                    cell = row.createCell(columnNumber);
                    cell.setCellValue(FECHA_RESPUESTA == null ? "No registra" : FECHA_RESPUESTA);
                }
                break;
            default:
                System.out.println("no se tiene tipo de conexion: {tipoConexion}");
        }

    }

}
