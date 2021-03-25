package frc.robot.commands;

import frc.robot.subsystems.Shooter;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;

import frc.robot.Constants.ShooterConstants;

public class Shoot extends CommandBase {
  @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})
  private final Shooter shooter;
  private Timer timer = new Timer();

  /**
   * Creates our shoot with index command
   * The idea of this command is to automatically charge up the shooter, open the index,
   * let the balls shoot out, and, after the command is done being called, close the index and
   * power down the shooter
   * @param Shooter = the shooter
   */
  public Shoot(Shooter Shooter) {
    shooter = Shooter;
    addRequirements(shooter);
  }

  //start charging up the motor and start the timer
  @Override
  public void initialize() {
      timer.reset();
      timer.start();
      shooter.shoot(ShooterConstants.Shooter_Speed);
  }
 
  //this waits until the shooter is charged up, then launches the balls
  @Override
  public void execute() {
      if(timer.get() >= ShooterConstants.Charge_Delay)
      {
        shooter.moveServo(ShooterConstants.Servo_Down);
      }
  }

  //When the command is over, turn off the shooter and close the index servo
  @Override
  public void end(boolean interrupted) {
      shooter.shoot(0);
      shooter.moveServo(ShooterConstants.Servo_Up);
  }

  //Command is controlled by driver, doesnt need to end on its own
  @Override
  public boolean isFinished() {
    return false;
  }
}
