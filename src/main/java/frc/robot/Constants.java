// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.math.kinematics.SwerveDriveKinematics;
import edu.wpi.first.math.util.Units;

/**
 * The Constants class provides a convenient place for teams to hold robot-wide numerical or boolean
 * constants. This class should not be used for any other purpose. All constants should be declared
 * globally (i.e. public static). Do not put anything functional in this class.
 *
 * <p>It is advised to statically import this class (or one of its inner classes) wherever the
 * constants are needed, to reduce verbosity.
 */
public final class Constants {
  
  public static final class ModuleConstants {
    public static final double kWheelDiameterMeters = Units.inchesToMeters(4);
    public static final double kDriveMotorGearRatio = 1 / 6.75;
    public static final double kTurningMotorGearRatio = 1 / 21.4286;
    public static final double kDriveEncoderRot2Meter = kDriveMotorGearRatio * Math.PI * kWheelDiameterMeters;
    public static final double kTurningEncoderRot2Rad = kTurningMotorGearRatio * 2 * Math.PI;
    public static final double kDriveEncoderRPM2MeterPerSec = kDriveEncoderRot2Meter / 60;
    public static final double kTurningEncoderRPM2RadPerSec = kTurningEncoderRot2Rad / 60;
    public static final double kPTurning = 0.5;
  }

  public static final class DriveConstants {

    public static final double kTrackWidth = Units.inchesToMeters(21.5);
    // Distance between right and left wheels
    public static final double kWheelBase = Units.inchesToMeters(21.5);
    // Distance between front and back wheels
    public static final SwerveDriveKinematics kDriveKinematics = new SwerveDriveKinematics(
      new Translation2d(kWheelBase / 2, -kTrackWidth / 2),
      new Translation2d(kWheelBase / 2, kTrackWidth / 2),
      new Translation2d(-kWheelBase / 2, -kTrackWidth / 2),
      new Translation2d(-kWheelBase / 2, kTrackWidth / 2)
    );

    public static final int kFrontLeftDriveMotorPort = 16;
    public static final int kBackLeftDriveMotorPort = 2;
    public static final int kFrontRightDriveMotorPort = 14;
    public static final int kBackRightDriveMotorPort = 12;

    public static final int kFrontLeftTurningMotorPort = 1;
    public static final int kBackLeftTurningMotorPort = 3;
    public static final int kFrontRightTurningMotorPort = 15;
    public static final int kBackRightTurningMotorPort = 13;

    public static final boolean kFrontLeftTurningEncoderReversed = false;
    public static final boolean kBackLeftTurningEncoderReversed = false;
    public static final boolean kFrontRightTurningEncoderReversed = false;
    public static final boolean kBackRightTurningEncoderReversed = false;

    public static final boolean kFrontLeftDriveEncoderReversed = false;
    public static final boolean kBackLeftDriveEncoderReversed = false;
    public static final boolean kFrontRightDriveEncoderReversed = true;
    public static final boolean kBackRightDriveEncoderReversed = true;

    public static final int kFrontLeftDriveAbsoluteEncoderPort = 4;
    public static final int kBackLeftDriveAbsoluteEncoderPort = 6;
    public static final int kFrontRightDriveAbsoluteEncoderPort = 5; 
    public static final int kBackRightDriveAbsoluteEncoderPort = 7;

    public static final boolean kFrontLeftDriveAbsoluteEncoderReversed = false;
    public static final boolean kBackLeftDriveAbsoluteEncoderReversed = false;
    public static final boolean kFrontRightDriveAbsoluteEncoderReversed = false;
    public static final boolean kBackRightDriveAbsoluteEncoderReversed = false;

    public static final double kFrontLeftDriveAbsoluteEncoderOffsetRad = -0.314941;
    public static final double kBackLeftDriveAbsoluteEncoderOffsetRad = -0.276123;
    public static final double kFrontRightDriveAbsoluteEncoderOffsetRad = 0.359375;
    public static final double kBackRightDriveAbsoluteEncoderOffsetRad = -0.250977;

    public static final double kPhysicalMaxSpeedMetersPerSecond = 10;
    public static final double kPhysicalMaxAngularSpeedRadiansPerSecond = 2 * 2 * Math.PI;

    public static final double kTeleDriveMaxSpeedMetersPerSecond = kPhysicalMaxSpeedMetersPerSecond;
    public static final double kTeleDriveMaxAngularSpeedRadiansPerSecond = kPhysicalMaxAngularSpeedRadiansPerSecond / 2;
    public static final double kTeleDriveMaxAccelerationUnitsPerSecond = 3;
    public static final double kTeleDriveMaxAngularAccelerationUnitsPerSecond = 3;
  }
  public static final class OIConstants {
    public static final int kDriverControllerPort = 0;
    public static final int kMechanismsControllerPort = 1;
    public static final double kDeadband = 0.25;


    //Driver

    public static final int kDriverYAxis = 1;
    public static final int kDriverXAxis = 0;
    public static final int kDriverRotAxis = 4;

    public static final int kDriverFieldOrientedButtonIdx = 6;
    public static final int kDriveResetHeading = 2;


    //Mechanisms
    public static final int MechanismsArmUp = 3;
    public static final int MechanismsArmDown = 2;

    public static final int algaeTakeIn = 7;
    public static final int algaeTakeOut = 8;
    public static final int coralTakeIn = 5;
    public static final int coralTakeOut = 6;
    public static final int handOut = 5;
    public static final int handIn = 6;
    public static final int lidIn = 4;
    public static final int lidOut = 1;

  }

  public enum Reef {
    L1,
    L2,
    L3,
    L4;
  }
  
}
