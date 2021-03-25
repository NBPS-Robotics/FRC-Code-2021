package frc.robot.subsystems;

import frc.robot.Constants.JoystickConstants;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class JoyStick extends SubsystemBase {
    //init the motors
    private final Joystick stick = new Joystick(JoystickConstants.Joystick1_ID);
    /** Creates a new ExampleSubsystem. */
    public JoyStick() {
    }
    /**
     * will i keep the name as succ? probably
     * can you change it? no.
     */
    public double getAxis(int axis){
        return stick.getRawAxis(axis);
    }
}