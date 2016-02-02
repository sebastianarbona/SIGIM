// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package com.core.irrigacion.domain;

import com.core.irrigacion.domain.Matafuego;
import flexjson.JSONDeserializer;
import flexjson.JSONSerializer;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

privileged aspect Matafuego_Roo_Json {
    
    public String Matafuego.toJson() {
        return new JSONSerializer()
        .exclude("*.class").serialize(this);
    }
    
    public String Matafuego.toJson(String[] fields) {
        return new JSONSerializer()
        .include(fields).exclude("*.class").serialize(this);
    }
    
    public static Matafuego Matafuego.fromJsonToMatafuego(String json) {
        return new JSONDeserializer<Matafuego>()
        .use(null, Matafuego.class).deserialize(json);
    }
    
    public static String Matafuego.toJsonArray(Collection<Matafuego> collection) {
        return new JSONSerializer()
        .exclude("*.class").serialize(collection);
    }
    
    public static String Matafuego.toJsonArray(Collection<Matafuego> collection, String[] fields) {
        return new JSONSerializer()
        .include(fields).exclude("*.class").serialize(collection);
    }
    
    public static Collection<Matafuego> Matafuego.fromJsonArrayToMatafuegoes(String json) {
        return new JSONDeserializer<List<Matafuego>>()
        .use("values", Matafuego.class).deserialize(json);
    }
    
}
