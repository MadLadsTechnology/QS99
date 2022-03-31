package ntnu.idatt2105.madlads.FullstackAPI.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ntnu.idatt2105.madlads.FullstackAPI.model.subjects.Subject;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SubjectDTO {
    private int id;
    private String subjectName;
    private String subjectCode;
    private String subjectDescription;
    private int mandatoryCount;
    private int subjectYear;

    public SubjectDTO(Subject subject) {
        this.id = subject.getId();
        this.subjectCode = subject.getSubjectCode();
        this.subjectName = subject.getSubjectName();
        this.subjectDescription = subject.getSubjectDescription();
        this.mandatoryCount = subject.getMandatoryCount();
        this.subjectYear = subject.getSubjectYear();
    }
}
