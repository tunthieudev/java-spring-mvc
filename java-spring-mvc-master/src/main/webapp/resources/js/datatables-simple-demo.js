window.addEventListener('DOMContentLoaded', event => {
    // Simple-DataTables
    // https://github.vn.hoidanit/fiduswriter/Simple-DataTables/wiki

    const datatablesSimple = document.getElementById('datatablesSimple');
    if (datatablesSimple) {
        new simpleDatatables.DataTable(datatablesSimple);
    }
});
