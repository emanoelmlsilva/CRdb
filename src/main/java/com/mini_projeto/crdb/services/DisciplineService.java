package com.mini_projeto.crdb.services;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mini_projeto.crdb.dtos.DisciplineDTO;
import com.mini_projeto.crdb.exceptions.DisciplineNotFoundException;
import com.mini_projeto.crdb.models.Discipline;
import com.mini_projeto.crdb.repositories.DisciplineRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DisciplineService {

    @Autowired
    private DisciplineRepository disciplineRepository;

    public DisciplineService() {
    }

    @PostConstruct
    public void initDiscipline() {

        ObjectMapper mapper = new ObjectMapper();

        TypeReference<List<Discipline>> typeReference = new TypeReference<List<Discipline>>() {
        };

        InputStream inputStream = ObjectMapper.class.getResourceAsStream("/json/subjects.json");

        try {
            List<Discipline> disciplinas = mapper.readValue(inputStream, typeReference);

            List<Discipline> bdDisciplinas = disciplineRepository.findAll();

            if (bdDisciplinas.size() > 0) {

                disciplinas.removeAll(bdDisciplinas);

            }

            this.disciplineRepository.saveAll(disciplinas);

        } catch (IOException erro) {
            System.out.println("Não foi possivel salvar as disciplinas no BD!" + erro.getCause());
        }

    }

    public List<Discipline> findAll() {

        return disciplineRepository.findAll();

    }

    public List<Discipline> findByName(String name) throws DisciplineNotFoundException {

        List<Discipline> list = disciplineRepository.findByNameContainsIgnoreCase(name);

        if (list.size() == 0)
            throw new DisciplineNotFoundException();

        return list;

    }

    public List<DisciplineDTO> fromToBaseDisciplina(List<Discipline> disciplines) {
        return disciplines.stream().map(discipline -> new DisciplineDTO(discipline)).collect(Collectors.toList());
    }

}