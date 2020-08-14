package com.mini_projeto.crdb.services;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Optional;

import com.mini_projeto.crdb.dtos.CommentDTO;
import com.mini_projeto.crdb.exceptions.CommentNotBelongException;
import com.mini_projeto.crdb.exceptions.CommentNotExistsException;
import com.mini_projeto.crdb.exceptions.CommentRemovedException;
import com.mini_projeto.crdb.exceptions.DisciplineNotFoundException;
import com.mini_projeto.crdb.exceptions.UserInvalidException;
import com.mini_projeto.crdb.models.Comment;
import com.mini_projeto.crdb.models.Discipline;
import com.mini_projeto.crdb.models.User;
import com.mini_projeto.crdb.repositories.CommentRepository;
import com.mini_projeto.crdb.repositories.DisciplineRepository;
import com.mini_projeto.crdb.repositories.UserRepository;

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

    public Comment insert(String headerAuthorization, String id_discipline, CommentDTO commentDTO)
            throws DisciplineNotFoundException {

        // ler o token e recuperar o subject
        Optional<String> userEmail = jwtService.recoverUser(headerAuthorization);

        // verifica se existe um usuario correspondende ao token
        User user = validateUser(userEmail);

        Discipline newDiscipline = disciplineRepository.findById(id_discipline)
                .orElseThrow(() -> new DisciplineNotFoundException());

        Comment comment = new Comment(commentDTO);

        comment.setUser(user);

        comment.setDiscipline(newDiscipline);

        return commentRepository.save(comment);

    }

    public Comment update(String headerAuthorization, Long id, CommentDTO commentDTO)
            throws DisciplineNotFoundException, CommentRemovedException, CommentNotBelongException,
            CommentNotExistsException {

        // ler o token e recuperar o subject
        Optional<String> userEmail = jwtService.recoverUser(headerAuthorization);

        // verifica se existe um usuario correspondende ao token
        validateUser(userEmail);

        Comment comment = commentRepository.findById(id).orElseThrow(() -> new CommentNotExistsException());

        if (!comment.getUser().getEmail().equals(userEmail.get())) {
            throw new CommentNotBelongException("Commentario não pertence ao usuario");
        }

        if (comment.getDeleted()) {
            throw new CommentRemovedException("Commentario ja foi deletado");
        }

        comment.setComment(commentDTO.getComment());

        comment = setDateTime(comment);

        comment.setId(id);

        return commentRepository.save(comment);

    }

    public Comment delete(String headerAuthorization, Long id)
            throws CommentRemovedException, CommentNotBelongException {

        Optional<String> userEmail = jwtService.recoverUser(headerAuthorization);

        validateUser(userEmail);

        Comment comment = commentRepository.findById(id).orElseThrow(() -> new CommentNotExistsException());

        if (!comment.getUser().getEmail().equals(userEmail.get())) {
            throw new CommentNotBelongException("Commentario não pertence ao usuario");
        }

        if (comment.getDeleted()) {
            throw new CommentRemovedException("Commentario ja foi deletado");
        }

        comment.setDeleted(true);

        comment = setDateTime(comment);

        comment.setId(id);

        return commentRepository.save(comment);

    }

    private Comment setDateTime(Comment comment) {
        comment.setLocalDate(LocalDate.now());
        comment.setLocalTime(LocalTime.now());
        return comment;
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