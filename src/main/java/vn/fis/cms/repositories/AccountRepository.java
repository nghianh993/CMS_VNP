package vn.fis.cms.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import vn.fis.cms.domain.User;

@Repository
public interface AccountRepository extends JpaRepository<User, Long>{
	//public Page<User> findAll(Pageable pageable);
}
