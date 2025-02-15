package frc.robot;

import java.io.IOException;

import org.json.simple.parser.ParseException;

import com.pathplanner.lib.config.RobotConfig;

import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.math.kinematics.SwerveDriveKinematics;
import edu.wpi.first.wpilibj.Joystick;
import frc.robot.util.drivers.Gyroscope;
import frc.robot.util.drivers.NavX;

public class Setup {
    
  public static Setup instance = new Setup();

  public static Setup getInstance() {
    if (instance == null) {
      instance = new Setup();
    }
	  return instance;
  }   

 //---------------------------------------------------------Swerve Drive------------------------------------------------------------------

  //measurments of robot in meters from center of wheel (19.25 inches squared, 39.37 inches in a meter)
  public double TRACKWIDTH = 0.607;
  public double WHEELBASE = 0.597;
    
  //offset of wheels sets the angle to start - CHANGE DIS BRO
  /*public double FRONT_RIGHT_ANGLE_OFFSET = -Math.toRadians(123.579545);
  public double FRONT_LEFT_ANGLE_OFFSET = -Math.toRadians(260.795455);
  public double BACK_RIGHT_ANGLE_OFFSET = -Math.toRadians(0);
  public double BACK_LEFT_ANGLE_OFFSET = -Math.toRadians(18.579545);*/
  public double FRONT_RIGHT_ANGLE_OFFSET = -Math.toRadians(0);
  public double FRONT_LEFT_ANGLE_OFFSET = -Math.toRadians(0);
  public double BACK_RIGHT_ANGLE_OFFSET = -Math.toRadians(0);
  public double BACK_LEFT_ANGLE_OFFSET = -Math.toRadians(0);
  
  
/* */
  //finds position of the wheels based on the position of the center
  public final SwerveDriveKinematics kinematics = new SwerveDriveKinematics(
    new Translation2d(-TRACKWIDTH / 2.0, -WHEELBASE / 2.0),
    new Translation2d(-TRACKWIDTH / 2.0, WHEELBASE / 2.0),        
    new Translation2d(TRACKWIDTH / 2.0, -WHEELBASE / 2.0),
    new Translation2d(TRACKWIDTH / 2.0, WHEELBASE / 2.0)
  );

 //----------------------------------------------------------Primary----------------------------------------------------------------------

  //Flight Stick (Primary)
  public static Joystick primaryJoystick = new Joystick(0);

  public Joystick getPrimaryJoystick() {
    return primaryJoystick;
  }
  

  //movement
  public double getPrimaryX(){
    return primaryJoystick.getRawAxis(0);
  }
  
  public double getPrimaryY(){
    return primaryJoystick.getRawAxis(1);
  }
  
  public double getPrimaryZ(){
    return primaryJoystick.getRawAxis(5);
  }
  

  //speed
  public boolean getPrimaryDriverXButton(){
    return primaryJoystick.getRawButton(5);
  }

  public boolean getPrimaryDriverAButton(){
    return primaryJoystick.getRawButton(6);
  }

  public boolean getPrimaryDriverBButton(){
    return primaryJoystick.getRawButton(7);
  }

  public boolean getPrimaryDriverYButton(){
    return primaryJoystick.getRawButton(8);
  }


  //ground intake
  public boolean getPrimaryGroundIntake(){
    return primaryJoystick.getRawButton(1);
  }

  public boolean getPrimaryOuttake(){
    return primaryJoystick.getRawButton(10);
  }




  //field oriented
  public boolean getFieldOriented(){
    return primaryJoystick.getRawButtonPressed(4);
  }

  public boolean getDeathButton(){
    return primaryJoystick.getRawButton(2);
  }

  //-----------------------------------------------------secondary--------------------------------------------------------------------
  
  //Xbox Controller (Secondary)
  public static Joystick secondaryJoystick = new Joystick(1);

  public Joystick getSecondaryJoystick() {
    return secondaryJoystick;
  }


