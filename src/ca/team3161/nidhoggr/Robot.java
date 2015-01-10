
package ca.team3161.nidhoggr;

import java.util.concurrent.TimeUnit;

import ca.team3161.lib.robot.Drivetrain;
import ca.team3161.lib.robot.RepeatingSubsystem;
import ca.team3161.lib.robot.TitanBot;
import ca.team3161.lib.utils.controls.Gamepad.PressType;
import ca.team3161.lib.utils.controls.LogitechDualAction;
import ca.team3161.lib.utils.controls.LogitechDualAction.LogitechAxis;
import ca.team3161.lib.utils.controls.LogitechDualAction.LogitechButton;
import ca.team3161.lib.utils.controls.LogitechDualAction.LogitechControl;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends TitanBot {

	public static final int AUTONOMOUS_PERIOD_SECONDS = 15;	
	private final SpeedController leftDrive = new Drivetrain(new Victor(0));
	private final LogitechDualAction gamepad = new LogitechDualAction(0);
	private final RepeatingSubsystem timedPrinter = new TimedPrinter();

	@Override
	public int getAutonomousPeriodLengthSeconds() {
		return AUTONOMOUS_PERIOD_SECONDS;
	}
	
	private volatile int counter = 0;
	@Override
	public void robotInit() {
		gamepad.setMode(LogitechControl.LEFT_STICK, raw -> -raw);
		gamepad.bind(LogitechButton.RIGHT_TRIGGER, PressType.PRESS, () -> leftDrive.set(0.6));
		gamepad.bind(LogitechButton.LEFT_TRIGGER, PressType.RELEASE, () -> leftDrive.set(0.0));
		SmartDashboard.putString("DB/String 0", "Robot init");
		counter = 0;
		timedPrinter.start();
	}

	@Override
	public void teleopInit() {
		SmartDashboard.putString("DB/String 1", "Teleop init");
		counter = 0;
		gamepad.enableBindings();
	}
	
	@Override
	public void autonomousRoutine() throws Exception {
		gamepad.disableBindings();
		SmartDashboard.putString("DB/String 0", "Auto start");
		leftDrive.set(1.0);
		waitFor(5, TimeUnit.SECONDS);
		leftDrive.set(0);
		SmartDashboard.putString("DB/String 0", "Auto end");
	}

	@Override
	public void teleopRoutine() {
		SmartDashboard.putString("DB/String 2", "Teleop routine " + counter++);
		//leftDrive.set(getLeftY() - getLeftX());
	}
	
	private double getLeftX() {
		return gamepad.getValue(LogitechControl.LEFT_STICK, LogitechAxis.X);
	}
	
	private double getLeftY() {
		return gamepad.getValue(LogitechControl.LEFT_STICK, LogitechAxis.Y);
	}
    
}
