/**
 * @description Abre um dialog para confirmar a exclusao de um atendimento
 */
$(document).on("click", ".atendimento-remove", function (e) {

    let idAtendimento = $(this).data("id");

    swal({
        title: "Excluir Atendimento?",
        text: "Esse atendimento não podera ser resolvidor após a exclusão?",
        icon: "warning",
        buttons: true,

    }).then((willDelete) => {

        if (willDelete) {
            return fetch(`http://localhost:8080/BeibeProject/Atendimento?action=removeAtendimento&id=${idAtendimento}`);
        }

    }).then(results => {
        return results.json();
    }).then(res => {

        if (res.status) {
            swal({
                title: "Atendimento Excluido!",
                icon: "success"
            }).then((value) => {

                window.location.replace("http://localhost:8080/BeibeProject/Cliente");
            });

        } else {

            swal({
                title: "Ops ocorreu um erro!",
                text: "Ocorreu um erro ao remover um atendimento.Tente novamente",
                icon: "error",
                button: "Ok",
            });
        }

    });
});