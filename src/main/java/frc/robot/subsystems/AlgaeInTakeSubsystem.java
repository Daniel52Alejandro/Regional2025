package frc.robot.subsystems;

import com.revrobotics.spark.SparkLowLevel.MotorType;
import com.revrobotics.spark.SparkMax;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.MechanismsConstants;

public class AlgaeInTakeSubsystem extends SubsystemBase{
    private static SparkMax wheels = new SparkMax(MechanismsConstants.kAlgaeMotorPort, MotorType.kBrushless);
    
    public AlgaeInTakeSubsystem() { }

    public void in(double speed){
        wheels.set(speed);
    }

    public void out(double speed){
        wheels.set(-speed);
    }

    public void stop(){
        wheels.stopMotor();
    }
}
