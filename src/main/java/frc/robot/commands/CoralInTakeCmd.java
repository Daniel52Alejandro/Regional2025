package frc.robot.commands;

import java.util.function.Supplier;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.CoralInTakeSubsystem;

public class CoralInTakeCmd extends Command {

    private final CoralInTakeSubsystem coralInTakeSubsystem;

    private final Supplier<Boolean> spit, take;

    public CoralInTakeCmd(CoralInTakeSubsystem inTakeSubsystem, Supplier<Boolean> take, Supplier<Boolean> spit){
        this.coralInTakeSubsystem = inTakeSubsystem;
        this.take = take;
        this.spit = spit;
        addRequirements(inTakeSubsystem);
    }

    @Override 
    public void execute(){
        if (take.get()){
            coralInTakeSubsystem.take(0.5);
        }
        else if (spit.get()){
            coralInTakeSubsystem.spit(0.5);
        }
        else{
            coralInTakeSubsystem.stop();
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
