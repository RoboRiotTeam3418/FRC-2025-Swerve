// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.swervedrive.drivebase;
import frc.robot.Constants;
import frc.robot.Setup;
import frc.robot.Constants.OperatorConstants;
import frc.robot.subsystems.SwerveSubsystem;
import swervelib.SwerveInputStream;

import java.util.Hashtable;
import java.util.Dictionary;
import java.util.ArrayList;

import edu.wpi.first.math.filter.SlewRateLimiter;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Subsystem;
import edu.wpi.first.wpilibj2.command.button.CommandJoystick;

/** An example command that uses an example subsystem. */
public class SpeedChanger extends Command {
  @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})
  public final Dictionary<String, Double[]> speedKeys = new Hashtable<>();
  public SwerveSubsystem m_subsystem;
  public String m_speed;
  public CommandJoystick m_primary;
  public SlewRateLimiter xFilter;
  SlewRateLimiter yFilter;

  /**
   * Creates a new ExampleCommand.
   *
   * @param subsystem The subsystem used by this command.
   * @param joyInput specific axis value
   * @param XOrY x is true, y is false
   */
  public SpeedChanger(SwerveSubsystem subsystem,String speed) {
    // Use addRequirements() here to declare subsystem dependencies.
    m_speed = speed;
    m_subsystem=subsystem;
    m_primary = Setup.getInstance().getPrimaryJoystick();
    addRequirements(m_subsystem);

  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    // Adding key-value pairs
    speedKeys.put("xtraSlow", Constants.SpeedChangerConstants.xtraSlowSpeeds);
    speedKeys.put("slow", Constants.SpeedChangerConstants.slowSpeeds);
    speedKeys.put("medium", Constants.SpeedChangerConstants.medSpeeds);
    speedKeys.put("fast", Constants.SpeedChangerConstants.fastSpeeds);
    xFilter = new SlewRateLimiter(Constants.SpeedChangerConstants.get(m_speed)[0],-0.9,0);
    yFilter = new SlewRateLimiter(Constants.SpeedChangerConstants.get(m_speed)[0], -0.9,0);
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    SwerveInputStream driveAngularVelocity = SwerveInputStream.of(m_subsystem.getSwerveDrive(),
                                                                () -> m_primary.getX()*Constants.SpeedChangerConstants.get(m_speed)[0],// CHECK FUNCTION
                                                                () -> m_primary.getY()*Constants.SpeedChangerConstants.get(m_speed)[0])// CHECK FUNCTION
                                                            .withControllerRotationAxis(m_primary::getTwist)
                                                            .deadband(OperatorConstants.DEADBAND)
                                                            .scaleTranslation(0.8)
                                                            .allianceRelativeControl(true);

    m_subsystem.driveFieldOriented(driveAngularVelocity);

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
