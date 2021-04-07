//importation de la classe Math 
import java.lang.Math;
import java.util.Random;

public class UseGen {
       private static Random random; //la variable ui contiendtra la probabilité qu'on génère 
       
       public static double expo(double variable)
       {
    	   random = new Random(); //méthode random qui génère aléatoirement des nombres 
    	   double y=random.nextDouble(); 
    	   
    	   while (y==0 || y==1)   //tant que x n'est pas entre 0  et 1 on applique encore la méthode random 
    	   {                         //jusqu'à avoir une probabilité 
    		   y=random.nextDouble();  
    	   }
    	   return (-Math.log(1-y)/variable);
       }
	
}
