package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Intake;

public class RunIntake extends CommandBase {
    Intake intake = new Intake();
    private final double speed;

    public RunIntake(Intake Intake, double Speed) {
        intake = Intake;
        speed = Speed;
        addRequirements(intake);
    }
    @Override
    public void execute() {
        intake.succ(speed);
    }
    
}