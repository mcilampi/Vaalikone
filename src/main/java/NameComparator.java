import java.util.Comparator;
import data.Ehdokas;

public class NameComparator implements Comparator<Ehdokas>{

	@Override
	public int compare(Ehdokas o1, Ehdokas o2) {
		return o1.getSukunimi().compareToIgnoreCase(o2.getSukunimi());
	}
	

}
