/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.revrobotics.ColorSensorV3;
import com.revrobotics.ColorMatch;

import edu.wpi.first.wpilibj.util.Color;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class color extends SubsystemBase {

  ColorSensorV3 m_ColorSensorV3 = new ColorSensorV3(Constants.i2cport);
  ColorMatch m_colorMatcher = new ColorMatch();
  /**
   * Creates a new color.
   */
  public color() {
   

    final Color BlueTarget = ColorMatch.makeColor(0.143, 0.427, 0.429);
    final Color GreenTarget = ColorMatch.makeColor(0.197, 0.561, 0.240);
    final Color RedTarget = ColorMatch.makeColor(0.561, 0.232, 0.114);
    final Color YellowTarget = ColorMatch.makeColor(0.361, 0.524, 0.113);

    m_colorMatcher.addColorMatch(BlueTarget);
    m_colorMatcher.addColorMatch(GreenTarget);
    m_colorMatcher.addColorMatch(RedTarget);
    m_colorMatcher.addColorMatch(YellowTarget);
  }

  

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    Color detected = m_ColorSensorV3.getColor();
    System.out.println(m_colorMatcher.matchClosestColor(detected));
  }
}
