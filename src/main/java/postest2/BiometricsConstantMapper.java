package postest2;

public class BiometricsConstantMapper implements IMapWrapper {

	// ///////////////////////////////////////////////////////////////////
	// "SensorColor" Property Constants
	// ///////////////////////////////////////////////////////////////////
	@BelongingProperty(PropertyNames.getSensorColor)
	public static final ConstantConverter BIO_SC_MONO = new ConstantConverter(1, "BIO_SC_MONO");
	@BelongingProperty(PropertyNames.getSensorColor)
	public static final ConstantConverter BIO_SC_GRAYSCALE = new ConstantConverter(2, "BIO_SC_GRAYSCALE");
	@BelongingProperty(PropertyNames.getSensorColor)
	public static final ConstantConverter BIO_SC_16 = new ConstantConverter(3, "BIO_SC_16");
	@BelongingProperty(PropertyNames.getSensorColor)
	public static final ConstantConverter BIO_SC_256 = new ConstantConverter(4, "BIO_SC_256");
	@BelongingProperty(PropertyNames.getSensorColor)
	public static final ConstantConverter BIO_SC_FULL = new ConstantConverter(5, "BIO_SC_FULL");

	// ///////////////////////////////////////////////////////////////////
	// "CapSensorColor" Property Constants
	// ///////////////////////////////////////////////////////////////////
	@BelongingProperty(PropertyNames.getCapSensorColor)
	public static final ConstantConverter BIO_CSC_MONO = new ConstantConverter(0x00000001, "BIO_CSC_MONO");
	@BelongingProperty(PropertyNames.getCapSensorColor)
	public static final ConstantConverter BIO_CSC_GRAYSCALE = new ConstantConverter(0x00000002,
			"BIO_CSC_GRAYSCALE");
	@BelongingProperty(PropertyNames.getCapSensorColor)
	public static final ConstantConverter BIO_CSC_16 = new ConstantConverter(0x00000004, "BIO_CSC_16");
	@BelongingProperty(PropertyNames.getCapSensorColor)
	public static final ConstantConverter BIO_CSC_256 = new ConstantConverter(0x00000008, "BIO_CSC_256");
	@BelongingProperty(PropertyNames.getCapSensorColor)
	public static final ConstantConverter BIO_CSC_FULL = new ConstantConverter(0x00000010, "BIO_CSC_FULL");

	// ///////////////////////////////////////////////////////////////////
	// "CapSensorOrientation" Property Constants
	// ///////////////////////////////////////////////////////////////////
	@BelongingProperty(PropertyNames.getCapSensorOrientation)
	public static final ConstantConverter BIO_CSO_NORMAL = new ConstantConverter(0x00000001, "BIO_CSO_NORMAL");
	@BelongingProperty(PropertyNames.getCapSensorOrientation)
	public static final ConstantConverter BIO_CSO_RIGHT = new ConstantConverter(0x00000002, "BIO_CSO_RIGHT");
	@BelongingProperty(PropertyNames.getCapSensorOrientation)
	public static final ConstantConverter BIO_CSO_INVERTED = new ConstantConverter(0x00000004,
			"BIO_CSO_INVERTED");
	@BelongingProperty(PropertyNames.getCapSensorOrientation)
	public static final ConstantConverter BIO_CSO_LEFT = new ConstantConverter(0x00000008, "BIO_CSO_LEFT");

	// ///////////////////////////////////////////////////////////////////
	// "SensorOrientation" Property Constants
	// ///////////////////////////////////////////////////////////////////
	@BelongingProperty(PropertyNames.getSensorOrientation)
	public static final ConstantConverter BIO_SO_NORMAL = new ConstantConverter(1, "BIO_SO_NORMAL");
	@BelongingProperty(PropertyNames.getSensorOrientation)
	public static final ConstantConverter BIO_SO_RIGHT = new ConstantConverter(2, "BIO_SO_RIGHT");
	@BelongingProperty(PropertyNames.getSensorOrientation)
	public static final ConstantConverter BIO_SO_INVERTED = new ConstantConverter(3, "BIO_SO_INVERTED");
	@BelongingProperty(PropertyNames.getSensorOrientation)
	public static final ConstantConverter BIO_SO_LEFT = new ConstantConverter(4, "BIO_SO_LEFT");

