import java.util.LinkedList;

public class Echean {
	static LinkedList<Event> ech; //la liste des clients 
	static double datedep; //signifie le temps de d�part client 
	static double TmpsMoy; //temps moyen dans le syst�me 
	
	
	
	public static void inser(LinkedList<Event> ech,Event e){  //insertion chronologique de l'�v�nement e dans l'�ch
		double ntime=e.date;
		int i=0;
		while(i<ech.size()&&ech.get(i).date<ntime){       //tant qu'on n'atteint pas la taille de l'ech et que la date limite n'est pas d�pass�e
			i++;
		}
			ech.add(i,e);                                 // on ajoute � l'�ch�ancier l'�v�nement, à l'emplacement i
		
	}
	
	public static LinkedList<Event> process(LinkedList<Event> ech,Event e,Statis stats){
		Event arr;
		Event dep;
		double curtmps=e.date;
		double arrtmps;
		double deptmps;
		double tmoy;
	
		int id=e.numero;
		if(e.type==true){            // si c'est un �v�nement de type arriv�e
		Statis.statsarr(e);       // si c'est une arriv�e, on traite les statistiques relatives � ce type
			if(curtmps<=MM.dureemax){
				if((arrtmps=curtmps+UseGen.expo(MM.lambda))<=MM.dureemax){
					arr=new Event(arrtmps,true);                 //on cr�e l'�venement arriv�e
					arr.numero=id+1;                              //on incr�mente son num�ro
					inser(ech,arr);                            // on l'insert dans l'ech
				}
			}
			deptmps=datedep+UseGen.expo(MM.mu); 
			while(deptmps<curtmps){                   //on fait cela afin d'�viter que le d�part arrive dans le bon ordre
				deptmps=datedep+UseGen.expo(MM.mu); 
				
			}
			dep=new Event(deptmps,false);              // on cr�e l'�v�nement d�part
			datedep=dep.date;                        // on lui attribue sa date de d�part
			dep.numero=id;                              // il garde le num�ro qu'il avait en arrivant
			inser(ech,dep);                          //on l'ins�re dans l'�ch�ancier
			tmoy=datedep-curtmps;                    // calcul du temps moyen dans le systeme (reultats simulation)
			TmpsMoy=TmpsMoy+tmoy;
		}
		else{
			
			Statis.statsdep(e);               //on traite les stats relatives aux d�parts si le type de l'�v�nement == false
		}
		return ech;                          //on retourne l'�chiancier obtenu
	}
	
	public static void gestionEch(){ 
		
		
		double arrtime;          //date d'arriv�e de l'�venement 2
		Statis stats=new Statis();
		Event e=new Event();
		ech=new LinkedList<Event>();
		inser(ech,e);             //on ins�re le premier �v�nement 
		
		Statis.statsarr(e);
		
		if(MM.start==1){        //on affiche que si debug est � 1
			System.out.println("Date :" + e.date +"Arrivee client #" +e.numero);
		}
		e=ech.poll();   
		if((arrtime=e.date+UseGen.expo(MM.lambda))<=MM.dureemax){ // si l'arriv�e � une date ne d�passant pas la date maximale
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
				if(e.type==true){               // si ==true, on affiche les arriv�es
					System.out.println("Date : " + e.date +" \n Arrivee client #" +e.numero);
				}
				else{                           // sinon on affiche les d�parts
					System.out.println("Date : " + e.date +"\n  Depart client #" +e.numero +" \n Arrivee  t= " + Statis.getArrTps(e.numero));
				}
			}
		}
	}

}
