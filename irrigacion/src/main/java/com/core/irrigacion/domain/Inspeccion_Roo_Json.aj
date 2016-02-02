// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package com.core.irrigacion.domain;

import com.core.irrigacion.domain.Inspeccion;
import flexjson.JSONDeserializer;
import flexjson.JSONSerializer;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

privileged aspect Inspeccion_Roo_Json {
    
    public String Inspeccion.toJson() {
        return new JSONSerializer()
        .exclude("*.class").serialize(this);
    }
    
    public String Inspeccion.toJson(String[] fields) {
        return new JSONSerializer()
        .include(fields).exclude("*.class").serialize(this);
    }
    
    public static Inspeccion Inspeccion.fromJsonToInspeccion(String json) {
        return new JSONDeserializer<Inspeccion>()
        .use(null, Inspeccion.class).deserialize(json);
    }
    
    public static String Inspeccion.toJsonArray(Collection<Inspeccion> collection) {
        return new JSONSerializer()
        .exclude("*.class").serialize(collection);
    }
    
    public static String Inspeccion.toJsonArray(Collection<Inspeccion> collection, String[] fields) {
        return new JSONSerializer()
        .include(fields).exclude("*.class").serialize(collection);
    }
    
    public static Collection<Inspeccion> Inspeccion.fromJsonArrayToInspeccions(String json) {
        return new JSONDeserializer<List<Inspeccion>>()
        .use("values", Inspeccion.class).deserialize(json);
    }
    
}