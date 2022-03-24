package org.p10.PetStore.Controllers;

import com.google.gson.Gson;
import org.p10.PetStore.Models.Pojo.UserPojo;
import org.p10.PetStore.Models.User;
import org.p10.PetStore.Models.UserStatus;
import org.p10.PetStore.Repositories.UserRepository;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/v1")
public class UserController {

    private final UserRepository userRepository;
    private final Gson gson;

    public UserController() {
        this.gson = new Gson();
        this.userRepository = new UserRepository();
    }

    @POST
    @Path("/user")
    @Produces(MediaType.TEXT_PLAIN)
    public Response insertUser(UserPojo userPojo) {
        User user = new User(userPojo.getId(), userPojo.getUserName(),
                userPojo.getFirstName(), userPojo.getLastName(),
                userPojo.getEmail(), userPojo.getPasswordHash(),
                userPojo.getSalt(), userPojo.getPhone(),
                UserStatus.values()[userPojo.getStatus()]);
        int affectedRows = userRepository.insertUser(user);
        if (affectedRows > 0) {
            return Response.ok(affectedRows).build();
        } else {
            return Response.serverError().build();
        }
    }

    @PUT
    @Path("/user")
    @Produces(MediaType.TEXT_PLAIN)
    public Response updateUser(UserPojo userPojo) {
        User user = new User(userPojo.getId(), userPojo.getUserName(),
                userPojo.getFirstName(), userPojo.getLastName(),
                userPojo.getEmail(), userPojo.getPasswordHash(),
                userPojo.getSalt(), userPojo.getPhone(),
                UserStatus.values()[userPojo.getStatus()]);
        user = userRepository.updateUser(user);
        if (user != null) {
            return Response.ok(gson.toJson(user)).build();
        } else {
            return Response.serverError().build();
        }
    }

    @DELETE
    @Path("/user/{username}")
    @Produces(MediaType.TEXT_PLAIN)
    public Response deleteUser(@PathParam("username") String username) {
        String name = userRepository.deleteUser(username);
        if (name != null) {
            return Response.ok(name).build();
        } else {
            return Response.serverError().build();
        }
    }

    @GET
    @Path("/user/{username}")
    @Produces(MediaType.TEXT_PLAIN)
    public Response getUser(@PathParam("username") String username) {
        User user = userRepository.getUser(username);
        return Response.ok(gson.toJson(user)).build();
    }
}
