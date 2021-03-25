package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;

import frc.robot.subsystems.DriveTrain;

import edu.wpi.first.wpilibj.Timer;

//this command will drive a robot for a specified amount of time
public class DriveForwardTime extends CommandBase {
    private final DriveTrain drive;
    private final double time;
    private final double speed;
    private Timer timer = new Timer();

    /**
     * 
     * @param time = time to drive forward
     * @param speed = speed to drive
     * @param drive = drive system
     */
    public DriveForwardTime(double Time, double Speed, DriveTrain Drive) {
        time = Time;
        speed = Speed;
        drive = Drive;
    }
    /**
     * reset timer, turn on power
     */
    @Override
    public void initialize() {
        timer.reset();
        timer.start();
    }
    public void execute()
    {
        drive.mecanumDrive(0,speed,0);
    }
    /**
     * make sure power is turned off in the end
     */
    @Override
    public void end(boolean interrupted)
    {
        drive.mecanumDrive(0, 0, 0);
    }
    /**
     * check to see if current time elapsed is greater than the time provided
     * if yes, end the commandS
     */
    @Override
    public boolean isFinished() {
        return timer.get() >= time;
    }
}