package web.dao;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import web.model.Role;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Repository("roleDao")
public class RoleDaoImpl implements RoleDao {

    @PersistenceContext
    EntityManager entityManager;

    protected EntityManager getEntityManager(){
        return this.entityManager;
    }

    @Transactional
    @Override
    public Set<Role> getAllRoles() {
        Set<Role> roleSet = new HashSet<>();
        List<Role> roleList = entityManager.createQuery("select role from Role role").getResultList();
        roleSet.addAll(roleList);
        return roleSet;
    }

}
