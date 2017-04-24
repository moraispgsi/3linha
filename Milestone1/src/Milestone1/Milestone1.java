/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Milestone1;

import TADInterfaces.LinhaTres;
import TADS.LinhaTresDinamica;
import java.util.Iterator;





/**
 *
 * @author Morai
 */
public class Milestone1 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        
        LinhaTres<String> linhaTres = new LinhaTresDinamica<>(5);
        linhaTres.addFirst("A");
        linhaTres.addFirst("B");
        linhaTres.addFirst("C");
        linhaTres.addFirst("D");
        linhaTres.addFirst("E");
        linhaTres.removeLast();
        
         Iterator<String> iterador = linhaTres.iterator();
         
         while(iterador.hasNext())
             System.out.println(iterador.next());

        
    }
    
}
