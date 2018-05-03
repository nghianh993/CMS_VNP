package vn.fis.cms.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import vn.fis.cms.domain.Permission;
import vn.fis.cms.domain.PermissionAuth;
import vn.fis.cms.model.AccountModel;
import vn.fis.cms.model.PermissionModel;
import vn.fis.cms.services.IPermissionService;

import java.util.List;
import java.util.Optional;

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

    @RequestMapping(value = { "/api/admin/permission/loaddata" }, method = RequestMethod.POST)
    public ModelAndView GetPermissionPage(int pageIndex, int pageSize) {
        Page<PermissionModel> pgPermission = permissionService.getPermissions(pageIndex -1, pageSize);
        List<PermissionModel> lstPermission = pgPermission.getContent();
        ModelAndView mav = new ModelAndView("pagging_permission");
        mav.addObject("lstPermission", lstPermission);
        mav.addObject("total", pgPermission.getTotalElements());
        mav.addObject("totalPage", pgPermission.getTotalPages());
        mav.addObject("currentpage", pageIndex);
        return mav;
    }

    @RequestMapping(value = { "/api/admin/permission/get" }, method = RequestMethod.GET)
    public @ResponseBody
    PermissionAuth GetPermission(
            @RequestParam(value = "id") int id
    ) {
        Optional<PermissionAuth> permission = permissionService.getPermissionById(id);

        if (!permission.isPresent()) {
            return null;
        }
        return permission.get();
    }

    @RequestMapping(value = { "/api/admin/permission/add" }, method = RequestMethod.POST)
    public @ResponseBody
    boolean AddPermission(
            @RequestParam(value = "id") int id,
            @RequestParam(value = "code") String code,
            @RequestParam(value = "description") String description,
            @RequestParam(value = "lock") boolean lock
    ) {
        if (code == null || description == null || code.equals("") || description.equals("")) {
            return false;
        }
        return permissionService.savePermission(id, code, description, lock);
    }

    @RequestMapping(value = { "/api/admin/permission/remove" }, method = RequestMethod.POST)
    public @ResponseBody
    boolean RemovePermission(
            @RequestParam(value = "id") int id
    ) {
        return permissionService.removePermissionById(id);
    }
}
