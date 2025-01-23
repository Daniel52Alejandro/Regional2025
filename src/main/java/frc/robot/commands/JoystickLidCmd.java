package frc.robot.commands;

import java.util.function.Supplier;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.HandSubsystem;
import frc.robot.subsystems.LidSubsystem;

public class JoystickLidCmd extends Command {

    private final LidSubsystem lidSubsystem;

    private final Supplier<Boolean> down, up;

    public JoystickLidCmd(LidSubsystem lidSubsystem, Supplier<Boolean> up, Supplier<Boolean> down){
        this.lidSubsystem = lidSubsystem;
        this.up = up;
        this.down = down;
        addRequirements(lidSubsystem);
    }

    @Override 
    public void execute(){
        if (up.get()){
            lidSubsystem.up(0.5);
        }
        else if (down.get()){
            lidSubsystem.down(0.5);
        }
        else{
            lidSubsystem.stop();
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
