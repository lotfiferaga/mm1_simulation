import java.util.LinkedList;

public class Echean {
	static LinkedList<Event> ech; //la liste des clients 
	static double datedep; //signifie le temps de départ client 
	static double TmpsMoy; //temps moyen dans le système 
	
	
	
	public static void inser(LinkedList<Event> ech,Event e){  //insertion chronologique de l'évènement e dans l'éch
		double ntime=e.date;
		int i=0;
		while(i<ech.size()&&ech.get(i).date<ntime){       //tant qu'on n'atteint pas la taille de l'ech et que la date limite n'est pas dépassée
			i++;
		}
			ech.add(i,e);                                 // on ajoute à  l'échéancier l'évènement, Ã  l'emplacement i
		
	}
	
	public static LinkedList<Event> process(LinkedList<Event> ech,Event e,Statis stats){
		Event arr;
		Event dep;
		double curtmps=e.date;
		double arrtmps;
		double deptmps;
		double tmoy;
	
		int id=e.numero;
		if(e.type==true){            // si c'est un évènement de type arrivée
		Statis.statsarr(e);       // si c'est une arrivée, on traite les statistiques relatives à ce type
			if(curtmps<=MM.dureemax){
				if((arrtmps=curtmps+UseGen.expo(MM.lambda))<=MM.dureemax){
					arr=new Event(arrtmps,true);                 //on crée l'évenement arrivée
					arr.numero=id+1;                              //on incrémente son numéro
					inser(ech,arr);                            // on l'insert dans l'ech
				}
			}
			deptmps=datedep+UseGen.expo(MM.mu); 
			while(deptmps<curtmps){                   //on fait cela afin d'éviter que le départ arrive dans le bon ordre
				deptmps=datedep+UseGen.expo(MM.mu); 
				
			}
			dep=new Event(deptmps,false);              // on crée l'évènement départ
			datedep=dep.date;                        // on lui attribue sa date de départ
			dep.numero=id;                              // il garde le numéro qu'il avait en arrivant
			inser(ech,dep);                          //on l'insère dans l'échéancier
			tmoy=datedep-curtmps;                    // calcul du temps moyen dans le systeme (reultats simulation)
			TmpsMoy=TmpsMoy+tmoy;
		}
		else{
			
			Statis.statsdep(e);               //on traite les stats relatives aux départs si le type de l'évènement == false
		}
		return ech;                          //on retourne l'échiancier obtenu
	}
	
	public static void gestionEch(){ 
		
		
		double arrtime;          //date d'arrivée de l'évenement 2
		Statis stats=new Statis();
		Event e=new Event();
		ech=new LinkedList<Event>();
		inser(ech,e);             //on insère le premier évènement 
		
		Statis.statsarr(e);
		
		if(MM.start==1){        //on affiche que si debug est à  1
			System.out.println("Date :" + e.date +"Arrivee client #" +e.numero);
		}
		e=ech.poll();   
		if((arrtime=e.date+UseGen.expo(MM.lambda))<=MM.dureemax){ // si l'arrivée à une date ne dépassant pas la date maximale
			Event arr=new Event(arrtime,true);
			arr.numero=e.numero+1;
			inser(ech,arr);
		}
		Event dep=new Event(e.date+UseGen.expo(MM.mu),false);
		dep.numero=e.numero;
		inser(ech,dep);
		datedep=dep.date;
		
		while(!ech.isEmpty()){                  //tant que l'echantillon n'est pas vide
			e=ech.poll(); 
			ech=process(ech,e,stats); 
			if(MM.start==1){
				if(e.type==true){               // si ==true, on affiche les arrivées
					System.out.println("Date : " + e.date +" \n Arrivee client #" +e.numero);
				}
				else{                           // sinon on affiche les départs
					System.out.println("Date : " + e.date +"\n  Depart client #" +e.numero +" \n Arrivee  t= " + Statis.getArrTps(e.numero));
				}
			}
		}
	}

}
