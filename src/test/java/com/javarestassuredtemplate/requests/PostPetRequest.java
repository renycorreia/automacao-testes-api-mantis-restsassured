package com.javarestassuredtemplate.requests;

import com.javarestassuredtemplate.bases.RequestRestBase;
import com.javarestassuredtemplate.jsonObjects.Category;
import com.javarestassuredtemplate.jsonObjects.Pet;
import com.javarestassuredtemplate.jsonObjects.Tag;
import com.javarestassuredtemplate.utils.GeneralUtils;
import io.restassured.http.Method;

public class PostPetRequest extends RequestRestBase {
    public PostPetRequest(){
        requestService = "/pet";
        method = Method.POST;
    }

    public void setJsonBodyUsingJsonFile(int id,
                            int categoryId,
                            String categoryName,
                            String name,
                            String photoUrl,
                            int tagId,
                            String tagName,
                            String status){
        jsonBody = GeneralUtils.readFileToAString("src/test/java/com/javarestassuredtemplate/jsons/PostPetJson.json")
                               .replace("$id", String.valueOf(id))
                               .replace("$categoryId", String.valueOf(categoryId))
                               .replace("$categoryName", categoryName)
                               .replace("$name", name)
                               .replace("$photoUrl", photoUrl)
                               .replace("$tagId", String.valueOf(tagId))
                               .replace("$tagName", tagName)
                               .replace("$status", status);
    }

    public void setJsonBodyUsingJavaObject(int id,
                                         int categoryId,
                                         String categoryName,
                                         String name,
                                         String photoUrl,
                                         int tagId,
                                         String tagName,
                                         String status){
        jsonBody = new Pet(id,
                           new Category(categoryId, categoryName),
                           name,
                           new String[]{photoUrl},
                           new Tag[]{new Tag(tagId, tagName)},
                           status);
    }

    public void setJsonBodyUsingJavaObject(Object jsonObject){
        jsonBody = jsonObject;
    }
}
