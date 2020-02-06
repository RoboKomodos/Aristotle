/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.revrobotics.ColorMatch;
import com.revrobotics.ColorMatchResult;
import com.revrobotics.ColorSensorV3;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import com.revrobotics.CANSparkMax;

import edu.wpi.first.wpilibj.util.Color;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj.Victor;

import frc.robot.Constants;

public class color extends SubsystemBase {
  //declare color sensor
  private ColorSensorV3 m_ColorSensorV3 = new ColorSensorV3(Constants.i2cport);
  //declare color matcher
  private ColorMatch m_colorMatcher = new ColorMatch();
  //declare color values
  private final Color BlueTarget = ColorMatch.makeColor(0.143, 0.427, 0.429);
  private final Color GreenTarget = ColorMatch.makeColor(0.197, 0.561, 0.240);
  private final Color RedTarget = ColorMatch.makeColor(0.561, 0.232, 0.114);
  private final Color YellowTarget = ColorMatch.makeColor(0.361, 0.524, 0.113);

  //current color values
  private Color detected;
  private Color needed;
  //color needed is not what the robot sees
  private Color seenByRobot = BlueTarget;

  //declare motor controllers
  private CANSparkMax motor = new CANSparkMax(Constants.colorWheelPort, MotorType.kBrushless);



  /**
   * Creates a new color.
   */
  public color() {
    //add color values to the matcher
    m_colorMatcher.addColorMatch(BlueTarget);
    m_colorMatcher.addColorMatch(GreenTarget);
    m_colorMatcher.addColorMatch(RedTarget);
    m_colorMatcher.addColorMatch(YellowTarget);
  }
  /**
   * Set needed colors
   * @param neededColor String e.g. "Blue","Green","Red","Yellow"
   */
  public void setColor(String neededColor){
    if(neededColor == "Blue"){
      needed = BlueTarget;
      seenByRobot = RedTarget;
    }
    if(neededColor == "Green"){
      needed = GreenTarget;
      seenByRobot = YellowTarget;
    }
    if(neededColor == "Red"){
      needed = RedTarget;
      seenByRobot = BlueTarget;
    }
    if(neededColor == "Yellow"){
      needed = YellowTarget;
      seenByRobot = GreenTarget;
    }
  }
  /**
   * gets current color seen
   * @return returns current color seen by robot
   */
  public Color getCurrentColor(){
    Color detected = m_ColorSensorV3.getColor();
    ColorMatchResult c = m_colorMatcher.matchClosestColor(detected);
    return c.color;
  }

  /**
   * tests to see if current color is the needed color
   * @return t/f depending on color needed
   */
  public boolean isColorCorrect(){
    Color c = getCurrentColor();
    if(c == seenByRobot){
      return true;
    }
    else{
      return false;
    }
  }
  


  /**
   * start wheel
   */
  public void startWheel(){
    motor.set(Constants.colorWheelSpeed);
  }
  /**
   * stop wheel
   */
  public void stopWheel(){
    motor.set(0.0);
  }


  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
