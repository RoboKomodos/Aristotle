/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;

import edu.wpi.first.wpilibj.controller.PIDController;
import edu.wpi.first.wpilibj2.command.PIDSubsystem;
import frc.robot.Constants;
import java.lang.System;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import org.java_websocket.WebSocket;
import org.java_websocket.drafts.Draft;
import org.java_websocket.drafts.Draft_6455;
import org.java_websocket.handshake.ClientHandshake;
import org.java_websocket.server.WebSocketServer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetSocketAddress;
import java.net.UnknownHostException;
import java.nio.ByteBuffer;
import java.util.Collections;

public class driveTrain extends PIDSubsystem {
  /**
   * Creates a new driveTrain.
   */
  private VictorSPX m_left;
  private VictorSPX m_right;
  private double m_speed = 1;
  private OutputStream outputStream;
  private ChatServer server;

  class ChatServer extends WebSocketServer {

    public ChatServer(int port) throws UnknownHostException {
        super(new InetSocketAddress(port));
    }

    public ChatServer(InetSocketAddress address) {
        super(address);
    }

    public ChatServer(int port, Draft_6455 draft) {
        super(new InetSocketAddress(port), Collections.<Draft>singletonList(draft));
    }

    @Override
    public void onOpen(WebSocket conn, ClientHandshake handshake) {
    }

    @Override
    public void onClose(WebSocket conn, int code, String reason, boolean remote) {

    }

    @Override
    public void onMessage(WebSocket conn, String message) {
        broadcast(message);
        System.out.println(conn + ": " + message);
    }

    @Override
    public void onMessage(WebSocket conn, ByteBuffer message) {
    }

    public void print(String s){
        broadcast(s);
    }

    @Override
    public void onError(WebSocket conn, Exception ex) {
        ex.printStackTrace();
        if (conn != null) {
            // some errors like port binding failed may not be assignable to a specific websocket
        }
    }

    @Override
    public void onStart() {
        System.out.println("Server started!");
        setConnectionLostTimeout(0);
        setConnectionLostTimeout(100);
    }

}

  public driveTrain() {
    super(
        // The PIDController used by the subsystem
        new PIDController(0, 0, 0));
    m_left = new VictorSPX(Constants.leftmotor);
    m_right = new VictorSPX(Constants.rightmotor);

    m_left.configOpenloopRamp(0.5);
    m_right.configOpenloopRamp(0.5);
    int port = 1180;
    server = new ChatServer(port);
    s.start();
  }
  /**
   * Sets the speed of the motors
   * @param speed as a double between 0 and 1
   */
  public void setSpeed(double speed){
    m_speed = speed;
  }
  /**
   * Sets the motor speed and direction
   * @param dx the joystick x position
   * @param dy the joystick y position
   */
  public void arcadeDrive(double dx, double dy){
    server.print(dx + " " + dy);
    
    dx = -dx;
    m_left.set(ControlMode.PercentOutput, m_speed*( dx+dy));
    m_right.set(ControlMode.PercentOutput, m_speed*( dy-dx));
  }
  @Override
  public void useOutput(double output, double setpoint) {
    // Use the output here
  }

  @Override
  public double getMeasurement() {
    // Return the process variable measurement here
    return 0;
  }
}