  //Pivot
  public boolean getSecondaryAButton(){
    return secondaryJoystick.getRawButton(1);
  }

  public boolean getSecondaryBButton(){
    return secondaryJoystick.getRawButton(2);
  }

  public boolean getSecondaryXButton(){
    return secondaryJoystick.getRawButton(3);
  }

  public boolean getSecondaryYButton(){
    return secondaryJoystick.getRawButton(4);
  }

  public double getSecondaryManualPivot(){
    return secondaryJoystick.getRawAxis(5);
  }

  public boolean getSecondaryRightStickPressed(){
    return secondaryJoystick.getRawButton(10);
  }


  //Shooter
  public double getSecondarySpeaker(){
    return secondaryJoystick.getRawAxis(3);
  }

  public boolean getSecondarySpeakerShoot(){
    return secondaryJoystick.getRawButton(6);
  }
    
  public double getSecondaryAmp(){
    return secondaryJoystick.getRawAxis(2);
  }

  public boolean getSecondaryAmpShoot(){
    return secondaryJoystick.getRawButton(5);
  }


  //Climber
  public boolean getSecondaryToggleClimberMode(){
    return secondaryJoystick.getRawButtonPressed(8);
  }

  public double getSecondaryRightClimber(){
    return secondaryJoystick.getRawAxis(5);
  }

  public double getSecondaryLeftClimber(){
    return secondaryJoystick.getRawAxis(1);
  }

  //---------------------------------------------------------Hardware------------------------------------------------------------------------

  //Gyroscope
  public final Gyroscope gyroscope = NavX.getInstance();

  //-----------------------------------------------------------IDs------------------------------------------------------------------------------

  //Swerve Drive
  public static final int DrivetrainSubsystem_FRONT_LEFT_DRIVE_MOTOR = 10; 
  public static final int DrivetrainSubsystem_FRONT_LEFT_ANGLE_MOTOR = 11; 

  public static final int DrivetrainSubsystem_BACK_LEFT_DRIVE_MOTOR = 12; 
  public static final int DrivetrainSubsystem_BACK_LEFT_ANGLE_MOTOR = 13; 

  //wired wrong so bad
  public static final int DrivetrainSubsystem_BACK_RIGHT_DRIVE_MOTOR = 15; 
  public static final int DrivetrainSubsystem_BACK_RIGHT_ANGLE_MOTOR = 14; 

  public static final int DrivetrainSubsystem_FRONT_RIGHT_DRIVE_MOTOR = 16; 
  public static final int DrivetrainSubsystem_FRONT_RIGHT_ANGLE_MOTOR = 17; 
  
    //Pivot
    public static final int PivotMotorID = 20;
    
    //Ground Intake
    public static final int IntakeMotorFrontID = 21;
    public static final int IntakeMotorBackID = 22;
    public static final int GamePieceSensorID = 0;
  
    //Shooter
    public static final int ShooterFeedingMotorLeftID = 23;
    public static final int ShooterFeedingMotorRightID = 24;
    public static final int ShooterFlywheelMotorTopID = 25;
    public static final int ShooterFlywheelMotorBotttomID = 26;
  
    //Climber
    public static final int ClimberMotorLeftID = 27;
    public static final int ClimberMotorRightID = 28;
    public static final int ClimberPotentiometerLeft = 1;
    public static final int ClimberPotentiometerRight = 2;
    
    //Acceleration and velocity max for pid config VALUES CURRENTLY BASED ON NOTHING
  public static final double maxVel = 0.5;
  public static final double maxAccel = 0.2;
    //PathPlanner
    /*public static final double maxSpeeds = 1;
    public static final HolonomicPathFollowerConfig pathFollowConfig = new HolonomicPathFollowerConfig(
      new PidConstants(0.5, 0.0, 0.1), 
      new PidConstants(0.5, 0.0, 0.1), 
      maxSpeeds, 
      new Translation2d(0.6 / 2.0, 0.6 / 2.0).getNorm(), 
      new ReplanningConfig());*/
    
}