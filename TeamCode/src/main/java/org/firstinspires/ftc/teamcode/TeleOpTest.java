package org.firstinspires.ftc.teamcode;

import static com.qualcomm.robotcore.hardware.Servo.MAX_POSITION;
import static com.qualcomm.robotcore.hardware.Servo.MIN_POSITION;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

@TeleOp(name="Robot: TeleOp", group="Robot")
public class TeleOpTest extends LinearOpMode {


    RobotHardware robot = new RobotHardware();

    //double clawPosition;
    private ElapsedTime runtime = new ElapsedTime();
    private CRServo Claw;
    private CRServo Swing;

    @Override
    public void runOpMode() {

        robot.init(hardwareMap);
        Claw = hardwareMap.get(CRServo.class, "Claw");
        Swing = hardwareMap.get(CRServo.class, "Swing");
        waitForStart();
        // Send telemetry message to signify robot waiting;
        telemetry.addData("Status", "Ready to run");    //
        telemetry.update();


        while (opModeIsActive()) {
            telemetry.addData("Mode:", "running");
            telemetry.update();

            float left = gamepad1.left_stick_y;
            float right = gamepad1.right_stick_y;
            float right2 = gamepad2.left_stick_y / -2;
            float right1 = gamepad2.right_stick_y / -2;

            boolean swing = gamepad1.x;
            boolean swing2 = gamepad1.b;



            if (gamepad1.right_stick_y < -0.1) {
                robot.setMotorPowers(0.7, 0, 0.7, 0);
            }

            if (gamepad1.right_stick_y > 0.1) {
                robot.setMotorPowers(-0.7, 0, -0.7, 0);
            }

            if (gamepad1.left_stick_y < -0.1) {
                robot.setMotorPowers(0, 0.7, 0, 0.7);
            }

            if (gamepad1.left_stick_y > 0.1) {
                robot.setMotorPowers(0, -0.7, 0, -0.7);
            } else {
                robot.setMotorPowers(0, 0, 0, 0);
            }


            if (gamepad2.left_stick_y < -0.1) {
                robot.setElevatorPower(1.0, 1.0);
            }

            if (gamepad2.left_stick_y > 0.1) {
                robot.setElevatorPower(-0.2, -0.2);
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