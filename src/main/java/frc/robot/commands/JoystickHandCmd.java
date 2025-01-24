package frc.robot.commands;

import java.util.function.Supplier;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Constants.MechanismsConstants;
import frc.robot.subsystems.HandSubsystem;

public class JoystickHandCmd extends Command {

    private final HandSubsystem handSubsystem;

    private final Supplier<Boolean> downFunction, upFunction;

    public JoystickHandCmd(HandSubsystem handSubsystem, Supplier<Boolean> upFunction, Supplier<Boolean> downFunction){
        this.handSubsystem = handSubsystem;
        this.upFunction = upFunction;
        this.downFunction = downFunction;
        addRequirements(handSubsystem);
    }

    @Override 
    public void execute(){
        if(upFunction.get()){
            handSubsystem.up(MechanismsConstants.kHandUpSpeed);
        } else if(downFunction.get()){
            handSubsystem.down(MechanismsConstants.kHandDownSpeed);
        } else {
            handSubsystem.stop();
        }
    }

    @Override
    public boolean isFinished(){
        return false;
    }

    @Override
    public void end(boolean interrupted){
        handSubsystem.stop();
    }
}
