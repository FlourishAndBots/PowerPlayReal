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
        robot.LeftElevator.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        waitForStart();
        // Send telemetry message to signify robot waiting;
        telemetry.addData("Status", "Ready to run");    //
        telemetry.update();

        robot.RightElevator.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        robot.RightElevator.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        robot.LeftElevator.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        robot.LeftElevator.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        int leftPos = robot.LeftElevator.getCurrentPosition();
        int rightPos = robot.RightElevator.getCurrentPosition();




        while (opModeIsActive()) {
            //when play is pressed on ds this is what shows up
            telemetry.addData("Mode:", "running");
            telemetry.update();


            //defines values for the gamepad sticks and buttons
            float left = gamepad1.left_stick_y;
            float right = gamepad1.right_stick_y;
            float right2 = gamepad2.left_stick_y / -2;
            float right1 = gamepad2.right_stick_y / -2;

            boolean swing = gamepad1.x;
            boolean swing2 = gamepad1.b;


            //driving
            if (gamepad1.right_stick_y < -0.2 || gamepad1.left_stick_y < -0.2 || gamepad1.left_stick_y > 0.2 || gamepad1.right_stick_y > 0.2) {
                robot.setMotorPowers(left,left,right,right);
            }
            //stop
            else if (gamepad1.right_stick_y < 0.2 || gamepad1.left_stick_y < 0.2 || gamepad1.left_stick_y > -0.2 || gamepad1.right_stick_y > -0.2) {
                robot.setMotorPowers(0,0,0,0);
            }
            //right strafe
            if (gamepad1.right_trigger > 0.1) {
                robot.setMotorPowers(-1,1,1,-1);
            }
            //left strafe
            else if (gamepad1.left_trigger > 0.1) {
                robot.setMotorPowers(1,-1,-1,1);

            }

            //elevator control
            if (gamepad2.left_stick_y < -0.2) {
                robot.RightElevator.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
                robot.LeftElevator.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
                robot.setElevatorPower(1,1);}
            else if (gamepad2.left_stick_y > 0.2) {
                robot.RightElevator.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
                robot.LeftElevator.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
                robot.setElevatorPower(-1,-1);}
            else if (gamepad2.a || gamepad2.b || gamepad2.y) {
                robot.RightElevator.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
                robot.LeftElevator.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
                robot.RightElevator.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
                robot.LeftElevator.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
                if (gamepad2.a) {
                    robot.RightElevator.setTargetPosition(200);
                    robot.LeftElevator.setTargetPosition(200); }
                else if (gamepad2.b) {
                    robot.RightElevator.setTargetPosition(400);
                    robot.LeftElevator.setTargetPosition(400); }
                else if (gamepad2.y) {
                    robot.RightElevator.setTargetPosition(600);
                    robot.LeftElevator.setTargetPosition(600); }
                robot.RightElevator.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                robot.LeftElevator.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                robot.setElevatorPower(1,1);
                while (opModeIsActive() && robot.LeftElevator.isBusy()){
                    telemetry.addData("encoder left:", robot.LeftElevator.getCurrentPosition());
                    telemetry.addData("encoder right:", robot.RightElevator.getCurrentPosition());
                    telemetry.update();
                    idle();
                }
                robot.setElevatorPower(0,0);
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