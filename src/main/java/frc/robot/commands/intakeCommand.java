package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.GroundIntake;
import frc.robot.subsystems.shooter;

public class intakeCommand extends Command {
    double value;
    GroundIntake intake;
    shooter shooty;
    public intakeCommand(double intakeValue, GroundIntake subsystem, shooter shoot){
        this.value=intakeValue;
        this.intake=subsystem;
        this.shooty=shoot;
        addRequirements(subsystem);
    }
    @Override
    public void execute(){
        intake.Intake(value);   
        shooty.feedForward(value);
    }
    @Override
    public boolean isFinished(){
        return (!intake.getNoteInShooter());
    }
    @Override
    public void end(boolean interrupted) {
        intake.Intake(0);
        shooty.feedForward(0);
    }
}
