//where file is located
package org.firstinspires.ftc.teamcode;

import static com.qualcomm.robotcore.hardware.Servo.MAX_POSITION;
import static com.qualcomm.robotcore.hardware.Servo.MIN_POSITION;

//necessary import statements
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

//teleop tag to make it a teleop program - stuff in parenthesis is what shows up on driver station
@TeleOp(name="Robot: TeleOp", group="Robot")
public class TeleOpTest extends LinearOpMode {

    //instantiates RobotHardware as the object robot
    RobotHardware robot = new RobotHardware();

    //double clawPosition;
    //defines servos here
    private ElapsedTime runtime = new ElapsedTime();
    private CRServo Claw;
    private CRServo Swing;

    @Override
    public void runOpMode() {

        //calls and initializes hardware map - the other file
        robot.init(hardwareMap);

        //references claw and swing from the hardware map - defined above
        Claw = hardwareMap.get(CRServo.class, "Claw");
        Swing = hardwareMap.get(CRServo.class, "Swing");

        robot.RightElevator.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        waitForStart();
        // Send telemetry message to signify robot waiting;
        telemetry.addData("Status", "Ready to run");    //
        telemetry.update();

        robot.RightElevator.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        robot.RightElevator.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        robot.RightElevator.setTargetPosition(50);

        robot.LeftElevator.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        robot.LeftElevator.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        robot.LeftElevator.setTargetPosition(50);

        int leftPos = robot.LeftElevator.getCurrentPosition();
        int rightPos = robot.RightElevator.getCurrentPosition();

        while (opModeIsActive()) {
            //when play is pressed on ds this is what shows up
            telemetry.addData("Mode:", "running");
            telemetry.update();

            while (robot.LeftElevator.isBusy() || robot.RightElevator.isBusy()){
                robot.LeftElevator.getCurrentPosition();
                robot.RightElevator.getCurrentPosition();

                telemetry.addData("left position", leftPos);
                telemetry.addData("right position", rightPos);
                telemetry.update();
            }
            //defines values for the gamepad sticks and buttons
            float left = gamepad1.left_stick_y;
            float right = gamepad1.right_stick_y;
            float right2 = gamepad2.left_stick_y / -2;
            float right1 = gamepad2.right_stick_y / -2;

            boolean swing = gamepad1.x;
            boolean swing2 = gamepad1.b;


            //driving
            if (gamepad1.right_stick_y < -0.1) {
                robot.setMotorPowers(1, 0, 1, 0);
            }

            if (gamepad1.right_stick_y > 0.1) {
                robot.setMotorPowers(-1, 0, -1, 0);
            }

            if (gamepad1.left_stick_y < -0.1) {
                robot.setMotorPowers(0, 1, 0, 1);
            }

            if (gamepad1.left_stick_y > 0.1) {
                robot.setMotorPowers(0, -1, 0, -1);
            } else {
                robot.setMotorPowers(0, 0, 0, 0);
            }

            //elevator control
            if (gamepad2.left_stick_y > 0.1) {

                robot.setElevatorPower(1, 1);

            }

            if (gamepad2.left_stick_y < -0.1) {
                robot.setElevatorPower(0.2, 0.2);
            } else {
                robot.setElevatorPower(0, 0);
            }

            //claw

            if (gamepad2.right_stick_y > 0.1) {
                Claw.setPower(-1);
            }
            if (gamepad2.right_stick_y < -0.1) {
                Claw.setPower(1);
            }

            //swing
            if (gamepad1.x){
                Swing.setPower(1);
            }
            if (gamepad1.b) {
                Swing.setPower(-1);
            }
        }
    }
}