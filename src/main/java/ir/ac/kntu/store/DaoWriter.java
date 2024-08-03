package ir.ac.kntu.store;

import java.io.*;

public class DaoWriter {

    public static void writeData(DataBase dataBase){
        try {
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream("Data.txt"));
            objectOutputStream.writeObject(dataBase);
            objectOutputStream.close();
        } catch (IOException e){
            throw new RuntimeException(e);
        }

    }

    public static DataBase read(){
        DataBase obj = null;
        try {
            ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream("Data.txt"));
            obj = (DataBase) objectInputStream.readObject();
            objectInputStream.close();
        } catch (FileNotFoundException e){
            throw new RuntimeException(e);
        } catch (IOException e){
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return obj;
    }

}
