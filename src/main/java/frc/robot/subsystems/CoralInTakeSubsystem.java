package frc.robot.subsystems;

import com.revrobotics.spark.SparkLowLevel.MotorType;
import com.revrobotics.spark.SparkMax;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class CoralInTakeSubsystem extends SubsystemBase{
    private static SparkMax clawWheels = new SparkMax(9, MotorType.kBrushless);
    
    public CoralInTakeSubsystem() { }

    public void in(double speed){
        clawWheels.set(speed);
    }

    public void out(double speed){
        clawWheels.set(-speed);
    }

    public void stop(){
        clawWheels.set(0);
    }
}
