
public class Event {
	double date;    //temps d'event 
	boolean type;  //type d'event si true donc arriv� sinon d�part du client 
	int numero;   //l'ordre d'arriv� du client 
	 
	//constructeur d'event 
	public Event()
	{
		type=true;
		date=0; 
	}
	//deuxi�me constructeur d'event avec param�tre
	public Event(double date, boolean type)
	{
		this.type=type;
		this.date=date; 
	}

}
