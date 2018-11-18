$("#addCategoria").submit(function (e) {

    e.preventDefault();

    let nomeCategoria = $("#nomeCategoria").val();
    let table = $("table tbody").get(0);
    let qtdCategorias = $("table tbody").children().length + 1;
    let action = "addCategoria";
    let actionMsg = "cadastrada";
    let id = "";

    if ($(".cancel-update ").is(":visible")) {
        action = "updateCategoria";
        actionMsg = "atualizada";
        id = localStorage.getItem("idCategoria");
    }

    $.ajax({
        url: "Categoria",
        data: {
            nomeCategoria,
            action,
            id
        },
        method: "POST",
        dataType: 'json'
    }).done(function (res) {

        if (id != "") {

            noty({
                text: `✔️ Categoria atualizada`,
                type: "success"
            });

            $(`#categoria-nome-${id}`).text(nomeCategoria);
            cancelaUpdate();

        } else if (res.status) {

            let tr = $("<tr>");
            let remove = `<td><span class="categoria-remove" data-id="${res.data.id}"><i class="icon-red fas fa-trash-alt"></i></span>`;
            remove += `<span class="categoria-update pointer" data-id="${res.data.id}"><i class="fas fa-pen"></i></span></td>`;

            let num = `<td>${qtdCategorias}</td>`
            let nome = $("<td>", {
                id: `categoria-nome-${res.data.id}`,
                text: `${res.data.nome}`
            });

            $(table).append($(tr).append(num, nome, remove));

            noty({
                text: `✔️ Categoria cadastrada`,
                type: "success"
            });

        } else {

            noty({
                text: `😕 Erro ao ${actionMsg}r Categoria`,
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
 * @description Abre um dialog para confirmar a exclusao de um cliente
 */
$(document).on("click", ".categoria-remove", function (e) {

    let idCategoria = $(this).data("id");
    let tr = $(this).parents("tr");

    swal({
        title: "Excluir Categoria?",
        text: "Todos os produtos que possuirem essa categoria serão excluidos?",
        icon: "warning",
        buttons: true,

    }).then((willDelete) => {

        if (willDelete) {
            return fetch(`http://localhost:8080/BeibeProject/Categoria?action=removeCategoria&id=${idCategoria}`);
        }

    }).then(results => {
        return results.json();
    }).then(json => {

        if (json.status) {
            swal({
                title: "Categoria Excluida!",
                icon: "success"
            }).then((value) => {

                $(tr).addClass('animated fadeOutRight');
                setTimeout(function () {
                    $(tr).remove();
                }, 400);
            });

        } else {

            swal({
                title: "Ops ocorreu um erro!",
                text: "Ocorreu um erro ao remove um categoria.Tente novamente",
                icon: "error",
                button: "Ok",
            });
        }

    });
});


$(document).on("click", ".categoria-update", function (e) {

    let idCategoria = $(this).data("id");
    let nome = $(`#categoria-nome-${idCategoria}`).text();
    console.log(nome);
    localStorage.setItem("idCategoria", idCategoria);

    $(".cancel-update ").show();
    $("#nomeCategoria").val(nome);
});


$(document).on("click", ".cancel-update", function (e) {
    cancelaUpdate();
});

function cancelaUpdate() {
    $(".cancel-update").hide();
    localStorage.clear();
    $("#nomeCategoria").val(" ");
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