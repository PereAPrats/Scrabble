/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto;
import java.io.*;

/**
 *
 * @author1 Joan Martorell Coll
 * @author2 Pere Antoni Prats Villalonga
 * 
 */

public class FicheroOut {
    private FileWriter f;
    private BufferedWriter bw;
    
    public FicheroOut(String nom) {
        try{
            f = new FileWriter(nom, true);
            bw = new BufferedWriter(f);
        }catch(Exception e){
            System.out.println("Error");
        }        
    }
    
    public void cerrar(){
        try{
            bw.close();
            f.close();   
        }catch(Exception e){
            System.out.println("Error");
        }        
    }
    
    public void escribirString(String s) {
        try{
            bw.write(s);
            bw.newLine();
        }catch(Exception e){
            System.out.println("Error");
        }
    }
    
    public void escribirPalabra(Palabra p) {
        try{
            bw.write(p.toString());
            bw.newLine();
        }catch(Exception e){
            System.out.println("Error");
        }
    }
}
