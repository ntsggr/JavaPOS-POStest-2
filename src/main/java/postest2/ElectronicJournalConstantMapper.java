package postest2;

public class ElectronicJournalConstantMapper {
	// ///////////////////////////////////////////////////////////////////
	// "CapStation", "Station" Property Constants
	// ///////////////////////////////////////////////////////////////////

	public static final ConstantConverter EJ_S_RECEIPT = new ConstantConverter(0x00000001, "EJ_S_RECEIPT");
	public static final ConstantConverter EJ_S_SLIP = new ConstantConverter(0x00000002, "EJ_S_SLIP");
	public static final ConstantConverter EJ_S_JOURNAL = new ConstantConverter(0x00000004, "EJ_S_JOURNAL");

	// ///////////////////////////////////////////////////////////////////
	// "retrieveCurrentMarker" Method, "markerType" Parameter Constants
	// "retrieveMarker" Method, "markerType" Parameter Constants
	// "retrieveMarkerByDateTime" Method, "markerType" Parameter Constants
	// ///////////////////////////////////////////////////////////////////

	public static final ConstantConverter EJ_MT_SESSION_BEG = new ConstantConverter(1, "EJ_MT_SESSION_BEG");
	public static final ConstantConverter EJ_MT_SESSION_END = new ConstantConverter(2, "EJ_MT_SESSION_END");
	public static final ConstantConverter EJ_MT_DOCUMENT = new ConstantConverter(3, "EJ_MT_DOCUMENT");
	public static final ConstantConverter EJ_MT_HEAD = new ConstantConverter(4, "EJ_MT_HEAD");
	public static final ConstantConverter EJ_MT_TAIL = new ConstantConverter(5, "EJ_MT_TAIL");
	
	
	
	

	public static int getConstantNumberFromString(String constant) {

		if (constant.equals(ElectronicJournalConstantMapper.EJ_S_RECEIPT.getConstant())) {
			return ElectronicJournalConstantMapper.EJ_S_RECEIPT.getContantNumber();
		}

		if (constant.equals(ElectronicJournalConstantMapper.EJ_S_SLIP.getConstant())) {
			return ElectronicJournalConstantMapper.EJ_S_SLIP.getContantNumber();
		}

		if (constant.equals(ElectronicJournalConstantMapper.EJ_S_JOURNAL.getConstant())) {
			return ElectronicJournalConstantMapper.EJ_S_JOURNAL.getContantNumber();
		}

		if (constant.equals(ElectronicJournalConstantMapper.EJ_MT_SESSION_BEG.getConstant())) {
			return ElectronicJournalConstantMapper.EJ_MT_SESSION_BEG.getContantNumber();
		}

		if (constant.equals(ElectronicJournalConstantMapper.EJ_MT_SESSION_END.getConstant())) {
			return ElectronicJournalConstantMapper.EJ_MT_SESSION_END.getContantNumber();
		}

		if (constant.equals(ElectronicJournalConstantMapper.EJ_MT_DOCUMENT.getConstant())) {
			return ElectronicJournalConstantMapper.EJ_MT_DOCUMENT.getContantNumber();
		}

		if (constant.equals(ElectronicJournalConstantMapper.EJ_MT_HEAD.getConstant())) {
			return ElectronicJournalConstantMapper.EJ_MT_HEAD.getContantNumber();
		}

		if (constant.equals(ElectronicJournalConstantMapper.EJ_MT_TAIL.getConstant())) {
			return ElectronicJournalConstantMapper.EJ_MT_TAIL.getContantNumber();
		}

		return Integer.parseInt(constant);
	}

}
