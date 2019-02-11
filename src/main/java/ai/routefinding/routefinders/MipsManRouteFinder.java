package ai.routefinding.routefinders;

import java.awt.Point;

import ai.routefinding.RouteFinder;
import utils.enums.Direction;

/**Route finding algorithm that controls Mipsman. Will aim to reach the nearest pellet whilst avoiding any ghouls.
 * @author Lewis Ackroyd*/
public class MipsManRouteFinder implements RouteFinder {

	@Override
	public Direction getRoute(Point myLocation, Point targetLocation) {
		// TODO Auto-generated method stub
		return Direction.DOWN;
	}

}