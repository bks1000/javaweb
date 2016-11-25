package com.june.core.utils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projection;
import org.hibernate.criterion.Projections;
import org.hibernate.internal.CriteriaImpl;
import org.hibernate.internal.CriteriaImpl.Subcriteria;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;

/**
 * hibernate 操作基类
 * @author lenovo
 *
 */
public class HibernateDao {
	
	@Autowired
	private SessionFactory sessionFactory;
	
	public Session getSession() {
		//事务必须是开启的(Required)，否则获取不到
		return sessionFactory.getCurrentSession();
	}
	
	/**
     * 保存PO
     * @param entity
     */
	public void save(Object entity) {
		this.getSession().save(entity);
	}

	public void saveOrUpdate(Object entity) {
        try{
            getSession().saveOrUpdate(entity);
        }
        catch(Exception e){
            e.printStackTrace();
        }
        
    }
	
	/**
     * 更改PO
     * 
     * @param entity
     */
    public void update(Object entity) {
        getSession().update(entity);

    }
    
    /**
     * 根据id删除PO
     * @param <T>
     * 
     */
    public <T> void removeById(Class<T> entityClass, Serializable id) {
        getSession().delete(get(entityClass, id));
    }
    
    /**
     * 删除PO
     * 
     * @param entity
     */
    public void delete(Object entity) {
        getSession().delete(entity);

    }

    /**
     * 查看存在PO
     * @param <T>
     * 
     * @param entity
     */
    public <T> boolean exists(Class<T> entityClass,Serializable id) {
        return get(entityClass,id) != null;
    }
    
    /**
     * 根据ID获取PO实例
     * @param <T>
     * 
     * @param id
     * @return 返回相应的持久化PO实例
     */
    public  <T> T get(Class<T> entityClass, Serializable id) {
        return (T) getSession().get(entityClass, id);
    }
    
    /**
     * 根据持久化实例的类型和ID，取得实例。
     * @param <T>
     * @param entityClass
     */
    public <T>T load(Class<T> entityClass, Serializable id) {
        return (T)getSession().load(entityClass, id);
    }
    
    public void flush() {
        getSession().flush();
    }
    
    /**
     * 将当前对象实例从session缓存中清除
     * @param entity
     */
    public void evict(Object entity) {
        getSession().evict(entity);
    }
    
    /**
     * 强制清除Session缓存
     */
    public void clear() {
        getSession().clear();
    }

    /**
     * 执行带参的HQL查询
     * 
     * @param sql
     * @param params
     * @return 查询结果
     */
    public List find(String hql, Object... params) {
        return createQuery(hql,params).list();
    }
    
    public List findByCriteria(DetachedCriteria criteria) {  
      Criteria criterias =criteria.getExecutableCriteria(getSession());
      return criterias.list();
  }
    
    /**
     * 绑定参数，并执行HQL查询，返回一个单个的持久化实例。如果根据参数查询得到了多个结果， 则返回{@link List}
     * 中的第一个元素，如果没有得到任何结果，返回null。
     */
    public Object findObject(String hql, Object... params) {
        List list = createQuery(hql,params).list();
        if (list.size() > 0) {
            return list.get(0);
        }
        return null;
    }
    
    /**sql语句查找对象*/
    public Object findObjectBySql(String sql,Object... params){
    	List list = createSqlQuery(sql,params).list();
    	if (list.size() > 0) {
            return list.get(0);
        }
        return null;
    }
    
