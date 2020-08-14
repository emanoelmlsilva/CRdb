package com.mini_projeto.crdb.services;

import java.util.Optional;

import com.mini_projeto.crdb.dtos.GradeDTO;
import com.mini_projeto.crdb.exceptions.DisciplineNotFoundException;
import com.mini_projeto.crdb.exceptions.GradeAlreadyExistsException;
import com.mini_projeto.crdb.exceptions.GradeNotBelongException;
import com.mini_projeto.crdb.exceptions.GradeNotBelongOfUserException;
import com.mini_projeto.crdb.exceptions.GradeNotExistsException;
import com.mini_projeto.crdb.exceptions.UserInvalidException;
import com.mini_projeto.crdb.models.Discipline;
import com.mini_projeto.crdb.models.Grade;
import com.mini_projeto.crdb.models.User;
import com.mini_projeto.crdb.repositories.DisciplineRepository;
import com.mini_projeto.crdb.repositories.GradeRepository;
import com.mini_projeto.crdb.repositories.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GradeService {

    @Autowired
    private GradeRepository gradeRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private DisciplineRepository disciplineRepository;

    @Autowired
    private JWTService jwtService;

    public GradeDTO insert(String headerAuthorization, String id_discipline, Grade grade)
            throws DisciplineNotFoundException {

        Optional<String> userEmail = jwtService.recoverUser(headerAuthorization);

        User user = validateUser(userEmail);

        Discipline discipline = disciplineRepository.findById(id_discipline)
                .orElseThrow(() -> new DisciplineNotFoundException());

        Optional<Grade> gradeRecover = gradeRepository.findByCode(id_discipline, userEmail.get());

        if (gradeRecover.isPresent()) {
            throw new GradeAlreadyExistsException("Nota do usuario já cadastrada");

        }

        grade.setUser(user);

        grade.setDiscipline(discipline);

        return new GradeDTO(gradeRepository.save(grade));

    }

    public GradeDTO update(String headerAuthorization, GradeDTO gradeDTO, Long id)
            throws GradeNotExistsException, GradeNotBelongException {

        Optional<String> userEmail = jwtService.recoverUser(headerAuthorization);

        validateUser(userEmail);

        Grade gradeRecover = gradeRepository.findById(id).orElseThrow(() -> new GradeNotExistsException());

        if (!gradeRecover.getUser().getEmail().equals(userEmail.get())) {

            throw new GradeNotBelongException("Nota não pertence ao usuario");
        }

        gradeRecover.setGrade(gradeDTO.getGrade());

        return new GradeDTO(gradeRepository.save(gradeRecover));
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

}