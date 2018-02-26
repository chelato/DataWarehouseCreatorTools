package es.netkia.languages;

import es.netkia.io.FileManager;

public class English extends Language {

	public English() {
		super();
		
		this.index.put("code"	, "en");
		this.index.put("codeName", "English");

		this.index.put("tabGenerator"	, "Generator");
		this.index.put("tabSettings"	, "Settings");
 
		this.index.put("loadpath"	, "Load path");
		this.index.put("scriptspath", "Scripts path");
		this.index.put("browse"		, "Browse");

		this.index.put("fileListTitle"			, "Files");
		this.index.put("fileInfo"				, "FILE INFORMATION");
		this.index.put("fileStatusSelected"		, "-");
		this.index.put("fileStatusNone"			, "No file selected ");
		this.index.put("fileStatusNull"			, "File does not exist ");
		this.index.put("fileName"				, "Name:");
		this.index.put("fileType"				, "Type:");
		this.index.put("fileSize"				, "Size:");
		this.index.put("fileModified"			, "Modified:");
		this.index.put("fileValidationStatus" 	, "Status");
		this.index.put("filePreviewTitle" 		, "File Preview");

		this.index.put("refreshTooltip"		, "reload");
		this.index.put("removeTooltip"		, "remove from list");
		this.index.put("newFileTooltip"		, "new file");
		this.index.put("editFileTooltip"	, "edit file");
		this.index.put("deleteFileTooltip"	, "delete file");

		this.index.put("inputFileName"	, "File name ("+FileManager.DAT+")");
		this.index.put("fileNameEmpty"	, "File names cannot be empty.");
		this.index.put("fileNameInvalid", "File names cannot contain the following characters:\n\\ / : * ? \" < > |");

		this.index.put("confirmDeleteMessage"	,"Do you want to permanently delete this file?");
		this.index.put("yesButton"				,"Yes");
		this.index.put("noButton"				,"No");

		this.index.put("generateButton"	, "GENERATE");
		this.index.put("progressbar"	, "PROGRESS");
		this.index.put("successCount"	, "done");
		this.index.put("errorCount"		, "errors");

		this.index.put("settingsLangTitle"			, "Language");
		this.index.put("settingsScriptValuesTitle"	, "Script Values");
		this.index.put("settingsPathsTitle"			, "Paths");
		this.index.put("settingsDefaultLoadPath"	, "Default load path");
		this.index.put("settingsDefaultScriptsPath"	, "Default scripts path");
		this.index.put("settingsResetToDefaults"	, "Reset to defaults");
		this.index.put("settingsApply"				, "Apply");
	}
}
