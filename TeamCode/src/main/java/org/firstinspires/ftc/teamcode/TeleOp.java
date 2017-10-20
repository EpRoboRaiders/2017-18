package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.Range;

@com.qualcomm.robotcore.eventloop.opmode.TeleOp(name="TeleOp", group="K9bot")
//@Disabled
public class TeleOp extends LinearOpMode {

    K9bot robot = new K9bot();

    @Override
    public void runOpMode() {

        double clawPosition = 1;
        double slow = .75;
        boolean speed = true;

        robot.init(hardwareMap);

        telemetry.addData("Start", "TeleOp Ready");
        telemetry.update();

        waitForStart();

        while(opModeIsActive()) {

            // Tank drive
           double left = gamepad1.left_stick_y;
           double right = gamepad1.right_stick_y;
            if(gamepad1.x) {
                speed = !speed;
            }
            if(gamepad1.right_bumper) {
                robot.leftMotor.setPower(speed ? 1 : slow);
                robot.rightMotor.setPower(speed ? 1 : slow);
            } else if(gamepad1.left_bumper) {
                robot.leftMotor.setPower(speed ? -1 : -slow);
                robot.rightMotor.setPower(speed ? -1 : -slow);
            } else {
                robot.leftMotor.setPower(-left * (speed ? 1 : slow));
                robot.rightMotor.setPower(-right * (speed ? 1 : slow));
            }

            // Claw
            if(gamepad2.y){
                clawPosition += .01;
            }else{
                if(clawPosition <= 0) {
                    clawPosition = 0;
                } else {
                    clawPosition -= .01;
                }
            }
            robot.claw.setPosition(clawPosition);

            // Lift
            double lift = gamepad2.left_stick_y;
            robot.liftMotor.setPower(lift);

            // Gripper
            if(gamepad2.b) {
                //Closed
                robot.leftGripper.setPosition(1);
                robot.rightGripper.setPosition(0);
            }else{
                //Open
                robot.leftGripper.setPosition(.7);
                robot.rightGripper.setPosition(.3);
            }

            telemetry.addData("Left Motor",  "%.2f", left);
            telemetry.addData("Right Motor",  "%.2f", right);
            telemetry.addData("Lift Motor(Glyph)",  "%.2f", lift);
            telemetry.update();

            // Pause for 40 mS each cycle = update 25 times a second.
            sleep(40);
        }
    }
}
