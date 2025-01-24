package frc.robot.commands;

import java.util.function.Supplier;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Constants.MechanismsConstants;
import frc.robot.subsystems.ElevatorSubsystem;

public class JoystickElevatorCmd extends Command {
    private final ElevatorSubsystem elevatorSubsystem;
    private final Supplier<Boolean> upFunction, downFunction;

    public JoystickElevatorCmd(ElevatorSubsystem elevatorSubsystem, Supplier<Boolean> upFunction, Supplier<Boolean> downFunction){
        this.elevatorSubsystem = elevatorSubsystem;
        this.upFunction = upFunction;
        this.downFunction = downFunction;
        addRequirements(elevatorSubsystem);
    }

    @Override 
    public void execute() {
        if(upFunction.get()) {
            elevatorSubsystem.up(MechanismsConstants.kElevatorUpSpeed);
        } else if(downFunction.get()) {
            elevatorSubsystem.down(MechanismsConstants.kElevatorDownSpeed);
        } else {
            elevatorSubsystem.stop();
        }
    }

    @Override
    public boolean isFinished() {
        return false;
    }

    @Override
    public void end(boolean interrupted) {
        elevatorSubsystem.stop();
    }
}