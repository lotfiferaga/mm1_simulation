import java.util.LinkedList;

public class Statis {
	static LinkedList<Double> listeTps;				//liste des temps d'arrivee
	static double  nbClients;							//nombre de clients dans le système
	static double nbClientsTot;						//nombre total de clients = nb d'évènements
	static double sansAtt;							// nombre de clients servis sans attente
	static double nbClientsMoy;						//nombre moyen de clients dans le systeme
	static double tpsMoy;							//temps moyen de séjour
	static double tpsDeSimu;						//temps de simulation
	
	public Statis(){
		listeTps=new LinkedList<Double>();
		nbClientsTot=0;
		nbClients=0;
		nbClientsMoy=0;
		sansAtt=0;
		tpsMoy=0;
		tpsDeSimu=0;
	}
	
	public static double getArrTps(int i){                      //permet de retourner le temps d'arrivée de l'évènement i
		return listeTps.get(i);
	}
	
	public static void statsTheo(Event e) {              // Calcul des stats théoriquement
		System.out.println();
		System.out.println("#######################RESULTATS THEORIQUES########################");
		System.out.println();
		if (MM.lambda < MM.mu)  {
			System.out.println("lambda < mu : file stable");
		}
		else {
			System.out.println("lambda > mu : file instable");
		}
		double ro= MM.lambda/MM.mu;
		System.out.println("Nombre de clients attendus = " + MM.lambda*MM.dureemax + "\nProba file occupee ro (lambda/mu) = " + ro + "\nProba sans attente (1-ro) = " + (1-ro) + "\nlambda  = " + MM.lambda );
		
		double nbClients = ro/(1-ro);
		double tpsMoyenSejour = 1/(MM.mu*(1-ro));
		System.out.println("le nombre de clients dans le systeme (ro/1-ro) = " + nbClients + "\nTemps moyen de sejour (1/mu(1-ro)) = " + tpsMoyenSejour);

		
	}
	
	public static void statsarr(Event e){           //traitement statistique d'une arrivée
		nbClientsTot++;                              // c'est un évènement, on incrémente le nombre de clients total 
		listeTps.add(e.date);
		if(e.type==true && nbClients==0 || nbClients==nbClientsTot/2)   // si le dernier est sorti ou que c'est le premier
			sansAtt++; 
		nbClients++;                                // c'est une arrivée : on incrémente le nombre de clients dans le système
		nbClientsMoy=(nbClientsTot/tpsDeSimu);    //on fait la moyenne
	}
	
	public static void statsdep(Event e){          //traitement statistique d'un départ 
		tpsDeSimu=e.date;
		nbClients--;                                       // c'est un départ, on décrémente le nombre de clients dans le système
		nbClientsMoy=(nbClientsTot/tpsDeSimu);
		tpsMoy=Echean.TmpsMoy/nbClientsTot;
		
	}
	
	public static void ResultSimu(){                        //Calculs  des Resultats après simulation
		double debit=nbClientsTot/tpsDeSimu;                //débit de la file d'attente 
		double proporSansAtt=sansAtt/nbClientsTot;          //proportion de clients servis sans attente
		double proporAvecAtt=1-proporSansAtt;               //proportion de clients servis avec attente
		System.out.println();
		System.out.println("######################################### RESULTATS DE SIMULATION ################################\n\nNombre total de clients = "+nbClientsTot+"\nProportion clients sans attente = "+proporSansAtt+"\nProportion clients avec attente = "+proporAvecAtt+"\nDebit = "+debit+"\nNb moyen de clients dans le systeme = "+nbClientsMoy+"\nTemps moyen de sejour = "+tpsMoy);
		
	}

}
