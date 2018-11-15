$("#addAtendimento").submit(function (e) {

    e.preventDefault();
    let tipo = $("#tipo").val();
    let produto = $("#produto").val();
    let descricao = $("#descricao").val();

    $.ajax({
        url: "Atendimento",
        data: {
            tipo,
            produto,
            descricao,
            action: "addAtendimento"
        },
        method: "POST",
        dataType: "json"
    }).done(function (res) {

        if (res.status) {

            noty({
                text: `✔️ Atendimento cadastrado`,
                type: "success"
            });

            $(this).reset();

        } else {
            noty({
                text: `😕 Erro ao cadastrar um atendimento`,
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
            open: "animated bounceInRight",
            close: "animated bounceOutRight"
        }
    }).show();
}