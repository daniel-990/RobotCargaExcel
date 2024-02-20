package org.example.Robot;
import org.example.Conexion.ConexionBaseDeDatos;

import java.util.Calendar;
import java.util.Timer;
import java.util.TimerTask;
public class Robot {
    Timer timer = new Timer();
    public void ejecutarFechaRobot(int hora, int minuto, int dia){
        Calendar startTime = Calendar.getInstance();
        startTime.set(Calendar.HOUR_OF_DAY, hora);
        startTime.set(Calendar.MINUTE, minuto);
        startTime.set(Calendar.SECOND, 0);
        // Programar la tarea para que se ejecute todos los días a la hora especificada
        timer.scheduleAtFixedRate(new RobotTarea(), startTime.getTime(), 24 * 60 * 60 * 1000L); // Intervalo de 24 horas
    }
}
class RobotTarea extends TimerTask {
    @Override
    public void run() {
        // se ejecuta la descarga de datos
        ConexionBaseDeDatos conectar = new ConexionBaseDeDatos();
        //ejecutar
        conectar.dataBase();
    }
}
