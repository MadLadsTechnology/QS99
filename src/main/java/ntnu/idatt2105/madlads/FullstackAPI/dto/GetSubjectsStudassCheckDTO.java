package ntnu.idatt2105.madlads.FullstackAPI.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ntnu.idatt2105.madlads.FullstackAPI.model.subjects.Queue;
import ntnu.idatt2105.madlads.FullstackAPI.model.subjects.Subject;
import ntnu.idatt2105.madlads.FullstackAPI.model.users.Student;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GetSubjectsStudassCheckDTO {
    private int id;
    private String subjectName;
    private String subjectCode;
    private String subjectDescription;
    private int subjectYear;
    private boolean isQueueActive;
    private Boolean isStudAss;

    public GetSubjectsStudassCheckDTO(Subject subject, Queue queue, Student student) {
        this.id = subject.getId();
        this.subjectCode = subject.getSubjectCode();
        this.subjectName = subject.getSubjectName();
        this.subjectDescription = subject.getSubjectDescription();
        this.subjectYear = subject.getSubjectYear();
        this.isQueueActive = queue.getStatus();
        if (student!=null){
            this.isStudAss = subject.getAssistants().contains(student);
        }else this.isStudAss = null;
    }
}
