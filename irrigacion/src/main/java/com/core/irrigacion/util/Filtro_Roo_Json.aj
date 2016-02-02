// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package com.core.irrigacion.util;

import com.core.irrigacion.util.Filtro;
import flexjson.JSONDeserializer;
import flexjson.JSONSerializer;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

privileged aspect Filtro_Roo_Json {
    
    public String Filtro.toJson() {
        return new JSONSerializer()
        .exclude("*.class").serialize(this);
    }
    
    public String Filtro.toJson(String[] fields) {
        return new JSONSerializer()
        .include(fields).exclude("*.class").serialize(this);
    }
    
    public static Filtro Filtro.fromJsonToFiltro(String json) {
        return new JSONDeserializer<Filtro>()
        .use(null, Filtro.class).deserialize(json);
    }
    
    public static String Filtro.toJsonArray(Collection<Filtro> collection) {
        return new JSONSerializer()
        .exclude("*.class").serialize(collection);
    }
    
    public static String Filtro.toJsonArray(Collection<Filtro> collection, String[] fields) {
        return new JSONSerializer()
        .include(fields).exclude("*.class").serialize(collection);
    }
    
    public static Collection<Filtro> Filtro.fromJsonArrayToFiltroes(String json) {
        return new JSONDeserializer<List<Filtro>>()
        .use("values", Filtro.class).deserialize(json);
    }
    
}
