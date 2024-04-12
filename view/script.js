const url = "http://localhost:8080/client";

function hideLoader() {
    document.getElementById("loading").style.display = "none";
}

function show(clients) {
    let tab = `<thead>
              <th scope="col">#<th>
              <th scope="col">Description<th>
              <th scope="col">Username<th>
              <th scope="col">User Id<th>
                </thead>`;

    for (let client of clients) {
        tab+= `
            <tr>
            <td scope="row">${client.client.id}</td>
            <td>${client.client.name}</td>
            <td>${client.client.cep}</td>
            </tr>
        `;
    }

    document.getElementById("clients").innerHTML = tab;

}

async function getClients(url) {
       const response = await fetch(url, {method: "GET"})

       var data = await response.json();
       console.log(data);
       if (response)
            hideLoader();
           show(data.clients);


 }

 getClients(url);