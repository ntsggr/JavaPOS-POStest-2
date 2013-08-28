package postest2;

public class RFIDScannerConstantMapper {
	
	  /////////////////////////////////////////////////////////////////////
	  // "CapMultipleProtocols", "CurrentTagProtocol", and
	  // "ProtocolMask" Property Constants
	  /////////////////////////////////////////////////////////////////////

	  public static final ConstantConverter RFID_PR_EPC0                         = new ConstantConverter( 0x00000001, "RFID_PR_EPC0");
	  public static final ConstantConverter RFID_PR_0PLUS                        = new ConstantConverter( 0x00000002, "RFID_PR_0PLUS");
	  public static final ConstantConverter RFID_PR_EPC1                         = new ConstantConverter( 0x00000004, "RFID_PR_EPC1");
	  public static final ConstantConverter RFID_PR_EPC1G2                       = new ConstantConverter( 0x00000008, "RFID_PR_EPC1G2");
	  public static final ConstantConverter RFID_PR_EPC2                         = new ConstantConverter( 0x00000010, "RFID_PR_EPC2");
	  public static final ConstantConverter RFID_PR_ISO14443A                    = new ConstantConverter( 0x00001000, "RFID_PR_ISO14443A");
	  public static final ConstantConverter RFID_PR_ISO14443B                    = new ConstantConverter( 0x00002000, "RFID_PR_ISO14443B");
	  public static final ConstantConverter RFID_PR_ISO15693                     = new ConstantConverter( 0x00003000, "RFID_PR_ISO15693");
	  public static final ConstantConverter RFID_PR_ISO180006B                   = new ConstantConverter( 0x00004000, "RFID_PR_ISO180006B");
	  public static final ConstantConverter RFID_PR_OTHER                        = new ConstantConverter( 0x01000000, "RFID_PR_OTHER");
	  public static final ConstantConverter RFID_PR_ALL                          = new ConstantConverter( 0x40000000, "RFID_PR_ALL"); // (ProtocolMask only)


	  /////////////////////////////////////////////////////////////////////
	  // "readTags" and "startReadTags" Methods: "Cmd" Parameter Constants
	  /////////////////////////////////////////////////////////////////////

	  public static final ConstantConverter RFID_RT_ID                           = new ConstantConverter( 0x10, "RFID_RT_ID");
	  public static final ConstantConverter RFID_RT_FULLUSERDATA                 = new ConstantConverter( 0x01, "RFID_RT_FULLUSERDATA");
	  public static final ConstantConverter RFID_RT_PARTIALUSERDATA              = new ConstantConverter( 0x02, "RFID_RT_PARTIALUSERDATA");
	  public static final ConstantConverter RFID_RT_ID_FULLUSERDATA              = new ConstantConverter( 0x11, "RFID_RT_ID_FULLUSERDATA");
	  public static final ConstantConverter RFID_RT_ID_PARTIALUSERDATA           = new ConstantConverter( 0x12, "RFID_RT_ID_PARTIALUSERDATA");
	  
	  
	  public static int getConstantNumberFromString(String constant){

			if(constant.equals(RFIDScannerConstantMapper.RFID_PR_EPC0.getConstant())) {
				return RFIDScannerConstantMapper.RFID_PR_EPC0.getContantNumber();
			}

			if(constant.equals(RFIDScannerConstantMapper.RFID_PR_0PLUS.getConstant())) {
				return RFIDScannerConstantMapper.RFID_PR_0PLUS.getContantNumber();
			}

			if(constant.equals(RFIDScannerConstantMapper.RFID_PR_EPC1.getConstant())) {
				return RFIDScannerConstantMapper.RFID_PR_EPC1.getContantNumber();
			}

			if(constant.equals(RFIDScannerConstantMapper.RFID_PR_EPC1G2.getConstant())) {
				return RFIDScannerConstantMapper.RFID_PR_EPC1G2.getContantNumber();
			}

			if(constant.equals(RFIDScannerConstantMapper.RFID_PR_EPC2.getConstant())) {
				return RFIDScannerConstantMapper.RFID_PR_EPC2.getContantNumber();
			}

			if(constant.equals(RFIDScannerConstantMapper.RFID_PR_ISO14443A.getConstant())) {
				return RFIDScannerConstantMapper.RFID_PR_ISO14443A.getContantNumber();
			}

			if(constant.equals(RFIDScannerConstantMapper.RFID_PR_ISO14443B.getConstant())) {
				return RFIDScannerConstantMapper.RFID_PR_ISO14443B.getContantNumber();
			}

			if(constant.equals(RFIDScannerConstantMapper.RFID_PR_ISO15693.getConstant())) {
				return RFIDScannerConstantMapper.RFID_PR_ISO15693.getContantNumber();
			}

			if(constant.equals(RFIDScannerConstantMapper.RFID_PR_ISO180006B.getConstant())) {
				return RFIDScannerConstantMapper.RFID_PR_ISO180006B.getContantNumber();
			}

			if(constant.equals(RFIDScannerConstantMapper.RFID_PR_OTHER.getConstant())) {
				return RFIDScannerConstantMapper.RFID_PR_OTHER.getContantNumber();
			}

			if(constant.equals(RFIDScannerConstantMapper.RFID_PR_ALL.getConstant())) {
				return RFIDScannerConstantMapper.RFID_PR_ALL.getContantNumber();
			}

			if(constant.equals(RFIDScannerConstantMapper.RFID_RT_ID.getConstant())) {
				return RFIDScannerConstantMapper.RFID_RT_ID.getContantNumber();
			}

			if(constant.equals(RFIDScannerConstantMapper.RFID_RT_FULLUSERDATA.getConstant())) {
				return RFIDScannerConstantMapper.RFID_RT_FULLUSERDATA.getContantNumber();
			}

			if(constant.equals(RFIDScannerConstantMapper.RFID_RT_PARTIALUSERDATA.getConstant())) {
				return RFIDScannerConstantMapper.RFID_RT_PARTIALUSERDATA.getContantNumber();
			}

			if(constant.equals(RFIDScannerConstantMapper.RFID_RT_ID_FULLUSERDATA.getConstant())) {
				return RFIDScannerConstantMapper.RFID_RT_ID_FULLUSERDATA.getContantNumber();
			}

			if(constant.equals(RFIDScannerConstantMapper.RFID_RT_ID_PARTIALUSERDATA.getConstant())) {
				return RFIDScannerConstantMapper.RFID_RT_ID_PARTIALUSERDATA.getContantNumber();
			}

			return Integer.parseInt(constant);
		}
	
}
