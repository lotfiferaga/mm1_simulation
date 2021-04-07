
public class Event {
	double date;    //temps d'event 
	boolean type;  //type d'event si true donc arrivé sinon départ du client 
	int numero;   //l'ordre d'arrivé du client 
	 
	//constructeur d'event 
	public Event()
	{
		type=true;
		date=0; 
	}
	//deuxième constructeur d'event avec paramètre
	public Event(double date, boolean type)
	{
		this.type=type;
		this.date=date; 
	}

}
