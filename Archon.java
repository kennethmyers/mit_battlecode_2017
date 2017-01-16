package prototype1;

import battlecode.common.*;


public class Archon extends RobotPlayer {
    public static void playTurn() throws Exception {

        // Generate a random direction
        Direction dir = randomDirection();

        // Randomly attempt to build a gardener in this direction
        if (rc.canHireGardener(dir) && Math.random() < .01) {
            rc.hireGardener(dir);
        }

        // Move randomly
        tryMove(randomDirection());

        // Broadcast archon's location for other robots on the team to know
        MapLocation myLocation = rc.getLocation();
        rc.broadcast(0,(int)myLocation.x);
        rc.broadcast(1,(int)myLocation.y);

    }

}
