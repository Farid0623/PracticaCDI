package Service;

import dto.StudentDto;
import model.Student;

import java.util.Arrays;
import java.util.List;

public class StudentServiceImpl implements StudentService {
    private static final List<Student> students = Arrays.asList(
            new Student(1L, "Farid", "Farid0623@gmail.com", "Primero"),
            new Student(2L, "Santiago", "Santiago0904@gmail.com", "Segundo"),
            new Student(3L, "Federico", "Federico123@gmail.com", "Tercero")
    );

    @Override
    public List<StudentDto> listar() {

        return null;
    }

}
