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
import frc.robot.commands.JoystickAlgaeInTakeCmd;
import frc.robot.commands.JoystickCoralInTakeCmd;
import frc.robot.commands.JoystickElevatorCmd;
import frc.robot.commands.JoystickHandCmd;
import frc.robot.commands.JoystickLidCmd;
import frc.robot.commands.JoystickScalerCmd;
import frc.robot.commands.JoystickSwerveCmd;
import frc.robot.subsystems.CoralInTakeSubsystem;
import frc.robot.subsystems.ElevatorSubsystem;
import frc.robot.subsystems.HandSubsystem;
import frc.robot.subsystems.LidSubsystem;
import frc.robot.subsystems.ScalerSubsystem;
import frc.robot.subsystems.SwerveSubsystem;
import frc.robot.subsystems.AlgaeInTakeSubsystem;

public class RobotContainer {
  // Subsystems
  private final SwerveSubsystem swerveSubsystem = new SwerveSubsystem();
  private final CoralInTakeSubsystem coralInTakeSubsystem = new CoralInTakeSubsystem();
  private final HandSubsystem handSubsystem = new HandSubsystem();
  private final AlgaeInTakeSubsystem algaeInTakeSubsystem = new AlgaeInTakeSubsystem();
  private final LidSubsystem lidSubsystem = new LidSubsystem();
  private final ElevatorSubsystem elevatorSubsystem = new ElevatorSubsystem();
  private final ScalerSubsystem scalerSubsystem = new ScalerSubsystem();
  // Controllers
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

    coralInTakeSubsystem.setDefaultCommand(new JoystickCoralInTakeCmd(
      coralInTakeSubsystem, 
      () -> mechanismsJoystick.getRawButton(OIConstants.kMechanismsCoralIn), 
      () -> mechanismsJoystick.getRawButton(OIConstants.kMechanismsCoralOut)
    ));

    algaeInTakeSubsystem.setDefaultCommand(new JoystickAlgaeInTakeCmd(
      algaeInTakeSubsystem, 
      () -> mechanismsJoystick.getRawButton(OIConstants.kMechanismsAlgaeOut), 
      () -> mechanismsJoystick.getRawButton(OIConstants.kMechanismsAlgaeIn)
    ));

    handSubsystem.setDefaultCommand(new JoystickHandCmd(
      handSubsystem,
      () -> mechanismsJoystick.getRawButton(OIConstants.kMechanismsHandUp), 
      () -> mechanismsJoystick.getRawButton(OIConstants.kMechanismsHandDown)
    ));

    lidSubsystem.setDefaultCommand(new JoystickLidCmd(
      lidSubsystem,
      () -> driverJoystick.getRawButton(OIConstants.kMechanismsLidUp), 
      () -> driverJoystick.getRawButton(OIConstants.kMechanismsLidDown)
    ));

    elevatorSubsystem.setDefaultCommand(new JoystickElevatorCmd(
      elevatorSubsystem,
      () -> driverJoystick.getRawButton(OIConstants.kMechanismsElevatorUp), 
      () -> driverJoystick.getRawButton(OIConstants.kMechanismsElevatorDown)
    ));

    scalerSubsystem.setDefaultCommand(new JoystickScalerCmd(
      scalerSubsystem,
      () -> driverJoystick.getRawButton(OIConstants.kMechanismsScalerUp), 
      () -> driverJoystick.getRawButton(OIConstants.kMechanismsScalerDown)
    ));

    // Trigger bindings
    configureBindings();
  }

  private void configureBindings() {
    new JoystickButton(driverJoystick, OIConstants.kDriverResetHeading).onTrue(new InstantCommand(() -> swerveSubsystem.zeroHeading()));
  }
}
