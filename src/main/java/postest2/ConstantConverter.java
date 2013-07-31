package postest2;

public class ConstantConverter {

	String constant;
	int contantNumber;

	public ConstantConverter(int constantNumber, String constant) {
		this.constant = constant;
		this.contantNumber = constantNumber;
	}
	
	public String getConstant() {
		return constant;
	}

	public void setConstant(String constant) {
		this.constant = constant;
	}

	public int getContantNumber() {
		return contantNumber;
	}

	public void setContantNumber(int contantNumber) {
		this.contantNumber = contantNumber;
	}

	
}
