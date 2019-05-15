/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package agenda.plantillas;

import agenda.Turno;
import java.time.LocalDate;
import java.util.ArrayList;

/**
 *
 * @author duvan
 */
public abstract class AgendaABS {
    protected String propietario;
    protected String descripcion;
    protected ArrayList<Turno> listTurnos;
    protected ReservasABS r;

    public AgendaABS(String propietario, String descripcion) {
        this.propietario = propietario;
        this.descripcion = descripcion;
        this.listTurnos=new ArrayList<>();
    }
    public boolean añadirTurno(LocalDate d,String franja){
        if(this.listTurnos.contains(new Turno(d, franja)))
            return false;
        else
            this.listTurnos.add(new Turno(d, franja));
        return true;
    }
    public void ajustarDias(int x){
        for (int i = 0; i < this.listTurnos.size(); i++) {
            this.listTurnos.get(i).AjustarDias(x);
        }
    }
    public ArrayList<Turno> consultarTurnosDeUnDia(LocalDate d){
        ArrayList<Turno>aux=new  ArrayList<>();
        for (int i = 0; i <this.listTurnos.size(); i++) {
            if(this.listTurnos.get(i).getFecha().equals(d))
                aux.add(this.listTurnos.get(i));
        }
        return aux;
    }
    public boolean reservar(String nombre,Turno t){
        if(this.listTurnos.contains(t))
            if(this.r.reservar(nombre, t))
                return true;
        return false;
    }
    public abstract  Turno reservar(String nombre);  
    public abstract Turno ElegirTurnoLibre();
    public boolean cancelarreserva(String nombre,Turno t){
        return this.r.cancelarReserva(nombre, t);
    }

    public String getDescripcion() {
        return descripcion;
    }

    public String getPropietario() {
        return propietario;
    }

    public ArrayList<Turno> getListTurnos() {
        return listTurnos;
    }

    public ReservasABS getR() {
        return r;
    }
    
    
    
}
