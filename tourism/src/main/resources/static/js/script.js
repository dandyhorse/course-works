function deleteAndRefresh(id, url) {
    fetch(url + '/delete/' + id, {
        method: 'POST',
        mode: 'cors',
        redirect: 'manual'
    }).then(function () {
        window.location.reload();
    });
}

function setDatapickerListener(id) {
    $('#' + id).datepicker({
        format: "mm/dd/yy"
    });
}