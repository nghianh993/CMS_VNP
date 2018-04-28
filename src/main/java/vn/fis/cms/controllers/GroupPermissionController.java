package vn.fis.cms.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class GroupPermissionController {

    @RequestMapping(value = {"/admin/group_permission"}, method = RequestMethod.GET)
    public String GroupPermission(ModelMap model) {
        return "group_permission";
    }
}
