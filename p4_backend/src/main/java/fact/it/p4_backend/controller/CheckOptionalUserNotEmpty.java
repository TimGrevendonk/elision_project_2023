package fact.it.p4_backend.controller;

import fact.it.p4_backend.exception.UserNotFoundException;

public class CheckOptionalUserNotEmpty {
    public static <T> T checkNotEmpty(T OptionalUser) throws UserNotFoundException {
        if (OptionalUser == null){
            throw new UserNotFoundException();
        }
        return OptionalUser;
    }
}
