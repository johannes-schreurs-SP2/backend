package be.ehb.sp2.backend.model;

import javax.persistence.*;

@Entity
public class UserAnswer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private User user;

    @ManyToOne
    private Survey survey;

    @ManyToOne
    private Answer answer;


    public UserAnswer() {

    }

    public String getUser() {
        return user.getEmail();
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
