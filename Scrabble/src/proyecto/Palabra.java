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

public class Palabra {
    private final char [] letras;
    private int longitud;
    private static final int MAX = 20;
    
    //Crear palabra vacia
    public Palabra(){
        letras = new char [MAX];
        longitud = 0;
    }
    
    //Crear palabra desde un string
    public Palabra(String s){
        letras = new char [MAX];
        char[] aux = s.toCharArray();
        try{
            for(int i = 0; i<aux.length;i++){
                letras[i] = aux[i];
            }
            longitud = aux.length;
        }catch(Exception e){}
    }
    
    public void addCharacter(char c){
        letras[longitud] = c;
        longitud++;
    }
    
    public boolean Vacia(){
        return(longitud==0);
    } 
    
    public int longitud(){
        return longitud;
    }
    
    public char[] toArray(){
        return letras;
    }
    
    public boolean comparar(Palabra p){
        boolean iguales = true;
        char[] aux = p.toArray();
        
        if(p.longitud != longitud){
            iguales = false;
        }else{
            for(int i=0; (i<longitud) && iguales; i++){
                if(aux[i] != letras[i]){iguales = false;}
            }
        }
        
        return iguales;
    }
    
    public int toInt(){
        int res;
        int mult;
        res = 0;
        mult = 1;
        for(int i = longitud-1; i>=0; i--){
            res = res + (letras[i] - '0') * mult;
            mult *= 10;
        }
        return res;
    }
    
    public char getLetra(int i){
        return letras[i];
    }
    
    public String toString(){
        String res = "";
        for (int i = 0; i<longitud; i++){
            res = res + letras[i];
        }
        return res;
    }
}
