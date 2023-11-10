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

public class Proyecto {

    /**
     * @param args the command line arguments
     */
    
    private LT lector = new LT();           //Para leer desde teclado
    private mensajes msj = new mensajes();  //Para mostrar todos los mensajes
    private Modo m = new Modo();            //Para los modos de juego
    
    
    public static void main(String[] args) {
        Proyecto p = new Proyecto();
        p.inicio();        
    }
    
    public void inicio() {
        int opcion;
        
        //Mostrar menú principal
        msj.menuPrincipal();
        //El usuario introduce la opción que desea
        opcion = lector.leerEntero();

        /*En caso de que no se introduzca una opción válida se muestra
          un mensaje de error y se vuelve a pedir la opción*/
        while(opcion < 1 || opcion > 3){
                msj.opcion_nula();
                opcion = lector.leerEntero();
        }
        
        //Si la opción es válida, se ejecuta el programa pertinente
        switch (opcion) {
            case 1: jugar(); break;
            case 2: estadisticas(); break;
            case 3: System.exit(0);
            default: break;
        }        
    }
    
    //modos de juego
    private void jugar() {
        int modo;
        int rondas;
        
        //Mostrar menu del modo Jugar        
        msj.menuJugar();
        //Se le pregunta al usuario a que modo quiere jugar        
        modo = lector.leerEntero();
        
        /*En caso de que no se introduzca un modo válido se muestra
          un mensaje de error y se vuelve a pedir el modo*/
        while(modo < 1 || modo > 3){
            msj.modo_nulo();
            modo = lector.leerEntero();
        }
        
        //Se ejecuta el modo de juego pertinente
        switch(modo){
            case 1: m.individual(); break;
            case 2: m.supercerebro(); break;
            case 3: inicio(); break;
            default: break;
        }
        
        //Se vuelve al inicio
        inicio();
    }
    
    //Estadisticas
    private void estadisticas() {
        //Se abre el fichero de estadisticas
        FicheroIn e = new FicheroIn("Estadisticas.txt");
        //Se lee todo el fichero
        String datos = e.leerTodo();
        //Se muestran las estadisticas
        msj.estadisticas(datos);
        //Vuelta al inicio
        inicio();
    }
    
}
