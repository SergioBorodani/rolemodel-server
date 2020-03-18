package ru.curs.rolemodel.service.impl;

import org.springframework.stereotype.Service;
import ru.curs.celesta.CallContext;
import ru.curs.celesta.transaction.CelestaTransaction;
import ru.curs.rolemodel.data.*;
import ru.curs.rolemodel.service.RoleModelService;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class RoleModelServiceImpl implements RoleModelService {

    @Override
    @CelestaTransaction
    public void deleteUser(CallContext callContext, String userSId) {
        PoljzovateljCursor cursor = new PoljzovateljCursor(callContext);

        boolean present = cursor.tryGet(userSId);

        if(present) {
            cursor.delete();
        }
    }

    @Override
    @CelestaTransaction
    public void deleteRole(CallContext callContext, String roleId) {
        RoljCursor cursor = new RoljCursor(callContext);

        boolean present = cursor.tryGet(roleId);

        if(present) {
            cursor.delete();
        }
    }

    @Override
    @CelestaTransaction
    public void deleteRazr(CallContext callContext, String razrId) {
        RazrCursor cursor = new RazrCursor(callContext);

        boolean present = cursor.tryGet(razrId);

        if(present) {
            cursor.delete();
        }
    }

    @Override
    @CelestaTransaction
    public List<String> getRoles(CallContext callContext) {
        RoljCursor roljCursor = new RoljCursor(callContext);

        List<String> rolesList = new ArrayList<>();

        roljCursor.forEach(c -> rolesList.add(c.getNazvanie()));

        return rolesList;
    }

    @Override
    @CelestaTransaction
    public void saveUserRole(CallContext callContext, String userLogin, String userRole) {
        PoljzovateljCursor poljzovateljCursor = new PoljzovateljCursor(callContext);
        RoljCursor roljCursor = new RoljCursor(callContext);
        SidRoljCursor sidRoljCursor = new SidRoljCursor(callContext);

        String sid = UUID.randomUUID().toString();
        poljzovateljCursor.setSid(sid);
        poljzovateljCursor.setLogin(userLogin);
        poljzovateljCursor.tryInsert();

        System.out.println(sid);
        System.out.println(userLogin);
        System.out.println(userRole);

        roljCursor.setRange("nazvanie", userRole);
        roljCursor.tryFirst();
        String rolj = roljCursor.getRolj();

        System.out.println(rolj);

        sidRoljCursor.setSid(sid);
        sidRoljCursor.setRolj(rolj);
        sidRoljCursor.tryInsert();
    }

    @Override
    @CelestaTransaction
    public List<String> getPerms(CallContext callContext) {
        RazrCursor razrCursor = new RazrCursor(callContext);

        List<String> permsList = new ArrayList<>();

        razrCursor.forEach(c -> permsList.add(c.getNazvanie()));

        return permsList;
    }

    @Override
    @CelestaTransaction
    public void saveRolePerm(CallContext callContext, String roleName, String rolePerm) {
        RoljCursor roljCursor = new RoljCursor(callContext);
        RazrCursor razrCursor = new RazrCursor(callContext);
        RoljRazrCursor roljRazrCursor = new RoljRazrCursor(callContext);

        String rolj = UUID.randomUUID().toString();
        roljCursor.setRolj(rolj);
        roljCursor.setNazvanie(roleName);
        roljCursor.tryInsert();

        System.out.println(rolj);
        System.out.println(roleName);
        System.out.println(rolePerm);

        razrCursor.setRange("nazvanie", rolePerm);
        razrCursor.tryFirst();
        String perm = razrCursor.getRazr();

        System.out.println(perm);

        roljRazrCursor.setRazr(perm);
        roljRazrCursor.setRolj(rolj);
        roljRazrCursor.tryInsert();
    }

    @Override
    @CelestaTransaction
    public void savePerm(CallContext callContext, String permName) {
        RazrCursor razrCursor = new RazrCursor(callContext);

        String razr = UUID.randomUUID().toString();
        razrCursor.setRazr(razr);
        razrCursor.setNazvanie(permName);
        razrCursor.tryInsert();

        System.out.println(razr);
        System.out.println(permName);
    }
}
