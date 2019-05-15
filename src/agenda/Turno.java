/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package agenda;

import java.time.LocalDate;
import java.time.Month;

/**
 *
 * @author duvan
 */
public class Turno {
    LocalDate fecha;
    String franja;

    public Turno(LocalDate fecha, String franja) {
        this.fecha = fecha;
        this.franja = franja;
    }

    public String getFranja() {
        return franja;
    }

    public LocalDate getFecha() {
        return fecha;
    }
    public void AjustarDias(int x){
        this.fecha.of(this.fecha.getYear(), this.fecha.getMonth(), this.fecha.getDayOfMonth()+x);
    }
    @Override
    public String toString(){
        return new String(this.fecha.toString()+" "+this.franja);
    }
    
}
