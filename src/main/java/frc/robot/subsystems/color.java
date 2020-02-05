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

import edu.wpi.first.wpilibj.util.Color;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj.Victor;

import frc.robot.Constants;

public class color extends SubsystemBase {
  //declare color sensor
  ColorSensorV3 m_ColorSensorV3 = new ColorSensorV3(Constants.i2cport);
  //declare color matcher
  ColorMatch m_colorMatcher = new ColorMatch();
  //declare color values
  final Color BlueTarget = ColorMatch.makeColor(0.143, 0.427, 0.429);
  final Color GreenTarget = ColorMatch.makeColor(0.197, 0.561, 0.240);
  final Color RedTarget = ColorMatch.makeColor(0.561, 0.232, 0.114);
  final Color YellowTarget = ColorMatch.makeColor(0.361, 0.524, 0.113);

  //current color values
  Color detected;
  Color needed;
  Color seenByRobot; //color needed is not what the robot sees

  //declare motor controllers
  Victor motor = new Victor(Constants.colorWheelPort);



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
      Color needed = BlueTarget;
      Color seenByRobot = RedTarget;
    }
    if(neededColor == "Green"){
      Color needed = GreenTarget;
      Color seenByRobot = YellowTarget;
    }
    if(neededColor == "Red"){
      Color needed = RedTarget;
      Color seenByRobot = BlueTarget;
    }
    if(neededColor == "Yellow"){
      Color needed = YellowTarget;
      Color seenByRobot = GreenTarget;
    }
  }

  /**
   * detects color and spins to needed color
   */
  public void spinToColor(){
    motor.set(0.05);
    while(m_colorMatcher.matchClosestColor(m_ColorSensorV3.getColor()).color != seenByRobot){
      continue;
    }
    motor.set(0.0);
    
    ///////////////////////////////////////////////////////
    
    
    motor.set(0.05);
    while(true){
      Color detected = m_ColorSensorV3.getColor();
      ColorMatchResult c = m_colorMatcher.matchClosestColor(detected);

      if(c.color==BlueTarget){
        System.out.println("Blue "+c.confidence);
      }
      else if(c.color==YellowTarget){
        System.out.println("Yellow "+c.confidence);
      }
      else if(c.color==GreenTarget){
        System.out.println("Green "+c.confidence);
      }
      else if(c.color==RedTarget){
        System.out.println("Red "+c.confidence);
      }
      if(c.color == seenByRobot){
        motor.set(0.0);
        System.out.println("Match "+c.confidence);
        break;
      }
    }
    
    
  }


  /**
   * start wheel for stage 2
   */
  public void startWheel(){
    motor.set(Constants.stage2WheelSpeed);
  }
  /**
   * stop wheel for stage 2
   */
  public void stopWheel(){
    motor.set(0.0);
  }


  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
