package ru.curs.rolemodel.service;

import ru.curs.celesta.CallContext;

import java.util.List;

public interface RoleModelService {
    void deleteUser(CallContext callContext, String userSId);

    void deleteRole(CallContext callContext, String roleId);

    void deleteRazr(CallContext callContext, String razrId);

    List<String> getRoles(CallContext callContext);

    void saveUserRole(CallContext callContext, String userLogin, String userRole);

    List<String> getPerms(CallContext callContext);

    void saveRolePerm(CallContext callContext, String roleName, String rolePerm);

    void savePerm(CallContext callContext, String permName);
}
