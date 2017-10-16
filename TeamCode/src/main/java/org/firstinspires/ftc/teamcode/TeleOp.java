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

        robot.init(hardwareMap);

        telemetry.addData("Start", "TeleOp Ready");
        telemetry.update();

        waitForStart();

        while(opModeIsActive()) {

            // Tank drive
           double left = gamepad1.left_stick_y;
           double right = gamepad1.right_stick_y;
            robot.leftMotor.setPower(-left);
            robot.rightMotor.setPower(-right);

            // Claw
            if(gamepad2.y){
                    clawPosition -= .01 ;
                }else{
                    clawPosition += .01 ;
                }
            robot.claw.setPosition(clawPosition);

            // Lift
            double lift = gamepad2.left_stick_y;
            robot.liftMotor.setPower(-lift);

            // Gripper
            if(gamepad2.b) {
                //Closed
                robot.leftGripper.setPosition(1);
                robot.rightGripper.setPosition(0);
            }else{
                //Open
                robot.leftGripper.setPosition(.8);
                robot.rightGripper.setPosition(.2);
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
