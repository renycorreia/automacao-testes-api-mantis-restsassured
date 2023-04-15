package com.mantisapi.dbsteps;

import com.mantisapi.utils.DBUtils;
import com.mantisapi.utils.GeneralUtils;

public class UsuariosDBSteps {
    private static String queriesPath = System.getProperty("user.dir")+"/src/test/java/com/mantisapi/queries/";

    public static String retornaSenhaDoUsuario(String userName){
        String query = GeneralUtils.readFileToAString(queriesPath + "retornaSenhaUsuarioQuery");
        query.replace("$nome", userName);

        return DBUtils.getQueryResult(query).get(0);
    }
}
