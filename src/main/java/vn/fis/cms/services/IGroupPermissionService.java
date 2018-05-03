package vn.fis.cms.services;

import org.springframework.stereotype.Service;
import vn.fis.cms.model.GroupPermissionModel;

import java.util.List;
import java.util.Optional;

@Service
public interface IGroupPermissionService {

    List<GroupPermissionModel> getGroupPermissions();

    Optional<GroupPermissionModel> getGroupPermissionByGroupId(int id);

    List<String> getPermissionByGroupId(int id);

    boolean saveGroup(int id, String name, String[] permissions);

    boolean removeGroupPermission(int id);

}
