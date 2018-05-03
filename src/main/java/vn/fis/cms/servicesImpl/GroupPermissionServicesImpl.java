package vn.fis.cms.servicesImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import vn.fis.cms.domain.GroupPermission;
import vn.fis.cms.domain.Groups;
import vn.fis.cms.domain.PermissionAuth;
import vn.fis.cms.model.GroupPermissionModel;
import vn.fis.cms.repositories.GroupPermissionRepository;
import vn.fis.cms.repositories.GroupRepository;
import vn.fis.cms.repositories.PermissionRepository;
import vn.fis.cms.services.IGroupPermissionService;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Service
public class GroupPermissionServicesImpl implements IGroupPermissionService {

    @Autowired
    GroupPermissionRepository groupPermissionRepository;

    @Autowired
    GroupRepository groupRepository;

    @Override
    public List<GroupPermissionModel> getGroupPermissions() {
        return groupPermissionRepository.findAllGroupPermissions();
    }

    @Override
    public Optional<GroupPermissionModel> getGroupPermissionByGroupId(int id) {
        return groupPermissionRepository.getGroupPermissionById(id);
    }

    @Override
    public boolean saveGroup(int id, String name, String[] permissions) {

        // find and replace old one in case of record was already exist
        Optional<GroupPermission> groupPermission = groupPermissionRepository.findById(id);
        if (groupPermission.isPresent()) {
            id = groupPermission.get().getId();
        }

        // save new group permission
        GroupPermission newGroupPermission = new GroupPermission();
        newGroupPermission.setId(id);
        newGroupPermission.setName(name);
        GroupPermission gp = groupPermissionRepository.save(newGroupPermission);

        if (gp == null) {
            return false;
        }

        // remove all permissions to add the new one
        if (id > 0) {
            List<Groups> toRemoves = groupRepository.findByGroupid(id);

            groupRepository.deleteAll(toRemoves);
        }

        // add all new permissions
        for (String permission : permissions) {
            Groups group = new Groups();
            group.setGroupid(gp.getId());
            group.setPermission(permission);

            groupRepository.save(group);
        }

        return true;
    }

    @Override
    public List<String> getPermissionByGroupId(int id) {
        return groupRepository.findPermissionByGroupid(id);
    }

    @Override
    public boolean removeGroupPermission(int id) {
        Optional<GroupPermission> groupPermission = groupPermissionRepository.findById(id);
        if (!groupPermission.isPresent()) {
            return false;
        }

        // remove all group permission's permissions
        List<Groups> toRemoves = groupRepository.findByGroupid(id);

        groupRepository.deleteAll(toRemoves);

        // remove group rermission
        groupPermissionRepository.delete(groupPermission.get());

        return true;
    }
}
