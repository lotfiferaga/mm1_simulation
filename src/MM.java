// la classe qui va contenir tous les résultats 
public class MM {
	public static double lambda; //paramètre lambda 
	public static double mu;   // le mu 
	public static double dureemax; // le temps maximal 
	public static int start; //l'ouverture du magasin par exemple
	private static Event e;  //l'event 
	
public static void main(String[] args){
		
		double time=System.currentTimeMillis();
		lambda=5;
		mu=6;
		dureemax=1000;
		start=1;
				
		System.out.println(" Debut de simulation");
		new Statis();
		e= new Event(0,true);
		new Echean();
		
		Echean.gestionEch(); 												//Programme principal 
		
		double finaltime=(System.currentTimeMillis()-time)/1000;       //permet d'avoir le temps d'cution 
		System.out.println("\n Reussi !\n Temps d'execution :"+ finaltime +"s");
		Statis.statsTheo(e);
		Statis.ResultSimu();
	}

}
