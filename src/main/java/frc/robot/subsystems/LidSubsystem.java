package frc.robot.subsystems;

import com.revrobotics.spark.SparkLowLevel.MotorType;
import com.revrobotics.spark.SparkMax;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class LidSubsystem extends SubsystemBase{
    private static SparkMax lid = new SparkMax(12, MotorType.kBrushless);
    
    public LidSubsystem() { }

    public void down(double speed){
        lid.set(-speed);
    }

    public void up(double speed){
        lid.set(speed);
    }

    public void stop(){
        lid.set(0);
    }
}