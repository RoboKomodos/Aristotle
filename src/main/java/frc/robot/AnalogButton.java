/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;


import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.button.Button;

/**
 * Add your docs here.
 */
public class AnalogButton extends Button{
    GenericHID m_joystick;
    int m_axis;
    double m_threshold;
    int m_location;
    boolean locationBased=false;
    /**
     * An implementation of button for analog buttons such as the triggers on an xbox controller
     * @param joystick the joystick to be used for the button
     * @param axis the axis that should be turned into a button
     * @param threshold the level to return true at
     */
    public AnalogButton(GenericHID joystick,int axis,double threshold){
        m_joystick = joystick;
        m_axis = axis;
        m_threshold = threshold;
    }
    /**
     * An implementation of button for analog buttons such as the triggers on an xbox controller
     * @param joystick the joystick to be used for the button
     * @param axis the axis that should be turned into a button
     */
    public AnalogButton(GenericHID joystick,int axis){
        m_joystick = joystick;
        m_axis = axis;
        m_threshold = .75;
    }
    /**
     * An implementation of button for analog buttons such as the Dpad that has different values for different directions.
     * @param joystick the joystick to be used for the button
     * @param axis the axis that should be turned into a button
     * @param location the target value for the button.
     * @param the range allowed around tarket value
     */
    public AnalogButton(GenericHID joystick,int axis,int location,int range){
        m_joystick = joystick;
        m_axis = axis;
        m_threshold = range;
        m_location=location;
        locationBased=true;
    }
    @Override
    public boolean get()
    {
        if(!locationBased)
            return m_joystick.getRawAxis(m_axis)>m_threshold;
        else
            return Math.abs(m_joystick.getRawAxis(m_axis)-m_location) < m_threshold;
    }
}
