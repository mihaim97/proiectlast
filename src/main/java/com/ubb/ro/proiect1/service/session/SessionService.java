package com.ubb.ro.proiect1.service.session;

import com.ubb.ro.proiect1.dto.session.SessionDTO;
import com.ubb.ro.proiect1.dto.session.SessionDTOView;

public interface SessionService {

    void addNewSession(SessionDTO sessionDTO);

    SessionDTOView querySession(String universityYear, Integer semester);

}
