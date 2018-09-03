package studygroup.daybreak.java8inaction.chap9.prrain;

import java.util.List;

public class Utils {
	public static void paint(List<Resizable> l) {
		l.forEach(r -> {
			r.setAbsoluteSize(42, 42);
			r.draw();
		});
	}
}
