package ru.spbstu.opd.business;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

import java.lang.reflect.InvocationTargetException;

public enum ReportType {
    JSON(JsonMapper.class), XML(XmlMapper.class);

    private final Class<? extends ObjectMapper> mapper;

    ReportType(Class<? extends ObjectMapper> mapper) {
        this.mapper = mapper;
    }

    public ObjectMapper getMapper() {
        try {
            return mapper.getConstructor().newInstance();
        } catch (InstantiationException | InvocationTargetException | IllegalAccessException | NoSuchMethodException e) {
            e.printStackTrace();
        }
        return null;
    }
}
