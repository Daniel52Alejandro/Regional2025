package frc.robot.commands;

import java.util.function.Supplier;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.AlgaInTakeSubsystem;

public class AlgaInTakeCmd extends Command {

    private final AlgaInTakeSubsystem algaInTakeSubsystem;

    private final Supplier<Boolean> spit, take;

    public AlgaInTakeCmd(AlgaInTakeSubsystem inTakeSubsystem, Supplier<Boolean> take, Supplier<Boolean> spit){
        this.algaInTakeSubsystem = inTakeSubsystem;
        this.take = take;
        this.spit = spit;
        addRequirements(inTakeSubsystem);
    }

    @Override 
    public void execute(){
        if (take.get()){
            algaInTakeSubsystem.take(0.5);
        }
        else if (spit.get()){
            algaInTakeSubsystem.spit(0.5);
        }
        else{
            algaInTakeSubsystem.stop();
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
