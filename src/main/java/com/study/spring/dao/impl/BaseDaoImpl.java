package com.study.spring.dao.impl;

import java.io.Serializable;
import java.math.BigInteger;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import com.study.spring.dao.BaseDao;



@Repository
public class BaseDaoImpl<T> implements BaseDao<T> {

    @Resource
    private SessionFactory sessionFactory;

    /**
	 * 获得当前事物的session 在一个应用程序中，如果DAO 层使用Spring 的hibernate 模板，通过Spring 来控制session
	 * 的生命周期，则首选getCurrentSession ()。
	 * openSession创建session时autoCloseSessionEnabled参数为false，即在事物结束后不会自动关闭session，需要手动关闭，如果不关闭将导致session关联的数据库连接无法释放，最后资源耗尽而使程序当掉。
	 * getCurrentSession创建session时autoCloseSessionEnabled，flushBeforeCompletionEnabled都为true，并且session会同sessionFactory组成一个map以sessionFactory为主键绑定到当前线程。
	 * 
	 * @return org.hibernate.Session
	 */
    protected Session getCurrentSession() {
		// 从会话工厂获取一个session
		return sessionFactory.getCurrentSession(); // 使用当前的session

		// return sessionFactory.openSession(); //重新建立一个新的session
    }

    public Serializable save(T o) {
        if (o != null) {
            Serializable save = this.getCurrentSession().save(o);
            return save;
        }
        return null;
    }

    public T get(Class<T> c, Serializable id) {
        return (T) this.getCurrentSession().get(c, id);
    }

    public T get(String hql) {
        Query q = this.getCurrentSession().createQuery(hql);
        List<T> l = q.list();
        if (l != null && l.size() > 0) {
            return l.get(0);
        }
        return null;
    }

    public T get(String hql, Map<String, Object> params) {
        Query q = this.getCurrentSession().createQuery(hql);
        if (params != null && !params.isEmpty()) {
            for (String key : params.keySet()) {
                q.setParameter(key, params.get(key));
            }
        }
        List<T> l = q.list();
        if (l != null && l.size() > 0) {
            return l.get(0);
        }
        return null;
    }

     public void delete(T o) {
         if (o != null) {
             this.getCurrentSession().delete(o);
         }
     }

     public void update(T o) {
         if (o != null) {
             this.getCurrentSession().update(o);
         }
     }

    public void saveOrUpdate(T o) {
        if (o != null) {
            this.getCurrentSession().saveOrUpdate(o);
        }
    }

    public List<T> find(String hql) {
        Query q = this.getCurrentSession().createQuery(hql);
        return q.list();
    }

    public List<T> find(String hql, Map<String, Object> params) {
        Query q = this.getCurrentSession().createQuery(hql);
        if (params != null && !params.isEmpty()) {
            for (String key : params.keySet()) {
                q.setParameter(key, params.get(key));
            }
        }
        return q.list();
    }

    public List<T> find(String hql, Map<String, Object> params, int page,
            int rows) {
        Query q = this.getCurrentSession().createQuery(hql);
        if (params != null && !params.isEmpty()) {
            for (String key : params.keySet()) {
                q.setParameter(key, params.get(key));
            }
        }
        return q.setFirstResult((page - 1) * rows).setMaxResults(rows).list();
    }

    public List<T> find(String hql, int page, int rows) {
        Query q = this.getCurrentSession().createQuery(hql);
        return q.setFirstResult((page - 1) * rows).setMaxResults(rows).list();
    }

    public Long count(String hql) {
        Query q = this.getCurrentSession().createQuery(hql);
        return (Long) q.uniqueResult();
    }

    public Long count(String hql, Map<String, Object> params) {
        Query q = this.getCurrentSession().createQuery(hql);
        if (params != null && !params.isEmpty()) {
            for (String key : params.keySet()) {
                q.setParameter(key, params.get(key));
            }
        }
        return (Long) q.uniqueResult();
    }

    public int executeHql(String hql) {
        Query q = this.getCurrentSession().createQuery(hql);
        return q.executeUpdate();
    }

    public int executeHql(String hql, Map<String, Object> params) {
        Query q = this.getCurrentSession().createQuery(hql);
        if (params != null && !params.isEmpty()) {
            for (String key : params.keySet()) {
                q.setParameter(key, params.get(key));
            }
        }
        return q.executeUpdate();
    }

    public List<Object[]> findBySql(String sql) {
        SQLQuery q  = this.getCurrentSession().createSQLQuery(sql);
        return q.list();
    }

    public List<Object[]> findBySql(String sql, int page, int rows) {
        SQLQuery q = this.getCurrentSession().createSQLQuery(sql);
        return q.setFirstResult((page - 1) * rows).setMaxResults(rows).list();
    }

    public List<Object[]> findBySql(String sql, Map<String, Object> params) {
        SQLQuery q = this.getCurrentSession().createSQLQuery(sql);
        if (params != null && !params.isEmpty()) {
            for (String key : params.keySet()) {
                q.setParameter(key, params.get(key));
            }
        }
        return q.list();
    }

    public List<Object[]> findBySql(String sql, Map<String, Object> params,
            int page, int rows) {
        SQLQuery q = this.getCurrentSession().createSQLQuery(sql);
        if (params != null && !params.isEmpty()) {
            for (String key : params.keySet()) {
                q.setParameter(key, params.get(key));
            }
        }
        return q.setFirstResult((page - 1) * rows).setMaxResults(rows).list();
    }

    public int executeSql(String sql) {
        SQLQuery q = this.getCurrentSession().createSQLQuery(sql);
        return q.executeUpdate();
    }

    public int executeSql(String sql, Map<String, Object> params) {
        SQLQuery q = this.getCurrentSession().createSQLQuery(sql);
        if (params != null && !params.isEmpty()) {
            for (String key : params.keySet()) {
                q.setParameter(key, params.get(key));
            }
        }
        return q.executeUpdate();
    }

    public BigInteger countBySql(String sql) {
        SQLQuery q = this.getCurrentSession().createSQLQuery(sql);
        return (BigInteger) q.uniqueResult();
    }

    public BigInteger countBySql(String sql, Map<String, Object> params) {
        SQLQuery q = this.getCurrentSession().createSQLQuery(sql);
        if (params != null && !params.isEmpty()) {
            for (String key : params.keySet()) {
                q.setParameter(key, params.get(key));
            }
        }
        return (BigInteger) q.uniqueResult();
    }

    /**
     * 批量保存数据
     * 
     * @param <T>
     * @param entitys 要持久化的临时实体对象集合
     */
    public void batchSave(List<T> entitys) throws SQLException {
        for (int i = 0; i < entitys.size(); i++) {
            getCurrentSession().save(entitys.get(i));
            if (i % 20 == 0) {
                // 20个对象后才清理缓存，写入数据库
                getCurrentSession().flush();
                getCurrentSession().clear();
            }
        }
        // 最后清理一下----防止大于20小于40的不保存
        getCurrentSession().flush();
        getCurrentSession().clear();
    }

}