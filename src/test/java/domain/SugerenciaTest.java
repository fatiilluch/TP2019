package domain;

import java.text.SimpleDateFormat;
import java.util.Date;

import domain.events.Evento;
import domain.events.Frecuencia;
import domain.events.Protocolo;

public class SugerenciaTest{
    public static void main(String[] args) {
    	Sensibilidad sensibilidadPepe = new Sensibilidad(2.0,3.2);
        Usuario pepe = new Usuario("Pepe", "123", sensibilidadPepe, "Pepe", "Grillo");
        Protocolo deportivo = Protocolo.Informal;
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        // https://docs.oracle.com/javase/8/docs/api/java/util/Date.html funcionamiento de Date()

        @SuppressWarnings("deprecation")
        Evento maniana = new Evento("maniana",new Date(119,7,26),deportivo,Frecuencia.ANUAL,pepe);

        maniana.setProximidad(3);
        if(maniana.esProximo()){ System.out.println("SOY PROXIMO JEJE");}
        else { System.out.println("NO SOY PROXIMO UE UE");}
    }
}
