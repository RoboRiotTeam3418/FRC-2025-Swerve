package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.pivot;

public class setPivotCommand extends Command{
    private final pivot Pivot;
    private double pivotTarget;
    //Speed for preset angle positions
    public double buttonSpeed = 0.5;
    public double slowButtonSpeed = 0.15;
    public double extraSlowButtonSpeed = 0.025;

    public setPivotCommand(pivot pivotSubsystem, double target) {
        this.Pivot = pivotSubsystem;
        this.pivotTarget = target;
        addRequirements(pivotSubsystem);
    }

    @Override
    public void execute() {
        if(Pivot.getPivotPosition() < pivotTarget){

            if((Pivot.getPivotPosition() > (pivotTarget - Pivot.extraSlowZone))){
               Pivot.getPivotMotor().set(extraSlowButtonSpeed);
            } else if(Pivot.getPivotPosition() > (pivotTarget - Pivot.slowZone)){
               Pivot.getPivotMotor().set(slowButtonSpeed);
            } else{
            Pivot.getPivotMotor().set(buttonSpeed);
            }

         } else if(Pivot.getPivotPosition() > pivotTarget){

            if(Pivot.getPivotPosition() < (pivotTarget + Pivot.extraSlowZone)){
               Pivot.getPivotMotor().set(-extraSlowButtonSpeed);
            } else if(Pivot.getPivotPosition() < (pivotTarget + Pivot.slowZone)){
               Pivot.getPivotMotor().set(-slowButtonSpeed);
            } else{
            Pivot.getPivotMotor().set(-buttonSpeed);
            }
         }
}
    @Override
    public void end(boolean interrupted) {
        Pivot.getPivotMotor().set(0);
    }
    @Override
    public boolean isFinished() {
        return (Pivot.getPivotPosition()>pivotTarget-1&&Pivot.getPivotPosition()<pivotTarget+1);
    }
}
