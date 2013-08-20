package postest2;


public class CheckScannerConstantMapper {

	
	 /////////////////////////////////////////////////////////////////////
    // "Color" Capability Constants
    /////////////////////////////////////////////////////////////////////

    public static final ConstantConverter CHK_CL_MONO      = new ConstantConverter( 1, "CHK_CL_MONO");
    public static final ConstantConverter CHK_CL_GRAYSCALE = new ConstantConverter( 2, "CHK_CL_GRAYSCALE");
    public static final ConstantConverter CHK_CL_16        = new ConstantConverter( 3, "CHK_CL_16");
    public static final ConstantConverter CHK_CL_256       = new ConstantConverter( 4, "CHK_CL_256");
    public static final ConstantConverter CHK_CL_FULL      = new ConstantConverter( 5, "CHK_CL_FULL");
    
    /////////////////////////////////////////////////////////////////////
    // "ImageFormat" Property Constants
    /////////////////////////////////////////////////////////////////////

    public static final ConstantConverter CHK_IF_NATIVE = new ConstantConverter( 1, "CHK_IF_NATIVE");
    public static final ConstantConverter CHK_IF_TIFF   = new ConstantConverter( 2, "CHK_IF_TIFF");
    public static final ConstantConverter CHK_IF_BMP    = new ConstantConverter( 3, "CHK_IF_BMP");
    public static final ConstantConverter CHK_IF_JPEG   = new ConstantConverter( 4, "CHK_IF_JPEG");
    public static final ConstantConverter CHK_IF_GIF    = new ConstantConverter( 5, "CHK_IF_GIF");

    /////////////////////////////////////////////////////////////////////
    // "ImageMemoryStatus" Property Constants
    /////////////////////////////////////////////////////////////////////

    public static final ConstantConverter CHK_IMS_EMPTY = new ConstantConverter( 1, "CHK_IMS_EMPTY");
    public static final ConstantConverter CHK_IMS_OK    = new ConstantConverter( 2, "CHK_IMS_OK");
    public static final ConstantConverter CHK_IMS_FULL  = new ConstantConverter( 3, "CHK_IMS_FULL");

    /////////////////////////////////////////////////////////////////////
    // "MapMode" Property Constants
    /////////////////////////////////////////////////////////////////////

    public static final ConstantConverter CHK_MM_DOTS          = new ConstantConverter( 1, "CHK_MM_DOTS");
    public static final ConstantConverter CHK_MM_TWIPS         = new ConstantConverter( 2, "CHK_MM_TWIPS");
    public static final ConstantConverter CHK_MM_ENGLISH       = new ConstantConverter( 3, "CHK_MM_ENGLISH");
    public static final ConstantConverter CHK_MM_METRIC        = new ConstantConverter( 4, "CHK_MM_METRIC");

    /////////////////////////////////////////////////////////////////////
    // "clearImage" Method Constants:
    /////////////////////////////////////////////////////////////////////

    //   "by" Parameter
    public static final ConstantConverter CHK_CLR_ALL             = new ConstantConverter( 1, "CHK_CLR_ALL");
    public static final ConstantConverter CHK_CLR_BY_FILEID       = new ConstantConverter( 2, "CHK_CLR_BY_FILEID");
    public static final ConstantConverter CHK_CLR_BY_FILEINDEX    = new ConstantConverter( 3, "CHK_CLR_BY_FILEINDEX");
    public static final ConstantConverter CHK_CLR_BY_IMAGETAGDATA = new ConstantConverter( 4, "CHK_CLR_BY_IMAGETAGDATA");

    /////////////////////////////////////////////////////////////////////
    // "defineCropArea" Method Constants:
    /////////////////////////////////////////////////////////////////////

    // "cropAreaID" Parameter or index number
    public static final ConstantConverter CHK_CROP_AREA_ENTIRE_IMAGE = new ConstantConverter( -1, "CHK_CROP_AREA_ENTIRE_IMAGE");
    public static final ConstantConverter CHK_CROP_AREA_RESET_ALL    = new ConstantConverter( -2, "CHK_CROP_AREA_RESET_ALL");

    // "cx" Parameter or integer width
    public static final ConstantConverter CHK_CROP_AREA_RIGHT        = new ConstantConverter( -1, "CHK_CROP_AREA_RIGHT");

    // "cy" Parameter or integer height
    public static final ConstantConverter CHK_CROP_AREA_BOTTOM       = new ConstantConverter( -1, "CHK_CROP_AREA_BOTTOM");

    /////////////////////////////////////////////////////////////////////
    // "retrieveMemory" Method Constants:
    /////////////////////////////////////////////////////////////////////

