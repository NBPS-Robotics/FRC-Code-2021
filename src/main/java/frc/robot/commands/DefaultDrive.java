package frc.robot.commands;

import java.util.function.DoubleSupplier;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.DriveTrain;

/**
 * A command to drvie the robot using mecanum with joystick input
 */

public class DefaultDrive extends CommandBase {
    private final DriveTrain drive;
    private final double x;
    private final double y;
    private final double c;

    public DefaultDrive(DriveTrain subsystem, double X, double Y, double C)
    {
        drive = subsystem;
        x = X;
        y = Y;
        c = C;
        addRequirements(drive);
    }

    public void execute() {
        drive.mecanumDrive(x, y, c);
    }
}