package postest2;

public enum PropertyNames {

	/* *** Belt *** */
	getMotionStatus,
	
	/* *** BillAcceptor *** */
	getDepositStatus,
	getFullStatus,
	
	/* *** BillDispenser *** */
	getDeviceStatus,
	
	/* *** Biometrics *** */
	getCapSensorColor,
	getCapSensorOrientation,
	getSensorType,
	getSensorOrientation,
	getCapSensorType,
	getSensorColor,
	
	/* *** CAT *** */
	getCapDailyLog,
	getLogStatus,
	getPaymentCondition,
	getPaymentMedia,
	getTransactionType,
	
	/* *** CheckScanner *** */
	getImageFormat,
	getCapImageFormat,
	getColor,
	getImageMemoryStatus,
	
	/* *** CoinDispenser *** */
	getDispenserStatus,
	
	/* *** ElectronicJournal *** */
	getCapStation,
	getStation,
	
	/* *** ElectronicValueRW *** */
	getCapCardSensor,
	getCapDetectionControl,
	getDetectionStatus,
	
	/* *** FiscalPrinter *** */
	getActualCurrency,
	getContractorId,
	getCountryCode,
	getDateType,
	getFiscalReceiptStation,
	getFiscalReceiptType,
	getMessageType,
	getPrinterState,
	getSlipSelection,
	getTotalizerType,
	
	/* *** Gate *** */
	getGetStatus,
	
	/* *** Keylock *** */
	getKeyPosition,
	getCapKeylockType,
	
	/* *** Lights *** */
	getCapAlarm,
	
	/* *** MICR *** */
	getCheckType,
	
	/* *** MSR *** */
	getCapDataEncryption,
	getCapDeviceAuthentication,
	getTracksToRead,
	getCardType,
	getDeviceAuthenticationProtocol,
	getErrorReportingType,
	
	/* *** PINPad *** */
	getAvailablePromptsList,
	getCapDisplay,
	getCapLanguage,
	
	/* *** PointCardRW *** */
	getCapTracksToRead, // getCapTracksToWrite
	getCardState,
	
	/* *** POSKeyboard *** */
	getEventTypes,
	getPOSKeyEventType,
	
	/* *** POSPower *** */
	getCapUPSChargeState, // getUPSChargeState
	getPowerSource,
	
	/* *** POSPrinter *** */
	getCapCharacterSet,
	getCapColor, // getCapJrnColor, getCapRecColor, getCapSlpColor
	getCapCartridge, // getCapJrnCartridgeSensor, getCapRecCartridgeSensor, getCapSlpCartridgeSensor, 
					 // getJrnCartridgeState, getRecCartridgeState, getSlpCartridgeState
	getCapRecMarkFeed,
	getCapRuledLine, // getCapRecRuledLine, getCapSlpRuledLine
	getCartridgeNotify,
	getCharacterSet,
	getErrorLevel,
	getPTRStation, // getErrorStation, getPageModeStation
	getMapMode,
	getPageModeDescriptor,
	getPageModePrintDirection,
	getRotateSpecial,
	getSlpPrintSide, 
	
	/* *** RFIDScanner *** */
	getCapMultipleProtocols, // getProtocolMask
	getCapWriteTag,
	
	/* *** Scale *** */
	getStatusNotify,
	getWeightUnit,
	
	/* *** Scanner *** */
	getScanDataType,
	
	/* *** SmartCardRW *** */
	getCapInterfaceMode,
	getInterfaceMode,
	getIsoEmvMode,
	getCapIsoEmvMode,
	getCapTransmissionProtocol,
	getTransmissionProtocol,
	
	/* *** ToneIndicator *** */
	getMelodyType
	
}

