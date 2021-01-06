package main.service;

import com.google.gson.Gson;
import main.api.request.ConvertRequest;
import main.api.response.ConvertResponse;
import main.api.response.ErrorsOnTransaction;
import main.model.*;
import main.repo.CurrencyRepository;
import main.repo.TransactionRepository;
import main.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import java.util.ArrayList;
import java.util.Optional;


@Service
public class ExchangeService {

    @Autowired
    private CurrencyRepository currencyRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private TransactionRepository transactionRepository;


    public ConvertResponse exchange(ConvertRequest convertRequest) {
        ConvertResponse convertResponse = new ConvertResponse();
        ErrorsOnTransaction errorsOnTransaction = new ErrorsOnTransaction();
        Transaction transaction = new Transaction();
        boolean hasErrors = false;
        User user = new User();

        updateCources();

        if (convertRequest.getName().equals("") || convertRequest.getSurname().equals("")
                || convertRequest.getAmount().equals("") || convertRequest.getCurTo().equals("")
                                                    || convertRequest.getCurFrom().equals("")) {
            errorsOnTransaction.setEmptyFields("Не все поля заполнены. ");
            hasErrors = true;
        }
        if (!isDigit(convertRequest.getAmount())){
            errorsOnTransaction.setNotDigits("В поле количество введено не число. ");
            hasErrors = true;
        }
        if (convertRequest.getCurTo().equals(convertRequest.getCurFrom())){
            errorsOnTransaction.setSameCurrency("Выбраны одинаковые валюты. ");
            hasErrors = true;
        }

        if (!hasErrors){
            String fullName = (convertRequest.getName() + " " + convertRequest.getSurname()).toUpperCase();
            fullName.trim();

            Optional<User> optionalUser = userRepository.findByName(fullName);
            if (optionalUser.isPresent()) {
                user = optionalUser.get();
            } else {
                User newUser = new User();
                newUser.setName(fullName);
                userRepository.save(newUser);
                user = newUser;
            }


            transaction.setCurFrom(currencyRepository.findOneByName(convertRequest.getCurFrom()).get());
            transaction.setCurTo(currencyRepository.findOneByName(convertRequest.getCurTo()).get());
            transaction.setOwner(user);

            double amountBefore = Double.parseDouble(convertRequest.getAmount());
            transaction.setAmountBefore(amountBefore);

            double currentCourseSource = currencyRepository.findOneByName(convertRequest.getCurFrom()).get().getCourse();
            double currentCourseTarget = currencyRepository.findOneByName(convertRequest.getCurTo()).get().getCourse();

            double amountAfter = amountBefore / currentCourseSource * currentCourseTarget;
            transaction.setCurrentCourse(currentCourseTarget/currentCourseSource);
            transaction.setAmountAfter(amountAfter);
            transaction.setStatus(TransactionStatus.COMPLETE.toString());

            transactionRepository.save(transaction);
            convertResponse.setStatus(true);
            convertResponse.setId(transaction.getId());
            convertResponse.setAmount(transaction.getAmountAfter());
        }
        else{
            convertResponse.setStatus(false);
            convertResponse.setErrorsOnTransaction(errorsOnTransaction);
        }

        //Возвращает id, количество целевой валюты, ошибки
        return convertResponse;
    }

    //Получение и парсинг курсов валют
    private void updateCources() {
        final RestTemplate restTemplate = new RestTemplate();
        ArrayList<Currency> currencies = currencyRepository.findAll();
        for(Currency c : currencies){
            String curName = c.getName();
            String url = "https://api.exchangeratesapi.io/latest?base=USD&symbols=" + curName;
            String stringCourse = restTemplate.getForObject(url, String.class);
            Gson g = new Gson();
            Course course = g.fromJson(stringCourse, Course.class);
            Rate rate = course.getRates();

            switch (curName){
                case "USD" : {
                    c.setCourse(rate.getUSD());
                    currencyRepository.save(c);
                    break;
                }
                case "RUB" : {
                    c.setCourse(rate.getRUB());
                    currencyRepository.save(c);
                    break;
                }
                case "JPY" : {
                    c.setCourse(rate.getJPY());
                    currencyRepository.save(c);
                    break;
                }
                case "CNY" : {
                    c.setCourse(rate.getCNY());
                    currencyRepository.save(c);
                    break;
                }
                case "GBP" : {
                    c.setCourse(rate.getGBP());
                    currencyRepository.save(c);
                    break;
                }
                default:
                    System.out.println("no such currency");
            }
        }
    }

    private static boolean isDigit(String s) throws NumberFormatException {
        try {
            Integer.parseInt(s);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}



//https://api.exchangeratesapi.io/latest?base=RUB&symbols=USD