	// ///////////////////////////////////////////////////////////////////
	// "CapSensorType" Property Constants
	// ///////////////////////////////////////////////////////////////////
	@BelongingProperty(PropertyNames.getCapSensorType)
	public static final ConstantConverter BIO_CST_FACIAL_FEATURES = new ConstantConverter(1,
			"BIO_CST_FACIAL_FEATURES");
	@BelongingProperty(PropertyNames.getCapSensorType)
	public static final ConstantConverter BIO_CST_VOICE = new ConstantConverter(2, "BIO_CST_VOICE");
	@BelongingProperty(PropertyNames.getSensorType)
	public static final ConstantConverter BIO_CST_FINGERPRINT = new ConstantConverter(3,
			"BIO_CST_FINGERPRINT");
	@BelongingProperty(PropertyNames.getCapSensorType)
	public static final ConstantConverter BIO_CST_IRIS = new ConstantConverter(4, "BIO_CST_IRIS");
	@BelongingProperty(PropertyNames.getCapSensorType)
	public static final ConstantConverter BIO_CST_RETINA = new ConstantConverter(5, "BIO_CST_RETINA");
	@BelongingProperty(PropertyNames.getCapSensorType)
	public static final ConstantConverter BIO_CST_HAND_GEOMETRY = new ConstantConverter(6,
			"BIO_CST_HAND_GEOMETRY");
	@BelongingProperty(PropertyNames.getCapSensorType)
	public static final ConstantConverter BIO_CST_SIGNATURE_DYNAMICS = new ConstantConverter(7,
			"BIO_CST_SIGNATURE_DYNAMICS");
	@BelongingProperty(PropertyNames.getCapSensorType)
	public static final ConstantConverter BIO_CST_KEYSTROKE_DYNAMICS = new ConstantConverter(8,
			"BIO_CST_KEYSTROKE_DYNAMICS");
	@BelongingProperty(PropertyNames.getCapSensorType)
	public static final ConstantConverter BIO_CST_LIP_MOVEMENT = new ConstantConverter(9,
			"BIO_CST_LIP_MOVEMENT");
	@BelongingProperty(PropertyNames.getCapSensorType)
	public static final ConstantConverter BIO_CST_THERMAL_FACE_IMAGE = new ConstantConverter(10,
			"BIO_CST_THERMAL_FACE_IMAGE");
	@BelongingProperty(PropertyNames.getCapSensorType)
	public static final ConstantConverter BIO_CST_THERMAL_HAND_IMAGE = new ConstantConverter(11,
			"BIO_CST_THERMAL_HAND_IMAGE");
	@BelongingProperty(PropertyNames.getCapSensorType)
	public static final ConstantConverter BIO_CST_GAIT = new ConstantConverter(12, "BIO_CST_GAIT");
	@BelongingProperty(PropertyNames.getCapSensorType)
	public static final ConstantConverter BIO_CST_PASSWORD = new ConstantConverter(13, "BIO_CST_PASSWORD");

