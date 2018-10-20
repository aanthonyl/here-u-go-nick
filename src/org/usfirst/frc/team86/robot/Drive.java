package org.usfirst.frc.team86.robot;
import org.usfirst.frc.team86.util.IUpdate;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

public class Drive implements IUpdate{
private TalonSRX leftFront;
private TalonSRX leftBack;
private TalonSRX rightFront;
private TalonSRX rightBack;
	public Drive(TalonSRX leftFront, TalonSRX leftBack, TalonSRX rightFront, TalonSRX rightBack){
	this.leftFront = leftFront;
	this.leftBack = leftBack;
	this.rightFront = rightFront;
	this.rightBack = rightBack;
}
	public void init(){
		leftFront.setInverted(false);
		leftBack.setInverted(false);
		rightFront.setInverted(true);
		rightBack.setInverted(true);
		
		leftFront.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder, 0, 0);
		leftBack.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder, 0, 0);
		rightFront.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder, 0, 0);
		rightBack.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder, 0, 0);
		
		leftFront.setSensorPhase(false);
		leftBack.setSensorPhase(false);
		rightFront.setSensorPhase(false);
		rightBack.setSensorPhase(false);
		
		leftBack.set(ControlMode.Follower, IO.leftFront.getDeviceID());
		rightBack.set(ControlMode.Follower, IO.rightFront.getDeviceID());
		
		leftFront.enableVoltageCompensation(true);
		leftBack.enableVoltageCompensation(true);
		rightFront.enableVoltageCompensation(true);
		rightBack.enableVoltageCompensation(true);
		
		leftFront.configVoltageCompSaturation(12.0, 0);
		leftBack.configVoltageCompSaturation(12.0, 0);
		rightFront.configVoltageCompSaturation(12.0, 0);
		rightBack.configVoltageCompSaturation(12.0, 0);
		
		leftFront.configVoltageMeasurementFilter(32, 0);
		leftBack.configVoltageMeasurementFilter(32, 0);
		rightFront.configVoltageMeasurementFilter(32, 0);
		rightBack.configVoltageMeasurementFilter(32, 0);

		leftFront.configClosedloopRamp(1.0, 0);
		rightFront.configClosedloopRamp(1.0, 0);
		

	}
	public void update(){
		
		leftFront.set(ControlMode.PercentOutput, -1*(JoystickIO.leftJoystick.getY()));
		rightFront.set(ControlMode.PercentOutput, -1*(JoystickIO.rightJoystick.getY()));

		
		if (JoystickIO.gearShiftDown.onButtonPressed()) {
		IO.gearShifter.set(true);
	} else if (JoystickIO.gearShiftUp.onButtonPressed()) {
		IO.gearShifter.set(false);
	}

	}
}

