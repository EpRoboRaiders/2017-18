package org.firstinspires.ftc.teamcode;

import android.graphics.Color;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.util.ElapsedTime;

@com.qualcomm.robotcore.eventloop.opmode.Autonomous(name="Jewel Autonomous", group="K9bot")
@Disabled
public class Jewel_Autonomous extends LinearOpMode
{

    private ElapsedTime runtime = new ElapsedTime(ElapsedTime.Resolution.MILLISECONDS);
    K9bot robot;

    public Jewel_Autonomous(K9bot new_robot) {
        robot = new_robot;
    }

    public void ReadJewel(boolean JewelBlueDesired)
    {
        {
            boolean SensorBlue;

            robot.colorSensor.enableLed(true);

            //set initial positions of JS2 and swing JS1 in between the balls
            robot.JSY.setPosition(0);
            robot.JSX.setPosition(.5);
            sleep(1000);

/*            if(robot.colorSensor.blue() > robot.colorSensor.red())
            {
                SensorBlue = true;
            }
            else
            {
                SensorBlue = false;
            }

            telemetry.addData("Jewel is ", (SensorBlue) ? "BLUE" : "RED");
            telemetry.update();

            if((!SensorBlue && JewelBlueDesired) || (SensorBlue && !JewelBlueDesired))
            {
                robot.JSX.setPosition(0);
            }
            else
            {
                robot.JSX.setPosition(1);
            }
            sleep(1000);
*/            robot.JSY.setPosition(.6);
            robot.JSX.setPosition(.5);

        }
    }

    public void runOpMode()
    {
    }
}

