package fact.it.p4_backend.controller;

import fact.it.p4_backend.exception.UserNotFoundException;

import java.util.Optional;

public class CheckOptionalUserNotEmpty {
    public static <T> T checkNotEmpty(Optional<T> OptionalUser) throws UserNotFoundException {
        if (OptionalUser.isEmpty()){
            throw new UserNotFoundException();
        }
        return OptionalUser.get();
    }
}
