$("#addProduto").submit(function (e) {
  e.preventDefault();

  let nome = $("#nome").val();
  let descricao = $("#descricao").val();
  let categoria = $("#categoria").val();
  let peso = $("#peso").val();
  let table = $("table tbody").get(0);
  let qtdProdutos = $("table tbody").children().length + 1;
  let action = "addProduto";
  let actionMsg = "cadastrada";
  let id = "";

  if ($("#cancel-update-produto").is(":visible")) {
    action = "updateProduto";
    actionMsg = "atualizada";
    id = localStorage.getItem("idProduto");
  }

  $.ajax({
      url: "Produto",
      data: {
        nome,
        descricao,
        categoria,
        peso,
        action,
        id
      },
      method: "POST",
      dataType: "json"
    })
    .done(function (res) {

      if (id != "" && res.status) {

        noty({
          text: `✔️ Produto atualizado`,
          type: "success"
        });

        let idProduto = localStorage.getItem("idProduto");
        let nomeCategoria = $("#categoria option:selected").text();
        $(`#produto-nome-${idProduto}`).text($("#nome").val());
        $(`#produto-peso-${idProduto}`).text($("#peso").val());
        $(`#produto-categoria-${idProduto}`).text(nomeCategoria);
        cancelaUpdate();
        $("#addProduto").trigger("reset");

      } else if (res.status) {

        let tr = $("<tr>");
        let acoes = `<td><span class="produto-remove pointer" data-id="${res.data.id}"><i class="icon-red fas fa-trash-alt"></i></span>`;
        acoes += `<span class="produto-update pointer" data-id="${res.data.id}"><i class="fas fa-pen"></i></span></td>`;
        let categoria = "<td>Perfume</td>";
        let pesoProduto = `<td>${peso}</td>`;
        let num = `<td>${qtdProdutos}</td>`;
        let nome = $("<td>", {
          id: `produto-nome-${res.data.id}`,
          text: `${res.data.nome}`
        });

        $(table).append($(tr).append(num, nome, categoria, pesoProduto, acoes));

        noty({
          text: `✔️ Produto cadastrado`,
          type: "success"
        });
        $("#addProduto").trigger("reset");
      } else {
        noty({
          text: `😕 Erro ao ${actionMsg}r o Produto`,
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
$(document).on("click", ".produto-remove", function (e) {
  let idProduto = $(this).data("id");
  let tr = $(this).parents("tr");

  swal({
      title: "Excluir Produto?",
      icon: "warning",
      buttons: true
    })
    .then(willDelete => {
      if (willDelete) {
        return fetch(
          `http://localhost:8080/BeibeProject/Produto?action=removeProduto&id=${idProduto}`
        );
      }
    })
    .then(results => {
      return results.json();
    })
    .then(json => {
      if (json.status) {
        swal({
          title: "Produto Excluido!",
          icon: "success"
        }).then(value => {

          $(tr).addClass("animated fadeOutRight");
          setTimeout(function () {
            $(tr).remove();
          }, 400);
        });
      } else {
        swal({
          title: "Ops ocorreu um erro!",
          text: "Ocorreu um erro ao remove um produto.Tente novamente",
          icon: "error",
          button: "Ok"
        });
      }
    });
});

$(document).on("click", ".produto-update", function (e) {

  let idProduto = $(this).data("id");

  $.ajax({
    url: "ProdutoServlet",
    data: {
      action: "getProduto",
      idProduto
    },
    method: "POST",
    dataType: "json"
  }).done(function (res) {
    localStorage.setItem("idProduto", idProduto);

    $("#nome").val(res.data.nome);
    $("#peso").val(res.data.peso);
    $("#descricao").val(res.data.descricao);
    $(`#produto-peso-${idProduto}`).text($("#peso").val());
    $(`#categoria option[value=${res.data.categoria.id}]`).prop('selected', true);
    $("#cancel-update-produto").show();

  });
});

$(document).on("click", "#cancel-update-produto", function (e) {
  cancelaUpdate();
});

function cancelaUpdate() {
  $("#cancel-update-produto").hide();
  localStorage.clear();
  $("#nome").val(" ");
  $("#nome").val(" ");
  $("#peso").val(" ");
  $("#descricao").val(" ");
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
      open: "animated bounceInRight",
      close: "animated bounceOutRight"
    }
  }).show();
}