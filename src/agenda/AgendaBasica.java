/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package agenda;

import agenda.plantillas.AgendaABS;
import agenda.plantillas.ReservasABS;

/**
 *
 * @author duvan
 */
public class AgendaBasica extends AgendaABS{
    
    public AgendaBasica(String propietario, String descripcion) {
        super(propietario, descripcion);
        this.r=new ReservasABS() {
            @Override
            public Turno reservar(String nombre) {
                Turno x=ElegirTurnoLibre();
                if(x!=null)
                    this.mapa.put(nombre, x);
                else{
                    return x;
                }
                return x;
            }
            
        };
    }

    @Override
    public Turno ElegirTurnoLibre() {
        Turno aux=null;
        boolean sw=false;
        for (int j = 0; j < this.listTurnos.size(); j++) {
            if(!this.r.estaOcupadoUnTurno(this.listTurnos.get(j))){
                aux=this.listTurnos.get(j);
                sw=true;
            }
                
        }
        if(!sw)
            return null;
        for (int i = 0; i <this.listTurnos.size() ; i++) {
            if(aux.getFecha().isAfter(this.listTurnos.get(i).getFecha()))
                aux=this.listTurnos.get(i);
        }
        return aux;
    }

    @Override
    public Turno reservar(String nombre) {
        return this.r.reservar(nombre);
    }
    
}
