package util;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.criteria.CriteriaQuery;

public class Dao<T extends Persistivel> {

    private final Class<T> classe;
    EntityManager manager;

    public Dao(Class<T> classe) {
        this.classe = classe;
    }

    public T alterar(T objeto) {
        manager = JpaUtil.getEntityManager();
        manager.getTransaction().begin();
        objeto = manager.merge(objeto);
        manager.getTransaction().commit();
        manager.close();
        return objeto;
    }

    public T buscarPorCodigo(Object id) {
        T objeto;
        manager = JpaUtil.getEntityManager();
        objeto = manager.find(classe, id);
        manager.close();
        return objeto;
    }

    public void excluir(T objeto) throws ExclusaoException{
        manager = JpaUtil.getEntityManager();
        EntityTransaction tx = manager.getTransaction();
        tx.begin();
        try {
            T temp = manager.find(classe, objeto.getCodigo());
            manager.remove(temp);
            tx.commit();
        } catch (Exception e) {             
            throw new ExclusaoException();
        } finally {           
            manager.close();
        }
    }
    
    public void inserir(T objeto) {
        manager = JpaUtil.getEntityManager();
        EntityTransaction tx = manager.getTransaction();
        tx.begin();
        manager.persist(objeto);
        tx.commit();
    }

    public List<T> listarTodos() {
        manager = JpaUtil.getEntityManager();
        CriteriaQuery<T> query
                = manager.getCriteriaBuilder().createQuery(classe);
        query.select(query.from(classe));
        List<T> lista = manager.createQuery(query).getResultList();
        manager.close();
        return lista;
    }

    public T buscarPorCarro(int id){
        manager = JpaUtil.getEntityManager();
        T objeto = (T) manager.createQuery("SELECT u FROM UsoVeiculo u WHERE u.veiculo.codigo = :id and u.devolucao is null")
                .setParameter("id", id)
                .getSingleResult();
        manager.close();
        return objeto;
    }
}
