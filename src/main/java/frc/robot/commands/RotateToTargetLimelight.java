package frc.robot.commands;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.DriveTrain;
import frc.robot.Constants.LimelightConstants;

/**
 * A command to drvie the robot using mecanum with joystick input
 */

public class RotateToTargetLimelight extends CommandBase {
    private final DriveTrain drive;
    NetworkTable table = NetworkTableInstance.getDefault().getTable("limelight");
    NetworkTableEntry tx = table.getEntry("tx");
    NetworkTableEntry ta = table.getEntry("ta");
    double x, a, error, current, straighten, leftBound, rightBound;
    Timer timer = new Timer();

    public RotateToTargetLimelight(DriveTrain subsystem, double leftBound, double rightBound) {
        drive = subsystem;
        this.leftBound = leftBound;
        this.rightBound = rightBound;
    }

    @Override
    public void initialize() {
        timer.reset();
        timer.start();
    }
    @Override
    public void execute() {
        x = tx.getDouble(0.0);
        a = ta.getDouble(0.0);
        if(x >= leftBound)
            drive.mecanumDrive(0, 0, LimelightConstants.Ideal_Rotate_Value);
        else if (x <= rightBound)
            drive.mecanumDrive(0, 0, -LimelightConstants.Ideal_Rotate_Value);
    }
    @Override
    public void end(boolean interrupetd) {
        drive.mecanumDrive(0, 0, 0);
    }
    @Override
    public boolean isFinished() {
         if(x<=leftBound-0.5&&x>=rightBound+.5&&timer.get()>1.5)
            return true;
        return false;
    }
}