/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package task;

/**
 *
 * @author Mahmoud
 */
public class Task {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        try{
            cipherform form = new cipherform();
            form.show();
        }
        catch(Exception e){
            System.out.println("Error With Gui Implementing");
        }
        // TODO code application logic here
    }
    
}
