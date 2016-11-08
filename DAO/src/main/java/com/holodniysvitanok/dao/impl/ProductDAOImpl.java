package com.holodniysvitanok.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Expression;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.holodniysvitanok.dao.ProductDAO;
import com.holodniysvitanok.entity.Category;
import com.holodniysvitanok.entity.Manufacturer;
import com.holodniysvitanok.entity.Product;

/**
 * @author Gubin Vladislav
 * @version 1.0
 * 
 **/
@SuppressWarnings({ "deprecation", "unchecked", "rawtypes" })
@Repository
@Transactional
public class ProductDAOImpl implements ProductDAO {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	@Transactional
	public List<Product> getAllProducts(int count) {
		Session session = sessionFactory.getCurrentSession();
		List<Product> list = session.createCriteria(Product.class)
				.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).setMaxResults(count).list();
		return list;
	}

	@Override
	public Product getProduct(long id) {
		Session currentSession = sessionFactory.getCurrentSession();
		Product product = (Product) currentSession.get(Product.class, id);
		return product;
	}

	@Override
	public void saveOrUpdateProduct(Product product) {
		Session session = sessionFactory.getCurrentSession();
		session.saveOrUpdate(product);

	}

	@Override
	public void deleteProduct(Product product) {
		Session session = sessionFactory.getCurrentSession();
		session.remove(product);

	}

	@Override
	public long getCountDuplicateFiles(String fileName, int numberFieldUrl) {
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("select count(*) from Product where urlImage" + numberFieldUrl + " = :fileName ");
		query.setParameter("fileName", fileName);
		return ((Long) query.uniqueResult()).intValue();
	}

	@Override
	public List<Product> getAllProductInCategory(Category category) {
		Session session = sessionFactory.getCurrentSession();
		Query<Product> query = session.createQuery("from Product where category = :categoryVar");
		query.setParameter("categoryVar", category);
		return query.list();
	}

	@Override
	public List<Product> getAllProductInCategory(Category category, int startRow, int maxRows) {
		Session session = sessionFactory.getCurrentSession();
		Query<Product> query = session.createQuery("from Product where category = :categoryVar");
		query.setParameter("categoryVar", category);
		query.setMaxResults(maxRows);
		query.setFirstResult(startRow);
		return query.list();

	}

	@Override
	public int getCountPageFromCategory(Category category) {
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("select count(*) from Product where  category = :categoryVar and show = true ");
		query.setParameter("categoryVar", category);
		return ((Long) query.uniqueResult()).intValue();
	}

	@Override
	public List<Product> getCarouselProducts() {
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("from Product where carousel = true and show = true");

		return query.getResultList();
	}

	@Override
	public List<Product> getRandProducts(int maxResult) {
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("from Product where show = true order by RAND()");
		query.setMaxResults(maxResult);
		return query.getResultList();
	}

	@Override
	public List<Product> getProductsByCriteria(String manufacturer, String category, String start, String end, String sort, String order) {

		Session session = sessionFactory.getCurrentSession();

		Criteria criteria = session.createCriteria(Product.class);

		int startVal = Integer.parseInt(start);
		int endVal = Integer.parseInt(end) + 1;
		int max = endVal - startVal;

		criteria.setFirstResult(startVal);
		criteria.setMaxResults(max);
		
		if (!manufacturer.equalsIgnoreCase("all")) {
			long manufacturerId = Long.parseLong(manufacturer);
			criteria.add(Expression.eq("manufacturer", new Manufacturer(manufacturerId)));
		}

		if (!category.equalsIgnoreCase("all")) {
			long categoryId = Long.parseLong(category);
			criteria.add(Expression.eq("category", new Category(categoryId)));
		}

		if (sort.equalsIgnoreCase("default")) {
			if (order.equalsIgnoreCase("asc")) {
				criteria.addOrder(Order.asc("id"));
			}
			if (order.equalsIgnoreCase("desc")) {
				criteria.addOrder(Order.desc("id"));
			}
		}

		if (sort.equalsIgnoreCase("price")) {
			if (order.equalsIgnoreCase("asc")) {
				criteria.addOrder(Order.asc("sellPrice"));
			}

			if (order.equalsIgnoreCase("desc")) {
				criteria.addOrder(Order.desc("sellPrice"));
			}
		}
		return criteria.list();
	}

	@Override
	public long getCountAllProducts(String manufacturer, String category) {
		Session session = sessionFactory.getCurrentSession();

		Criteria criteria = session.createCriteria(Product.class);
		
		if (!manufacturer.equalsIgnoreCase("all")) {
			long manufacturerId = Long.parseLong(manufacturer);
			criteria.add(Expression.eq("manufacturer", new Manufacturer(manufacturerId)));
		}

		if (!category.equalsIgnoreCase("all")) {
			long categoryId = Long.parseLong(category);
			criteria.add(Expression.eq("category", new Category(categoryId)));
		}
		
		criteria.setProjection(Projections.rowCount());
		
		return ((Long) criteria.uniqueResult()).intValue();
	}

	@Override
	public List<Product> getProductsByIdList(List<Long> idProductWithBasket) {
		Session session = sessionFactory.getCurrentSession();

		Query query = session.createQuery("FROM Product WHERE id in (:listId)");
		query.setParameterList("listId", idProductWithBasket);

		return query.getResultList();
	}

}
