package postest2;

public class BeltConstantMapper implements IMapWrapper {

	// ///////////////////////////////////////////////////////////////////
	// "MotionStatus" Property Constants
	// ///////////////////////////////////////////////////////////////////
	@BelongingProperty(PropertyNames.getMotionStatus)
	public static final ConstantConverter BELT_MT_FORWARD = new ConstantConverter(1, "BELT_MT_FORWARD");
	@BelongingProperty(PropertyNames.getMotionStatus)
	public static final ConstantConverter BELT_MT_BACKWARD = new ConstantConverter(2, "BELT_MT_BACKWARD");
	@BelongingProperty(PropertyNames.getMotionStatus)
	public static final ConstantConverter BELT_MT_STOPPED = new ConstantConverter(3, "BELT_MT_STOPPED");
	@BelongingProperty(PropertyNames.getMotionStatus)
	public static final ConstantConverter BELT_MT_EMERGENCY = new ConstantConverter(4, "BELT_MT_EMERGENCY");
	@BelongingProperty(PropertyNames.getMotionStatus)
	public static final ConstantConverter BELT_MT_MOTOR_FAULT = new ConstantConverter(5,
			"BELT_MT_MOTOR_FAULT");

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

	public static int getConstantNumberFromString(String constant) {

		if (constant.equals(BeltConstantMapper.BELT_RIC_BACKWARD.getConstant())) {
			return BeltConstantMapper.BELT_RIC_BACKWARD.getContantNumber();
		}

		if (constant.equals(BeltConstantMapper.BELT_RIC_FORWARD.getConstant())) {
			return BeltConstantMapper.BELT_RIC_FORWARD.getContantNumber();
		}

		if (constant.equals(BeltConstantMapper.BELT_AIC_BACKWARD.getConstant())) {
			return BeltConstantMapper.BELT_AIC_BACKWARD.getContantNumber();
		}

		if (constant.equals(BeltConstantMapper.BELT_AIC_FORWARD.getConstant())) {
			return BeltConstantMapper.BELT_AIC_FORWARD.getContantNumber();
		}

		if (constant.equals(BeltConstantMapper.BELT_MT_FORWARD.getConstant())) {
			return BeltConstantMapper.BELT_MT_FORWARD.getContantNumber();
		}

		if (constant.equals(BeltConstantMapper.BELT_MT_BACKWARD.getConstant())) {
			return BeltConstantMapper.BELT_MT_BACKWARD.getContantNumber();
		}

		if (constant.equals(BeltConstantMapper.BELT_MT_STOPPED.getConstant())) {
			return BeltConstantMapper.BELT_MT_STOPPED.getContantNumber();
		}

		if (constant.equals(BeltConstantMapper.BELT_MT_EMERGENCY.getConstant())) {
			return BeltConstantMapper.BELT_MT_EMERGENCY.getContantNumber();
		}

		if (constant.equals(BeltConstantMapper.BELT_MT_MOTOR_FAULT.getConstant())) {
			return BeltConstantMapper.BELT_MT_MOTOR_FAULT.getContantNumber();
		}

		return Integer.parseInt(constant);
	}

	@Override
	public IMapWrapper getTheClass() {
		return this;
	}

}
