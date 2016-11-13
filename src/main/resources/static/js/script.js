// '/tourists/edit?id=' + ${tourist.id}}"

function deleteTourist(id, url) {
    alert(id);
    alert(url);

    fetch(url + '/delete', {
        method: 'POST',
        mode: 'cors',
        redirect: 'follow',
        headers: new Headers({
            'Content-Type': 'application/json'
            // text/plain
        }),
        body: JSON.stringify({id: +id})
    }).then(function (response) {
        return response.text();
    });
}