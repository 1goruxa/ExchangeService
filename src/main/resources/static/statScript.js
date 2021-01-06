$(function(){
        const appendCur = function(data){
                var CurCode =
                    data.name + ': ' + data.amount + '<br>';
                $('#cur_stat')
                    .append('<div>' + CurCode + '</div>');
            };

        $.ajax
               ({
                    method: "GET",
                    url: '/MoneyExchangeServiceStat/',
                    contentType: "application/json; charset=utf-8",
                    success: function(response) {
                        for(i in response) {
                               appendCur(response[i]);
                        }
                    }
                });
            });

$(function() {
            const appendStat = function(data){
                            var record = JSON.parse(data);

                            $('#filtered_stat')
                                .append(record.id + '. ' + record.user.name + ': ' + record.amount + ' ' + record.curName + '<br>');
                        };


            $('#filter_button').click(function() {
            let filterType = document.getElementById("filter_type").value;
            let filterAmount = document.getElementById("amount").value;

              $.ajax
                   ({
                        method: "GET",
                        url: '/filter/',
                        contentType: "application/json; charset=utf-8",
                        data:{
                            type: filterType,
                            amount: filterAmount
                        },
                        success: function(response) {
                           $('#filtered_stat').empty();

                            for(i in response) {
                               var resp =  JSON.stringify(response[i]);
                               appendStat(resp);
                        }
                        }
                    });
            });
        });