//importation de la classe Math 
import java.lang.Math;
import java.util.Random;

public class UseGen {
       private static Random random; //la variable ui contiendtra la probabilit� qu'on g�n�re 
       
       public static double expo(double variable)
       {
    	   random = new Random(); //m�thode random qui g�n�re al�atoirement des nombres 
    	   double y=random.nextDouble(); 
    	   
    	   while (y==0 || y==1)   //tant que x n'est pas entre 0  et 1 on applique encore la m�thode random 
    	   {                         //jusqu'� avoir une probabilit� 
    		   y=random.nextDouble();  
    	   }
    	   return (-Math.log(1-y)/variable);
       }
	
}
