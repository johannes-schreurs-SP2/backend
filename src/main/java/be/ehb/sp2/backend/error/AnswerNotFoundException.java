package be.ehb.sp2.backend.error;

public class AnswerNotFoundException extends RuntimeException {

    public AnswerNotFoundException(Long id) {
        super("Answer with id: " + id + " not found.");
    }
}
