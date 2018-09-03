package studygroup.daybreak.java8inaction.chap9.prrain;

import java.util.Arrays;
import java.util.List;

import studygroup.daybreak.java8inaction.chap9.prrain.Ellipse;
import studygroup.daybreak.java8inaction.chap9.prrain.Resizable;
import studygroup.daybreak.java8inaction.chap9.prrain.Square;
import studygroup.daybreak.java8inaction.chap9.prrain.Triangle;

public class Game {
	public static void main(String[] args) {
		//List<Resizable> resizableShapes = Arrays.asList(new Square(), new Rectangle(), new Ellipse());
        List<Resizable> resizableShapes =
                Arrays.asList(new Square(),
                        new Triangle(), new Ellipse());
        
        Utils.paint(resizableShapes);
	}
}
