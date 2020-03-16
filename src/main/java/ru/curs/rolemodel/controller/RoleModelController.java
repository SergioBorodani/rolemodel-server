package ru.curs.rolemodel.controller;

import org.springframework.web.bind.annotation.*;
import ru.curs.celesta.SystemCallContext;
import ru.curs.rolemodel.service.RoleModelService;

import java.util.List;

import static ru.curs.rolemodel.controller.RoleModelController.API_PATH;

@RestController
@CrossOrigin
@RequestMapping(API_PATH)
public class RoleModelController {

    public static final String API_PATH = "/api/rolemodel";

    public static final String DELETE_USER_PATH = "/deleteuser";
    public static final String DELETE_ROLE_PATH = "/deleterole";
    public static final String DELETE_RAZR_PATH = "/deleterazr";

    public static final String GET_ROLES_PATH = "/getroles";
    public static final String GET_PERMS_PATH = "/getperms";

    public static final String SAVE_USER_ROLE_PATH = "/saveuserrole";
    public static final String SAVE_ROLE_PERM_PATH = "/saveroleperm";
    public static final String SAVE_PERM_PATH = "/saveperm";

    private final RoleModelService roleModelService;

    public RoleModelController(RoleModelService roleModelService) {
        this.roleModelService = roleModelService;
    }

    @GetMapping(DELETE_USER_PATH)
    public void deleteUser(@RequestParam(value = "userSId") String userSId) {

        roleModelService.deleteUser(new SystemCallContext(), userSId);
    }

    @GetMapping(DELETE_ROLE_PATH)
    public void deleteRole(@RequestParam(value = "roleId") String roleId) {

        roleModelService.deleteRole(new SystemCallContext(), roleId);
    }

    @GetMapping(DELETE_RAZR_PATH)
    public void deleteRazr(@RequestParam(value = "razrId") String razrId) {

        roleModelService.deleteRazr(new SystemCallContext(), razrId);
    }

    @GetMapping(GET_ROLES_PATH)
    public List<String> getRoles() {

        return roleModelService.getRoles(new SystemCallContext());
    }

    @GetMapping(SAVE_USER_ROLE_PATH)
    public void saveUserRole(@RequestParam(value = "userLogin") String userLogin,
                             @RequestParam(value = "userRole") String userRole) {

        roleModelService.saveUserRole(new SystemCallContext(), userLogin, userRole);
    }

    @GetMapping(GET_PERMS_PATH)
    public List<String> getPerms() {

        return roleModelService.getPerms(new SystemCallContext());
    }

    @GetMapping(SAVE_ROLE_PERM_PATH)
    public void saveRolePerm(@RequestParam(value = "roleName") String roleName,
                             @RequestParam(value = "rolePerm") String rolePerm) {

        roleModelService.saveRolePerm(new SystemCallContext(), roleName, rolePerm);
    }

    @GetMapping(SAVE_PERM_PATH)
    public void savePerm(@RequestParam(value = "permName") String permName) {

        roleModelService.savePerm(new SystemCallContext(), permName);
    }
}
