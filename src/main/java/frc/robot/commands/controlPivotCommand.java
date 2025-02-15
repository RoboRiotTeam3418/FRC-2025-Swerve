package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Setup;
import frc.robot.subsystems.pivot;

public class controlPivotCommand extends Command{
    private final pivot Pivot;
    public double pivotMotorSpeed;

    public controlPivotCommand(pivot pivotSubsystem) {
        this.Pivot = pivotSubsystem;
        addRequirements(pivotSubsystem);
    }

    @Override
    public void execute() {
        if (Setup.getInstance().getSecondaryManualPivot() < -.1 || Setup.getInstance().getSecondaryManualPivot() > .1){
            pivotMotorSpeed=Setup.getInstance().getSecondaryManualPivot();
            //Limits & Soft Stops
            if(Pivot.getPivotPosition() > 0 && Pivot.getPivotPosition() < 5){
                pivotMotorSpeed = -0.1;
            } else if(Pivot.getPivotPosition() > Pivot.pivotMaxAngle && Setup.getInstance().getSecondaryManualPivot() > 0){
                pivotMotorSpeed = 0;
            } else if (Pivot.getPivotPosition() < Pivot.pivotMinAngle && Setup.getInstance().getSecondaryManualPivot() < 0){
                pivotMotorSpeed = 0;
            } else if((Pivot.getPivotPosition() > (Pivot.pivotMaxAngle - 5)) || (Pivot.getPivotPosition() < (Pivot.pivotMinAngle + 5))){
                pivotMotorSpeed /=2;
            }

            //Double Speed when Pressed
            if(Setup.getInstance().getSecondaryRightStickPressed()){
                pivotMotorSpeed *= 2;
            }

        } else {
            pivotMotorSpeed=0;
        }
    Pivot.getPivotMotor().set(pivotMotorSpeed/2.2);
}
}
