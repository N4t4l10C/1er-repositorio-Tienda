package libreria;

import libreria.servicio.ServicioMaster;

public class Libreria {

    public static void main(String[] args) throws Exception{
        
        ServicioMaster sM=new ServicioMaster();
        String resp=null;
        
        do {
            
            sM.elPrograma();
            
            resp=sM.pregunta();
            
        } while (resp.equals("S"));
        
        System.out.println("");
        System.out.println("o--------------------------o");
        System.out.println("Gracias vuelva prontos!");
        System.out.println("o--------------------------o");
      
    }
}
