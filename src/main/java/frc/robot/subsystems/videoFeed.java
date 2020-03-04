/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.cscore.UsbCamera;
import edu.wpi.cscore.VideoSink;
import edu.wpi.cscore.VideoSource.ConnectionStrategy;
import edu.wpi.first.cameraserver.CameraServer;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class videoFeed extends SubsystemBase {
  UsbCamera m_camera1;
  UsbCamera m_camera2;
  VideoSink m_sink;
  boolean cameraToggle = false;

  public videoFeed() {
    m_camera1 = CameraServer.getInstance().startAutomaticCapture(0);
    m_camera2 = CameraServer.getInstance().startAutomaticCapture(1);
    m_sink = CameraServer.getInstance().getServer();
    m_camera1.setConnectionStrategy(ConnectionStrategy.kKeepOpen);
    m_camera2.setConnectionStrategy(ConnectionStrategy.kKeepOpen);
    m_sink.setSource(m_camera1);
  }

  public void toggle(){
    if(cameraToggle){
      m_sink.setSource(m_camera1);
    }else{
      m_sink.setSource(m_camera2);
    }
    cameraToggle=!cameraToggle;
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
