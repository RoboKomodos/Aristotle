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
import com.revrobotics.ControlType;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import java.util.Hashtable;

import com.revrobotics.CANEncoder;
import com.revrobotics.CANPIDController;
import com.revrobotics.CANSparkMax;

import edu.wpi.first.wpilibj.util.Color;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
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
  //color needed for the robot to see
  private Color seenByRobot = null;
  //declare motor controllers
  private CANSparkMax motor = new CANSparkMax(Constants.colorWheelPort, MotorType.kBrushless);
  //Hashtable with all of the colors
  private Hashtable<String,Color> DesiredColor= new Hashtable<String,Color>();



  private CANPIDController m_pidController = motor.getPIDController();
  private CANEncoder m_encoder = motor.getEncoder();
  private double kP, kI, kD, kIz, kFF, kMaxOutput, kMinOutput;

  kP = 0.1; 
  kI = 1e-4;
  kD = 1; 
  kIz = 0; 
  kFF = 0; 
  kMaxOutput = 1; 
  kMinOutput = -1;

  private double rotation = 5.0;


  /**
   * Creates a new color.
   */
  public color() {
    //add color values to the matcher
    m_colorMatcher.addColorMatch(BlueTarget);
    m_colorMatcher.addColorMatch(GreenTarget);
    m_colorMatcher.addColorMatch(RedTarget);
    m_colorMatcher.addColorMatch(YellowTarget);
    //add color values to the hashtable
    DesiredColor.put("blue",RedTarget);
    DesiredColor.put("green",YellowTarget);
    DesiredColor.put("red",BlueTarget);
    DesiredColor.put("yellow",GreenTarget);


    m_pidController.setP(kP);
    m_pidController.setI(kI);
    m_pidController.setD(kD);
    m_pidController.setIZone(kIz);
    m_pidController.setFF(kFF);
    m_pidController.setOutputRange(kMinOutput, kMaxOutput);

    SmartDashboard.putNumber("P Gain", kP);
    SmartDashboard.putNumber("I Gain", kI);
    SmartDashboard.putNumber("D Gain", kD);
    SmartDashboard.putNumber("I Zone", kIz);
    SmartDashboard.putNumber("Feed Forward", kFF);
    SmartDashboard.putNumber("Max Output", kMaxOutput);
    SmartDashboard.putNumber("Min Output", kMinOutput);
    SmartDashboard.putNumber("Rotations", rotation);

    SmartDashboard.putNumber("SetPoint", rotation);
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
   * gets needed color and starts spinning the wheel
   * **for stage 3
   */
  public void startColorSeekingWheel(){
    seenByRobot = DesiredColor.get(DriverStation.getInstance().getGameSpecificMessage().toLowerCase());
    motor.set(Constants.colorWheelSpeed);
  }
  /**
   * stops wheel
   */
  public void stopWheel(){
    motor.set(0.0);
  }



  @Override
  public void periodic() {
    // This method will be called once per scheduler run

    // read PID coefficients from SmartDashboard
    double p = SmartDashboard.getNumber("P Gain", 0);
    double i = SmartDashboard.getNumber("I Gain", 0);
    double d = SmartDashboard.getNumber("D Gain", 0);
    double iz = SmartDashboard.getNumber("I Zone", 0);
    double ff = SmartDashboard.getNumber("Feed Forward", 0);
    double max = SmartDashboard.getNumber("Max Output", 0);
    double min = SmartDashboard.getNumber("Min Output", 0);
    double r = SmartDashboard.getNumber("Rotations", 5);

    SmartDashboard.putNumber("ProcessVariable", m_encoder.getPosition());

    // if PID coefficients on SmartDashboard have changed, write new values to controller
    if((p != kP)) { m_pidController.setP(p); kP = p; }
    if((i != kI)) { m_pidController.setI(i); kI = i; }
    if((d != kD)) { m_pidController.setD(d); kD = d; }
    if((iz != kIz)) { m_pidController.setIZone(iz); kIz = iz; }
    if((ff != kFF)) { m_pidController.setFF(ff); kFF = ff; }
    if((r!= rotation)) {rotation = r;}
    if((max != kMaxOutput) || (min != kMinOutput)) { 
      m_pidController.setOutputRange(min, max); 
      kMinOutput = min; kMaxOutput = max; 
    }
  }
}
