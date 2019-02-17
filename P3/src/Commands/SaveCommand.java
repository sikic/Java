package Commands;

import java.io.*;
import java.util.Scanner;

import Excepciones.Null_execute;
import Excepciones.Parse_null;
import Excepciones.YesOrNoException;
import Game_2048.Game;
import utils.MyStringUtils;

public class SaveCommand extends Command {

	private final static String  commandInfo = "Save < FileName >";
	private final static String helpInfo = "allows to save  a game from in a file.";
	private boolean filename_confirmed;
	private String archivo;
	public final static String filenameInUseMsg = "The file already exists;" + '\n'
			+ " do you want to overwrite it ? (Y/N)";
	
	public SaveCommand(String guardar) {
		super(commandInfo, helpInfo);
		this.archivo = guardar;
	}

	
	public boolean execute(Game game) throws Null_execute {
		try{
			game.store(this.archivo);
		}catch(Exception e) {
			throw new Null_execute("Error al guardar fichero");
		}
		return false;
	}


	public Command Parse(String[] commandWords, Scanner in) throws Parse_null{
		if(commandWords[0].equalsIgnoreCase("save")) {
		if(commandWords[1] != null){
			try {
				return new SaveCommand(confirmFileNameStringForWrite(commandWords[1],in));
			} catch (YesOrNoException e){
				
				throw new Parse_null("Responda solo con Y or N, usando solo una letra");
			}	
			
			}
	
		else
			return null;//mirar esto
		}
		return null;
	
		}

private String confirmFileNameStringForWrite(String filenameString, Scanner in) throws Parse_null, YesOrNoException {
		String loadName = filenameString;
		filename_confirmed = false;
			while (!filename_confirmed) {
					if (MyStringUtils.validFileName(loadName)) {
							File file = new File(loadName);
							if (!file.exists())
								filename_confirmed = true;
							else {
								loadName = getLoadName(filenameString, in);
								filename_confirmed = true;
							}
					} else {
						throw new Parse_null("el archivo no es valido para escribir");
					}
			}
			return loadName;
		}
public String getLoadName(String filenameString, Scanner in) throws YesOrNoException {
	
	String newFilename = null;
	boolean yesOrNo = false;
		while (!yesOrNo) {
			System.out.print(filenameInUseMsg + ": ");
			String[] responseYorN = in.nextLine().trim().split("\\s+");
			if (responseYorN.length == 1){
				switch (responseYorN[0].toLowerCase()) {
						case "y":
							yesOrNo = true;
							newFilename= filenameString;	
							break;
						case "n":
							yesOrNo = true;
							do{
								System.out.println("Por favor introduzca otro nombre ");
								newFilename = in.nextLine();
							}while(newFilename.equals(filenameString));
							if(!MyStringUtils.validFileName(newFilename) && newFilename.length() == 1)
								System.out.println("Invalid filename: the filename contains invalid characters.");
							else if(newFilename.length() > 1)
								System.out.println("Invalid filename: the filename contains spaces.");

							break;
						default:
							System.out.println("introduzca solo 'Y o 'N");
			}
		} else 
				System.out.println("Invalid filename: the filename contains spaces");
		}
	return newFilename;
	}

}
