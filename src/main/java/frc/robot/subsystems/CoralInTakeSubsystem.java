package frc.robot.subsystems;

import com.revrobotics.spark.SparkLowLevel.MotorType;
import com.revrobotics.spark.SparkMax;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.MechanismsConstants;

public class CoralInTakeSubsystem extends SubsystemBase{
    private static SparkMax clawWheels = new SparkMax(MechanismsConstants.kCoralMotorPort, MotorType.kBrushless);
    
    public CoralInTakeSubsystem() { }

    public void in(double speed){
        clawWheels.set(speed);
    }

    public void out(double speed){
        clawWheels.set(-speed);
    }

    public void stop(){
        clawWheels.stopMotor();
    }
}
