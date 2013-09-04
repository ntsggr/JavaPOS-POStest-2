package postest2;


public class MSRConstantMapper implements IMapWrapper {

    /////////////////////////////////////////////////////////////////////
    // "TracksToRead" Property Constants
    /////////////////////////////////////////////////////////////////////
	@BelongingProperty(PropertyNames.getTracksToRead)
    public static final ConstantConverter MSR_TR_NONE      = new ConstantConverter(0, "MSR_TR_NONE");
	
	@BelongingProperty(PropertyNames.getTracksToRead)
    public static final ConstantConverter MSR_TR_1         = new ConstantConverter(1, "MSR_TR_1");
	@BelongingProperty(PropertyNames.getTracksToRead)
    public static final ConstantConverter MSR_TR_2         = new ConstantConverter(2, "MSR_TR_2");
	@BelongingProperty(PropertyNames.getTracksToRead)
    public static final ConstantConverter MSR_TR_3         = new ConstantConverter(4, "MSR_TR_3");
	@BelongingProperty(PropertyNames.getTracksToRead)
    public static final ConstantConverter MSR_TR_4         = new ConstantConverter(8, "MSR_TR_4");

	@BelongingProperty(PropertyNames.getTracksToRead)
    public static final ConstantConverter MSR_TR_1_2       = new ConstantConverter(MSR_TR_1.getContantNumber() | MSR_TR_2.getContantNumber(), "MSR_TR_1_2");
	@BelongingProperty(PropertyNames.getTracksToRead)
	public static final ConstantConverter MSR_TR_1_3       = new ConstantConverter(MSR_TR_1.getContantNumber() | MSR_TR_3.getContantNumber(), "MSR_TR_1_3");
	@BelongingProperty(PropertyNames.getTracksToRead)
	public static final ConstantConverter MSR_TR_1_4       = new ConstantConverter(MSR_TR_1.getContantNumber() | MSR_TR_4.getContantNumber(), "MSR_TR_1_4");
	@BelongingProperty(PropertyNames.getTracksToRead)
	public static final ConstantConverter MSR_TR_2_3       = new ConstantConverter(MSR_TR_2.getContantNumber() | MSR_TR_3.getContantNumber(), "MSR_TR_2_3");
	@BelongingProperty(PropertyNames.getTracksToRead)
	public static final ConstantConverter MSR_TR_2_4       = new ConstantConverter(MSR_TR_2.getContantNumber() | MSR_TR_4.getContantNumber(), "MSR_TR_2_4");
	@BelongingProperty(PropertyNames.getTracksToRead)
	public static final ConstantConverter MSR_TR_3_4       = new ConstantConverter(MSR_TR_3.getContantNumber() | MSR_TR_4.getContantNumber(), "MSR_TR_3_4");

	@BelongingProperty(PropertyNames.getTracksToRead)
	public static final ConstantConverter MSR_TR_1_2_3     = new ConstantConverter(MSR_TR_1.getContantNumber() | MSR_TR_2.getContantNumber() | MSR_TR_3.getContantNumber(), "MSR_TR_1_2_3");
	@BelongingProperty(PropertyNames.getTracksToRead)
	public static final ConstantConverter MSR_TR_1_2_4     = new ConstantConverter(MSR_TR_1.getContantNumber() | MSR_TR_2.getContantNumber() | MSR_TR_4.getContantNumber(), "MSR_TR_1_2_4");
	@BelongingProperty(PropertyNames.getTracksToRead)
	public static final ConstantConverter MSR_TR_1_3_4     = new ConstantConverter(MSR_TR_1.getContantNumber() | MSR_TR_3.getContantNumber() | MSR_TR_4.getContantNumber(), "MSR_TR_1_3_4");
	@BelongingProperty(PropertyNames.getTracksToRead)
	public static final ConstantConverter MSR_TR_2_3_4     = new ConstantConverter(MSR_TR_2.getContantNumber() | MSR_TR_3.getContantNumber() | MSR_TR_4.getContantNumber(), "MSR_TR_2_3_4");

	@BelongingProperty(PropertyNames.getTracksToRead)
	public static final ConstantConverter MSR_TR_1_2_3_4   = new ConstantConverter(MSR_TR_1.getContantNumber() | MSR_TR_2.getContantNumber() |
                                               MSR_TR_3.getContantNumber() | MSR_TR_4.getContantNumber(), "MSR_TR_1_2_3_4");


    /////////////////////////////////////////////////////////////////////
    // "ErrorReportingType" Property Constants
    /////////////////////////////////////////////////////////////////////
	@BelongingProperty(PropertyNames.getErrorReportingType)
    public static final ConstantConverter MSR_ERT_CARD         = new ConstantConverter(0, "MSR_ERT_CARD");
	@BelongingProperty(PropertyNames.getErrorReportingType)
    public static final ConstantConverter MSR_ERT_TRACK        = new ConstantConverter(1, "MSR_ERT_TRACK");