    // "by" Parameter
    public static final ConstantConverter CHK_LOCATE_BY_FILEID       = new ConstantConverter( 1, "CHK_LOCATE_BY_FILEID");
    public static final ConstantConverter CHK_LOCATE_BY_FILEINDEX    = new ConstantConverter( 2, "CHK_LOCATE_BY_FILEINDEX");
    public static final ConstantConverter CHK_LOCATE_BY_IMAGETAGDATA = new ConstantConverter( 3, "CHK_LOCATE_BY_IMAGETAGDATA");
    
    
    
    
    public static int getConstantNumberFromString(String constant){

		if(constant.equals(CheckScannerConstantMapper.CHK_CL_16.getConstant())) {
			return CheckScannerConstantMapper.CHK_CL_16.getContantNumber();
		}
		
		if(constant.equals(CheckScannerConstantMapper.CHK_CL_256.getConstant())) {
			return CheckScannerConstantMapper.CHK_CL_256.getContantNumber();
		}

		if(constant.equals(CheckScannerConstantMapper.CHK_CL_FULL.getConstant())) {
			return CheckScannerConstantMapper.CHK_CL_FULL.getContantNumber();
		}

		if(constant.equals(CheckScannerConstantMapper.CHK_CL_GRAYSCALE.getConstant())) {
			return CheckScannerConstantMapper.CHK_CL_GRAYSCALE.getContantNumber();
		}

		if(constant.equals(CheckScannerConstantMapper.CHK_CL_MONO.getConstant())) {
			return CheckScannerConstantMapper.CHK_CL_MONO.getContantNumber();
		}
		
		if(constant.equals(CheckScannerConstantMapper.CHK_CLR_ALL.getConstant())) {
			return CheckScannerConstantMapper.CHK_CLR_ALL.getContantNumber();
		}

		if(constant.equals(CheckScannerConstantMapper.CHK_CLR_BY_FILEID.getConstant())) {
			return CheckScannerConstantMapper.CHK_CLR_BY_FILEID.getContantNumber();
		}
		
		if(constant.equals(CheckScannerConstantMapper.CHK_CLR_BY_FILEINDEX.getConstant())) {
			return CheckScannerConstantMapper.CHK_CLR_BY_FILEINDEX.getContantNumber();
		}
		
		if(constant.equals(CheckScannerConstantMapper.CHK_CLR_BY_IMAGETAGDATA.getConstant())) {
			return CheckScannerConstantMapper.CHK_CLR_BY_IMAGETAGDATA.getContantNumber();
		}
		
		if(constant.equals(CheckScannerConstantMapper.CHK_CROP_AREA_BOTTOM.getConstant())) {
			return CheckScannerConstantMapper.CHK_CROP_AREA_BOTTOM.getContantNumber();
		}
		
		if(constant.equals(CheckScannerConstantMapper.CHK_CROP_AREA_ENTIRE_IMAGE.getConstant())) {
			return CheckScannerConstantMapper.CHK_CROP_AREA_ENTIRE_IMAGE.getContantNumber();
		}
		
		if(constant.equals(CheckScannerConstantMapper.CHK_CROP_AREA_RESET_ALL.getConstant())) {
			return CheckScannerConstantMapper.CHK_CROP_AREA_RESET_ALL.getContantNumber();
		}
		
		if(constant.equals(CheckScannerConstantMapper.CHK_CROP_AREA_RIGHT.getConstant())) {
			return CheckScannerConstantMapper.CHK_CROP_AREA_RIGHT.getContantNumber();
		}
		
		if(constant.equals(CheckScannerConstantMapper.CHK_IF_BMP.getConstant())) {
			return CheckScannerConstantMapper.CHK_IF_BMP.getContantNumber();
		}
		
		if(constant.equals(CheckScannerConstantMapper.CHK_IF_GIF.getConstant())) {
			return CheckScannerConstantMapper.CHK_IF_GIF.getContantNumber();
		}
		
		if(constant.equals(CheckScannerConstantMapper.CHK_IF_JPEG.getConstant())) {
			return CheckScannerConstantMapper.CHK_IF_JPEG.getContantNumber();
		}
		
		if(constant.equals(CheckScannerConstantMapper.CHK_IF_NATIVE.getConstant())) {
			return CheckScannerConstantMapper.CHK_IF_NATIVE.getContantNumber();
		}
		
		if(constant.equals(CheckScannerConstantMapper.CHK_IF_TIFF.getConstant())) {
			return CheckScannerConstantMapper.CHK_IF_TIFF.getContantNumber();
		}
		
		if(constant.equals(CheckScannerConstantMapper.CHK_IMS_EMPTY.getConstant())) {
			return CheckScannerConstantMapper.CHK_IMS_EMPTY.getContantNumber();
		}
		
		if(constant.equals(CheckScannerConstantMapper.CHK_IMS_FULL.getConstant())) {
			return CheckScannerConstantMapper.CHK_IMS_FULL.getContantNumber();
		}
		
		if(constant.equals(CheckScannerConstantMapper.CHK_IMS_OK.getConstant())) {
			return CheckScannerConstantMapper.CHK_IMS_OK.getContantNumber();
		}
		
		if(constant.equals(CheckScannerConstantMapper.CHK_LOCATE_BY_FILEID.getConstant())) {
			return CheckScannerConstantMapper.CHK_LOCATE_BY_FILEID.getContantNumber();
		}
		
		if(constant.equals(CheckScannerConstantMapper.CHK_LOCATE_BY_FILEINDEX.getConstant())) {
			return CheckScannerConstantMapper.CHK_LOCATE_BY_FILEINDEX.getContantNumber();
		}
		
		if(constant.equals(CheckScannerConstantMapper.CHK_LOCATE_BY_IMAGETAGDATA.getConstant())) {
			return CheckScannerConstantMapper.CHK_LOCATE_BY_IMAGETAGDATA.getContantNumber();
		}
		
		if(constant.equals(CheckScannerConstantMapper.CHK_MM_DOTS.getConstant())) {
			return CheckScannerConstantMapper.CHK_MM_DOTS.getContantNumber();
		}
		
		if(constant.equals(CheckScannerConstantMapper.CHK_MM_ENGLISH.getConstant())) {
			return CheckScannerConstantMapper.CHK_MM_ENGLISH.getContantNumber();
		}
		
		if(constant.equals(CheckScannerConstantMapper.CHK_MM_METRIC.getConstant())) {
			return CheckScannerConstantMapper.CHK_MM_METRIC.getContantNumber();
		}
		
		if(constant.equals(CheckScannerConstantMapper.CHK_MM_TWIPS.getConstant())) {
			return CheckScannerConstantMapper.CHK_MM_TWIPS.getContantNumber();
		}
		
		return Integer.parseInt(constant);
	}
	
    
    
    
	
}
