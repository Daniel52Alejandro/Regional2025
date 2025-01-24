package frc.robot.commands;

import java.util.function.Supplier;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Constants.MechanismsConstants;
import frc.robot.subsystems.AlgaeInTakeSubsystem;

public class JoystickAlgaeInTakeCmd extends Command {

    private final AlgaeInTakeSubsystem algaeInTakeSubsystem;

    private final Supplier<Boolean> inFunction, outFunction;

    public JoystickAlgaeInTakeCmd(AlgaeInTakeSubsystem inTakeSubsystem, Supplier<Boolean> outFunction, Supplier<Boolean> inFunction){
        this.algaeInTakeSubsystem = inTakeSubsystem;
        this.outFunction = outFunction;
        this.inFunction = inFunction;
        addRequirements(inTakeSubsystem);
    }

    @Override 
    public void execute(){
        if(outFunction.get()){
            algaeInTakeSubsystem.in(MechanismsConstants.kAlgaeInSpeed);
        } else if(inFunction.get()){
            algaeInTakeSubsystem.out(MechanismsConstants.kAlgaeOutSpeed);
        } else{
            algaeInTakeSubsystem.stop();
        }
    }

    @Override
    public boolean isFinished(){
        return false;
    }

    @Override
    public void end(boolean interrupted){
        algaeInTakeSubsystem.stop();
    }
}
