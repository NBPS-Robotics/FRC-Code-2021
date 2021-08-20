package frc.robot;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants.IntakeConstants;
import frc.robot.Constants.JoystickConstants;
import frc.robot.Constants.ShooterConstants;
import frc.robot.subsystems.Drive;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.JoyStick;
import frc.robot.subsystems.Shooter;


public class TeleOp {
    private final Drive drive;
    private final Shooter shooter;
    private final Intake intake;
    private final JoyStick stick;

    public TeleOp(Drive Drive, Intake Intake, Shooter Shooter, JoyStick Stick){
        drive = Drive;
        shooter = Shooter;
        intake = Intake;
        stick = Stick;
        addRequirements(Drive);
        //addRequirements(Shooter);
        //addRequirements(Intake);
        //addRequirements(Stick);
    }
    // Called when the command is initially scheduled.
  @Override
  public void initialize() {

  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    drive.mecanumDrive(stick.getAxis(JoystickConstants.XStick1_ID),stick.getAxis(JoystickConstants.YStick2_ID), stick.getAxis(JoystickConstants.XStick2_ID));
    if(stick.RB()){
        intake.succ(IntakeConstants.IntakeSpeed);
    }
    else{
      intake.succ(0);
    }
    if(stick.Ypressed()){
        shooter.shootGoal(ShooterConstants.Shooter_Speed,ShooterConstants.Shooter2_Speed);
    }
    if(stick.Bpressed()){
      shooter.shootGoal(ShooterConstants.Shooter_Speed*0.78,ShooterConstants.Shooter2_Speed*0.78);
    }
    if(stick.Apressed()){
      shooter.shootGoal(ShooterConstants.Shooter_Speed*0.75,ShooterConstants.Shooter2_Speed*0.75);
    }
    if(stick.Xpressed()){
      shooter.shootGoal(ShooterConstants.Shooter_Speed*0.3,ShooterConstants.Shooter2_Speed*0.3);
    }
    if(stick.LB()){
      Drive.speedControl(0.25);
    }
    else Drive.speedControl(0.5);
    if(stick.Dpad()== 0){
      shooter.on(ShooterConstants.Shooter_Speed,ShooterConstants.Shooter2_Speed);
    }
    if(stick.Dpad()== 180){
      shooter.off();
    }
  }

}
