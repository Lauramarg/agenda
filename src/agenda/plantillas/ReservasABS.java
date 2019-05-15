/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package agenda.plantillas;

import agenda.Turno;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

/**
 *
 * @author duvan
 */
public abstract class ReservasABS {
    protected HashMap<String,Turno> mapa;

    public ReservasABS() {
        this.mapa=new HashMap<>();
    }
    public boolean reservar(String nombre,Turno t){
        if(null!=this.mapa.get(nombre)){
            return false;
        }else{
            this.mapa.put(nombre, t);
            return true;
        }
    }
    public abstract Turno reservar(String nombre);
    
    public String consultarUsuario(Turno t){
        if(this.mapa.containsKey(t)){
            for (HashMap.Entry<String, Turno> e : mapa.entrySet()) {
                String key = e.getKey();
                Turno value = e.getValue();
                if(value.equals(t));
                    return key;
            }
        }
        return null;
    }
    public boolean estaOcupadoUnTurno(Turno t){
        if(this.mapa.containsValue(t))
            return true;
        return false;
    }
    public boolean cancelarReserva(String nombre,Turno t){
        return this.mapa.remove(nombre, t);
    }
    public ArrayList<Turno> getTurnosOcupados(){
        ArrayList<Turno>aux=new ArrayList<>();
        for (HashMap.Entry<String, Turno> e : mapa.entrySet()) {
                String key = e.getKey();
                Turno value = e.getValue();
                aux.add(value);
            }
        return aux;
    }
    
}
