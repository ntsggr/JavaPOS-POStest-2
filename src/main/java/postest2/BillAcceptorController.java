/*
 * Copyright 2013 NTS New Technology Systems GmbH. All Rights reserved.
 * NTS PROPRIETARY/CONFIDENTIAL. Use is subject to NTS License Agreement.
 * Address: Doernbacher Strasse 126, A-4073 Wilhering, Austria
 * Homepage: www.ntswincash.com
 */
package postest2;

import java.awt.Dimension;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;

import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import jpos.BillAcceptor;
import jpos.JposException;

import org.apache.xerces.parsers.DOMParser;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

public class BillAcceptorController extends CommonController implements Initializable {

	@FXML
	@RequiredState(JposState.ENABLED)
	public Pane functionPane;

	// Controls
	@FXML
	public ComboBox<String> currencyCode;
	@FXML
	public ComboBox<Boolean> realTimeDataEnabled;
	@FXML
	public ComboBox<String> endDeposit_success;
	@FXML
	public ComboBox<String> pauseDeposit_control;

	@FXML
	public Label readCashCount_cashCount;
	@FXML
	public Label readCashCount_discrepancy;

	@FXML
	public TextField adjustCashCounts;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		this.service = new BillAcceptor();
		RequiredStateChecker.invokeThis(this, service);
		setUpLogicalNameComboBox();
	}

	/* ************************************************************************
	 * ************************ Action Handler *********************************
	 * ***********************************************************************
	 */
	// Shows statistics of device if they are supported by the device
	@Override
	@FXML
	public void handleInfo(ActionEvent e) {
		try {
			String msg = DeviceProperties.getProperties(service);

			JTextArea jta = new JTextArea(msg);
			JScrollPane jsp = new JScrollPane(jta) {
				@Override
				public Dimension getPreferredSize() {
					return new Dimension(460, 390);
				}
			};
			JOptionPane.showMessageDialog(null, jsp, "Information", JOptionPane.INFORMATION_MESSAGE);

		} catch (Exception jpe) {
			JOptionPane.showMessageDialog(null, "Exception in Info\nException: " + jpe.getMessage(), "Exception",
					JOptionPane.ERROR_MESSAGE);
			System.err.println("Jpos exception " + jpe);
		}
	}

	// Shows statistics of device if they are supported by the device
	@Override
	@FXML
	public void handleStatistics(ActionEvent e) {
		String[] stats = new String[] { "", "U_", "M_" };
		try {
			((BillAcceptor) service).retrieveStatistics(stats);
			DOMParser parser = new DOMParser();
			parser.parse(new InputSource(new java.io.StringReader(stats[1])));

			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder db = dbf.newDocumentBuilder();
			Document doc = db.parse(new ByteArrayInputStream(stats[1].getBytes()));

			printStatistics(doc.getDocumentElement(), "");

			JOptionPane.showMessageDialog(null, statistics, "Statistics", JOptionPane.INFORMATION_MESSAGE);
		} catch (IOException ioe) {
			ioe.printStackTrace();
		} catch (SAXException saxe) {
			saxe.printStackTrace();
		} catch (ParserConfigurationException e1) {
			e1.printStackTrace();
		} catch (JposException jpe) {
			jpe.printStackTrace();
			JOptionPane.showMessageDialog(null, "Statistics are not supported!", "Statistics",
					JOptionPane.ERROR_MESSAGE);
		}

		statistics = "";
	}

	@FXML
	public void handleDeviceEnable(ActionEvent e) {
		System.out.println("DevEnable");
		try {
			if (deviceEnabled.isSelected()) {
				((BillAcceptor) service).setDeviceEnabled(true);
				setUpComboBoxes();

			} else {
				((BillAcceptor) service).setDeviceEnabled(false);
			}
		} catch (JposException je) {
			JOptionPane.showMessageDialog(null, je.getMessage());
		}

		RequiredStateChecker.invokeThis(this, service);
	}

	@Override
	@FXML
	public void handleOCE(ActionEvent e) {
		super.handleOCE(e);
		deviceEnabled.setSelected(true);
		handleDeviceEnable(e);
	}

	@FXML
	public void handleSetCurrencyCode(ActionEvent e) {
		System.out.println("currencyCode");
		try {
			((BillAcceptor) service).setCurrencyCode(currencyCode.getSelectionModel().getSelectedItem());
		} catch (JposException e1) {
			JOptionPane.showMessageDialog(null, e1.getMessage());
			e1.printStackTrace();
		}
	}

	@FXML
	public void handleSetRealTimeDataEnabled(ActionEvent e) {
		System.out.println("realtimedataenabled");
		try {
			((BillAcceptor) service).setRealTimeDataEnabled(realTimeDataEnabled.getSelectionModel().getSelectedItem());
		} catch (JposException e1) {
			JOptionPane.showMessageDialog(null, e1.getMessage());
			e1.printStackTrace();
		}
	}

	@FXML
	public void handleAdjustCashCounts(ActionEvent e) {
		System.out.println("adjust");
		if (!adjustCashCounts.getText().isEmpty()) {
			try {
				((BillAcceptor) service).adjustCashCounts(adjustCashCounts.getText());
			} catch (JposException e1) {
				JOptionPane.showMessageDialog(null, e1.getMessage());
				e1.printStackTrace();
			}
		}
	}

	@FXML
	public void handleBeginDeposit(ActionEvent e) {
		System.out.println("begin");
		try {
			((BillAcceptor) service).beginDeposit();
		} catch (JposException e1) {
			JOptionPane.showMessageDialog(null, e1.getMessage());
			e1.printStackTrace();
		}
	}

	@FXML
	public void handleEndDeposit(ActionEvent e) {
		System.out.println("end");
		try {
			((BillAcceptor) service).endDeposit(BillAcceptorConstantMapper
					.getConstantNumberFromString(endDeposit_success.getSelectionModel().getSelectedItem()));
		} catch (JposException e1) {
			JOptionPane.showMessageDialog(null, e1.getMessage());
			e1.printStackTrace();
		}
	}

	@FXML
	public void handleFixDeposit(ActionEvent e) {
		System.out.println("fix");
		try {
			((BillAcceptor) service).fixDeposit();
		} catch (JposException e1) {
			JOptionPane.showMessageDialog(null, e1.getMessage());
			e1.printStackTrace();
		}
	}

	@FXML
	public void handlePauseDeposit(ActionEvent e) {
		System.out.println("pause");
		try {
			((BillAcceptor) service).pauseDeposit(BillAcceptorConstantMapper
					.getConstantNumberFromString(pauseDeposit_control.getSelectionModel().getSelectedItem()));
		} catch (JposException e1) {
			JOptionPane.showMessageDialog(null, e1.getMessage());
			e1.printStackTrace();
		}
	}

	@FXML
	public void handleReadCashCount(ActionEvent e) {
		System.out.println("readCashCount");
		String[] cashCounts = new String[1];
		boolean[] discrepancy = new boolean[1];
		try {
			((BillAcceptor) service).readCashCounts(cashCounts, discrepancy);
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
			currencies = ((BillAcceptor) service).getDepositCodeList().split(",");
		} catch (JposException e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
			e.printStackTrace();
		}

		currencyCode.getItems().clear();
		for (int i = 0; i < currencies.length; i++) {
			currencyCode.getItems().add(currencies[i]);
		}
		currencyCode.setValue(currencies[0]);

	}

	private void setUpRealTimeDataEnabled() {

		realTimeDataEnabled.getItems().clear();
		realTimeDataEnabled.getItems().add(true);
		realTimeDataEnabled.getItems().add(false);
		realTimeDataEnabled.setValue(true);

	}

	private void setUpEndDepositSuccess() {
		endDeposit_success.getItems().clear();
		endDeposit_success.getItems().add(BillAcceptorConstantMapper.BACC_DEPOSIT_COMPLETE.getConstant());
		endDeposit_success.setValue(BillAcceptorConstantMapper.BACC_DEPOSIT_COMPLETE.getConstant());
	}

	private void setUpPauseDepositControl() {

		pauseDeposit_control.getItems().clear();
		pauseDeposit_control.getItems().add(BillAcceptorConstantMapper.BACC_DEPOSIT_PAUSE.getConstant());
		pauseDeposit_control.getItems().add(BillAcceptorConstantMapper.BACC_DEPOSIT_RESTART.getConstant());
		pauseDeposit_control.setValue(BillAcceptorConstantMapper.BACC_DEPOSIT_PAUSE.getConstant());
	}

	private void setUpComboBoxes() {
		setUpCurrencyCode();
		setUpRealTimeDataEnabled();
		setUpEndDepositSuccess();
		setUpPauseDepositControl();
	}

	private void setUpLogicalNameComboBox() {
		if (!LogicalNameGetter.getLogicalNamesByCategory("BillAcceptor").isEmpty()) {
			logicalName.setItems(LogicalNameGetter.getLogicalNamesByCategory("BillAcceptor"));
		}
	}

}