    /**
     * 分页查询函数，使用hql.
     *
     * @param pageNo 页号,从1开始.
     */
    public Page pagedQuery(String hql, int pageNo, int pageSize, Object... values) {
        Assert.hasText(hql);
        Assert.isTrue(pageNo >= 1, "pageNo should start from 1");
        // Count查询
        String countQueryString = "";
        if(hql.indexOf("fetch")>1){
        	countQueryString = " select count (*) " + removeSelect(removeOrders(hql)).replace("fetch", "");
        }else{
        	countQueryString = " select count (*) " + removeSelect(removeOrders(hql));
        }
        
        Query countQuery = createQuery(countQueryString, values);
        List countlist = countQuery.list();
        long totalCount = Long.parseLong(countlist.get(0).toString());

        if (totalCount < 1)
            return new Page();
        // 实际查询返回分页对象
        int startIndex = Page.getStartOfPage(pageNo, pageSize);
        Query query = createQuery(hql, values);
        List list = query.setFirstResult(startIndex).setMaxResults(pageSize).list();

        return new Page(startIndex, totalCount, pageSize, list);
    }
    
    /**
     * 根据给定的分页信息(<code>page</code>参数)和detached Hibernate criteria对象，执行分页查询。
     * <code>page</code>对象中的<code>autoCount</code>属性如果为true，则自动执行
     * {@link #count(CriteriaImpl)}查询。
     * 
     * @param page 给出startIndex,numPerPage,autoCount属性。
     * @param criteria detached Hibernate criteria 对象
     * @return 传入的page参数，它的data和totalCount属性是被重新赋值的（根据查询结果）。
     */
    public Page pagedQuery(int pageNo,int pageSize, DetachedCriteria criteria) {
        Criteria c = criteria.getExecutableCriteria(getSession());
        CriteriaImpl impl = (CriteriaImpl) c;
        return executePagedQuery(pageNo,pageSize, impl);
    }
    
    
    /**
     * 根据给定的分页信息(<code>page</code>参数)和Hibernate criteria对象，执行分页查询。
     * <code>page</code> 对象中的<code>autoCount</code>属性如果为true，则自动执行
     * {@link #count(CriteriaImpl)}查询。
     * 
     * @param page 给出startIndex,numPerPage,autoCount属性。
     * @param criteria Hibernate criteria 对象
     * @return 传入的page参数，它的data和totalCount属性是被重新赋值的（根据查询结果）。
     */
    public Page pagedQuery(int pageNo,int pageSize, Criteria criteria) {
        if (criteria == null || (!(criteria instanceof CriteriaImpl) && !(criteria instanceof CriteriaImpl.Subcriteria))) {
            throw new IllegalArgumentException("'pagedQuery(org.hibernate.criterion.Criteria, int, int)' error, Argument 'criteria' must not be null and it must be instance of CriteriaImpl");
        }
        // 当使用createAlias方法的时候，session将返回Subcriteria的实例，
        // 此时需要通过getParent方法找到CriteriaImpl的实例
        while (criteria instanceof CriteriaImpl.Subcriteria) {
            CriteriaImpl.Subcriteria sc = (Subcriteria) criteria;
            criteria = sc.getParent();
        }
        CriteriaImpl impl = (CriteriaImpl) criteria;

        return executePagedQuery(pageNo,pageSize, impl);
    }
    
    /**
     * 执行Criteria分页查询.同时执行Count查询.
     */
    @SuppressWarnings("rawtypes")
    protected Page executePagedQuery(int pageNo,int pageSize, CriteriaImpl criteria) {
        // 执行count查询
        int totalCount = count(criteria);
        // 实际查询返回分页对象
        int startIndex = Page.getStartOfPage(pageNo, pageSize);
        List list = criteria.setFirstResult(startIndex).setMaxResults(pageSize).list();
        return new Page(startIndex, totalCount, pageSize, list);
    }
    
