document.addEventListener("DOMContentLoaded", function () {
    const modal = document.getElementById("modalStatus");

    modal.addEventListener("show.bs.modal", function (event) {
        const button = event.relatedTarget;

        const id = button.getAttribute("data-id");
        const status = button.getAttribute("data-status");

        document.getElementById("idDoacao").value = id;
        document.getElementById("statusDoacao").value = status;
    });
});
