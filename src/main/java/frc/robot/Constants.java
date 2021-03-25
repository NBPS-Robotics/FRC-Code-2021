// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

/**
 * The Constants class provides a convenient place for teams to hold robot-wide numerical or boolean
 * constants. This class should not be used for any other purpose. All constants should be declared
 * globally (i.e. public static). Do not put anything functional in this class.
 *
 * <p>It is advised to statically import this class (or one of its inner classes) wherever the
 * constants are needed, to reduce verbosity.
 */
public final class Constants {
    public static final class DriveConstants {
        public static final int FrontLeft_ID = 2;
        public static final int FrontRight_ID = 6;
        public static final int BackLeft_ID = 3;
        public static final int BackRight_ID = 5;
    }
    
    public static final class IntakeConstants {
        public static final int Intake_ID = 4;
        public static final double IntakeSpeed = -0.8;
    }

    public static final class ShooterConstants {
        public static final int Shooter_ID = 1;
        public static final int Servo_ID = 0;

        public static final double Servo_Up = .5;
        public static final double Servo_Down = 0;

        public static final double Shooter_Speed = -0.9; //ideal motor speed to run the shooter at
        public static final double Charge_Delay = 4; //time in seconds for the shooter to reach ideal speed
    }
    public static final class JoystickConstants {
        public static final int XStick1_ID = 0;
        public static final int YStick1_ID = 1;
        public static final int XStick2_ID = 4;
        public static final int YStick2_ID = 5;

        public static final int Joystick1_ID = 0;
        public static final int Joystick2_ID = 1;
    }
    public static final class LimelightConstants {
        public static final double Ideal_Strafe_Value = 0.4;
        public static final double Ideal_Forward_Value = 0.2;
        public static final double Ideal_Rotate_Value = 0.15;

        public static final double Ideal_Area_Value = 2.5;
        public static final double Area_Range_Value = 0.3;

        public static final double kP = 0.005;
    }
    public static final class AutoConstants {
        public static final double Auto_Shoot_Power = -0.9;
        public static final double Auto_Charge_Delay = 2;
        public static final double Auto_Shoot_EndTime = 5;

        
    }
}
