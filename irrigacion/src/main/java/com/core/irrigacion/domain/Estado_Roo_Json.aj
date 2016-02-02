// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package com.core.irrigacion.domain;

import com.core.irrigacion.domain.Estado;
import flexjson.JSONDeserializer;
import flexjson.JSONSerializer;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

privileged aspect Estado_Roo_Json {
    
    public String Estado.toJson() {
        return new JSONSerializer()
        .exclude("*.class").serialize(this);
    }
    
    public String Estado.toJson(String[] fields) {
        return new JSONSerializer()
        .include(fields).exclude("*.class").serialize(this);
    }
    
    public static Estado Estado.fromJsonToEstado(String json) {
        return new JSONDeserializer<Estado>()
        .use(null, Estado.class).deserialize(json);
    }
    
    public static String Estado.toJsonArray(Collection<Estado> collection) {
        return new JSONSerializer()
        .exclude("*.class").serialize(collection);
    }
    
    public static String Estado.toJsonArray(Collection<Estado> collection, String[] fields) {
        return new JSONSerializer()
        .include(fields).exclude("*.class").serialize(collection);
    }
    
    public static Collection<Estado> Estado.fromJsonArrayToEstadoes(String json) {
        return new JSONDeserializer<List<Estado>>()
        .use("values", Estado.class).deserialize(json);
    }
    
}
