// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import edu.wpi.first.wpilibj2.command.sysid.SysIdRoutine.Mechanism;
import frc.robot.Constants.OIConstants;
import frc.robot.commands.CoralInTakeCmd;
import frc.robot.commands.JoystickHandCmd;
import frc.robot.commands.JoystickLidCmd;
import frc.robot.commands.JoystickSwerveCmd;
import frc.robot.subsystems.CoralInTakeSubsystem;
import frc.robot.subsystems.HandSubsystem;
import frc.robot.subsystems.LidSubsystem;
import frc.robot.subsystems.SwerveSubsystem;
import frc.robot.subsystems.AlgaInTakeSubsystem;

public class RobotContainer {
  // Subsystems
  private final SwerveSubsystem swerveSubsystem = new SwerveSubsystem();
  private final CoralInTakeSubsystem coralInTakeSubsystem = new CoralInTakeSubsystem();
  private final HandSubsystem handSubsystem = new HandSubsystem();
  private final AlgaInTakeSubsystem algaInTakeSubsystem = new AlgaInTakeSubsystem();
  private final LidSubsystem lidSubsystem = new LidSubsystem();
  //private final VisionAprilTagSubsystem visionAprilTagSubsystem = new VisionAprilTagSubsystem();

  // Contrillers
  private final Joystick driverJoystick = new Joystick(OIConstants.kDriverControllerPort);
  private final Joystick mechanismsJoystick = new Joystick(OIConstants.kMechanismsControllerPort);

  public RobotContainer() {
    // Default commands
    swerveSubsystem.setDefaultCommand(new JoystickSwerveCmd(
      swerveSubsystem,
      () -> -driverJoystick.getRawAxis(OIConstants.kDriverYAxis),
      () -> driverJoystick.getRawAxis(OIConstants.kDriverXAxis),
      () -> driverJoystick.getRawAxis(OIConstants.kDriverRotAxis),
      () -> driverJoystick.getRawButton(OIConstants.kDriverFieldOrientedButtonIdx)
    ));

    coralInTakeSubsystem.setDefaultCommand(new CoralInTakeCmd(coralInTakeSubsystem, 
    () -> mechanismsJoystick.getRawButton(OIConstants.coralTakeIn), 
    () -> mechanismsJoystick.getRawButton(OIConstants.coralTakeOut)
    ));

    algaInTakeSubsystem.setDefaultCommand(new CoralInTakeCmd(coralInTakeSubsystem, 
    () -> mechanismsJoystick.getRawButton(OIConstants.coralTakeIn), 
    () -> mechanismsJoystick.getRawButton(OIConstants.coralTakeOut)
    ));

    handSubsystem.setDefaultCommand(new JoystickHandCmd(handSubsystem,
    () -> driverJoystick.getRawButton(OIConstants.handIn), 
    () -> driverJoystick.getRawButton(OIConstants.handOut)
    ));

   lidSubsystem.setDefaultCommand(new JoystickLidCmd(lidSubsystem,
   () -> driverJoystick.getRawButton(OIConstants.lidIn), 
    () -> driverJoystick.getRawButton(OIConstants.lidOut)
   ));

    // Trigger bindings
    configureBindings();
  }

  private void configureBindings() {
    new JoystickButton(driverJoystick, OIConstants.kDriveResetHeading).onTrue(new InstantCommand(() -> swerveSubsystem.zeroHeading()));

    

    //new JoystickButton(driverJoystick, 4).onTrue(new AimTagCmd(swerveSubsystem, visionAprilTagSubsystem));
  }

  // public Command getAutonomousCommand() {
  //   return autoChooser.getSelected();
  // }
}
