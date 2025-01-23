package frc.robot.subsystems;

import com.ctre.phoenix6.hardware.CANcoder;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.spark.SparkBase;
import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.SparkLowLevel.MotorType;
import com.revrobotics.spark.config.EncoderConfig;
import com.revrobotics.spark.config.SparkMaxConfig;

import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.math.kinematics.SwerveModulePosition;
import edu.wpi.first.math.kinematics.SwerveModuleState;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Constants.DriveConstants;
import frc.robot.Constants.ModuleConstants;

public class SwerveModule {

    private final SparkMax driveMotor;
    private final SparkMax turningMotor;

    private final SparkMaxConfig driveMotorConfig;
    private final SparkMaxConfig turningMotorConfig;

    private final RelativeEncoder driveEncoder;
    private final RelativeEncoder turningEncoder;

    private final EncoderConfig driveEncoderConfig;
    private final EncoderConfig turningEncoderConfig;

    private final PIDController turningPidController;

    private CANcoder absoluteEncoder;
    private final boolean absoluteEncoderReversed;
    private final double absoluteEncoderOffsetRad;
    private int absoluteEncoderId;

    public SwerveModule(int driveMotorId, int turningMotorId, boolean driveMotorReversed, boolean turningMotorReversed, int absoluteEncoderId, double absoluteEncoderOffset, boolean absoluteEncoderReversed) {
        // Absolute CAN coder
        this.absoluteEncoderOffsetRad = absoluteEncoderOffset;
        this.absoluteEncoderReversed = absoluteEncoderReversed;
        this.absoluteEncoderId = absoluteEncoderId;
        this.absoluteEncoder = new CANcoder(absoluteEncoderId, "CANivore6702");

        // Spark Max motors
        this.driveMotor = new SparkMax(driveMotorId, MotorType.kBrushless);
        this.turningMotor = new SparkMax(turningMotorId, MotorType.kBrushless);

        // Motor Configuration
        this.driveMotorConfig = new SparkMaxConfig();
        this.driveMotorConfig.inverted(driveMotorReversed);

        this.driveEncoderConfig = new EncoderConfig();
        this.driveEncoderConfig.positionConversionFactor(ModuleConstants.kDriveEncoderRot2Meter);
        this.driveEncoderConfig.velocityConversionFactor(ModuleConstants.kDriveEncoderRPM2MeterPerSec);

        this.driveMotorConfig.apply(this.driveEncoderConfig);
        this.driveMotor.configure(this.driveMotorConfig, SparkBase.ResetMode.kNoResetSafeParameters, SparkBase.PersistMode.kNoPersistParameters);
        
        this.turningMotorConfig = new SparkMaxConfig();
        this.turningMotorConfig.inverted(turningMotorReversed);

        this.turningEncoderConfig = new EncoderConfig();
        this.turningEncoderConfig.positionConversionFactor(ModuleConstants.kTurningEncoderRot2Rad);
        this.turningEncoderConfig.velocityConversionFactor(ModuleConstants.kTurningEncoderRPM2RadPerSec);

        this.turningMotorConfig.apply(this.turningEncoderConfig);
        this.turningMotor.configure(this.turningMotorConfig, SparkBase.ResetMode.kNoResetSafeParameters, SparkBase.PersistMode.kNoPersistParameters);

        // Spark Max relative encoders
        this.driveEncoder = driveMotor.getEncoder();
        this.turningEncoder = turningMotor.getEncoder();

        resetEncoders();

        // PID
        this.turningPidController = new PIDController(ModuleConstants.kPTurning, 0, 0);
        this.turningPidController.enableContinuousInput(-Math.PI, Math.PI);
    }

    public SwerveModulePosition getPosition() {
        return new SwerveModulePosition(driveEncoder.getPosition(), new Rotation2d(turningEncoder.getPosition()));
    }

    public double getDrivePosition() {
        return driveEncoder.getPosition();
    }

    public double getTurningPosition() {
        return turningEncoder.getPosition();
    }

    public double getDriveVelocity() {
        return driveEncoder.getVelocity();
    }

    public double getTurningVelocity() {
        return turningEncoder.getVelocity();
    }

    public double getAbsoluteEncoderRad() {
        double angle = absoluteEncoder.getPosition().getValueAsDouble();

        SmartDashboard.putNumber("InitialAngle["+ absoluteEncoderId +"]", angle);

        angle *= 2.0 * Math.PI;
        angle -= (absoluteEncoderOffsetRad * 2.0 * Math.PI);

        return angle * (absoluteEncoderReversed ? -1.0 : 1.0);
    }

    public void resetEncoders() {
        driveEncoder.setPosition(0);
        turningEncoder.setPosition(getAbsoluteEncoderRad());
    }

    public SwerveModuleState getState() {
        return new SwerveModuleState(getDriveVelocity(), new Rotation2d(getTurningPosition()));
    }

    public void setDesiredState(SwerveModuleState state) {
        if (Math.abs(state.speedMetersPerSecond) < 0.001) {
            stop();
            return;
        }

        state.optimize(getState().angle);
        driveMotor.set(state.speedMetersPerSecond / DriveConstants.kPhysicalMaxSpeedMetersPerSecond);
        turningMotor.set(turningPidController.calculate(getTurningPosition(), state.angle.getRadians()));

        // SmartDashboard.putNumber("Speed["+ absoluteEncoderId +"]", state.speedMetersPerSecond);
        // SmartDashboard.putNumber("Angle[" + absoluteEncoderId + "]", state.angle.getDegrees());
    }

    public void stop() {
        driveMotor.set(0);
        turningMotor.set(0);
    }
}