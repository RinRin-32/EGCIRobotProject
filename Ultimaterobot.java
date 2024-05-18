package aigroupwork;
import robocode.*;

import java.awt.*;

// Insert yourname here
// Burin Intachuen
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
	public void run() {
		// Initialization of the robot should be put here
		setColors(Color.pink, Color.meganta, Color.black);
		setAdjustGunForRobotTurn(true);
		setAdjustRadarForGunTurn(true);
		// After trying out your robot, try uncommenting the import at the top,
		// and the next line:

		// setColors(Color.red,Color.blue,Color.green); // body,gun,radar

		// Robot main loop
		while(true) {
			// Replace the next 4 lines with any behavior you would like
			
		}
	}

	/**
	 * onScannedRobot: What to do when you see another robot
	 */
	public void onScannedRobot(ScannedRobotEvent e) {
		// Replace the next line with any behavior you would like
		double distance = e.getgetDistance();
		if (distance < 200)
		{
			fire(2.5);
		}
		
	}

	public void onRobotDeath(RobotDeathEvent e) {
		if(


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
