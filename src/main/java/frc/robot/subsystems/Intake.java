// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import frc.robot.Constants.IntakeConstants;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

public class Intake extends SubsystemBase {
    //init the motors
    public static CANSparkMax intake = new CANSparkMax(IntakeConstants.Intake_ID, MotorType.kBrushless);

    /** Creates a new ExampleSubsystem. */
    public Intake() {
    }
    /**
     * will i keep the name as succ? probably
     * can you change it? no.
     */
    public void succ(double speed) {
        intake.set(speed);
    }
}
