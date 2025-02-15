package frc.robot.subsystems;

import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.SparkLowLevel.MotorType;
import edu.wpi.first.wpilibj.AnalogPotentiometer;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Setup;

public class Climber extends SubsystemBase{

    private static Climber instance;

        public static Climber getInstance() {
                if (instance == null) {
                        instance = new Climber();
                }
                return instance;
        }

    //Varibales
    AnalogPotentiometer ClimberPotentiometerLeft;
    AnalogPotentiometer ClimberPotentiometerRight;
    public SparkMax LeftClimbermotor;
    public SparkMax RightClimbermotor;
    
    double LY, RY;
    double LPot, RPot;
    public double leftMin = 14;
    public double rightMin = 14;
    public double leftMax = 35;
    public double rightMax = 32.5;
    double speed = 1;

    public double getPotentiometerLeft(){
        return ClimberPotentiometerLeft.get();
    }

    public double getPotentiometerRight(){
        return ClimberPotentiometerRight.get();
    }

    public Climber(){
        //scaled in quargajohns
        LeftClimbermotor = new SparkMax(Setup.ClimberMotorLeftID, MotorType.kBrushless);
        //ClimberPotentiometerLeft = new AnalogPotentiometer(1,100);
        RightClimbermotor = new SparkMax(Setup.ClimberMotorRightID, MotorType.kBrushless);
        //ClimberPotentiometerRight = new AnalogPotentiometer(2,100);
    }
/* 
    @Override
    public void updateSubsystem(){

        if(Pivot.getInstance().getPivotPosition()<330 && Pivot.getInstance().getPivotPosition()>5){
            Pivot.getInstance().pivotMotor.set(.1);
        } else {
            Pivot.getInstance().pivotMotor.set(0);
        }


        LY = Setup.getInstance().getSecondaryLeftClimber();
        RY = Setup.getInstance().getSecondaryRightClimber();
        LPot = ClimberPotentiometerLeft.get();
        RPot = ClimberPotentiometerRight.get();


        //deadband
        if(LY > -0.1 && LY < 0.1){
            LY = 0;
        }
        if (RY > -0.1 && RY < 0.1){
            RY = 0;
        }

        //Left Limits
        if (getPotentiometerLeft()>=leftMax && LY<0) {
            LeftClimbermotor.set(0);
        } else if (getPotentiometerLeft()<=leftMin && LY>0) {
            LeftClimbermotor.set(0);
        } else {
            LeftClimbermotor.set(LY*speed);
        }

        //Right Limits
        if (getPotentiometerRight()>=rightMax && RY<0) {
            RightClimbermotor.set(0);
         } else if (getPotentiometerRight()<=rightMin && RY>0) {
            RightClimbermotor.set(0);
        } else {
            RightClimbermotor.set(-RY*speed);
        }
    }

    @Override
    public void outputToSmartDashboard(){
        SmartDashboard.putNumber("ClimberPotentiometerLeft", LPot);
        SmartDashboard.putNumber("ClimberPotentiometerRight", RPot);
    }

    @Override
    public void stop(){
        RightClimbermotor.set(0);
        LeftClimbermotor.set(0);
    }*/
}