package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants.IntakeConstants;
import frc.robot.Constants.JoystickConstants;
import frc.robot.Constants.ShooterConstants;
import frc.robot.subsystems.DriveTrain;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.JoyStick;
import frc.robot.subsystems.Shooter;


public class TeleOp extends CommandBase {
    private final DriveTrain drive;
    private final Shooter shooter;
    private final Intake intake;
    private final JoyStick stick;

    public TeleOp(DriveTrain Drive, Intake Intake, Shooter Shooter, JoyStick Stick){
        drive = Drive;
        shooter = Shooter;
        intake = Intake;
        stick = Stick;
        addRequirements(Drive);
        addRequirements(Shooter);
        addRequirements(Intake);
        addRequirements(Stick);
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
    if(stick.Bpressed()){
        shooter.shootGoal();
    }
    if(stick.LB()){
      DriveTrain.speedControl(0.3);
    }
    else DriveTrain.speedControl(1);
    if(stick.Dpad()== 0){
      shooter.on(ShooterConstants.Shooter_Speed);
    }
    if(stick.Dpad()== 180){
      shooter.off();
    }
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {

  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
