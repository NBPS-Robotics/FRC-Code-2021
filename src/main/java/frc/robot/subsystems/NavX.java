// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;


import edu.wpi.first.wpilibj2.command.SubsystemBase;

import com.kauailabs.navx.frc.AHRS;
import edu.wpi.first.wpilibj.SerialPort;


public class NavX extends SubsystemBase {
    //its the navx 
    private AHRS navx = new AHRS(SerialPort.Port.kMXP);

    //now in a subsystem
    public NavX() {
        navx.reset();
    }

    public double getAngle()
    {
        return navx.getAngle();
    }

    public void reset()
    {
        navx.reset();
    }

}