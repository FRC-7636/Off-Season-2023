// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.AbsoluteEncoder;
import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.SparkMaxPIDController;
import com.revrobotics.CANSparkMax.ControlType;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.Constants.IntakeConstants;

public class Intake extends SubsystemBase {
  CANSparkMax cubeintake = new CANSparkMax(IntakeConstants.cubeintakeport, MotorType.kBrushless);
  CANSparkMax coneintake = new CANSparkMax(IntakeConstants.coneintakeport, MotorType.kBrushless);
  CANSparkMax turn = new CANSparkMax(IntakeConstants.turnport, MotorType.kBrushless);
  // DigitalInput ls = new DigitalInput(0);
  RelativeEncoder turnEncoder;
  // AbsoluteEncoder turnEncoder;

  SparkMaxPIDController turnPidController;

  /** Creates a new Intake. */
  public Intake() {
    cubeintake.restoreFactoryDefaults();
    coneintake.restoreFactoryDefaults();
    turn.restoreFactoryDefaults();

    cubeintake.setInverted(false);
    coneintake.setInverted(false);
    turn.setInverted(false);

    cubeintake.setIdleMode(IdleMode.kBrake);
    coneintake.setIdleMode(IdleMode.kBrake);
    turn.setIdleMode(IdleMode.kBrake);
    turnEncoder= turn.getEncoder();
    turnEncoder.setPosition(0);

    coneintake.setSmartCurrentLimit(30);
    cubeintake.setSmartCurrentLimit(30);


    turnPidController = turn.getPIDController();


    turnPidController.setFF(0.0001, 0);
    turnPidController.setP(0.000005, 0);
    turnPidController.setI(0, 0);
    turnPidController.setD(0, 0);
    turnPidController.setOutputRange(-1, 1);

    turnPidController.setSmartMotionMaxAccel(2000*4, 0);
    turnPidController.setSmartMotionMaxVelocity(2500*4, 0);
    turnPidController.setReference(0.1, ControlType.kSmartMotion);
     }

     public void intakecone(){
      cubeintake.set(0.5);
      coneintake.set(0.5);
     }

     public void intakecube(){
      cubeintake.set(0.5);
      coneintake.set(-0.5);
     }
     public void keepcube(){
      cubeintake.set(0.01);
      coneintake.set(-0.01);
     }
     public void neoStop(){
      cubeintake.set(0);
      coneintake.set(0);
     }
     public void shootcone(){
      cubeintake.set(-0.9);
      coneintake.set(-0.9);
     }  
     public void shootcube(){     
      cubeintake.set(-0.9);
      coneintake.set(0.9);
     }
     public void shootangle(){
      turnPidController.setReference(6, ControlType.kSmartMotion);
     }
     public void autoshootangle(){
      turnPidController.setReference(6.6, ControlType.kSmartMotion);
     }
     public void shootangleback(){
      turnPidController.setReference(0.7, ControlType.kSmartMotion);
     }
     public void floorcubeangle(){
      turnPidController.setReference(13.5, ControlType.kSmartMotion);
     }
     public void floorconeangle(){
      turnPidController.setReference(11.3, ControlType.kSmartMotion);
     }
     public void hmconeangle(){
      turnPidController.setReference(10.7, ControlType.kSmartMotion);
     }
     public void portalangle(){
      turnPidController.setReference(3, ControlType.kSmartMotion);
     }
     public void adjustup(){
      turn.set(0.1);
     }
     public void adjustdown(){
      turn.set(-0.1);
     }
    //  public void floordowncone(){
    //   turnPidController.setReference(15, ControlType.kSmartMotion);
    //  }
     public void stop(){
      turn.set(0);
     }

  // public void intakeStart(){
  //    m_Timer.start();
  //   intakeleft.set(0.1);
  //   intakeright.set(-0.1);
  //  }


  //   }
  // public void mini(){
  //   intakeleft.set(0.05);
  //   intakeright.set(-0.05);
  //  }

  // // public void intakeStart(){
  // //    m_Timer.start();
  // //   intakeleft.set(0.4);
  
  // //   if (m_Timer.get() > 3.0) {
  // //     intakeleft.set(0.0);
  // //       m_Timer.stop();
  // //       m_Timer.reset();
  // //   }


  // // }

public double conevolt(){
 return coneintake.getBusVoltage();
}
public double cubevolt(){
  return cubeintake.getBusVoltage();
}

// private static double buttonoffset=0 ;
// public void buttonoffsetdown(Boolean trigger){
//    buttonoffset--;
// }
// public void buttonoffsetup(Boolean trigger){
//    buttonoffset++;
// }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
      // if(ls.get()){
      //   // turnPidController.setReference(0, ControlType.kSmartMotion);
      //   turnEncoder.setPosition(0);
      // }
      SmartDashboard.putNumber("position", turnEncoder.getPosition());

      // SmartDashboard.putBoolean("trueornot", ls.get());
  }
}
