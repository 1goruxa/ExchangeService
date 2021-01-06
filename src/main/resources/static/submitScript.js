
$(function(){
            $("#convert").click(function() {
            //let text = document.getElementById("text").value;
//            var textForm = {"text":$("#text").val(),"deadline":$("#deadline").val()};
//            document.getElementById("text").value="";
//            document.getElementById("deadline").value="";
            var textForm = {"firstname":$("#firstname").val(),"surname":$("#surname").val(),"curfrom":$("#curfrom").val(),
                                "curto":$("#curto").val(),"amount":$("#amount").val()};



            $.ajax
                ({
                    method: "POST",
                    url: "/exchange",
                    data: JSON.stringify(textForm),
                    contentType: "application/json; charset=utf-8",
                    success: function(response) {
                            if(response.status == true){
                                var amount = response.amount;
                                var resp = "Конвертация выполнена успешно(" + response.status + "). id операции: " + response.id;
                                document.getElementById("statusResult").textContent = resp;
                                document.getElementById("amountResult").textContent = "Количество целевой валюты: " + amount.toFixed(2);
                            }
                            else{
                              document.getElementById("statusResult").textContent = "Конвертация не выполнена(" + response.status + ")";
                              var errors = response.errorsOnTransaction;
                              var sameCurrency;
                              var emptyFields;
                              var notDigits;

                              errors.sameCurrency === undefined ? (sameCurrency = "") : (sameCurrency = errors.sameCurrency);
                              errors.emptyFields === undefined ? (emptyFields = "") : (emptyFields = errors.emptyFields);
                              errors.notDigits === undefined ? (notDigits = "") : (notDigits = errors.notDigits);

                              document.getElementById("amountResult").textContent = sameCurrency + " " + emptyFields + " " + notDigits;
                            }
                           }
                     });

                });

            });
$(function(){
            $("#stats").click(function() {
                location.href = "MoneyExchangeServiceStats";

            });
        });





