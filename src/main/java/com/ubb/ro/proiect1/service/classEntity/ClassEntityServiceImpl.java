package com.ubb.ro.proiect1.service.classEntity;

import com.ubb.ro.proiect1.dao.UserDAO;
import com.ubb.ro.proiect1.dao.classentity.ClassEntityDAO;
import com.ubb.ro.proiect1.dao.session.SessionDAO;
import com.ubb.ro.proiect1.entity.ClassEntity;
import com.ubb.ro.proiect1.entity.SessionEntity;
import com.ubb.ro.proiect1.entity.User;
import com.ubb.ro.proiect1.util.SingleResultFromList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ClassEntityServiceImpl implements ClassEntityService {

    @Autowired
    private ClassEntityDAO  classEntityDAO;

    @Autowired
    private SessionDAO sessionDAO;

    @Autowired
    private UserDAO userDAO;


    @Override
    @Transactional
    public void addClass(ClassEntityDTO classEntityDTO) {
        User user = this.userDAO.searchById(classEntityDTO.getTeacherID());
        ClassEntity classEntity = new ClassEntity(classEntityDTO.getName(), classEntityDTO.getSemester(), classEntityDTO.getCredits(), user);
        classEntityDAO.persist(classEntity);
    }

    @Override
    @Transactional
    public void delete(int id) {
        ClassEntity classEntity = this.classEntityDAO.findById(id);
        classEntityDAO.remove(classEntity);

    }

    @Override
    @Transactional
    public ClassEntityDTO findById(int id) {
        ClassEntity classEntity = classEntityDAO.findById(id);
        return new ClassEntityDTO(classEntity.getId(), classEntity.getName(), classEntity.getSemester(), classEntity.getCredits(), classEntity.getTeacherId().getId());
    }

    @Override
    @Transactional
    public List<ClassEntityDTOView> getClasses(Authentication authentication) {
        User user = SingleResultFromList.getSingleResult(this.userDAO.searchByName(authentication.getName()));
        if(user != null){
            return this.classEntityDAO.findAll().stream().map(this::toDto).collect(Collectors.toList());
        }
        return null;
    }

    private ClassEntityDTOView toDto(ClassEntity classEntity){
        ClassEntityDTOView classEntityDTOView = new ClassEntityDTOView();
        classEntityDTOView.setName(classEntity.getName());
        classEntityDTOView.setSemester(classEntity.getSemester());
        classEntityDTOView.setCredits(classEntity.getCredits());
        classEntityDTOView.setTeacherName(classEntity.getTeacherId().getFullName());
        return classEntityDTOView;

    }
}
