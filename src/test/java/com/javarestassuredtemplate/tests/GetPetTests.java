package com.javarestassuredtemplate.tests;

import com.javarestassuredtemplate.bases.TestBase;
import com.javarestassuredtemplate.requests.GetPetRequest;
import io.restassured.response.ValidatableResponse;
import org.apache.http.HttpStatus;
import org.testng.annotations.Test;
import static org.hamcrest.Matchers.*;

public class GetPetTests extends TestBase {
    GetPetRequest getPetRequest;

    @Test
    public void buscarPetExistente(){
        //Parâmetros
        int petId = 9999;
        int statusCodeEsperado = HttpStatus.SC_OK;
        int categoryId = 9999;
        String categoryName = "felinos";
        String name = "Shepherd";
        String photoUrl = "http://photodogatito.com/image123.png";
        int tagId = 9999;
        String tagName = "macho";
        String status = "available";

        //Fluxo
        getPetRequest = new GetPetRequest(petId);
        ValidatableResponse response = getPetRequest.executeRequest();

        //Asserções
        response.statusCode(statusCodeEsperado);
        response.body("id",equalTo(petId),
                "category.id", equalTo(categoryId),
                        "category.name", equalTo(categoryName),
                        "name", equalTo(name),
                        "photoUrls[0]", equalTo(photoUrl),
                        "tags[0].id", equalTo(tagId),
                        "tags[0].name", equalTo(tagName),
                        "status", equalTo(status));
    }
}
