package vn.fis.cms.servicesImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import vn.fis.cms.model.PermissionModel;
import vn.fis.cms.repositories.PermissionRepository;
import vn.fis.cms.services.IPermissionService;

@Service
public class PermissionServicesImpl implements IPermissionService {

    @Autowired
    PermissionRepository permissionRepository;

    @Override
    public Page<PermissionModel> getPermissions(int pageIndex, int pageSize) {
        Pageable pageable = PageRequest.of(pageIndex, pageSize);

        return permissionRepository.findAllPermission(pageable);
    }
}
