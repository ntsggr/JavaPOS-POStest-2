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
import javafx.scene.control.TabPane;
import javafx.scene.control.TextField;

import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import jpos.HardTotals;
import jpos.JposException;

import org.apache.xerces.parsers.DOMParser;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

public class HardTotalsController extends CommonController implements Initializable {

	@FXML
	@RequiredState(JposState.ENABLED)
	public TabPane functionPane;

	@FXML
	public TextField claimFile_hTotalsFile;
	@FXML
	public TextField claimFile_timeout;
	@FXML
	public TextField create_filename;
	@FXML
	public TextField create_size;
	@FXML
	public TextField create_hTotalsFile;
	@FXML
	public TextField delete_filename;
	@FXML
	public TextField read_hTotalsFile;
	@FXML
	public TextField read_offset;
	@FXML
	public TextField read_count;
	@FXML
	public TextField read_data;
	@FXML
	public TextField releaseFile_hTotalsFile;
	@FXML
	public TextField rename_hTotalsFile;
	@FXML
	public TextField rename_filename;
	@FXML
	public TextField find_filename;
	@FXML
	public TextField find_hTotalsFile;
	@FXML
	public TextField find_size;
	@FXML
	public TextField findByIndex_index;
	@FXML
	public TextField findByIndex_filename;
	@FXML
	public TextField recalculateValidationData_hTotalsFile;
	@FXML
	public TextField setAll_hTotalsFile;
	@FXML
	public TextField setAll_value;
	@FXML
	public TextField validateData_hTotalsFile;

