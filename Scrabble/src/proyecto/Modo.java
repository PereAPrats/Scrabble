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

public class Modo {
    private LT lector = new LT();
    private mensajes msj = new mensajes();
    private FicheroOut setEstadisticas;     //Acceso al archivo de estadisticas
    private FicheroIn fIn;      //Acceso al archivo de diccionario
    private Jugador jugador;    //Para guardar la información del jugador
    private Jugador ordenador;  //Para guardar la información del ordenador
    private Alfabeto alf;       //Para puntuar las palabras del jugador
    private Alfabeto alfPC;     //Para puntuar las palabras del ordenador
    
    private char[] letras;      //Aqui se guardan las fichas de las que dse dispone
    private Palabra palabra;    //Aqui se guarda la palabra introducida
    private String strAux;      //String auxiliar
    private int puntos;         //Aqui se almacenan los puntos ganados por el jugador
    public int puntos2;         //Aqui se almacenan los puntos ganados por el ordenador
    private boolean correcto;   //Sirve para determinar si la palabra es correcta
    
    
    
    //Modo de juego individual
    public void individual(){
        int rondas;
        //Se llama a las clases necesarias
        fIn = new FicheroIn("Estadisticas.txt");
        setEstadisticas = new FicheroOut("Estadisticas.txt");        
        jugador = new Jugador();        
        alf = new Alfabeto();
        //Preguntar el nombre al jugador
        System.out.println("");
        System.out.println("Porfavor, introduzca un nombre:");
        String nombre = lector.leerLinea();
        System.out.println("");
        jugador.setName(nombre);
        //Se piden las rondas que se desean jugar
        System.out.println("");
        System.out.println("Quantas rondas desea jugar?");
        rondas = lector.leerEntero();
        System.out.println("");
        //Se juegan las rondas indicadas
        for(int i = 1; i<= rondas; i++){
            correcto = false;
            //Mostrar las fichas disponibles
            System.out.println("");
            System.out.println("RONDA " + i);
            System.out.println("Estas son las letras disponibles: ");
            //Se asignan las fichas de la ronda
            letras = alf.asignFichas();
            System.out.println("");
            System.out.println(mostrarLetras());
            System.out.println("");
            //Dar al jugador x tiempo para pansar
            msj.timer(20);
            //Preguntarle al jugador por su palabra
            System.out.println("Introduzca una palabra:");
            strAux = lector.leerLinea();
            palabra = new Palabra(strAux);
            //Comprobar palabra en el diccionario
            correcto = alf.comprobar(palabra);            
            //Se indica si la palabra es correcta y la cantidad de puntos obtenidos
            if(correcto){ 
                //Se puntua la palabra
                puntos = alf.puntuar(palabra);
                System.out.println("");
                System.out.println("Con la palabra " + palabra.toString() + " ha obtenido " + puntos + " puntos");
                //Se suman los puntos obtenidos
                jugador.setPoints(puntos);
                System.out.println("Su puntuación actual es de " + jugador.getPoints() + " puntos");
                System.out.println("");
            }else{
                //Se quitan 10 puntos
                puntos = -10;
                System.out.println("");
                System.out.println("La palabra introducida no es válida (-10p)");
                //Se restan 10 puntos
                jugador.setPoints(-10);
                System.out.println("Su puntuación actual es de " + jugador.getPoints() + " puntos");
                System.out.println("");
            }
        }
        
        //Se guardan las estadísticas
        setEstadisticas.escribirString(jugador.toString());
        
        //Se cierra todo y se vuelve al inicio
        setEstadisticas.cerrar();
        fIn.cerrar();
        msj.partidaAcabada();
    }
    
    //Modo de juego supercerebro
    public void supercerebro(){
        int rondas;
        //Se llama a las clases necesarias
        setEstadisticas = new FicheroOut("Estadisticas.txt");
        jugador = new Jugador();  
        ordenador = new Jugador();
        alf = new Alfabeto();
        alfPC = new Alfabeto();
        //Preguntar el nombre al jugador
        System.out.println("");
        System.out.println("Porfavor, introduzca un nombre:");
        String nombre = lector.leerLinea();
        System.out.println("");
        //Se piden las rondas que se desean jugar
        System.out.println("");
        System.out.println("Quantas rondas desea jugar?");
        rondas = lector.leerEntero();
        System.out.println("");        
        jugador.setName(nombre);
        ordenador.setName("SuperCerebro");
        
        //Se juegan las rondas indicadas
        for(int i = 1; i<= rondas; i++){
            //Turno jugador
            correcto = false;
            //Mostrar las fichas disponibles
            System.out.println("");
            System.out.println("RONDA " + i);
            System.out.println("Estas son las letras disponibles: ");
            //Se asignan las fichas de la ronda
            letras = alf.asignFichas();
            System.out.println("");
            System.out.println(mostrarLetras());
            System.out.println("");
            //Dar al jugador x tiempo para pansar
            msj.timer(20);
            //Preguntarle al jugador por su palabra
            System.out.println("Introduzca una palabra:");
            strAux = lector.leerLinea();
            palabra = new Palabra(strAux);
            //Comprobar palabra en el diccionario
            correcto = alf.comprobar(palabra);            
            //Se indica si la palabra es correcta y la cantidad de puntos obtenidos
            if(correcto){ 
                //Se puntua la palabra
                puntos = alf.puntuar(palabra);
                System.out.println("");
                System.out.println("Con la palabra " + palabra.toString() + " ha obtenido " + puntos + " puntos");
                //Se suman los puntos obtenidos
                jugador.setPoints(puntos);
                System.out.println("Su puntuación actual es de " + jugador.getPoints() + " puntos");
                System.out.println("");
            }else{
                //Se quitan 10 puntos
                puntos = -10;
                System.out.println("");
                System.out.println("La palabra introducida no es válida (-10p)");
                //Se restan 10 puntos
                jugador.setPoints(-10);
                System.out.println("Su puntuación actual es de " + jugador.getPoints() + " puntos");
                System.out.println("");
            }
            
            
            //Turno ordenador
            //Sacar las fichas
            letras = alfPC.asignFichas();
            //Comprobar palabra a palabra del diccionario si se puede hacer y coger la de mayor puntuación
            palabra = alfPC.buscarMejor(letras);
            puntos2 = alfPC.puntuar(palabra);
            //Mostrar al jugador las estadisticas del ordenador
            System.out.println("El ordenador dispone de las siguientes fichas:");
            System.out.println(mostrarLetras());
            System.out.flush();
            System.out.println("");
            System.out.println("El ordenador ha jugado la palabra " + palabra.toString());
            System.out.println("");
            System.out.println("Con esta palabra, el ordenador ha ganado " + puntos2 + " puntos");
            //Asignar puntos
            ordenador.setPoints(puntos2);
            System.out.println("La puntuación actual del ordenador es de " + ordenador.getPoints() + " puntos");
            System.out.println("");
        }
        
        //Se guardan las estadisticas
        setEstadisticas.escribirString(jugador.toString());
        setEstadisticas.escribirString(ordenador.toString());
        
        //Se cierra todo y se vuelve al inicio
        setEstadisticas.cerrar();
        msj.partidaAcabada();
    }
    
    //Mostrar letras
    private String mostrarLetras(){
        String s = "";
        //Imprime las letras en la misma linea
        for(int i = 0; i<letras.length;i++){
            s += (letras[i] + "  ");
        }
        return s;
    }
}
