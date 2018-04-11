package org.usfirst.frc.team5109.robot;

#Latest code as of 4/11/18

import com.ctre.phoenix.motorcontrol.*;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import java.util.logging.Level;
import java.util.logging.Logger;
import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.networktables.*;
import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.DriverStation;
/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends IterativeRobot {
	TalonSRX leftMotor1 =  new TalonSRX(6);
	TalonSRX leftMotor2 =  new TalonSRX(10);
	TalonSRX rightMotor1 =  new TalonSRX(5);//10
	TalonSRX rightMotor2 =  new TalonSRX(4);//10
	TalonSRX rightElevatorMotor = new TalonSRX(2);
	TalonSRX leftElevatorMotor = new TalonSRX(1);
	TalonSRX intakeBags = new TalonSRX(8);
	TalonSRX scalar = new TalonSRX(0);
	Joystick leftJoy = new Joystick(0);
	Joystick rightJoy = new Joystick(1);
	Joystick operator = new Joystick(2);
	// Solenoids for gear shifting
	Solenoid Solenoid2 = new Solenoid(2);//1
	Solenoid Solenoid1 = new Solenoid(1);
	// Anand's Solenoids, 0 is used for clamping, 3 is used for extending
	Solenoid Solenoid0 = new Solenoid(0);
	boolean clamped = false;
	Solenoid Solenoid3 = new Solenoid(3);
	boolean extended = false;
	
	Solenoid Solenoid5 = new Solenoid(5);
	//Solenoids for gear shifting
	Solenoid Solenoid4 = new Solenoid(4);//1
	Compressor compressor;
	boolean lowgear = false;
	Encoder rightEncoder = new Encoder(0, 1, true); 
	Encoder leftEncoder = new Encoder(8, 9, false); 
	double  leftspeed = 0;
	double rightspeed = 0;
	long idealright = 0;
	long idealleft = 0;
	//t = 0;
	int Counter = 1;
	boolean anandAutoComplete = true;
	boolean anandSetter = false;
	/*boolean spinningBags = false;
	boolean spinningBags1 = false;*/
	
	/*double length = testEncoder.getDistance();
	//double period = testEncoder.getPeriod();
	
	boolean direction = testEncoder.getDirection();
	boolean stopped = testEncoder.getStopped();
	//For the encoder do not move 
	int count = 0;
	int i = 0;
	boolean testing = true;
	*/

	NetworkTableInstance inst = NetworkTableInstance.getDefault();
	NetworkTable imutable;
	

 

	


	
	/**
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code.
	 */
	@Override
	public void robotInit() {
	    compressor = new Compressor(0);
		CameraServer.getInstance().startAutomaticCapture();
		leftEncoder.setDistancePerPulse(1);
		rightEncoder.setDistancePerPulse(1);
		/*.0184
		/*NetworkTableInstance table = NetworkTableInstance.getDefault();
		NetworkTableInstance instance = NetworkTableInstance.getDefault();
		NetworkTable rootTable = instance.getTable("");
		System.out.println(rootTable);
		double[] defaultValue = new double[0];
		while(true) {
			double[] areas = table.getNumberArray("area",defaultValue);
			for(double area : areas) {
				System.out.println(area + " ");
			}
			System.out.println();
			Timer.delay(1);
		} */

		//exampleSolenoid.set(true);
		//exampleSolenoid.set(false);
		//c.setClosedLoopControl(true);
		//c.setClosedLoopControl(false);
		
		

	}

	/**
	 * This function is run once each time the robot enters autonomous mode.
	 */
	@Override
	public void autonomousInit() {
	  String gameData;
		leftEncoder.reset();
		rightEncoder.reset();
		idealright = rightEncoder.get();
		idealleft = leftEncoder.get();
		Counter = 1;

	}
	/**
	 * This function is called periodically during autonomous.
	 */
	
	
	public void autonomousPeriodic() {
		int startpos = 6;// 1 is right side, 2 is right middle, 3 is left middle, 4 is left side
		leftElevatorMotor.set(ControlMode.PercentOutput, 0.4);
		rightElevatorMotor.set(ControlMode.PercentOutput, -0.4);
		long leftCount = leftEncoder.get();
		long rightCount = rightEncoder.get();
		if(anandSetter == false) {
			anandAutoComplete = false;
			anandSetter = true;	
		}
		/*leftMotor1.set(ControlMode.PercentOutput, -.3);
		leftMotor2.set(ControlMode.PercentOutput, -.3);
		rightMotor1.set(ControlMode.PercentOutput, .2832);
		rightMotor2.set(ControlMode.PercentOutput, .2832);
		Timer.delay(5.2);
		leftMotor1.set(ControlMode.PercentOutput, 0);
		leftMotor2.set(ControlMode.PercentOutput, 0);
		rightMotor1.set(ControlMode.PercentOutput, 0);
		rightMotor2.set(ControlMode.PercentOutput, 0);
		leftCount = leftEncoder.get();
		rightCount = rightEncoder.get();
		System.out.println("left: " + leftCount);
		System.out.println("right: " + rightCount);*/
		//Solenoid3.set(true);
		//Timer.delay(.2);
		//intakeBags.set(ControlMode.PercentOutput, 1);
		
		/*if(Counter == 1) {	
			anandStraightSix();
			anandEjectProcess();
			Timer.delay(.5);
			anandBackUp();
			Counter++;
		} else {
			leftMotor1.set(ControlMode.PercentOutput, 0);
			leftMotor2.set(ControlMode.PercentOutput, 0);
			rightMotor1.set(ControlMode.PercentOutput, 0);
			rightMotor2.set(ControlMode.PercentOutput, 0);
		}*/
		String gameData = DriverStation.getInstance().getGameSpecificMessage ();
		if(gameData.length() > 0) {
			if (startpos == 1) {
				if(gameData.charAt(0) == 'R') {
					if(anandAutoComplete == false) {
						anandStraightNine();
						anand90Counter();
						anandSquareUp();
						anandEjectProcess();
						Timer.delay(.5);
						anandBackUp();
						anandAutoComplete = true;
					}
				}
				else if (anandAutoComplete == false){
					anandStraightNine();
					anandFun();
					anandAutoComplete = true;
				}
			}
			
			else if(startpos == 2) {
				if(gameData.charAt(0) == 'R') {
					if(anandAutoComplete == false) {
						anandStraightSix();
						anandEjectProcess();
						anandBackUp();
						anandAutoComplete = true;
					}
					else if(anandAutoComplete == false) {
						anandStraightSix();
						anandBackUp();
						anandAutoComplete = true;
					}
				}
			}
			
			else if(startpos == 3) {
				if(gameData.charAt(0) == 'L') {
					if(anandAutoComplete == false) {
						anandStraightSix();
						anandEjectProcess();
						anandBackUp();
						anandAutoComplete = true;
					}
				}
				else if(anandAutoComplete == false) {
					anandStraightSix();
					anandBackUp();
					anandAutoComplete = true;
				}
			}
			else if(startpos == 4) {
				if(gameData.charAt(0) == 'L') {
					if(anandAutoComplete == false) {
						anandStraightNine();
						anandAutoComplete = true;
					}
				}
				else if(anandAutoComplete == false) {
					anandStraightNine();
					anandAutoComplete = true;;
				}
			}
			else if(startpos == 5) {
				if(gameData.charAt(0) == 'L') {
					if(anandAutoComplete == false) {
						anandStraightSix();
						anandEjectProcess();
						anandBackUp();
						anandAutoComplete = true;
					}
				}
				else if(anandAutoComplete == false) {
					anandBitForward();
					anand90Clock();
					anandCrossField();
					anand90Counter();
					anandShortSix();
					anandEjectProcess();
					anandBackUp();
					anandAutoComplete = true;
				}
			}
			else if(startpos == 6) {
				if(gameData.charAt(0) == 'R') {
					if(anandAutoComplete == false) {
						anandStraightSix();
						anandEjectProcess();
						anandBackUp();
						anandAutoComplete = true;
					}
				}
				else if(anandAutoComplete == false) {
					anandBitForward();
					anand90Counter();
					anandCrossField();
					anand90Clock();
					anandShortSix();
					anandEjectProcess();
					anandBackUp();
					anandAutoComplete = true;
				}
			}
			else if(startpos == 7) {
					if(anandAutoComplete == false) {
						anandShortSix();
						anandEjectProcess();
						anandBackUp();
						anandAutoComplete = true;
					}
			}
			else if(startpos == 8) {
				if(anandAutoComplete == false) {
					anandQuickNine();
					anand90Counter();
					anandSquareUp();
					anandEjectProcess();
					anandBackUp();
					anandAutoComplete = true;
				}
			}
		}
	}
       
        
	
	
	@Override
	public void teleopInit() {
		leftEncoder.reset();
		rightEncoder.reset();
		CameraServer.getInstance().startAutomaticCapture();
	}

	/**
	 * This function is called periodically during teleoperated mode.
	 */
	
	

	public void teleopPeriodic() {
	//compressor = new Compressor(0);
		
		leftMotor1.set(ControlMode.PercentOutput, leftJoy.getY());
		leftMotor2.set(ControlMode.PercentOutput, leftJoy.getY());
		rightMotor1.set(ControlMode.PercentOutput, -1 * rightJoy.getY());
		rightMotor2.set(ControlMode.PercentOutput, -1 * rightJoy.getY());
		leftElevatorMotor.set(ControlMode.PercentOutput, operator.getY());
		rightElevatorMotor.set(ControlMode.PercentOutput, -operator.getY());
		int leftCount = leftEncoder.get();
		int rightCount = rightEncoder.get();
		boolean ejc = false;
		System.out.println("right: " + rightCount);
		System.out.println("left: " + leftCount);
		//rightElevatorMotor.set(ControlMode.PercentOutput, -1*operator.getY());
		//compressor.setClosedLoopControl(true);
		//compressor.start();
		double x = 0;
		double y = 0;
		//NetworkTableEntry xValue = NetworkTableEntry.setDouble(x);
		//NetworkTableEntry yValue = NetworkTableEntry.setDouble(y);
		//in and out for two cylinders using button 2
		
		/*if(leftJoy.getRawButton(3)) {
			Solenoid3.set(false);
		} else {
			Solenoid3.set(true);
		}*/
		/*if(leftJoy.getRawButton(4)) {
			Solenoid4.set(false);
		} else {
			Solenoid4.set(true);
		}*/
		/*if(leftJoy.getRawButton(4)) {
			Solenoid5.set(false);
		} else {
			Solenoid5.set(true);
		}*/
		if(operator.getRawButton(3) == true) {
			scalar.set(ControlMode.PercentOutput, .5);
		}
		else if(operator.getRawButton(2) == true) {
			scalar.set(ControlMode.PercentOutput, -.5);
		}
		else {
			scalar.set(ControlMode.PercentOutput, 0);
		}
		
		if(operator.getRawButton(7) == true) {
				intakeBags.set(ControlMode.PercentOutput, -1);
		}
		else {
			if(operator.getRawButton(8) == true) {
				intakeBags.set(ControlMode.PercentOutput, 1);
			}
			else {
				intakeBags.set(ControlMode.PercentOutput, 0);
			}
		}
		if (rightJoy.getRawButton(12)) {
			anand90Clock();
		}
		//if (rightJoy.getRawButton(10)) {
			//anandStraightSix();
		//}
		if(rightJoy.getRawButton(11)) {
			anand90Counter();
		}
		
		if (operator.getRawButton(9)) {
			if (ejc = false) {
				Solenoid2.set(false);
				ejc = true;
			} else {
				Solenoid2.set(true);
				ejc = false;
			}
		}
		if (operator.getRawButton(8)) {
			if (ejc = true) {
				Solenoid2.set(false);
				ejc = false;
			}else {
				Solenoid2.set(false);
				ejc = false;
			}
		}
		if (rightJoy.getRawButton(2)) {
			if (lowgear == true) {
				
				Solenoid1.set(true);
				lowgear = false;
				Timer.delay(.08);
			} else {
				
				Solenoid1.set(false);
				lowgear = true;
				Timer.delay(.08);
			}
		}
		if (rightJoy.getRawButton(1) == true) {
				Solenoid0.set(true);
				
				Timer.delay(.08);
			}
			else {
				Solenoid0.set(false);
				
				Timer.delay(.08);
			}
		
		if (leftJoy.getRawButton(1)) {
			if(extended == false) {
				Solenoid3.set(true);
				extended = true;
				Timer.delay(.08);
			}
			else {
				Solenoid3.set(false);
				extended = false;
				Timer.delay(.08);
			}
		}
		
		
		
		
																																																																												//while(isOperatorControl() && isEnabled()) {
			
			
		//}
	}
	public void rightTurn() {
		long leftCount = leftEncoder.get();
		long rightCount = rightEncoder.get();
		if(leftCount <= 5500) {
			leftMotor1.set(ControlMode.PercentOutput, -0.35);
			leftMotor2.set(ControlMode.PercentOutput, -0.35);
			rightMotor1.set(ControlMode.PercentOutput, 0);
			rightMotor2.set(ControlMode.PercentOutput, 0);
		}
		else {
			Solenoid3.set(true);
			leftMotor1.set(ControlMode.PercentOutput, -0.2);
			leftMotor2.set(ControlMode.PercentOutput, -0.2);
			rightMotor1.set(ControlMode.PercentOutput, 0.2);
			rightMotor2.set(ControlMode.PercentOutput, 0.2);
		Timer.delay(1);
		leftMotor1.set(ControlMode.PercentOutput, 0);
		leftMotor2.set(ControlMode.PercentOutput, 0);
		rightMotor1.set(ControlMode.PercentOutput, 0);
		rightMotor2.set(ControlMode.PercentOutput, 0);
		

Timer.delay(1);
Solenoid0.set(true);
Counter = 2;		
		}
		
	}
	public void leftTurn() {
		long leftCount = leftEncoder.get();
		long rightCount = rightEncoder.get();
		if(rightCount <= 8400) {
			leftMotor1.set(ControlMode.PercentOutput, 0);
			leftMotor2.set(ControlMode.PercentOutput, 0);
			rightMotor1.set(ControlMode.PercentOutput, 0.35);
			rightMotor2.set(ControlMode.PercentOutput, 0.35);
		}
		else {
			Solenoid3.set(true);
			leftMotor1.set(ControlMode.PercentOutput, -0.2);
			leftMotor2.set(ControlMode.PercentOutput, -0.2);
			rightMotor1.set(ControlMode.PercentOutput, 0.2);
			rightMotor2.set(ControlMode.PercentOutput, 0.2);
		Timer.delay(1);
		leftMotor1.set(ControlMode.PercentOutput, 0);
		leftMotor2.set(ControlMode.PercentOutput, 0);
		rightMotor1.set(ControlMode.PercentOutput, 0);
		rightMotor2.set(ControlMode.PercentOutput, 0);
		Timer.delay(1);
		intakeBags.set(ControlMode.PercentOutput, .7);
		
		Solenoid0.set(true);
		Counter = 3;

			
		}

	}
	public void left45() {
		long leftCount = leftEncoder.get();
		long rightCount = rightEncoder.get();
		if(rightCount <= 12500) {
			leftMotor1.set(ControlMode.PercentOutput, 0);
			leftMotor2.set(ControlMode.PercentOutput, 0);
			rightMotor1.set(ControlMode.PercentOutput, 0.35);
			rightMotor2.set(ControlMode.PercentOutput, 0.35);
		}
		else {
		
		//Timer.delay(1);
		leftMotor1.set(ControlMode.PercentOutput, 0);
		leftMotor2.set(ControlMode.PercentOutput, 0);
		rightMotor1.set(ControlMode.PercentOutput, 0);
		rightMotor2.set(ControlMode.PercentOutput, 0);
		//Timer.delay(1);
		
	//	Solenoid0.set(true);
		Counter = 2;

			
		}

	}
	public void right45() {
		long leftCount = leftEncoder.get();
		long rightCount = rightEncoder.get();
		if(leftCount <= 5500) {
			leftMotor1.set(ControlMode.PercentOutput, -0.35);
			leftMotor2.set(ControlMode.PercentOutput, -0.35);
			rightMotor1.set(ControlMode.PercentOutput, 0);
			rightMotor2.set(ControlMode.PercentOutput, 0);
		}
		else {
			Solenoid3.set(true);
			leftMotor1.set(ControlMode.PercentOutput, -0.2);
			leftMotor2.set(ControlMode.PercentOutput, -0.2);
			rightMotor1.set(ControlMode.PercentOutput, 0.2);
			rightMotor2.set(ControlMode.PercentOutput, 0.2);
		Timer.delay(2.9);
		leftMotor1.set(ControlMode.PercentOutput, 0);
		leftMotor2.set(ControlMode.PercentOutput, 0);
		rightMotor1.set(ControlMode.PercentOutput, 0);
		rightMotor2.set(ControlMode.PercentOutput, 0);
		Timer.delay(1);
		intakeBags.set(ControlMode.PercentOutput, .7);
		Solenoid0.set(true);

		Timer.delay(2);
		leftElevatorMotor.set(ControlMode.PercentOutput, 0);
		Counter = 4;

			
		}

	}
	public void anandStraightNine() {
		leftMotor1.set(ControlMode.PercentOutput, -.3);
		leftMotor2.set(ControlMode.PercentOutput, -.3);
		rightMotor1.set(ControlMode.PercentOutput, .3);
		rightMotor2.set(ControlMode.PercentOutput, .3);
		Timer.delay(5.5);
		leftMotor1.set(ControlMode.PercentOutput, 0);
		leftMotor2.set(ControlMode.PercentOutput, 0);
		rightMotor1.set(ControlMode.PercentOutput, 0);
		rightMotor2.set(ControlMode.PercentOutput, 0);
	}
	
	public void anandStraightSix() {
		leftMotor1.set(ControlMode.PercentOutput, -.475);
		leftMotor2.set(ControlMode.PercentOutput, -.475);
		rightMotor1.set(ControlMode.PercentOutput, .475);
		rightMotor2.set(ControlMode.PercentOutput, .475);
		Timer.delay(4);
		leftMotor1.set(ControlMode.PercentOutput, 0);
		leftMotor2.set(ControlMode.PercentOutput, 0);
		rightMotor1.set(ControlMode.PercentOutput, 0);
		rightMotor2.set(ControlMode.PercentOutput, 0);
	}
	
	public void anandBitForward() {
		leftMotor1.set(ControlMode.PercentOutput, -.3);
		leftMotor2.set(ControlMode.PercentOutput, -.3);
		rightMotor1.set(ControlMode.PercentOutput, .3);
		rightMotor2.set(ControlMode.PercentOutput, .3);
		Timer.delay(.99);
		leftMotor1.set(ControlMode.PercentOutput, 0);
		leftMotor2.set(ControlMode.PercentOutput, 0);
		rightMotor1.set(ControlMode.PercentOutput, 0);
		rightMotor2.set(ControlMode.PercentOutput, 0);
	}
	
	public void anandCrossField() {
		leftMotor1.set(ControlMode.PercentOutput, -.42);
		leftMotor2.set(ControlMode.PercentOutput, -.42);
		rightMotor1.set(ControlMode.PercentOutput, .42);
		rightMotor2.set(ControlMode.PercentOutput, .42);
		Timer.delay(3.7);
		leftMotor1.set(ControlMode.PercentOutput, 0);
		leftMotor2.set(ControlMode.PercentOutput, 0);
		rightMotor1.set(ControlMode.PercentOutput, 0);
		rightMotor2.set(ControlMode.PercentOutput, 0);
	}
	
	public void anandShortSix() {
		leftMotor1.set(ControlMode.PercentOutput, -.3);
		leftMotor2.set(ControlMode.PercentOutput, -.3);
		rightMotor1.set(ControlMode.PercentOutput, .3);
		rightMotor2.set(ControlMode.PercentOutput, .3);
		Timer.delay(3);
		leftMotor1.set(ControlMode.PercentOutput, 0);
		leftMotor2.set(ControlMode.PercentOutput, 0);
		rightMotor1.set(ControlMode.PercentOutput, 0);
		rightMotor2.set(ControlMode.PercentOutput, 0);
	}
	
	public void anandQuickSix() {
		leftMotor1.set(ControlMode.PercentOutput, -.5);
		leftMotor2.set(ControlMode.PercentOutput, -.5);
		rightMotor1.set(ControlMode.PercentOutput, .5);
		rightMotor2.set(ControlMode.PercentOutput, .5);
		Timer.delay(2.75);
		leftMotor1.set(ControlMode.PercentOutput, 0);
		leftMotor2.set(ControlMode.PercentOutput, 0);
		rightMotor1.set(ControlMode.PercentOutput, 0);
		rightMotor2.set(ControlMode.PercentOutput, 0);
	}
	
	public void anandQuickNine() {
		leftMotor1.set(ControlMode.PercentOutput, -.5);
		leftMotor2.set(ControlMode.PercentOutput, -.5);
		rightMotor1.set(ControlMode.PercentOutput, .5);
		rightMotor2.set(ControlMode.PercentOutput, .5);
		Timer.delay(3.3);
		leftMotor1.set(ControlMode.PercentOutput, 0);
		leftMotor2.set(ControlMode.PercentOutput, 0);
		rightMotor1.set(ControlMode.PercentOutput, 0);
		rightMotor2.set(ControlMode.PercentOutput, 0);
	}
	public void anandEjectProcess() {
		Solenoid3.set(true);
		Timer.delay(.8);
		intakeBags.set(ControlMode.PercentOutput, 1);
		Timer.delay(1);
		intakeBags.set(ControlMode.PercentOutput, 0);
	}
	
	public void anandBackUp() {
		leftMotor1.set(ControlMode.PercentOutput, .3);
		leftMotor2.set(ControlMode.PercentOutput, .3);
		rightMotor1.set(ControlMode.PercentOutput, -.299);
		rightMotor2.set(ControlMode.PercentOutput, -.299);
		Timer.delay(1);
		leftMotor1.set(ControlMode.PercentOutput, 0);
		leftMotor2.set(ControlMode.PercentOutput, 0);
		rightMotor1.set(ControlMode.PercentOutput, 0);
		rightMotor2.set(ControlMode.PercentOutput, 0);
	}
	
	public void anandSquareUp() {
		leftMotor1.set(ControlMode.PercentOutput, -.3);
		leftMotor2.set(ControlMode.PercentOutput, -.3);
		rightMotor1.set(ControlMode.PercentOutput, .299);
		rightMotor2.set(ControlMode.PercentOutput, .299);
		Timer.delay(1);
		leftMotor1.set(ControlMode.PercentOutput, 0);
		leftMotor2.set(ControlMode.PercentOutput, 0);
		rightMotor1.set(ControlMode.PercentOutput, 0);
		rightMotor2.set(ControlMode.PercentOutput, 0);
	}
	
	public void anand90Clock() {
		leftMotor1.set(ControlMode.PercentOutput, -.3);
		leftMotor2.set(ControlMode.PercentOutput, -.3);
		rightMotor1.set(ControlMode.PercentOutput, -.299);
		rightMotor2.set(ControlMode.PercentOutput, -.299);
		Timer.delay(1.489);
		leftMotor1.set(ControlMode.PercentOutput, 0);
		leftMotor2.set(ControlMode.PercentOutput, 0);
		rightMotor1.set(ControlMode.PercentOutput, 0);
		rightMotor2.set(ControlMode.PercentOutput, 0);
	}
	public void anand90Counter() {
		leftMotor1.set(ControlMode.PercentOutput, .3);
		leftMotor2.set(ControlMode.PercentOutput, .3);
		rightMotor1.set(ControlMode.PercentOutput, .299);
		rightMotor2.set(ControlMode.PercentOutput, .299);
		Timer.delay(1.489);
		leftMotor1.set(ControlMode.PercentOutput, 0);
		leftMotor2.set(ControlMode.PercentOutput, 0);
		rightMotor1.set(ControlMode.PercentOutput, 0);
		rightMotor2.set(ControlMode.PercentOutput, 0);
	}
	
	public void anandFun() {
		leftMotor1.set(ControlMode.PercentOutput, .299);
		leftMotor2.set(ControlMode.PercentOutput, .299);
		rightMotor1.set(ControlMode.PercentOutput, .3);
		rightMotor2.set(ControlMode.PercentOutput, .3);
		Timer.delay(3.1);
		leftMotor1.set(ControlMode.PercentOutput, 0);
		leftMotor2.set(ControlMode.PercentOutput, 0);
		rightMotor1.set(ControlMode.PercentOutput, 0);
		rightMotor2.set(ControlMode.PercentOutput, 0);
	}
	
	public void shortStraight() {

		double Acceleration = 0.03;
		long leftCount = leftEncoder.get();
		long rightCount = rightEncoder.get();
		long leftChange = leftCount - idealleft;
		long rightChange = rightCount - idealright;
		idealleft = leftEncoder.get();
		idealright = rightEncoder.get();
		leftElevatorMotor.set(ControlMode.PercentOutput, operator.getY());
			if(leftCount <= 17700 && rightCount < 17700) {	
				if (leftChange == 80) {     //Make this change testing thing a method because we use it several times
				}
				else if (leftChange >= 80) {
					leftspeed = leftspeed - Acceleration;
				}
				else if (leftChange <= 80) {
					leftspeed = leftspeed + Acceleration;	
				}
				if (rightChange == 80) {
				}
				else if (rightChange >= 80) {
					rightspeed = rightspeed - Acceleration;
				}
				else if (rightChange <= 80) {
					rightspeed = rightspeed + Acceleration;	
				}
				if (leftspeed >= 0.5) {
					leftspeed = 0.5;
				}
				if (rightspeed >= 0.5) {
					rightspeed = 0.5;
				}
				leftMotor1.set(ControlMode.PercentOutput, -leftspeed);
				leftMotor2.set(ControlMode.PercentOutput, -leftspeed);
				rightMotor1.set(ControlMode.PercentOutput, rightspeed);
				rightMotor2.set(ControlMode.PercentOutput, rightspeed);
				 
			}
			else {
				leftMotor1.set(ControlMode.PercentOutput, 0);
				leftMotor2.set(ControlMode.PercentOutput, 0);
				rightMotor1.set(ControlMode.PercentOutput, 0);
				rightMotor2.set(ControlMode.PercentOutput, 0);
				Timer.delay(.3);
				Counter = 3;
				leftEncoder.reset();
				rightEncoder.reset();
			}
		
		  }	
	public void exshortStraight() {

		double Acceleration = 0.03;
		long leftCount = leftEncoder.get();
		long rightCount = rightEncoder.get();
		long leftChange = leftCount - idealleft;
		long rightChange = rightCount - idealright;
		idealleft = leftEncoder.get();
		idealright = rightEncoder.get();
		leftElevatorMotor.set(ControlMode.PercentOutput, operator.getY());
			if(leftCount <= 200 && rightCount < 200) {	
				if (leftChange == 40) {     //Make this change testing thing a method because we use it several times
				}
				else if (leftChange >= 40) {
					leftspeed = leftspeed - Acceleration;
				}
				else if (leftChange <= 40) {
					leftspeed = leftspeed + Acceleration;	
				}
				if (rightChange == 40) {
				}
				else if (rightChange >= 40) {
					rightspeed = rightspeed - Acceleration;
				}
				else if (rightChange <= 40) {
					rightspeed = rightspeed + Acceleration;	
				}
				if (leftspeed >= 0.5) {
					leftspeed = 0.5;
				}
				if (rightspeed >= 0.5) {
					rightspeed = 0.5;
				}
				leftMotor1.set(ControlMode.PercentOutput, -leftspeed);
				leftMotor2.set(ControlMode.PercentOutput, -leftspeed);
				rightMotor1.set(ControlMode.PercentOutput, rightspeed);
				rightMotor2.set(ControlMode.PercentOutput, rightspeed);
				 
			}
			else {
				leftMotor1.set(ControlMode.PercentOutput, 0);
				leftMotor2.set(ControlMode.PercentOutput, 0);
				rightMotor1.set(ControlMode.PercentOutput, 0);
				rightMotor2.set(ControlMode.PercentOutput, 0);
				Counter = 1;
				leftEncoder.reset();
				rightEncoder.reset();
			}
		
		  }	
	public void driveStraight() {

		double Acceleration = 0.01;
		long leftCount = leftEncoder.get();
		long rightCount = rightEncoder.get();
		long leftChange = leftCount - idealleft;
		long rightChange = rightCount - idealright;
		idealleft = leftEncoder.get();
		idealright = rightEncoder.get();
		leftElevatorMotor.set(ControlMode.PercentOutput, operator.getY());
			if(leftCount <= 15400 && rightCount < 15400) {	
				if (leftChange == 40) {     //Make this change testing thing a method because we use it several times
				}
				else if (leftChange >= 40) {
					leftspeed = leftspeed - Acceleration;
				}
				else if (leftChange <= 40) {
					leftspeed = leftspeed + Acceleration;	
				}
				if (rightChange == 40) {
				}
				else if (rightChange >= 40) {
					rightspeed = rightspeed - Acceleration;
				}
				else if (rightChange <= 40) {
					rightspeed = rightspeed + Acceleration;	
				}
				if (leftspeed >= 0.5) {
					leftspeed = 0.5;
				}
				if (rightspeed >= 0.5) {
					rightspeed = 0.5;
				}
				leftMotor1.set(ControlMode.PercentOutput, -leftspeed);
				leftMotor2.set(ControlMode.PercentOutput, -leftspeed);
				rightMotor1.set(ControlMode.PercentOutput, rightspeed);
				rightMotor2.set(ControlMode.PercentOutput, rightspeed);
				 
			}
			else {
				leftMotor1.set(ControlMode.PercentOutput, 0);
				leftMotor2.set(ControlMode.PercentOutput, 0);
				rightMotor1.set(ControlMode.PercentOutput, 0);
				rightMotor2.set(ControlMode.PercentOutput, 0);
				Counter = 1;
				leftEncoder.reset();
				rightEncoder.reset();
			}
		
		  }
	public void middleStraightE() {
		double Acceleration = 0.01;
		long leftCount = leftEncoder.get();
		long rightCount = rightEncoder.get();
		long leftChange = leftCount - idealleft;
		long rightChange = rightCount - idealright;
		idealleft = leftEncoder.get();
		idealright = rightEncoder.get();

			if(leftCount <= 11720 && rightCount < 11720) {	  //Why not just call driveStraight in here and add arguments to driveStraight if we need it
				if (leftChange == 40) {
				}
				else if (leftChange >= 40) {
					leftspeed = leftspeed - Acceleration;
				}
				else if (leftChange <= 40) {
					leftspeed = leftspeed + Acceleration;	
				}
				if (rightChange == 40) {
				}
				else if (rightChange >= 40) {
					rightspeed = rightspeed - Acceleration;
				}
				else if (rightChange <= 40) {
					rightspeed = rightspeed + Acceleration;	
				}
				if (leftspeed >= 0.5) {
					leftspeed = 0.5;
				}
				if (rightspeed >= 0.5) {
					rightspeed = 0.5;
				}
				leftMotor1.set(ControlMode.PercentOutput, -leftspeed);
				leftMotor2.set(ControlMode.PercentOutput, -leftspeed);
				rightMotor1.set(ControlMode.PercentOutput, rightspeed);
				rightMotor2.set(ControlMode.PercentOutput, rightspeed);
			}
			else {
				//leftElevatorMotor.set(ControlMode.PercentOutput, .2);
				//Timer.delay(1);
				//leftElevatorMotor.set(ControlMode.PercentOutput, 0);
				Solenoid3.set(true);
				Timer.delay(2);
				leftMotor1.set(ControlMode.PercentOutput, 0);
				leftMotor2.set(ControlMode.PercentOutput, 0);
				rightMotor1.set(ControlMode.PercentOutput, 0);
				rightMotor2.set(ControlMode.PercentOutput, 0);
				
				Timer.delay(1);
				Solenoid0.set(true);
				intakeBags.set(ControlMode.PercentOutput, .7);
				Timer.delay(1);
				intakeBags.set(ControlMode.PercentOutput, 0);
				Counter = 1;
				leftEncoder.reset();
				rightEncoder.reset();
			}
		
		  }
	public void middleStraight() {
		double Acceleration = 0.01;
		long leftCount = leftEncoder.get();
		long rightCount = rightEncoder.get();
		long leftChange = leftCount - idealleft;
		long rightChange = rightCount - idealright;
		idealleft = leftEncoder.get();
		idealright = rightEncoder.get();
		leftElevatorMotor.set(ControlMode.PercentOutput, operator.getY());
			if(leftCount <= 11820 && rightCount < 11820) {	
				if (leftChange == 80) {
				}
				else if (leftChange >= 80) {
					leftspeed = leftspeed - Acceleration;
				}
				else if (leftChange <= 80) {
					leftspeed = leftspeed + Acceleration;	
				}
				if (rightChange == 80) {
				}
				else if (rightChange >= 80) {
					rightspeed = rightspeed - Acceleration;
				}
				else if (rightChange <= 80) {
					rightspeed = rightspeed + Acceleration;	
				}
				if (leftspeed >= 0.5) {
					leftspeed = 0.5;
				}
				if (rightspeed >= 0.5) {
					rightspeed = 0.5;
				}
				leftMotor1.set(ControlMode.PercentOutput, -leftspeed);
				leftMotor2.set(ControlMode.PercentOutput, -leftspeed);
				rightMotor1.set(ControlMode.PercentOutput, rightspeed);
				rightMotor2.set(ControlMode.PercentOutput, rightspeed);
			}
			else {
				leftMotor1.set(ControlMode.PercentOutput, 0);
				leftMotor2.set(ControlMode.PercentOutput, 0);
				rightMotor1.set(ControlMode.PercentOutput, 0);
				rightMotor2.set(ControlMode.PercentOutput, 0);
				
				Counter = 1;
				leftEncoder.reset();
				rightEncoder.reset();
			}
		
		  }
	public void middleStraightL() {
		double Acceleration = 0.01;
		long leftCount = leftEncoder.get();
		long rightCount = rightEncoder.get();
		long leftChange = leftCount - idealleft;
		long rightChange = rightCount - idealright;
		idealleft = leftEncoder.get();
		idealright = rightEncoder.get();

			if(leftCount <= 11720 && rightCount < 11720) {	  //Why not just call driveStraight in here and add arguments to driveStraight if we need it
				if (leftChange == 40) {
				}
				else if (leftChange >= 40) {
					leftspeed = leftspeed - Acceleration;
				}
				else if (leftChange <= 40) {
					leftspeed = leftspeed + Acceleration;	
				}
				if (rightChange == 40) {
				}
				else if (rightChange >= 40) {
					rightspeed = rightspeed - Acceleration;
				}
				else if (rightChange <= 40) {
					rightspeed = rightspeed + Acceleration;	
				}
				if (leftspeed >= 0.5) {
					leftspeed = 0.5;
				}
				if (rightspeed >= 0.5) {
					rightspeed = 0.5;
				}
				leftMotor1.set(ControlMode.PercentOutput, -leftspeed);
				leftMotor2.set(ControlMode.PercentOutput, -leftspeed);
				rightMotor1.set(ControlMode.PercentOutput, rightspeed);
				rightMotor2.set(ControlMode.PercentOutput, rightspeed);
			}
			else {
				//leftElevatorMotor.set(ControlMode.PercentOutput, .2);
				//Timer.delay(1);
				//leftElevatorMotor.set(ControlMode.PercentOutput, 0);
				Solenoid3.set(true);
				Timer.delay(2);
				leftMotor1.set(ControlMode.PercentOutput, 0);
				leftMotor2.set(ControlMode.PercentOutput, 0);
				rightMotor1.set(ControlMode.PercentOutput, 0);
				rightMotor2.set(ControlMode.PercentOutput, 0);
				
				Timer.delay(1);
				Solenoid0.set(true);
				intakeBags.set(ControlMode.PercentOutput, .7);
				Timer.delay(1);
				intakeBags.set(ControlMode.PercentOutput, 0);
				Counter = 1;
				leftEncoder.reset();
				rightEncoder.reset();
			}
		
		  }
    //old code; delet this?? zibran is bad and libeal
	public void moveOnYAxis(int speed) { //0 - 255 and moves full robot forwards or backwards
		leftMotor1.set(ControlMode.PercentOutput, -1*speed);
		leftMotor2.set(ControlMode.PercentOutput, -1*speed);
		rightMotor1.set(ControlMode.PercentOutput, speed);
		rightMotor2.set(ControlMode.PercentOutput, speed);
	}
	public void turn90(int degree) {//90 or -90 nothing else works, and turns in a perfect right angle
		if (degree == 90) {
			leftMotor1.set(ControlMode.PercentOutput, 25);
			leftMotor2.set(ControlMode.PercentOutput, 25);
			rightMotor1.set(ControlMode.PercentOutput, 25);
			rightMotor2.set(ControlMode.PercentOutput, 25);
		} else if(degree == -90) {
			leftMotor1.set(ControlMode.PercentOutput, -25);
			leftMotor2.set(ControlMode.PercentOutput, -25);
			rightMotor1.set(ControlMode.PercentOutput, 25);
			rightMotor2.set(ControlMode.PercentOutput, 25);
		}
		/*if (degree2 == 45) {
			leftMotor1.set(ControlMode.PercentOutput, 4);
			leftMotor2.set(ControlMode.PercentOutput, 4);
			rightMotor1.set(ControlMode.PercentOutput, -4);
			rightMotor2.set(ControlMode.PercentOutput, -4);
		} else if(degree2 == -45) {
			leftMotor1.set(ControlMode.PercentOutput, -4);
			leftMotor2.set(ControlMode.PercentOutput, -4);
			rightMotor1.set(ControlMode.PercentOutput, 4);
			rightMotor2.set(ControlMode.PercentOutput, 4);
		}*/
	}
	
	
	@Override
	public void testInit() {
		
		imutable = inst.getTable("IMU Table");
		System.out.println(imutable.getKeys());
		
	}
	/**
	 * This function is called periodically during test mode.
	 */

	public void testPeriodic() {
		/*leftMotor1.set(ControlMode.PercentOutput, .5);
		double encoderDistanceRaw = testEncoder.getRaw();
		boolean encoderDirection = testEncoder.getDirection();
		int count = testEncoder.get();
		//System.out.println(encoderDirection);
		//System.out.println(encoderDistanceRaw);
		//System.out.println(count);
		
		//inches per pulse = .0736310in/pulse
		double rate = testEncoder.getRate();
		//System.out.println(rate * -1);
		*/
		
		System.out.println(imutable.getEntry("yaw").getDouble(0));
		System.out.println(imutable.getEntry("pitch").getDouble(0));
		System.out.println(imutable.getEntry("roll").getDouble(0));	
		
		if (imutable.getEntry("roll").getDouble(0) < 135 && imutable.getEntry("roll").getDouble(0) > 0) {
			while (imutable.getEntry("roll").getDouble(0) < 150 && imutable.getEntry("roll").getDouble(0) > 0) {
				leftMotor1.set(ControlMode.PercentOutput, -.25);
				leftMotor2.set(ControlMode.PercentOutput, -.25);
				rightMotor1.set(ControlMode.PercentOutput, .25);
				rightMotor2.set(ControlMode.PercentOutput, .25);
			}			
			leftMotor1.set(ControlMode.PercentOutput, 0);
			leftMotor2.set(ControlMode.PercentOutput, 0);
			rightMotor1.set(ControlMode.PercentOutput, 0);
			rightMotor2.set(ControlMode.PercentOutput, 0);
		} 

		if (imutable.getEntry("roll").getDouble(0) > -145 && imutable.getEntry("roll").getDouble(0) < 0) {
			while (imutable.getEntry("roll").getDouble(0) > -160 && imutable.getEntry("roll").getDouble(0) < 0) {
				leftMotor1.set(ControlMode.PercentOutput, .25);
				leftMotor2.set(ControlMode.PercentOutput, .25);
				rightMotor1.set(ControlMode.PercentOutput, -.25);
				rightMotor2.set(ControlMode.PercentOutput, -.25);				
			}
			leftMotor1.set(ControlMode.PercentOutput, 0);
			leftMotor2.set(ControlMode.PercentOutput, 0);
			rightMotor1.set(ControlMode.PercentOutput, 0);
			rightMotor2.set(ControlMode.PercentOutput, 0);
		} 
		
	}
}
