package frc.robot.commands;

import java.util.function.Supplier;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Constants.MechanismsConstants;
import frc.robot.subsystems.CoralInTakeSubsystem;

public class JoystickCoralInTakeCmd extends Command {

    private final CoralInTakeSubsystem coralInTakeSubsystem;

    private final Supplier<Boolean> inFunction, outFunction;

    public JoystickCoralInTakeCmd(CoralInTakeSubsystem inTakeSubsystem, Supplier<Boolean> outFunction, Supplier<Boolean> inFunction){
        this.coralInTakeSubsystem = inTakeSubsystem;
        this.outFunction = outFunction;
        this.inFunction = inFunction;
        addRequirements(inTakeSubsystem);
    }

    @Override 
    public void execute(){
        if(outFunction.get()){
            coralInTakeSubsystem.in(MechanismsConstants.kCoralInSpeed);
        } else if(inFunction.get()){
            coralInTakeSubsystem.out(MechanismsConstants.kCoralOutSpeed);
        } else{
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
