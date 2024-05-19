package aigroupwork;
import robocode.*;
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

//Taken from https://github.com/mgalushka/robocode-robots/blob/master/src/main/java/com/maximgalushka/robocode/AdvancedEnemyBot.java
public class AdvancedEnemyBot extends EnemyBot{

    private double x;
    private double y;

    @Override
    public void reset() {
        super.reset();

        x = 0.0;
        y = 0.0;
    }

    public void update(ScannedRobotEvent e, Robot robot) {
        super.update(e);

        double absBearingDeg = (robot.getHeading() + e.getBearing());
        if (absBearingDeg < 0) absBearingDeg += 360;

        // yes, you use the _sine_ to get the X value because 0 deg is North
        x = robot.getX() + Math.sin(Math.toRadians(absBearingDeg)) * e.getDistance();

        // yes, you use the _cosine_ to get the Y value because 0 deg is North
        y = robot.getY() + Math.cos(Math.toRadians(absBearingDeg)) * e.getDistance();
    }

    public double getFutureX(long when){
        return x + Math.sin(Math.toRadians(getHeading())) * getVelocity() * when;
    }

    public double getFutureY(long when){
        return y + Math.cos(Math.toRadians(getHeading())) * getVelocity() * when;
    }

    public double getFutureT(Robot robot, double bulletVelocity){

        // enemy velocity
        double v_E = getVelocity();

        // temp variables
        double x_diff = x - robot.getX();
        double y_diff = y - robot.getY();

        // angles of enemy's heading
        double sin = Math.sin(Math.toRadians(getHeading()));
        double cos = Math.cos(Math.toRadians(getHeading()));

        // calculated time
        double T;
        double v_B = bulletVelocity;

        double xy = (x_diff*sin + y_diff*cos);

        T = ( (v_E*xy) + Math.sqrt(sqr(v_E)*sqr(xy) + (sqr(x_diff) + sqr(y_diff))*(sqr(v_B) + sqr(v_E))) ) / (sqr(v_B) - sqr(v_E));

        return T;

    }

    private static double sqr(double in){
        return in * in;
    }
}



/**
 * <p></p>
 * Taken from 
 * @author Maxim Galushka
 * @since 15.06.12
 */
public class EnemyBot extends Robot {

    private volatile double bearing;
    private volatile double distance;
    private volatile double energy;
    private volatile double heading;
    private volatile String name = "";
    private volatile double velocity;

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getBearing() {
        return bearing;
    }

    public void setBearing(double bearing) {
        this.bearing = bearing;
    }

    public double getEnergy() {
        return energy;
    }

    public void setEnergy(double energy) {
        this.energy = energy;
    }

    public double getHeading() {
        return heading;
    }

    public void setHeading(double heading) {
        this.heading = heading;
    }

    public double getVelocity() {
        return velocity;
    }

    public void setVelocity(double velocity) {
        this.velocity = velocity;
    }

    public void reset() {
        bearing = 0.0;
        distance = 0.0;
        energy = 0.0;
        heading = 0.0;
        name = "";
        velocity = 0.0;

        System.out.printf("Reset tracked bot!\n");
    }


    public boolean none() {
        System.out.printf("None tracked!\n");
        return "".equals(name);
    }

    public void update(ScannedRobotEvent e) {
        bearing = e.getBearing();
        distance = e.getDistance();
        energy = e.getEnergy();
        heading = e.getHeading();
        name = e.getName();
        velocity = e.getVelocity();
    }

    @Override
    public String toString() {
        return "EnemyBot{" +
                "bearing=" + bearing +
                ", distance=" + distance +
                ", energy=" + energy +
                ", heading=" + heading +
                ", name='" + name + '\'' +
                ", velocity=" + velocity +
                '}';
    }
}
