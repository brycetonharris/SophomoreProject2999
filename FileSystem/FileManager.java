package FileSystem;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Dictionary;
import java.util.Hashtable;

public class FileManager {
	/**
	 * @param fileName The name of the file to be created, minus the .txt
	 * @param Points The initial number of points, or "clicks"
	 * @param Clickers The initial number of clickers
	 * @param Grandpas The initial number of some arbitrary next clicker upgrade
	 */
	public static void newSave(String fileName,int Points,int Clickers,int Grandpas) {
		File f = new File("src/Saves/"+fileName+".txt");
		if(!f.exists()) {
			try {
				f.createNewFile();
				FileWriter write = new FileWriter(f);
				write.write(String.valueOf(Points)+"|"+String.valueOf(Clickers)+"|"+String.valueOf(Grandpas));
				write.close();
				System.out.println("File created.");
			} catch (IOException e) {
				System.out.println("Could not create file, or could not write to file.");
			}
		} else {
			System.out.println("File by name already exists.");
		}
	}
	/**
	 * @param fileName The name of the file to be deleted, minus the .txt
	 */
	public static void deleteSave(String fileName) {
		File f = new File("src/Saves/"+fileName+".txt");
		if(f.exists()) {
			f.delete();
			System.out.println("File deleted.");
		} else {
			System.out.println("File by name does not exist.");
		}
	}
	/**
	 * @param fileName The name of the file to format data from, minus the .txt
	 * @return A dictionary object easily readable using Java's builtin Dictionary class
	 */
	public static Dictionary<String,Integer> dictData(String fileName) {
		File f = new File("src/Saves/"+fileName+".txt");
		if(f.exists()) {
			Dictionary<String,Integer> d = new Hashtable<>();
			String readData = null;
			try {
				readData = Files.readString(Path.of("src/Saves/"+fileName+".txt"));
			} catch (IOException e) {
				System.out.println("Error getting file path.");
			}
			String[] split = readData.split("\\|");
			d.put("Points",Integer.parseInt(split[0]));
			d.put("Clickers",Integer.parseInt(split[1]));
			d.put("Grandpas",Integer.parseInt(split[2]));
			return d;
		} else {
			throw new IllegalArgumentException("File by name does not exist.");
		}
	}
	/**
	 * @param fileName The name of the file to be updated, minus the .txt
	 * @param Points The number of points, or "clicks", enter -1 to leave unaltered
	 * @param Clickers The number of clickers, enter -1 to leave unaltered
	 * @param Grandpas The number of some arbitrary upgrade name, enter -1 to leave unaltered
	 */
	public static void updateData(String fileName,int Points,int Clickers,int Grandpas) {
		File f = new File("src/Saves/"+fileName+".txt");
		if(f.exists()) {
			String readData = null;
			try {
				readData = Files.readString(Path.of("src/Saves/"+fileName+".txt"));
			} catch (IOException e) {
				System.out.println("Could not read from file.");
			}
			String[] split = readData.split("\\|");
			String writeString = "";
			if(Points==-1) {
				writeString = writeString + split[0] + "|";
			} else {
				writeString = writeString + String.valueOf(Points) + "|";
			}
			if(Clickers==-1) {
				writeString = writeString + split[1] + "|";
			} else {
				writeString = writeString + String.valueOf(Clickers) + "|";
			}
			if(Grandpas==-1) {
				writeString = writeString + split[2];
			} else {
				writeString = writeString + String.valueOf(Grandpas);
			}
			try {
				FileWriter write = new FileWriter(f);
				write.write(writeString);
				write.close();
			} catch (IOException e) {
				System.out.println("Unable to write to file.");
			}
		} else {
			System.out.println("File by name does not exist.");
		}
	}
	/**
	 * Only used for quickly reading data from a file, no practical use in code
	 */
	public static void debugPrint(String fileName) {
		File f = new File("src/Saves/"+fileName+".txt");
		if(f.exists()) {
			try {
				System.out.println(Files.readString(Path.of("src/Saves/"+fileName+".txt")));
			} catch (IOException e) {
				System.out.println("Unable to read from file.");
			}
		} else {
			System.out.println("File by name does not exist.");
		}
	}
	/**
	 * @return Returns the number of saves that exist, really only to measure how large any save select screen needs to be
	 */
	public static int numOfSaves() {
		File d = new File("src/Saves");
		return d.listFiles().length;
	}
}
