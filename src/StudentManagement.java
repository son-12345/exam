

import java.io.*;
import java.util.*;

import org.json.simple.*;
import org.json.simple.parser.*;

public class StudentManagement {
    private static final String FILE_NAME = "student.json";
    private static JSONArray loadData() {
        try (FileReader reader = new FileReader(FILE_NAME)) {
            JSONParser jsonParser = new JSONParser();
            return (JSONArray) jsonParser.parse(reader);
        } catch (FileNotFoundException e) {
            return new JSONArray();
        } catch (IOException | ParseException e) {
            e.printStackTrace();
            return new JSONArray();
        }
    }

    // Lưu dữ liệu vào file JSON
    private static void saveData(JSONArray data) {
        try (FileWriter file = new FileWriter(FILE_NAME)) {
            file.write(data.toJSONString());
            file.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Hiển thị toàn bộ sinh viên
    private static void displayStudents(JSONArray data) {
        System.out.println("Danh sách sinh viên:");
        for (Object obj : data) {
            JSONObject student = (JSONObject) obj;
            System.out.println("ID: " + student.get("ID"));
            System.out.println("Tên: " + student.get("Tên"));

            // Kiểm tra nếu subjects là null
            if (student.get("Môn học") != null) {
                JSONObject subjects = (JSONObject) student.get("Môn học");
                System.out.println("Môn học và điểm thi:");
                for (Object key : subjects.keySet()) {
                    System.out.println("- " + key + ": " + subjects.get(key));
                }
            } else {
                System.out.println("Không có môn học nào được đăng ký.");
            }

            System.out.println();
        }
    }

    private static void addStudent(JSONArray data) {
        Scanner scanner = new Scanner(System.in);
        JSONObject newStudent = new JSONObject();
        System.out.print("Nhập ID của sinh viên: ");
        String id = scanner.nextLine();
        newStudent.put("ID", id);
        System.out.print("Nhập tên của sinh viên: ");
        String name = scanner.nextLine();
        newStudent.put("Tên", name);
        JSONObject subjects = new JSONObject();
        System.out.print("Nhập số lượng môn học: ");
        int numSubjects = Integer.parseInt(scanner.nextLine());
        for (int i = 0; i < numSubjects; i++) {
            System.out.print("Nhập tên môn học: ");
            String subject = scanner.nextLine();
            System.out.print("Nhập điểm thi: ");
            String score = scanner.nextLine();
            subjects.put(subject, score);
        }
        newStudent.put("Môn học", subjects);
        data.add(newStudent);
        saveData(data);
        System.out.println("Sinh viên mới đã được thêm vào danh sách.");
    }

    // Chỉnh sửa thông tin sinh viên
    private static void editStudent(JSONArray data) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Nhập ID của sinh viên cần sửa: ");
        String studentId = scanner.nextLine();
        for (Object obj : data) {
            JSONObject student = (JSONObject) obj;
            if (studentId.equals(student.get("ID"))) {
                System.out.println("1. Chỉnh sửa tên");
                System.out.println("2. Thêm môn học và điểm thi");
                System.out.println("3. Xóa môn học");
                System.out.print("Chọn chức năng: ");
                int choice = Integer.parseInt(scanner.nextLine());
                switch (choice) {
                    case 1:
                        System.out.print("Nhập tên mới: ");
                        String newName = scanner.nextLine();
                        student.put("Tên", newName);
                        break;
                    case 2:
                        JSONObject subjects = (JSONObject) student.get("Môn học");
                        System.out.print("Nhập số lượng môn học cần thêm: ");
                        int numSubjects = Integer.parseInt(scanner.nextLine());
                        for (int i = 0; i < numSubjects; i++) {
                            System.out.print("Nhập tên môn học: ");
                            String subject = scanner.nextLine();
                            System.out.print("Nhập điểm thi: ");
                            String score = scanner.nextLine();
                            subjects.put(subject, score);
                        }
                        student.put("Môn học", subjects);
                        break;
                    case 3:
                        JSONObject subjectsToRemove = (JSONObject) student.get("Môn học");
                        System.out.print("Nhập tên môn học cần xóa: ");
                        String subjectToRemove = scanner.nextLine();
                        subjectsToRemove.remove(subjectToRemove);
                        student.put("Môn học", subjectsToRemove);
                        break;
                    default:
                        System.out.println("Lựa chọn không hợp lệ.");
                }
                saveData(data);
                System.out.println("Thông tin sinh viên đã được cập nhật.");
                return;
            }
        }
        System.out.println("Không tìm thấy sinh viên có ID này.");
    }

    // Tìm kiếm sinh viên theo tên hoặc ID
    private static void searchStudent(JSONArray data) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Nhập tên hoặc ID của sinh viên: ");
        String searchTerm = scanner.nextLine();
        boolean found = false;
        for (Object obj : data) {
            JSONObject student = (JSONObject) obj;
            String id = (String) student.get("ID");
            String name = (String) student.get("Tên");
            if (name.toLowerCase().contains(searchTerm.toLowerCase()) || id.equals(searchTerm)) {
                System.out.println("Thông tin sinh viên:");
                System.out.println("ID: " + id);
                System.out.println("Tên: " + name);
                JSONObject subjects = (JSONObject) student.get("Môn học");
                System.out.println("Môn học và điểm thi:");
                for (Object key : subjects.keySet()) {
                    System.out.println("- " + key + ": " + subjects.get(key));
                }
                System.out.println();
                found = true;
            }
        }
        if (!found) {
            System.out.println("Không tìm thấy sinh viên.");
        }
    }

    public static void main(String[] args) {
        JSONArray data = loadData();

        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("\n=== MENU ===");
            System.out.println("1. Hiển thị toàn bộ sinh viên");
            System.out.println("2. Thêm sinh viên");
            System.out.println("3. Sửa thông tin sinh viên");
            System.out.println("4. Tìm kiếm sinh viên");
            System.out.println("5. Thoát");
            System.out.print("Chọn chức năng: ");
            int choice = Integer.parseInt(scanner.nextLine());

            switch (choice) {
                case 1:
                    displayStudents(data);
                    break;
                case 2:
                    addStudent(data);
                    break;
                case 3
                        :
                    editStudent(data);
                    break;
                case 4:
                    searchStudent(data);
                    break;
                case 5:
                    System.out.println("Thoát chương trình.");
                    System.exit(0);
                default:
                    System.out.println("Lựa chọn không hợp lệ.");
            }
        }
    }
}
