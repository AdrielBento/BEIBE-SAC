    /**
     * @description Realiza uma requisicao para preencher o combo de cidades
     */
    $("#estado").change(function (e) {

        let idEstado = $("#estado").val();
        let url = "Cidade";
        let action = "getCidades";

        $.ajax({
            url: url,
            data: {
                idEstado,
                action
            },
            method: "POST",
            dataType: 'json'
        }).done(function (res) {

            if (res.status) {

                $("#cidade").empty();
                $.each(res.data, function (i, obj) {
                    $("#cidade").append(`<option value="${obj.id}">${obj.nome}</option>`);
                });
            } else {

                noty({
                    text: "😕 Erro ao buscar as cidades",
                    type: "error"
                });
            }


        }).fail(function (jqXHR, textStatus) {
            console.error(jqXHR, textStatus);
            noty({
                text: " ❌ Ops,ocorreu um erro.Tente novamente",
                type: "error"
            });
        });
    });


    /**
     * @description Inicializa as mascaras
     */
    +
    function init() {
        $('#cep').mask('00000-000');
        $('#cpf').mask('000.000.000-00');
        $("#telefone").mask('(00) 0000-0000');
    }();

    $("#formUsuario").submit(function (e) {

        e.preventDefault();

        let cpf = $("#cpf").val();
        cpf = cpf.replace(/[^\d]+/g, '');
        let type = $(this).data("type");

        if (!validaCPF(cpf)) {

            noty({
                text: " ❌ CPF invalido",
                type: "error"
            });

        } else {


            let form = $("#formUsuario").serializeArray();
            let msg = type == "addUsuario" ? "adiciona" : "atualiza";

            form.push({
                name: "action",
                value: `${type}`
            });

            $.ajax({
                url: "Usuario",
                data: form,
                method: "POST",
                dataType: "json"
            }).done(function (res) {

                if (res.status) {
                    noty({
                        text: `✔️ Usuario ${msg}do`,
                        type: "success"
                    });

                } else {

                    noty({
                        text: `😕 Erro ao ${msg}r Usuario`,
                        type: "error"
                    });
                }


            }).fail(function (jqXHR, textStatus) {
                console.error(jqXHR, textStatus);
                noty({
                    text: " ❌ Ops,ocorreu um erro.Tente novamente",
                    type: "error"
                });
            });

        }
    });

    /**
     * @description validador de CPF
     * @param {String} strCPF 
     */
    function validaCPF(strCPF) {

        let soma;
        let resto;

        soma = 0;

        if (strCPF == "00000000000") return false;

        for (i = 1; i <= 9; i++) {
            soma = soma + parseInt(strCPF.substring(i - 1, i)) * (11 - i);
        }

        resto = (soma * 10) % 11;

        if ((resto == 10) || (resto == 11)) resto = 0;
        if (resto != parseInt(strCPF.substring(9, 10))) return false;

        soma = 0;
        for (i = 1; i <= 10; i++) soma = soma + parseInt(strCPF.substring(i - 1, i)) * (12 - i);
        resto = (soma * 10) % 11;

        if ((resto == 10) || (resto == 11)) resto = 0;
        if (resto != parseInt(strCPF.substring(10, 11))) return false;

        return true;
    }

    function noty(config) {

        let {
            text,
            type
        } = config;

        new Noty({

            text: config.text,
            type: config.type,
            timeout: 3500,
            progressBar: true,
            theme: "metroui",
            animation: {
                open: 'animated bounceInRight',
                close: 'animated bounceOutRight'
            }

        }).show();
    }