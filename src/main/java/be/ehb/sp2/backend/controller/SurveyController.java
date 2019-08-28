package be.ehb.sp2.backend.controller;

import be.ehb.sp2.backend.error.SurveyNotFoundException;
import be.ehb.sp2.backend.model.Survey;
import be.ehb.sp2.backend.repository.SurveyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/surveys")
public class SurveyController {

    @Autowired
    SurveyRepository surveyRepository;

    @GetMapping({"", "/"})
    public Iterable<Survey> getAllSurveys(){
        return surveyRepository.findAll();
    }

    @GetMapping("/{id}")
    public Survey getSurveyById(@PathVariable Long id) {
        return surveyRepository.findById(id).orElseThrow(() -> new SurveyNotFoundException(id));
    }

    @GetMapping("/search/name/{keyword}")
    public Iterable<Survey> getSurveysByKeyword(@PathVariable String keyword){
        return surveyRepository.findSurveysByNameContaining(keyword);
    }

    @PostMapping({"", "/"})
    public Survey createSurvey(@RequestBody Survey survey) {
        survey.setId(null);
        return surveyRepository.save(survey);
    }

    @PutMapping("")
    public Survey updateSurvey(@RequestBody Survey survey) {
        Survey surveyToUpdate;
        surveyToUpdate = surveyRepository.findById(survey.getId()).get();
        surveyToUpdate.setName(survey.getName());
        return surveyRepository.save(survey);
    }

    @DeleteMapping("/{id}")
    public void removeSurvey(@PathVariable Long id) {
        if(! surveyRepository.existsById(id)) {
            throw new SurveyNotFoundException(id);
        } else {
            surveyRepository.deleteById(id);
        }
    }

}
