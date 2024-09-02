package Lavoro;

public class GestoreCollisioni {

	public static boolean controllaCollisione(Giocatore ombrello, Goccia goccia) {
		if(ombrello.getBordi().intersects(goccia.getBordi())) {
			return true;
		}
		return false;
	}
	
	public static boolean controllaCollisioneTopo(Topo topo, Goccia goccia) {
		if(topo.getBordi().intersects(goccia.getBordi())){
			return true;
		}
		return false;
	}
}
