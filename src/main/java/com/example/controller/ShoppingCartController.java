package com.example.controller;

import com.example.domain.Item;
import jakarta.servlet.ServletContext;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/exam06")
public class ShoppingCartController {

    @Autowired
    private HttpSession session;
    @Autowired
    private ServletContext application;

    @GetMapping("")
    public String index(Model model){
        if((List<Item>)application.getAttribute("itemList") == null){
            List<Item> itemList = new ArrayList<>();
            Item notebook = new Item();
            notebook.setName("手帳ノート");
            notebook.setPrice(1000);
            itemList.add(notebook);

            Item stationery  = new Item();
            stationery.setName("文房具セット");
            stationery.setPrice(1500);
            itemList.add(stationery);

            Item file = new Item();
            file.setName("ファイル");
            file.setPrice(2000);
            itemList.add(file);

            application.setAttribute("itemList", itemList);
        }

        if((List<Item>)session.getAttribute("shoppingList") == null){
            session.setAttribute("shoppingList", new ArrayList<Item>());
        }

        int totalPrice = getTotalPrice();
        model.addAttribute("totalPrice", totalPrice);

        return "item-and-cart";
    }

    @PostMapping("/inCart")
    public String inCart(int itemIndex){
        System.out.println("itemIndex=" + itemIndex);
        final List<Item> itemList =  (List<Item>)application.getAttribute("itemList");
        List<Item> shoppingList =  (List<Item>)session.getAttribute("shoppingList");
        shoppingList.add(itemList.get(itemIndex)); //選択された商品を買い物かごに入れる
        return "redirect:/exam06";
    }

    @PostMapping("/delete")
    public String delete(int shoppinIndex){
        List<Item> shoppingList =  (List<Item>)session.getAttribute("shoppingList");
        shoppingList.remove(shoppinIndex); //買い物かごのshoppingIndex番目の商品を削除
        return "redirect:/exam06";
    }

    /**
     * sessionスコープの購入するものリストから合計金額を計算する
     * @return totalPrice
     */
     private int getTotalPrice(){
        List<Item> shoppingList =  (List<Item>)session.getAttribute("shoppingList");
        int totalPrice = 0;
        for(Item item: shoppingList){
            totalPrice += item.getPrice();
        }
        return totalPrice;
    }
}
