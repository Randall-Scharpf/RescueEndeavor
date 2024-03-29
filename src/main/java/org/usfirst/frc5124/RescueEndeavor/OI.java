// RobotBuilder Version: 2.0
//
// This file was generated by RobotBuilder. It contains sections of
// code that are automatically generated and assigned by robotbuilder.
// These sections will be updated in the future when you export to
// Java from RobotBuilder. Do not put any code or make any change in
// the blocks indicating autogenerated code or it will be lost on an
// update. Deleting the comments indicating the section will prevent
// it from being updated in the future.


package org.usfirst.frc5124.RescueEndeavor;

import java.util.Map;

import org.usfirst.frc5124.RescueEndeavor.commands.*;

import edu.wpi.cscore.CvSink;
import edu.wpi.cscore.CvSource;
import edu.wpi.cscore.UsbCamera;
import edu.wpi.cscore.VideoMode;
import edu.wpi.cscore.VideoMode.PixelFormat;
import edu.wpi.cscore.VideoSource.ConnectionStrategy;
import edu.wpi.first.cameraserver.CameraServer;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.shuffleboard.BuiltInWidgets;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;


/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
    //// CREATING BUTTONS
    // One type of button is a joystick button which is any button on a joystick.
    // You create one by telling it which joystick it's on and which button
    // number it is.
    // Joystick stick = new Joystick(port);
    // Button button = new JoystickButton(stick, buttonNumber);

    // There are a few additional built in buttons you can use. Additionally,
    // by subclassing Button you can create custom triggers and bind those to
    // commands the same as any other Button.

    //// TRIGGERING COMMANDS WITH BUTTONS
    // Once you have a button, it's trivial to bind it to a button in one of
    // three ways:

    // Start the command when the button is pressed and let it run the command
    // until it is finished as determined by it's isFinished method.
    // button.whenPressed(new ExampleCommand());

    // Run the command while the button is being held down and interrupt it once
    // the button is released.
    // button.whileHeld(new ExampleCommand());

    // Start the command when the button is released  and let it run the command
    // until it is finished as determined by it's isFinished method.
    // button.whenReleased(new ExampleCommand());


    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS

    private final double INTAKE_POWER = 0.45;
    private final double WEAK_ARM = 0.1;
    private final double STRONG_ARM = 1;

    public final XboxController driver;
    public final XboxController operator;

    public final CvSink frontSink;
    public final CvSink backSink;
    public final UsbCamera front;
    public final UsbCamera back;
    public final CvSource imageStream;
    public boolean frontSelected;

    private final Button driverRightBumper;
    private final Button driverLeftBumper;
    private final Button operatorLeftPad;
    private final Button operatorRightPad;
    private final Button operatorLeftBumper;
    private final Button operatorRightBumper;
    private final Button operatorUpPad;
    private final Button operatorDownPad;
    private final Button operatorA;
    private final Button operatorB;
    private final Button operatorX;
    private final Button operatorY;

    public OI() {

        front = CameraServer.getInstance().startAutomaticCapture();
        front.setResolution(640, 480);
        front.setFPS(15);
        front.setPixelFormat(PixelFormat.kMJPEG);
        front.setConnectionStrategy(ConnectionStrategy.kKeepOpen);
        frontSink = CameraServer.getInstance().getVideo(front);

        back = CameraServer.getInstance().startAutomaticCapture();
        back.setResolution(640, 480);
        front.setFPS(15);
        back.setPixelFormat(PixelFormat.kMJPEG);
        back.setConnectionStrategy(ConnectionStrategy.kKeepOpen);
        backSink = CameraServer.getInstance().getVideo(back);

        imageStream = new CvSource("Image Stream", new VideoMode(PixelFormat.kMJPEG, 640, 480, 15));
        frontSelected = true;

        ShuffleboardTab display = Shuffleboard.getTab("Driver Display");
        display.add("Camera View", imageStream)
                .withWidget(BuiltInWidgets.kCameraStream)
                .withSize(8, 6)
                .withPosition(1, 0)
                .withProperties(Map.of(
                    "Show crosshair", true,
                    "Crosshair color", "white",
                    "Show controls", true,
                    "Rotation", "NONE"
                ));

        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTORS



        // SmartDashboard Buttons
        SmartDashboard.putData("SwitchCamera", new SwitchCamera());

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTORS
        
        driver = new XboxController(0);
        operator = new XboxController(3);

        driverLeftBumper = new Button(){
            @Override
            public boolean get() {
                return driver.getBumper(Hand.kLeft);
            }
        };
        driverLeftBumper.whenPressed(new SwitchCamera());

        driverRightBumper = new Button(){
            @Override
            public boolean get() {
                return driver.getBumper(Hand.kRight);
            }
        };
        driverRightBumper.whenPressed(new HatchPushFlaps(true));
        driverRightBumper.whenReleased(new HatchPushFlaps(false));

        operatorLeftPad = new Button(){
            @Override
            public boolean get() {
                return operator.getPOV() == 270;
            }
        };
        operatorLeftPad.whenPressed(new HatchArmPower(WEAK_ARM));
        operatorLeftPad.whenReleased(new HatchArmPower(0));

        operatorRightPad = new Button(){
            @Override
            public boolean get() {
                return operator.getPOV() == 90;
            }
        };
        operatorRightPad.whenPressed(new HatchArmPower(-WEAK_ARM));
        operatorRightPad.whenReleased(new HatchArmPower(0));

        operatorLeftBumper = new Button(){
            @Override
            public boolean get() {
                return operator.getBumper(Hand.kLeft);
            }
        };
        operatorLeftBumper.whenPressed(new HatchArmPower(STRONG_ARM));
        operatorLeftBumper.whenReleased(new HatchArmPower(0));

        operatorRightBumper = new Button(){
            @Override
            public boolean get() {
                return operator.getBumper(Hand.kRight);
            }
        };
        operatorRightBumper.whenPressed(new HatchArmPower(-STRONG_ARM));
        operatorRightBumper.whenReleased(new HatchArmPower(0));

        operatorDownPad = new Button(){
            @Override
            public boolean get() {
                return operator.getPOV() == 180;
            }
        };
        operatorDownPad.whenPressed(new IntakePower(INTAKE_POWER));
        operatorDownPad.whenPressed(new IntakePower(0));

        operatorUpPad = new Button(){
            @Override
            public boolean get() {
                return operator.getPOV() == 0;
            }
        };
        operatorUpPad.whenPressed(new IntakePower(-INTAKE_POWER));
        operatorUpPad.whenPressed(new IntakePower(0));

        operatorA = new Button(){
            @Override
            public boolean get() {
                return operator.getAButton();
            }
        };
        operatorA.whileHeld(new CatapultShoot());

        operatorB = new Button(){
            @Override
            public boolean get() {
                return operator.getBButton();
            }
        };
        operatorB.whenPressed(new HatchCloseClaws());

        operatorX = new Button(){
            @Override
            public boolean get() {
                return operator.getXButton();
            }
        };
        operatorX.toggleWhenPressed(new IntakeDeploy());
        
        operatorY = new Button(){
            @Override
            public boolean get() {
                return operator.getYButton();
            }
        };
        operatorY.whenPressed(new HatchDeploy());
        operatorY.whenReleased(new HatchPushFlaps(false));
    }

    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=FUNCTIONS

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=FUNCTIONS
}

