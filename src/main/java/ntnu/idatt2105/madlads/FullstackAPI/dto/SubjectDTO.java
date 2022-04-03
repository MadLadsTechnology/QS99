package ntnu.idatt2105.madlads.FullstackAPI.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ntnu.idatt2105.madlads.FullstackAPI.model.subjects.Subject;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SubjectDTO {
    @Schema(type = "number", description = "Id of the object", required = true, example = "2")
    private int id;
    @Schema(type = "string", description = "Name of the subject", required = true, example = "Fullstack")
    private String subjectName;
    @Schema(type = "string", description = "Code of the subject", required = true, example = "IDATT2105")
    private String subjectCode;
    @Schema(type = "string", description = "Description of the subject", required = true, example = "Dette er fullstack faget")
    private String subjectDescription;
    @Schema(type = "number", description = "Year of the subject", required = true, example = "2022")
    private int subjectYear;
    @Schema(type = "number", description = "How many exercises are mandatory", required = true, example = "2")
    private int mandatoryCount;

    public SubjectDTO(Subject subject) {
        this.id = subject.getId();
        this.subjectCode = subject.getSubjectCode();
        this.subjectName = subject.getSubjectName();
        this.subjectDescription = subject.getSubjectDescription();
        this.subjectYear = subject.getSubjectYear();
    }
}
