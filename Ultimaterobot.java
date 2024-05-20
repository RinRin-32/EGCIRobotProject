package aigroupwork;

import robocode.*;
import static robocode.util.Utils.normalRelativeAngleDegrees;
import robocode.BattleRules;


public class Ultimaterobot extends AdvancedRobot  {

	//Corner, 0=topLeft, 90=topRight, 180=bottomRight, 270(-90)=bottomLeft, static to keep in between rounds
	int corner = 0;
	//Gun Direction for turning around or looking at enemy
	int gunDirection = 1;
	//stop the robot from shooting at other robot
	boolean stopWhenSeeRobot = false;

	public void run() {

		//get user's robot position
		int X = (int)getX();
		int Y = (int)getY();

		//get battlefield size
		int width = (int)getBattleFieldWidth();
		int height = (int)getBattleFieldHeight();

		//see which corner to go to
		if (X < (int)(width/2) && Y > (int)(height/2)) {
			corner = 0;
		} else if (X > (int)(width/2) && Y > (int)(height/2)) {
			corner = 90;
		} else if (X > (int)(width/2) && Y < (int)(height/2)) {
			corner = 180;
		} else if (X < (int)(width/2) && Y < (int)(height/2)) {
			corner = -90;
		}

		//go to corner first before fighting
		goCorner();

		// Turns the gun infinitely, looking for enemies
		while (true) {
			turnGunRight(360);
		}
	}

	public void onScannedRobot(ScannedRobotEvent e) {
		if (stopWhenSeeRobot) {
			// Turn the robot towards the enemy
			setTurnRight(e.getBearing());
			// Shoots always that it's aiming at the enemy
			setFire(3);
			// And move forward
			setAhead(100);
			// Inverts the gun direction on each turn
			gunDirection = -gunDirection;
			// Turn 360 degrees (clockwise or anti-clockwise)
			setTurnGunRight(360 * gunDirection);
			// Execute all the pending actions
			execute();
		}
	}

	public void goCorner() {
		stopWhenSeeRobot = false;
		// turn to face the wall to the "right" of our desired corner.
		turnRight(normalRelativeAngleDegrees(corner - getHeading()));
		// Move to that wall
		ahead(5000);
		// Turn to face the corner
		turnLeft(90);
		// Move to the corner
		ahead(5000);
		// Turn gun to starting point
		turnGunLeft(90);
		// Can shoot at other robots now
		stopWhenSeeRobot = true;
	}

}