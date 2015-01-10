package ca.team3161.nidhoggr;

import java.util.concurrent.TimeUnit;

import ca.team3161.lib.robot.RepeatingSubsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class TimedPrinter extends RepeatingSubsystem {
	private int count = 0;
	public TimedPrinter() {
		super(2, TimeUnit.SECONDS);
	}
	
	@Override
	protected void defineResources() {
	}

	@Override
	protected void task() throws Exception {
		SmartDashboard.putString("DB/String 9", "Count: " + ++count);
	}
	
}
