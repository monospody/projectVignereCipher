package sample;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;

import java.io.*;
import java.util.Scanner;

public class Controller {
    public Button openFile;
    public TextField input;
    public Button encrypt;
    public Button decrypt;
    public String data;
    public File myFile;

    public void open(ActionEvent actionEvent) throws FileNotFoundException {
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("TXT File", "*.txt"));
        myFile = fileChooser.showOpenDialog(null);
        Scanner in = new Scanner(myFile);

        while (in.hasNextLine()){
            data = in.nextLine();
            System.out.println(data);
        }
        in.close();

    }

    public void setEncrypt(ActionEvent actionEvent) throws IOException {
        if (input.getText() !=null) {
            String key = input.getText();
            String output = "";
            File myOutputFile = new File(myFile.getName().substring(0, myFile.getName().length() - 4) + "_encrypt.txt");
            key = key.toLowerCase();

            for (int i = 0, j = 0; i < data.length(); i++) {
                int cur = (int) data.charAt(i);


                if (cur >= 'a' && cur <= 'z') {
                    output += Character.toString((char) ((cur + key.charAt(j) - 2 * 'a') % 26 + 'a'));
                    j = ++j % key.length();
                }
                // should work for uppercase between 'A' and 'Z'
                else if (cur >= 'A' && cur <= 'Z') {
                    output += Character.toString((char) ((cur - 'A' + key.charAt(j) - 'a') % 26 + 'A'));
                    j = ++j % key.length();
                } else {
                    output += (char) cur;
                }

            }
            System.out.println(output);

            BufferedWriter writer = new BufferedWriter(new FileWriter(myOutputFile));
            writer.write(output);

            writer.close();
        }
    }

    public void setDecrypt(ActionEvent actionEvent) throws IOException {
        if (input.getText() !=null) {
            String key = input.getText();
            String output = "";
            File myOutputFile = new File(myFile.getName().substring(0, myFile.getName().length() - 4) + "_decrypt.txt");
            key = key.toLowerCase();

            for (int i = 0, j = 0; i < data.length(); i++) {
                int cur = (int) data.charAt(i);

                if (cur >= 'a' && cur <= 'z') {
                    output += Character.toString((char) ((cur - key.charAt(j) + 26) % 26 + 'a'));
                    j = ++j % key.length();
                }
                // should work for uppercase between 'A' and 'Z'
                else if (cur >= 'A' && cur <= 'Z') {
                    output += Character.toString((char) ((cur - key.charAt(j) + 58) % 26 + 'A'));
                    j = ++j % key.length();
                } else {
                    output += (char) cur;
                }

            }


            System.out.println(output);

            BufferedWriter writer = new BufferedWriter(new FileWriter(myOutputFile));
            writer.write(output);

            writer.close();
        }
    }


}
