package postest2;

public class BeltConstantMapper {

	// ///////////////////////////////////////////////////////////////////
	// "MotionStatus" Property Constants
	// ///////////////////////////////////////////////////////////////////

	public static final ConstantConverter BELT_MT_FORWARD = new ConstantConverter(1, "BELT_MT_FORWARD");
	public static final ConstantConverter BELT_MT_BACKWARD = new ConstantConverter(2, "BELT_MT_BACKWARD");
	public static final ConstantConverter BELT_MT_STOPPED = new ConstantConverter(3, "BELT_MT_STOPPED");
	public static final ConstantConverter BELT_MT_EMERGENCY = new ConstantConverter(4, "BELT_MT_EMERGENCY");
	public static final ConstantConverter BELT_MT_MOTOR_FAULT = new ConstantConverter(5, "BELT_MT_MOTOR_FAULT");

	// ///////////////////////////////////////////////////////////////////
	// "adjustItemCount" Method: "Direction" Parameter Constants
	// ///////////////////////////////////////////////////////////////////

	public static final ConstantConverter BELT_AIC_BACKWARD = new ConstantConverter(1, "BELT_AIC_BACKWARD");
	public static final ConstantConverter BELT_AIC_FORWARD = new ConstantConverter(2, "BELT_AIC_FORWARD");

	// ///////////////////////////////////////////////////////////////////
	// "resetItemCount" Method: "Direction" Parameter Constants
	// ///////////////////////////////////////////////////////////////////

	public static final ConstantConverter BELT_RIC_BACKWARD = new ConstantConverter(1, "BELT_RIC_BACKWARD");
	public static final ConstantConverter BELT_RIC_FORWARD = new ConstantConverter(2, "BELT_RIC_FORWARD");

	public static int getConstantNumberFromString(String constant){

		if(constant.equals(BeltConstantMapper.BELT_RIC_BACKWARD.getConstant())) {
			return BeltConstantMapper.BELT_RIC_BACKWARD.getContantNumber();
		}
		
		if(constant.equals(BeltConstantMapper.BELT_RIC_FORWARD.getConstant())) {
			return BeltConstantMapper.BELT_RIC_FORWARD.getContantNumber();
		}

		if(constant.equals(BeltConstantMapper.BELT_AIC_BACKWARD.getConstant())) {
			return BeltConstantMapper.BELT_AIC_BACKWARD.getContantNumber();
		}
		
		if(constant.equals(BeltConstantMapper.BELT_AIC_FORWARD.getConstant())) {
			return BeltConstantMapper.BELT_AIC_FORWARD.getContantNumber();
		}
		
		return Integer.parseInt(constant);
	}
	
	
}
