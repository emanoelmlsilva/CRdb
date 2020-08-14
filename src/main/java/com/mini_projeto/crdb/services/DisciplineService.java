package com.mini_projeto.crdb.services;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mini_projeto.crdb.dtos.DisciplineDTO;
import com.mini_projeto.crdb.exceptions.DisciplineNotFoundException;
import com.mini_projeto.crdb.exceptions.NotExistsException;
import com.mini_projeto.crdb.exceptions.UserInvalidException;
import com.mini_projeto.crdb.models.Comment;
import com.mini_projeto.crdb.models.Discipline;
import com.mini_projeto.crdb.models.Favorite;
import com.mini_projeto.crdb.models.Grade;
import com.mini_projeto.crdb.models.ProfielDiscipline;
import com.mini_projeto.crdb.models.User;
import com.mini_projeto.crdb.repositories.DisciplineRepository;
import com.mini_projeto.crdb.repositories.FavoriteRepository;
import com.mini_projeto.crdb.repositories.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DisciplineService {

    @Autowired
    private DisciplineRepository disciplineRepository;

    @Autowired
    private JWTService jwtService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private FavoriteRepository favoriteRepository;

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

    public List<DisciplineDTO> findAll() {

        return fromToBaseDisciplina(disciplineRepository.findAll());

    }

    public List<DisciplineDTO> findByName(String name) throws DisciplineNotFoundException {

        List<Discipline> list = disciplineRepository.findByNameContainsIgnoreCase(name);

        if (list.size() == 0)
            throw new DisciplineNotFoundException();

        return fromToBaseDisciplina(list);

    }

    public ProfielDiscipline findProfielDiscipline(String headerAuthorization, String code) throws NotExistsException {

        ProfielDiscipline profielDiscipline = new ProfielDiscipline();

        Optional<String> userEmail = jwtService.recoverUser(headerAuthorization);

        validateUser(userEmail);

        Optional<Discipline> discipline = disciplineRepository.findByCode(code);

        if (!discipline.isPresent()) {
            throw new NotExistsException("Codigo da disciplina invalido ou não existe!");
        }

        profielDiscipline.setName(discipline.get().getName());

        profielDiscipline.setNumericFavorite(calculateFavorite(discipline.get().getFavorites()));

        Double sum = discipline.get().getGrades().size() > 0 ? calculateAverage(discipline.get().getGrades()) : 0;

        profielDiscipline.setArithmeticAverage(sum);

        Optional<Favorite> favorite = favoriteRepository.findByCode(discipline.get().getCode(), userEmail.get());

        Boolean isFAvorite = favorite.isPresent() ? favorite.get().getActive() : false;

        profielDiscipline.setFavorite(isFAvorite);

        profielDiscipline.setListComment(commentMark(discipline.get().getComments(), userEmail.get()));

        return profielDiscipline;

    }

    public List<DisciplineDTO> fromToBaseDisciplina(List<Discipline> disciplines) {
        return disciplines.stream().map(discipline -> new DisciplineDTO(discipline)).collect(Collectors.toList());
    }

    public User validateUser(Optional<String> email) {
        if (email.isEmpty()) {
            throw new UserInvalidException("Email não existe!");
        }

        Optional<User> user = userRepository.findByEmail(email.get());

        if (user.isEmpty()) {
            throw new UserInvalidException("Usuario não existe");
        }

        return user.get();
    }

    private int calculateFavorite(List<Favorite> favorites) {
        return (int) favorites.stream().filter(item -> item.getActive() == true).count();
    }

    private Double calculateAverage(List<Grade> grades) {

        Double sum = grades.stream().mapToDouble(item -> item.getGrade()).sum();

        return (Double) sum / grades.size();
    }

    private List<Comment> commentMark(List<Comment> comments, String email) {

        comments.stream().forEach(item -> {

            if (item.getUser().getEmail().equals(email)) {
                String commentMessage = item.getComment();
                item.setComment("Minha Publicação -> " + commentMessage);
            }
        });

        return comments;

    }
}