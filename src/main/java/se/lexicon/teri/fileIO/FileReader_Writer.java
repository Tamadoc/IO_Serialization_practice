package se.lexicon.teri.fileIO;

import se.lexicon.teri.Car;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileReader_Writer {

    /*
     * Exercise 1
     * Read in a String from a file and return the String using FileReader
     */
    public static String readString(File source) {
        StringBuilder stringBuilder = new StringBuilder();

        try (FileReader reader = new FileReader(source)) {
            int i;
            while ((i = reader.read()) != -1) {
                char letter = (char) i;
                stringBuilder.append(letter);
            }
        } catch (FileNotFoundException e) {
            System.out.println("File does not exist.");
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return stringBuilder.toString();
    }

    /*
     * Exercise 2
     * Read in lines from a file using BufferedReader and store in a collection
     */
    public static List<String> readList(File source) {
        List<String> names = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(source))) {
            String line;
            while ((line = reader.readLine()) != null) {
                names.add(line);
            }
        } catch (FileNotFoundException e) {
            System.out.println("File does not exist.");
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return names;
    }

    /*
     * Exercise 3
     * Write each String object from a collection into a text file
     */
    public static void writeFromList(File destination, List<String> sourceList) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(destination))) {
            System.out.println("File " + destination.getName() + " created");
            for (String string : sourceList) {
                writer.write(string);
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /*
     * Exercise 4
     * Copy a file using BufferedInputStream and BufferedOutputStream
     */
    public static void copyFile(File source, File destination) {
        try (
                BufferedInputStream in = new BufferedInputStream(new FileInputStream(source));
                BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(destination))
        ) {
            byte[] buffer = new byte[1024];
            int bytesRead;
            while ((bytesRead = in.read(buffer)) > 0) {
                out.write(buffer, 0, bytesRead);
                out.flush();
            }
            System.out.println("File " + destination.getName() + " copied");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /*
     * Exercise 4
     * Create a serializable Cars class and save/read list using ObjectOutputStream and ObjectInputStream"
     */
    public static void saveObjects(List<Car> carsList, File destinationFile) {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(destinationFile))) {
            for (Car car : carsList) {
                out.writeObject(car);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void readObjects(File sourceFile) {
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(sourceFile))) {
            for (; ; ) {
                Car deserialized = (Car) in.readObject();
                System.out.println("Registration: " + deserialized.getRegistration());
                System.out.println("Brand: " + deserialized.getBrand());
                System.out.println("Model: " + deserialized.getModel());
                System.out.println("Year: " + deserialized.getYear());
                System.out.println();
            }
        } catch (EOFException e) {
            System.out.println("END OF FILE");
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
