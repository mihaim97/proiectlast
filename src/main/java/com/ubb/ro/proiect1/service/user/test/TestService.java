package com.ubb.ro.proiect1.service.user.test;

import com.ubb.ro.proiect1.dao.classentity.ClassEntityDAO;
import com.ubb.ro.proiect1.dao.session.SessionDAO;
import com.ubb.ro.proiect1.entity.ClassEntity;
import com.ubb.ro.proiect1.entity.SessionEntity;
import com.ubb.ro.proiect1.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

@Service
public class TestService {

    @Autowired
    private SessionDAO sessionDAO;

    @Autowired
    private ClassEntityDAO classEntityDAO;

    @Autowired
    private UserService userService;

    @Transactional
    public void performOperation() {
        /*SessionEntity session = new SessionEntity();
        session.setSessionType("SESIUNE");
        session.setDateCreated(LocalDate.now());
        session.setDateStart(LocalDate.now());
        session.setDateEnd(LocalDate.now());
        this.sessionDAO.persist(session);*/

        /*ClassEntity classEntity = new ClassEntity();
        classEntity.setCredits(5);
        classEntity.setName("Materia 1");
        classEntity.setSemester(2);
        classEntity.setTeacherId(this.userService.loadUser("mihai"));
        this.classEntityDAO.persist(classEntity);*/

       /* ClassEntity classEntity = this.classEntityDAO.findById(1);
        SessionEntity session = this.sessionDAO.findById(1);
        System.out.println(session.getClasses().get(0).getTeacherId().getUsername());*/

        //session.getClasses().add(classEntity);

        System.out.println("Performed operation");
    }

}
