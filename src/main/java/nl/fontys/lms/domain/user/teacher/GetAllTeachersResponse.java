package nl.fontys.lms.domain.user.teacher;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import nl.fontys.lms.persistence.entity.TeacherEntity;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GetAllTeachersResponse {
    private ArrayList<Teacher> teachers;
}
