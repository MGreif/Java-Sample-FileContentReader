package DefaultPackage;
import java.awt.*;
import java.io.*;
import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class MainClass {
	public static File[] listOfFiles;
	public static String[] fileParts = new String[2];
	public static FileContent content;
	public static String path = "E:\\files/";
	public static void main(String [] args)
	{
		File folder = new File(path);
		listOfFiles = folder.listFiles();
		initFrame();	
	}
	public static void initFrame() {
		//Setting up Overlay//
		JFrame frame = new JFrame("Main");
		frame.setSize(new Dimension(600,500));
		frame.setLayout(null);
		JList<File> fileList = new JList<File>(listOfFiles);
		fileList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		fileList.setLocation(new Point(10,10));
		fileList.setSize(new Dimension(200,200));
		JTextArea textField = new JTextArea();
		textField.setLocation(new Point(250,10));
		textField.setSize(new Dimension(200,100));
		textField.setVisible(true);
		frame.add(textField);
		frame.setVisible(true);	
		frame.add(fileList);
		//Setting up Overlay//
		//Adding List Listener//
		fileList.addListSelectionListener(
			new ListSelectionListener() {
				public void valueChanged(ListSelectionEvent e) {
					content = getFileContent(convertToFile(fileList.getSelectedValue().toString()));
					textField.setText("Name: " + content.name + System.lineSeparator() + "Date: " + content.date);
			}});
		//Adding List Listener//
	}		
	public static File convertToFile(String s) {
		return new File(s);
	}
	public static FileContent getFileContent(File f) {
		try {
		FileReader fr = new FileReader(f);
		char[] buff = new char[50];
		fr.read(buff);
		fr.close();
		String fileContent = "";
		for(char c : buff) {
			if (c != Character.MIN_VALUE) {
				fileContent += c;
			}
		}
		fileParts = fileContent.split("-");
		} catch(Exception e) {	}
		FileContent t = new FileContent(fileParts[0],fileParts[1]);
		return t;	
	}
	public static void log(String e) {
		System.out.println(e);
	}
}
