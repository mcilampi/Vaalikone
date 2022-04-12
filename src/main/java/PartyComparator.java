import java.util.Comparator;
import data.Ehdokas;

public class PartyComparator implements Comparator<Ehdokas>{

	@Override
	public int compare(Ehdokas o1, Ehdokas o2) {
		return o1.getPuolue().compareToIgnoreCase(o2.getPuolue());
	}
	

}
