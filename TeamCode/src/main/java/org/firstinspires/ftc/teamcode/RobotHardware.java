//where file is located
package org.firstinspires.ftc.teamcode;

import static org.firstinspires.ftc.robotcontroller.external.samples.RobotHardware.MID_SERVO;

//necessary import statements for motors, servos and etc
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

/**
 * This file works in conjunction with the External Hardware Class sample called: ConceptExternalHardwareClass.java
 * Please read the explanations in that Sample about how to use this class definition.
 *
 * This file defines a Java Class that performs all the setup and configuration for a sample robot's hardware (motors and sensors).
 * It assumes three motors (left_drive, right_drive and arm) and two servos (left_hand and right_hand)
 *
 * This one file/class can be used by ALL of your OpModes without having to cut & paste the code each time.
 *
 * Where possible, the actual hardware objects are "abstracted" (or hidden) so the OpMode code just makes calls into the class,
 * rather than accessing the internal hardware directly. This is why the objects are declared "private".
 *
 * Use Android Studio to Copy this Class, and Paste it into your team's code folder with *exactly the same name*.
 *
 * Or.. In OnBot Java, add a new file named HardwareRobot.java, drawing from this Sample; select Not an OpMode.
 * Also add a new OpMode, drawing from the Sample ConceptExternalHardwareClass.java; select TeleOp.
 *
 */

//class name
public class RobotHardware {

    // motor and servos are defined here
    public DcMotor FrontRightMotor   = null;
    public DcMotor FrontLeftMotor  = null;
    public DcMotor BackRightMotor = null;
    public DcMotor BackLeftMotor = null;
    public DcMotor RightElevator = null;
    public DcMotor LeftElevator = null;
    public Servo Claw = null;
    public Servo Swing = null;

        public final static double CLAW_HOME = 0.5;
        public final static double SWING_HOME = 0.5;

    public final static double MIN_POSITION = 0;
    public final static double MAX_POSITION = 1;

    HardwareMap hwMap = null;
    private ElapsedTime period = new ElapsedTime();


    // Define a constructor that allows the OpMode to pass a reference to itself.
    public RobotHardware () {

    }

    public void init(HardwareMap ahwMap) {
        hwMap = ahwMap;
        // Define and Initialize Motors (note: need to use reference to actual OpMode).
        //motor configs in green - what is shown in the driver station config file
        FrontRightMotor = hwMap.get(DcMotor.class, "FR");
        FrontLeftMotor = hwMap.get(DcMotor.class, "FL");
        BackRightMotor = hwMap.get(DcMotor.class, "BR");
        BackLeftMotor = hwMap.get(DcMotor.class, "BL");
        RightElevator = hwMap.get(DcMotor.class, "rightElevator");
        LeftElevator = hwMap.get(DcMotor.class, "leftElevator");


        // To drive forward, most robots need the motor on one side to be reversed, because the axles point in opposite directions.
        // Pushing the left stick forward MUST make robot go forward. So adjust these two lines based on your first test drive.
        // Note: The settings here assume direct drive on left and right wheels.  Gear Reduction or 90 Deg drives may require direction flips
        //orients wheels
        BackRightMotor.setDirection(DcMotor.Direction.FORWARD);
        BackLeftMotor.setDirection(DcMotor.Direction.REVERSE);
        FrontRightMotor.setDirection(DcMotor.Direction.FORWARD);
        FrontLeftMotor.setDirection(DcMotor.Direction.REVERSE);
        RightElevator.setDirection(DcMotor.Direction.FORWARD);
        LeftElevator.setDirection(DcMotor.Direction.REVERSE);


        //Claw.setPosition(MID_SERVO);
        //Swing.setPosition(MID_SERVO);

        //Claw.setPosition(CLAW_HOME);
        //Swing.setPosition(SWING_HOME);

        //makes sure motors don't turn on
        BackRightMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        BackLeftMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        FrontRightMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        FrontLeftMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);



        // If there are encoders connected, switch to RUN_USING_ENCODER mode for greater accuracy
        // leftDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        // rightDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        //calling methods - setting them to 0
        setMotorPowers(0, 0, 0, 0);
        setAllMotorPowers(0);
        turn(0, 0);
        setElevatorPower(0, 0);
       // setClawPosition(0);

    }

    //methods are created here

        //makes it easier to set all the individual motor powers
        public void setMotorPowers(double FRPower, double FLPower, double BRPower, double BLPower){
            FrontRightMotor.setPower(FRPower);
            FrontLeftMotor.setPower(FLPower);
            BackRightMotor.setPower(BRPower);
            BackLeftMotor.setPower(BLPower);
        }

        //makes it easier to set the individual motor powers of the elevator - probably not needed bc they need to move together // CHANGE THIS TO ONE POWER
        public void setElevatorPower(double RPower, double LPower){
            RightElevator.setPower(RPower);
            LeftElevator.setPower(LPower);

        }



        //makes it easier to set all the motor powers at once
        public void setAllMotorPowers(double allPower) {
            setMotorPowers(allPower, allPower, allPower, allPower);

        }

        //method to turn robot
        public void turn(double LeftPower, double RightPower){
            FrontLeftMotor.setPower(LeftPower);
            BackLeftMotor.setPower(LeftPower);
            FrontRightMotor.setPower(RightPower);
            BackRightMotor.setPower(RightPower);
        }





}





