/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto;

/**
 *
 * @author1 Joan Martorell Coll
 * @author2 Pere Antoni Prats Villalonga
 * 
 */

import java.time.LocalDate;

public class Jugador {
    
    private String nombre;
    private int puntos;
    
    public Jugador(String name){
        nombre = name;
        puntos = 0;
    }
    
    public Jugador(){
        puntos = 0;
    }
    
    public void setName(String s){
        nombre = s;
    }
    
    public void setPoints(int i){
        puntos += i;
    }
    
    public int getPoints(){
        return puntos;
    }
    
    public String toString(){
       LocalDate f = LocalDate.now();
       String res;
       String ptos = Integer.toString(puntos);
       String fecha = f.toString();
       
       res = nombre + "    " + ptos + "    " + fecha;
       return res;
    }    
}
    