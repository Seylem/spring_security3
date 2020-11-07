package web.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import web.model.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository("userDao")
public class UserDaoImpl implements UserDao {

    @PersistenceContext
    EntityManager entityManager;

    protected EntityManager getEntityManager() {
        return this.entityManager;
    }

    @Transactional
    @Override
    public void addUser(User user) {
        getEntityManager().persist(user);
    }

    @Transactional
    @Override
    public void removeUser(Integer id) {
        getEntityManager().createQuery("delete from User where id=:id")
                .setParameter("id", id)
                .executeUpdate();
    }

    @Transactional
    @Override
    public void updateUser(User user) {
        getEntityManager().merge(user);
    }

    @Transactional
    @Override
    public User getUserById(Integer id) {
        return getEntityManager().find(User.class, id);
    }


    @Transactional
    @Override
    public List<User> listUsers() {
        return getEntityManager()
                .createQuery("from User")
                .getResultList();
    }

    @Transactional
    @Override
    public User getUserByName(String name) {
        return (User) getEntityManager().createQuery("select user from User user where user.name=:name")
                .setParameter("name", name)
                .getSingleResult();
    }
}
