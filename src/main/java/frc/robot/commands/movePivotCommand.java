package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.Pivot;

public class movePivotCommand extends Command{
    private final Pivot m_subsystem;
    private double targetPoint;
    private double deadband;

    /**
     * Creates a new ExampleCommand.
     *
     * @param subsystem The subsystem used by this command.
     */
    public movePivotCommand(Pivot subsystem, double target, double offset) {
      m_subsystem = subsystem;
      targetPoint=target;
        deadband=offset;
      // Use addRequirements() here to declare subsystem dependencies.
      addRequirements(subsystem);
    }
  
    // Called when the command is initially scheduled.
    @Override
    public void initialize() {

    }
  
    // Called every time the scheduler runs while the command is scheduled.
    @Override
    public void execute() {
        if (m_subsystem.getEncoder().getPosition()*360>targetPoint+deadband) {
            m_subsystem.getMotor().set(.25);
        } else if(m_subsystem.getEncoder().getPosition()*360<targetPoint-deadband) {
            m_subsystem.getMotor().set(-.25);
        } else {
            m_subsystem.getMotor().set(0);
        }
    }
  
    // Called once the command ends or is interrupted.
    @Override
    public void end(boolean interrupted) {}
  
    // Returns true when the command should end.
    @Override
    public boolean isFinished() {
      return false;
    }
}
