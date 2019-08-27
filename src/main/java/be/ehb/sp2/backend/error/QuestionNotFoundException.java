package be.ehb.sp2.backend.error;

public class QuestionNotFoundException extends RuntimeException {

    public QuestionNotFoundException(Long id) {
        super("Question with id: " + id + " not found.");
    }
}
