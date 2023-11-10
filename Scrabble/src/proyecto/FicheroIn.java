/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto;

/*
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
*/

import java.io.*;

/**
 *
 * @author1 Joan Martorell Coll
 * @author2 Pere Antoni Prats Villalonga
 * 
 */

public class FicheroIn {
    private FileReader f;
    private BufferedReader br;
    private int car;
    
    public FicheroIn(String nom) {
        try{
            f = new FileReader(nom);
            br = new BufferedReader(f);
        }catch(Exception e){
            System.out.println("Error");
        }
    }
    
    public void cerrar() {
        try{
            br.close();
            f.close();
        }catch(Exception e){
            System.out.println("Error");
        }
    }
    
    public String leerTodo(){
        String s ="";
        try{
            String l = br.readLine();
            while (l != null){
                s = s + l + '\n';
                l = br.readLine();
            }   
        }catch(Exception e){
            System.out.println("Error");
        }
        return s;
    }
    
    public Palabra leerPalabra(){
        Palabra aux = new Palabra(); //  la que se va a devovler
        try{
            //coloco sobre el primer char
            car = br.read();       
            //mientras no estoy sobre el primer char de la palabra o final
            saltarBlancosYOtros();
            // mientras no final y char de palabra 
            while((car != -1) && (car>=33)){
                //inserta letra en palabra
                aux.addCharacter((char)car);
                //lee siguiente char
                car = br.read();
            }
        }catch(Exception e){
            System.out.println("Error");
        }
        return aux;
    }
    
    public void leerLinea(){
        try{
            br.readLine();
        }catch(Exception e){
            System.out.println("Error");
        }
    }
    
    private void saltarBlancosYOtros(){
        try{
            // mientras no final de fichero y c <= 32 (espacio o cualquier otro char)
            while((car != -1) && (car <= 32)){
                car = br.read();
            }
        }catch(Exception e){
            System.out.println("Error");
        }
    }
}
