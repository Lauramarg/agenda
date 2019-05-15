/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package agenda;

import agenda.plantillas.AgendaABS;
import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;

/**
 *
 * @author duvan
 */
public class Agenda {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        AgendaABS a=new AgendaBasica("Enrique", "Tutorias");
        a.añadirTurno(LocalDate.of(2018, Month.DECEMBER, 12), "09:30-10:00 y 10:30-11:00");
        a.añadirTurno(LocalDate.of(2018, Month.DECEMBER, 13), "10:30-11:00");
        AgendaABS b=new AgendaBalanceada("Enrique", "Revision del Examen");
        b.añadirTurno(LocalDate.of(2018, Month.DECEMBER, 12),"12:00-12:30 y 13:30-14:00");
        b.añadirTurno(LocalDate.of(2018, Month.DECEMBER, 13),"11:00-11:30 y 12:30-13:00");
        ArrayList<AgendaABS> lista=new ArrayList<>();
        lista.add(a);
        lista.add(b);
        for (int i = 0; i < lista.size(); i++) {
            System.out.println("Descripcion :"+lista.get(i).getDescripcion());
            System.out.println("Numero de turnos "+lista.get(i).consultarTurnosDeUnDia(LocalDate.of(2018, Month.DECEMBER, 13)).size());
            System.out.println(lista.get(i).reservar("Juan").toString());
            System.out.println(lista.get(i).reservar("Juan").toString());
            for (int j = 0; j < lista.get(i).getR().getTurnosOcupados().size(); j++) {
                System.out.println("Ocupados "+lista.get(i).getR().getTurnosOcupados().get(j).toString());
            }
            lista.get(i).cancelarreserva("Juan",lista.get(i).getR().getTurnosOcupados().get(0) );
            if(i!=0)
                System.out.println("El dia con mayor disponibilidad es: "+lista.get(i).ElegirTurnoLibre().toString());

                

        }
    }
    
}
