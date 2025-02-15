package frc.robot.commands;

import com.revrobotics.spark.SparkMax;
import com.revrobotics.RelativeEncoder;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Setup;
import frc.robot.subsystems.shooter;

public class shootCommand extends Command{
    public SparkMax feedingMotorLeft;
    public SparkMax feedingMotorRight;
    public SparkMax flywheelMotorTop;
    public SparkMax flywheelMotorBottom;
    public RelativeEncoder shooterEncoderTop;
    public RelativeEncoder shooterEncoderBottom;
    public PIDController shooterController;
    double speakerTargetSpeed;
    Setup control;

    private final shooter shoot;
    public shootCommand(shooter Shooter){
        this.shoot=Shooter;
        addRequirements(Shooter);
    }
    @Override
    public void initialize() {
        feedingMotorLeft = shoot.feedingMotorLeft;
        feedingMotorRight = shoot.feedingMotorRight;
        flywheelMotorTop = shoot.flywheelMotorTop;
        flywheelMotorBottom = shoot.flywheelMotorBottom;
        shooterEncoderTop = shoot.shooterEncoderTop;
        shooterEncoderBottom = shoot.shooterEncoderBottom;
        shooterController = shoot.shooterController;
        speakerTargetSpeed = shoot.speakerTargetSpeed;
        shooterController.setSetpoint(speakerTargetSpeed);
        control = Setup.getInstance();
    }
    @Override
    public void execute() {
        if (control.getSecondarySpeaker() >= 0.7){ // flywheel control
            shoot.speakerShoot();
        } else if (control.getSecondaryAmp() >= 0.7) {
            shoot.ampShoot();
        } else {
            flywheelMotorTop.set(0);
            flywheelMotorBottom.set(0);
        }

        if (Setup.getInstance().getPrimaryGroundIntake() /*&& GroundIntake.getInstance().getNoteInShooter() */== true){ // Intake
            shoot.feedForward(1);
        } else if (Setup.getInstance().getPrimaryOuttake()){ // Outtake
            shoot.feedForward(-1);
        } else if(control.getSecondarySpeakerShoot()){
            shoot.feedForward(1);
        } else if(control.getSecondaryAmpShoot()){
            shoot.feedForward(.5);
        } else {
            feedingMotorLeft.set(0);
            feedingMotorRight.set(0);
        }
    }
}
