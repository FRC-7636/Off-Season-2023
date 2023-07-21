// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix.led.CANdle;
import com.ctre.phoenix.led.StrobeAnimation;
import com.ctre.phoenix.led.CANdle.LEDStripType;
import frc.robot.subsystems.Intake;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.CandleConstants;

public class Candle extends SubsystemBase {
  /** Creates a new Candle. */
  CANdle m_Candle = new CANdle(CandleConstants.CandlePort);
  Intake mIntake;
  StrobeAnimation m_StrobeAnimation = new StrobeAnimation(0, 0, 0);
  public Candle() {
    m_Candle.configFactoryDefault();
    m_Candle.clearAnimation(0);
    m_Candle.setLEDs(3, 31, 91);
    m_Candle.configLEDType(LEDStripType.BRG);

    m_Candle.configBrightnessScalar(1);
  }

  public void Red(){
    m_Candle.clearAnimation(0);
    m_Candle.setLEDs(255, 0, 0);
  }

  public void Green(){
    m_Candle.clearAnimation(0);
    m_Candle.setLEDs(0, 255, 0);
  }

  public void Blue(){
    m_Candle.clearAnimation(0);
    m_Candle.setLEDs(0, 0, 255);
  }


  public void midlevel(){
    m_Candle.animate(m_StrobeAnimation);
    m_StrobeAnimation.setR(200);
    m_StrobeAnimation.setG(200);
    m_StrobeAnimation.setB(0);
    m_StrobeAnimation.setSpeed(0.8);
  }

  public void highlevel(){
    m_Candle.animate(m_StrobeAnimation);
    m_StrobeAnimation.setR(85);
    m_StrobeAnimation.setG(59);
    m_StrobeAnimation.setB(190);
    m_StrobeAnimation.setSpeed(0.8);
  }
  public boolean conejudge(){
    if(mIntake.conevolt()<10){
      return true;
    }else{
      return false;
    }
  } 
  public boolean cubejudge(){
    if(mIntake.cubevolt()<10){
      return true;
    }else{
      return false;
    }
  }
  public void coneisready(){
    if(conejudge()==true){
      m_Candle.animate(m_StrobeAnimation);
      m_StrobeAnimation.setR(0);
      m_StrobeAnimation.setG(200);
      m_StrobeAnimation.setB(0);
      m_StrobeAnimation.setSpeed(0.8);
  }
   else{
    m_Candle.animate(m_StrobeAnimation);
    m_StrobeAnimation.setR(200);
    m_StrobeAnimation.setG(0);
    m_StrobeAnimation.setB(0);
    m_StrobeAnimation.setSpeed(0.8);
  }
}
  public void cubeisready(){
    if(cubejudge()==true){
      m_Candle.animate(m_StrobeAnimation);
      m_StrobeAnimation.setR(0);
      m_StrobeAnimation.setG(200);
      m_StrobeAnimation.setB(0);
      m_StrobeAnimation.setSpeed(0.8);
    }
     else{
      m_Candle.animate(m_StrobeAnimation);
      m_StrobeAnimation.setR(200);
      m_StrobeAnimation.setG(0);
      m_StrobeAnimation.setB(0);
      m_StrobeAnimation.setSpeed(0.8);
    }
  }
  
  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
