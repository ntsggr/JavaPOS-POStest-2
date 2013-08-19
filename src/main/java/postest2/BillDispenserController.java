/*
 * Copyright 2013 NTS New Technology Systems GmbH. All Rights reserved.
 * NTS PROPRIETARY/CONFIDENTIAL. Use is subject to NTS License Agreement.
 * Address: Doernbacher Strasse 126, A-4073 Wilhering, Austria
 * Homepage: www.ntswincash.com
 */
package postest2;

import java.net.URL;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import jpos.BillDispenser;
import jpos.Biometrics;
import jpos.JposException;

public class BillDispenserController extends CommonController implements Initializable {

	@FXML
	@RequiredState(JposState.ENABLED)
	public Pane functionPane;

	// Controls
	@FXML @RequiredState(JposState.OPENED)
	public CheckBox asyncMode;
	
	@FXML
	public ComboBox<String> currencyCode;
	@FXML
	public ComboBox<Integer> currentExit;

	Biometrics b;
	@FXML
	public Label readCashCount_cashCount;
	@FXML
	public Label readCashCount_discrepancy;

	@FXML
	public TextField adjustCashCounts;
	@FXML
	public TextField dispenseCash_cashCounts;

	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		this.service = new BillDispenser();
		RequiredStateChecker.invokeThis(this, service);

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
				((BillDispenser) service).setDeviceEnabled(true);
				setUpComboBoxes();

			} else {
				((BillDispenser) service).setDeviceEnabled(false);
			}
		} catch (JposException je) {
			JOptionPane.showMessageDialog(null, je.getMessage());
		}
		RequiredStateChecker.invokeThis(this, service);
	}
	
	@FXML
	public void handleAsyncMode(ActionEvent e) {
		System.out.println("asyncMode");
		try {
			((BillDispenser)service).setAsyncMode(asyncMode.isSelected());
		} catch (JposException je) {
			JOptionPane.showMessageDialog(null, je.getMessage());
		}
	}
	
	
	@FXML
	public void handleSetCurrencyCode(ActionEvent e) {
		System.out.println("currencyCode");
		try {
			((BillDispenser) service).setCurrencyCode(currencyCode.getSelectionModel().getSelectedItem());
		} catch (JposException e1) {
			JOptionPane.showMessageDialog(null, e1.getMessage());
			e1.printStackTrace();
		}
	}
	
	@FXML
	public void handleSetCurrentExit(ActionEvent e) {
		System.out.println("currenctExit");
		try {
			((BillDispenser) service).setCurrentExit(currentExit.getSelectionModel().getSelectedItem());
		} catch (JposException e1) {
			JOptionPane.showMessageDialog(null, e1.getMessage());
			e1.printStackTrace();
		}
	}
	

	@FXML
	public void handleAdjustCashCounts(ActionEvent e) {
		System.out.println("adjust");
		if(!adjustCashCounts.getText().isEmpty()){
			try {
				((BillDispenser) service).adjustCashCounts(adjustCashCounts.getText());
			} catch (JposException e1) {
				JOptionPane.showMessageDialog(null, e1.getMessage());
				e1.printStackTrace();
			}
		}
	}
	
	@FXML
	public void handleDispenseCash(ActionEvent e) {
		System.out.println("dispenseCash");
		if(!adjustCashCounts.getText().isEmpty()){
			try {
				((BillDispenser) service).adjustCashCounts(adjustCashCounts.getText());
			} catch (JposException e1) {
				JOptionPane.showMessageDialog(null, e1.getMessage());
				e1.printStackTrace();
			}
		}
	}
	
	@FXML
	public void handleReadCashCount(ActionEvent e) {
		System.out.println("readCashCount");
		String[] cashCounts = new String[1];
		boolean[] discrepancy = new boolean[1];
		try {
			((BillDispenser) service).readCashCounts(cashCounts, discrepancy);
		} catch (JposException e1) {
			JOptionPane.showMessageDialog(null, e1.getMessage());
			e1.printStackTrace();
		}
		this.readCashCount_cashCount.setText(cashCounts[0]);
		this.readCashCount_discrepancy.setText("" + discrepancy[0]);
	}
	
	/*
	 * Set Up all ComboBoxes
	 */
	
	private void setUpCurrencyCode() {
		String[] currencies = null;
		try {
			currencies = ((BillDispenser) service).getCurrencyCodeList().split(",");
		} catch (JposException e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
			e.printStackTrace();
		}
	
		currencyCode.getItems().clear();
		for(int i = 0; i < currencies.length; i++){
			currencyCode.getItems().add(currencies[i]);
		}
		currencyCode.setValue(currencies[0]);
		
	}
	
	private void setUpCurrentExit() {
		currentExit.getItems().clear();
		try {
			for(int i = 1; i <= ((BillDispenser)service).getDeviceExits(); i++){
				currentExit.getItems().add(i);
			}
		} catch (JposException e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
			e.printStackTrace();
		}
		currentExit.setValue(1);
		
	}
	
	private void setUpComboBoxes() {
		setUpLogicalNameComboBox();
		setUpCurrencyCode();
		setUpCurrentExit();
	}

	private void setUpLogicalNameComboBox() {
		if (!LogicalNameGetter.getLogicalNamesByCategory("BillDispenser").isEmpty()) {
			logicalName.setItems(LogicalNameGetter.getLogicalNamesByCategory("BillDispenser"));
		}
	}

	
}