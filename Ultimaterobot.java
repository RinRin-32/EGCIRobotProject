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
public class Ultimaterobot extends Robot
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
			peek = true;
			// Move up the wall
			ahead(moveAmount);
			// Don't look now
			peek = false;
			// Turn to the next wall
			turnRight(90);
			doGun();
			doScanner();
		}
	}

	/**
	 * onScannedRobot: What to do when you see another robot
	 */
	public void onScannedRobot(ScannedRobotEvent e) {
		// Replace the next line with any behavior you would like
		double distance = e.getDistance();
		fireGun();
		//if (distance < 200)
		//{
		//	fire(2.5);
		//}
	
		
	}


	void scan() {
		setTurnRadarRight(360);
	}

	void fireGun() {

		// don't fire if there's no enemy
		//if (e.none()) return;

		// convenience variable
		double max = Math.max(getBattleFieldHeight(), getBattleFieldWidth());
		// only shoot if we're (close to) pointing at our enemy
		if (Math.abs(getTurnRemaining()) < 10) {
			if (e.getDistance() < max / 2) {
				// fire hard when close
				setFire(2);
			} else {
				// otherwise, just plink him
				//setFire(1);
			}
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
// Code used from https://github.com/mgalushka/robocode-robots/blob/master/src/main/java/com/maximgalushka/robocode/AdvancedEnemyBot.java
// Code used from https://github.com/mgalushka/robocode-robots/blob/master/src/main/java/com/maximgalushka/robocode/EnemyBot.java
