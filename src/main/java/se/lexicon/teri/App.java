package se.lexicon.teri;

import se.lexicon.teri.fileIO.FileReader_Writer;

import java.io.File;
import java.util.Arrays;
import java.util.List;


public class App 
{
    public static void main( String[] args )
    {
        System.out.println("Exercise 1: Random text");
        String text = FileReader_Writer.readString(new File("files/randomText.txt"));
        System.out.println(text);
        System.out.println("==================");


        System.out.println("\nExercise 2: List of names");
        List<String> names = FileReader_Writer.readList(new File("files/listOfNames.txt"));
        System.out.println(names);
        System.out.println("==================");


        System.out.println("\nExercise 3: Write the contents of a Collection to a text file");
        List<String> list = Arrays.asList(
                "Object/Line 1",
                "Object/Line 2",
                "Object/Line 3",
                "Object/Line 4",
                "Object/Line 5"
        );
        File url = new File("files/newTextFile.txt");
        FileReader_Writer.writeFromList(url, list);
        System.out.println("==================");


        System.out.println("\nExercise 4: Copy a file using BufferedInputStream and BufferedOutputStream");
        File sourceFile = new File("files/randomText.txt");
        File destinationUrl = new File("files/copies/randomTextCopy.txt");
        FileReader_Writer.copyFile(sourceFile, destinationUrl);
        System.out.println("==================");


        System.out.println("\nExercise 5: Create a serializable Cars class and save/read list using " +
                "ObjectOutputStream and ObjectInputStream");
        File carsFile = new File("files/cars.ser");
        List<Car> cars = Arrays.asList(
                new Car("KLT 328", "Peugot", "450", 1977),
                new Car("BRR 566", "Ford", "Anglia", 1980),
                new Car("RGA 586", "Renault", "Clio", 1985),
                new Car("ABR 678", "Land Rover", "Explorer", 1990)
        );

        FileReader_Writer.saveObjects(cars, carsFile);
        FileReader_Writer.readObjects(carsFile);
        System.out.println("==================");
    }
}
