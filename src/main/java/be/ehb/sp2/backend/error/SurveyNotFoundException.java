package be.ehb.sp2.backend.error;

public class SurveyNotFoundException extends RuntimeException {

    public SurveyNotFoundException(Long id) {
        super("Survey with id: " + " not found.");
    }
}