	// ///////////////////////////////////////////////////////////////////
	// "SensorType" Property Constants
	// ///////////////////////////////////////////////////////////////////
	@BelongingProperty(PropertyNames.getSensorType)
	public static final ConstantConverter BIO_ST_FACIAL_FEATURES = new ConstantConverter(1,
			"BIO_ST_FACIAL_FEATURES");
	@BelongingProperty(PropertyNames.getSensorType)
	public static final ConstantConverter BIO_ST_VOICE = new ConstantConverter(2, "BIO_ST_VOICE");
	@BelongingProperty(PropertyNames.getSensorType)
	public static final ConstantConverter BIO_ST_FINGERPRINT = new ConstantConverter(3, "BIO_ST_FINGERPRINT");
	@BelongingProperty(PropertyNames.getSensorType)
	public static final ConstantConverter BIO_ST_IRIS = new ConstantConverter(4, "BIO_ST_IRIS");
	@BelongingProperty(PropertyNames.getSensorType)
	public static final ConstantConverter BIO_ST_RETINA = new ConstantConverter(5, "BIO_ST_RETINA");
	@BelongingProperty(PropertyNames.getSensorType)
	public static final ConstantConverter BIO_ST_HAND_GEOMETRY = new ConstantConverter(6,
			"BIO_ST_HAND_GEOMETRY");
	@BelongingProperty(PropertyNames.getSensorType)
	public static final ConstantConverter BIO_ST_SIGNATURE_DYNAMICS = new ConstantConverter(7,
			"BIO_ST_SIGNATURE_DYNAMICS");
	@BelongingProperty(PropertyNames.getSensorType)
	public static final ConstantConverter BIO_ST_KEYSTROKE_DYNAMICS = new ConstantConverter(8,
			"BIO_ST_KEYSTROKE_DYNAMICS");
	@BelongingProperty(PropertyNames.getSensorType)
	public static final ConstantConverter BIO_ST_LIP_MOVEMENT = new ConstantConverter(9,
			"BIO_ST_LIP_MOVEMENT");
	@BelongingProperty(PropertyNames.getSensorType)
	public static final ConstantConverter BIO_ST_THERMAL_FACE_IMAGE = new ConstantConverter(10,
			"BIO_ST_THERMAL_FACE_IMAGE");
	@BelongingProperty(PropertyNames.getSensorType)
	public static final ConstantConverter BIO_ST_THERMAL_HAND_IMAGE = new ConstantConverter(11,
			"BIO_ST_THERMAL_HAND_IMAGE");
	@BelongingProperty(PropertyNames.getSensorType)
	public static final ConstantConverter BIO_ST_GAIT = new ConstantConverter(12, "BIO_ST_GAIT");
	@BelongingProperty(PropertyNames.getSensorType)
	public static final ConstantConverter BIO_ST_PASSWORD = new ConstantConverter(13, "BIO_ST_PASSWORD");

