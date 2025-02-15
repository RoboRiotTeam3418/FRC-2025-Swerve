package frc.robot.util.drivers;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableInstance;

/* List of Important Values to get (limelight.getTable().getEntry(<put desired value here in quotes>).getDouble(0.0);)
 * "tv" is there a target
 * "tx" X offset of apriltag in degrees
 * "ty" Y offset of apriltag in degrees
 * "ta" area of apriltag on camera (finds the distance between the AprilTag and the LimeLight)
 * more can be found on the official website at https://docs.limelightvision.io/en/latest/networktables_api.html
 */
public class Limelight {

    //get instance
    private static Limelight instance;
    
    public static Limelight getInstance() {
        if(instance == null) {
            instance = new Limelight();
        }
        return instance;
    }
    public NetworkTable table = NetworkTableInstance.getDefault().getTable("limelight");
    
    //sees if the limelight sees any acceptable targets
    public boolean Target() {
        return table.getEntry("tv").getDouble(0.0)==1;
    }

    //changes the limelight pipeline
    public void setPipe(int pipeNum) {
        NetworkTableInstance.getDefault().getTable("limelight").getEntry("pipeline").setNumber(pipeNum);
    }
    //untested
    public double getXOffset() {
        return table.getEntry("tx").getDouble(0.0);
    }
    public double getYOffset() {
        return table.getEntry("ty").getDouble(0.0);
    }
    /**
     * useful for determining distance from apriltag
     * @return the area the apriltag takes up in the limelight
     */
    public double getArea() {
        return table.getEntry("ta").getDouble(0.0);
    }
}
