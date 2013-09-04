package postest2;

import java.awt.Dimension;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;

import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import jpos.CashDrawer;
import jpos.CashDrawerConst;
import jpos.JposException;
import jpos.events.StatusUpdateEvent;
import jpos.events.StatusUpdateListener;

import org.apache.xerces.parsers.DOMParser;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

public class CashDrawerController extends CommonController implements Initializable, StatusUpdateListener {

	@FXML
	@RequiredState(JposState.ENABLED)
	public TextArea textAreaActionLog;
	@FXML
	@RequiredState(JposState.ENABLED)
	public Button buttonOpenCash;
	@FXML
	@RequiredState(JposState.ENABLED)
	public Button buttonGetDrawer;
	@FXML
	@RequiredState(JposState.ENABLED)
	public Button buttonWaitForDrawer;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		setUpLogicalNameComboBox("CashDrawer");
		service = new CashDrawer();
		((CashDrawer) service).addStatusUpdateListener(this);
		RequiredStateChecker.invokeThis(this, service);
	}

	/* ************************************************************************
	 * ************************ Action Handler *********************************
	 * ***********************************************************************
	 */

	@FXML
	public void handleDeviceEnable(ActionEvent e) {
		try {
			if (deviceEnabled.isSelected()) {
				((CashDrawer) service).setDeviceEnabled(true);
				System.out.println("true");
			} else {
				((CashDrawer) service).setDeviceEnabled(false);
				System.out.println("false");
			}
			RequiredStateChecker.invokeThis(this, service);
		} catch (JposException je) {
			System.err.println("CashDrawerPanel: CheckBoxListener: Jpos Exception" + je);
		}
	}

	@Override
	@FXML
	public void handleOCE(ActionEvent e) {
		super.handleOCE(e);
		handleDeviceEnable(e);
	}

	@FXML
	public void handleOpenCash(ActionEvent e) {
		try {
			((CashDrawer) service).openDrawer();
		} catch (JposException je) {
			JOptionPane.showMessageDialog(null, "Exception in openDrawer: " + je.getMessage(), "Exception",
					JOptionPane.ERROR_MESSAGE);
		}
	}

	@FXML
	public void handleGetDrawer(ActionEvent e) {
		try {
			if (((CashDrawer) service).getDrawerOpened()) {
				textAreaActionLog.appendText("Cash drawer is open.\n");
			} else {
				textAreaActionLog.appendText("Cash drawer is closed.\n");
			}
		} catch (JposException je) {
			JOptionPane.showMessageDialog(null, "Exception in getDrawerOpened: " + je.getMessage(),
					"Exception", JOptionPane.ERROR_MESSAGE);
		}
	}

	@FXML
	public void handleWaitForDrawer(ActionEvent e) {
		try {
			((CashDrawer) service).waitForDrawerClose(100, 500, 100, 200);
			textAreaActionLog.appendText("Cash drawer is closed.\n");
		} catch (JposException je) {
			textAreaActionLog.appendText("Jpos exception " + je + "\n");
			JOptionPane.showMessageDialog(null, "Exception in waitForDrawerClose: " + je.getMessage(),
					"Exception", JOptionPane.ERROR_MESSAGE);
		}
	}

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
			JOptionPane.showMessageDialog(null, "Exception in Info\nException: " + jpe.getMessage(),
					"Exception", JOptionPane.ERROR_MESSAGE);
			System.err.println("Jpos exception " + jpe);
		}
	}

	// Shows statistics of device if they are supported by the device
	@Override
	@FXML
	public void handleStatistics(ActionEvent e) {
		String[] stats = new String[] { "", "U_", "M_" };
		try {
			((CashDrawer) service).retrieveStatistics(stats);
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

	@Override
	public void statusUpdateOccurred(StatusUpdateEvent sue) {
		String msg = "Status Update Event: ";
		statusLabel.setText("" + sue.getStatus());
		switch (sue.getStatus()) {
		case CashDrawerConst.CASH_SUE_DRAWERCLOSED:
			msg += "Drawer Closed\n";
			break;
		case CashDrawerConst.CASH_SUE_DRAWEROPEN:
			msg += "Drawer Opened\n";
			break;
		default:
			msg += "Unknown Status: " + Integer.toString(sue.getStatus()) + "\n";
			break;
		}
	}


}