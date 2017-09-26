package Innlevering1.dataTypes;

public class SubjectAndProgram extends Tables {
    String subjectId;
    int programId;

    public SubjectAndProgram() {
        this("test", 0);
    }

    public SubjectAndProgram(String subjectId, int programId) {
        setSubjectId(subjectId);
        setProgramId(programId);
    }

    public String getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(String subjectId) {
        this.subjectId = subjectId;
    }

    public int getProgramId() {
        return programId;
    }

    public void setProgramId(int programId) {
        this.programId = programId;
    }

    @Override
    public String toString() {
        return String.format("%s %-10s %-50s", super.toString(), getSubjectId(), getProgramId());
    }
}
