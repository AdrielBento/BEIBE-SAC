/**
 * @description Abre um dialog para confirmar a exclusao de um cliente
 */
$(document).on("click", ".usuario-remove", function (e) {

    let idUsuario = $(this).data("id");
    let tr = $(this).parents("tr");

    swal({
        title: "Excluir Usuario?",
        // text: "Todos os produtos que possuirem essa categoria serão excluidos?",
        icon: "warning",
        buttons: true,

    }).then((willDelete) => {

        if (willDelete) {
            return fetch(`http://localhost:8080/BeibeProject/Usuario?action=removeUsuario&idUsuario=${idUsuario}`);
        }

    }).then(results => {
        return results.json();
    }).then(json => {

        if (json.status) {
            swal({
                title: "Usuario Excluido!",
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