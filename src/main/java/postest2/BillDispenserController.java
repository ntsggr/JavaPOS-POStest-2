package postest2;

import java.awt.Dimension;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
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

import jpos.BillDispenser;
import jpos.JposException;

import org.apache.xerces.parsers.DOMParser;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

public class BillDispenserController extends CommonController implements Initializable {

	@FXML
	@RequiredState(JposState.ENABLED)
	public Pane functionPane;

	// Controls
	@FXML
	@RequiredState(JposState.OPENED)
	public CheckBox asyncMode;

	@FXML
	public ComboBox<String> currencyCode;
	@FXML
	public ComboBox<Integer> currentExit;

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
		setUpLogicalNameComboBox("BillDispenser");
	}

	/* ************************************************************************
	 * ************************ Action Handler *********************************
	 * ***********************************************************************
	 */

	@FXML
	public void handleDeviceEnable(ActionEvent e) {
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

	@Override
	@FXML
	public void handleOCE(ActionEvent e) {
		super.handleOCE(e);
		deviceEnabled.setSelected(true);
		handleDeviceEnable(e);
	}

	@FXML
	public void handleAsyncMode(ActionEvent e) {
		try {
			((BillDispenser) service).setAsyncMode(asyncMode.isSelected());
		} catch (JposException je) {
			JOptionPane.showMessageDialog(null, je.getMessage());
		}
	}

	@FXML
	public void handleSetCurrencyCode(ActionEvent e) {
		try {
			((BillDispenser) service).setCurrencyCode(currencyCode.getSelectionModel().getSelectedItem());
		} catch (JposException e1) {
			JOptionPane.showMessageDialog(null, e1.getMessage());
			e1.printStackTrace();
		}
	}

	@FXML
	public void handleSetCurrentExit(ActionEvent e) {
		try {
			((BillDispenser) service).setCurrentExit(currentExit.getSelectionModel().getSelectedItem());
		} catch (JposException e1) {
			JOptionPane.showMessageDialog(null, e1.getMessage());
			e1.printStackTrace();
		}
	}

	@FXML
	public void handleAdjustCashCounts(ActionEvent e) {
		if (!adjustCashCounts.getText().isEmpty()) {
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
		if (!adjustCashCounts.getText().isEmpty()) {
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

	/**
	 * Shows statistics of device if they are supported by the device
	 */
	@Override
	@FXML
	public void handleInfo(ActionEvent e) {
		try {
			IMapWrapper bdcm = new BillDispenserConstantMapper();
			String msg = DeviceProperties.getProperties(service, bdcm);
			JTextArea jta = new JTextArea(msg);
			@SuppressWarnings("serial")
			JScrollPane jsp = new JScrollPane(jta) {
				@Override
				public Dimension getPreferredSize() {
					return new Dimension(460, 390);
				}
			};
			JOptionPane.showMessageDialog(null, jsp, "Information", JOptionPane.INFORMATION_MESSAGE);

		} catch (Exception jpe) {
			JOptionPane.showMessageDialog(null, "Exception in Info\nException: " + jpe.getMessage(),
					"Exception", JOptionPane.ERROR_MESSAGE);
			System.err.println("Jpos exception " + jpe);
		}
	}

	/**
	 * Shows statistics of device if they are supported by the device
	 */
	@Override
	@FXML
	public void handleStatistics(ActionEvent e) {
		String[] stats = new String[] { "", "U_", "M_" };
		try {
			((BillDispenser) service).retrieveStatistics(stats);
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
		for (int i = 0; i < currencies.length; i++) {
			currencyCode.getItems().add(currencies[i]);
		}
		currencyCode.setValue(currencies[0]);

	}

	private void setUpCurrentExit() {
		currentExit.getItems().clear();
		try {
			for (int i = 1; i <= ((BillDispenser) service).getDeviceExits(); i++) {
				currentExit.getItems().add(i);
			}
		} catch (JposException e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
			e.printStackTrace();
		}
		currentExit.setValue(1);

	}

	private void setUpComboBoxes() {
		setUpCurrencyCode();
		setUpCurrentExit();
	}

}