package com.example.endterm1.cache;

import com.example.endterm1.model.Student;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StudentCache {

    private static StudentCache instance;
    private final Map<String, List<Student>> cache = new HashMap<>();

    private StudentCache() {}

    public static StudentCache getInstance() {
        if (instance == null) {
            instance = new StudentCache();
        }
        return instance;
    }

    public void put(String key, List<Student> students) {
        cache.put(key, students);
    }

    public List<Student> get(String key) {
        return cache.get(key);
    }

    public boolean contains(String key) {
        return cache.containsKey(key);
    }

    public void clear() {
        cache.clear();
    }
}
