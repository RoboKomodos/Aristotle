/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

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
    //port for ball sucker thing in ipor
    public static final int inputPort = 3;
    //press button for thing above
    public static final double  inputSpeed = .6;

     
    //motor controller ports
    public static final int elevatorPort = 4;
    public static final int leftmotor = 0;
    public static final int rightmotor = 1;
    public static final int outputMotorPort = 2;
    //other constants

    public static final int forwardChannel = 0; //Pneumatic forward channel
    public static final int reverseChannel = 1; //Pneumatic reverse channel
    public static final int pneumaticAxis = 2;
    public static final double pneumaticTimeout = .5;
    public static final int pneumaticUpDegree = 0;
    public static final int pneumaticDownDegree = 180;
    public static final int pneumaticRange = 10;

    public static final double elevatorSpeed = 0.6; //speed for elevator to move at
    public static final double deadzone = 0.05;
    public static final double mikhailSpeed = 0.35;
    public static final double outputSpeed = 1;
    
}
