package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.util.ElapsedTime;

@TeleOp(name="Robot: TeleOp", group="Robot")
public class TeleOpTest extends LinearOpMode{


    RobotHardware robot = new RobotHardware();

    private ElapsedTime runtime = new ElapsedTime();

    @Override
    public void runOpMode() throws InterruptedException {

        robot.init(hardwareMap);
        waitForStart();
        // Send telemetry message to signify robot waiting;
        telemetry.addData("Status", "Ready to run");    //
        telemetry.update();
        while (opModeIsActive()){
            telemetry.addData("Mode:", "running");
            telemetry.update();

            float left = gamepad1.left_stick_y;
            float right = gamepad1.right_stick_y;

            if (gamepad1.right_stick_y < -0.1 ) {
                robot.setMotorPowers(0.5, 0, 0.5, 0);
            }

            if (gamepad1.right_stick_y > 0.1 ) {
                robot.setMotorPowers(-0.5, 0, -0.5, 0);
            }

            if (gamepad1.left_stick_y < -0.1 ) {
                robot.setMotorPowers(0, 0.5, 0, 0.5);
            }

            if (gamepad1.left_stick_y > 0.1 ) {
                robot.setMotorPowers(0, -0.5, 0, -0.5);
            }
            else {
                robot.setMotorPowers(0, 0, 0, 0);
            }


            /*
            if (gamepad1.right_stick_y > 0.1 ) {
                robot.setMotorPowers(0.5, 0, 0.5, 0);
            }

            if(gamepad1.right_stick_y < -0.1){
                robot.setMotorPowers(-0.5, 0, -0.5, 0);
            }

            if(gamepad1.left_stick_y > 0.1){
                robot.setMotorPowers(0, 0.5, 0, 0.5);
            }

            if(gamepad1.left_stick_y < -0.1){
                robot.setMotorPowers(0, -0.5, 0, -0.5);
            }

            else {
                robot.setMotorPowers(0, 0, 0, 0);
                }
*/
            }
    }
}