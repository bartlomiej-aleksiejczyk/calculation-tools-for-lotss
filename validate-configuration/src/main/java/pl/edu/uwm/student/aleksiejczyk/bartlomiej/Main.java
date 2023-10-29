package pl.edu.uwm.student.aleksiejczyk.bartlomiej;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) {
        if (SchemaValidator.validateXMLAgainstXSDSchema()) {
            System.out.println("Your XML file is valid against schema.");
        } else {
            System.out.println("Your XML file is NOT valid against schema!");
        }
    }
}