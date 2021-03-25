package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;

import frc.robot.subsystems.DriveTrain;

import com.kauailabs.navx.frc.AHRS;

//import edu.wpi.first.wpilibj.SerialPort;


//this command will drive the robot plus or minus a given degree
public class DriveGivenAngle extends CommandBase {
    private final DriveTrain drive;
    private final double angle;
    private final double speed;
    private final double deadzone;
    private double currentAngle;
    private AHRS navx;

    /**
     * We set up this class with a few variables
     * @param Angle = the angle we will be traveling to (ex if we are at 180 and want to turn 90 degrees to the right, we would put 90. the initial angle does not matter for this class)
     * @param Speed = the speed at which we will be turning
     * @param Drive = the drive subsystem
     * @param DeadZone = the zone in between which we can turn off (see isFinished)
     */
    public DriveGivenAngle(DriveTrain Drive, double Angle, double Speed, double DeadZone, AHRS p_navx) {
        angle = Angle;
        speed = Speed;
        drive = Drive;
        deadzone = DeadZone;
        navx = p_navx;
    }

    /**
     * The basic way we run this class is that while we are below the target angle, we turn
     * basically, if the angle is less than our current angle, we turn left
     * and if greater, we turn right
     * if we are at the angle already (only possible if you put 0 for the angle) we do nothing
     */
    @Override
    public void initialize() {
        currentAngle = navx.getAngle();
        if(angle < 0 )
            drive.mecanumDrive(0,0,-speed);
        else if(angle > 0)
            drive.mecanumDrive(0,0,speed);
        else 
            drive.mecanumDrive(0.1, 0, 0);
    }
    /**
     * Make sure we tell the wheels to stop spinning once we are done
     */
    @Override
    public void end(boolean interrupted)
    {
        drive.mecanumDrive(0, 0, 0);
    }
    /**
     * basically, this gives us a small range of angles to be in between while turning
     * while turning at higher speeds, we tend to overshoot or undershoot by small
     * amounts that really wouldnt have much of an affect on the auto as a whole
     * However, these angles can lead to oscilation, where the robot violently jerks
     * back and forth trying to reach the angle
     * To prevent this, we set a small safe zone defined by m_deadzone
     */
    @Override
    public boolean isFinished() {
        /*if(m_angle+currentAngle + m_deadzone > navx.getAngle() && m_angle+currentAngle - m_deadzone < navx.getAngle())
            return true;
        else*/
            return false;
    }
}