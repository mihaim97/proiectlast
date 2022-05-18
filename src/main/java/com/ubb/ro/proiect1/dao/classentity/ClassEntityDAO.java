package com.ubb.ro.proiect1.dao.classentity;

import com.ubb.ro.proiect1.entity.ClassEntity;
import com.ubb.ro.proiect1.entity.SessionEntity;

import java.util.List;

public interface ClassEntityDAO {

    void persist(ClassEntity sessionEntity);

    void remove(ClassEntity classEntity);

    ClassEntity findById(int id);

}
