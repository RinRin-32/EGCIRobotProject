package aigroupwork;

import robocode.*;
import static robocode.util.Utils.normalRelativeAngleDegrees;


public class Ultimaterobot extends AdvancedRobot  {

	//Corner, 0=topLeft, 90=topRight, 180=bottomRight, 270(-90)=bottomLeft, static to keep in between rounds
	static int corner = 0;
	//Gun Direction for turning around or looking at enemy
	int gunDirection = 1;
	//Number of other robot in the field for calculations on death
	int others;
	//stop the robot from shooting at other robot
	boolean stopWhenSeeRobot = false;

	public void run() {

		//go to corner first before fighting
		goCorner();
		//get number of other robot
		others = getOthers();

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

	public void onDeath(DeathEvent e) {
		// Well, others should never be 0, but better safe than sorry.
		if (others == 0) {
			return;
		}

		// If 75% of the robots are still alive when we die, we'll switch corners.
		if (getOthers() / (double) others >= .75) {
			corner += 90;
			if (corner == 270) {
				corner = -90;
			}
		}
	}
}