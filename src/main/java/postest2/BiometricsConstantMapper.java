package postest2;

public class BiometricsConstantMapper {

	// ///////////////////////////////////////////////////////////////////
	// "SensorOrientation" Property Constants
	// ///////////////////////////////////////////////////////////////////

	public static final ConstantConverter BIO_SO_NORMAL = new ConstantConverter(1, "BIO_SO_NORMAL");
	public static final ConstantConverter BIO_SO_RIGHT = new ConstantConverter(2, "BIO_SO_RIGHT");
	public static final ConstantConverter BIO_SO_INVERTED = new ConstantConverter(3, "BIO_SO_INVERTED");
	public static final ConstantConverter BIO_SO_LEFT = new ConstantConverter(4, "BIO_SO_LEFT");

	// ///////////////////////////////////////////////////////////////////
	// "SensorType" Property Constants
	// ///////////////////////////////////////////////////////////////////

	public static final ConstantConverter BIO_ST_FACIAL_FEATURES = new ConstantConverter(1,
			"BIO_ST_FACIAL_FEATURES");
	public static final ConstantConverter BIO_ST_VOICE = new ConstantConverter(2, "BIO_ST_VOICE");
	public static final ConstantConverter BIO_ST_FINGERPRINT = new ConstantConverter(3,
			"BIO_ST_FINGERPRINT");
	public static final ConstantConverter BIO_ST_IRIS = new ConstantConverter(4, "BIO_ST_IRIS");
	public static final ConstantConverter BIO_ST_RETINA = new ConstantConverter(5, "BIO_ST_RETINA");
	public static final ConstantConverter BIO_ST_HAND_GEOMETRY = new ConstantConverter(6,
			"BIO_ST_HAND_GEOMETRY");
	public static final ConstantConverter BIO_ST_SIGNATURE_DYNAMICS = new ConstantConverter(7,
			"BIO_ST_SIGNATURE_DYNAMICS");
	public static final ConstantConverter BIO_ST_KEYSTROKE_DYNAMICS = new ConstantConverter(8,
			"BIO_ST_KEYSTROKE_DYNAMICS");
	public static final ConstantConverter BIO_ST_LIP_MOVEMENT = new ConstantConverter(9,
			"BIO_ST_LIP_MOVEMENT");
	public static final ConstantConverter BIO_ST_THERMAL_FACE_IMAGE = new ConstantConverter(10,
			"BIO_ST_THERMAL_FACE_IMAGE");
	public static final ConstantConverter BIO_ST_THERMAL_HAND_IMAGE = new ConstantConverter(11,
			"BIO_ST_THERMAL_HAND_IMAGE");
	public static final ConstantConverter BIO_ST_GAIT = new ConstantConverter(12, "BIO_ST_GAIT");
	public static final ConstantConverter BIO_ST_PASSWORD = new ConstantConverter(13, "BIO_ST_PASSWORD");

	
	public static int getConstantNumberFromString(String constant){

		if(constant.equals(BiometricsConstantMapper.BIO_SO_INVERTED.getConstant())) {
			return BiometricsConstantMapper.BIO_SO_INVERTED.getContantNumber();
		}
		
		if(constant.equals(BiometricsConstantMapper.BIO_SO_LEFT.getConstant())) {
			return BiometricsConstantMapper.BIO_SO_LEFT.getContantNumber();
		}

		if(constant.equals(BiometricsConstantMapper.BIO_SO_NORMAL.getConstant())) {
			return BiometricsConstantMapper.BIO_SO_NORMAL.getContantNumber();
		}

		if(constant.equals(BiometricsConstantMapper.BIO_SO_RIGHT.getConstant())) {
			return BiometricsConstantMapper.BIO_SO_RIGHT.getContantNumber();
		}

		if(constant.equals(BiometricsConstantMapper.BIO_ST_FACIAL_FEATURES.getConstant())) {
			return BiometricsConstantMapper.BIO_ST_FACIAL_FEATURES.getContantNumber();
		}

		if(constant.equals(BiometricsConstantMapper.BIO_ST_FINGERPRINT.getConstant())) {
			return BiometricsConstantMapper.BIO_ST_FINGERPRINT.getContantNumber();
		}

		if(constant.equals(BiometricsConstantMapper.BIO_ST_GAIT.getConstant())) {
			return BiometricsConstantMapper.BIO_ST_GAIT.getContantNumber();
		}

		if(constant.equals(BiometricsConstantMapper.BIO_ST_HAND_GEOMETRY.getConstant())) {
			return BiometricsConstantMapper.BIO_ST_HAND_GEOMETRY.getContantNumber();
		}

		if(constant.equals(BiometricsConstantMapper.BIO_ST_IRIS.getConstant())) {
			return BiometricsConstantMapper.BIO_ST_IRIS.getContantNumber();
		}

		if(constant.equals(BiometricsConstantMapper.BIO_ST_KEYSTROKE_DYNAMICS.getConstant())) {
			return BiometricsConstantMapper.BIO_ST_KEYSTROKE_DYNAMICS.getContantNumber();
		}

		if(constant.equals(BiometricsConstantMapper.BIO_ST_LIP_MOVEMENT.getConstant())) {
			return BiometricsConstantMapper.BIO_ST_LIP_MOVEMENT.getContantNumber();
		}

		if(constant.equals(BiometricsConstantMapper.BIO_ST_PASSWORD.getConstant())) {
			return BiometricsConstantMapper.BIO_ST_PASSWORD.getContantNumber();
		}

		if(constant.equals(BiometricsConstantMapper.BIO_ST_RETINA.getConstant())) {
			return BiometricsConstantMapper.BIO_ST_RETINA.getContantNumber();
		}

		if(constant.equals(BiometricsConstantMapper.BIO_ST_SIGNATURE_DYNAMICS.getConstant())) {
			return BiometricsConstantMapper.BIO_ST_SIGNATURE_DYNAMICS.getContantNumber();
		}

		if(constant.equals(BiometricsConstantMapper.BIO_ST_THERMAL_FACE_IMAGE.getConstant())) {
			return BiometricsConstantMapper.BIO_ST_THERMAL_FACE_IMAGE.getContantNumber();
		}

		if(constant.equals(BiometricsConstantMapper.BIO_ST_THERMAL_HAND_IMAGE.getConstant())) {
			return BiometricsConstantMapper.BIO_ST_THERMAL_HAND_IMAGE.getContantNumber();
		}

		if(constant.equals(BiometricsConstantMapper.BIO_ST_VOICE.getConstant())) {
			return BiometricsConstantMapper.BIO_ST_VOICE.getContantNumber();
		}
		
		
		return Integer.parseInt(constant);
	}
	
	
	
}
