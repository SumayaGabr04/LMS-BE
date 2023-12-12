package nl.fontys.lms.domain.course;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GetAllEnrolledCoursesResponse {
    private ArrayList<Course> courses;
}
