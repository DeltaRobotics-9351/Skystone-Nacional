package org.firstinspires.ftc.team9351.autonomous.nacional;


import com.github.deltarobotics9351.deltadrive.drive.mecanum.IMUDriveMecanum;
import com.github.deltarobotics9351.deltadrive.drive.mecanum.TimeDriveMecanum;
import com.github.deltarobotics9351.deltadrive.hardware.DeltaHardware;
import com.github.deltarobotics9351.deltadrive.parameters.IMUDriveParameters;
import com.github.deltarobotics9351.deltadrive.utils.ChassisType;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.team9351.MotivateTelemetry;
import org.firstinspires.ftc.team9351.hardware.Hardware;

@Disabled
@Autonomous(name="NA-B-Autonomo Foundation Azul", group="Final")
public class AutonomoFoundationAzul_2 extends LinearOpMode {

    private Hardware hdw;
    private TimeDriveMecanum timeDrive; //en este objeto se encuentran todas las funciones para
    //el movimiento de las llantas mecanum con tiempo para
    //mantener el codigo mas organizado y facil de cambiar.

    private IMUDriveMecanum imuDrive;

    private DeltaHardware deltaHardware;

    @Override
    public void runOpMode() {
        hdw = new Hardware(hardwareMap); //creamos el hardware
        hdw.initHardware(false); //lo inicializamos

        deltaHardware = new DeltaHardware(hardwareMap, hdw.wheelFrontLeft, hdw.wheelFrontRight, hdw.wheelBackLeft, hdw.wheelBackRight, ChassisType.mecanum);

        timeDrive = new TimeDriveMecanum(deltaHardware, telemetry); //el objeto necesita el hardware para definir el power
        //a los motores y el telemetry para mandar mensajes.

        imuDrive = new IMUDriveMecanum(deltaHardware, this);

        IMUDriveParameters parameters = new IMUDriveParameters();
        parameters.ROTATE_CORRECTION_POWER = 0.15;
        parameters.ROTATE_MAX_CORRECTION_TIMES = 3;

        imuDrive.initIMU(parameters);

        while(!imuDrive.isIMUCalibrated() && !isStopRequested()){
            telemetry.addData("[/!\\]", "Calibrando el sensor IMU, espera...");
            telemetry.addData("[Status]", imuDrive.getIMUCalibrationStatus());
            telemetry.update();
        }

        // hdw.wheelFrontLeft.setDirection(DcMotor.Direction.REVERSE);

        MotivateTelemetry.doMotivateRed(telemetry);
        telemetry.update();

        //esperamos que el usuario presione <play> en la driver station
        waitForStart();

        agitarse();
        //agitarse();

        timeDrive.forward(1, 0.1); //avanzamos un poco

        imuDrive.rotate(5, 0.5); // giramos en direccion a el centro de la foundation

        subirSliders();

        timeDrive.forward(0.5, 0.8); //avanzamos hacia la foundation

        imuDrive.rotate(-5, 0.5); //nos alineamos a la foundation

        //imuDrive.rotate(20, 0.5);

        timeDrive.forward(0.5, 0.2); //avanzamos un poco mas para agarrarnos a la foundation

        bajarSliders();

        //timeDrive.forward(1, 0.3);

        timeDrive.backwards(0.4, 2.4); //jalamos la foundation

        timeDrive.strafeRight(0.4, 5);

        timeDrive.backwards(0.4, 0.5); //jalamos la foundation


        subirSliders();

        timeDrive.strafeRight(0.4, 4);

        hdw.servoStoneAutonomous.setPosition(0.5);
    }

    public void agitarse(){
        timeDrive.forward(1, 0.2);
        timeDrive.backwards(1, 0.2);
    }

    public void subirSliders(){
        hdw.motorSliders.setPower(1); //subimos los sliders
        sleep(600);
        hdw.motorSliders.setPower(0);
    }

    public void bajarSliders(){
        hdw.motorSliders.setPower(-1); //bajamos los sliders
        sleep(600);
        hdw.motorSliders.setPower(0);
        sleep(500);
    }



}