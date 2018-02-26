package es.netkia.languages;

import es.netkia.io.FileManager;

public class Spanish extends Language {
	
	public Spanish() {
		super();
		
		this.index.put("code"		, "es");
		this.index.put("codeName"	, "Spanish");
		
		this.index.put("tabGenerator"	, "Generador");
		this.index.put("tabSettings"	, "Ajustes");
		
		this.index.put("loadpath"	, "Ruta de carga");
		this.index.put("scriptspath", "Ruta de scripts");
		this.index.put("browse"		, "Examinar");
		
		this.index.put("fileListTitle"			, "Ficheros");
		this.index.put("fileInfo"				, "INFORMACION DEL FICHERO");
		this.index.put("fileStatusSelected"		, "- ");
		this.index.put("fileStatusNone"			, "Ningun fichero seleccionado ");
		this.index.put("fileStatusNull"			, "El fichero no existe ");
		this.index.put("fileName"				, "Nombre:");
		this.index.put("fileType"				, "Tipo:");
		this.index.put("fileSize"				, "Tamaño:");
		this.index.put("fileModified"			, "Modificado:");
		this.index.put("fileValidationStatus" 	, "Estado");
		this.index.put("filePreviewTitle" 		, "Vista Previa");
		
		this.index.put("refreshTooltip"		, "recargar");
		this.index.put("removeTooltip"		, "eliminar de la lista");
		this.index.put("newFileTooltip"		, "crear fichero");
		this.index.put("editFileTooltip"	, "editar fichero");
		this.index.put("deleteFileTooltip"	, "eliminar fichero");
		
		this.index.put("inputFileName"	, "Nombre del fichero ("+FileManager.DAT+")");
		this.index.put("fileNameEmpty"	,"El nombre del fichero no puede estar vacío.");
		this.index.put("fileNameInvalid","El nombre del fichero no puede contener los siguientes caracteres:\n\\ / : * ? \" < > |");
		
		this.index.put("confirmDeleteMessage"	,"¿Desea eliminar permanentemente este fichero?");
		this.index.put("yesButton"				,"Si");
		this.index.put("noButton"				,"No");
		
		this.index.put("generateButton"	, "GENERAR");
		this.index.put("progressbar"	, "PROGRESO");
		this.index.put("successCount"	, "hechos");
		this.index.put("errorCount"		, "errores");
		
		this.index.put("settingsLangTitle"			, "Idioma");
		this.index.put("settingsScriptValuesTitle"	, "Valores del Script");
		this.index.put("settingsPathsTitle"			, "Rutas");
		this.index.put("settingsDefaultLoadPath"	, "Ruta de carga predeterminada");
		this.index.put("settingsDefaultScriptsPath"	, "Ruta de scripts predeterminada");
		this.index.put("settingsResetToDefaults"	, "Restablecer valores predeterminados");
		this.index.put("settingsApply"				, "Aplicar");
	}
}
