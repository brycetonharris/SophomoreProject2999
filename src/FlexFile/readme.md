To create a new file, use the constructor File(String fileName,String...properties). The second parameter is a vararg parameter. Here is an example:
FlexFile file = new FlexFile("Save 1","Basic Logs","Gold Logs","Cherry Logs","Lumberjacks","Planets");
Obviously you need to use file.setProperty to change these after initialization, but each property can hold any type of data.
Example:
file.setProperty("Basic Logs",500);
file.setProperty("Planets",false);
In order for the file to actually be created, you must save it using file.saveFile();
In order to get a file's property, you must define the data type of the file using classes. Example:
file.getProperty("Basic Logs",Integer.class)+10 would return 510.
In order to get an existing file and its data, you must use the constructor File(String fileName). Example:
Suppose you just launched the game and want to load a file named "Save 1". You should do FlexFile file = new FlexFile("Save 1");
The constructor will automatically retrieve the data from the existing file and create a new file object accordingly.
