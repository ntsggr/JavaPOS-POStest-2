package postest2;

public class CoinDispenserConstantMapper implements IMapWrapper {

	// ///////////////////////////////////////////////////////////////////
	// "DispenserStatus" Property Constants
	// ///////////////////////////////////////////////////////////////////
	@BelongingProperty(PropertyNames.getDispenserStatus)
	public static final ConstantConverter COIN_STATUS_OK = new ConstantConverter(1, "COIN_STATUS_OK");
	@BelongingProperty(PropertyNames.getDispenserStatus)
	public static final ConstantConverter COIN_STATUS_EMPTY = new ConstantConverter(2, "COIN_STATUS_EMPTY");
	@BelongingProperty(PropertyNames.getDispenserStatus)
	public static final ConstantConverter COIN_STATUS_NEAREMPTY = new ConstantConverter(3,
			"COIN_STATUS_NEAREMPTY");
	@BelongingProperty(PropertyNames.getDispenserStatus)
	public static final ConstantConverter COIN_STATUS_JAM = new ConstantConverter(4, "COIN_STATUS_JAM");

	@Override
	public IMapWrapper getTheClass() {
		return this;
	}

}
