package postest2;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import jpos.profile.JposDevCats;

public class POSPrinterController implements Initializable {

	@FXML
	private ComboBox<String> logicalName;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
	}
	
	private void setUpLogicalNameComboBox() {
		logicalName.setItems(LogicalNameGetter.getLogicalNamesByCategory(JposDevCats.POSPRINTER_DEVCAT.toString()));
	}
	
	/* ************************************************************************
	 * ************************ Action Handler *********************************
	 * ***********************************************************************
	 */

	/*
	 * Action Handler
	 */
	@FXML
	public void handle(ActionEvent e) {
		// Event ...

	}

}