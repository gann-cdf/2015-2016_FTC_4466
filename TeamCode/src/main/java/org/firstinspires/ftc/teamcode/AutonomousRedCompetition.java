package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;

import com.qualcomm.robotcore.hardware.OpticalDistanceSensor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.TouchSensor;



/**
 * Created by alexbulanov on 12/19/16.
 */
    @com.qualcomm.robotcore.eventloop.opmode.Autonomous(name = "Red Auto Comp")
public class AutonomousRedCompetition extends LinearOpMode {


    DcMotor l;
    DcMotor r;
    DcMotor lb;
    DcMotor rb;
    OpticalDistanceSensor eods;
    ColorSensor color_left;
    ColorSensor color_right;
    Servo button_left;
    Servo button_right;
    Servo wall_servo;
    TouchSensor touch;

    @Override
    public void runOpMode() throws InterruptedException {
        l = hardwareMap.dcMotor.get("l");
        r = hardwareMap.dcMotor.get("r");
        rb = hardwareMap.dcMotor.get("rb");
        lb = hardwareMap.dcMotor.get("lb");
        button_left = hardwareMap.servo.get("bl");
        button_right = hardwareMap.servo.get("br");
        eods = hardwareMap.opticalDistanceSensor.get("eods");
        color_right = hardwareMap.colorSensor.get("cr");
        r.setDirection(DcMotor.Direction.REVERSE);
        rb.setDirection(DcMotor.Direction.REVERSE);
        touch = hardwareMap.touchSensor.get("t");
        wall_servo = hardwareMap.servo.get("ws");

        waitForStart();
        while (opModeIsActive()) {
            wall_servo.setPosition(0.39);
            button_right.setPosition(0.1);
            button_left.setPosition(0.9);
            color_right.enableLed(false);
            while (eods.getLightDetected() < 0.03 && opModeIsActive()) {
                drive(0.2);
            }
            if (!opModeIsActive()) break;
            stopDrive();
            drive(0.2);
            sleepOpMode(100);
            stopDrive();
            while (eods.getLightDetected() < 0.03 && opModeIsActive()) {
                setLeftPower(0.2);
                setRightPower(-0.2);
            }
            if (!opModeIsActive()) break;
            while (eods.getLightDetected() > 0.03 && opModeIsActive()) {
            }
            if (!opModeIsActive()) break;
            stopDrive();
            while (!touch.isPressed()) {
                while (eods.getLightDetected() < 0.03 && !touch.isPressed() && opModeIsActive()) {
                    setRightPower(0.14);
                }
                if (!opModeIsActive()) break;
                stopDrive();
                while (eods.getLightDetected() > 0.03 && !touch.isPressed() && opModeIsActive()) {
                    setLeftPower(0.12);
                }
                if (!opModeIsActive()) break;
                stopDrive();
            }
            stopDrive();
            if (!opModeIsActive()) break;
            telemetry.addLine("B/R : " + Integer.toString(color_right.red()) + "/" + Integer.toString(color_right.blue()));
            telemetry.update();
            boolean colorFirstSide = RightRed();
            //insert beacon pushing code here
            drive(-0.12);
            sleepOpMode(600);
            if (!opModeIsActive()) break;
            stopDrive();
            wall_servo.setPosition(0.1);
            sleepOpMode(550);
            if (!opModeIsActive()) break;
            if (!colorFirstSide) {
                button_right.setPosition(0.95);
            } else {
                button_left.setPosition(0.05);
            }
            while (eods.getLightDetected() > 0.03 && opModeIsActive()) {
                setRightPower(0.12);
            }
            if (!opModeIsActive()) break;
            stopDrive();
            drive(0.13);
            sleepOpMode(700);
            if (!opModeIsActive()) break;
            stopDrive();
            //Second Beacon
            drive(-0.2);
            sleepOpMode(400);
            stopDrive();
            if (!opModeIsActive()) break;
            wall_servo.setPosition(0.37);
            button_right.setPosition(0.1);
            button_left.setPosition(0.9);
            setLeftPower(-0.18);
            setRightPower(0.18);
            sleepOpMode(1700);
            if (!opModeIsActive()) break;
            while (eods.getLightDetected() < 0.03 && opModeIsActive()) {
                drive(0.15);
            }
            if (!opModeIsActive()) break;
            stopDrive();
            drive(0.2);
            sleepOpMode(100);
            stopDrive();
            while (eods.getLightDetected() < 0.03 && opModeIsActive()) {
                setLeftPower(0.18);
                setRightPower(-0.18);
            }
            if (!opModeIsActive()) break;
            while (eods.getLightDetected() > 0.03 && opModeIsActive()) {
            }
            if (!opModeIsActive()) break;
            stopDrive();
            while (!touch.isPressed()) {
                while (eods.getLightDetected() < 0.03 && !touch.isPressed() && opModeIsActive()) {
                    setRightPower(0.17);
                }
                if (!opModeIsActive()) break;
                stopDrive();
                while (eods.getLightDetected() > 0.03 && !touch.isPressed() && opModeIsActive()) {
                    setLeftPower(0.12);
                }
                if (!opModeIsActive()) break;
                stopDrive();
            }

            if (!opModeIsActive()) break;
            boolean colorSecondSide = RightRed();
            stopDrive();
            setRightPower(0.17);
            setRightPower(-0.17);
            sleepOpMode(300);
            stopDrive();
            //insert beacon pushing code here
            drive(-0.12);
            sleepOpMode(600);
            if (!opModeIsActive()) break;
            stopDrive();
            wall_servo.setPosition(0.1);
            sleepOpMode(550);
            if (!opModeIsActive()) break;
            if (!colorSecondSide) {
                button_right.setPosition(0.95);
            } else {
                button_left.setPosition(0.05);
            }
            while (eods.getLightDetected() > 0.03 && opModeIsActive()) {
                setRightPower(0.17);
            }
            if (!opModeIsActive()) break;
            stopDrive();
            drive(0.13);
            sleepOpMode(700);
            stopDrive();
            break;
        }
        stopDrive();
        l.close();
        r.close();
        lb.close();
        rb.close();
        button_left.close();
        button_right.close();
        wall_servo.close();
        touch.close();
        eods.close();
        color_right.close();
        stop();
    }


    public void drive(double power) {
        l.setPower(power);
        r.setPower(power);
        lb.setPower(power);
        rb.setPower(power);
    }

    public void stopDrive() {
        drive(0);
    }

    public void setLeftPower(double power) {
        l.setPower(power);
        lb.setPower(power);
    }

    public void setRightPower(double power) {
        r.setPower(power);
        rb.setPower(power);
    }

    public void sleepOpMode(double millTime) throws InterruptedException {
        double time = System.currentTimeMillis();
        while (opModeIsActive() && System.currentTimeMillis() < time + millTime) {
            this.sleep(1);
        }
    }

    public boolean RightRed() throws InterruptedException{
        if (color_right.red() < 200 && color_right.blue() < 200) {
            return color_right.red() > color_right.blue();
        }
        else {
            sleepOpMode(1);
            return RightRed();
        }
    }
}




