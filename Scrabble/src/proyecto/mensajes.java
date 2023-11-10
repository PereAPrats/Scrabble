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

public class mensajes {
    //Mensajes de error
    
    public void opcion_nula(){
        System.out.println("");
        System.out.println("[ERROR] Opción incorrecta, introduzca uno opción correcta:");
    }
    
    public void modo_nulo(){
        System.out.println("");
        System.out.println("[ERROR] Modo de juejo incorrecto, introduzca un modo correcto:");
    }
    
    //Menues
    
    public void menuPrincipal(){
        System.out.println(" __________________________________");
        System.out.println("");
        System.out.println(" | ********** SCRABBLE ********** |");
        System.out.println(" __________________________________");
        System.out.println(" |       ¿Qué deseas hacer?       |");
        System.out.println(" |                                |");
        System.out.println(" |       [1]  JUGAR               |");
        System.out.println(" |       [2]  VER ESTADÍSTICAS    |");
        System.out.println(" |       [3]  SALIR               |");
        System.out.println(" |________________________________|");
        System.out.println("");
        System.out.println("OPCIÓN ELEGIDA:");
    }
    
    public void menuJugar(){
        System.out.println(" __________________________________");
        System.out.println("");
        System.out.println(" | ********** SCRABBLE ********** |");
        System.out.println(" __________________________________");
        System.out.println(" |    ¿Qué modo deseas jugar?     |");
        System.out.println(" |                                |");
        System.out.println(" |       [1]  INDIVIDUAL          |");
        System.out.println(" |       [2]  SUPERCEREBRO        |");
        System.out.println(" |       [3]  ATRÁS               |");
        System.out.println(" |________________________________|");
        System.out.println("");
        System.out.println("MODO DE JUEGO ELEGIDO:");
    }
    
    //Otros
    
    public void estadisticas(String datos){
        System.out.println("");
        System.out.println("ESTADISTICAS");
        System.out.println("Nombre      Puntos      Fecha");
        System.out.println("_________________________________");
        System.out.println("");
        System.out.println(datos);
        System.out.println("_________________________________");
        System.out.println("");
    }
    
    //Partida acabada
    public void partidaAcabada(){
        System.out.println("");
        System.out.println("Partida acabada");
        System.out.println("");
    }
    
    //Timer
    public void timer(int tiempo){
        System.out.println("");
        System.out.println("Dispone de " + tiempo + " segundos para pensar");
        System.out.print("Tiempo restante: ");
        for(int j = tiempo; j > 0; j = j-5){
            System.out.print(j + " ");
            try{
                Thread.sleep(5000);
            }catch(InterruptedException e){}
        }
        System.out.print(0);
        System.out.println("");
        System.out.println("");
    }
}
