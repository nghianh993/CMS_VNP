package vn.fis.cms.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import vn.fis.cms.domain.GroupPermission;
import vn.fis.cms.model.GroupPermissionModel;

import java.util.List;
import java.util.Optional;

@Repository
public interface GroupPermissionRepository extends JpaRepository<GroupPermission, Integer> {
    @Query("SELECT NEW vn.fis.cms.model.GroupPermissionModel(u.id, u.name) from GroupPermission u")
    List<GroupPermissionModel> findAllGroupPermissions();

    @Query("SELECT NEW vn.fis.cms.model.GroupPermissionModel(u.id, u.name) from GroupPermission u WHERE u.id=:id")
    Optional<GroupPermissionModel> getGroupPermissionById(@Param("id") int id);
}
