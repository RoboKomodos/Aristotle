/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.I2C;

/**
 * The Constants class provides a convenient place for teams to hold robot-wide numerical or boolean
 * constants.  This class should not be used for any other purpose.  All constants should be
 * declared globally (i.e. public static).  Do not put anything functional in this class.
 *
 * <p>It is advised to statically import this class (or one of its inner classes) wherever the
 * constants are needed, to reduce verbosity.
 */
public final class Constants {
    //controller port
    public static final int controllerport = 0;
    //input
    public static final int inputPort = 3;
    public static final double  inputSpeed = .6;
    //output
    public static final int outputMotorPort = 2;
    public static final double outputSpeed = 1;
    //elevator
    public static final int elevatorPort = 4;
    public static final double elevatorSpeed = 0.6;
    //drivetrain
    public static final int leftmotor = 0;
    public static final int rightmotor = 1;
    public static final double deadzone = 0.05;
    public static final double mikhailSpeed = 0.35;
    public static final int mikhailAxis = 3;
    //color wheel
    public static final int colorWheelPort = 6;
    public static final double colorWheelSpeed = 0.5;
    public static final I2C.Port i2cport = I2C.Port.kOnboard;
    public static final int colorWheelAxis = 2;
    public static final int stage3Degree = 90;
    public static final int stage2Degree = 270;

    
}
