package pageUIs;

public class AbstractPageUI { 
	
	public static final String DYNAMIC_PAGE_LINK = "//a[text()='%s']";
	public static final String DYNAMIC_PAGE_HEADER_TEXT = "//p[@class='heading3' and text()='%s']";
	public static final String DYNAMIC_TEXTBOX_TEXTAREA = "(//input | //textarea)[@name='%s']";
	public static final String DYNAMIC_BUTTON = "//input[@value='%s']";
	public static final String DYNAMIC_RADIOBUTTON = "//input[@value='%s']";
	public static final String DYNAMIC_DROPDOWN_LIST = "//select[@name='%s']";
	public static final String DYNAMIC_DATE_TIME_PICKER = "//input[@id='dob']"; //*
	public static final String DYNAMIC_CELL_VALUE = "//td[text()='%s']/following-sibling::td";
	
}