	public static int getConstantNumberFromString(String constant) {

		if (constant.equals(BiometricsConstantMapper.BIO_SC_MONO.getConstant())) {
			return BiometricsConstantMapper.BIO_SC_MONO.getContantNumber();
		}

		if (constant.equals(BiometricsConstantMapper.BIO_SC_GRAYSCALE.getConstant())) {
			return BiometricsConstantMapper.BIO_SC_GRAYSCALE.getContantNumber();
		}

		if (constant.equals(BiometricsConstantMapper.BIO_SC_16.getConstant())) {
			return BiometricsConstantMapper.BIO_SC_16.getContantNumber();
		}

		if (constant.equals(BiometricsConstantMapper.BIO_SC_256.getConstant())) {
			return BiometricsConstantMapper.BIO_SC_256.getContantNumber();
		}

		if (constant.equals(BiometricsConstantMapper.BIO_SC_FULL.getConstant())) {
			return BiometricsConstantMapper.BIO_SC_FULL.getContantNumber();
		}

		if (constant.equals(BiometricsConstantMapper.BIO_CSO_NORMAL.getConstant())) {
			return BiometricsConstantMapper.BIO_CSO_NORMAL.getContantNumber();
		}

		if (constant.equals(BiometricsConstantMapper.BIO_CSO_RIGHT.getConstant())) {
			return BiometricsConstantMapper.BIO_CSO_RIGHT.getContantNumber();
		}

		if (constant.equals(BiometricsConstantMapper.BIO_CSO_INVERTED.getConstant())) {
			return BiometricsConstantMapper.BIO_CSO_INVERTED.getContantNumber();
		}

		if (constant.equals(BiometricsConstantMapper.BIO_CSO_LEFT.getConstant())) {
			return BiometricsConstantMapper.BIO_CSO_LEFT.getContantNumber();
		}

		if (constant.equals(BiometricsConstantMapper.BIO_CSC_MONO.getConstant())) {
			return BiometricsConstantMapper.BIO_CSC_MONO.getContantNumber();
		}

		if (constant.equals(BiometricsConstantMapper.BIO_CSC_GRAYSCALE.getConstant())) {
			return BiometricsConstantMapper.BIO_CSC_GRAYSCALE.getContantNumber();
		}

		if (constant.equals(BiometricsConstantMapper.BIO_CSC_16.getConstant())) {
			return BiometricsConstantMapper.BIO_CSC_16.getContantNumber();
		}

		if (constant.equals(BiometricsConstantMapper.BIO_CSC_256.getConstant())) {
			return BiometricsConstantMapper.BIO_CSC_256.getContantNumber();
		}

		if (constant.equals(BiometricsConstantMapper.BIO_CSC_FULL.getConstant())) {
			return BiometricsConstantMapper.BIO_CSC_FULL.getContantNumber();
		}

		if (constant.equals(BiometricsConstantMapper.BIO_SO_INVERTED.getConstant())) {
			return BiometricsConstantMapper.BIO_SO_INVERTED.getContantNumber();
		}

		if (constant.equals(BiometricsConstantMapper.BIO_SO_LEFT.getConstant())) {
			return BiometricsConstantMapper.BIO_SO_LEFT.getContantNumber();
		}

		if (constant.equals(BiometricsConstantMapper.BIO_SO_NORMAL.getConstant())) {
			return BiometricsConstantMapper.BIO_SO_NORMAL.getContantNumber();
		}

		if (constant.equals(BiometricsConstantMapper.BIO_SO_RIGHT.getConstant())) {
			return BiometricsConstantMapper.BIO_SO_RIGHT.getContantNumber();
		}

		if (constant.equals(BiometricsConstantMapper.BIO_ST_FACIAL_FEATURES.getConstant())) {
			return BiometricsConstantMapper.BIO_ST_FACIAL_FEATURES.getContantNumber();
		}

		if (constant.equals(BiometricsConstantMapper.BIO_ST_FINGERPRINT.getConstant())) {
			return BiometricsConstantMapper.BIO_ST_FINGERPRINT.getContantNumber();
		}

		if (constant.equals(BiometricsConstantMapper.BIO_ST_GAIT.getConstant())) {
			return BiometricsConstantMapper.BIO_ST_GAIT.getContantNumber();
		}

		if (constant.equals(BiometricsConstantMapper.BIO_ST_HAND_GEOMETRY.getConstant())) {
			return BiometricsConstantMapper.BIO_ST_HAND_GEOMETRY.getContantNumber();
		}

		if (constant.equals(BiometricsConstantMapper.BIO_ST_IRIS.getConstant())) {
			return BiometricsConstantMapper.BIO_ST_IRIS.getContantNumber();
		}

		if (constant.equals(BiometricsConstantMapper.BIO_ST_KEYSTROKE_DYNAMICS.getConstant())) {
			return BiometricsConstantMapper.BIO_ST_KEYSTROKE_DYNAMICS.getContantNumber();
		}

		if (constant.equals(BiometricsConstantMapper.BIO_ST_LIP_MOVEMENT.getConstant())) {
			return BiometricsConstantMapper.BIO_ST_LIP_MOVEMENT.getContantNumber();
		}

		if (constant.equals(BiometricsConstantMapper.BIO_ST_PASSWORD.getConstant())) {
			return BiometricsConstantMapper.BIO_ST_PASSWORD.getContantNumber();
		}

		if (constant.equals(BiometricsConstantMapper.BIO_ST_RETINA.getConstant())) {
			return BiometricsConstantMapper.BIO_ST_RETINA.getContantNumber();
		}

		if (constant.equals(BiometricsConstantMapper.BIO_ST_SIGNATURE_DYNAMICS.getConstant())) {
			return BiometricsConstantMapper.BIO_ST_SIGNATURE_DYNAMICS.getContantNumber();
		}

		if (constant.equals(BiometricsConstantMapper.BIO_ST_THERMAL_FACE_IMAGE.getConstant())) {
			return BiometricsConstantMapper.BIO_ST_THERMAL_FACE_IMAGE.getContantNumber();
		}

		if (constant.equals(BiometricsConstantMapper.BIO_ST_THERMAL_HAND_IMAGE.getConstant())) {
			return BiometricsConstantMapper.BIO_ST_THERMAL_HAND_IMAGE.getContantNumber();
		}

		if (constant.equals(BiometricsConstantMapper.BIO_ST_VOICE.getConstant())) {
			return BiometricsConstantMapper.BIO_ST_VOICE.getContantNumber();
		}

		if (constant.equals(BiometricsConstantMapper.BIO_CST_FACIAL_FEATURES.getConstant())) {
			return BiometricsConstantMapper.BIO_CST_FACIAL_FEATURES.getContantNumber();
		}

		if (constant.equals(BiometricsConstantMapper.BIO_CST_FINGERPRINT.getConstant())) {
			return BiometricsConstantMapper.BIO_CST_FINGERPRINT.getContantNumber();
		}

		if (constant.equals(BiometricsConstantMapper.BIO_CST_GAIT.getConstant())) {
			return BiometricsConstantMapper.BIO_CST_GAIT.getContantNumber();
		}

		if (constant.equals(BiometricsConstantMapper.BIO_CST_HAND_GEOMETRY.getConstant())) {
			return BiometricsConstantMapper.BIO_CST_HAND_GEOMETRY.getContantNumber();
		}

		if (constant.equals(BiometricsConstantMapper.BIO_CST_IRIS.getConstant())) {
			return BiometricsConstantMapper.BIO_CST_IRIS.getContantNumber();
		}

		if (constant.equals(BiometricsConstantMapper.BIO_CST_KEYSTROKE_DYNAMICS.getConstant())) {
			return BiometricsConstantMapper.BIO_CST_KEYSTROKE_DYNAMICS.getContantNumber();
		}

		if (constant.equals(BiometricsConstantMapper.BIO_CST_LIP_MOVEMENT.getConstant())) {
			return BiometricsConstantMapper.BIO_CST_LIP_MOVEMENT.getContantNumber();
		}

		if (constant.equals(BiometricsConstantMapper.BIO_CST_PASSWORD.getConstant())) {
			return BiometricsConstantMapper.BIO_CST_PASSWORD.getContantNumber();
		}

		if (constant.equals(BiometricsConstantMapper.BIO_CST_RETINA.getConstant())) {
			return BiometricsConstantMapper.BIO_CST_RETINA.getContantNumber();
		}

		if (constant.equals(BiometricsConstantMapper.BIO_CST_SIGNATURE_DYNAMICS.getConstant())) {
			return BiometricsConstantMapper.BIO_CST_SIGNATURE_DYNAMICS.getContantNumber();
		}

		if (constant.equals(BiometricsConstantMapper.BIO_CST_THERMAL_FACE_IMAGE.getConstant())) {
			return BiometricsConstantMapper.BIO_CST_THERMAL_FACE_IMAGE.getContantNumber();
		}

		if (constant.equals(BiometricsConstantMapper.BIO_CST_THERMAL_HAND_IMAGE.getConstant())) {
			return BiometricsConstantMapper.BIO_CST_THERMAL_HAND_IMAGE.getContantNumber();
		}

		if (constant.equals(BiometricsConstantMapper.BIO_CST_VOICE.getConstant())) {
			return BiometricsConstantMapper.BIO_CST_VOICE.getContantNumber();
		}

		return Integer.parseInt(constant);
	}

	@Override
	public IMapWrapper getTheClass() {
		return this;
	}

}
