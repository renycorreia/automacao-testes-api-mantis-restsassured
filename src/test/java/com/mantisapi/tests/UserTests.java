package com.mantisapi.tests;

import com.github.javafaker.Faker;
import com.mantisapi.bases.TestBase;
import com.mantisapi.jsonObjects.user.*;
import com.mantisapi.requests.user.*;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import io.restassured.response.ValidatableResponse;
import org.apache.http.HttpStatus;
import org.testng.annotations.Test;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Locale;

import static com.mantisapi.GlobalParameters.ENVIROMENT;
import static org.hamcrest.Matchers.*;

public class UserTests extends TestBase {
    GetUserLogadoRequest getUserLogadoRequest;
    PostUserRequest postUserRequest;
    DeleteUserRequest deleteUserRequest;

    @Test
    public void retornarDadosUsuarioLogado(){
        getUserLogadoRequest = new GetUserLogadoRequest();
        ValidatableResponse response = getUserLogadoRequest.executeRequest();

        response.statusCode(HttpStatus.SC_OK);

        response.body("id", equalTo(1)
                , "name", equalTo("administrator")
                , "real_name", equalTo("joaquim estevez")
                , "email", equalTo("joaquim.estevezz@gmail.com")
                , "language", isA(String.class)
                , "timezone", equalTo("UTC")
                , "access_level", isA(Object.class)
                , "access_level.id", equalTo(90)
                , "access_level.name", equalTo("administrator")
                , "access_level.label", isA(String.class)
                , "projects", isA(List.class)
        );
    }

    @Test
    public void criarUsuarioDadosMinimos(){

        Faker faker = new Faker(new Locale("pt_BR"));

        User usuario = new User();
        usuario.setUsername(faker.name().username());
        usuario.setPassword(faker.food().sushi());
        usuario.setReal_name(faker.name().fullName());
        usuario.setEmail(usuario.getUsername()+"@gmail.com");
        usuario.setAccess_level(new Access_level("developer"));
        usuario.setEnabled(true);
        usuario.setProtectd(false);

            postUserRequest = new PostUserRequest(usuario);
            ValidatableResponse response = postUserRequest.executeRequest();

            response.statusCode(HttpStatus.SC_CREATED);

            response.body("user.name", equalTo(usuario.getUsername())
                    , "user.real_name", equalTo(usuario.getReal_name())
                    , "user.email", equalTo(usuario.getEmail())
                    , "user.access_level.name", equalTo(usuario.getAccess_level().getName())
            );

        if(ENVIROMENT.equals("hml")){
            int id = response
                    .extract()
                    .jsonPath()
                    .getInt("user.id")
                    ;

            excluirUsuario(id); //o número de usuário no ambiente trial é limitado, então todos os usuários do teste serão excluídos após as validações
        }
    }

    @Test
    public void criarUsuarioDadosMinimosCSV() throws IOException {
        Reader reader = Files.newBufferedReader(Paths.get("src/test/java/com/mantisapi/files/users.csv"));

        CsvToBean<User> csvToBean = new CsvToBeanBuilder(reader)
                .withType(User.class)
                .build();

        List<User> usuarios = csvToBean.parse();

        for (User usuario : usuarios)
        {
            usuario.setAccess_level(new Access_level(usuario.getAccess_level_name()));

            postUserRequest = new PostUserRequest(usuario);
            ValidatableResponse response = postUserRequest.executeRequest();

            response.statusCode(HttpStatus.SC_CREATED);

            response.body("user.name", equalTo(usuario.getUsername())
                    , "user.real_name", equalTo(usuario.getReal_name())
                    , "user.email", equalTo(usuario.getEmail())
                    , "user.access_level.name", equalTo(usuario.getAccess_level().getName())
            );

            int id = response
                    .extract()
                    .jsonPath()
                    .getInt("user.id")
                    ;

            excluirUsuario(id); //excluindo usuário uma vez que não é possível criar usuário duplicado
        }
    }

    @Test
    public void excluirUsuario(){

        Faker faker = new Faker(new Locale("pt_BR"));

        User usuario = new User();
        usuario.setUsername(faker.name().username());
        usuario.setPassword(faker.food().sushi());
        usuario.setReal_name(faker.name().fullName());
        usuario.setEmail(usuario.getUsername()+"@gmail.com");
        usuario.setAccess_level(new Access_level("developer"));
        usuario.setEnabled(true);
        usuario.setProtectd(false);

        postUserRequest = new PostUserRequest(usuario);
        ValidatableResponse response = postUserRequest.executeRequest();

        response.statusCode(HttpStatus.SC_CREATED);

        int id = response
                .extract()
                .jsonPath()
                .getInt("user.id")
                ;

        excluirUsuario(id);
    }

    public void excluirUsuario(int idUsuario){
        deleteUserRequest = new DeleteUserRequest(idUsuario);

        ValidatableResponse response = deleteUserRequest.executeRequest();

        response.statusCode(HttpStatus.SC_NO_CONTENT);
    }
}
