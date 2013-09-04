package postest2;

public class ScannerConstantMapper implements IMapWrapper {

	// ///////////////////////////////////////////////////////////////////
	// "ScanDataType" Property Constants
	// ///////////////////////////////////////////////////////////////////
	@BelongingProperty(PropertyNames.getScanDataType)
	public static final ConstantConverter SCAN_SDT_UPCA = new ConstantConverter(101, "SCAN_SDT_UPCA");
	@BelongingProperty(PropertyNames.getScanDataType)
	public static final ConstantConverter SCAN_SDT_UPCE = new ConstantConverter(102, "SCAN_SDT_UPCE");
	@BelongingProperty(PropertyNames.getScanDataType)
	public static final ConstantConverter SCAN_SDT_JAN8 = new ConstantConverter(103, "SCAN_SDT_JAN8");
	@BelongingProperty(PropertyNames.getScanDataType)
	public static final ConstantConverter SCAN_SDT_EAN8 = new ConstantConverter(103, "SCAN_SDT_EAN8");
	@BelongingProperty(PropertyNames.getScanDataType)
	public static final ConstantConverter SCAN_SDT_JAN13 = new ConstantConverter(104, "SCAN_SDT_JAN13");
	@BelongingProperty(PropertyNames.getScanDataType)
	public static final ConstantConverter SCAN_SDT_EAN13 = new ConstantConverter(104, "SCAN_SDT_EAN13");
	@BelongingProperty(PropertyNames.getScanDataType)
	public static final ConstantConverter SCAN_SDT_TF = new ConstantConverter(105, "SCAN_SDT_TF");
	@BelongingProperty(PropertyNames.getScanDataType)
	public static final ConstantConverter SCAN_SDT_ITF = new ConstantConverter(106, "SCAN_SDT_ITF");
	@BelongingProperty(PropertyNames.getScanDataType)
	public static final ConstantConverter SCAN_SDT_Codabar = new ConstantConverter(107, "SCAN_SDT_Codabar");
	@BelongingProperty(PropertyNames.getScanDataType)
	public static final ConstantConverter SCAN_SDT_Code39 = new ConstantConverter(108, "SCAN_SDT_Code39");
	@BelongingProperty(PropertyNames.getScanDataType)
	public static final ConstantConverter SCAN_SDT_Code93 = new ConstantConverter(109, "SCAN_SDT_Code93");
	@BelongingProperty(PropertyNames.getScanDataType)
	public static final ConstantConverter SCAN_SDT_Code128 = new ConstantConverter(110, "SCAN_SDT_Code128");
	@BelongingProperty(PropertyNames.getScanDataType)
	public static final ConstantConverter SCAN_SDT_UPCA_S = new ConstantConverter(111, "SCAN_SDT_UPCA_S");
	@BelongingProperty(PropertyNames.getScanDataType)
	public static final ConstantConverter SCAN_SDT_UPCE_S = new ConstantConverter(112, "SCAN_SDT_UPCE_S");
	@BelongingProperty(PropertyNames.getScanDataType)
	public static final ConstantConverter SCAN_SDT_UPCD1 = new ConstantConverter(113, "SCAN_SDT_UPCD1");
	@BelongingProperty(PropertyNames.getScanDataType)
	public static final ConstantConverter SCAN_SDT_UPCD2 = new ConstantConverter(114, "SCAN_SDT_UPCD2");
	@BelongingProperty(PropertyNames.getScanDataType)
	public static final ConstantConverter SCAN_SDT_UPCD3 = new ConstantConverter(115, "SCAN_SDT_UPCD3");
	@BelongingProperty(PropertyNames.getScanDataType)
	public static final ConstantConverter SCAN_SDT_UPCD4 = new ConstantConverter(116, "SCAN_SDT_UPCD4");
	@BelongingProperty(PropertyNames.getScanDataType)
	public static final ConstantConverter SCAN_SDT_UPCD5 = new ConstantConverter(117, "SCAN_SDT_UPCD5");
	@BelongingProperty(PropertyNames.getScanDataType)
	public static final ConstantConverter SCAN_SDT_EAN8_S = new ConstantConverter(118, "SCAN_SDT_EAN8_S");
	@BelongingProperty(PropertyNames.getScanDataType)
	public static final ConstantConverter SCAN_SDT_EAN13_S = new ConstantConverter(119, "SCAN_SDT_EAN13_S");
	@BelongingProperty(PropertyNames.getScanDataType)
	public static final ConstantConverter SCAN_SDT_EAN128 = new ConstantConverter(120, "SCAN_SDT_EAN128");
	@BelongingProperty(PropertyNames.getScanDataType)
	public static final ConstantConverter SCAN_SDT_OCRA = new ConstantConverter(121, "SCAN_SDT_OCRA");
	@BelongingProperty(PropertyNames.getScanDataType)
	public static final ConstantConverter SCAN_SDT_OCRB = new ConstantConverter(122, "SCAN_SDT_OCRB");
	@BelongingProperty(PropertyNames.getScanDataType)
	public static final ConstantConverter SCAN_SDT_RSS14 = new ConstantConverter(131, "SCAN_SDT_RSS14");
	@BelongingProperty(PropertyNames.getScanDataType)
	public static final ConstantConverter SCAN_SDT_RSS_EXPANDED = new ConstantConverter(132, "SCAN_SDT_RSS_EXPANDED");
	@BelongingProperty(PropertyNames.getScanDataType)
	public static final ConstantConverter SCAN_SDT_GS1DATABAR = new ConstantConverter(131, "SCAN_SDT_GS1DATABAR");
	@BelongingProperty(PropertyNames.getScanDataType)
	public static final ConstantConverter SCAN_SDT_GS1DATABAR_E = new ConstantConverter(132, "SCAN_SDT_GS1DATABAR_E");
	@BelongingProperty(PropertyNames.getScanDataType)
	public static final ConstantConverter SCAN_SDT_CCA = new ConstantConverter(151, "SCAN_SDT_CCA");
	@BelongingProperty(PropertyNames.getScanDataType)
	public static final ConstantConverter SCAN_SDT_CCB = new ConstantConverter(152, "SCAN_SDT_CCB");
	@BelongingProperty(PropertyNames.getScanDataType)
	public static final ConstantConverter SCAN_SDT_CCC = new ConstantConverter(153, "SCAN_SDT_CCC");
	@BelongingProperty(PropertyNames.getScanDataType)
	public static final ConstantConverter SCAN_SDT_PDF417 = new ConstantConverter(201, "SCAN_SDT_PDF417");
	@BelongingProperty(PropertyNames.getScanDataType)
	public static final ConstantConverter SCAN_SDT_MAXICODE = new ConstantConverter(202, "SCAN_SDT_MAXICODE");
	@BelongingProperty(PropertyNames.getScanDataType)
	public static final ConstantConverter SCAN_SDT_DATAMATRIX = new ConstantConverter(203, "SCAN_SDT_DATAMATRIX");
	@BelongingProperty(PropertyNames.getScanDataType)
	public static final ConstantConverter SCAN_SDT_QRCODE = new ConstantConverter(204, "SCAN_SDT_QRCODE");
	@BelongingProperty(PropertyNames.getScanDataType)
	public static final ConstantConverter SCAN_SDT_UQRCODE = new ConstantConverter(205, "SCAN_SDT_UQRCODE");
	@BelongingProperty(PropertyNames.getScanDataType)
	public static final ConstantConverter SCAN_SDT_AZTEC = new ConstantConverter(206, "SCAN_SDT_AZTEC");
	@BelongingProperty(PropertyNames.getScanDataType)
	public static final ConstantConverter SCAN_SDT_UPDF417 = new ConstantConverter(207, "SCAN_SDT_UPDF417");
	@BelongingProperty(PropertyNames.getScanDataType)
	public static final ConstantConverter SCAN_SDT_OTHER = new ConstantConverter(501, "SCAN_SDT_OTHER");
	@BelongingProperty(PropertyNames.getScanDataType)
	public static final ConstantConverter SCAN_SDT_UNKNOWN = new ConstantConverter(0, "SCAN_SDT_UNKNOWN");
	
	
	public static int getConstantNumberFromString(String constant) {

		if (constant.equals(ScannerConstantMapper.SCAN_SDT_UPCA.getConstant())) {
			return ScannerConstantMapper.SCAN_SDT_UPCA.getContantNumber();
		}
		
		if (constant.equals(ScannerConstantMapper.SCAN_SDT_UPCE.getConstant())) {
			return ScannerConstantMapper.SCAN_SDT_UPCE.getContantNumber();
		}
		
		if (constant.equals(ScannerConstantMapper.SCAN_SDT_JAN8.getConstant())) {
			return ScannerConstantMapper.SCAN_SDT_JAN8.getContantNumber();
		}
		
		if (constant.equals(ScannerConstantMapper.SCAN_SDT_EAN8.getConstant())) {
			return ScannerConstantMapper.SCAN_SDT_EAN8.getContantNumber();
		}
		
		if (constant.equals(ScannerConstantMapper.SCAN_SDT_JAN13.getConstant())) {
			return ScannerConstantMapper.SCAN_SDT_JAN13.getContantNumber();
		}
		
		if (constant.equals(ScannerConstantMapper.SCAN_SDT_EAN13.getConstant())) {
			return ScannerConstantMapper.SCAN_SDT_EAN13.getContantNumber();
		}
		
		if (constant.equals(ScannerConstantMapper.SCAN_SDT_TF.getConstant())) {
			return ScannerConstantMapper.SCAN_SDT_TF.getContantNumber();
		}
		
		if (constant.equals(ScannerConstantMapper.SCAN_SDT_ITF.getConstant())) {
			return ScannerConstantMapper.SCAN_SDT_ITF.getContantNumber();
		}
		
		if (constant.equals(ScannerConstantMapper.SCAN_SDT_Codabar.getConstant())) {
			return ScannerConstantMapper.SCAN_SDT_Codabar.getContantNumber();
		}
		
		if (constant.equals(ScannerConstantMapper.SCAN_SDT_Code39.getConstant())) {
			return ScannerConstantMapper.SCAN_SDT_Code39.getContantNumber();
		}
		
		if (constant.equals(ScannerConstantMapper.SCAN_SDT_Code93.getConstant())) {
			return ScannerConstantMapper.SCAN_SDT_Code93.getContantNumber();
		}
		
		if (constant.equals(ScannerConstantMapper.SCAN_SDT_Code128.getConstant())) {
			return ScannerConstantMapper.SCAN_SDT_Code128.getContantNumber();
		}
		
		if (constant.equals(ScannerConstantMapper.SCAN_SDT_UPCA_S.getConstant())) {
			return ScannerConstantMapper.SCAN_SDT_UPCA_S.getContantNumber();
		}
		
		if (constant.equals(ScannerConstantMapper.SCAN_SDT_UPCE_S.getConstant())) {
			return ScannerConstantMapper.SCAN_SDT_UPCE_S.getContantNumber();
		}
		
		if (constant.equals(ScannerConstantMapper.SCAN_SDT_UPCD1.getConstant())) {
			return ScannerConstantMapper.SCAN_SDT_UPCD1.getContantNumber();
		}
		
		if (constant.equals(ScannerConstantMapper.SCAN_SDT_UPCD2.getConstant())) {
			return ScannerConstantMapper.SCAN_SDT_UPCD2.getContantNumber();
		}
		
		if (constant.equals(ScannerConstantMapper.SCAN_SDT_UPCD3.getConstant())) {
			return ScannerConstantMapper.SCAN_SDT_UPCD3.getContantNumber();
		}
		
		if (constant.equals(ScannerConstantMapper.SCAN_SDT_UPCD4.getConstant())) {
			return ScannerConstantMapper.SCAN_SDT_UPCD4.getContantNumber();
		}
		
		if (constant.equals(ScannerConstantMapper.SCAN_SDT_UPCD5.getConstant())) {
			return ScannerConstantMapper.SCAN_SDT_UPCD5.getContantNumber();
		}
		
		if (constant.equals(ScannerConstantMapper.SCAN_SDT_EAN8_S.getConstant())) {
			return ScannerConstantMapper.SCAN_SDT_EAN8_S.getContantNumber();
		}
		
		if (constant.equals(ScannerConstantMapper.SCAN_SDT_EAN13_S.getConstant())) {
			return ScannerConstantMapper.SCAN_SDT_EAN13_S.getContantNumber();
		}
		
		if (constant.equals(ScannerConstantMapper.SCAN_SDT_EAN128.getConstant())) {
			return ScannerConstantMapper.SCAN_SDT_EAN128.getContantNumber();
		}
		
		if (constant.equals(ScannerConstantMapper.SCAN_SDT_OCRA.getConstant())) {
			return ScannerConstantMapper.SCAN_SDT_OCRA.getContantNumber();
		}
		
		if (constant.equals(ScannerConstantMapper.SCAN_SDT_OCRB.getConstant())) {
			return ScannerConstantMapper.SCAN_SDT_OCRB.getContantNumber();
		}
		
		if (constant.equals(ScannerConstantMapper.SCAN_SDT_RSS14.getConstant())) {
			return ScannerConstantMapper.SCAN_SDT_RSS14.getContantNumber();
		}
		
		if (constant.equals(ScannerConstantMapper.SCAN_SDT_RSS_EXPANDED.getConstant())) {
			return ScannerConstantMapper.SCAN_SDT_RSS_EXPANDED.getContantNumber();
		}
		
		if (constant.equals(ScannerConstantMapper.SCAN_SDT_GS1DATABAR.getConstant())) {
			return ScannerConstantMapper.SCAN_SDT_GS1DATABAR.getContantNumber();
		}
		
		if (constant.equals(ScannerConstantMapper.SCAN_SDT_GS1DATABAR_E.getConstant())) {
			return ScannerConstantMapper.SCAN_SDT_GS1DATABAR_E.getContantNumber();
		}
		
		if (constant.equals(ScannerConstantMapper.SCAN_SDT_CCA.getConstant())) {
			return ScannerConstantMapper.SCAN_SDT_CCA.getContantNumber();
		}
		
		if (constant.equals(ScannerConstantMapper.SCAN_SDT_PDF417.getConstant())) {
			return ScannerConstantMapper.SCAN_SDT_PDF417.getContantNumber();
		}
		
		if (constant.equals(ScannerConstantMapper.SCAN_SDT_MAXICODE.getConstant())) {
			return ScannerConstantMapper.SCAN_SDT_MAXICODE.getContantNumber();
		}
		
		if (constant.equals(ScannerConstantMapper.SCAN_SDT_DATAMATRIX.getConstant())) {
			return ScannerConstantMapper.SCAN_SDT_DATAMATRIX.getContantNumber();
		}
		
		if (constant.equals(ScannerConstantMapper.SCAN_SDT_QRCODE.getConstant())) {
			return ScannerConstantMapper.SCAN_SDT_QRCODE.getContantNumber();
		}
		
		if (constant.equals(ScannerConstantMapper.SCAN_SDT_UQRCODE.getConstant())) {
			return ScannerConstantMapper.SCAN_SDT_UQRCODE.getContantNumber();
		}
		
		if (constant.equals(ScannerConstantMapper.SCAN_SDT_AZTEC.getConstant())) {
			return ScannerConstantMapper.SCAN_SDT_AZTEC.getContantNumber();
		}
		
		if (constant.equals(ScannerConstantMapper.SCAN_SDT_UPDF417.getConstant())) {
			return ScannerConstantMapper.SCAN_SDT_UPDF417.getContantNumber();
		}
		
		if (constant.equals(ScannerConstantMapper.SCAN_SDT_OTHER.getConstant())) {
			return ScannerConstantMapper.SCAN_SDT_OTHER.getContantNumber();
		}
		
		if (constant.equals(ScannerConstantMapper.SCAN_SDT_UNKNOWN.getConstant())) {
			return ScannerConstantMapper.SCAN_SDT_UNKNOWN.getContantNumber();
		}

		return Integer.parseInt(constant);
	}

	@Override
	public IMapWrapper getTheClass() {
		return this;
	}

}
