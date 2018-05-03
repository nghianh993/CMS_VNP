package vn.fis.cms.services;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import vn.fis.cms.domain.Permission;
import vn.fis.cms.domain.PermissionAuth;
import vn.fis.cms.model.PermissionModel;

import java.util.List;
import java.util.Optional;

@Service
public interface IPermissionService {

    Page<PermissionModel> getPermissions(int pageIndex, int pageSize);

    List<PermissionAuth> getAllPermissions();

    boolean savePermission(int id, String code, String description, boolean lock);

    boolean removePermissionById(int id);

    Optional<PermissionAuth> getPermissionById(int id);

    Optional<PermissionAuth> getPermissionByCode(String code);
}
