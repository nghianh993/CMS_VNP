package vn.fis.cms.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import vn.fis.cms.model.PermissionModel;
import vn.fis.cms.services.IPermissionService;

import java.util.List;

@Controller
public class PermissionController {

    @Autowired
    IPermissionService permissionService;

    @RequestMapping(value = {"/admin/permission"}, method = RequestMethod.GET)
    public String Permission(ModelMap model) {
        Page<PermissionModel> pgPermission = permissionService.getPermissions(0, 2);
        List<PermissionModel> content = pgPermission.getContent();

        model.addAttribute("lstPermission", content);
        model.addAttribute("total", pgPermission.getTotalElements());
        model.addAttribute("totalPage", pgPermission.getTotalPages());
        model.addAttribute("currentpage", 1);
        model.addAttribute("active", "permission");

        return "permission";
    }
}
