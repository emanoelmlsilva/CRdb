package com.mini_projeto.crdb.services;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mini_projeto.crdb.dtos.DisciplineDTO;
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
        System.out.println("iniciou++++++++++++++++++++++");
        ObjectMapper mapper = new ObjectMapper();
        System.out.println("passo 01");
        TypeReference<List<Discipline>> typeReference = new TypeReference<List<Discipline>>() {
        };
        System.out.println("passo 02");
        InputStream inputStream = ObjectMapper.class.getResourceAsStream("/json/subjects.json");
        System.out.println("passo 03");
        try {
            List<Discipline> disciplinas = mapper.readValue(inputStream, typeReference);
            System.out.println("passo 04" + disciplinas.get(0).toString());
            List<Discipline> bdDisciplinas = disciplineRepository.findAll();
            System.out.println("passo 05");
            if (bdDisciplinas.size() > 0) {
                System.out.println("passo 06");
                disciplinas.removeAll(bdDisciplinas);

            }
            System.out.println("antes de salva ---------------------------------------");
            this.disciplineRepository.saveAll(disciplinas);
            System.out.println("Disciplinas salvas no BD!");

        } catch (IOException erro) {
            System.out.println("Não foi possivel salvar as disciplinas no BD!" + erro.getCause());
        }

    }

    public List<DisciplineDTO> findAll() {
        System.out.println("create findAllService");
        return fromToBaseDisciplina(disciplineRepository.findAll());
    }

    public List<DisciplineDTO> fromToBaseDisciplina(List<Discipline> disciplines) {
        System.out.println("create fromTo");
        return disciplines.stream().map(discipline -> new DisciplineDTO(discipline)).collect(Collectors.toList());
    }

}