package main.service;

import main.api.response.AllCurStatResponse;
import main.api.response.FilterResponse;
import main.model.Currency;
import main.model.Transaction;
import main.model.User;
import main.repo.CurrencyRepository;
import main.repo.TransactionRepository;
import main.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.logging.Filter;


@Service
public class StatService {
    @Autowired
    private CurrencyRepository currencyRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private TransactionRepository transactionRepository;

    //Вывод доступных валют по убыванию объемов конвертаций
    public ArrayList<AllCurStatResponse> curStat(){
        ArrayList<AllCurStatResponse> allCurStatResponses = new ArrayList<>();

        ArrayList<Currency> currencies = currencyRepository.findAll();
        for (Currency c: currencies){
            AllCurStatResponse allCurStatResponse = new AllCurStatResponse();
            allCurStatResponse.setName(c.getName());
            Double amount = transactionRepository.findTransAmount(c.getName());
            allCurStatResponse.setAmount(amount);
            if(amount != null){
                allCurStatResponses.add(allCurStatResponse);
            }
        }

        //Сортируем по убыванию
        Collections.sort(allCurStatResponses, new Comparator<>() {
            @Override
            public int compare(AllCurStatResponse o1, AllCurStatResponse o2) {
                return Double.compare(o2.getAmount(), o1.getAmount());
            }
        });

        return allCurStatResponses;
    }


    //Объем одной операции. Перебираем все транзакции, для каждой считаем переходной курс, если превышает, то запихиваем
    //в коллекцию <id, пользователь, сумма в $>

    public ArrayList<FilterResponse> filterStat(String type, String amountS){
        FilterResponse filterResponse = new FilterResponse();
        ArrayList<FilterResponse> filterResponseArrayList = new ArrayList<>();

        int amount;
        try {
            amount = Integer.parseInt(amountS);
        } catch (NumberFormatException e) {
            amount = 0;
        }


        if (type.equals("oneoper")){
            List<Transaction> transactionList = transactionRepository.findAllForOneOper(amount);


            for (Transaction t : transactionList){
                filterResponse = new FilterResponse();
                filterResponse.setId(t.getId());
                filterResponse.setUser(t.getOwner());
                filterResponse.setAmount(t.getAmountBefore());
                filterResponse.setCurName(t.getCurFrom().getName());
                filterResponseArrayList.add(filterResponse);
            }
        }
        else {
            List<User> users = userRepository.findAll();
            for(User u : users){
                Double wholeSum = transactionRepository.calcAllSum(u.getId());
                if (wholeSum >= amount){
                    filterResponse = new FilterResponse();
                    filterResponse.setId(u.getId());
                    filterResponse.setUser(u);
                    filterResponse.setAmount(wholeSum);
                    filterResponse.setCurName("");
                    filterResponseArrayList.add(filterResponse);
                }
            }


        }
        return filterResponseArrayList;
    }



}
