package prictic;

import java.io.*;

public class Main {
    public static void main(String[] args) {

        File dir = new File("src\\prictic\\file");
        File exText = new File(dir, "example.txt");
        File exText2 = new File(dir, "ex2.txt");

        byte[] exxxx = "example".getBytes();

        byte[] text;

//        File personFile = new File(dir, "person.dat");
//        try(ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(personFile)))
//        {
//            Person p = new Person("Sam", 33, 178, true);
//            oos.writeObject(p);
//        }
//        catch(Exception ex){
//
//            System.out.println(ex.getMessage());
//        }
//
//        try(ObjectInputStream ois = new ObjectInputStream(new FileInputStream(personFile)))
//        {
//            //todo: исправить вывод
//            Person p = (Person) ois.readObject();
//            System.out.printf("Name: %s \t Age: %d \t Height: %f  Married: %b \n ", p.getName(), p.getAge(), p.getHeight(), p.getMarried());
//        }
//        catch(Exception ex){
//
//            System.out.println(ex.getMessage());
//        }

        FileInputStream fis = null;

        try {
            fis = new FileInputStream(exText);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        } finally {
            try {
                if (fis != null)
                    fis.close();
            } catch (IOException ex) {
                System.out.println(ex.getMessage());
            }
        }

        try (FileInputStream fis1 = new FileInputStream(exText)) {
            System.out.println(fis1.available());
//            text = new byte[fis1.available()];
            int i;
            while ((i = fis1.read()) != -1) { // i = '\s' | i = 32
                System.out.print((char) i);
            }
//            fis1.read(text);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }

        try (FileInputStream fis1 = new FileInputStream(exText2)) {
            System.out.println(fis1.available());
            int i;
            while ((i = fis1.read()) != -1) { // i = '\s' | i = 32
                int k = i - 32;
                System.out.print((char) k);
            }
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }

        try (FileInputStream fis1 = new FileInputStream(exText);
             FileOutputStream fos = new FileOutputStream(exText)
        ) {
            text = new byte[fis1.available()];

            byte[] buffer = text;

            int offset = 0;
            int i;
            while ((i = fis1.read(text)) != 1) {
                fos.write(text);
            }

//            fos.write(buffer, offset, buffer.length - offset - 2);
            System.out.println("\nThe file has been written");
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }

    }
//
//    public static void fisVoid (File exText) throws IOException {
//        FileInputStream fis1 = new FileInputStream(exText);
//    }
}
