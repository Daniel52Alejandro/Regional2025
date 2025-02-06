package frc.robot.subsystems;

import com.revrobotics.spark.SparkLowLevel.MotorType;
import com.revrobotics.spark.SparkMax;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class HandSubsystem extends SubsystemBase{

    private static SparkMax hand = new SparkMax(11, MotorType.kBrushless);
    DigitalInput leftSwitch = new DigitalInput(0);
    DigitalInput rightSwitch = new DigitalInput(0);

    public HandSubsystem() { }

    public void down(double speed){
        boolean stop = speed > 0 && (!leftSwitch.get() || !rightSwitch.get());

        if(stop){
            stop();
        } else {
            hand.set(speed);
        }
    }

    public void up(double speed){
        boolean stop = speed > 0 && (!leftSwitch.get() || !rightSwitch.get());

        if(stop){
            stop();
        } else {
            hand.set(-speed);
        }    
    }

    public boolean getBothSwitches(){
        return !leftSwitch.get() || !rightSwitch.get();
    }

    public void stop(){
        hand.set(0);
    }

    @Override
    public void periodic(){
        SmartDashboard.putBoolean("Right limitSwitch", rightSwitch.get());
        SmartDashboard.putBoolean("Left limitSwitch", leftSwitch.get());
    }
}
