package ojt.product.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import ojt.product.dao.ProductDAO;
import ojt.product.entity.Product;



@Controller
public class ProductController {

    @Autowired
    ProductDAO proDao;

    @RequestMapping("/product")
    public String showform(Model m) {
        m.addAttribute("command", new Product());
        return "product";
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String save(@ModelAttribute("product") Product product) {
        proDao.save(product);
        return "redirect:/viewProduct";

    }

    @RequestMapping("/viewProduct")
    public ModelAndView viewPro(Model m) {
        ModelAndView mv = new ModelAndView("viewProduct");
        mv.addObject("list",proDao.getProducts());
        return mv;
    }

//    @RequestMapping("/viewProduct")    
//    public String viewPro(Model m){    
//        List<Product> list=proDao.getProducts();    
//        m.addAttribute("list",list);  
//        return "viewemp";    
//    }    
    
    @RequestMapping(value = "/editproduct/{id}")
    public String edit(@PathVariable int id, Model m) {
        Product p = proDao.getProductById(id);
        m.addAttribute("command", p);
        return "producteditform";
    }

    @RequestMapping(value = "/editsave", method = RequestMethod.POST)
    public String editsave(@ModelAttribute("product") Product product) {
        proDao.update(product);
        return "redirect:/viewProduct";
    }

    /* It deletes record for the given id in URL and redirects to /viewemp */
    @RequestMapping(value = "/deleteproduct/{id}", method = RequestMethod.GET)
    public String delete(@PathVariable int id) {
        proDao.delete(id);
        return "redirect:/viewProduct";
    }
}
