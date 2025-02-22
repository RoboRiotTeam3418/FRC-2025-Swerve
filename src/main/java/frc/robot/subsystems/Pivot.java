package frc.robot.subsystems;

import com.revrobotics.AbsoluteEncoder;
import com.revrobotics.spark.SparkLowLevel.MotorType;
import com.revrobotics.spark.SparkMax;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Pivot extends SubsystemBase {

   SparkMax PivotMotor;
   public AbsoluteEncoder encoder;
   
   public Pivot() {
      PivotMotor = new SparkMax(27, MotorType.kBrushless);
      encoder = PivotMotor.getAbsoluteEncoder();
   }
   public SparkMax getMotor() {
      return PivotMotor;
   }
   public AbsoluteEncoder getEncoder() {
      return encoder;
   }
   @Override
   public void periodic() {
      SmartDashboard.putNumber("pivot", encoder.getPosition()*360);
   }
}