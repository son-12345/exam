package student;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;

public class JsonUtils {

    private static final Gson gson = new Gson();

    public static List<Student> loadStudentsFromFile(String filename) {
        try (FileReader reader = new FileReader(filename)) {
            Type studentListType = new TypeToken<List<Student>>() {}.getType();
            return gson.fromJson(reader, studentListType);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void saveStudentsToFile(List<Student> students, String filename) {
        try (FileWriter writer = new FileWriter(filename)) {
            gson.toJson(students, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
