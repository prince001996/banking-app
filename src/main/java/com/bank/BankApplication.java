package com.bank;

import com.bank.model.CustomerDetail;
import com.bank.service.AccountService;
import com.bank.service.CustomerService;
import com.bank.service.TransactionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class BankApplication {
	
	public static final Logger LOG = LoggerFactory.getLogger(BankApplication.class);
	
	public static void main(String[] args) {
		SpringApplication.run(BankApplication.class, args);
		LOG.info("Bank Application running...");
	}
	
	@Bean
	public CommandLineRunner demo(CustomerService customerService,
								  AccountService accountService, TransactionService transactionService) {
		return (args) -> {
			createtestUsers(customerService);
			createTestAccounts(accountService);
			createTestTransactions(transactionService);
		};
	}
	
	private void createTestTransactions(TransactionService transactionService) {
		//transactions from one account to another account of a person
		transactionService.transact(10002, 10002, 200);
		transactionService.transact(10004, 10004, 100);
		
		//transaction between two persons
		transactionService.transact(10000, 10001, 300);
		transactionService.transact(10001, 10003, 500);
		transactionService.transact(10002, 10005, 200);
		transactionService.transact(10008, 10006, 200);
		transactionService.transact(10004, 10002, 900);
		
		//invalid transactions
		transactionService.transact(10010, 10001, 200);
		transactionService.transact(10000, 10010, 200);
		transactionService.transact(10000, 10001, 200000);
		
		
	}
	
	private void createTestAccounts(AccountService accountService) {
		//accounts for first user
		accountService.createAndassignAccount(12345, 5000);
		
		//accounts for second user
		accountService.createAndassignAccount(12346, 8000);
		accountService.createAndassignAccount(12346, 3000);
		
		//accounts for third user
		accountService.createAndassignAccount(12347, 5000);
		accountService.createAndassignAccount(12347, 3000);
		accountService.createAndassignAccount(12347, 10000);
		
		//accounts for fourth user
		accountService.createAndassignAccount(12348, 5000);
		accountService.createAndassignAccount(12348, 3000);
		
		//accounts for fifth user
		accountService.createAndassignAccount(12349, 5000);
		
		//accounts for sixth user
		accountService.createAndassignAccount(12350, 1000);
	}
	
	public static void createtestUsers(CustomerService customerService){
		//first user
		CustomerDetail customerDetail1 = new CustomerDetail(
				12345, 98765, "Arisha Barron", "street 1", "patna", 800001
		);
		customerService.addCustomer(customerDetail1);
		
		//second user
		CustomerDetail customerDetail2 = new CustomerDetail(
				12346, 98764, "Branden Gibson","street 2", "bangalore", 560098
		);
		customerService.addCustomer(customerDetail2);
		
		//third user
		CustomerDetail customerDetail3 = new CustomerDetail(
				12347, 98763, "Rhonda Church","street 3", "pune", 560070
		);
		customerService.addCustomer(customerDetail3);
		
		//fourth user
		CustomerDetail customerDetail4 = new CustomerDetail(
				12348, 98762, "Georgina Hazel","street 4", "chennai", 111000
		);
		customerService.addCustomer(customerDetail4);
		
		//fifth user
		CustomerDetail customerDetail5 = new CustomerDetail(
				12349, 98761, "Prince Kumar" ,"street 5", "delhi", 110084
		);
		customerService.addCustomer(customerDetail5);
		
		//sixth user
		CustomerDetail customerDetail6 = new CustomerDetail(
				12350, 98760, "Princess", "street 6", "noida", 220033
		);
		customerService.addCustomer(customerDetail6);
	}
	
	
}
