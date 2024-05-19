package aigroupwork;
import robocode.*;
import com.maximgalushka.robocode.AdvancedEnemyBot;
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
	private AdvancedEnemyBot enemy = new AdvancedEnemyBot();
	public void run() {
		// Initialization of the robot should be put here
		setColors(Color.pink, Color.meganta, Color.black);
		setAdjustGunForRobotTurn(true);
		setAdjustRadarForGunTurn(true);
		enemy.reset()
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
			firegun();
			scan();
		}
	}

	/**
	 * onScannedRobot: What to do when you see another robot
	 */
	public void onScannedRobot(ScannedRobotEvent e) {
		// Replace the next line with any behavior you would like
		//double distance = e.getgetDistance();
		//if (distance < 200)
		//{
		//	fire(2.5);
		//}
		if (enemy.none() || e.getName().equals(enemy.getName())) 
		{
		enemy.update(e);
		}
		
	}

	public void onRobotDeath(RobotDeathEvent e) {
	if (e.getName().equals(enemy.getName())) {
		enemy.reset();
	}
}

	void scan() {
		setTurnRadarRight(360);
	}

	void fireGun() {

		// don't fire if there's no enemy
		if (enemy.none()) return;

		// convenience variable
		double max = Math.max(getBattleFieldHeight(), getBattleFieldWidth());
		// only shoot if we're (close to) pointing at our enemy
		if (Math.abs(getTurnRemaining()) < 10) {
			if (enemy.getDistance() < max / 2) {
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
