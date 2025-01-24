package frc.robot.subsystems;

import com.revrobotics.spark.SparkLowLevel.MotorType;
import com.revrobotics.spark.SparkMax;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class AlgaeInTakeSubsystem extends SubsystemBase{
    private static SparkMax wheels = new SparkMax(13, MotorType.kBrushless);
    
    public AlgaeInTakeSubsystem() { }

    public void in(double speed){
        wheels.set(speed);
    }

    public void out(double speed){
        wheels.set(-speed);
    }

    public void stop(){
        wheels.set(0);
    }
}
