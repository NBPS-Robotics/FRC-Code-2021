package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.subsystems.DriveTrain;
import frc.robot.subsystems.Shooter;

/**
 * A command to drvie the robot using mecanum with joystick input
 */

public class AutoDriveToLineAndShootLeft extends CommandBase {
    private final DriveTrain drive;
    private final Shooter shooter;
    

    public AutoDriveToLineAndShootLeft(DriveTrain subsystem, Shooter Shooter)
    {
        drive = subsystem;
        shooter = Shooter;
        addRequirements(drive, shooter);
    }
    @Override
    public void initialize() {
        new SequentialCommandGroup(
            new DriveForwardTime(1, 0.3, drive),
            new RotateToTargetLimelight(drive, -1.5, -3.5),
            new AutoShootWithIndex(shooter)
            
        ).schedule();
    }
}