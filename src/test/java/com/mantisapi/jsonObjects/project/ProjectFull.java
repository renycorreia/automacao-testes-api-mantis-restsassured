package com.mantisapi.jsonObjects.project;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProjectFull {
    private int id;
    private ProjectData projectData;
    Access_level Access_levelObject;
    ArrayList<CustomField>custom_fields = new ArrayList<>();
    ArrayList<Version>versions = new ArrayList<>();
    ArrayList<Category>categories = new ArrayList<>();
}