    /**
     * 根据给出的hibernate Critera的实现，执行分页查询，用于分页查询计算总记录数。
     * 由于count可能对性能有影响，所以子类可以根数据量、索引的实际情况覆盖本方法，以提供更高性能的count查询。 <br>
     * <h3>关于缺省实现：</h3><br>
     * 缺省实现是将criteria中projection和OrderBy条件取出，然后执行count查询。
     * 完成后再将projection和OrderBy条件 重新设回去。
     * @return 总记录数，如果没有符合条件的记录，返回0.
     */
    @SuppressWarnings({ "unchecked", "rawtypes" })
    protected int count(CriteriaImpl criteria) {
        // 先把Projection和OrderBy条件取出来,清空两者来执行Count操作
        Projection projection = criteria.getProjection();
        List<CriteriaImpl.OrderEntry> orderEntries;
        try {
            orderEntries = (List) ReflectUtil.getPrivateProperty(criteria, "orderEntries");
            ReflectUtil.setPrivateProperty(criteria, "orderEntries", new ArrayList());
        } catch (Exception e) {
            throw new InternalError(" Runtime Exception impossibility throw ");
        }
        // 执行查询
        int totalCount =  Integer.parseInt(criteria.setProjection(Projections.rowCount()).uniqueResult().toString());
        // 将之前的Projection和OrderBy条件重新设回去
        criteria.setProjection(projection);
        if (projection == null) {
            criteria.setResultTransformer(CriteriaSpecification.ROOT_ENTITY);
        }
        try {
            ReflectUtil.setPrivateProperty(criteria, "orderEntries", orderEntries);
        } catch (Exception e) {
            throw new InternalError(" Runtime Exception impossibility throw ");
        }
        return totalCount;
    }

    /**
     * 创建Query对象. 对于需要first,max,fetchsize,cache,cacheRegion等诸多设置的函数,可以在返回Query后自行设置.
     * 留意可以连续设置,如下：
     * <pre>
     * dao.getQuery(hql).setMaxResult(100).setCacheable(true).list();
     * </pre>
     * 调用方式如下：
     * <pre>
     *        dao.createQuery(hql)
     *        dao.createQuery(hql,arg0);
     *        dao.createQuery(hql,arg0,arg1);
     *        dao.createQuery(hql,new Object[arg0,arg1,arg2])
     * </pre>
     *
     * @param values 可变参数.
     */
    public Query createQuery(String hql, Object... values) {
        Assert.hasText(hql);
        Query query = getSession().createQuery(hql);
        for (int i = 0; i < values.length; i++) {
            if(values[i] instanceof Date) {
                query.setTimestamp(i, (Date)values[i]);
            } else {
                query.setParameter(i, values[i]);
            }
        }
        return query;
    }
    
    public Query createSqlQuery(String hql, Object... values) {
        Assert.hasText(hql);
        Query query = getSession().createSQLQuery(hql);
        for (int i = 0; i < values.length; i++) {
            if(values[i] instanceof Date) {
                query.setTimestamp(i, (Date)values[i]);
            } else {
                query.setParameter(i, values[i]);
            }
        }
        return query;
    }
    
    /**
     * 去除hql的select 子句，未考虑union的情况,用于pagedQuery.
     *
     * @see #pagedQuery(String,int,int,Object[])
     */
    private static String removeSelect(String hql) {
        Assert.hasText(hql);
        int beginPos = hql.toLowerCase().indexOf("from");
        Assert.isTrue(beginPos != -1, " hql : " + hql + " must has a keyword 'from'");
        return hql.substring(beginPos);
    }
    
    /**
     * 去除hql的orderby 子句，用于pagedQuery.
     *
     * @see #pagedQuery(String,int,int,Object[])
     */
    private static String removeOrders(String hql) {
        Assert.hasText(hql);
        Pattern p = Pattern.compile("order\\s*by[\\w|\\W|\\s|\\S]*", Pattern.CASE_INSENSITIVE);
        Matcher m = p.matcher(hql);
        StringBuffer sb = new StringBuffer();
        while (m.find()) {
            m.appendReplacement(sb, "");
        }
        m.appendTail(sb);
        return sb.toString();
    }
    
    /**
     * 执行批处理语句.如 之间insert, update, delete 等.
     */
    public int executeHql(final String hql, final Object... params) {
        Query query = createQuery(hql,params);
        Object result = query.executeUpdate();
        return result == null ? 0 : ((Integer) result).intValue();
    }
    
