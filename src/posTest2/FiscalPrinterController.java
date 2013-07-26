/*
	 * Copyright 2013 NTS New Technology Systems GmbH. All Rights reserved.
	 * NTS PROPRIETARY/CONFIDENTIAL. Use is subject to NTS License Agreement.
	 * Address: Doernbacher Strasse 126, A-4073 Wilhering, Austria
	 * Homepage: www.ntswincash.com
	 */
package posTest2;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import jpos.profile.JposDevCats;

public class FiscalPrinterController implements Initializable {
		
		
	@FXML private ComboBox<String> logicalName;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		setUpLogicalNameComboBox();
		
	}

	
	private void setUpLogicalNameComboBox(){
		logicalName.setItems(LogicalNameGetter.getLogicalNamesByCategory(JposDevCats.LINEDISPLAY_DEVCAT.toString()));
	}
	
	
	/* ************************************************************************
	 ************************* Action Handler *********************************
	 *************************************************************************/
	
	/*
	 * Action Handler
	 */
	@FXML
	public void handle(ActionEvent e) {
		//Event ...

    }
	
	


}

	