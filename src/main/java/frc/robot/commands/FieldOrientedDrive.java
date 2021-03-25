package frc.robot.commands;

import java.util.function.DoubleSupplier;



import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.DriveTrain;

/**
 * A command to drvie the robot using mecanum with joystick input 
 * This drives in relation to the field, not the robot itsef
 */

public class FieldOrientedDrive extends CommandBase {
    private final DriveTrain drive;
    private final DoubleSupplier x;
    private final DoubleSupplier y;
    private final DoubleSupplier c;
    private final DoubleSupplier theta;
    

    public FieldOrientedDrive(DriveTrain subsystem, DoubleSupplier X, DoubleSupplier Y, DoubleSupplier C, DoubleSupplier Theta)
    {
        drive = subsystem;
        x = X;
        y = Y;
        c = C;
        theta = Theta;
        addRequirements(drive);
    }
    @Override
    public void initialize() {
    }
    @Override
    public void execute() {
        drive.mecanumDriveGyro(x.getAsDouble(), y.getAsDouble(), c.getAsDouble(), theta.getAsDouble());
    }
}