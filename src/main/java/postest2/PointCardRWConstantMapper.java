package postest2;

public class PointCardRWConstantMapper {

	// ///////////////////////////////////////////////////////////////////
	// "MapMode" Property Constants
	// ///////////////////////////////////////////////////////////////////
	public static final ConstantConverter PCRW_MM_DOTS = new ConstantConverter(1, "PPAD_TRANS_DEBIT");
	public static final ConstantConverter PCRW_MM_TWIPS = new ConstantConverter(2, "PCRW_MM_TWIPS");
	public static final ConstantConverter PCRW_MM_ENGLISH = new ConstantConverter(3, "PCRW_MM_ENGLISH");
	public static final ConstantConverter PCRW_MM_METRIC = new ConstantConverter(4, "PCRW_MM_METRIC");

	// ///////////////////////////////////////////////////////////////////
	// "RotatePrint" Method: "Rotation" Parameter Constants
	// ///////////////////////////////////////////////////////////////////
	public static final ConstantConverter PCRW_RP_NORMAL = new ConstantConverter(0x0001, "PCRW_RP_NORMAL");
	// In drafts of spec, but not in final - should never have been defined.
	// public static final int PCRW_RP_NORMAL_ASYNC = 0x0002;
	public static final ConstantConverter PCRW_RP_RIGHT90 = new ConstantConverter(0x0101, "PCRW_RP_RIGHT90");
	public static final ConstantConverter PCRW_RP_LEFT90 = new ConstantConverter(0x0102, "PCRW_RP_LEFT90");
	public static final ConstantConverter PCRW_RP_ROTATE180 = new ConstantConverter(0x0103,
			"PCRW_RP_ROTATE180");

	public static int getConstantNumberFromString(String constant) {

		if (constant.equals(PointCardRWConstantMapper.PCRW_MM_DOTS.getConstant())) {
			return PointCardRWConstantMapper.PCRW_MM_DOTS.getContantNumber();
		}

		if (constant.equals(PointCardRWConstantMapper.PCRW_MM_TWIPS.getConstant())) {
			return PointCardRWConstantMapper.PCRW_MM_TWIPS.getContantNumber();
		}

		if (constant.equals(PointCardRWConstantMapper.PCRW_MM_ENGLISH.getConstant())) {
			return PointCardRWConstantMapper.PCRW_MM_ENGLISH.getContantNumber();
		}

		if (constant.equals(PointCardRWConstantMapper.PCRW_MM_METRIC.getConstant())) {
			return PointCardRWConstantMapper.PCRW_MM_METRIC.getContantNumber();
		}

		if (constant.equals(PointCardRWConstantMapper.PCRW_RP_NORMAL.getConstant())) {
			return PointCardRWConstantMapper.PCRW_RP_NORMAL.getContantNumber();
		}

		if (constant.equals(PointCardRWConstantMapper.PCRW_RP_RIGHT90.getConstant())) {
			return PointCardRWConstantMapper.PCRW_RP_RIGHT90.getContantNumber();
		}

		if (constant.equals(PointCardRWConstantMapper.PCRW_RP_LEFT90.getConstant())) {
			return PointCardRWConstantMapper.PCRW_RP_LEFT90.getContantNumber();
		}

		if (constant.equals(PointCardRWConstantMapper.PCRW_RP_ROTATE180.getConstant())) {
			return PointCardRWConstantMapper.PCRW_RP_ROTATE180.getContantNumber();
		}

		return Integer.parseInt(constant);
	}

}
