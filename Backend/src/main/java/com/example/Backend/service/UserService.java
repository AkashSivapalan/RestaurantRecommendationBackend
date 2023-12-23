package com.example.Backend.service;

import com.example.Backend.api.model.User;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UserService {

    private List<User> userList;



    public UserService(){
        userList= new ArrayList<>();
        User user1 = new User(1,"Ida","Toronto","ida@gmail.com","password");
        User user2 = new User(2,"Hans","NYC","hans@gmail.com","password");
        User user3 = new User(3,"Lars","Toronto","lars@gmail.com","password");
        User user4 = new User(4,"Ben","NYC","ben@gmail.com","password");
        User user5 = new User(5,"Eva","Vancouver", "eva@gmail.com","password");

        userList.addAll(Arrays.asList(user1,user2,user3,user4,user5));
    }

    public Optional<User> getUser(Integer id){
        Optional optional = Optional.empty();
        for (User user:userList){
            if(id==user.getId()){
                optional = Optional.of(user);
                return optional;
            }
        }

        return optional;

    }

    public Optional<User> putUser(Integer id,String fieldName,String val){
        Optional optional = Optional.empty();
        for (User user:userList){
            if(id==user.getId()){
                if (fieldName.equals("email")){
                    user.setEmail(val);
                }else if (fieldName.equals("password")){
                    user.setPassword(val);
                }else if (fieldName.equals("city")){
                    user.setCity(val);
                }else if (fieldName.equals("name")){
                    user.setName(val);
                }

                optional = Optional.of(user);
                return optional;
            }
        }


        return optional;

    }



    public Optional<User> deleteUser(Integer id){
        Optional optional = Optional.empty();
        Iterator<User> iterator = userList.iterator();
        while (iterator.hasNext()) {
            User user = iterator.next();
            if (user.getId() == id) {
                optional = Optional.of(user);
                iterator.remove();
                return optional;
            }
        }
        return optional;

    }
}
