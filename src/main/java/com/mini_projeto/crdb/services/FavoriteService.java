package com.mini_projeto.crdb.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.mini_projeto.crdb.exceptions.DisciplineNotFoundException;
import com.mini_projeto.crdb.exceptions.FavoriteNotBelongException;
import com.mini_projeto.crdb.exceptions.UserInvalidException;
import com.mini_projeto.crdb.models.Discipline;
import com.mini_projeto.crdb.models.Favorite;
import com.mini_projeto.crdb.models.User;
import com.mini_projeto.crdb.repositories.DisciplineRepository;
import com.mini_projeto.crdb.repositories.FavoriteRepository;
import com.mini_projeto.crdb.repositories.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FavoriteService {

    @Autowired
    private FavoriteRepository favoriteRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private DisciplineRepository disciplineRepository;

    @Autowired
    private JWTService jwtService;

    public Favorite insert(String headerAuthorization, String id_discipline)
            throws DisciplineNotFoundException, FavoriteNotBelongException {

        Favorite favoriteSave;

        Optional<String> userEmail = jwtService.recoverUser(headerAuthorization);

        User user = validateUser(userEmail);

        Discipline discipline = disciplineRepository.findById(id_discipline)
                .orElseThrow(() -> new DisciplineNotFoundException());

        Optional<Favorite> favorite = favoriteRepository.findByCode(id_discipline, userEmail.get());

        if (!favorite.isPresent()) {

            favoriteSave = new Favorite();

            favoriteSave.setUser(user);

            favoriteSave.setDiscipline(discipline);

        } else if (!favorite.get().getUser().getEmail().equals(userEmail.get())) {
            throw new FavoriteNotBelongException("Favoritar não pertence ao usuario");
        } else {
            favoriteSave = favorite.get();

            favoriteSave.setActive(!favoriteSave.getActive());
        }

        return favoriteRepository.save(favoriteSave);

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