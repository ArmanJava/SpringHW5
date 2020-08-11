package ru.geekbrains.homework5.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.geekbrains.homework5.service.ProductService;

@Controller
@RequestMapping("/catalog")
public class CatalogController {

    private ProductService productService;

    @Autowired
    public void setProductService(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public String catalogPage(
            @RequestParam(value = "min", required = false) Integer min,
            @RequestParam(value = "max", required = false) Integer max,
            @RequestParam(value = "page", required = false) Integer page,
            Model model
    ) {
        if (min == null) min = 0;
        if (max == null) max = 1000000;
        if (page == null) page = 0;
        model.addAttribute("numberOfPages", productService.getNumberOfPages(min, max));
        model.addAttribute("products", productService.getPagingList(page, min, max));
        model.addAttribute("pageNumber", page);
        model.addAttribute("min", min);
        model.addAttribute("max", max);
        return "catalog";
    }
}