    /////////////////////////////////////////////////////////////////////
    // "CapDataEncryption", "DataEncryptionAlgorithm" Property Constants
    //   (added in 1.12)
    /////////////////////////////////////////////////////////////////////
    @BelongingProperty(PropertyNames.getCapDataEncryption)
    public static final ConstantConverter MSR_DE_NONE          = new ConstantConverter(0x00000001, "MSR_DE_NONE");
    @BelongingProperty(PropertyNames.getCapDataEncryption)
    public static final ConstantConverter MSR_DE_3DEA_DUKPT    = new ConstantConverter(0x00000002, "MSR_DE_3DEA_DUKPT");
    // Note: Service-specific values begin at 0x01000000.


    /////////////////////////////////////////////////////////////////////
    // "CapDeviceAuthentication" Property Constants (added in 1.12)
    /////////////////////////////////////////////////////////////////////
    @BelongingProperty(PropertyNames.getCapDeviceAuthentication)
    public static final ConstantConverter MSR_DA_NOT_SUPPORTED = new ConstantConverter(0, "MSR_DA_NOT_SUPPORTED");
    @BelongingProperty(PropertyNames.getCapDeviceAuthentication)
    public static final ConstantConverter MSR_DA_OPTIONAL      = new ConstantConverter(1, "MSR_DA_OPTIONAL");
    @BelongingProperty(PropertyNames.getCapDeviceAuthentication)
    public static final ConstantConverter MSR_DA_REQUIRED      = new ConstantConverter(2, "MSR_DA_REQUIRED");


    /////////////////////////////////////////////////////////////////////
    // "DeviceAuthenticationProtocol" Property Constants (added in 1.12)
    /////////////////////////////////////////////////////////////////////
    @BelongingProperty(PropertyNames.getDeviceAuthenticationProtocol)
    public static final ConstantConverter MSR_AP_NONE              = new ConstantConverter(0, "MSR_AP_NONE");
    @BelongingProperty(PropertyNames.getDeviceAuthenticationProtocol)
    public static final ConstantConverter MSR_AP_CHALLENGERESPONSE = new ConstantConverter(1, "MSR_AP_CHALLENGERESPONSE");

    
    /////////////////////////////////////////////////////////////////////
    // "CardType" Property Constants (added in 1.12)
    /////////////////////////////////////////////////////////////////////
    @BelongingProperty(PropertyNames.getCardType)
    public static final String MSR_CT_AAMVA = "AAMVA";
    @BelongingProperty(PropertyNames.getCardType)
    public static final String MSR_CT_BANK  = "BANK";


    /////////////////////////////////////////////////////////////////////
    // "retrieveCardProperty" Parameter Constants (added in 1.12)
    /////////////////////////////////////////////////////////////////////

    public static final String MSR_RCP_AccountNumber  = "AccountNumber";
    public static final String MSR_RCP_Address        = "Address";
    public static final String MSR_RCP_BirthDate      = "BirthDate";
    public static final String MSR_RCP_City           = "City";
    public static final String MSR_RCP_Class          = "Class";
    public static final String MSR_RCP_Endorsements   = "Endorsements";
    public static final String MSR_RCP_ExpirationDate = "ExpirationDate";
    public static final String MSR_RCP_EyeColor       = "EyeColor";
    public static final String MSR_RCP_FirstName      = "FirstName";
    public static final String MSR_RCP_Gender         = "Gender";
    public static final String MSR_RCP_HairColor      = "HairColor";
    public static final String MSR_RCP_Height         = "Height";
    public static final String MSR_RCP_LicenseNumber  = "LicenseNumber";
    public static final String MSR_RCP_MiddleInitial  = "MiddleInitial";
    public static final String MSR_RCP_PostalCode     = "PostalCode";
    public static final String MSR_RCP_Restrictions   = "Restrictions";
    public static final String MSR_RCP_ServiceCode    = "ServiceCode";
    public static final String MSR_RCP_State          = "State";
    public static final String MSR_RCP_Suffix         = "Suffix";
    public static final String MSR_RCP_Surname        = "Surname";
    public static final String MSR_RCP_Title          = "Title";
    public static final String MSR_RCP_Weight         = "Weight";
    
