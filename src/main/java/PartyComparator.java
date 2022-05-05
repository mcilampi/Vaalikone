import java.util.Comparator;
import data.Ehdokas;
/**
 * Comparator to order candidates by party.
 * @author hannu
 *
 */
public class PartyComparator implements Comparator<Ehdokas>{

	@Override
	public int compare(Ehdokas o1, Ehdokas o2) {
		return o1.getPuolue().compareToIgnoreCase(o2.getPuolue());
	}
	

}
