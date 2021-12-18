package com.cuatroa.retotres.service;

import com.cuatroa.retotres.model.User;
import com.cuatroa.retotres.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.converter.xml.SourceHttpMessageConverter;
import org.springframework.stereotype.Service;

import java.io.Console;
import java.util.List;
import java.util.Optional;

/**
 *
 * @author desaextremo
 */
@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    /**
     * @return List<User>
     */
    public List<User> getAll() {
        return userRepository.getAll();
    }

    /**
     * @param id
     * @return Optional<User>
     */
    public Optional<User> getUser(int id) {

        return userRepository.getUser(id);
    }

    /**
     * @param user
     * @return User
     */
    public User create(User user) {

        // obtiene el maximo id existente en la coleccion
        Optional<User> userIdMaximo = userRepository.lastUserId();

        // si el id del Usaurio que se recibe como parametro es nulo, entonces valida el
        // maximo id existente en base de datos
        if (user.getId() == null) {
            // valida el maximo id generado, si no hay ninguno aun el primer id sera 1
            if (userIdMaximo.isEmpty())
                user.setId(1);
            // si retorna informacion suma 1 al maximo id existente y lo asigna como el
            // codigo del usuario
            else
                user.setId(userIdMaximo.get().getId() + 1);
        }

        Optional<User> e = userRepository.getUser(user.getId());
        if (e.isEmpty()) {
            if (emailExists(user.getEmail()) == false) {
                return userRepository.create(user);
            } else {
                return user;
            }
        } else {
            return user;
        }

    }

    /**
     * @param user
     * @return User
     */
    public User update(User user) {

        if (user.getId() != null) {
            Optional<User> userDb = userRepository.getUser(user.getId());
            if (!userDb.isEmpty()) {
                if (user.getIdentification() != null) {
                    userDb.get().setIdentification(user.getIdentification());
                }
                if (user.getName() != null) {
                    userDb.get().setName(user.getName());
                }
                if (user.getAddress() != null) {
                    userDb.get().setAddress(user.getAddress());
                }
                if (user.getCellPhone() != null) {
                    userDb.get().setCellPhone(user.getCellPhone());
                }
                if (user.getEmail() != null) {
                    userDb.get().setEmail(user.getEmail());
                }
                if (user.getPassword() != null) {
                    userDb.get().setPassword(user.getPassword());
                }
                if (user.getZone() != null) {
                    userDb.get().setZone(user.getZone());
                }

                userRepository.update(userDb.get());
                return userDb.get();
            } else {
                return user;
            }
        } else {
            return user;
        }
    }

    /**
     * @param userId
     * @return boolean
     */
    public boolean delete(int userId) {
        Boolean aBoolean = getUser(userId).map(user -> {
            userRepository.delete(user);
            return true;
        }).orElse(false);
        return aBoolean;
    }

    /**
     * @param email
     * @return boolean
     */
    public boolean emailExists(String email) {
        return userRepository.emailExists(email);
    }

    /**
     * @param email
     * @param password
     * @return User
     */
    public User authenticateUser(String email, String password) {
        Optional<User> usuario = userRepository.authenticateUser(email, password);

        System.out.println("usuario: " + email + " " + password);

        return usuario.get();

    }
    
    public List<User> getByMonthBirthDay(String month){
        return userRepository.getByMonthBirthDay(month);
    }

}
