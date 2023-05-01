package ojt.product.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import ojt.product.entity.Product;

public class ProductDAO {
    
    JdbcTemplate jdbcTemplate;
    
    public void setTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate=jdbcTemplate;
    }

    public int save(Product p) {
        String sql = "INSERT INTO product (name,price) VALUES (?,?)";
        int saveInt = jdbcTemplate.update(sql,p.getName(),p.getPrice());
        return saveInt;
    }
    
    public int update(Product p){    
        String sql = "UPDATE product SET name=?,price=? WHERE id=?";
        int result = jdbcTemplate.update(sql,p.getName(),p.getPrice());
        return result;
    }    
    
    public int delete(int id){    
        String sql="delete from product where id="+id+"";    
        return jdbcTemplate.update(sql);    
    }  
    
    public Product getProductById(int id){    
        String sql="select * from product where id=?";    
        return jdbcTemplate.queryForObject(sql, new Object[]{id},new BeanPropertyRowMapper<Product>(Product.class));    
    }    
    
    public List<Product> getProducts(){    
        return jdbcTemplate.query("select * from product",new RowMapper<Product>(){    
            public Product mapRow(ResultSet rs, int row) throws SQLException {    
                Product p=new Product();    
                p.setId(rs.getInt(1));    
                p.setName(rs.getString(2));    
                p.setPrice(rs.getDouble(3));    
                   
                return p;    
            }    
        });    
    }    
}
