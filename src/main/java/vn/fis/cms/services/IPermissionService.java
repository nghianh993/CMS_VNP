package vn.fis.cms.services;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import vn.fis.cms.model.PermissionModel;

@Service
public interface IPermissionService {

    Page<PermissionModel> getPermissions(int pageIndex, int pageSize);
}
