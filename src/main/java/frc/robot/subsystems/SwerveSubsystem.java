package frc.robot.subsystems;
import com.studica.frc.AHRS;

import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.kinematics.SwerveDriveKinematics;
import edu.wpi.first.math.kinematics.SwerveModulePosition;
import edu.wpi.first.math.kinematics.SwerveModuleState;
import edu.wpi.first.math.kinematics.SwerveDriveOdometry;
import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.AnalogPotentiometer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.DriveConstants;

public class SwerveSubsystem extends SubsystemBase {
  private final SwerveModule frontLeft = new SwerveModule(
    DriveConstants.kFrontLeftDriveMotorPort,
    DriveConstants.kFrontLeftTurningMotorPort,
    DriveConstants.kFrontLeftDriveEncoderReversed,
    DriveConstants.kFrontLeftTurningEncoderReversed,
    DriveConstants.kFrontLeftDriveAbsoluteEncoderPort,
    DriveConstants.kFrontLeftDriveAbsoluteEncoderOffsetRad,
    DriveConstants.kFrontLeftDriveAbsoluteEncoderReversed
  );

  private final SwerveModule frontRight = new SwerveModule(
    DriveConstants.kFrontRightDriveMotorPort,
    DriveConstants.kFrontRightTurningMotorPort,
    DriveConstants.kFrontRightDriveEncoderReversed,
    DriveConstants.kFrontRightTurningEncoderReversed,
    DriveConstants.kFrontRightDriveAbsoluteEncoderPort,
    DriveConstants.kFrontRightDriveAbsoluteEncoderOffsetRad,
    DriveConstants.kFrontRightDriveAbsoluteEncoderReversed
  );

  private final SwerveModule backLeft = new SwerveModule(
    DriveConstants.kBackLeftDriveMotorPort,
    DriveConstants.kBackLeftTurningMotorPort,
    DriveConstants.kBackLeftDriveEncoderReversed,
    DriveConstants.kBackLeftTurningEncoderReversed,
    DriveConstants.kBackLeftDriveAbsoluteEncoderPort,
    DriveConstants.kBackLeftDriveAbsoluteEncoderOffsetRad,
    DriveConstants.kBackLeftDriveAbsoluteEncoderReversed
  );

  private final SwerveModule backRight = new SwerveModule(
    DriveConstants.kBackRightDriveMotorPort,
    DriveConstants.kBackRightTurningMotorPort,
    DriveConstants.kBackRightDriveEncoderReversed,
    DriveConstants.kBackRightTurningEncoderReversed,
    DriveConstants.kBackRightDriveAbsoluteEncoderPort,
    DriveConstants.kBackRightDriveAbsoluteEncoderOffsetRad,
    DriveConstants.kBackRightDriveAbsoluteEncoderReversed
  );
  AnalogInput input = new AnalogInput(3);
  AnalogPotentiometer pot = new AnalogPotentiometer(input, 360, 0);

  private final AHRS gyro = new AHRS(AHRS.NavXComType.kMXP_SPI);
  private final SwerveDriveOdometry odometer = new SwerveDriveOdometry(
    DriveConstants.kDriveKinematics, 
    new Rotation2d(0),
    new SwerveModulePosition[] {
      frontLeft.getPosition(),
      frontRight.getPosition(),
      backLeft.getPosition(),
      backRight.getPosition()
    }
  );

  public SwerveSubsystem() {
    input.setAverageBits(2);
    new Thread(() -> {
      try {
        Thread.sleep(1000);
        zeroHeading();
      } catch (Exception e) {
      }
    }).start();
  }

  public void zeroHeading() {
    gyro.reset();
  }

  public void calibrateWheels(double angle) {
    frontLeft.setDesiredState(new SwerveModuleState(1,new Rotation2d(angle)));
    backLeft.setDesiredState(new SwerveModuleState(1,new Rotation2d(angle)));     
    frontRight.setDesiredState(new SwerveModuleState(1,new Rotation2d(angle)));
    backRight.setDesiredState(new SwerveModuleState(1,new Rotation2d(angle)));
  }

  public double getHeading() {
    //∫return Math.abs(gyro.getAngle()) % 360;
    return Math.IEEEremainder(gyro.getAngle(), 360);
  }

  public Rotation2d getRotation2d() {
    return Rotation2d.fromDegrees(getHeading());
  }

  public Pose2d getPose() {
    return odometer.getPoseMeters();
  }

  public void resetOdometry(Pose2d pose) {
    odometer.resetPosition(
      getRotation2d(),
      new SwerveModulePosition[] {
        frontLeft.getPosition(),
        frontRight.getPosition(),
        backLeft.getPosition(),
        backRight.getPosition()
      },
      pose
    );
  }

  @Override
  public void periodic() {
    odometer.update(
      getRotation2d(),
      new SwerveModulePosition[] {
        frontLeft.getPosition(),
        frontRight.getPosition(),
        backLeft.getPosition(),
        backRight.getPosition()
      }
    );
    SmartDashboard.putNumber("Potenciometer", pot.get());
    SmartDashboard.putNumber("Robot Heading", getHeading());
    SmartDashboard.putString("Robot Location", getPose().getTranslation().toString());
  }

  public void stopModules() {
    frontLeft.stop();
    frontRight.stop();
    backLeft.stop();
    backRight.stop();
  }

  public void setModuleStates(SwerveModuleState[] desiredStates) {
    SwerveDriveKinematics.desaturateWheelSpeeds(desiredStates, DriveConstants.kPhysicalMaxSpeedMetersPerSecond);
    frontLeft.setDesiredState(desiredStates[0]);
    frontRight.setDesiredState(desiredStates[1]);
    backLeft.setDesiredState(desiredStates[2]);
    backRight.setDesiredState(desiredStates[3]);
  }

  public SwerveModulePosition[] getModulePositions() {
    return new SwerveModulePosition[] {
      frontLeft.getPosition(),
      frontRight.getPosition(),
      backLeft.getPosition(),
      backRight.getPosition()
    };
  }
  
  public SwerveModuleState[] getModuleStates() {
    return new SwerveModuleState[] {
      frontLeft.getState(),
      frontRight.getState(),
      backLeft.getState(),
      backRight.getState()
    };
  }
}