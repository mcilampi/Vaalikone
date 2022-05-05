import java.util.Comparator;
import data.Ehdokas;
/**
 * Comparator to order candidates by candidate number.
 * @author hannu
 *
 */
public class NumberComparator implements Comparator<Ehdokas>{

	@Override
	public int compare(Ehdokas o1, Ehdokas o2) {
		if (o1.getEhdokasNumero() > o2.getEhdokasNumero()) {
			return 1;
		} else {
			return -1;
		}
	}
	

}
