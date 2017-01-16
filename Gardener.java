package prototype1;

import battlecode.common.*;


public class Gardener extends RobotPlayer {
    public static void playTurn() throws Exception{
        // Listen for home archon's location
        int xPos = rc.readBroadcast(0);
        int yPos = rc.readBroadcast(1);
        MapLocation archonLoc = new MapLocation(xPos,yPos);

        // Generate a random direction
        Direction dir = randomDirection();

        if (rc.canPlantTree(dir) && Math.random() < .01){
            rc.plantTree(dir);
        }



        // Randomly attempt to build a soldier or lumberjack in this direction
        if (rc.canBuildRobot(RobotType.SOLDIER, dir) && Math.random() < .01) {
            rc.buildRobot(RobotType.SOLDIER, dir);
        } else if (rc.canBuildRobot(RobotType.LUMBERJACK, dir) && Math.random() < .01 && rc.isBuildReady()) {
            rc.buildRobot(RobotType.LUMBERJACK, dir);
        }

        TreeInfo[] treeInfo = rc.senseNearbyTrees(2.0f, myTeam);
        System.out.println(treeInfo.length);

        if (treeInfo.length > 0){
            System.out.println("Tree in watering range");
            if (rc.canWater(treeInfo[0].location) && treeInfo[0].getMaxHealth() - treeInfo[0].getHealth() >= GameConstants.WATER_HEALTH_REGEN_RATE){
                rc.water(treeInfo[0].location);
                System.out.println("Watering a tree!");
            }
        }

        TreeInfo[] treeInfo2 = rc.senseNearbyTrees(-1, myTeam);

        if (treeInfo2.length > 0) {
            MapLocation treeToWaterLocation = treeInfo2[0].location;
            Direction directionToMove = rc.getLocation().directionTo(treeToWaterLocation);
            System.out.println("Moving towards a tree");
            tryMove(directionToMove);
            return;
        }
        tryMove(randomDirection());

    }
}
