package com.yuan.models.manager;

import com.yuan.models.FlagType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

/**
 * @author joryun ON 2017/10/22.
 */
public interface ManagerDao extends JpaRepository<Manager, Integer>, JpaSpecificationExecutor<Manager> {

    Manager findByAccountAndPasswordAndFlag(String account, String password, FlagType flag);

    Page<Manager> findByManagerRoleAndFlag(RoleType managerRole, FlagType flag, Pageable pageable);

    Manager findByManagerIdAndFlag(Integer managerId, FlagType flag);

    List<Manager> findByFlagAndManagerRole(FlagType flag, RoleType managerRole);

    Page<Manager> findManagersByFlagAndManagerRole(FlagType flag, RoleType managerRole, Pageable pageable);

}
