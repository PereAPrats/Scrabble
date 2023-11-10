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

import java.util.Random;

public class Alfabeto {
    private FicheroIn f;
    
    private int puntuacion;         //Puntos de la palabra
    private int total;              //numero de fichas
    private int backupTotal;        //Copia de seguridad
    private int longi = 26;         //Longitud de los arrays de fichas
    
    private Palabra palabra;        //Palabra a puntuar    
    private Palabra[] alfabeto;     //Letras abecedario
    
    private int[] numFichas;        //Numero de cada letra disponible
    private int[] backupNumFichas;  //Copia de seguridad
    private int[] puntos;           //Puntos de cada letra    
    private char[] fichas;          //Fichas de la ronda
    
    
    
    public Alfabeto(){
        alfabeto = new Palabra[longi];
        numFichas = new int[longi];
        puntos = new int[longi];
        backupNumFichas = new int[longi];
        Palabra aux;
        int i;
        //Abrir archivo
        f = new FicheroIn("esp.alf");
        //Leer el total de fichas disponibles (la primera palabra del archivo es el total)
        aux = f.leerPalabra();
        total = aux.toInt();
        backupTotal = total;
        
        i = 0;
        aux = f.leerPalabra(); //Leer primera letra
        while(!aux.Vacia()){
            //Asignar a la variable alfabeto las letras disponibles            
            alfabeto[i] = aux;
            //Asignar a la variable numFichas el numero total de fichas de cada letra
            aux = f.leerPalabra();
            numFichas[i] = aux.toInt();
            //Asignar a la variable puntos el numero de puntos de cada ficha
            aux = f.leerPalabra();
            puntos[i] = aux.toInt();
            //Leer otra letra
            aux = f.leerPalabra();
            i++;
        }
        //Poner los puntos a 0 y hacer copia de seguridad
        puntuacion = 0;
        backupTotal = total;
        backupNumFichas = numFichas;
        
        //Cerrar el fichero
        f.cerrar();
    }
    
    public boolean comprobar(Palabra p){
        f = new FicheroIn("esp.dic");
        boolean iguales = false;
        boolean posible = true;
        palabra = p;
        Palabra modelo;         //Para almacenar las palabras leidas del diccionario
        
        //Primero hay que comprobar que solo se hayan usado las fichas disponibles
        for(int i=0; (i<palabra.longitud()) && (posible); i++){
            posible = false;
            for(int j=0; (j<fichas.length) && (!posible); j++){
                posible = (palabra.getLetra(i) == fichas[j]);
                if(posible){fichas[j] = ' ';}
            }
        }
        
        if(posible){
            //Leer primera palabra del diccionario
            modelo = f.leerPalabra();
            //Se verifica si la palabra es valida
            do{
                //Se mira si las palabras son iguales
                iguales = palabra.comparar(modelo);
                //Se lee la siguiente palabra del diccionario
                modelo = f.leerPalabra();
            }while((!modelo.Vacia()) && (!iguales));    //Seguirá buscando mientras no llegue al final y las dos palabras sean diferentes;
        }
        
        f.cerrar();
        return iguales;        
    }
    
    public int puntuar(Palabra p){
        int i;          //Letra de la palabra a puntuar
        int posLetra;   //Posicion de la letra en el array de alfabeto
        palabra = p;
        puntuacion = 0;
        //Analizamos la palabra letra por letra
        for(i=0; i<palabra.longitud();i++){
            posLetra=0;
            //Buscar la posicion que ocupa la letra a puntuar
            while(palabra.getLetra(i) != alfabeto[posLetra].getLetra(0)){
                posLetra++;
            }
            //Sumar los puntos de la letra
            puntuacion += puntos[posLetra];
        }
        return puntuacion;
    }
    
    public char[] asignFichas(){
        fichas = new char[11];
        
        //Se comprueba que queden fichas suficientes
        if(total<11){
            //En caso de no quedar fichas se resetea la memoria de las fichas
            total = backupTotal;
            numFichas = backupNumFichas;
        }
        
        for(int i = 0; i<fichas.length;i++){
            fichas[i] = mezclar();
            numFichas[i] -= 1;
            total--;
        }
        return fichas;
    }
    
    private char mezclar(){
        int n;
        boolean noQuedan;   //indica que no quedan fichas a repartir de esa letra
        Random r = new Random();
        //Suponemos que aun quedan fichas
        noQuedan = false;
        
        do{
            n = r.nextInt(alfabeto.length-1);
            //Comprobamos que aun queden fichas
            if(numFichas[n] == 0){
                noQuedan = true;
            }else{
                noQuedan = false;
            }
        }while(noQuedan);
        
        
        
        return alfabeto[n].getLetra(0);
    }
    
    public Palabra buscarMejor(char[] c){
        f = new FicheroIn("esp.dic");
        Palabra mejor = null;
        Palabra probar;     
        char[] aux = c;
        int puntosAux;
        boolean sePuede = true;
        
        puntuacion = 0;
        
        //Leer primera palabra del diccionario (siempre va a entrar ya que es seguro que en el erchivo habra una palabra)
        probar = f.leerPalabra();
        //Mientras queden palabras se buscara la de mas puntuación
        while(!probar.Vacia()){
            //Se vuelven a resetear las fichas después de analizar cada palabra ya que durante el proceso se borran algunas
            aux = c;
            //Se recorre la palabra del diccionario letra por letra a ver si se dispone de las fichas necesarias            
            for(int i = 0; (i<probar.longitud()); i++){
                //se presupone que no se dispone de la letra necesaria
                sePuede = false;                
                //Se comprueba la suposicion buscando en el array de fichas a ver si esta la letra
                for(int j = 0; (j<aux.length) && (!sePuede); j++){
                    //Si está la letra entonces se cambia el boleano para salir del bucle y analizar la siguiente letra
                    if(probar.getLetra(i) == aux[j]){
                        sePuede = true;
                        //Se elimina la letra ya usada
                        aux[j] = ' ';
                    }
                }
            }
            //Si la palabra se puede hacer
            if(sePuede){
                //Calcular puntos de la palabra                              
                int i;          //Letra de la palabra a puntuar
                int posLetra;   //Posicion de la letra en el array de alfabeto
                
                puntosAux = 0;
                
                for(i=0; i<probar.longitud();i++){      //Puntuar cada letra
                    posLetra=0;
                    //Buscar la posicion que ocupa la letra a puntuar
                    while((posLetra<26) && (probar.getLetra(i) != alfabeto[posLetra].getLetra(0))){
                        posLetra++;
                    }
                    //Sumar los puntos de la letra
                    puntosAux += puntos[posLetra];
                }
                
                //Comparar con la de mayor puntuación hasta ahora
                if(puntosAux > puntuacion){
                    mejor = probar;
                    puntuacion = puntosAux;
                }
            }
            //Leer otra palabra
            probar = f.leerPalabra();
        }        
        
        return mejor;
    }
}
