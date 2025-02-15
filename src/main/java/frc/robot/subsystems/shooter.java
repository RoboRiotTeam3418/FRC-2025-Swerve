package frc.robot.subsystems;

import com.revrobotics.spark.SparkMax;
import com.revrobotics.RelativeEncoder;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import com.revrobotics.spark.SparkLowLevel.MotorType;
import frc.robot.Setup;

public class shooter extends SubsystemBase{
    //get instance
    private static shooter instance;

    public static shooter getInstance() {
            if (instance == null) {
                    instance = new shooter();
            }
            return instance;
    }

    //variables
    public SparkMax feedingMotorLeft;
    public SparkMax feedingMotorRight;
    public SparkMax flywheelMotorTop;
    public SparkMax flywheelMotorBottom;
    public RelativeEncoder shooterEncoderTop;
    public RelativeEncoder shooterEncoderBottom;
    public PIDController shooterController;

    public double leftTrig, rightTrig, PIDValue;
    public boolean leftShoot, rightShoot, timeToShoot; 
    public double speakerTargetSpeed = -5000;
    public double ampTargetSpeed = .5;
    public final double shooterP = 0.0002;
    public final double shooterI = 0.0001;
    public final double shooterD = 0;

    public shooter(){
        feedingMotorLeft = new SparkMax(Setup.ShooterFeedingMotorLeftID, MotorType.kBrushless);
        feedingMotorRight = new SparkMax(Setup.ShooterFeedingMotorRightID, MotorType.kBrushless);
        flywheelMotorTop = new SparkMax(Setup.ShooterFlywheelMotorTopID, MotorType.kBrushless);
        flywheelMotorBottom = new SparkMax(Setup.ShooterFlywheelMotorBotttomID,MotorType.kBrushless);
        shooterController = new PIDController(shooterP, shooterI, shooterD);
        shooterEncoderTop = flywheelMotorTop.getEncoder();
        shooterEncoderBottom = flywheelMotorBottom.getEncoder();
        shooterController.setSetpoint(speakerTargetSpeed);
        //shooterController.setTolerance(50, 20);
    }

    public double getTopShooterEncoder(){
        return shooterEncoderTop.getVelocity();
    }
    public double getBottomShooterEncoder(){
        return shooterEncoderBottom.getVelocity();
    }

    public void speakerShoot(){
        PIDValue = shooterController.calculate(getTopShooterEncoder(),speakerTargetSpeed);
        flywheelMotorTop.set(PIDValue);
        flywheelMotorBottom.set(PIDValue);
    }

    public void ampShoot(){
        flywheelMotorTop.set(-ampTargetSpeed);
        flywheelMotorBottom.set(-ampTargetSpeed);
    }

    public void feedForward(double speed){
        feedingMotorLeft.set(speed);
        feedingMotorRight.set(-speed);
    }

     /*public void updateSubsystem(){
        rightShoot = Setup.getInstance().getSecondarySpeakerShoot(); 
        rightTrig = Setup.getInstance().getSecondarySpeaker();

        leftShoot = Setup.getInstance().getSecondaryAmpShoot(); 
        leftTrig = Setup.getInstance().getSecondaryAmp();

        if (rightTrig >= 0.7){ // flywheel control
            speakerShoot();
        } else {
            flywheelMotorTop.set(0);
            flywheelMotorBottom.set(0);
        }

       
        if (Setup.getInstance().getPrimaryGroundIntake()/*&& GroundIntake.getInstance().getNoteInShooter()*/ /* == true){ // Intake
            feedForward(1);
        } else if (Setup.getInstance().getPrimaryOuttake()){ // Outtake
            feedBackward(1);
        } else if(rightShoot){
            feedForward(1);
        } else if(leftShoot){
            feedForward(.5);
        } else {
            feedingMotorLeft.set(0);
            feedingMotorRight.set(0);
        }
    
    }*/

    public void outputToSmartDashboard(){
        SmartDashboard.putNumber("TopPID", getTopShooterEncoder());
        SmartDashboard.putNumber("BottomPID",getBottomShooterEncoder());
    }

    public void stop(){
        feedingMotorLeft.set(0);
        feedingMotorRight.set(0);
        flywheelMotorTop.set(0);
        flywheelMotorBottom.set(0);
    }
}
