package vn.fis.cms.servicesImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import vn.fis.cms.domain.Permission;
import vn.fis.cms.domain.PermissionAuth;
import vn.fis.cms.model.PermissionModel;
import vn.fis.cms.repositories.PermissionRepository;
import vn.fis.cms.services.IPermissionService;

import java.util.Optional;

@Service
public class PermissionServicesImpl implements IPermissionService {

    @Autowired
    PermissionRepository permissionRepository;

    @Override
    public Page<PermissionModel> getPermissions(int pageIndex, int pageSize) {
        Pageable pageable = PageRequest.of(pageIndex, pageSize);

        return permissionRepository.findAllPermission(pageable);
    }

    @Override
    public boolean savePermission(int id, String code, String description, boolean lock) {

        PermissionAuth permission = new PermissionAuth();

        permission.setId(id);
        permission.setCode(code);
        permission.setDescription(description);
        permission.setIslock(lock);

        return permissionRepository.save(permission) != null;
    }

    @Override
    public boolean removePermissionById(int id) {
        try {
            permissionRepository.deleteById(id);
            return true;
        } catch (Exception ex) {
            System.out.println(ex);
            return false;
        }
    }

    @Override
    public Optional<PermissionAuth> getPermissionById(int id) {
        return permissionRepository.findById(id);
    }
}
