package vn.fis.cms.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import vn.fis.cms.domain.PermissionAuth;
import vn.fis.cms.model.PermissionModel;

@Repository
public interface PermissionRepository extends JpaRepository<PermissionAuth, Integer> {

    @Query("SELECT NEW vn.fis.cms.model.PermissionModel(u.id, u.code, u.description, u.islock) from PermissionAuth u")
    Page<PermissionModel> findAllPermission(Pageable pageable);
}
