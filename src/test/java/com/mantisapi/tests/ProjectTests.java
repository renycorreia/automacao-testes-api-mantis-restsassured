package com.mantisapi.tests;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.javafaker.Faker;
import com.mantisapi.bases.TestBase;
import com.mantisapi.jsonObjects.project.ProjectData;
import com.mantisapi.jsonObjects.project.ProjectFull;
import com.mantisapi.jsonObjects.project.ProjectNewName;
import com.mantisapi.requests.project.DeleteProjectsRequest;
import com.mantisapi.requests.project.GetProjectsRequest;
import com.mantisapi.requests.project.PatchProjectsRequest;
import com.mantisapi.requests.project.PostProjectsRequest;
import io.restassured.response.ValidatableResponse;
import org.apache.http.HttpStatus;
import org.testng.annotations.*;

import java.util.List;
import java.util.Locale;

import static org.hamcrest.Matchers.*;

public class ProjectTests extends TestBase {
    GetProjectsRequest getProjectsRequest;
    PostProjectsRequest postProjectsRequest;
    PatchProjectsRequest patchProjectsRequest;
    DeleteProjectsRequest deleteProjectsRequest;

    @Test
    public void listarProjetos(){
        int statusCodeEsperado = HttpStatus.SC_OK;

        //Fluxo
        ValidatableResponse response = retornarTodosProjetos();

        //Asserções
        response.statusCode(statusCodeEsperado);

        response.body(
                "projects", isA(List.class)
                , "projects.id[0]", isA(Integer.class)
                , "projects.name[0]", isA(String.class)
                , "projects.status[0]", isA(Object.class)
                , "projects.status.id[0]", isA(Integer.class)
                , "projects.status.name[0]", isA(String.class)
                , "projects.status.label[0]", isA(String.class)
                , "projects.description[0]", isA(String.class)
                , "projects.enabled[0]", isA(boolean.class)
                , "projects.view_state[0]", isA(Object.class)
                , "projects.view_state.id[0]", isA(Integer.class)
                , "projects.view_state.name[0]", isA(String.class)
                , "projects.view_state.label[0]", isA(String.class)
                , "projects.access_level[0]", isA(Object.class)
                , "projects.access_level.id[0]", isA(Integer.class)
                , "projects.access_level.name[0]", isA(String.class)
                , "projects.access_level.label[0]", isA(String.class)
                , "projects.custom_fields[0]", isA(List.class)
                , "projects.versions[0]", isA(List.class)
                , "projects.categories[0]", isA(List.class)
                , "projects.categories[0].id[0]", isA(Integer.class)
                , "projects.categories[0].name[0]", isA(String.class)
                , "projects.categories[0].project[0]", isA(Object.class)
                , "projects.categories[0].project[0].id", isA(Integer.class)
                , "projects.categories[0].project[0].name", equalTo(null)

        );
    }

    @Test
    public void criarProjeto()
    {
        Faker faker = new Faker(new Locale("pt_BR"));

        int statusCodeEsperado = HttpStatus.SC_CREATED;

        ProjectData project = new ProjectData();
        project.setName("Project "+faker.food().fruit());
        project.setDescription(faker.howIMetYourMother().catchPhrase());
        project.setEnabled(true);

        ValidatableResponse response = criarProjeto(project);

        response.statusCode(statusCodeEsperado);

        response.body("project", isA(Object.class)
                , "project.id", isA(Integer.class)
                , "project.name", isA(String.class)
                , "project.status", isA(Object.class)
                , "project.status.id", isA(Integer.class)
                , "project.status.name", isA(String.class)
                , "project.status.label", isA(String.class)
                , "project.description", isA(String.class)
                , "project.enabled", isA(boolean.class)
                , "project.view_state", isA(Object.class)
                , "project.view_state.id", isA(Integer.class)
                , "project.view_state.name", isA(String.class)
                , "project.view_state.label", isA(String.class)
                , "project.access_level", isA(Object.class)
                , "project.access_level.id", isA(Integer.class)
                , "project.access_level.name", isA(String.class)
                , "project.access_level.label", isA(String.class)
                , "project.custom_fields", isA(List.class)
                , "project.versions", isA(List.class)
                , "project.categories", isA(List.class)
        );

        response.body("project.name", equalTo(project.getName())
                , "project.description", equalTo(project.getDescription())
        );
    }

    @Test
    public void editarProjeto()
    {
        Faker faker = new Faker(new Locale("pt_BR"));

        int statusCodeEsperado = HttpStatus.SC_OK;

        ProjectData project = new ProjectData();
        project.setName("Project "+faker.food().fruit());
        project.setDescription(faker.howIMetYourMother().catchPhrase());
        project.setEnabled(true);

        ValidatableResponse response = criarProjeto(project);

        /*int id = response
                .extract()
                .path("project.id")
                ;*/

        int id = response
                .extract()
                .jsonPath()
                .getInt("project.id")
                ;

        StringBuilder projectNameReverso = new  StringBuilder();
        projectNameReverso.append(project.getName()).reverse();

        ProjectNewName projectNewName = new ProjectNewName();

        projectNewName.setName(projectNameReverso.toString());

        patchProjectsRequest = new PatchProjectsRequest(id, projectNewName);

        response = patchProjectsRequest.executeRequest();

        response.statusCode(statusCodeEsperado);

        response.body("project.id", equalTo(id)
                , "project.name", equalTo(projectNameReverso.toString())
        );
    }

    @Test
    public void excluirProjeto()
    {
        Faker faker = new Faker(new Locale("pt_BR"));

        int statusCodeEsperado = HttpStatus.SC_OK;

        ProjectData project = new ProjectData();
        project.setName("Project "+faker.food().fruit());
        project.setDescription(faker.howIMetYourMother().catchPhrase());
        project.setEnabled(true);

        ValidatableResponse response = criarProjeto(project);

        int idProject = response
                .extract()
                .jsonPath()
                .getInt("project.id")
        ;

        response = excluirProjeto(idProject);

        response.statusCode(statusCodeEsperado);
    }

    @Test
    public void criarProjetoNomeVazio()
    {
        Faker faker = new Faker(new Locale("pt_BR"));

        int statusCodeEsperado = HttpStatus.SC_INTERNAL_SERVER_ERROR;

        ProjectData project = new ProjectData();
        project.setName("");
        project.setDescription(faker.howIMetYourMother().catchPhrase());
        project.setEnabled(true);

        ValidatableResponse response = criarProjeto(project);

        response.statusCode(statusCodeEsperado);
    }

    @AfterClass
    public void excluirTodosProjetos()
    {
        ValidatableResponse response = retornarTodosProjetos();

        List<Integer> ids = response
                        .extract()
                        .path("projects.id");

        for(int idProject : ids){
            response = excluirProjeto(idProject);

            response.statusCode(HttpStatus.SC_OK);
        }
    }

    public ValidatableResponse criarProjeto(ProjectData project)
    {
        postProjectsRequest = new PostProjectsRequest(project);

        ValidatableResponse response = postProjectsRequest.executeRequest();

        return response;
    }

    public ValidatableResponse retornarTodosProjetos()
    {
        getProjectsRequest = new GetProjectsRequest();
        ValidatableResponse response = getProjectsRequest.executeRequest();

        return response;
    }

    public ValidatableResponse excluirProjeto(int idProject)
    {
        deleteProjectsRequest = new DeleteProjectsRequest(idProject);

        ValidatableResponse response = deleteProjectsRequest.executeRequest();

        return response;
    }
}
