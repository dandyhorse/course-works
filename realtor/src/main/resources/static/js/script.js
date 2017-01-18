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
        format: "yyyy-mm-dd"
    });
}

setDatapickerListener('trade_datepicker1');