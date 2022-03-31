package ntnu.idatt2105.madlads.FullstackAPI.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ntnu.idatt2105.madlads.FullstackAPI.model.subjects.Queue;
import ntnu.idatt2105.madlads.FullstackAPI.model.subjects.Subject;

import java.util.ArrayList;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GetSubjectsDTO {
    private int id;
    private String subjectName;
    private String subjectCode;
    private String subjectDescription;
    private int mandatoryCount;
    private int subjectYear;
    private boolean isQueueActive;

    public GetSubjectsDTO(Subject subject, Queue queue) {
        this.id = subject.getId();
        this.subjectCode = subject.getSubjectCode();
        this.subjectName = subject.getSubjectName();
        this.subjectDescription = subject.getSubjectDescription();
        this.mandatoryCount = subject.getMandatoryCount();
        this.subjectYear = subject.getSubjectYear();
        this.isQueueActive = queue.getStatus();
    }
}
