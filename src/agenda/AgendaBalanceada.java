/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package agenda;

import agenda.plantillas.AgendaABS;
import agenda.plantillas.ReservasABS;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;


public class AgendaBalanceada extends AgendaABS{
    HashMap<LocalDate,Integer> mapaContador;

    public AgendaBalanceada(String propietario, String descripcion) {
        super(propietario, descripcion);
        this.mapaContador=new HashMap<>();
        this.r=new ReservasABS() {
            @Override
            public Turno reservar(String nombre) {
                Turno aux=ElegirTurnoLibre();
                if(aux!=null){
                    r.reservar(nombre,aux);
                    modificarDiasDecrementar(aux.getFecha());
                    return aux;
                }else{
                    return null;
                }
            }
        };
    }

    @Override
    public Turno ElegirTurnoLibre() {
        Integer aux=0;
        LocalDate auxd=this.listTurnos.get(0).getFecha();
        for (HashMap.Entry<LocalDate, Integer> e : this.mapaContador.entrySet()) {
                LocalDate key = e.getKey();
                Integer value = e.getValue();
                if(aux<value){
                    aux=value;
                    auxd=key;
                }
        }
        for (int i = 0; i < this.listTurnos.size(); i++) {
            if(this.listTurnos.get(i).getFecha().equals(auxd))
                return this.listTurnos.get(i);
        }
        return null;
    }

    @Override
    public Turno reservar(String nombre) {
        return this.r.reservar(nombre);
    }
    @Override
    public boolean añadirTurno(LocalDate d,String franja){
        if(this.listTurnos.contains(new Turno(d, franja)))
            return false;
        else
            this.listTurnos.add(new Turno(d, franja));
        modificarDiasAumentar(d);
        return true;
    }
    private void modificarDiasAumentar(LocalDate d){
        if(this.mapaContador.containsKey(d)){
            this.mapaContador.replace(d,this.mapaContador.get(d)+1);
        }else{
            this.mapaContador.put(d,1);
        }
    }
    private void modificarDiasDecrementar(LocalDate d){
        if(this.mapaContador.containsKey(d)){
            if(this.mapaContador.get(d)==1){
                this.mapaContador.remove(d);
            }else{
                this.mapaContador.replace(d,this.mapaContador.get(d)-1);
            }
        }
    }
    @Override
    public boolean reservar(String nombre,Turno t){
        if(this.listTurnos.contains(t))
            if(this.r.reservar(nombre, t)){
                this.modificarDiasDecrementar(t.getFecha());
                return true;
            }
                
        return false;
    }
    @Override
    public boolean cancelarreserva(String nombre,Turno t){
        boolean sw= this.r.cancelarReserva(nombre, t);
        if(sw)
            this.modificarDiasDecrementar(t.getFecha());
        return sw;
    }
    
}
