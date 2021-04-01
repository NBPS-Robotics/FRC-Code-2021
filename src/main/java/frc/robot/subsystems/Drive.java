// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.CANEncoder;
import com.revrobotics.CANSparkMax;

import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.drive.MecanumDrive;
import edu.wpi.first.wpilibj.geometry.Pose2d;
import edu.wpi.first.wpilibj.kinematics.DifferentialDriveOdometry;
import edu.wpi.first.wpilibj.kinematics.DifferentialDriveWheelSpeeds;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.DriveConstants;

public class Drive extends SubsystemBase {
    // The motors on the left side of the drive.
    private static CANSparkMax frontLeft = new CANSparkMax(DriveConstants.FrontLeft_ID, MotorType.kBrushless);
    private static CANSparkMax frontRight = new CANSparkMax(DriveConstants.FrontRight_ID, MotorType.kBrushless);
    private static CANSparkMax backLeft = new CANSparkMax(DriveConstants.BackLeft_ID, MotorType.kBrushless);
    private static CANSparkMax backRight = new CANSparkMax(DriveConstants.BackRight_ID, MotorType.kBrushless);
    private final SpeedControllerGroup m_leftMotors = new SpeedControllerGroup(
            frontLeft, backLeft);

    // The motors on the right side of the drive.
    private final SpeedControllerGroup m_rightMotors = new SpeedControllerGroup(
            frontRight, backRight);

    // The robot's drive
    //private final DifferentialDrive m_drive = new DifferentialDrive(m_leftMotors, m_rightMotors);
    private MecanumDrive m_drive = new MecanumDrive(frontLeft, backLeft, frontRight, backRight);
    // The left-side drive encoder
    private final CANEncoder m_leftEncoder = backLeft.getEncoder();

    // The right-side drive encoder
    private final CANEncoder m_rightEncoder = backRight.getEncoder();

    // The gyro sensor
    private final NavX m_gyro = new NavX();

    // Odometry class for tracking robot pose
    private final DifferentialDriveOdometry m_odometry;

    /**
     * Creates a new DriveSubsystem.
     */
    public Drive() {
    // Sets the distance per pulse for the encoders
    m_leftEncoder.setPositionConversionFactor(DriveConstants.kEncoderDistancePerPulse);
    m_rightEncoder.setPositionConversionFactor(DriveConstants.kEncoderDistancePerPulse);

    resetEncoders();
    m_odometry = new DifferentialDriveOdometry(m_gyro.getRotation());
  }

  @Override
  public void periodic() {
    // Update the odometry in the periodic block
    m_odometry.update(m_gyro.getRotation(), m_leftEncoder.getPosition(),
                      m_rightEncoder.getPosition());
  }

  /**
   * Returns the currently-estimated pose of the robot.
   *
   * @return The pose.
   */
  public Pose2d getPose() {
    return m_odometry.getPoseMeters();
  }

  /**
   * Returns the current wheel speeds of the robot.
   *
   * @return The current wheel speeds.
   */
  public DifferentialDriveWheelSpeeds getWheelSpeeds() {
    return new DifferentialDriveWheelSpeeds(m_leftEncoder.getVelocity(), m_rightEncoder.getVelocity());
  }

  /**
   * Resets the odometry to the specified pose.
   *
   * @param pose The pose to which to set the odometry.
   */
  public void resetOdometry(Pose2d pose) {
    resetEncoders();
    m_odometry.resetPosition(pose, m_gyro.getRotation());
  }

  /**
   * Controls the left and right sides of the drive directly with voltages.
   *
   * @param leftVolts  the commanded left output
   * @param rightVolts the commanded right output
   */
  public void tankDriveVolts(double leftVolts, double rightVolts) {
    m_leftMotors.setVoltage(leftVolts);
    m_rightMotors.setVoltage(-rightVolts);
    m_drive.feed();
  }

  /**
   * Resets the drive encoders to currently read a position of 0.
   */
  public void resetEncoders() {
    m_leftEncoder.setPosition(0);
    m_rightEncoder.setPosition(0);
  }

  /**
   * Gets the average distance of the two encoders.
   *
   * @return the average of the two encoder readings
   */
  public double getAverageEncoderDistance() {
    return (m_leftEncoder.getPosition() + m_rightEncoder.getPosition()) / 2.0;
  }

  /**
   * Gets the left drive encoder.
   *
   * @return the left drive encoder
   */
  public CANEncoder getLeftEncoder() {
    return m_leftEncoder;
  }

  /**
   * Gets the right drive encoder.
   *
   * @return the right drive encoder
   */
  public CANEncoder getRightEncoder() {
    return m_rightEncoder;
  }

  /**
   * Sets the max output of the drive.  Useful for scaling the drive to drive more slowly.
   *
   * @param maxOutput the maximum output to which the drive will be constrained
   */
  public void setMaxOutput(double maxOutput) {
    m_drive.setMaxOutput(maxOutput);
  }

  /**
   * Zeroes the heading of the robot.
   */
  public void zeroHeading() {
    m_gyro.reset();
  }

  /**
   * Returns the heading of the robot.
   *
   * @return the robot's heading in degrees, from -180 to 180
   */
  public double getHeading() {
    return m_gyro.getRotation().getDegrees();
  }

  /**
   * Returns the turn rate of the robot.
   *
   * @return The turn rate of the robot, in degrees per second
   */
  public double getTurnRate() {
    return -m_gyro.getRate();
  }
  /**
   * Drives the robot using base mecanum (y stick 1 = forward, x stick 1 = sideways, x stick 2 = rotation)
   *
   * @param x = speed in x direction
   * @param y = speed in y direction
   * @param c = rotation speed
   */
  public void mecanumDrive(double x, double y, double c) {
    m_drive.driveCartesian(x*DriveConstants.speed,-y*DriveConstants.speed,c*DriveConstants.speed);
  }
  /**
   * Drives the robot using base mecanum (y stick 1 = forward, x stick 1 = sideways, x stick 2 = rotation)
   * This, however, is in relation to the field instead of the robot
   * @param x = speed in x direction
   * @param y = speed in y direction
   * @param c = rotation speed
   * @param theta = navx gyro angle
   */
  public void mecanumDriveGyro(double x, double y, double c, double theta)
  {
      m_drive.driveCartesian(-x, y, c, theta);
  }
  public void testMotor(double power){
      frontLeft.set(power*DriveConstants.speed);
      frontRight.set(power*DriveConstants.speed);
      backLeft.set(power*DriveConstants.speed);
      backRight.set(power*DriveConstants.speed);
  }
  public static void speedControl(double Speed){
    DriveConstants.speed = Speed;
}
}
