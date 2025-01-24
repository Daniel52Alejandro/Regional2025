package frc.robot.subsystems;

import com.revrobotics.spark.SparkLowLevel.MotorType;
import com.revrobotics.spark.SparkMax;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class HandSubsystem extends SubsystemBase{
    private static SparkMax hand = new SparkMax(11, MotorType.kBrushless);
    
    public HandSubsystem() { }

    public void down(double speed){
        hand.set(speed);
    }

    public void up(double speed){
        hand.set(-speed);
    }

    public void stop(){
        hand.set(0);
    }
}
