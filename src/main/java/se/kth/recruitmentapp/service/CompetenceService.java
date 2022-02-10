package se.kth.recruitmentapp.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import se.kth.recruitmentapp.domain.Competence;
import se.kth.recruitmentapp.repository.CompetenceRepository;

import java.util.List;

@Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRES_NEW)
@Service
public class CompetenceService {
    @Autowired
    private CompetenceRepository repository;

    public List<Competence> getAllCompetences(){
        return repository.findAll();
    }
}
