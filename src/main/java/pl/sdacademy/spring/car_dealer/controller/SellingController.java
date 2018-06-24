package pl.sdacademy.spring.car_dealer.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.sdacademy.spring.car_dealer.model.Customer;
import pl.sdacademy.spring.car_dealer.model.Purchase;
import pl.sdacademy.spring.car_dealer.model.PurchaseFormData;
import pl.sdacademy.spring.car_dealer.service.SellingService;

import java.util.List;
import java.util.Scanner;

@Controller
@RequestMapping("/purchases")
public class SellingController {

    private final SellingService sellingService;

    public SellingController(SellingService sellingService) {
        this.sellingService = sellingService;
    }

    @GetMapping("/{id}")
    public String getPurchase(
            @PathVariable("id") Long purchaseId,
            Model model) {
        Purchase purchase = sellingService.loadPurchaseById(purchaseId);
        if (purchase != null) {
            model.addAttribute("p", purchase);
        }
        return "purchaseDetails";
    }

    @PostMapping
    public String sold(
            @ModelAttribute("sellData") PurchaseFormData purchaseData) {
        Customer customer = new Customer();
        customer.setName(purchaseData.getName());
        customer.setSurname(purchaseData.getSurname());
        customer.setDocumentNo(purchaseData.getDocumentNo());
        customer.setTelephone(purchaseData.getTelephone());
        sellingService.sell(
                purchaseData.getVehicleId(),
                customer,
                purchaseData.getPrice());
        return "redirect:/vehicles";
    }

    public void buyVehicle(Long vehicleId) {
        Customer customer = getCustomerData();
        Long customerPrice = getCustomerPrice();
        sellingService.sell(vehicleId, customer, customerPrice);
    }

    public void showPurchaseHistory() {
        System.out.print("Provide Document Number: ");
        String documentNo = readInput();
        List<Purchase> customerHistory = sellingService.loadHistory(documentNo);
        customerHistory.forEach(System.out::println);
    }

    private Customer getCustomerData() {
        Customer customer = new Customer();
        System.out.println("Provide customer data:");
        System.out.print("   Name: ");
        customer.setName(readInput());
        System.out.print("   Last name: ");
        customer.setSurname(readInput());
        System.out.print("   Document number: ");
        customer.setDocumentNo(readInput());
        System.out.print("   Telephone: ");
        customer.setTelephone(readInput());
        return customer;
    }

    private Long getCustomerPrice() {
        while (true) {
            try {
                System.out.print("Selling price for this customer: ");
                return Long.parseLong(readInput());
            } catch (NumberFormatException e) {
                System.out.println("Invalid price. Provide again.");
            }
        }
    }

    private String readInput() {
        return new Scanner(System.in).nextLine();
    }
}