    public int executeSql(final String sql, final Object... params) {
        Query query =createSqlQuery(sql,params);
        Object result = query.executeUpdate();
        return result == null ? 0 : ((Integer) result).intValue();
    }
    
    /**
     * 根据查询条件返回唯一一条记录
     * @param <T>
     */
    @SuppressWarnings("unchecked")
    public  <T> T unique(final String hql, final Object... params) {
        Query query = createQuery(hql,params);
        return (T) query.setMaxResults(1).uniqueResult();
    }
    
    /**
     * 二级缓存使用查询 
     * @param <T>
     */
    @SuppressWarnings("unchecked")
    public <T> T getModelWithCache(String hql,Object... params) {
        Query q = createQuery(hql,params);
        q.setCacheable(true);
        return (T)q.list().get(0);
    }
    
    /**
     * 二级缓存使用查询 
     * @param <T>
     */
    @SuppressWarnings("unchecked")
    public <T> List<T> getListWithCache(String hql,Object... params) {
        Query q = createQuery(hql,params);
        q.setCacheable(true);
        return q.list();
    }
    
    /**
     * hql语句返回map的查询 
     */
    @SuppressWarnings("unchecked")
    public List<Map<String,Object>> queryHqlListMap(String hql,Object... params){
        Query q = createQuery(hql,params);
        return q.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP).list();
    }
    
    /**
     * sql语句返回map的查询 
     */
    @SuppressWarnings("unchecked")
    public List<Map<String,Object>> querySqlListMap(String sql,Object... params){
        Query q = createSqlQuery(sql,params);
        return q.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP).list();
    }
    
    /**
     * sql语句返回map的查询 (返回String类型MAP)
     */
    @SuppressWarnings("unchecked")
    public List<Map<String,String>> querySqlListMapForString(String sql,Object... params){
        Query q = createSqlQuery(sql,params);
        return q.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP).list();
    }
    
    
    /**
     * 分页查询函数，返回map的查询 ,使用sql.
     *
     * @param pageNo 页号,从1开始.
     */
    public Page pagedMapQuery(String sql, int pageNo, int pageSize, Object... values) {
        Assert.hasText(sql);
        Assert.isTrue(pageNo >= 1, "pageNo should start from 1");
        // Count查询
        String countQueryString = " select count(*) " + removeSelect(removeOrders(sql));
        Query countQuery = createSqlQuery(countQueryString, values);
        List countlist = countQuery.list();
        long totalCount =countlist.size()==0?0: Long.parseLong(countlist.get(0).toString());

        if (totalCount < 1)
            return new Page();
        // 实际查询返回分页对象
        int startIndex = Page.getStartOfPage(pageNo, pageSize);
        Query query = createSqlQuery(sql, values);
        List list = query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP).setFirstResult(startIndex).setMaxResults(pageSize).list();

        return new Page(startIndex, totalCount, pageSize, list);
    }

    /**
     * 分页查询函数，返回bean的查询 ,使用sql.
     * @param <T>
     *
     * @param pageNo 页号,从1开始.
     */
    public <T> Page pagedBeanQuery(String sql, Class<T> entityClass,int pageNo, int pageSize, Object... values) {
        Assert.hasText(sql);
        Assert.isTrue(pageNo >= 1, "pageNo should start from 1");
        // Count查询
        String countQueryString = " select count(*) " + removeSelect(removeOrders(sql));
        Query countQuery = createSqlQuery(countQueryString, values);
        List countlist = countQuery.list();
        long totalCount =Long.parseLong(countlist.get(0).toString());

        if (totalCount < 1)
            return new Page();
        // 实际查询返回分页对象
        int startIndex = Page.getStartOfPage(pageNo, pageSize);
        Query query = createSqlQuery(sql, values);
        List list = query.setResultTransformer(Transformers.aliasToBean(entityClass)).setFirstResult(startIndex).setMaxResults(pageSize).list();

        return new Page(startIndex, totalCount, pageSize, list);
    }
}
