package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.hardware.ColorSensor;

@com.qualcomm.robotcore.eventloop.opmode.Autonomous(name="Autonomous", group="K9bot")
@Disabled
public class Jewel_Autonomous extends LinearOpMode  {

    private ElapsedTime runtime = new ElapsedTime(ElapsedTime.Resolution.MILLISECONDS);
    K9bot robot;
    static private final boolean BLUE_DESIRED = true;

    public Jewel_Autonomous(K9bot new_robot) {
        robot = new_robot;
    }

    public void ReadJewel(boolean JewelBlueDesired) {
        boolean SensorBlue;

        //set initial positions of JS2 and swing JS1 in between the balls
        robot.JS2.setPosition(.5);
        runtime.reset();
        while(runtime.milliseconds() <= 1700) {
            robot.JS1.setPower(.1);
        }
        robot.JS1.setPower(0);

            // Display color values
            telemetry.addData("Red", robot.colorSensor.red());
            telemetry.addData("Blue", robot.colorSensor.blue());
            telemetry.update();

            if (robot.colorSensor.red() < robot.colorSensor.blue()) {
                SensorBlue = true;
            } else {
                SensorBlue = false;
            }

            telemetry.addData("Jewel is ", (SensorBlue) ? "BLUE" : "RED");
            telemetry.update();

            if ((!SensorBlue && JewelBlueDesired) ||
                    (SensorBlue && !JewelBlueDesired)) {
                robot.JS2.setPosition(0);
            } else {
                robot.JS2.setPosition(1);
            }
            robot.JS2.setPosition(.5);
        }

    public void runOpMode() {
    }
}

