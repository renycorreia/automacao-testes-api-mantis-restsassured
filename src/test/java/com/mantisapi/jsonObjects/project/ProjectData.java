package com.mantisapi.jsonObjects.project;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProjectData {
    private String name;
    Status StatusObject;
    private String description;
    private boolean enabled;
    View_state View_stateObject;
}

