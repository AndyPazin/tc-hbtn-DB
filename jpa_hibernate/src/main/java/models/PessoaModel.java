package models;

import entities.Pessoa;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

public class PessoaModel {

    private EntityManagerFactory emf;
    private EntityManager em;

    public PessoaModel() {
        emf = Persistence.createEntityManagerFactory("admin-jpa");
        em = emf.createEntityManager();
    }

    public void create(Pessoa pessoa) {
        em.getTransaction().begin();
        em.persist(pessoa);
        em.getTransaction().commit();
    }

    public Pessoa findById(Long id) {
        return em.find(Pessoa.class, id);
    }

    public List<Pessoa> findAll() {
        return em.createQuery("SELECT p FROM Pessoa p", Pessoa.class).getResultList();
    }

    public void update(Pessoa pessoa) {
        em.getTransaction().begin();
        em.merge(pessoa);
        em.getTransaction().commit();
    }

    public void delete(Long id) {
        Pessoa pessoa = findById(id);
        if (pessoa != null) {
            em.getTransaction().begin();
            em.remove(pessoa);
            em.getTransaction().commit();
        }
    }
}