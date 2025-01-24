package frc.robot.commands;

import java.util.function.Supplier;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Constants.MechanismsConstants;
import frc.robot.subsystems.LidSubsystem;

public class JoystickLidCmd extends Command {

    private final LidSubsystem lidSubsystem;

    private final Supplier<Boolean> downFunction, upFunction;

    public JoystickLidCmd(LidSubsystem lidSubsystem, Supplier<Boolean> upFunction, Supplier<Boolean> downFunction){
        this.lidSubsystem = lidSubsystem;
        this.upFunction = upFunction;
        this.downFunction = downFunction;
        addRequirements(lidSubsystem);
    }

    @Override 
    public void execute(){
        if(upFunction.get()){
            lidSubsystem.up(MechanismsConstants.kLidUpSpeed);
        } else if(downFunction.get()){
            lidSubsystem.down(MechanismsConstants.kLidDownSpeed);
        } else {
            lidSubsystem.stop();
        }
    }

    @Override
    public boolean isFinished(){
        return false;
    }

    @Override
    public void end(boolean interrupFunctionted) { 
        lidSubsystem.stop();
    }
}
