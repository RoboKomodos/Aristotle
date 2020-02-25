/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.XboxController.Button;
import edu.wpi.first.wpilibj2.command.*;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.subsystems.*;

/**
 * This class is where the bulk of the robot should be declared.  Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls).  Instead, the structure of the robot
 * (including subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {
  //init controller
  XboxController xbox = new XboxController(Constants.controllerport);
  // The robot's subsystems and commands are defined here...
  public driveTrain m_driveTrain;
  output m_output; //output


  /**
   * The container for the robot.  Contains subsystems, OI devices, and commands.
   */
  public RobotContainer() {
    //init output object
    m_output = new output();
    // Configure the button bindings
    m_driveTrain = new driveTrain();
    configureButtonBindings();
  }

  /**
   * Use this method to define your button->command mappings.  Buttons can be created by
   * instantiating a {@link GenericHID} or one of its subclasses ({@link
   * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then passing it to a
   * {@link edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */
  private void configureButtonBindings() {
    m_driveTrain.setDefaultCommand(new RunCommand(()->m_driveTrain.arcadeDrive(
      -deadzone(xbox.getX(GenericHID.Hand.kLeft)),
      deadzone(xbox.getY(GenericHID.Hand.kLeft))
    ),m_driveTrain));
    new AnalogButton(xbox, 3).whenPressed(new InstantCommand(()->m_driveTrain.setSpeed(Constants.mikhailSpeed))).whenReleased(new InstantCommand(()->m_driveTrain.setSpeed(1)));
    new JoystickButton(xbox, Button.kY.value).whenPressed(new InstantCommand(m_output::startWheel, m_output)).whenReleased(new InstantCommand(m_output::stopWheel, m_output));
  }
  /**
   * Removes the oscillation of joystick positions close to zero
   * @param position is the joystick value between -1 and 1 for the x or y axis
   * @return double
   */
  private double deadzone(double position){
    double sign = position/Math.abs(position);
    return Math.abs(position)<=Constants.deadzone?0:sign*(Math.abs(position)-Constants.deadzone)/(1-Constants.deadzone);
    //binds y to output and runs when held

  }
  

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    // An ExampleCommand will run in autonomous
    return null;
  }
}
