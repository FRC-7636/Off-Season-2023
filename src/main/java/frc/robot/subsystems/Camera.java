// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import org.opencv.core.Mat;
import org.opencv.core.Point;
import org.opencv.core.Scalar;
import org.opencv.imgproc.Imgproc;

import edu.wpi.first.cameraserver.CameraServer;
import edu.wpi.first.cscore.CvSink;
import edu.wpi.first.cscore.CvSource;
import edu.wpi.first.cscore.UsbCamera;
import edu.wpi.first.cscore.VideoMode.PixelFormat;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;


public class Camera extends SubsystemBase {
  /** Creates a new Camera. */
  public Camera() {

    CameraServer.startAutomaticCapture();
    System.out.print(CameraServer.startAutomaticCapture());
    // camera.setVideoMode(PixelFormat.kMJPEG, 320, 240, 120);
    // new Thread(() -> {
    //     CvSink cvSink = CameraServer.getVideo();
    //     CvSource outputStream = CameraServer.putVideo("camera stream", 320, 240);
    //     Mat source = new Mat();
    //     while(!Thread.interrupted()) {
    //         cvSink.grabFrame(source);
    //         outputStream.putFrame(source);
    //     }
    // }).start();
              };

  @Override
  public void periodic() {
  
  }
}
