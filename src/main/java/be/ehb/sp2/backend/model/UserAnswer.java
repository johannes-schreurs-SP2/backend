package be.ehb.sp2.backend.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;

@Entity
public class UserAnswer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JsonBackReference(value="userAnswer")
    private User user;

    @ManyToOne
    @JsonBackReference(value="userAnswerSurvey")
    private Survey survey;

    @ManyToOne
    @JsonBackReference(value="userAnswerAnswer")
    private Answer answer;

    private Boolean isAnswered;

    public Boolean getAnswered() {
        return isAnswered;
    }

    public void setAnswered(Boolean answered) {
        isAnswered = answered;
    }

    public UserAnswer() {

    }

    public Long getUser() {
        return user.getId();
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Long getSurvey() {
        return survey.getId();
    }

    public void setSurvey(Survey survey){
        this.survey = survey;
    }

    public Long getAnswer() {
        return answer.getId();
    }

    public void setAnswer(Answer answer) {
        this.answer = answer;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

}
