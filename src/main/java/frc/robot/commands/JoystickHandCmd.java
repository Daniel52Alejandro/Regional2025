package frc.robot.commands;

import java.util.function.Supplier;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.HandSubsystem;

public class JoystickHandCmd extends Command {

    private final HandSubsystem handSubsystem;

    private final Supplier<Boolean> down, up;

    public JoystickHandCmd(HandSubsystem handSubsystem, Supplier<Boolean> up, Supplier<Boolean> down){
        this.handSubsystem = handSubsystem;
        this.up = up;
        this.down = down;
        addRequirements(handSubsystem);
    }

    @Override 
    public void execute(){
        if (up.get()){
            handSubsystem.up(0.5);
        }
        else if (down.get()){
            handSubsystem.down(0.5);
        }
        else{
            handSubsystem.stop();
        }
    }

    @Override
    public boolean isFinished(){
        return false;
    }

    @Override
    public void end(boolean interrupted){
        
    }
}
