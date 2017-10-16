package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

public class TeleOpTester extends LinearOpMode {

    K9bot robot = new K9bot();

    @Override
    public void runOpMode() {

        robot.init(hardwareMap);

        telemetry.addData("Start", "TeleOpTester Ready");
        telemetry.update();

        waitForStart();

        while(opModeIsActive()) {

        //driving test
           double left = gamepad1.left_stick_y;
           double right = gamepad1.right_stick_y;
           robot.leftMotor.setPower(-left);
           robot.rightMotor.setPower(-right);

            //lift test
            if(gamepad2.b) {
                robot.liftMotor.setPower(1);
            } else {
                robot.liftMotor.setPower(0);
            }

            //Left and right Gripper
            if(gamepad2.x) {
                robot.leftGripper.setPosition(1);
                robot.rightGripper.setPosition(1);
            } else {
                robot.rightGripper.setPosition(0);
                robot.rightGripper.setPosition(0);
            }

            //Relic
            if(gamepad2.a) {
                robot.relicMotor.setPower(1);
            } else {
                robot.relicMotor.setPower(0);
            }

            //JS1 & JS2
            if(gamepad2.right_trigger < .5) {
                robot.JS1.setPower(1);
                robot.JS2.setPosition(1);
            } else {
                robot.JS1.setPower(0);
                robot.JS2.setPosition(0);
            }

            // Claw
            double clawPosition = 1;
            if(gamepad2.y){
                clawPosition -= .01 ;
            }else{
                clawPosition += .01 ;
            }
            robot.claw.setPosition(clawPosition);


        }
    }
}