    public static int getConstantNumberFromString(String constant){

		if(constant.equals(MSRConstantMapper.MSR_AP_CHALLENGERESPONSE.getConstant())) {
			return MSRConstantMapper.MSR_AP_CHALLENGERESPONSE.getContantNumber();
		}
		
		if(constant.equals(MSRConstantMapper.MSR_AP_NONE.getConstant())) {
			return MSRConstantMapper.MSR_AP_NONE.getContantNumber();
		}

		if(constant.equals(MSRConstantMapper.MSR_DA_NOT_SUPPORTED.getConstant())) {
			return MSRConstantMapper.MSR_DA_NOT_SUPPORTED.getContantNumber();
		}

		if(constant.equals(MSRConstantMapper.MSR_DA_OPTIONAL.getConstant())) {
			return MSRConstantMapper.MSR_DA_OPTIONAL.getContantNumber();
		}

		if(constant.equals(MSRConstantMapper.MSR_DA_REQUIRED.getConstant())) {
			return MSRConstantMapper.MSR_DA_REQUIRED.getContantNumber();
		}

		if(constant.equals(MSRConstantMapper.MSR_DE_3DEA_DUKPT.getConstant())) {
			return MSRConstantMapper.MSR_DE_3DEA_DUKPT.getContantNumber();
		}

		if(constant.equals(MSRConstantMapper.MSR_DE_NONE.getConstant())) {
			return MSRConstantMapper.MSR_DE_NONE.getContantNumber();
		}

		if(constant.equals(MSRConstantMapper.MSR_ERT_CARD.getConstant())) {
			return MSRConstantMapper.MSR_ERT_CARD.getContantNumber();
		}

		if(constant.equals(MSRConstantMapper.MSR_ERT_TRACK.getConstant())) {
			return MSRConstantMapper.MSR_ERT_TRACK.getContantNumber();
		}

		if(constant.equals(MSRConstantMapper.MSR_TR_1.getConstant())) {
			return MSRConstantMapper.MSR_TR_1.getContantNumber();
		}

		if(constant.equals(MSRConstantMapper.MSR_TR_1_2.getConstant())) {
			return MSRConstantMapper.MSR_TR_1_2.getContantNumber();
		}

		if(constant.equals(MSRConstantMapper.MSR_TR_1_2_3.getConstant())) {
			return MSRConstantMapper.MSR_TR_1_2_3.getContantNumber();
		}

		if(constant.equals(MSRConstantMapper.MSR_TR_1_2_3_4.getConstant())) {
			return MSRConstantMapper.MSR_TR_1_2_3_4.getContantNumber();
		}

		if(constant.equals(MSRConstantMapper.MSR_TR_1_2_4.getConstant())) {
			return MSRConstantMapper.MSR_TR_1_2_4.getContantNumber();
		}

		if(constant.equals(MSRConstantMapper.MSR_TR_1_3.getConstant())) {
			return MSRConstantMapper.MSR_TR_1_3.getContantNumber();
		}

		if(constant.equals(MSRConstantMapper.MSR_TR_1_3_4.getConstant())) {
			return MSRConstantMapper.MSR_TR_1_3_4.getContantNumber();
		}

		if(constant.equals(MSRConstantMapper.MSR_TR_1_4.getConstant())) {
			return MSRConstantMapper.MSR_TR_1_4.getContantNumber();
		}

		if(constant.equals(MSRConstantMapper.MSR_TR_2.getConstant())) {
			return MSRConstantMapper.MSR_TR_2.getContantNumber();
		}

		if(constant.equals(MSRConstantMapper.MSR_TR_2_3.getConstant())) {
			return MSRConstantMapper.MSR_TR_2_3.getContantNumber();
		}

		if(constant.equals(MSRConstantMapper.MSR_TR_2_3_4.getConstant())) {
			return MSRConstantMapper.MSR_TR_2_3_4.getContantNumber();
		}

		if(constant.equals(MSRConstantMapper.MSR_TR_2_4.getConstant())) {
			return MSRConstantMapper.MSR_TR_2_4.getContantNumber();
		}

		if(constant.equals(MSRConstantMapper.MSR_TR_3.getConstant())) {
			return MSRConstantMapper.MSR_TR_3.getContantNumber();
		}

		if(constant.equals(MSRConstantMapper.MSR_TR_3_4.getConstant())) {
			return MSRConstantMapper.MSR_TR_3_4.getContantNumber();
		}

		if(constant.equals(MSRConstantMapper.MSR_TR_4.getConstant())) {
			return MSRConstantMapper.MSR_TR_4.getContantNumber();
		}

		if(constant.equals(MSRConstantMapper.MSR_TR_NONE.getConstant())) {
			return MSRConstantMapper.MSR_TR_NONE.getContantNumber();
		}
		
		
		return Integer.parseInt(constant);
	}

	@Override
	public IMapWrapper getTheClass() {
		return this;
	}
	
}