	@FXML
	public ComboBox<Boolean> create_errorDetection;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		service = new HardTotals();
		RequiredStateChecker.invokeThis(this, service);
		setUpLogicalNameComboBox("HardTotals");
	}

	/* ************************************************************************
	 * ************************ Action Handler *********************************
	 * ***********************************************************************
	 */

	@FXML
	public void handleDeviceEnable(ActionEvent e) {
		try {
			if (deviceEnabled.isSelected()) {
				((HardTotals) service).setDeviceEnabled(true);
				setUpComboBoxes();
			} else {
				((HardTotals) service).setDeviceEnabled(false);
			}
			RequiredStateChecker.invokeThis(this, service);
		} catch (JposException je) {
			System.err.println("HardTotalsPanel: CheckBoxListener: Jpos Exception" + je);
		}
	}

	@Override
	@FXML
	public void handleOCE(ActionEvent e) {
		super.handleOCE(e);
		deviceEnabled.setSelected(true);
		handleDeviceEnable(e);
	}

	/**
	 * Shows statistics of device if they are supported by the device
	 */
	@Override
	@FXML
	public void handleInfo(ActionEvent e) {
		try {
			String msg = DeviceProperties.getProperties(service, null);
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
			JOptionPane.showMessageDialog(null, "Exception in Info\nException: " + jpe.getMessage(), "Exception",
					JOptionPane.ERROR_MESSAGE);
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
			((HardTotals) service).retrieveStatistics(stats);
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
	public void handleClaimFile(ActionEvent e) {
		if(claimFile_hTotalsFile.getText().isEmpty() || claimFile_timeout.getText().isEmpty()){
			JOptionPane.showMessageDialog(null, "Every Field should have a value!");
		} else {
			try {
				((HardTotals)service).claimFile(Integer.parseInt(claimFile_hTotalsFile.getText()), Integer.parseInt(claimFile_timeout.getText()));
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
	public void handleCreate(ActionEvent e) {
		if(create_filename.getText().isEmpty() || create_size.getText().isEmpty()){
			JOptionPane.showMessageDialog(null, "Every Field should have a value!");
		} else {
			int[] totalsFile = new int[1];
			try {
				((HardTotals)service).create(create_filename.getText(), totalsFile, Integer.parseInt(create_size.getText()), create_errorDetection.getSelectionModel().getSelectedItem());
				create_hTotalsFile.setText("" + totalsFile[0]);
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
	public void handleDelete(ActionEvent e) {
		if(delete_filename.getText().isEmpty()){
			JOptionPane.showMessageDialog(null, "Field should have a value!");
		} else {
			try {
				((HardTotals)service).delete(delete_filename.getText());
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
	public void handleRead(ActionEvent e) {
		if(read_hTotalsFile.getText().isEmpty() || read_offset.getText().isEmpty() || read_count.getText().isEmpty()){
			JOptionPane.showMessageDialog(null, "Every Field should have a value!");
		} else {
			byte[] data = new byte[Integer.parseInt(read_count.getText())];
			try {
				((HardTotals)service).read(Integer.parseInt(read_hTotalsFile.getText()), data, Integer.parseInt(read_offset.getText()), Integer.parseInt(read_count.getText()));
				String dataNew = "";
				for(int i = 0; i < data.length; i++){
					dataNew += data[i];
				}
				read_data.setText(dataNew);
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
	public void handleReleaseFile(ActionEvent e) {
		if(releaseFile_hTotalsFile.getText().isEmpty()){
			JOptionPane.showMessageDialog(null, "Field should have a value!");
		} else {
			try {
				((HardTotals)service).releaseFile(Integer.parseInt(releaseFile_hTotalsFile.getText()));
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
	public void handleRename(ActionEvent e) {
		if(rename_hTotalsFile.getText().isEmpty() || rename_filename.getText().isEmpty()){
			JOptionPane.showMessageDialog(null, "Every Field should have a value!");
		} else {
			try {
				((HardTotals)service).rename(Integer.parseInt(rename_hTotalsFile.getText()), rename_filename.getText());
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
	public void handleBeginTrans(ActionEvent e) {
		try {
			((HardTotals)service).beginTrans();
		} catch (JposException e1) {
			JOptionPane.showMessageDialog(null, e1.getMessage());
			e1.printStackTrace();
		}
	}
	
	@FXML
	public void handleCommitTrans(ActionEvent e) {
		try {
			((HardTotals)service).commitTrans();
		} catch (JposException e1) {
			JOptionPane.showMessageDialog(null, e1.getMessage());
			e1.printStackTrace();
		}
	}
	
	@FXML
	public void handleRollback(ActionEvent e) {
		try {
			((HardTotals)service).rollback();
		} catch (JposException e1) {
			JOptionPane.showMessageDialog(null, e1.getMessage());
			e1.printStackTrace();
		}
	}
	
	@FXML
	public void handleFind(ActionEvent e) {
		if(find_filename.getText().isEmpty()){
			JOptionPane.showMessageDialog(null, "Field should have a value!");
		} else {
			int[] size = new int[1];
			int[] file = new int[1];
			try {
				((HardTotals)service).find(find_filename.getText(),file, size);
				find_hTotalsFile.setText("" + file[0]);
				find_size.setText("" + size[0]);
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
	public void handleFindByIndex(ActionEvent e) {
		if(findByIndex_index.getText().isEmpty()){
			JOptionPane.showMessageDialog(null, "Field should have a value!");
		} else {
			String[] filename = new String[1];
			try {
				((HardTotals)service).findByIndex(Integer.parseInt(findByIndex_index.getText()), filename);
				findByIndex_filename.setText("" + filename[0]);
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
	public void handleRecalculateValidationData(ActionEvent e) {
		if(recalculateValidationData_hTotalsFile.getText().isEmpty()){
			JOptionPane.showMessageDialog(null, "Field should have a value!");
		} else {
			try {
				((HardTotals)service).recalculateValidationData(Integer.parseInt(recalculateValidationData_hTotalsFile.getText()));
			} catch (JposException e1) {
				JOptionPane.showMessageDialog(null, e1.getMessage());
				e1.printStackTrace();
			}
		}
	}
	
	@FXML
	public void handleSetAll(ActionEvent e) {
		if(setAll_hTotalsFile.getText().isEmpty() || setAll_value.getText().isEmpty()){
			JOptionPane.showMessageDialog(null, "Every Field should have a value!");
		} else {
			try {
				((HardTotals)service).setAll(Integer.parseInt(setAll_hTotalsFile.getText()), Byte.parseByte(setAll_value.getText()));
			} catch (JposException e1) {
				JOptionPane.showMessageDialog(null, e1.getMessage());
				e1.printStackTrace();
			} catch (NumberFormatException e1) {
				JOptionPane.showMessageDialog(null, e1.getMessage());
				e1.printStackTrace();
			}
		}
	}

	@FXML
	public void handleValidateData(ActionEvent e) {
		if(validateData_hTotalsFile.getText().isEmpty()){
			JOptionPane.showMessageDialog(null, "Field should have a value!");
		} else {
			try {
				((HardTotals)service).validateData(Integer.parseInt(validateData_hTotalsFile.getText()));
			} catch (JposException e1) {
				JOptionPane.showMessageDialog(null, e1.getMessage());
				e1.printStackTrace();
			} catch (NumberFormatException e1) {
				JOptionPane.showMessageDialog(null, e1.getMessage());
				e1.printStackTrace();
			}
		}
	}
	
	
	/*
	 * ComboBoxes
	 */
	
	private void setUpCreateErrorDetection(){
		create_errorDetection.getItems().clear();
		create_errorDetection.getItems().add(true);
		create_errorDetection.getItems().add(false);
		create_errorDetection.setValue(true);
	}
	
	private void setUpComboBoxes(){
		setUpCreateErrorDetection();
	}
	
	
	

}
