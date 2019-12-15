package cron;

import java.util.Timer;
import java.util.TimerTask;
import java.util.*;

class TimerEx {
    @SuppressWarnings("deprecation")
	public static void main(String arglist[]) {
        Timer timer = new Timer();
        Date horarioDeEjecucion = new Date();
        horarioDeEjecucion.setHours(2);
        horarioDeEjecucion.setMinutes(0);


        TimerTask task = new TimerTask() {

            @Override
            public void run()
            {
                System.out.println("Estoy funcionando, son las " + new Date() + ".");
                //Acá habría que agarrar a los usuarios y fijarse si tienen eventos y sugerencias
            }
        };
        // Empieza a las 2 de la mañana y se repite cada 86400000 ms, que es un día
        timer.schedule(task,horarioDeEjecucion,86400000);
    }
}