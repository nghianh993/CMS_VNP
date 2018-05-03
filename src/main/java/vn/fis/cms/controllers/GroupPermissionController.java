package vn.fis.cms.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import vn.fis.cms.domain.Group;
import vn.fis.cms.domain.Groups;
import vn.fis.cms.domain.Permission;
import vn.fis.cms.domain.PermissionAuth;
import vn.fis.cms.model.GroupPermissionModel;
import vn.fis.cms.services.IGroupPermissionService;
import vn.fis.cms.services.IPermissionService;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Controller
public class GroupPermissionController {

    @Autowired
    IGroupPermissionService groupPermissionService;

    @Autowired
    IPermissionService permissionService;

    @RequestMapping(value = {"/admin/group_permission"}, method = RequestMethod.GET)
    public String GroupPermission(ModelMap model) {

        List<Group> groupList = new LinkedList<>();

        List<GroupPermissionModel> content = groupPermissionService.getGroupPermissions();
        List<PermissionAuth> permissionList = permissionService.getAllPermissions();

        for (GroupPermissionModel groupPermissionModel : content) {
            int id = groupPermissionModel.getId();
            List<String> codes = groupPermissionService.getPermissionByGroupId(id);
            List<PermissionAuth> permissionAuthList = new LinkedList<>();

            for (String code : codes) {
                Optional<PermissionAuth> permissionAuth = permissionService.getPermissionByCode(code);
                if (permissionAuth.isPresent()) {
                    permissionAuthList.add(permissionAuth.get());
                }
            }

            groupList.add(new Group(groupPermissionModel, permissionAuthList));
        }

        model.addAttribute("lstGroupPermission", content);
        model.addAttribute("groupList", groupList);
        model.addAttribute("permissionList", permissionList);
        model.addAttribute("active", "group_permission");

        return "group_permission";
    }

    @RequestMapping(value = { "/api/admin/group_permission/add" }, method = RequestMethod.POST)
    public @ResponseBody
    boolean AddPermission(
            @RequestParam(value = "id") int id,
            @RequestParam(value = "name") String name,
            @RequestParam(value = "permissions") String[] permissions
    ) {
        if ("".equals(name) || permissions == null || permissions.length <= 0) {
            return false;
        }
        return groupPermissionService.saveGroup(id, name, permissions);
    }

    @RequestMapping(value = { "/api/admin/group_permission/get" }, method = RequestMethod.GET)
    public @ResponseBody
    Group getGroup(
            @RequestParam(value = "id") int id
    ) {
        Optional<GroupPermissionModel> groupPermissionModel = groupPermissionService.getGroupPermissionByGroupId(id);

        if (!groupPermissionModel.isPresent()) {
            return null;
        }

        List<String> codes = groupPermissionService.getPermissionByGroupId(id);
        List<PermissionAuth> permissionAuthList = new LinkedList<>();

        if (codes != null) {
            for (String code : codes) {
                Optional<PermissionAuth> permissionAuth = permissionService.getPermissionByCode(code);
                if (permissionAuth.isPresent()) {
                    permissionAuthList.add(permissionAuth.get());
                }
            }
        }

        Group group = new Group(groupPermissionModel.get(), permissionAuthList);


        return group;
    }

    @RequestMapping(value = { "/api/admin/group_permission/remove" }, method = RequestMethod.POST)
    public @ResponseBody
    boolean RemovePermission(
            @RequestParam(value = "id") int id
    ) {
        return groupPermissionService.removeGroupPermission(id);
    }
}
