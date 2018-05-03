package vn.fis.cms.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import vn.fis.cms.domain.Groups;

import java.util.List;

public interface GroupRepository extends JpaRepository<Groups, Integer> {

    @Query("SELECT u.permission from Groups u WHERE u.groupid=:id")
    List<String> findPermissionByGroupid(@Param("id") int id);

    List<Groups> findByGroupid(int id);
}
