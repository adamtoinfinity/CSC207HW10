import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import edu.grinnell.glimmer.ushahidi.UshahidiClient;
import edu.grinnell.glimmer.ushahidi.UshahidiIncident;
import edu.grinnell.glimmer.ushahidi.UshahidiIncidentList;
import edu.grinnell.glimmer.ushahidi.UshahidiUtils;
import edu.grinnell.glimmer.ushahidi.UshahidiWebClient;

public class UshahidiManager {

    UshahidiWebClient webClient;
    UshahidiIncident[] list;

    public void setUp(String url) throws Exception {
	webClient = new UshahidiWebClient(url);
	this.resetIncidentList();
    }// setUp

    public void resetIncidentList() {
	list = webClient.getIncidents();
    }// resetIncidentList

    public void order(Comparator t) {
	Arrays.sort(list, t);
    }

    public void order(Comparator a, Comparator b) {
	order(combine(a, b));
    }

    public Comparator<UshahidiIncident> combine(
	    final Comparator<UshahidiIncident> a,
	    final Comparator<UshahidiIncident> b) {
	Comparator<UshahidiIncident> combinedComparator = new Comparator<UshahidiIncident>() {
	    Comparator<UshahidiIncident> mainComparator = a;
	    Comparator<UshahidiIncident> secondaryComparator = b;

	    @Override
	    public int compare(UshahidiIncident o1, UshahidiIncident o2) {
		if (mainComparator.compare(o1, o2) == 0) {
		    return secondaryComparator.compare(o1, o2);
		} else {
		    return mainComparator.compare(o1, o2);
		}
	    }
	};
	return combinedComparator;
    }
}// UnshidiManager
