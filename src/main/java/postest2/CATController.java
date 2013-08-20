package postest2;

import java.net.URL;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import jpos.BumpBar;
import jpos.CAT;
import jpos.CATConst;
import jpos.CashChanger;
import jpos.JposException;

public class CATController extends CommonController implements Initializable {

	@FXML
	@RequiredState(JposState.ENABLED)
	public TabPane functionPane;

	@FXML
	@RequiredState(JposState.OPENED)
	public CheckBox asyncMode;

	@FXML
	public ComboBox<String> paymentMedia;
	@FXML
	public ComboBox<Boolean> trainingMode;
	@FXML
	public ComboBox<String> accessDailyLog_type;

	@FXML
	public TextField additionalSecurityInformation;
	@FXML
	public TextField accessDailyLog_sequenceNumber;
	@FXML
	public TextField accessDailyLog_timeout;
	@FXML
	public TextField authorize_sequenceNumber;
	@FXML
	public TextField authorize_amount;
	@FXML
	public TextField authorize_taxOthers;
	@FXML
	public TextField authorize_timeout;
	@FXML
	public TextField cashDeposit_sequenceNumber;
	@FXML
	public TextField cashDeposit_amount;
	@FXML
	public TextField cashDeposit_timeout;
	@FXML
	public TextField checkCard_sequenceNumber;
	@FXML
	public TextField checkCard_timeout;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		service = new CAT();
		setUpLogicalNameComboBox();
		RequiredStateChecker.invokeThis(this, service);
		setUpComboBoxes();
	}

	/* ************************************************************************
	 * ************************ Action Handler *********************************
	 * ***********************************************************************
	 */

	@FXML
	public void handleDeviceEnable(ActionEvent e) {
		System.out.println("DevEnable");
		try {
			if (deviceEnabled.isSelected()) {
				((CAT) service).setDeviceEnabled(true);
				setUpComboBoxes();

			} else {
				((CAT) service).setDeviceEnabled(false);
			}
		} catch (JposException je) {
			JOptionPane.showMessageDialog(null, je.getMessage());
		}

		RequiredStateChecker.invokeThis(this, service);
	}

	@FXML
	public void handleAsyncMode(ActionEvent e) {
		System.out.println("async");
		try {
			((CAT) service).setAsyncMode(asyncMode.isSelected());
		} catch (JposException e1) {
			JOptionPane.showMessageDialog(null, e1.getMessage());
			e1.printStackTrace();
		}
	}

	@FXML
	public void handleSetAdditionalSecurityInformation(ActionEvent e) {
		System.out.println("setaddit");
		if(additionalSecurityInformation.getText().isEmpty()){
			JOptionPane.showMessageDialog(null, "Field should have a value");
		} else {
		try {
			((CAT) service).setAdditionalSecurityInformation(additionalSecurityInformation.getText());
		} catch (JposException e1) {
			JOptionPane.showMessageDialog(null, e1.getMessage());
			e1.printStackTrace();
		}
		}
	}

	@FXML
	public void handleSetPaymentMedia(ActionEvent e) {
		System.out.println("setpaymes");
		try {
			((CAT) service).setPaymentMedia(CATConstantMapper.getConstantNumberFromString(paymentMedia
					.getSelectionModel().getSelectedItem()));
		} catch (JposException e1) {
			JOptionPane.showMessageDialog(null, e1.getMessage());
			e1.printStackTrace();
		}
	}

	@FXML
	public void handleSetTrainingMode(ActionEvent e) {
		System.out.println("settraining");
		try {
			((CAT) service).setTrainingMode(trainingMode.getSelectionModel().getSelectedItem());
		} catch (JposException e1) {
			JOptionPane.showMessageDialog(null, e1.getMessage());
			e1.printStackTrace();
		}
	}

	@FXML
	public void handleAccessDailyLog(ActionEvent e) {
		System.out.println("dailylog");
		if(accessDailyLog_sequenceNumber.getText().isEmpty() || accessDailyLog_timeout.getText().isEmpty()){
			JOptionPane.showMessageDialog(null, "Every Field should have a value");
		} else {
			try {
				((CAT) service).accessDailyLog(Integer.parseInt(accessDailyLog_sequenceNumber.getText()), CATConstantMapper
						.getConstantNumberFromString(accessDailyLog_type.getSelectionModel().getSelectedItem()), Integer
						.parseInt(accessDailyLog_timeout.getText()));
			} catch (NumberFormatException e1) {
				JOptionPane.showMessageDialog(null, e1.getMessage());
				e1.printStackTrace();
			} catch (JposException e1) {
				JOptionPane.showMessageDialog(null, e1.getMessage());
				e1.printStackTrace();
			}
		}
	}

	@FXML
	public void handleAuthorizeCompletion(ActionEvent e) {
		System.out.println("authComp");
		if(authorize_amount.getText().isEmpty() || authorize_sequenceNumber.getText().isEmpty() || authorize_taxOthers.getText().isEmpty() || authorize_timeout.getText().isEmpty()){
			JOptionPane.showMessageDialog(null, "Every Field should have a value");
		} else {
			try {
				((CAT) service).authorizeCompletion(Integer.parseInt(authorize_sequenceNumber.getText()),
						Long.parseLong(authorize_amount.getText()), 
						Long.parseLong(authorize_taxOthers.getText()),
						Integer.parseInt(authorize_timeout.getText()));
			} catch (NumberFormatException e1) {
				JOptionPane.showMessageDialog(null, e1.getMessage());
				e1.printStackTrace();
			} catch (JposException e1) {
				JOptionPane.showMessageDialog(null, e1.getMessage());
				e1.printStackTrace();
			}
		}
	}

	@FXML
	public void handleAuthorizePreSales(ActionEvent e) {
		System.out.println("authpresal");
		if(authorize_amount.getText().isEmpty() || authorize_sequenceNumber.getText().isEmpty() || authorize_taxOthers.getText().isEmpty() || authorize_timeout.getText().isEmpty()){
			JOptionPane.showMessageDialog(null, "Every Field should have a value");
		} else {
			try {
				((CAT) service).authorizePreSales(Integer.parseInt(authorize_sequenceNumber.getText()),
						Long.parseLong(authorize_amount.getText()), 
						Long.parseLong(authorize_taxOthers.getText()),
						Integer.parseInt(authorize_timeout.getText()));
			} catch (NumberFormatException e1) {
				JOptionPane.showMessageDialog(null, e1.getMessage());
				e1.printStackTrace();
			} catch (JposException e1) {
				JOptionPane.showMessageDialog(null, e1.getMessage());
				e1.printStackTrace();
			}
		}
	}

	@FXML
	public void handleAuthorizeVoid(ActionEvent e) {
		System.out.println("authVoid");
		if(authorize_amount.getText().isEmpty() || authorize_sequenceNumber.getText().isEmpty() || authorize_taxOthers.getText().isEmpty() || authorize_timeout.getText().isEmpty()){
			JOptionPane.showMessageDialog(null, "Every Field should have a value");
		} else {
			try {
				((CAT) service).authorizeVoid(Integer.parseInt(authorize_sequenceNumber.getText()),
						Long.parseLong(authorize_amount.getText()), 
						Long.parseLong(authorize_taxOthers.getText()),
						Integer.parseInt(authorize_timeout.getText()));
			} catch (NumberFormatException e1) {
				JOptionPane.showMessageDialog(null, e1.getMessage());
				e1.printStackTrace();
			} catch (JposException e1) {
				JOptionPane.showMessageDialog(null, e1.getMessage());
				e1.printStackTrace();
			}
		}
	}

	@FXML
	public void handleAuthorizeSales(ActionEvent e) {
		System.out.println("authsale");
		if(authorize_amount.getText().isEmpty() || authorize_sequenceNumber.getText().isEmpty() || authorize_taxOthers.getText().isEmpty() || authorize_timeout.getText().isEmpty()){
			JOptionPane.showMessageDialog(null, "Every Field should have a value");
		} else {
			try {
				((CAT) service).authorizeSales(Integer.parseInt(authorize_sequenceNumber.getText()),
						Long.parseLong(authorize_amount.getText()), 
						Long.parseLong(authorize_taxOthers.getText()),
						Integer.parseInt(authorize_timeout.getText()));
			} catch (NumberFormatException e1) {
				JOptionPane.showMessageDialog(null, e1.getMessage());
				e1.printStackTrace();
			} catch (JposException e1) {
				JOptionPane.showMessageDialog(null, e1.getMessage());
				e1.printStackTrace();
			}
		}
	}

	@FXML
	public void handleAuthorizeRefund(ActionEvent e) {
		System.out.println("authRef");
		if(authorize_amount.getText().isEmpty() || authorize_sequenceNumber.getText().isEmpty() || authorize_taxOthers.getText().isEmpty() || authorize_timeout.getText().isEmpty()){
			JOptionPane.showMessageDialog(null, "Every Field should have a value");
		} else {
			try {
				((CAT) service).authorizeRefund(Integer.parseInt(authorize_sequenceNumber.getText()),
						Long.parseLong(authorize_amount.getText()), 
						Long.parseLong(authorize_taxOthers.getText()),
						Integer.parseInt(authorize_timeout.getText()));
			} catch (NumberFormatException e1) {
				JOptionPane.showMessageDialog(null, e1.getMessage());
				e1.printStackTrace();
			} catch (JposException e1) {
				JOptionPane.showMessageDialog(null, e1.getMessage());
				e1.printStackTrace();
			}
		}
	}

	@FXML
	public void handleAuthorizeVoidPreSales(ActionEvent e) {
		System.out.println("authVoidPresal");
		if(authorize_amount.getText().isEmpty() || authorize_sequenceNumber.getText().isEmpty() || authorize_taxOthers.getText().isEmpty() || authorize_timeout.getText().isEmpty()){
			JOptionPane.showMessageDialog(null, "Every Field should have a value");
		} else {
			try {
				((CAT) service).authorizeVoidPreSales(Integer.parseInt(authorize_sequenceNumber.getText()),
						Long.parseLong(authorize_amount.getText()), 
						Long.parseLong(authorize_taxOthers.getText()),
						Integer.parseInt(authorize_timeout.getText()));
			} catch (NumberFormatException e1) {
				JOptionPane.showMessageDialog(null, e1.getMessage());
				e1.printStackTrace();
			} catch (JposException e1) {
				JOptionPane.showMessageDialog(null, e1.getMessage());
				e1.printStackTrace();
			}
		}
	}

	@FXML
	public void handleCashDeposit(ActionEvent e) {
		System.out.println("cashDepo");
		if(cashDeposit_amount.getText().isEmpty() || cashDeposit_sequenceNumber.getText().isEmpty() || cashDeposit_timeout.getText().isEmpty()){
			JOptionPane.showMessageDialog(null, "Every Field should have a value");
		} else {
			try {
				((CAT)service).cashDeposit(Integer.parseInt(cashDeposit_sequenceNumber.getText()), Long.parseLong(cashDeposit_amount.getText()), Integer.parseInt(cashDeposit_timeout.getText()));
			} catch (NumberFormatException e1) {
				JOptionPane.showMessageDialog(null, e1.getMessage());
				e1.printStackTrace();
			} catch (JposException e1) {
				JOptionPane.showMessageDialog(null, e1.getMessage());
				e1.printStackTrace();
			}
		}
	}

	@FXML
	public void handleCheckCard(ActionEvent e) {
		System.out.println("checkcard");
		if(checkCard_sequenceNumber.getText().isEmpty() || checkCard_timeout.getText().isEmpty()){
			JOptionPane.showMessageDialog(null, "Every Field should have a value");
		} else {
			try {
				((CAT)service).checkCard(Integer.parseInt(checkCard_sequenceNumber.getText()), Integer.parseInt(checkCard_timeout.getText()));
			} catch (NumberFormatException e1) {
				JOptionPane.showMessageDialog(null, e1.getMessage());
				e1.printStackTrace();
			} catch (JposException e1) {
				JOptionPane.showMessageDialog(null, e1.getMessage());
				e1.printStackTrace();
			}
		}
	}

	@FXML
	public void handleLockTerminal(ActionEvent e) {
		System.out.println("lock");
		try {
			((CAT)service).lockTerminal();
		} catch (JposException e1) {
			JOptionPane.showMessageDialog(null, e1.getMessage());
			e1.printStackTrace();
		}
	}

	@FXML
	public void handleUnlockTerminal(ActionEvent e) {
		System.out.println("unlock");
		try {
			((CAT)service).unlockTerminal();
		} catch (JposException e1) {
			JOptionPane.showMessageDialog(null, e1.getMessage());
			e1.printStackTrace();
		}
	}

	/*
	 * Set up ComboBoxes
	 */
	private void setUpPaymentMedia() {
		paymentMedia.getItems().clear();
		paymentMedia.getItems().add(CATConstantMapper.CAT_MEDIA_UNSPECIFIED.getConstant());
		paymentMedia.getItems().add(CATConstantMapper.CAT_MEDIA_NONDEFINE.getConstant());
		paymentMedia.getItems().add(CATConstantMapper.CAT_MEDIA_CREDIT.getConstant());
		paymentMedia.getItems().add(CATConstantMapper.CAT_MEDIA_DEBIT.getConstant());
		paymentMedia.getItems().add(CATConstantMapper.CAT_MEDIA_ELECTRONIC_MONEY.getConstant());
		paymentMedia.setValue(CATConstantMapper.CAT_MEDIA_UNSPECIFIED.getConstant());
	}

	private void setUpTrainingMode() {
		trainingMode.getItems().clear();
		trainingMode.getItems().add(true);
		trainingMode.getItems().add(false);
		trainingMode.setValue(false);

	}

	private void setUpAccessDailyLogType() {
		accessDailyLog_type.getItems().clear();
		accessDailyLog_type.getItems().add(CATConstantMapper.CAT_DL_REPORTING.getConstant());
		accessDailyLog_type.getItems().add(CATConstantMapper.CAT_DL_SETTLEMENT.getConstant());
		accessDailyLog_type.setValue(CATConstantMapper.CAT_DL_REPORTING.getConstant());
	}

	private void setUpComboBoxes() {
		setUpPaymentMedia();
		setUpTrainingMode();
		setUpAccessDailyLogType();
	}

	private void setUpLogicalNameComboBox() {
		if (!LogicalNameGetter.getLogicalNamesByCategory("CAT").isEmpty()) {
			logicalName.setItems(LogicalNameGetter.getLogicalNamesByCategory("CAT"));
		}
	}

}
