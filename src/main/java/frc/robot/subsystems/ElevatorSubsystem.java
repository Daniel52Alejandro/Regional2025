package frc.robot.subsystems;

import com.revrobotics.spark.SparkBase;
import com.revrobotics.spark.SparkLowLevel.MotorType;
import com.revrobotics.spark.config.SparkMaxConfig;
import com.revrobotics.spark.SparkMax;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.MechanismsConstants;

public class ElevatorSubsystem extends SubsystemBase{
    private SparkMax elevatorLeader = new SparkMax(MechanismsConstants.kElevatorLeaderMotorPort, MotorType.kBrushless);
    private SparkMax elevatorFollower = new SparkMax(MechanismsConstants.kElevatorFollowerMotorPort, MotorType.kBrushless);

    public ElevatorSubsystem() { 
        SparkMaxConfig elevatorFollowerConfig = new SparkMaxConfig();
        elevatorFollowerConfig.follow(elevatorLeader, true);

        elevatorFollower.configure(elevatorFollowerConfig, SparkBase.ResetMode.kNoResetSafeParameters, SparkBase.PersistMode.kNoPersistParameters);
    }

    public void down(double speed) {
        elevatorLeader.set(-speed);
    }

    public void up(double speed) {
        elevatorLeader.set(speed);
    }

    public void stop() {
        elevatorLeader.set(0);
    }
}