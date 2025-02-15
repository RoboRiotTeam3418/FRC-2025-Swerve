package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Setup;
import frc.robot.subsystems.Climber;

public class climbCommand extends Command{
    Climber climber;
    double LPot;
    double RPot;
    double speed = 1;
    double LY;
    double RY;

    public climbCommand(Climber subsystem) {
        this.climber=subsystem;
        addRequirements(climber);
        }
    @Override
    public void execute() {
        //LPot = climber.getPotentiometerRight();
        //RPot = climber.getPotentiometerLeft();
        LY = Setup.primaryJoystick.getRawAxis(2);
        RY = Setup.primaryJoystick.getRawAxis(6);

        //Left Limits
        /*if (climber.getPotentiometerLeft()>=climber.leftMax && LY<0) {
            climber.LeftClimbermotor.set(0);
        } else if (climber.getPotentiometerLeft()<=climber.leftMin && LY>0) {
            climber.LeftClimbermotor.set(0);
        } else {*/
            climber.LeftClimbermotor.set(LY*speed);
        //}

        //Right Limits
        /*if (climber.getPotentiometerRight()>=climber.rightMax && RY<0) {
            climber.RightClimbermotor.set(0);
         } else if (climber.getPotentiometerRight()<=climber.rightMin && RY>0) {
            climber.RightClimbermotor.set(0);
        } else {*/
            climber.RightClimbermotor.set(-RY*speed);
        //}
    }
}
