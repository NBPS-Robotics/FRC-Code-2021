// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import frc.robot.Constants.IntakeConstants;
import frc.robot.Constants.ShooterConstants;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import com.revrobotics.CANSparkMax;
import edu.wpi.first.wpilibj.Servo;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

public class Shooter extends SubsystemBase {
    //init the motors
    public static CANSparkMax shooter = new CANSparkMax(ShooterConstants.Shooter_ID, MotorType.kBrushless);
    public static CANSparkMax shooter2 = new CANSparkMax(ShooterConstants.Shooter2_ID, MotorType.kBrushless);
    public static Intake intake = new Intake();
    public static boolean state = false;
    /** Creates a new ExampleSubsystem. */
    public Shooter() {
        servo.set(ShooterConstants.Servo_Down);
        
        
    }
    private Servo servo = new Servo(ShooterConstants.Servo_ID);

    /**
     * @param speed = speed to shoot at
     */
    public void on(double speed, double speed2)
    {
        shooter.set(speed);
        shooter2.set(speed2);
    }
    public void off(){
        on(0,0);
    }
    public void shootGoal(double speed, double speed2){
        on(speed, speed2);
        servo.set(ShooterConstants.Servo_Down);
        intake.succ(IntakeConstants.IntakeSpeed);
        Time.wait(2000);
        servo.set(ShooterConstants.Servo_Up);
        Time.wait(500);
        servo.set(ShooterConstants.Servo_Down);
        Time.wait(1500);
        
        servo.set(ShooterConstants.Servo_Down);
        intake.succ(0);
        off();
    }
    /**
     * 
     * @param position = position for index servo to be at
     */
    public void moveServo(double position)
    {
        servo.set(position);
    }
}
