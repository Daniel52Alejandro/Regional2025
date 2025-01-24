package frc.robot.commands;

import java.util.function.Supplier;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Constants.MechanismsConstants;
import frc.robot.subsystems.ScalerSubsystem;

public class JoystickScalerCmd extends Command {
    private final ScalerSubsystem scalerSubsystem;
    private final Supplier<Boolean> upFunction, downFunction;

    public JoystickScalerCmd(ScalerSubsystem scalerSubsystem, Supplier<Boolean> upFunction, Supplier<Boolean> downFunction){
        this.scalerSubsystem = scalerSubsystem;
        this.upFunction = upFunction;
        this.downFunction = downFunction;
        addRequirements(scalerSubsystem);
    }

    @Override 
    public void execute() {
        if(upFunction.get()) {
            scalerSubsystem.up(MechanismsConstants.kScalerUpSpeed);
        } else if(downFunction.get()) {
            scalerSubsystem.down(MechanismsConstants.kScalerDownSpeed);
        } else {
            scalerSubsystem.stop();
        }
    }

    @Override
    public boolean isFinished(){
        return false;
    }

    @Override
    public void end(boolean interrupted){
        scalerSubsystem.stop();
    }
}