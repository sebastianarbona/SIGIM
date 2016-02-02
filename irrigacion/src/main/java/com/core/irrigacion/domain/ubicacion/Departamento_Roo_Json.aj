// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package com.core.irrigacion.domain.ubicacion;

import com.core.irrigacion.domain.ubicacion.Departamento;
import flexjson.JSONDeserializer;
import flexjson.JSONSerializer;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

privileged aspect Departamento_Roo_Json {
    
    public String Departamento.toJson() {
        return new JSONSerializer()
        .exclude("*.class").serialize(this);
    }
    
    public String Departamento.toJson(String[] fields) {
        return new JSONSerializer()
        .include(fields).exclude("*.class").serialize(this);
    }
    
    public static Departamento Departamento.fromJsonToDepartamento(String json) {
        return new JSONDeserializer<Departamento>()
        .use(null, Departamento.class).deserialize(json);
    }
    
    public static String Departamento.toJsonArray(Collection<Departamento> collection) {
        return new JSONSerializer()
        .exclude("*.class").serialize(collection);
    }
    
    public static String Departamento.toJsonArray(Collection<Departamento> collection, String[] fields) {
        return new JSONSerializer()
        .include(fields).exclude("*.class").serialize(collection);
    }
    
    public static Collection<Departamento> Departamento.fromJsonArrayToDepartamentoes(String json) {
        return new JSONDeserializer<List<Departamento>>()
        .use("values", Departamento.class).deserialize(json);
    }
    
}
