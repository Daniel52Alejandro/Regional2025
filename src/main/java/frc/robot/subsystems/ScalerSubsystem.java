package frc.robot.subsystems;

import com.revrobotics.spark.SparkBase;
import com.revrobotics.spark.SparkLowLevel.MotorType;
import com.revrobotics.spark.config.SparkMaxConfig;
import com.revrobotics.spark.SparkMax;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.MechanismsConstants;

public class ScalerSubsystem extends SubsystemBase {
    private SparkMax scalerLeader = new SparkMax(MechanismsConstants.kScalerLeaderMotorPort, MotorType.kBrushless);
    private SparkMax scalerFollower = new SparkMax(MechanismsConstants.kScalerFollowerMotorPort, MotorType.kBrushless);

    public ScalerSubsystem() { 
        SparkMaxConfig scalerFollowerConfig = new SparkMaxConfig();
        scalerFollowerConfig.follow(scalerLeader, true);

        scalerFollower.configure(scalerFollowerConfig, SparkBase.ResetMode.kNoResetSafeParameters, SparkBase.PersistMode.kNoPersistParameters);
    }

    public void down(double speed) {
        scalerLeader.set(-speed);
    }

    public void up(double speed) {
        scalerLeader.set(speed);
    }

    public void stop() {
        scalerLeader.stopMotor();
    }
}