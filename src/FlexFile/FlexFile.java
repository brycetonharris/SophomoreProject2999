package Stuff;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.io.BufferedReader;
import java.io.FileReader;

public class FlexFile {
	private String fileName;
	private String[] properties;
	private Object[] values;
	public FlexFile(String fileName,String...properties) {
		this.fileName = fileName;
		this.properties = properties;
		values = new Object[properties.length];
	}
	public FlexFile(String fileName) {
		try {
			this.fileName = fileName;
			BufferedReader read = new BufferedReader(new FileReader("src/Saves/"+fileName+".txt"));
			Object[] temp = read.lines().toArray();
			String[] get = new String[temp.length];
			for(int i=0;i<temp.length;i++) {
				get[i] = String.valueOf(temp[i]);
			}
			properties = new String[get.length];
			values = new Object[get.length];
			for(int i=0;i<get.length;i++) {
				String[] line = get[i].split(": ");
				properties[i] = line[0];
				Class cast = Class.forName(line[1].split(" ")[1]);
				values[i] = cast.getMethod("valueOf",String.class).invoke(null,line[2]);
			}
			read.close();
		} catch (IOException | ClassNotFoundException | NoSuchMethodException | SecurityException | IllegalAccessException | InvocationTargetException e) {
			e.printStackTrace();
		}
	}
	/**
	 * @param property The name of the property to update
	 * @param value The data to change the property's value to
	 */
	public void setProperty(String property,Object value) {
		for(int i=0;i<properties.length;i++) {
			if(properties[i].equals(property)) {
				values[i] = value;
				break;
			}
		}
	}
	/**
	 * @param property The name of the property to get
	 * @param type The data type to return (i.e. Integer.class)
	 * @return The value of the property
	 */
	public <T> T getProperty(String property,Class<T> type) {
		for(int i=0;i<properties.length;i++) {
			if(properties[i].equals(property)) {
				return (T)values[i];
			}
		}
		return null;
	}
	public void saveFile() {
		File file = new File("src/Saves/"+fileName+".txt");
		String assembly = "";
		try {
			if(file.createNewFile()) {
				for(int i=0;i<properties.length;i++) {
					assembly = assembly + properties[i]+": "+String.valueOf(values[i].getClass())+": "+String.valueOf(values[i]);
					if(i!=properties.length-1) {
						assembly = assembly + String.format("%n");
					}
				}
				Files.writeString(Path.of("src/Saves/"+fileName+".txt"),assembly);
			} else {
				
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	/**
	 * @return Whether the file could be deleted/existed or not
	 */
	public boolean delete() {
		return new File("src/Saves/"+fileName+".txt").delete();
	}
	public void printProperties() {
		for(String s:properties) {
			System.out.println(s);
		}
	}
	public String getName() {
		return fileName;
	}
}
