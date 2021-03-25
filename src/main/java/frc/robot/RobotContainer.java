/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.Constants.IntakeConstants;
import frc.robot.Constants.JoystickConstants;
import frc.robot.commands.AutoDriveToLineAndShootLeft;
import frc.robot.commands.DefaultDrive;
import frc.robot.commands.DriveForwardTime;
import frc.robot.commands.DriveToTargetLimelight;
import frc.robot.commands.FieldOrientedDrive;
import frc.robot.commands.RunIntake;
import frc.robot.commands.Shoot;
import frc.robot.subsystems.DriveTrain;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.NavX;
import frc.robot.subsystems.Shooter;


/**
 * This class is where the bulk of the robot should be declared.  Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls).  Instead, the structure of the robot
 * (including subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {
  // The robot's subsystems and commands are defined here...
  Joystick m_joystick1 = new Joystick(JoystickConstants.Joystick1_ID);
  Joystick m_joystick2 = new Joystick(JoystickConstants.Joystick2_ID);
  
  //subsystems
  private final DriveTrain drive = new DriveTrain();
  private final Intake intake = new Intake();
  private final Shooter shooter = new Shooter();
  final NavX navx = new NavX();
  //commands
  private final FieldOrientedDrive m_FOD = new FieldOrientedDrive(drive,
      () -> m_joystick1.getRawAxis(JoystickConstants.XStick1_ID),
      () -> m_joystick1.getRawAxis(JoystickConstants.YStick1_ID),
      () -> m_joystick1.getRawAxis(JoystickConstants.XStick2_ID), () -> navx.getAngle());
  private final DefaultDrive m_default = new DefaultDrive(drive, m_joystick1.getRawAxis(JoystickConstants.XStick1_ID),m_joystick1.getRawAxis(JoystickConstants.YStick1_ID), m_joystick1.getRawAxis(JoystickConstants.XStick2_ID));
  private final AutoDriveToLineAndShootLeft a_autoDriveToLineAndShootLeft = new AutoDriveToLineAndShootLeft(drive, shooter);
  // chooser
  private SendableChooser<Command> autonomousChooser = new SendableChooser<Command>();
  private SendableChooser<Command> driveChooser = new SendableChooser<Command>();
  // limelight stuff
  NetworkTable table = NetworkTableInstance.getDefault().getTable("limelight");
  NetworkTableEntry tx = table.getEntry("tx");
  NetworkTableEntry ta = table.getEntry("ta");

  /**
   * The container for the robot. Contains subsystems, OI devices, and commands.
   */
  public RobotContainer() {
    // sets up our auto chooser(see the classes for details)
    //autonomousChooser.setDefaultOption("Drive Forward", new DriveForwardTime(1, -0.3, drive));
    //autonomousChooser.addOption("Shoot Left Of Target", a_autoDriveToLineAndShootLeft);
    // sets up drive chooser with the option between FOD and default
    //driveChooser.setDefaultOption("Field Oriented Drive", m_FOD);
    //driveChooser.addOption("Default Drive", m_default);
    // publishes the choosers
    //SmartDashboard.putData("Autonomous Mode", autonomousChooser);
    //SmartDashboard.putData("Drive Mode", driveChooser);
    // Configure the button bindings
    configureButtonBindings();

    // sets the drive to what it should be
    drive.setDefaultCommand(m_default);

    // guess
    SmartDashboard.putNumber("navx", navx.getAngle());

  }

  /**
   * Use this method to define your button->command mappings. Buttons can be
   * created by instantiating a {@link GenericHID} or one of its subclasses
   * ({@link edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then
   * passing it to a {@link edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */
  private void configureButtonBindings() {
    //new JoystickButton(m_joystick1, 1).whenPressed(new RunIntake(intake, IntakeConstants.IntakeSpeed))
    //    .whenReleased(new RunIntake(intake, 0));

    //new JoystickButton(m_joystick1, 2).whileHeld(new Shoot(shooter));

    //new JoystickButton(m_joystick1, 3).whileHeld(new DriveToTargetLimelight(drive, navx));

    //new JoystickButton(m_joystick1, 4)
    //    .whileHeld(new SequentialCommandGroup(new DriveToTargetLimelight(drive, navx), new Shoot(shooter)
    //));

  }


  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    // An ExampleCommand will run in autonomous
    return a_autoDriveToLineAndShootLeft;
  }
}
