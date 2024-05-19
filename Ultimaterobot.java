package aigroupwork;
import robocode.*;
import robocode.HitRobotEvent;
import robocode.ScannedRobotEvent;
import robocode.Robot;
import java.awt.*;
// Insert yourname here
// Burin Intachuen
// Mhadhanagul Charoenphon
// Possathorn Sujipisut

//import java.awt.Color;

// API help : https://robocode.sourceforge.io/docs/robocode/robocode/Robot.html

/**
 * Ultimaterobot - a robot by (your name here)
 */
public class Ultimaterobot extends AdvancedRobot
{
	/**
	 * run: Ultimaterobot's default behavior
	 */
	boolean peek;
	double moveAmount;
	public void run() {
		// Initialization of the robot should be put here
		setColors(Color.pink, Color.magenta, Color.black);
		setAdjustGunForRobotTurn(true);
		setAdjustRadarForGunTurn(true);
		moveAmount = Math.max(getBattleFieldWidth(), getBattleFieldHeight());
		peek = false;
		turnLeft(getHeading() % 90);
		ahead(moveAmount);
		peek = true;
		turnGunRight(90);
		turnRight(90);
		

		// After trying out your robot, try uncommenting the import at the top,
		// and the next line:

		// setColors(Color.red,Color.blue,Color.green); // body,gun,radar

		// Robot main loop
		while(true) {
			// Look before we turn when ahead() completes.
			turnGunRight(180);
			peek = true;
			// Move up the wall
			ahead(moveAmount);
			// Don't look now
			peek = false;
			// Turn to the next wall
			turnRight(90);
		}
	}

	/**
	 * onScannedRobot: What to do when you see another robot
	 */
	public void onScannedRobot(ScannedRobotEvent e) {
		// Replace the next line with any behavior you would like
		double distance = e.getDistance();
		if (distance < 200)
		{
			fire(2.5);
		}
		else if (200 < distance && distance < 300)
		{
			fire(1.0);
		}
	
		
	}

	/**
	 * onHitByBullet: What to do when you're hit by a bullet
	 */
	public void onHitByBullet(HitByBulletEvent e) {
		// Replace the next line with any behavior you would like
		back(10);
	}
	
	/**
	 * onHitWall: What to do when you hit a wall
	 */
	public void onHitWall(HitWallEvent e) {
		// Replace the next line with any behavior you would like
		back(20);
	}	
}
