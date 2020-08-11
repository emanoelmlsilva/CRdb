package com.mini_projeto.crdb.services;

import java.util.Optional;

import com.mini_projeto.crdb.Utils.ValidateUser;
import com.mini_projeto.crdb.exceptions.CommentAlreadyExistsException;
import com.mini_projeto.crdb.exceptions.DisciplineNotExistsException;
import com.mini_projeto.crdb.exceptions.UserInvalidException;
import com.mini_projeto.crdb.models.Comment;
import com.mini_projeto.crdb.models.Discipline;
import com.mini_projeto.crdb.models.User;
import com.mini_projeto.crdb.repositories.CommentRepository;
import com.mini_projeto.crdb.repositories.DisciplineRepository;
import com.mini_projeto.crdb.repositories.UserRepository;

import org.hibernate.validator.internal.metadata.facets.Validatable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentService {

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private DisciplineRepository disciplineRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    JWTService jwtService;

    public Comment insert(String headerAuthorization, String id_discipline, Comment comment)
            throws DisciplineNotExistsException {

        // ler o token e recuperar o subject
        Optional<String> userEmail = jwtService.recoverUser(headerAuthorization);

        // verifica se existe um usuario correspondende ao token
        User user = validateUser(userEmail);

        Discipline newDiscipline = disciplineRepository.findById(id_discipline)
                .orElseThrow(() -> new DisciplineNotExistsException());

        comment.setUser(user);

        newDiscipline.getComments().add(comment);

        return commentRepository.save(comment);

    }

    private User validateUser(Optional<String> email) {
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